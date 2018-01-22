package decaf.translate;

import java.util.Stack;
import java.util.ArrayList;

import decaf.Driver;
import decaf.tree.Tree;
import decaf.backend.OffsetCounter;
import decaf.machdesc.Intrinsic;
import decaf.scope.ClassScope;
import decaf.scope.Scope;
import decaf.symbol.Symbol;
import decaf.symbol.Class;
import decaf.symbol.Function;
import decaf.symbol.Variable;
import decaf.tac.Label;
import decaf.tac.Temp;
import decaf.tac.VTable;
import decaf.type.ClassType;
import decaf.type.BaseType;

public class TransPass2 extends Tree.Visitor {

	private Translater tr;

	private Temp currentThis;

	private Function currentFunc;

	private Stack<Label> loopExits;

	public TransPass2(Translater tr) {
		this.tr = tr;
		loopExits = new Stack<Label>();
	}

	@Override
	public void visitClassDef(Tree.ClassDef classDef) {
		for (Tree f : classDef.fields) {
			f.accept(this);
		}
	}

	@Override
	public void visitMethodDef(Tree.MethodDef funcDefn) {
		currentFunc = funcDefn.symbol;
		if (!funcDefn.statik) {
			currentThis = ((Variable) funcDefn.symbol.getAssociatedScope()
					.lookup("this")).getTemp();
		}
		tr.beginFunc(funcDefn.symbol);
		funcDefn.body.accept(this);
		tr.endFunc();
		currentThis = null;
		currentFunc = null;
	}

	@Override
	public void visitTopLevel(Tree.TopLevel program) {
		for (Tree.ClassDef cd : program.classes) {
			cd.accept(this);
		}
	}

	@Override
	public void visitVarDef(Tree.VarDef varDef) {
		if (varDef.symbol.isLocalVar()) {
			Temp t;
			if (varDef.type.type.equal(BaseType.COMPLEX)) {
				t = tr.genNewComplex();
			} else {
				t = Temp.createTempI4();
			}
			t.sym = varDef.symbol;
			varDef.symbol.setTemp(t);
		}
	}

	@Override
	public void visitBinary(Tree.Binary expr) {
		expr.left.accept(this);
		expr.right.accept(this);
		switch (expr.tag) {
		case Tree.PLUS:
			if (expr.left.type.equal(BaseType.COMPLEX) || expr.right.type.equal(BaseType.COMPLEX)) {	
				expr.val = tr.genNewComplex();
				Temp left = expr.left.val;
				Temp right = expr.right.val;
				if (expr.left.type.equal(BaseType.INT)) left = tr.genToComplex(left);
				if (expr.right.type.equal(BaseType.INT)) right = tr.genToComplex(right);
				Temp leftReal = tr.genLoadReal(left);
				Temp leftImg = tr.genLoadImg(left);
				Temp rightReal = tr.genLoadReal(right);
				Temp rightImg = tr.genLoadImg(right);
				Temp dstReal = tr.genAdd(leftReal, rightReal);
				Temp dstImg = tr.genAdd(leftImg, rightImg);
				tr.genStoreReal(dstReal, expr.val);
				tr.genStoreImg(dstImg, expr.val);
			} else {
				expr.val = tr.genAdd(expr.left.val, expr.right.val);
			}
			break;
		case Tree.MINUS:
			expr.val = tr.genSub(expr.left.val, expr.right.val);
			break;
		case Tree.MUL:
			if (expr.left.type.equal(BaseType.COMPLEX) || expr.right.type.equal(BaseType.COMPLEX)) {	
				expr.val = tr.genNewComplex();
				Temp left = expr.left.val;
				Temp right = expr.right.val;
				if (expr.left.type.equal(BaseType.INT)) left = tr.genToComplex(left);
				if (expr.right.type.equal(BaseType.INT)) right = tr.genToComplex(right);
				Temp leftReal = tr.genLoadReal(left);
				Temp leftImg = tr.genLoadImg(left);
				Temp rightReal = tr.genLoadReal(right);
				Temp rightImg = tr.genLoadImg(right);
				Temp dstReal = tr.genSub(tr.genMul(leftReal, rightReal), tr.genMul(leftImg, rightImg));
				Temp dstImg = tr.genAdd(tr.genMul(leftReal, rightImg), tr.genMul(leftImg, rightReal));
				tr.genStoreReal(dstReal, expr.val);
				tr.genStoreImg(dstImg, expr.val);
			} else {
				expr.val = tr.genMul(expr.left.val, expr.right.val);
			}
			break;
		case Tree.DIV:
			tr.genCheckDivisionByZero(expr.right.val);
			expr.val = tr.genDiv(expr.left.val, expr.right.val);
			break;
		case Tree.MOD:
			tr.genCheckDivisionByZero(expr.right.val);
			expr.val = tr.genMod(expr.left.val, expr.right.val);
			break;
		case Tree.AND:
			expr.val = tr.genLAnd(expr.left.val, expr.right.val);
			break;
		case Tree.OR:
			expr.val = tr.genLOr(expr.left.val, expr.right.val);
			break;
		case Tree.LT:
			expr.val = tr.genLes(expr.left.val, expr.right.val);
			break;
		case Tree.LE:
			expr.val = tr.genLeq(expr.left.val, expr.right.val);
			break;
		case Tree.GT:
			expr.val = tr.genGtr(expr.left.val, expr.right.val);
			break;
		case Tree.GE:
			expr.val = tr.genGeq(expr.left.val, expr.right.val);
			break;
		case Tree.EQ:
		case Tree.NE:
			genEquNeq(expr);
			break;
		}
	}

