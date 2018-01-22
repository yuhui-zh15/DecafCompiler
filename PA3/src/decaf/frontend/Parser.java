//### This file created by BYACC 1.8(/Java extension  1.13)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//###           14 Sep 06  -- Keltin Leung-- ReduceListener support, eliminate underflow report in error recovery
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 11 "Parser.y"
package decaf.frontend;

import decaf.tree.Tree;
import decaf.tree.Tree.*;
import decaf.error.*;
import java.util.*;
//#line 25 "Parser.java"
interface ReduceListener {
  public boolean onReduce(String rule);
}




public class Parser
             extends BaseParser
             implements ReduceListener
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

ReduceListener reduceListener = null;
void yyclearin ()       {yychar = (-1);}
void yyerrok ()         {yyerrflag=0;}
void addReduceListener(ReduceListener l) {
  reduceListener = l;}


//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:SemValue
String   yytext;//user variable to return contextual strings
SemValue yyval; //used to return semantic vals from action routines
SemValue yylval;//the 'lval' (result) I got from yylex()
SemValue valstk[] = new SemValue[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new SemValue();
  yylval=new SemValue();
  valptr=-1;
}
final void val_push(SemValue val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    SemValue[] newstack = new SemValue[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final SemValue val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final SemValue val_peek(int relative)
{
  return valstk[valptr-relative];
}
//#### end semantic value section ####
public final static short VOID=257;
public final static short BOOL=258;
public final static short INT=259;
public final static short STRING=260;
public final static short CLASS=261;
public final static short NULL=262;
public final static short EXTENDS=263;
public final static short THIS=264;
public final static short WHILE=265;
public final static short FOR=266;
public final static short IF=267;
public final static short ELSE=268;
public final static short RETURN=269;
public final static short BREAK=270;
public final static short NEW=271;
public final static short PRINT=272;
public final static short READ_INTEGER=273;
public final static short READ_LINE=274;
public final static short LITERAL=275;
public final static short IDENTIFIER=276;
public final static short AND=277;
public final static short OR=278;
public final static short STATIC=279;
public final static short INSTANCEOF=280;
public final static short LESS_EQUAL=281;
public final static short GREATER_EQUAL=282;
public final static short EQUAL=283;
public final static short NOT_EQUAL=284;
public final static short SUPER=285;
public final static short DCOPY=286;
public final static short SCOPY=287;
public final static short COMPLEX=288;
public final static short PRINTCOMP=289;
public final static short CASE=290;
public final static short DEFAULT=291;
public final static short DO=292;
public final static short OD=293;
public final static short NEXT=294;
public final static short UMINUS=295;
public final static short EMPTY=296;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    4,    5,    5,    5,    5,    5,
    5,    5,    2,    6,    6,    7,    7,    7,    9,    9,
   10,   10,    8,    8,   11,   12,   12,   13,   13,   13,
   13,   13,   13,   13,   13,   13,   13,   13,   22,   24,
   24,   23,   14,   14,   14,   28,   28,   26,   26,   27,
   25,   25,   25,   25,   25,   25,   25,   25,   25,   25,
   25,   25,   25,   25,   25,   25,   25,   25,   25,   25,
   25,   25,   25,   25,   25,   25,   25,   25,   25,   25,
   25,   25,   25,   31,   31,   33,   32,   30,   30,   29,
   29,   34,   34,   16,   17,   20,   15,   35,   35,   18,
   18,   19,   21,
};
final static short yylen[] = {                            2,
    1,    2,    1,    2,    2,    1,    1,    1,    1,    1,
    2,    3,    6,    2,    0,    2,    2,    0,    1,    0,
    3,    1,    7,    6,    3,    2,    0,    1,    2,    1,
    1,    1,    2,    2,    2,    2,    1,    2,    4,    3,
    0,    3,    3,    1,    0,    2,    0,    2,    4,    5,
    1,    1,    1,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    2,    2,    2,
    2,    2,    3,    3,    1,    1,    4,    5,    6,    5,
    4,    4,    8,    2,    0,    4,    4,    1,    1,    1,
    0,    3,    1,    5,    9,    1,    6,    2,    0,    2,
    1,    4,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    3,    0,    2,    0,    0,   14,   18,
    0,    7,    8,    6,    9,    0,    0,   10,   13,   16,
    0,    0,   17,   11,    0,    4,    0,    0,    0,    0,
   12,    0,   22,    0,    0,    0,    0,    5,    0,    0,
    0,   27,   24,   21,   23,    0,   89,   75,    0,    0,
    0,    0,   96,    0,    0,    0,    0,   88,    0,   76,
    0,    0,    0,    0,    0,    0,    0,    0,   25,    0,
    0,    0,   28,   37,   26,    0,   30,   31,   32,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   53,    0,
    0,    0,    0,   51,   52,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   70,   71,   72,   29,   33,   34,   35,   36,   38,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   46,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   73,   74,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   67,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   77,    0,    0,  102,    0,
   81,   82,  103,    0,    0,   39,   42,    0,   49,    0,
    0,   94,    0,    0,   78,    0,    0,   85,   40,   80,
   50,    0,    0,   97,   79,    0,    0,   98,    0,    0,
    0,   84,    0,    0,    0,   83,   95,    0,    0,   87,
   86,
};
final static short yydgoto[] = {                          2,
    3,    4,   73,   21,   34,    8,   11,   23,   35,   36,
   74,   46,   75,   76,   77,   78,   79,   80,   81,   82,
   83,   84,  106,  153,   85,   94,   95,   88,  190,   89,
  206,  211,  212,  144,  204,
};
final static short yysindex[] = {                      -252,
 -230,    0, -252,    0, -216,    0, -225,  -71,    0,    0,
  -82,    0,    0,    0,    0, -220, -108,    0,    0,    0,
   -1,  -74,    0,    0,  -73,    0,   26,  -13,   32, -108,
    0, -108,    0,  -72,   34,   47,   52,    0,  -23, -108,
  -23,    0,    0,    0,    0,    5,    0,    0,   63,   64,
   67,  101,    0,  234,   68,   70,   71,    0,   73,    0,
   75,   78,   79,   80,  101,  101,  101,   61,    0,  101,
  101,  101,    0,    0,    0,   65,    0,    0,    0,   72,
   74,   76,   84,   85,  862,   62,    0, -154,    0,  101,
  101,  101,  862,    0,    0,   89,   48,  101,   91,   97,
  101,  101,  101,  101,  101, -147,  466,  -21,  -21, -136,
  492,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,
  101,  101,  101,    0,  101,  101,  108,  503,   95,  525,
  114,   81,  862,   27,    0,    0,  553,  577,  665,   43,
  691,  101, -137,   41,  116,    0,  920,  894,   18,   18,
  -32,  -32,   36,   36,  -21,  -21,  -21,   18,   18,  751,
  862,  101,   41,  101,   41,    0,  778,  101,    0, -118,
    0,    0,    0,   37, -147,    0,    0,  101,    0,  118,
  129,    0,  804,  -87,    0,  862,  141,    0,    0,    0,
    0,  101,   41,    0,    0, -242,  148,    0,  142,  147,
   82,    0,   41,  101,  101,    0,    0,  830,  841,    0,
    0,
};
final static short yyrindex[] = {                         0,
    0,    0,  199,    0,   92,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  149,    0,    0,  171,
    0,  171,    0,    0,    0,  173,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -55,    0,    0,    0,    0,
    0,  -53,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -59,  -59,  -59,  -59,    0,  -59,
  -59,  -59,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  873,  440,    0,    0,  -59,
  -55,  -59,  160,    0,    0,    0,    0,  -59,    0,    0,
  -59,  -59,  -59,  -59,  -59,  -63,    0,  151,  352,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  -59,  -59,  -59,  -59,  -59,  -59,  -59,  -59,  -59,  -59,
  -59,  -59,  -59,    0,  -59,  -59,  125,    0,    0,    0,
    0,  -59,   54,    0,    0,    0,    0,    0,    0,    0,
    0,  -59,    0,  -55,    0,    0,  981,   -5,  760,  821,
 1007, 1009,  943,  954,  378,  405,  414,  886,  965,    0,
    3,  -25,  -55,  -59,  -55,    0,    0,  -59,    0,    0,
    0,    0,    0,    0,  -63,    0,    0,  -59,    0,    0,
  179,    0,    0,  -33,    0,   58,    0,    0,    0,    0,
    0,  -18,  -55,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -55,  -59,  -59,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,  219,  224,   -8,   31,    0,    0,    0,  213,    0,
   -4,    0, -146,  -90,    0,    0,    0,    0,    0,    0,
    0,    0,   94,   83, 1198,  -12, 1006,    0,    0,   42,
    0,    0,    0,  -83,    0,
};
final static int YYTABLESIZE=1413;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         99,
  139,   99,   99,   45,  131,  101,   99,  187,    1,  129,
  127,   99,  128,  134,  130,   91,   28,   28,   28,   47,
  150,   33,   45,   33,  134,   99,  192,  133,  194,  132,
   99,   44,   58,   86,   43,   66,   45,   67,   66,   72,
   71,   22,   19,   43,   68,    5,    7,   25,  209,   66,
    9,   10,   66,   66,  131,   24,  208,   26,  135,  129,
  127,   43,  128,  134,  130,   30,  217,  179,   70,  135,
  178,   32,  131,   67,   39,   72,   71,  129,   86,   31,
   68,  134,  130,  183,   97,   66,  178,   66,  191,   99,
   40,   99,   41,   67,   93,   72,   71,   93,   92,   42,
   68,   92,   90,   91,   70,   66,   92,   98,  135,   99,
  100,  207,  101,   67,  102,   72,   71,  103,  104,  105,
   68,  137,  136,  115,   70,   66,  135,   42,  141,   69,
  116,  145,  117,   67,  118,   72,   71,  146,  142,  155,
   68,   86,  119,  120,   70,   66,  152,  172,   12,   13,
   14,   15,   16,  174,  176,  186,  188,  197,  201,  198,
   86,   48,   86,   42,   70,   48,   48,   48,   48,   48,
   48,   48,  178,   31,   12,   13,   14,   15,   16,   18,
  203,  205,   48,   48,   48,   48,   48,   68,  213,   86,
   86,   68,   68,   68,   68,   68,   17,   68,    1,  214,
   86,   27,   29,   38,  215,   18,  216,    5,   68,   68,
   68,   20,   68,   19,   15,   48,   47,   48,  100,   90,
   47,    6,   47,   99,   99,   99,   99,   99,   99,   41,
   99,   99,   99,   99,   20,   99,   99,   99,   99,   99,
   99,   99,   99,   68,   37,  185,   99,  210,  123,  124,
   47,   99,   99,   99,   99,   99,   99,   47,   99,   99,
   99,   12,   13,   14,   15,   16,   47,  199,   48,   49,
   50,   51,   66,   52,   53,   54,   55,   56,   57,   58,
    0,    0,    0,    0,   59,    0,    0,    0,    0,   60,
   61,   62,   18,   63,   64,    0,   65,   12,   13,   14,
   15,   16,   47,    0,   48,   49,   50,   51,    0,   52,
   53,   54,   55,   56,   57,   58,    0,    0,    0,    0,
   59,  110,   47,    0,   48,   60,   61,   62,   18,   63,
   64,   54,   65,   56,   57,   58,    0,    0,    0,    0,
   59,    0,   47,    0,   48,   60,   61,   62,    0,    0,
   64,   54,    0,   56,   57,   58,    0,    0,    0,    0,
   59,    0,   47,    0,   48,   60,   61,   62,    0,    0,
   64,   54,    0,   56,   57,   58,    0,    0,    0,    0,
   59,    0,    0,    0,    0,   60,   61,   62,   69,    0,
   64,    0,   69,   69,   69,   69,   69,    0,   69,    0,
    0,   48,   48,    0,    0,   48,   48,   48,   48,   69,
   69,   69,    0,   69,   56,    0,    0,    0,   56,   56,
   56,   56,   56,    0,   56,    0,    0,   68,   68,    0,
    0,   68,   68,   68,   68,   56,   56,   56,    0,   56,
    0,   57,    0,    0,   69,   57,   57,   57,   57,   57,
   58,   57,    0,    0,   58,   58,   58,   58,   58,    0,
   58,    0,   57,   57,   57,    0,   57,    0,    0,    0,
   56,   58,   58,   58,    0,   58,   52,    0,    0,    0,
   44,   52,   52,    0,   52,   52,   52,    0,    0,    0,
   12,   13,   14,   15,   16,    0,    0,   57,   44,   52,
    0,   52,  131,    0,    0,    0,   58,  129,  127,   96,
  128,  134,  130,    0,    0,    0,    0,    0,    0,    0,
    0,   18,    0,  154,    0,  133,    0,  132,  131,    0,
   52,    0,  156,  129,  127,    0,  128,  134,  130,  131,
    0,    0,    0,  173,  129,  127,    0,  128,  134,  130,
    0,  133,    0,  132,    0,    0,  135,    0,    0,    0,
    0,  131,  133,    0,  132,  175,  129,  127,    0,  128,
  134,  130,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  135,    0,  133,    0,  132,    0,    0,  131,
    0,    0,    0,  135,  129,  127,  180,  128,  134,  130,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  133,  131,  132,  135,    0,  181,  129,  127,
    0,  128,  134,  130,    0,    0,    0,    0,   69,   69,
    0,    0,   69,   69,   69,   69,  133,    0,  132,    0,
    0,    0,    0,  135,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   56,   56,    0,    0,   56,   56,
   56,   56,    0,    0,    0,    0,    0,  135,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   57,   57,    0,    0,   57,   57,   57,   57,    0,
   58,   58,    0,    0,   58,   58,   58,   58,    0,    0,
    0,  131,    0,    0,    0,  182,  129,  127,    0,  128,
  134,  130,    0,    0,    0,    0,   52,   52,    0,    0,
   52,   52,   52,   52,  133,    0,  132,  131,    0,    0,
    0,  184,  129,  127,    0,  128,  134,  130,    0,    0,
    0,    0,  121,  122,    0,    0,  123,  124,  125,  126,
  133,    0,  132,    0,    0,  135,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  121,  122,
    0,    0,  123,  124,  125,  126,    0,    0,    0,  121,
  122,  135,    0,  123,  124,  125,  126,  131,    0,    0,
    0,    0,  129,  127,    0,  128,  134,  130,    0,    0,
   63,  121,  122,   63,    0,  123,  124,  125,  126,    0,
  133,    0,  132,    0,  131,    0,    0,   63,   63,  129,
  127,    0,  128,  134,  130,    0,    0,    0,    0,  121,
  122,    0,    0,  123,  124,  125,  126,  133,    0,  132,
  131,  135,    0,  189,    0,  129,  127,    0,  128,  134,
  130,    0,   63,  121,  122,    0,    0,  123,  124,  125,
  126,   64,  202,  133,   64,  132,  131,    0,  135,    0,
  195,  129,  127,    0,  128,  134,  130,  131,   64,   64,
    0,    0,  129,  127,    0,  128,  134,  130,  220,  133,
    0,  132,    0,    0,  135,    0,    0,    0,  131,  221,
  133,    0,  132,  129,  127,    0,  128,  134,  130,   51,
    0,    0,    0,   64,   51,   51,    0,   51,   51,   51,
  135,  133,    0,  132,    0,    0,   62,    0,    0,   62,
  131,  135,   51,    0,   51,  129,  127,    0,  128,  134,
  130,  121,  122,   62,   62,  123,  124,  125,  126,    0,
    0,    0,  135,  133,    0,  132,  131,    0,    0,    0,
    0,  129,  127,   51,  128,  134,  130,  121,  122,    0,
    0,  123,  124,  125,  126,    0,    0,    0,   62,  133,
    0,  132,    0,   54,  135,   54,   54,   54,    0,    0,
    0,    0,    0,    0,   55,    0,   55,   55,   55,    0,
   54,   54,   54,    0,   54,   61,    0,    0,   61,    0,
  135,   55,   55,   55,    0,   55,    0,    0,    0,    0,
    0,   65,   61,   61,   65,    0,    0,  121,  122,    0,
    0,  123,  124,  125,  126,   54,   63,   63,   65,   65,
    0,    0,   63,   63,    0,    0,   55,   59,    0,   60,
   59,   87,   60,    0,  121,  122,    0,   61,  123,  124,
  125,  126,    0,    0,   59,   59,   60,   60,    0,    0,
    0,    0,    0,   65,    0,    0,    0,    0,    0,    0,
  121,  122,    0,    0,  123,  124,  125,  126,    0,    0,
    0,    0,    0,    0,    0,    0,   87,   64,   64,   59,
    0,   60,    0,   64,   64,    0,  121,  122,    0,    0,
  123,  124,  125,  126,    0,    0,    0,  121,  122,    0,
    0,  123,  124,  125,  126,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  121,  122,
    0,    0,  123,  124,  125,  126,    0,    0,    0,   51,
   51,    0,    0,   51,   51,   51,   51,    0,    0,   87,
    0,    0,   62,   62,    0,    0,    0,    0,   62,   62,
  121,    0,    0,    0,  123,  124,  125,  126,   87,    0,
   87,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  123,  124,  125,  126,    0,    0,    0,   87,   87,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   87,   54,
   54,    0,    0,   54,   54,   54,   54,    0,    0,    0,
   55,   55,    0,    0,   55,   55,   55,   55,    0,    0,
    0,   61,   61,    0,    0,    0,    0,   61,   61,   93,
    0,    0,    0,    0,    0,    0,    0,   65,   65,    0,
    0,    0,  107,  108,  109,  111,    0,  112,  113,  114,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   59,   59,   60,   60,  138,    0,  140,
    0,    0,    0,    0,    0,  143,    0,    0,  147,  148,
  149,  143,  151,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  157,  158,
  159,  160,  161,  162,  163,  164,  165,  166,  167,  168,
  169,    0,  170,  171,    0,    0,    0,    0,    0,  177,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  107,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  143,
    0,  193,    0,    0,    0,  196,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  200,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  218,  219,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   91,   35,   36,   59,   37,   59,   40,  154,  261,   42,
   43,   45,   45,   46,   47,   41,   91,   91,   91,  262,
  104,   30,   41,   32,   46,   59,  173,   60,  175,   62,
   64,   40,  275,   46,   39,   41,   41,   33,   44,   35,
   36,   11,  125,   41,   40,  276,  263,   17,  291,   45,
  276,  123,   58,   59,   37,  276,  203,   59,   91,   42,
   43,   59,   45,   46,   47,   40,  213,   41,   64,   91,
   44,   40,   37,   33,   41,   35,   36,   42,   91,   93,
   40,   46,   47,   41,   54,   45,   44,   93,  172,  123,
   44,  125,   41,   33,   41,   35,   36,   44,   41,  123,
   40,   44,   40,   40,   64,   45,   40,   40,   91,   40,
   40,  202,   40,   33,   40,   35,   36,   40,   40,   40,
   40,  276,   61,   59,   64,   45,   91,  123,   40,  125,
   59,   41,   59,   33,   59,   35,   36,   41,   91,  276,
   40,  154,   59,   59,   64,   45,  294,   40,  257,  258,
  259,  260,  261,   59,   41,  293,   41,  276,   41,  123,
  173,   37,  175,  123,   64,   41,   42,   43,   44,   45,
   46,   47,   44,   93,  257,  258,  259,  260,  261,  288,
  268,   41,   58,   59,   60,   61,   62,   37,   41,  202,
  203,   41,   42,   43,   44,   45,  279,   47,    0,   58,
  213,  276,  276,  276,   58,  288,  125,   59,   58,   59,
   60,   41,   62,   41,  123,   91,  276,   93,   59,   41,
  276,    3,  276,  257,  258,  259,  260,  261,  262,  293,
  264,  265,  266,  267,   11,  269,  270,  271,  272,  273,
  274,  275,  276,   93,   32,  152,  280,  206,  281,  282,
  276,  285,  286,  287,  288,  289,  290,  276,  292,  293,
  294,  257,  258,  259,  260,  261,  262,  185,  264,  265,
  266,  267,  278,  269,  270,  271,  272,  273,  274,  275,
   -1,   -1,   -1,   -1,  280,   -1,   -1,   -1,   -1,  285,
  286,  287,  288,  289,  290,   -1,  292,  257,  258,  259,
  260,  261,  262,   -1,  264,  265,  266,  267,   -1,  269,
  270,  271,  272,  273,  274,  275,   -1,   -1,   -1,   -1,
  280,  261,  262,   -1,  264,  285,  286,  287,  288,  289,
  290,  271,  292,  273,  274,  275,   -1,   -1,   -1,   -1,
  280,   -1,  262,   -1,  264,  285,  286,  287,   -1,   -1,
  290,  271,   -1,  273,  274,  275,   -1,   -1,   -1,   -1,
  280,   -1,  262,   -1,  264,  285,  286,  287,   -1,   -1,
  290,  271,   -1,  273,  274,  275,   -1,   -1,   -1,   -1,
  280,   -1,   -1,   -1,   -1,  285,  286,  287,   37,   -1,
  290,   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,  277,  278,   -1,   -1,  281,  282,  283,  284,   58,
   59,   60,   -1,   62,   37,   -1,   -1,   -1,   41,   42,
   43,   44,   45,   -1,   47,   -1,   -1,  277,  278,   -1,
   -1,  281,  282,  283,  284,   58,   59,   60,   -1,   62,
   -1,   37,   -1,   -1,   93,   41,   42,   43,   44,   45,
   37,   47,   -1,   -1,   41,   42,   43,   44,   45,   -1,
   47,   -1,   58,   59,   60,   -1,   62,   -1,   -1,   -1,
   93,   58,   59,   60,   -1,   62,   37,   -1,   -1,   -1,
   41,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
  257,  258,  259,  260,  261,   -1,   -1,   93,   59,   60,
   -1,   62,   37,   -1,   -1,   -1,   93,   42,   43,  276,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  288,   -1,   58,   -1,   60,   -1,   62,   37,   -1,
   91,   -1,   41,   42,   43,   -1,   45,   46,   47,   37,
   -1,   -1,   -1,   41,   42,   43,   -1,   45,   46,   47,
   -1,   60,   -1,   62,   -1,   -1,   91,   -1,   -1,   -1,
   -1,   37,   60,   -1,   62,   41,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   91,   -1,   60,   -1,   62,   -1,   -1,   37,
   -1,   -1,   -1,   91,   42,   43,   44,   45,   46,   47,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   60,   37,   62,   91,   -1,   41,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,  277,  278,
   -1,   -1,  281,  282,  283,  284,   60,   -1,   62,   -1,
   -1,   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  277,  278,   -1,   -1,  281,  282,
  283,  284,   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  277,  278,   -1,   -1,  281,  282,  283,  284,   -1,
  277,  278,   -1,   -1,  281,  282,  283,  284,   -1,   -1,
   -1,   37,   -1,   -1,   -1,   41,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,   -1,  277,  278,   -1,   -1,
  281,  282,  283,  284,   60,   -1,   62,   37,   -1,   -1,
   -1,   41,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   -1,   -1,  277,  278,   -1,   -1,  281,  282,  283,  284,
   60,   -1,   62,   -1,   -1,   91,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,  278,
   -1,   -1,  281,  282,  283,  284,   -1,   -1,   -1,  277,
  278,   91,   -1,  281,  282,  283,  284,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,   -1,
   41,  277,  278,   44,   -1,  281,  282,  283,  284,   -1,
   60,   -1,   62,   -1,   37,   -1,   -1,   58,   59,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,  277,
  278,   -1,   -1,  281,  282,  283,  284,   60,   -1,   62,
   37,   91,   -1,   93,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   93,  277,  278,   -1,   -1,  281,  282,  283,
  284,   41,   59,   60,   44,   62,   37,   -1,   91,   -1,
   93,   42,   43,   -1,   45,   46,   47,   37,   58,   59,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   59,   60,
   -1,   62,   -1,   -1,   91,   -1,   -1,   -1,   37,   59,
   60,   -1,   62,   42,   43,   -1,   45,   46,   47,   37,
   -1,   -1,   -1,   93,   42,   43,   -1,   45,   46,   47,
   91,   60,   -1,   62,   -1,   -1,   41,   -1,   -1,   44,
   37,   91,   60,   -1,   62,   42,   43,   -1,   45,   46,
   47,  277,  278,   58,   59,  281,  282,  283,  284,   -1,
   -1,   -1,   91,   60,   -1,   62,   37,   -1,   -1,   -1,
   -1,   42,   43,   91,   45,   46,   47,  277,  278,   -1,
   -1,  281,  282,  283,  284,   -1,   -1,   -1,   93,   60,
   -1,   62,   -1,   41,   91,   43,   44,   45,   -1,   -1,
   -1,   -1,   -1,   -1,   41,   -1,   43,   44,   45,   -1,
   58,   59,   60,   -1,   62,   41,   -1,   -1,   44,   -1,
   91,   58,   59,   60,   -1,   62,   -1,   -1,   -1,   -1,
   -1,   41,   58,   59,   44,   -1,   -1,  277,  278,   -1,
   -1,  281,  282,  283,  284,   93,  277,  278,   58,   59,
   -1,   -1,  283,  284,   -1,   -1,   93,   41,   -1,   41,
   44,   46,   44,   -1,  277,  278,   -1,   93,  281,  282,
  283,  284,   -1,   -1,   58,   59,   58,   59,   -1,   -1,
   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,
  277,  278,   -1,   -1,  281,  282,  283,  284,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   91,  277,  278,   93,
   -1,   93,   -1,  283,  284,   -1,  277,  278,   -1,   -1,
  281,  282,  283,  284,   -1,   -1,   -1,  277,  278,   -1,
   -1,  281,  282,  283,  284,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,  278,
   -1,   -1,  281,  282,  283,  284,   -1,   -1,   -1,  277,
  278,   -1,   -1,  281,  282,  283,  284,   -1,   -1,  154,
   -1,   -1,  277,  278,   -1,   -1,   -1,   -1,  283,  284,
  277,   -1,   -1,   -1,  281,  282,  283,  284,  173,   -1,
  175,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  281,  282,  283,  284,   -1,   -1,   -1,  202,  203,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  213,  277,
  278,   -1,   -1,  281,  282,  283,  284,   -1,   -1,   -1,
  277,  278,   -1,   -1,  281,  282,  283,  284,   -1,   -1,
   -1,  277,  278,   -1,   -1,   -1,   -1,  283,  284,   52,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  277,  278,   -1,
   -1,   -1,   65,   66,   67,   68,   -1,   70,   71,   72,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  277,  278,  277,  278,   90,   -1,   92,
   -1,   -1,   -1,   -1,   -1,   98,   -1,   -1,  101,  102,
  103,  104,  105,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  121,  122,
  123,  124,  125,  126,  127,  128,  129,  130,  131,  132,
  133,   -1,  135,  136,   -1,   -1,   -1,   -1,   -1,  142,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  152,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  172,
   -1,  174,   -1,   -1,   -1,  178,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  188,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  214,  215,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=296;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,"'#'","'$'","'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'",null,"'@'",null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"VOID","BOOL","INT","STRING",
"CLASS","NULL","EXTENDS","THIS","WHILE","FOR","IF","ELSE","RETURN","BREAK",
"NEW","PRINT","READ_INTEGER","READ_LINE","LITERAL","IDENTIFIER","AND","OR",
"STATIC","INSTANCEOF","LESS_EQUAL","GREATER_EQUAL","EQUAL","NOT_EQUAL","SUPER",
"DCOPY","SCOPY","COMPLEX","PRINTCOMP","CASE","DEFAULT","DO","OD","NEXT",
"UMINUS","EMPTY",
};
final static String yyrule[] = {
"$accept : Program",
"Program : ClassList",
"ClassList : ClassList ClassDef",
"ClassList : ClassDef",
"VariableDef : Variable ';'",
"Variable : Type IDENTIFIER",
"Type : INT",
"Type : VOID",
"Type : BOOL",
"Type : STRING",
"Type : COMPLEX",
"Type : CLASS IDENTIFIER",
"Type : Type '[' ']'",
"ClassDef : CLASS IDENTIFIER ExtendsClause '{' FieldList '}'",
"ExtendsClause : EXTENDS IDENTIFIER",
"ExtendsClause :",
"FieldList : FieldList VariableDef",
"FieldList : FieldList FunctionDef",
"FieldList :",
"Formals : VariableList",
"Formals :",
"VariableList : VariableList ',' Variable",
"VariableList : Variable",
"FunctionDef : STATIC Type IDENTIFIER '(' Formals ')' StmtBlock",
"FunctionDef : Type IDENTIFIER '(' Formals ')' StmtBlock",
"StmtBlock : '{' StmtList '}'",
"StmtList : StmtList Stmt",
"StmtList :",
"Stmt : VariableDef",
"Stmt : SimpleStmt ';'",
"Stmt : IfStmt",
"Stmt : WhileStmt",
"Stmt : ForStmt",
"Stmt : ReturnStmt ';'",
"Stmt : PrintStmt ';'",
"Stmt : BreakStmt ';'",
"Stmt : PrintCompStmt ';'",
"Stmt : StmtBlock",
"Stmt : DoStmt ';'",
"DoStmt : DO DoSubStmt DoBranchList OD",
"DoBranchList : NEXT DoSubStmt DoBranchList",
"DoBranchList :",
"DoSubStmt : Expr ':' Stmt",
"SimpleStmt : LValue '=' Expr",
"SimpleStmt : Call",
"SimpleStmt :",
"Receiver : Expr '.'",
"Receiver :",
"LValue : Receiver IDENTIFIER",
"LValue : Expr '[' Expr ']'",
"Call : Receiver IDENTIFIER '(' Actuals ')'",
"Expr : LValue",
"Expr : Call",
"Expr : Constant",
"Expr : Expr '+' Expr",
"Expr : Expr '-' Expr",
"Expr : Expr '*' Expr",
"Expr : Expr '/' Expr",
"Expr : Expr '%' Expr",
"Expr : Expr EQUAL Expr",
"Expr : Expr NOT_EQUAL Expr",
"Expr : Expr '<' Expr",
"Expr : Expr '>' Expr",
"Expr : Expr LESS_EQUAL Expr",
"Expr : Expr GREATER_EQUAL Expr",
"Expr : Expr AND Expr",
"Expr : Expr OR Expr",
"Expr : '(' Expr ')'",
"Expr : '-' Expr",
"Expr : '!' Expr",
"Expr : '@' Expr",
"Expr : '$' Expr",
"Expr : '#' Expr",
"Expr : READ_INTEGER '(' ')'",
"Expr : READ_LINE '(' ')'",
"Expr : THIS",
"Expr : SUPER",
"Expr : NEW IDENTIFIER '(' ')'",
"Expr : NEW Type '[' Expr ']'",
"Expr : INSTANCEOF '(' Expr ',' IDENTIFIER ')'",
"Expr : '(' CLASS IDENTIFIER ')' Expr",
"Expr : DCOPY '(' Expr ')'",
"Expr : SCOPY '(' Expr ')'",
"Expr : CASE '(' Expr ')' '{' ACaseExprList DefaultExpr '}'",
"ACaseExprList : ACaseExprList ACaseExpr",
"ACaseExprList :",
"ACaseExpr : Constant ':' Expr ';'",
"DefaultExpr : DEFAULT ':' Expr ';'",
"Constant : LITERAL",
"Constant : NULL",
"Actuals : ExprList",
"Actuals :",
"ExprList : ExprList ',' Expr",
"ExprList : Expr",
"WhileStmt : WHILE '(' Expr ')' Stmt",
"ForStmt : FOR '(' SimpleStmt ';' Expr ';' SimpleStmt ')' Stmt",
"BreakStmt : BREAK",
"IfStmt : IF '(' Expr ')' Stmt ElseClause",
"ElseClause : ELSE Stmt",
"ElseClause :",
"ReturnStmt : RETURN Expr",
"ReturnStmt : RETURN",
"PrintStmt : PRINT '(' ExprList ')'",
"PrintCompStmt : PRINTCOMP '(' ExprList ')'",
};

