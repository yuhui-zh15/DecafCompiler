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
   13,   13,   13,   13,   13,   13,   13,   13,   22,   23,
   23,   25,   24,   14,   14,   14,   29,   29,   27,   27,
   28,   26,   26,   26,   26,   26,   26,   26,   26,   26,
   26,   26,   26,   26,   26,   26,   26,   26,   26,   26,
   26,   26,   26,   26,   26,   26,   26,   26,   26,   26,
   26,   26,   26,   26,   32,   32,   34,   33,   31,   31,
   30,   30,   35,   35,   16,   17,   20,   15,   36,   36,
   18,   18,   19,   21,
};
final static short yylen[] = {                            2,
    1,    2,    1,    2,    2,    1,    1,    1,    1,    1,
    2,    3,    6,    2,    0,    2,    2,    0,    1,    0,
    3,    1,    7,    6,    3,    2,    0,    1,    2,    1,
    1,    1,    2,    2,    2,    2,    1,    2,    4,    2,
    0,    2,    3,    3,    1,    0,    2,    0,    2,    4,
    5,    1,    1,    1,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    2,    2,
    2,    2,    2,    3,    3,    1,    1,    4,    5,    6,
    5,    4,    4,    8,    2,    0,    4,    4,    1,    1,
    1,    0,    3,    1,    5,    9,    1,    6,    2,    0,
    2,    1,    4,    4,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    3,    0,    2,    0,    0,   14,   18,
    0,    7,    8,    6,    9,    0,    0,   10,   13,   16,
    0,    0,   17,   11,    0,    4,    0,    0,    0,    0,
   12,    0,   22,    0,    0,    0,    0,    5,    0,    0,
    0,   27,   24,   21,   23,    0,   90,   76,    0,    0,
    0,    0,   97,    0,    0,    0,    0,   89,    0,   77,
    0,    0,    0,    0,   41,    0,    0,    0,   25,    0,
    0,    0,   28,   37,   26,    0,   30,   31,   32,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   54,    0,
    0,    0,    0,   52,   53,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   71,   72,   73,   29,   33,   34,   35,   36,   38,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   47,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   74,   75,    0,    0,    0,    0,    0,
    0,   40,    0,    0,   68,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   78,    0,    0,  103,    0,   82,
   83,  104,    0,   39,   42,    0,    0,   50,    0,    0,
   95,    0,    0,   79,    0,    0,   86,   43,   81,   51,
    0,    0,   98,   80,    0,    0,   99,    0,    0,    0,
   85,    0,    0,    0,   84,   96,    0,    0,   88,   87,
};
final static short yydgoto[] = {                          2,
    3,    4,   73,   21,   34,    8,   11,   23,   35,   36,
   74,   46,   75,   76,   77,   78,   79,   80,   81,   82,
   83,   84,  106,  151,  152,   85,   94,   95,   88,  189,
   89,  205,  210,  211,  143,  203,
};
final static short yysindex[] = {                      -252,
 -244,    0, -252,    0, -226,    0, -230,  -71,    0,    0,
  580,    0,    0,    0,    0, -225, -110,    0,    0,    0,
   -1,  -74,    0,    0,  -73,    0,   26,  -22,   33, -110,
    0, -110,    0,  -72,   38,   39,   48,    0,  -30, -110,
  -30,    0,    0,    0,    0,    5,    0,    0,   51,   55,
   70,  101,    0,  418,   72,   73,   75,    0,   78,    0,
   79,   80,   82,   83,    0,  101,  101,   61,    0,  101,
  101,  101,    0,    0,    0,   65,    0,    0,    0,   68,
   74,   76,   84,   85,  869,   71,    0, -147,    0,  101,
  101,  101,  869,    0,    0,   91,   47,  101,   98,   99,
  101,  101,  101,  101,  101,  101,  -24,  -24, -134,  467,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  101,
  101,  101,  101,  101,  101,  101,  101,  101,  101,  101,
  101,  101,    0,  101,  101,  112,  478,   94,  500,  113,
   81,  869,   43,    0,    0,  528,  540,  552,   58,  579,
 -273,    0,  667,  114,    0,  931,  910,   18,   18,  -32,
  -32,  -13,  -13,  -24,  -24,  -24,   18,   18,  753,  869,
  101,   41,  101,   41,    0,  805,  101,    0, -120,    0,
    0,    0,   34,    0,    0,   41,  101,    0,  117,  115,
    0,  729, -108,    0,  869,  132,    0,    0,    0,    0,
  101,   41,    0,    0, -219,  135,    0,  119,  121,   56,
    0,   41,  101,  101,    0,    0,  827,  848,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,  180,    0,   66,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  123,    0,    0,  156,
    0,  156,    0,    0,    0,  158,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -55,    0,    0,    0,    0,
    0,  -53,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -76,  -76,  -76,    0,  -76,
  -76,  -76,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  880,  440,    0,    0,  -76,
  -55,  -76,  146,    0,    0,    0,    0,  -76,    0,    0,
  -76,  -76,  -76,  -76,  -76,  -76,  151,  352,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -76,
  -76,  -76,  -76,  -76,  -76,  -76,  -76,  -76,  -76,  -76,
  -76,  -76,    0,  -76,  -76,  125,    0,    0,    0,    0,
  -76,   59,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  491,   -5,  861,  982,  606,
  622,  921,  959,  378,  405,  414,  984, 1003,    0,    3,
  -25,  -55,  -76,  -55,    0,    0,  -76,    0,    0,    0,
    0,    0,    0,    0,    0,  -55,  -76,    0,    0,  165,
    0,    0,  -33,    0,   63,    0,    0,    0,    0,    0,
  -18,  -55,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -55,  -76,  -76,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  204,  197,   17,   31,    0,    0,    0,  182,    0,
  -14,    0, -104,  -90,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, 1203,  -11,  294,    0,    0,
    7,    0,    0,    0,  -96,    0,
};
final static int YYTABLESIZE=1417;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                        100,
  138,  100,  100,   46,  130,  102,  100,  149,    1,  128,
  126,  100,  127,  133,  129,   92,   28,   28,   28,  184,
  185,  133,   46,  130,   43,  100,   45,  132,  128,  131,
  100,    5,  133,  129,   86,   67,    7,   67,   67,   72,
   71,   22,   47,   44,   68,    9,   33,   25,   33,   66,
   24,   10,   67,   67,  130,   58,   44,   26,  134,  128,
  126,   44,  127,  133,  129,   30,  134,  191,   70,  193,
   31,  208,   32,   67,  190,   72,   71,  134,   39,   86,
   68,  198,   40,  178,   97,   66,  177,   67,   41,  100,
   90,  100,   42,   67,   91,   72,   71,  207,  182,   94,
   68,  177,   94,   93,   70,   66,   93,  216,  134,   92,
  206,   98,   99,   67,  100,   72,   71,  101,  102,  103,
   68,  104,  105,  114,   70,   66,  115,   42,  136,   69,
  140,  135,  116,   67,  117,   72,   71,  141,  144,  145,
   68,  154,  118,  119,   70,   66,   12,   13,   14,   15,
   16,  171,  173,  175,  187,  196,  197,  200,  177,  202,
   86,   49,   86,   42,   70,   49,   49,   49,   49,   49,
   49,   49,  204,   31,   86,  212,  213,   18,  214,    1,
  215,    5,   49,   49,   49,   49,   49,   69,   15,   86,
   86,   69,   69,   69,   69,   69,   20,   69,   19,   48,
   86,   27,   29,   38,  101,   91,    6,   20,   69,   69,
   69,  209,   69,   37,    0,   49,    0,   49,    0,    0,
   48,    0,   48,  100,  100,  100,  100,  100,  100,    0,
  100,  100,  100,  100,    0,  100,  100,  100,  100,  100,
  100,  100,  100,   69,    0,    0,  100,    0,  122,  123,
   48,  100,  100,  100,  100,  100,  100,   48,  100,  100,
  100,   12,   13,   14,   15,   16,   47,    0,   48,   49,
   50,   51,   67,   52,   53,   54,   55,   56,   57,   58,
    0,    0,    0,    0,   59,    0,    0,    0,    0,   60,
   61,   62,   18,   63,   64,    0,   65,   12,   13,   14,
   15,   16,   47,    0,   48,   49,   50,   51,    0,   52,
   53,   54,   55,   56,   57,   58,    0,    0,    0,    0,
   59,  109,   47,    0,   48,   60,   61,   62,   18,   63,
   64,   54,   65,   56,   57,   58,    0,    0,    0,   87,
   59,    0,   47,    0,   48,   60,   61,   62,    0,    0,
   64,   54,    0,   56,   57,   58,    0,    0,    0,    0,
   59,    0,   47,    0,   48,   60,   61,   62,    0,    0,
   64,   54,    0,   56,   57,   58,    0,    0,    0,    0,
   59,    0,    0,    0,   87,   60,   61,   62,   70,    0,
   64,    0,   70,   70,   70,   70,   70,    0,   70,    0,
    0,   49,   49,    0,    0,   49,   49,   49,   49,   70,
   70,   70,    0,   70,   57,    0,    0,    0,   57,   57,
   57,   57,   57,    0,   57,    0,    0,   69,   69,    0,
    0,   69,   69,   69,   69,   57,   57,   57,    0,   57,
    0,   58,    0,    0,   70,   58,   58,   58,   58,   58,
   59,   58,    0,    0,   59,   59,   59,   59,   59,    0,
   59,    0,   58,   58,   58,   87,   58,   87,    0,    0,
   57,   59,   59,   59,    0,   59,   53,    0,    0,   87,
   45,   53,   53,    0,   53,   53,   53,    0,    0,    0,
    0,    0,    0,    0,   87,   87,    0,   58,   45,   53,
    0,   53,    0,  130,    0,   87,   59,  155,  128,  126,
    0,  127,  133,  129,  130,    0,    0,    0,  172,  128,
  126,    0,  127,  133,  129,    0,  132,    0,  131,    0,
   53,   66,    0,    0,   66,    0,  130,  132,    0,  131,
  174,  128,  126,    0,  127,  133,  129,    0,   66,   66,
    0,    0,    0,    0,    0,    0,    0,  134,    0,  132,
    0,  131,    0,    0,  130,    0,    0,    0,  134,  128,
  126,  179,  127,  133,  129,    0,  130,    0,    0,    0,
  180,  128,  126,   66,  127,  133,  129,  132,  130,  131,
  134,    0,  181,  128,  126,    0,  127,  133,  129,  132,
    0,  131,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  132,    0,  131,    0,  130,    0,    0,  134,  183,
  128,  126,    0,  127,  133,  129,    0,    0,   70,   70,
  134,    0,   70,   70,   70,   70,    0,    0,  132,    0,
  131,    0,  134,    0,    0,    0,   60,    0,    0,   60,
    0,    0,    0,    0,   57,   57,    0,    0,   57,   57,
   57,   57,   61,   60,   60,   61,    0,    0,    0,  134,
    0,    0,    0,    0,   12,   13,   14,   15,   16,   61,
   61,   58,   58,    0,    0,   58,   58,   58,   58,    0,
   59,   59,    0,   96,   59,   59,   59,   59,   60,    0,
    0,    0,    0,  130,   19,   18,    0,    0,  128,  126,
    0,  127,  133,  129,   61,    0,   53,   53,    0,    0,
   53,   53,   53,   53,  186,    0,  132,    0,  131,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  120,  121,    0,    0,  122,  123,  124,
  125,    0,    0,    0,  120,  121,    0,  134,  122,  123,
  124,  125,    0,    0,    0,  130,    0,   66,   66,    0,
  128,  126,    0,  127,  133,  129,  120,  121,    0,    0,
  122,  123,  124,  125,    0,    0,    0,  201,  132,  130,
  131,    0,    0,    0,  128,  126,    0,  127,  133,  129,
    0,    0,    0,    0,  120,  121,    0,    0,  122,  123,
  124,  125,  132,    0,  131,    0,  120,  121,    0,  134,
  122,  123,  124,  125,    0,    0,    0,    0,  120,  121,
    0,    0,  122,  123,  124,  125,   12,   13,   14,   15,
   16,  130,    0,  134,    0,  188,  128,  126,    0,  127,
  133,  129,    0,    0,    0,  120,  121,    0,   17,  122,
  123,  124,  125,  130,  132,    0,  131,   18,  128,  126,
    0,  127,  133,  129,    0,    0,    0,    0,    0,    0,
    0,    0,   60,   60,  130,  219,  132,    0,  131,  128,
  126,    0,  127,  133,  129,  134,    0,  194,   61,   61,
    0,   64,    0,    0,   64,  130,  220,  132,    0,  131,
  128,  126,    0,  127,  133,  129,   52,  134,   64,   64,
    0,   52,   52,    0,   52,   52,   52,    0,  132,    0,
  131,    0,    0,    0,    0,    0,    0,    0,  134,   52,
    0,   52,    0,  120,  121,    0,  130,  122,  123,  124,
  125,  128,  126,   64,  127,  133,  129,    0,    0,  134,
    0,   55,    0,   55,   55,   55,    0,  130,    0,  132,
   52,  131,  128,  126,    0,  127,  133,  129,   55,   55,
   55,    0,   55,    0,    0,    0,    0,    0,    0,    0,
  132,    0,  131,    0,    0,    0,    0,    0,    0,   56,
  134,   56,   56,   56,    0,  120,  121,    0,    0,  122,
  123,  124,  125,   55,    0,    0,   56,   56,   56,    0,
   56,  134,   65,    0,   63,   65,    0,   63,    0,  120,
  121,    0,    0,  122,  123,  124,  125,    0,    0,   65,
   65,   63,   63,   62,    0,    0,   62,    0,    0,    0,
    0,   56,    0,    0,    0,    0,    0,    0,    0,    0,
   62,   62,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   65,    0,   63,    0,    0,    0,
    0,  120,  121,    0,    0,  122,  123,  124,  125,    0,
    0,    0,    0,    0,    0,   62,    0,    0,    0,    0,
    0,    0,    0,  120,  121,    0,    0,  122,  123,  124,
  125,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  120,  121,    0,    0,  122,  123,
  124,  125,    0,    0,    0,    0,    0,   64,   64,    0,
    0,    0,    0,   64,   64,  120,  121,    0,    0,  122,
  123,  124,  125,    0,    0,    0,   52,   52,    0,    0,
   52,   52,   52,   52,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  120,    0,    0,    0,
  122,  123,  124,  125,    0,    0,    0,   55,   55,    0,
    0,   55,   55,   55,   55,    0,    0,    0,    0,    0,
    0,  122,  123,  124,  125,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   56,   56,    0,    0,   56,
   56,   56,   56,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   93,    0,    0,    0,   65,   65,
   63,   63,    0,    0,   65,   65,   63,   63,  107,  108,
  110,    0,  111,  112,  113,    0,    0,    0,    0,   62,
   62,    0,    0,    0,    0,   62,   62,    0,    0,    0,
    0,    0,  137,    0,  139,    0,    0,    0,    0,    0,
  142,    0,    0,  146,  147,  148,  142,  150,  153,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  156,  157,  158,  159,  160,  161,  162,  163,
  164,  165,  166,  167,  168,    0,  169,  170,    0,    0,
    0,    0,    0,  176,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  142,    0,  192,    0,    0,    0,  195,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  199,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  217,  218,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   91,   35,   36,   59,   37,   59,   40,  104,  261,   42,
   43,   45,   45,   46,   47,   41,   91,   91,   91,  293,
  294,   46,   41,   37,   39,   59,   41,   60,   42,   62,
   64,  276,   46,   47,   46,   41,  263,   33,   44,   35,
   36,   11,  262,   41,   40,  276,   30,   17,   32,   45,
  276,  123,   58,   59,   37,  275,   40,   59,   91,   42,
   43,   59,   45,   46,   47,   40,   91,  172,   64,  174,
   93,  291,   40,   33,  171,   35,   36,   91,   41,   91,
   40,  186,   44,   41,   54,   45,   44,   93,   41,  123,
   40,  125,  123,   33,   40,   35,   36,  202,   41,   41,
   40,   44,   44,   41,   64,   45,   44,  212,   91,   40,
  201,   40,   40,   33,   40,   35,   36,   40,   40,   40,
   40,   40,   40,   59,   64,   45,   59,  123,  276,  125,
   40,   61,   59,   33,   59,   35,   36,   91,   41,   41,
   40,  276,   59,   59,   64,   45,  257,  258,  259,  260,
  261,   40,   59,   41,   41,  276,  123,   41,   44,  268,
  172,   37,  174,  123,   64,   41,   42,   43,   44,   45,
   46,   47,   41,   93,  186,   41,   58,  288,   58,    0,
  125,   59,   58,   59,   60,   61,   62,   37,  123,  201,
  202,   41,   42,   43,   44,   45,   41,   47,   41,  276,
  212,  276,  276,  276,   59,   41,    3,   11,   58,   59,
   60,  205,   62,   32,   -1,   91,   -1,   93,   -1,   -1,
  276,   -1,  276,  257,  258,  259,  260,  261,  262,   -1,
  264,  265,  266,  267,   -1,  269,  270,  271,  272,  273,
  274,  275,  276,   93,   -1,   -1,  280,   -1,  281,  282,
  276,  285,  286,  287,  288,  289,  290,  276,  292,  293,
  294,  257,  258,  259,  260,  261,  262,   -1,  264,  265,
  266,  267,  278,  269,  270,  271,  272,  273,  274,  275,
   -1,   -1,   -1,   -1,  280,   -1,   -1,   -1,   -1,  285,
  286,  287,  288,  289,  290,   -1,  292,  257,  258,  259,
  260,  261,  262,   -1,  264,  265,  266,  267,   -1,  269,
  270,  271,  272,  273,  274,  275,   -1,   -1,   -1,   -1,
  280,  261,  262,   -1,  264,  285,  286,  287,  288,  289,
  290,  271,  292,  273,  274,  275,   -1,   -1,   -1,   46,
  280,   -1,  262,   -1,  264,  285,  286,  287,   -1,   -1,
  290,  271,   -1,  273,  274,  275,   -1,   -1,   -1,   -1,
  280,   -1,  262,   -1,  264,  285,  286,  287,   -1,   -1,
  290,  271,   -1,  273,  274,  275,   -1,   -1,   -1,   -1,
  280,   -1,   -1,   -1,   91,  285,  286,  287,   37,   -1,
  290,   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,
   -1,  277,  278,   -1,   -1,  281,  282,  283,  284,   58,
   59,   60,   -1,   62,   37,   -1,   -1,   -1,   41,   42,
   43,   44,   45,   -1,   47,   -1,   -1,  277,  278,   -1,
   -1,  281,  282,  283,  284,   58,   59,   60,   -1,   62,
   -1,   37,   -1,   -1,   93,   41,   42,   43,   44,   45,
   37,   47,   -1,   -1,   41,   42,   43,   44,   45,   -1,
   47,   -1,   58,   59,   60,  172,   62,  174,   -1,   -1,
   93,   58,   59,   60,   -1,   62,   37,   -1,   -1,  186,
   41,   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  201,  202,   -1,   93,   59,   60,
   -1,   62,   -1,   37,   -1,  212,   93,   41,   42,   43,
   -1,   45,   46,   47,   37,   -1,   -1,   -1,   41,   42,
   43,   -1,   45,   46,   47,   -1,   60,   -1,   62,   -1,
   91,   41,   -1,   -1,   44,   -1,   37,   60,   -1,   62,
   41,   42,   43,   -1,   45,   46,   47,   -1,   58,   59,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,   60,
   -1,   62,   -1,   -1,   37,   -1,   -1,   -1,   91,   42,
   43,   44,   45,   46,   47,   -1,   37,   -1,   -1,   -1,
   41,   42,   43,   93,   45,   46,   47,   60,   37,   62,
   91,   -1,   41,   42,   43,   -1,   45,   46,   47,   60,
   -1,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   60,   -1,   62,   -1,   37,   -1,   -1,   91,   41,
   42,   43,   -1,   45,   46,   47,   -1,   -1,  277,  278,
   91,   -1,  281,  282,  283,  284,   -1,   -1,   60,   -1,
   62,   -1,   91,   -1,   -1,   -1,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,  277,  278,   -1,   -1,  281,  282,
  283,  284,   41,   58,   59,   44,   -1,   -1,   -1,   91,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,  261,   58,
   59,  277,  278,   -1,   -1,  281,  282,  283,  284,   -1,
  277,  278,   -1,  276,  281,  282,  283,  284,   93,   -1,
   -1,   -1,   -1,   37,  125,  288,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   93,   -1,  277,  278,   -1,   -1,
  281,  282,  283,  284,   58,   -1,   60,   -1,   62,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  277,  278,   -1,   -1,  281,  282,  283,
  284,   -1,   -1,   -1,  277,  278,   -1,   91,  281,  282,
  283,  284,   -1,   -1,   -1,   37,   -1,  277,  278,   -1,
   42,   43,   -1,   45,   46,   47,  277,  278,   -1,   -1,
  281,  282,  283,  284,   -1,   -1,   -1,   59,   60,   37,
   62,   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,  277,  278,   -1,   -1,  281,  282,
  283,  284,   60,   -1,   62,   -1,  277,  278,   -1,   91,
  281,  282,  283,  284,   -1,   -1,   -1,   -1,  277,  278,
   -1,   -1,  281,  282,  283,  284,  257,  258,  259,  260,
  261,   37,   -1,   91,   -1,   93,   42,   43,   -1,   45,
   46,   47,   -1,   -1,   -1,  277,  278,   -1,  279,  281,
  282,  283,  284,   37,   60,   -1,   62,  288,   42,   43,
   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  277,  278,   37,   59,   60,   -1,   62,   42,
   43,   -1,   45,   46,   47,   91,   -1,   93,  277,  278,
   -1,   41,   -1,   -1,   44,   37,   59,   60,   -1,   62,
   42,   43,   -1,   45,   46,   47,   37,   91,   58,   59,
   -1,   42,   43,   -1,   45,   46,   47,   -1,   60,   -1,
   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   60,
   -1,   62,   -1,  277,  278,   -1,   37,  281,  282,  283,
  284,   42,   43,   93,   45,   46,   47,   -1,   -1,   91,
   -1,   41,   -1,   43,   44,   45,   -1,   37,   -1,   60,
   91,   62,   42,   43,   -1,   45,   46,   47,   58,   59,
   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,   -1,   41,
   91,   43,   44,   45,   -1,  277,  278,   -1,   -1,  281,
  282,  283,  284,   93,   -1,   -1,   58,   59,   60,   -1,
   62,   91,   41,   -1,   41,   44,   -1,   44,   -1,  277,
  278,   -1,   -1,  281,  282,  283,  284,   -1,   -1,   58,
   59,   58,   59,   41,   -1,   -1,   44,   -1,   -1,   -1,
   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   58,   59,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   93,   -1,   93,   -1,   -1,   -1,
   -1,  277,  278,   -1,   -1,  281,  282,  283,  284,   -1,
   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  277,  278,   -1,   -1,  281,  282,  283,
  284,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  277,  278,   -1,   -1,  281,  282,
  283,  284,   -1,   -1,   -1,   -1,   -1,  277,  278,   -1,
   -1,   -1,   -1,  283,  284,  277,  278,   -1,   -1,  281,
  282,  283,  284,   -1,   -1,   -1,  277,  278,   -1,   -1,
  281,  282,  283,  284,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  277,   -1,   -1,   -1,
  281,  282,  283,  284,   -1,   -1,   -1,  277,  278,   -1,
   -1,  281,  282,  283,  284,   -1,   -1,   -1,   -1,   -1,
   -1,  281,  282,  283,  284,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  277,  278,   -1,   -1,  281,
  282,  283,  284,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   52,   -1,   -1,   -1,  277,  278,
  277,  278,   -1,   -1,  283,  284,  283,  284,   66,   67,
   68,   -1,   70,   71,   72,   -1,   -1,   -1,   -1,  277,
  278,   -1,   -1,   -1,   -1,  283,  284,   -1,   -1,   -1,
   -1,   -1,   90,   -1,   92,   -1,   -1,   -1,   -1,   -1,
   98,   -1,   -1,  101,  102,  103,  104,  105,  106,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  120,  121,  122,  123,  124,  125,  126,  127,
  128,  129,  130,  131,  132,   -1,  134,  135,   -1,   -1,
   -1,   -1,   -1,  141,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  171,   -1,  173,   -1,   -1,   -1,  177,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  187,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  213,  214,
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
"DoStmt : DO DoBranchList DoSubStmt OD",
"DoBranchList : DoBranchList DoBranch",
"DoBranchList :",
"DoBranch : DoSubStmt NEXT",
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