	private void genEquNeq(Tree.Binary expr) {
		if (expr.left.type.equal(BaseType.STRING)
				|| expr.right.type.equal(BaseType.STRING)) {
			tr.genParm(expr.left.val);
			tr.genParm(expr.right.val);
			expr.val = tr.genDirectCall(Intrinsic.STRING_EQUAL.label,
					BaseType.BOOL);
			if(expr.tag == Tree.NE){
				expr.val = tr.genLNot(expr.val);
			}
		} else {
			if(expr.tag == Tree.EQ)
				expr.val = tr.genEqu(expr.left.val, expr.right.val);
			else
				expr.val = tr.genNeq(expr.left.val, expr.right.val);
		}
	}

	@Override
	public void visitAssign(Tree.Assign assign) {
		assign.left.accept(this);
		assign.expr.accept(this);
		switch (assign.left.lvKind) {
		case ARRAY_ELEMENT:
			Tree.Indexed arrayRef = (Tree.Indexed) assign.left;
			Temp esz = tr.genLoadImm4(OffsetCounter.WORD_SIZE);
			Temp t = tr.genMul(arrayRef.index.val, esz);
			Temp base = tr.genAdd(arrayRef.array.val, t);
			tr.genStore(assign.expr.val, base, 0);
			break;
		case MEMBER_VAR:
			Tree.Ident varRef = (Tree.Ident) assign.left;
			tr.genStore(assign.expr.val, varRef.owner.val, varRef.symbol
					.getOffset());
			break;
		case PARAM_VAR:
		case LOCAL_VAR:
			if (assign.left.type.equal(BaseType.COMPLEX)) {
				Temp left = ((Tree.Ident) assign.left).symbol.getTemp();
				Temp right = assign.expr.val;
				if (assign.expr.type.equal(BaseType.INT)) right = tr.genToComplex(right);
				Temp rightReal = tr.genLoadReal(right);
				Temp rightImg = tr.genLoadImg(right);
				tr.genStoreReal(rightReal, left);
				tr.genStoreImg(rightImg, left);
			} else {
				tr.genAssign(((Tree.Ident) assign.left).symbol.getTemp(),
					assign.expr.val);
			}
			break;
		}
	}

	@Override
	public void visitLiteral(Tree.Literal literal) {
		switch (literal.typeTag) {
		case Tree.INT:
			literal.val = tr.genLoadImm4(((Integer)literal.value).intValue());
			break;
		case Tree.BOOL:
			literal.val = tr.genLoadImm4((Boolean)(literal.value) ? 1 : 0);
			break;
		case Tree.IMG:
			literal.val = tr.genNewComplex();
			tr.genStoreReal(tr.genLoadImm4(0), literal.val);
			tr.genStoreImg(tr.genLoadImm4((Integer)literal.value), literal.val);
			break;
		default:
			literal.val = tr.genLoadStrConst((String)literal.value);
		}
	}

	@Override
	public void visitExec(Tree.Exec exec) {
		exec.expr.accept(this);
	}