//#line 515 "Parser.y"
	
	/**
	 * 打印当前归约所用的语法规则<br>
	 * 请勿修改。
	 */
	public boolean onReduce(String rule) {
		if (rule.startsWith("$$"))
			return false;
		else
			rule = rule.replaceAll(" \\$\\$\\d+", "");

		if (rule.endsWith(":"))
			System.out.println(rule + " <empty>");
		else
			System.out.println(rule);
		return false;
	}
	
	public void diagnose() {
		addReduceListener(this);
		yyparse();
	}
//#line 711 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    //if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      //if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        //if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        //if (yychar < 0)    //it it didn't work/error
        //  {
        //  yychar = 0;      //change it to default string (no -1!)
          //if (yydebug)
          //  yylexdebug(yystate,yychar);
        //  }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        //if (yydebug)
          //debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      //if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0 || valptr<0)   //check for under & overflow here
            {
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            //if (yydebug)
              //debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            //if (yydebug)
              //debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0 || valptr<0)   //check for under & overflow here
              {
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        //if (yydebug)
          //{
          //yys = null;
          //if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          //if (yys == null) yys = "illegal-symbol";
          //debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          //}
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    //if (yydebug)
      //debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    if (reduceListener == null || reduceListener.onReduce(yyrule[yyn])) // if intercepted!
      switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 58 "Parser.y"
{
						tree = new Tree.TopLevel(val_peek(0).clist, val_peek(0).loc);
					}
break;
case 2:
//#line 64 "Parser.y"
{
						yyval.clist.add(val_peek(0).cdef);
					}
break;
case 3:
//#line 68 "Parser.y"
{
						yyval.clist = new ArrayList<Tree.ClassDef>();
						yyval.clist.add(val_peek(0).cdef);
					}
break;
case 5:
//#line 78 "Parser.y"
{
						yyval.vdef = new Tree.VarDef(val_peek(0).ident, val_peek(1).type, val_peek(0).loc);
					}
break;
case 6:
//#line 84 "Parser.y"
{
						yyval.type = new Tree.TypeIdent(Tree.INT, val_peek(0).loc);
					}
break;
case 7:
//#line 88 "Parser.y"
{
						yyval.type = new Tree.TypeIdent(Tree.VOID, val_peek(0).loc);
					}
break;
case 8:
//#line 92 "Parser.y"
{
						yyval.type = new Tree.TypeIdent(Tree.BOOL, val_peek(0).loc);
					}
break;
case 9:
//#line 96 "Parser.y"
{
						yyval.type = new Tree.TypeIdent(Tree.STRING, val_peek(0).loc);
					}
break;
case 10:
//#line 100 "Parser.y"
{
						yyval.type = new Tree.TypeIdent(Tree.COMPLEX, val_peek(0).loc);
					}
break;
case 11:
//#line 104 "Parser.y"
{
						yyval.type = new Tree.TypeClass(val_peek(0).ident, val_peek(1).loc);
					}
break;
case 12:
//#line 108 "Parser.y"
{
						yyval.type = new Tree.TypeArray(val_peek(2).type, val_peek(2).loc);
					}
break;
case 13:
//#line 114 "Parser.y"
{
						yyval.cdef = new Tree.ClassDef(val_peek(4).ident, val_peek(3).ident, val_peek(1).flist, val_peek(5).loc);
					}
break;
case 14:
//#line 120 "Parser.y"
{
						yyval.ident = val_peek(0).ident;
					}
break;
case 15:
//#line 124 "Parser.y"
{
						yyval = new SemValue();
					}
break;
case 16:
//#line 130 "Parser.y"
{
						yyval.flist.add(val_peek(0).vdef);
					}
break;
case 17:
//#line 134 "Parser.y"
{
						yyval.flist.add(val_peek(0).fdef);
					}
break;
case 18:
//#line 138 "Parser.y"
{
						yyval = new SemValue();
						yyval.flist = new ArrayList<Tree>();
					}
break;
case 20:
//#line 146 "Parser.y"
{
						yyval = new SemValue();
						yyval.vlist = new ArrayList<Tree.VarDef>(); 
					}
break;
case 21:
//#line 153 "Parser.y"
{
						yyval.vlist.add(val_peek(0).vdef);
					}
break;
case 22:
//#line 157 "Parser.y"
{
						yyval.vlist = new ArrayList<Tree.VarDef>();
						yyval.vlist.add(val_peek(0).vdef);
					}
break;
case 23:
//#line 164 "Parser.y"
{
						yyval.fdef = new MethodDef(true, val_peek(4).ident, val_peek(5).type, val_peek(2).vlist, (Block) val_peek(0).stmt, val_peek(4).loc);
					}
break;
case 24:
//#line 168 "Parser.y"
{
						yyval.fdef = new MethodDef(false, val_peek(4).ident, val_peek(5).type, val_peek(2).vlist, (Block) val_peek(0).stmt, val_peek(4).loc);
					}
break;
case 25:
//#line 174 "Parser.y"
{
						yyval.stmt = new Block(val_peek(1).slist, val_peek(2).loc);
					}
break;
case 26:
//#line 180 "Parser.y"
{
						yyval.slist.add(val_peek(0).stmt);
					}
break;
case 27:
//#line 184 "Parser.y"
{
						yyval = new SemValue();
						yyval.slist = new ArrayList<Tree>();
					}
break;
case 28:
//#line 191 "Parser.y"
{
						yyval.stmt = val_peek(0).vdef;
					}
break;
case 29:
//#line 196 "Parser.y"
{
						if (yyval.stmt == null) {
							yyval.stmt = new Tree.Skip(val_peek(0).loc);
						}
					}
break;
case 39:
//#line 213 "Parser.y"
{
						yyval.stmt = new Tree.DoStmt((Tree.DoSubStmt)val_peek(2).stmt, val_peek(1).dslist, val_peek(3).loc);
					}
break;
case 40:
//#line 219 "Parser.y"
{
						yyval.dslist = new ArrayList<Tree.DoSubStmt>();
						yyval.dslist.add((Tree.DoSubStmt)val_peek(1).stmt);
						yyval.dslist.addAll(val_peek(0).dslist);
					}
break;
case 41:
//#line 225 "Parser.y"
{
						yyval.dslist = new ArrayList<Tree.DoSubStmt>();
					}
break;
case 42:
//#line 231 "Parser.y"
{
						yyval.stmt = new Tree.DoSubStmt(val_peek(2).expr, val_peek(0).stmt, val_peek(2).loc);
					}
break;
case 43:
//#line 237 "Parser.y"
{
						yyval.stmt = new Tree.Assign(val_peek(2).lvalue, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 44:
//#line 241 "Parser.y"
{
						yyval.stmt = new Tree.Exec(val_peek(0).expr, val_peek(0).loc);
					}
break;
case 45:
//#line 245 "Parser.y"
{
						yyval = new SemValue();
					}
break;
case 47:
//#line 252 "Parser.y"
{
						yyval = new SemValue();
					}
break;
case 48:
//#line 258 "Parser.y"
{
						yyval.lvalue = new Tree.Ident(val_peek(1).expr, val_peek(0).ident, val_peek(0).loc);
						if (val_peek(1).loc == null) {
							yyval.loc = val_peek(0).loc;
						}
					}
break;
case 49:
//#line 265 "Parser.y"
{
						yyval.lvalue = new Tree.Indexed(val_peek(3).expr, val_peek(1).expr, val_peek(3).loc);
					}
break;
case 50:
//#line 271 "Parser.y"
{
						yyval.expr = new Tree.CallExpr(val_peek(4).expr, val_peek(3).ident, val_peek(1).elist, val_peek(3).loc);
						if (val_peek(4).loc == null) {
							yyval.loc = val_peek(3).loc;
						}
					}
break;
case 51:
//#line 280 "Parser.y"
{
						yyval.expr = val_peek(0).lvalue;
					}
break;
case 54:
//#line 286 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.PLUS, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 55:
//#line 290 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.MINUS, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 56:
//#line 294 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.MUL, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 57:
//#line 298 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.DIV, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 58:
//#line 302 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.MOD, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 59:
//#line 306 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.EQ, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 60:
//#line 310 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.NE, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 61:
//#line 314 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.LT, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 62:
//#line 318 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.GT, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 63:
//#line 322 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.LE, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 64:
//#line 326 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.GE, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 65:
//#line 330 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.AND, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 66:
//#line 334 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.OR, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 67:
//#line 338 "Parser.y"
{
						yyval = val_peek(1);
					}
break;
case 68:
//#line 342 "Parser.y"
{
						yyval.expr = new Tree.Unary(Tree.NEG, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 69:
//#line 346 "Parser.y"
{
						yyval.expr = new Tree.Unary(Tree.NOT, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 70:
//#line 350 "Parser.y"
{
						yyval.expr = new Tree.Unary(Tree.GETREAL, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 71:
//#line 354 "Parser.y"
{
						yyval.expr = new Tree.Unary(Tree.GETIMG, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 72:
//#line 358 "Parser.y"
{
						yyval.expr = new Tree.Unary(Tree.TOCOMPLEX, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 73:
//#line 362 "Parser.y"
{
						yyval.expr = new Tree.ReadIntExpr(val_peek(2).loc);
					}
break;
case 74:
//#line 366 "Parser.y"
{
						yyval.expr = new Tree.ReadLineExpr(val_peek(2).loc);
					}
break;
case 75:
//#line 370 "Parser.y"
{
						yyval.expr = new Tree.ThisExpr(val_peek(0).loc);
					}
break;
case 76:
//#line 374 "Parser.y"
{
						yyval.expr = new Tree.SuperExpr(val_peek(0).loc);
					}
break;
case 77:
//#line 378 "Parser.y"
{
						yyval.expr = new Tree.NewClass(val_peek(2).ident, val_peek(3).loc);
					}
break;
case 78:
//#line 382 "Parser.y"
{
						yyval.expr = new Tree.NewArray(val_peek(3).type, val_peek(1).expr, val_peek(4).loc);
					}
break;
case 79:
//#line 386 "Parser.y"
{
						yyval.expr = new Tree.TypeTest(val_peek(3).expr, val_peek(1).ident, val_peek(5).loc);
					}
break;
case 80:
//#line 390 "Parser.y"
{
						yyval.expr = new Tree.TypeCast(val_peek(2).ident, val_peek(0).expr, val_peek(0).loc);
					}
break;
case 81:
//#line 394 "Parser.y"
{
						yyval.expr = new Tree.DcopyExpr(val_peek(1).expr, val_peek(3).loc);
					}
break;
case 82:
//#line 398 "Parser.y"
{
						yyval.expr = new Tree.ScopyExpr(val_peek(1).expr, val_peek(3).loc);
					}
break;
case 83:
//#line 402 "Parser.y"
{
						yyval.expr = new Tree.CaseExpr(val_peek(5).expr, val_peek(2).celist, val_peek(1).expr, val_peek(7).loc);
					}
break;
case 84:
//#line 408 "Parser.y"
{
						yyval.celist.add((Tree.ACaseExpr)val_peek(0).expr);
					}
break;
case 85:
//#line 412 "Parser.y"
{
						yyval = new SemValue();
						yyval.celist = new ArrayList<Tree.ACaseExpr>();
					}
break;
case 86:
//#line 419 "Parser.y"
{
						yyval.expr = new Tree.ACaseExpr((Tree.Literal)val_peek(3).expr, val_peek(1).expr, val_peek(3).loc);
					}
break;
case 87:
//#line 425 "Parser.y"
{
						yyval.expr = val_peek(1).expr;
					}
break;
case 88:
//#line 431 "Parser.y"
{
						yyval.expr = new Tree.Literal(val_peek(0).typeTag, val_peek(0).literal, val_peek(0).loc);
					}
break;
case 89:
//#line 435 "Parser.y"
{
						yyval.expr = new Null(val_peek(0).loc);
					}
break;
case 91:
//#line 442 "Parser.y"
{
						yyval = new SemValue();
						yyval.elist = new ArrayList<Tree.Expr>();
					}
break;
case 92:
//#line 449 "Parser.y"
{
						yyval.elist.add(val_peek(0).expr);
					}
break;
case 93:
//#line 453 "Parser.y"
{
						yyval.elist = new ArrayList<Tree.Expr>();
						yyval.elist.add(val_peek(0).expr);
					}
break;
case 94:
//#line 460 "Parser.y"
{
						yyval.stmt = new Tree.WhileLoop(val_peek(2).expr, val_peek(0).stmt, val_peek(4).loc);
					}
break;
case 95:
//#line 466 "Parser.y"
{
						yyval.stmt = new Tree.ForLoop(val_peek(6).stmt, val_peek(4).expr, val_peek(2).stmt, val_peek(0).stmt, val_peek(8).loc);
					}
break;
case 96:
//#line 472 "Parser.y"
{
						yyval.stmt = new Tree.Break(val_peek(0).loc);
					}
break;
case 97:
//#line 478 "Parser.y"
{
						yyval.stmt = new Tree.If(val_peek(3).expr, val_peek(1).stmt, val_peek(0).stmt, val_peek(5).loc);
					}
break;
case 98:
//#line 484 "Parser.y"
{
						yyval.stmt = val_peek(0).stmt;
					}
break;
case 99:
//#line 488 "Parser.y"
{
						yyval = new SemValue();
					}
break;
case 100:
//#line 494 "Parser.y"
{
						yyval.stmt = new Tree.Return(val_peek(0).expr, val_peek(1).loc);
					}
break;
case 101:
//#line 498 "Parser.y"
{
						yyval.stmt = new Tree.Return(null, val_peek(0).loc);
					}
break;
case 102:
//#line 504 "Parser.y"
{
						yyval.stmt = new Print(val_peek(1).elist, val_peek(3).loc);
					}
break;
case 103:
//#line 510 "Parser.y"
{
						yyval.stmt = new PrintComp(val_peek(1).elist, val_peek(3).loc);
					}
break;
//#line 1403 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    //if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      //if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        //if (yychar<0) yychar=0;  //clean, if necessary
        //if (yydebug)
          //yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      //if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
//## The -Jnorun option was used ##
//## end of method run() ########################################



//## Constructors ###############################################
//## The -Jnoconstruct option was used ##
//###############################################################



}
//################### END OF CLASS ##############################