//#line 517 "Parser.y"
	
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
//#line 709 "Parser.java"
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
						yyval.stmt = new DoStmt(val_peek(2).slist, val_peek(1).stmt, val_peek(3).loc);
					}
break;
case 40:
//#line 219 "Parser.y"
{
						yyval.slist.add(val_peek(0).stmt);
					}
break;
case 41:
//#line 223 "Parser.y"
{
						yyval = new SemValue();
						yyval.slist = new ArrayList<Tree>();
					}
break;
case 43:
//#line 233 "Parser.y"
{
						yyval.stmt = new DoSubStmt(val_peek(2).expr, val_peek(0).stmt, val_peek(2).loc);
					}
break;
case 44:
//#line 239 "Parser.y"
{
						yyval.stmt = new Tree.Assign(val_peek(2).lvalue, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 45:
//#line 243 "Parser.y"
{
						yyval.stmt = new Tree.Exec(val_peek(0).expr, val_peek(0).loc);
					}
break;
case 46:
//#line 247 "Parser.y"
{
						yyval = new SemValue();
					}
break;
case 48:
//#line 254 "Parser.y"
{
						yyval = new SemValue();
					}
break;
case 49:
//#line 260 "Parser.y"
{
						yyval.lvalue = new Tree.Ident(val_peek(1).expr, val_peek(0).ident, val_peek(0).loc);
						if (val_peek(1).loc == null) {
							yyval.loc = val_peek(0).loc;
						}
					}
break;
case 50:
//#line 267 "Parser.y"
{
						yyval.lvalue = new Tree.Indexed(val_peek(3).expr, val_peek(1).expr, val_peek(3).loc);
					}
break;
case 51:
//#line 273 "Parser.y"
{
						yyval.expr = new Tree.CallExpr(val_peek(4).expr, val_peek(3).ident, val_peek(1).elist, val_peek(3).loc);
						if (val_peek(4).loc == null) {
							yyval.loc = val_peek(3).loc;
						}
					}
break;
case 52:
//#line 282 "Parser.y"
{
						yyval.expr = val_peek(0).lvalue;
					}
break;
case 55:
//#line 288 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.PLUS, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 56:
//#line 292 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.MINUS, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 57:
//#line 296 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.MUL, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 58:
//#line 300 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.DIV, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 59:
//#line 304 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.MOD, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 60:
//#line 308 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.EQ, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 61:
//#line 312 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.NE, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 62:
//#line 316 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.LT, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 63:
//#line 320 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.GT, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 64:
//#line 324 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.LE, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 65:
//#line 328 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.GE, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 66:
//#line 332 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.AND, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 67:
//#line 336 "Parser.y"
{
						yyval.expr = new Tree.Binary(Tree.OR, val_peek(2).expr, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 68:
//#line 340 "Parser.y"
{
						yyval = val_peek(1);
					}
break;
case 69:
//#line 344 "Parser.y"
{
						yyval.expr = new Tree.Unary(Tree.NEG, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 70:
//#line 348 "Parser.y"
{
						yyval.expr = new Tree.Unary(Tree.NOT, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 71:
//#line 352 "Parser.y"
{
						yyval.expr = new Tree.Unary(Tree.GETREAL, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 72:
//#line 356 "Parser.y"
{
						yyval.expr = new Tree.Unary(Tree.GETIMG, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 73:
//#line 360 "Parser.y"
{
						yyval.expr = new Tree.Unary(Tree.TOCOMPLEX, val_peek(0).expr, val_peek(1).loc);
					}
break;
case 74:
//#line 364 "Parser.y"
{
						yyval.expr = new Tree.ReadIntExpr(val_peek(2).loc);
					}
break;
case 75:
//#line 368 "Parser.y"
{
						yyval.expr = new Tree.ReadLineExpr(val_peek(2).loc);
					}
break;
case 76:
//#line 372 "Parser.y"
{
						yyval.expr = new Tree.ThisExpr(val_peek(0).loc);
					}
break;
case 77:
//#line 376 "Parser.y"
{
						yyval.expr = new Tree.SuperExpr(val_peek(0).loc);
					}
break;
case 78:
//#line 380 "Parser.y"
{
						yyval.expr = new Tree.NewClass(val_peek(2).ident, val_peek(3).loc);
					}
break;
case 79:
//#line 384 "Parser.y"
{
						yyval.expr = new Tree.NewArray(val_peek(3).type, val_peek(1).expr, val_peek(4).loc);
					}
break;
case 80:
//#line 388 "Parser.y"
{
						yyval.expr = new Tree.TypeTest(val_peek(3).expr, val_peek(1).ident, val_peek(5).loc);
					}
break;
case 81:
//#line 392 "Parser.y"
{
						yyval.expr = new Tree.TypeCast(val_peek(2).ident, val_peek(0).expr, val_peek(0).loc);
					}
break;
case 82:
//#line 396 "Parser.y"
{
						yyval.expr = new Tree.DcopyExpr(val_peek(1).expr, val_peek(3).loc);
					}
break;
case 83:
//#line 400 "Parser.y"
{
						yyval.expr = new Tree.ScopyExpr(val_peek(1).expr, val_peek(3).loc);
					}
break;
case 84:
//#line 404 "Parser.y"
{
						yyval.expr = new Tree.CaseExpr(val_peek(5).expr, val_peek(2).elist, val_peek(1).expr, val_peek(7).loc);
					}
break;
case 85:
//#line 410 "Parser.y"
{
						yyval.elist.add(val_peek(0).expr);
					}
break;
case 86:
//#line 414 "Parser.y"
{
						yyval = new SemValue();
						yyval.elist = new ArrayList<Expr>();
					}
break;
case 87:
//#line 421 "Parser.y"
{
						yyval.expr = new Tree.ACaseExpr(val_peek(3).expr, val_peek(1).expr, val_peek(3).loc);
					}
break;
case 88:
//#line 427 "Parser.y"
{
						yyval.expr = new Tree.DefaultExpr(val_peek(1).expr, val_peek(3).loc);
					}
break;
case 89:
//#line 433 "Parser.y"
{
						yyval.expr = new Tree.Literal(val_peek(0).typeTag, val_peek(0).literal, val_peek(0).loc);
					}
break;
case 90:
//#line 437 "Parser.y"
{
						yyval.expr = new Null(val_peek(0).loc);
					}
break;
case 92:
//#line 444 "Parser.y"
{
						yyval = new SemValue();
						yyval.elist = new ArrayList<Tree.Expr>();
					}
break;
case 93:
//#line 451 "Parser.y"
{
						yyval.elist.add(val_peek(0).expr);
					}
break;
case 94:
//#line 455 "Parser.y"
{
						yyval.elist = new ArrayList<Tree.Expr>();
						yyval.elist.add(val_peek(0).expr);
					}
break;
case 95:
//#line 462 "Parser.y"
{
						yyval.stmt = new Tree.WhileLoop(val_peek(2).expr, val_peek(0).stmt, val_peek(4).loc);
					}
break;
case 96:
//#line 468 "Parser.y"
{
						yyval.stmt = new Tree.ForLoop(val_peek(6).stmt, val_peek(4).expr, val_peek(2).stmt, val_peek(0).stmt, val_peek(8).loc);
					}
break;
case 97:
//#line 474 "Parser.y"
{
						yyval.stmt = new Tree.Break(val_peek(0).loc);
					}
break;
case 98:
//#line 480 "Parser.y"
{
						yyval.stmt = new Tree.If(val_peek(3).expr, val_peek(1).stmt, val_peek(0).stmt, val_peek(5).loc);
					}
break;
case 99:
//#line 486 "Parser.y"
{
						yyval.stmt = val_peek(0).stmt;
					}
break;
case 100:
//#line 490 "Parser.y"
{
						yyval = new SemValue();
					}
break;
case 101:
//#line 496 "Parser.y"
{
						yyval.stmt = new Tree.Return(val_peek(0).expr, val_peek(1).loc);
					}
break;
case 102:
//#line 500 "Parser.y"
{
						yyval.stmt = new Tree.Return(null, val_peek(0).loc);
					}
break;
case 103:
//#line 506 "Parser.y"
{
						yyval.stmt = new Print(val_peek(1).elist, val_peek(3).loc);
					}
break;
case 104:
//#line 512 "Parser.y"
{
						yyval.stmt = new PrintComp(val_peek(1).elist, val_peek(3).loc);
					}
break;
//#line 1400 "Parser.java"
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