	@Override
	public void visitUnary(Tree.Unary expr) {
		expr.expr.accept(this);
		switch (expr.tag){
		case Tree.NEG:
			expr.val = tr.genNeg(expr.expr.val);
			break;
		case Tree.GETREAL:
			expr.val = tr.genLoadReal(expr.expr.val);
			break;
		case Tree.GETIMG:
			expr.val = tr.genLoadImg(expr.expr.val);
			break;
		case Tree.TOCOMPLEX:
			expr.val = tr.genToComplex(expr.expr.val);
			break;
		default:
			expr.val = tr.genLNot(expr.expr.val);
		}
	}

	@Override
	public void visitNull(Tree.Null nullExpr) {
		nullExpr.val = tr.genLoadImm4(0);
	}

	@Override
	public void visitBlock(Tree.Block block) {
		for (Tree s : block.block) {
			s.accept(this);
		}
	}

	@Override
	public void visitThisExpr(Tree.ThisExpr thisExpr) {
		thisExpr.val = currentThis;
	}

	@Override
	public void visitReadIntExpr(Tree.ReadIntExpr readIntExpr) {
		readIntExpr.val = tr.genIntrinsicCall(Intrinsic.READ_INT);
	}

	@Override
	public void visitReadLineExpr(Tree.ReadLineExpr readStringExpr) {
		readStringExpr.val = tr.genIntrinsicCall(Intrinsic.READ_LINE);
	}

	@Override
	public void visitReturn(Tree.Return returnStmt) {
		if (returnStmt.expr != null) {
			returnStmt.expr.accept(this);
			tr.genReturn(returnStmt.expr.val);
		} else {
			tr.genReturn(null);
		}

	}

	@Override
	public void visitPrint(Tree.Print printStmt) {
		for (Tree.Expr r : printStmt.exprs) {
			r.accept(this);
			tr.genParm(r.val);
			if (r.type.equal(BaseType.BOOL)) {
				tr.genIntrinsicCall(Intrinsic.PRINT_BOOL);
			} else if (r.type.equal(BaseType.INT)) {
				tr.genIntrinsicCall(Intrinsic.PRINT_INT);
			} else if (r.type.equal(BaseType.STRING)) {
				tr.genIntrinsicCall(Intrinsic.PRINT_STRING);
			}
		}
	}

	@Override
	public void visitIndexed(Tree.Indexed indexed) {
		indexed.array.accept(this);
		indexed.index.accept(this);
		tr.genCheckArrayIndex(indexed.array.val, indexed.index.val);
		
		Temp esz = tr.genLoadImm4(OffsetCounter.WORD_SIZE);
		Temp t = tr.genMul(indexed.index.val, esz);
		Temp base = tr.genAdd(indexed.array.val, t);
		indexed.val = tr.genLoad(base, 0);
	}

	@Override
	public void visitIdent(Tree.Ident ident) {
		if(ident.lvKind == Tree.LValue.Kind.MEMBER_VAR){
			ident.owner.accept(this);
		}
		
		switch (ident.lvKind) {
		case MEMBER_VAR:
			ident.val = tr.genLoad(ident.owner.val, ident.symbol.getOffset());
			break;
		default:
			ident.val = ident.symbol.getTemp();
			break;
		}
	}
	
	@Override
	public void visitBreak(Tree.Break breakStmt) {
		tr.genBranch(loopExits.peek());
	}

	@Override 
	public void visitSuperExpr(Tree.SuperExpr superExpr) {
		superExpr.val = currentThis;
	}

	@Override
	public void visitCallExpr(Tree.CallExpr callExpr) {
		if (callExpr.isArrayLength) {
			callExpr.receiver.accept(this);
			callExpr.val = tr.genLoad(callExpr.receiver.val,
					-OffsetCounter.WORD_SIZE);
		} else {
			if (callExpr.receiver != null) {
				callExpr.receiver.accept(this);
			}
			for (Tree.Expr expr : callExpr.actuals) {
				expr.accept(this);
			}
			if (callExpr.receiver != null) {
				tr.genParm(callExpr.receiver.val);
			}
			for (Tree.Expr expr : callExpr.actuals) {
				tr.genParm(expr.val);
			}
			if (callExpr.receiver == null) {
				callExpr.val = tr.genDirectCall(
						callExpr.symbol.getFuncty().label, callExpr.symbol
								.getReturnType());
			} else {
                Temp vt = tr.genLoad(callExpr.receiver.val, 0);
                if (callExpr.receiver instanceof Tree.ThisExpr) {
                    ClassType thisType = currentFunc.getScope().getOwner().getType();
                    VTable thisVtable = thisType.getSymbol().getVtable();
                    vt = tr.genLoadVTable(thisVtable);
				} 
				if (callExpr.receiver instanceof Tree.SuperExpr) {
					ClassType superType = currentFunc.getScope().getOwner().getType().getParentType();
					while (superType != null && superType.getClassScope().lookupVisible(callExpr.method) == null) {
						superType = superType.getParentType();
					}
					VTable superVtable = superType.getSymbol().getVtable();
					vt = tr.genLoadVTable(superVtable);
				}
                Temp func = tr.genLoad(vt, callExpr.symbol.getOffset());
                callExpr.val = tr.genIndirectCall(func, callExpr.symbol
                        .getReturnType());
			}
		}

	}

	@Override
	public void visitForLoop(Tree.ForLoop forLoop) {
		if (forLoop.init != null) {
			forLoop.init.accept(this);
		}
		Label cond = Label.createLabel();
		Label loop = Label.createLabel();
		tr.genBranch(cond);
		tr.genMark(loop);
		if (forLoop.update != null) {
			forLoop.update.accept(this);
		}
		tr.genMark(cond);
		forLoop.condition.accept(this);
		Label exit = Label.createLabel();
		tr.genBeqz(forLoop.condition.val, exit);
		loopExits.push(exit);
		if (forLoop.loopBody != null) {
			forLoop.loopBody.accept(this);
		}
		tr.genBranch(loop);
		loopExits.pop();
		tr.genMark(exit);
	}

	@Override
	public void visitIf(Tree.If ifStmt) {
		ifStmt.condition.accept(this);
		if (ifStmt.falseBranch != null) {
			Label falseLabel = Label.createLabel();
			tr.genBeqz(ifStmt.condition.val, falseLabel);
			ifStmt.trueBranch.accept(this);
			Label exit = Label.createLabel();
			tr.genBranch(exit);
			tr.genMark(falseLabel);
			ifStmt.falseBranch.accept(this);
			tr.genMark(exit);
		} else if (ifStmt.trueBranch != null) {
			Label exit = Label.createLabel();
			tr.genBeqz(ifStmt.condition.val, exit);
			if (ifStmt.trueBranch != null) {
				ifStmt.trueBranch.accept(this);
			}
			tr.genMark(exit);
		}
	}

	@Override
	public void visitNewArray(Tree.NewArray newArray) {
		newArray.length.accept(this);
		newArray.val = tr.genNewArray(newArray.length.val);
	}

	@Override
	public void visitNewClass(Tree.NewClass newClass) {
		newClass.val = tr.genDirectCall(newClass.symbol.getNewFuncLabel(),
				BaseType.INT);
	}

	@Override
	public void visitWhileLoop(Tree.WhileLoop whileLoop) {
		Label loop = Label.createLabel();
		tr.genMark(loop);
		whileLoop.condition.accept(this);
		Label exit = Label.createLabel();
		tr.genBeqz(whileLoop.condition.val, exit);
		loopExits.push(exit);
		if (whileLoop.loopBody != null) {
			whileLoop.loopBody.accept(this);
		}
		tr.genBranch(loop);
		loopExits.pop();
		tr.genMark(exit);
	}

	@Override
	public void visitTypeTest(Tree.TypeTest typeTest) {
		typeTest.instance.accept(this);
		typeTest.val = tr.genInstanceof(typeTest.instance.val,
				typeTest.symbol);
	}

	@Override
	public void visitTypeCast(Tree.TypeCast typeCast) {
		typeCast.expr.accept(this);
		if (!typeCast.expr.type.compatible(typeCast.symbol.getType())) {
			tr.genClassCast(typeCast.expr.val, typeCast.symbol);
		}
		typeCast.val = typeCast.expr.val;
	}

	@Override
	public void visitCaseExpr(Tree.CaseExpr caseExpr) {
		caseExpr.val = Temp.createTempI4();

		caseExpr.conditionexpr.accept(this);
		caseExpr.defaultexpr.accept(this);
		tr.genAssign(caseExpr.val, caseExpr.defaultexpr.val);

		Label next = Label.createLabel();		
		for (Tree.ACaseExpr aCaseExpr: caseExpr.casesexpr) {
			tr.genMark(next);
			aCaseExpr.literal.accept(this);
			Temp isEqual = tr.genEqu(caseExpr.conditionexpr.val, aCaseExpr.literal.val);
			tr.genBeqz(isEqual, next = Label.createLabel());
			aCaseExpr.expr.accept(this);
			tr.genAssign(caseExpr.val, aCaseExpr.expr.val);
		}
		tr.genMark(next); // Guard for end
	}

	@Override
    public void visitDoStmt(Tree.DoStmt doStmt) {
        Label loop = Label.createLabel();
        Label exit = Label.createLabel();

        tr.genMark(loop);
        loopExits.push(exit);
		
		Label next = Label.createLabel();
		for (Tree.DoSubStmt doSubStmt: doStmt.dslist) {
			tr.genMark(next);
			doSubStmt.expr.accept(this);
			tr.genBeqz(doSubStmt.expr.val, next = Label.createLabel());
			doSubStmt.stmt.accept(this);
			tr.genBranch(loop);
		}
		tr.genMark(next); // Guard for end

		loopExits.pop();
		tr.genMark(exit);
	}

	@Override
	public void visitPrintComp(Tree.PrintComp printCompStmt) {
		for (Tree.Expr r : printCompStmt.exprs) {
			r.accept(this);
			tr.genParm(tr.genLoadReal(r.val));
			tr.genIntrinsicCall(Intrinsic.PRINT_INT);
			tr.genParm(tr.genLoadStrConst("+"));
			tr.genIntrinsicCall(Intrinsic.PRINT_STRING);
			tr.genParm(tr.genLoadImg(r.val));
			tr.genIntrinsicCall(Intrinsic.PRINT_INT);
			tr.genParm(tr.genLoadStrConst("j"));
			tr.genIntrinsicCall(Intrinsic.PRINT_STRING);			
		}
	}

	@Override
    public void visitScopyExpr(Tree.ScopyExpr scopyExpr) {
        scopyExpr.expr.accept(this);
        Temp src = scopyExpr.expr.val;

        Class cls = ((ClassType) scopyExpr.expr.type).getSymbol();
        Temp dst = tr.genDirectCall(cls.getNewFuncLabel(), BaseType.INT);
        scopyExpr.val = dst;

        Temp vtable = tr.genLoad(src, 0);
		tr.genStore(vtable, dst, 0);
		
        for (int offset = OffsetCounter.WORD_SIZE; offset < cls.getSize(); offset += OffsetCounter.WORD_SIZE) {
            Temp var = tr.genLoad(src, offset);

            if (cls.getTypeMap().get(offset).equal(BaseType.COMPLEX)) {
                Temp real = tr.genLoadReal(var);
				Temp img = tr.genLoadImg(var);
				Temp newcomp = tr.genNewComplex();
				tr.genStoreReal(real, newcomp);
				tr.genStoreImg(img, newcomp);
                tr.genStore(newcomp, dst, offset);
            } else {
				tr.genStore(var, dst, offset);
            }
        }
    }

    @Override
    public void visitDcopyExpr(Tree.DcopyExpr dcopyExpr) {
        dcopyExpr.expr.accept(this);
        Temp src = dcopyExpr.expr.val;
        Class cls = ((ClassType) dcopyExpr.expr.type).getSymbol();
        dcopyExpr.val = dfsCopy(cls, src);
    }

    private Temp dfsCopy(Class cls, Temp src) {
        Temp dst = tr.genDirectCall(cls.getNewFuncLabel(), BaseType.INT);

        Temp vtable = tr.genLoad(src, 0);
        tr.genStore(vtable, dst, 0);

        for (int offset = OffsetCounter.WORD_SIZE; offset < cls.getSize(); offset += OffsetCounter.WORD_SIZE) {
            Temp var = tr.genLoad(src, offset);

            if (cls.getTypeMap().get(offset).isClassType()) {
                Class classtype = ((ClassType) cls.getTypeMap().get(offset)).getSymbol();
                Temp newclass = dfsCopy(classtype, var);
                tr.genStore(newclass, dst, offset);
            } else if (cls.getTypeMap().get(offset).equal(BaseType.COMPLEX)) {
                Temp real = tr.genLoadReal(var);
				Temp img = tr.genLoadImg(var);
				Temp newcomp = tr.genNewComplex();
				tr.genStoreReal(real, newcomp);
				tr.genStoreImg(img, newcomp);
                tr.genStore(newcomp, dst, offset);
            } else {
                tr.genStore(var, dst, offset);
            }
        }
        return dst;
    }

}
