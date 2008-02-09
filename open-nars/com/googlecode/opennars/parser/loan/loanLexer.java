package com.googlecode.opennars.parser.loan;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class loanLexer extends Lexer {
    public static final int COMMA=20;
    public static final int PN_CHARS_U=75;
    public static final int MINUS=44;
    public static final int AT_PREFIX=8;
    public static final int PERCENT=16;
    public static final int OPEN_BRACE=40;
    public static final int DOUBLE=49;
    public static final int EQUIVALENCE=35;
    public static final int AT_BASE=4;
    public static final int FALSE=57;
    public static final int EQUIVALENCE_CONC=37;
    public static final int PN_CHARS_BASE=69;
    public static final int QUERY_VAR=47;
    public static final int AT_DELAY=10;
    public static final int LBRACKET=42;
    public static final int INHERITANCE=26;
    public static final int INSTANCE=28;
    public static final int TILDE=45;
    public static final int DECIMAL=17;
    public static final int QUESTION=15;
    public static final int CONJ=19;
    public static final int DISJ=21;
    public static final int IMPLICATION=31;
    public static final int DOT=6;
    public static final int STM_VAR=48;
    public static final int RANGLE=68;
    public static final int INTEGER=12;
    public static final int IMPLICATION_RETRO=33;
    public static final int FUTURE=25;
    public static final int RBRACKET=43;
    public static final int RPAREN=13;
    public static final int PN_PREFIX=65;
    public static final int EQUIVALENCE_PRED=36;
    public static final int LANGLE=67;
    public static final int LPAREN=11;
    public static final int INSTANCE_PROPERTY=30;
    public static final int ECHAR=74;
    public static final int PLUS=73;
    public static final int DIGIT=70;
    public static final int AMPERSAND=38;
    public static final int PAST=23;
    public static final int PNAME_NS=9;
    public static final int INTEGER_NEGATIVE=53;
    public static final int SIMILARITY=27;
    public static final int PROPERTY=29;
    public static final int DECIMAL_POSITIVE=51;
    public static final int STRING_LITERAL_LONG1=60;
    public static final int IMPLICATION_PRED=32;
    public static final int WS=64;
    public static final int PNAME_LN=62;
    public static final int EXCLAMATION=14;
    public static final int LANGTAG=71;
    public static final int PN_LOCAL=66;
    public static final int CLOSE_BRACE=41;
    public static final int VARNAME=76;
    public static final int COMMENT=78;
    public static final int PN_CHARS=77;
    public static final int PRESENT=24;
    public static final int AT_IMPORT=7;
    public static final int DOUBLE_NEGATIVE=55;
    public static final int STRING_LITERAL_LONG2=61;
    public static final int SEMICOLON=18;
    public static final int EXPONENT=72;
    public static final int BAR=39;
    public static final int DECIMAL_NEGATIVE=54;
    public static final int IRI_REF=5;
    public static final int EOF=-1;
    public static final int DOUBLE_POSITIVE=52;
    public static final int EOL=63;
    public static final int REFERENCE=79;
    public static final int Tokens=80;
    public static final int INTEGER_POSITIVE=50;
    public static final int STAR=46;
    public static final int NOT=22;
    public static final int TRUE=56;
    public static final int STRING_LITERAL2=59;
    public static final int STRING_LITERAL1=58;
    public static final int IMPLICATION_CONC=34;
    public loanLexer() {;} 
    public loanLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "/Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g"; }

    // $ANTLR start WS
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:152:5: ( ( ' ' | '\\t' | EOL )+ )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:152:7: ( ' ' | '\\t' | EOL )+
            {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:152:7: ( ' ' | '\\t' | EOL )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\t' && LA1_0<='\n')||LA1_0=='\r'||LA1_0==' ') ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

             channel=HIDDEN; 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WS

    // $ANTLR start AT_IMPORT
    public final void mAT_IMPORT() throws RecognitionException {
        try {
            int _type = AT_IMPORT;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:156:2: ( '@import' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:156:4: '@import'
            {
            match("@import"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AT_IMPORT

    // $ANTLR start AT_PREFIX
    public final void mAT_PREFIX() throws RecognitionException {
        try {
            int _type = AT_PREFIX;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:160:2: ( '@prefix' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:160:4: '@prefix'
            {
            match("@prefix"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AT_PREFIX

    // $ANTLR start AT_BASE
    public final void mAT_BASE() throws RecognitionException {
        try {
            int _type = AT_BASE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:163:9: ( '@base' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:163:11: '@base'
            {
            match("@base"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AT_BASE

    // $ANTLR start AT_DELAY
    public final void mAT_DELAY() throws RecognitionException {
        try {
            int _type = AT_DELAY;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:167:2: ( '@delay' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:167:4: '@delay'
            {
            match("@delay"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AT_DELAY

    // $ANTLR start INHERITANCE
    public final void mINHERITANCE() throws RecognitionException {
        try {
            int _type = INHERITANCE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:172:2: ( '-->' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:172:4: '-->'
            {
            match("-->"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INHERITANCE

    // $ANTLR start SIMILARITY
    public final void mSIMILARITY() throws RecognitionException {
        try {
            int _type = SIMILARITY;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:176:2: ( '<->' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:176:4: '<->'
            {
            match("<->"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SIMILARITY

    // $ANTLR start INSTANCE
    public final void mINSTANCE() throws RecognitionException {
        try {
            int _type = INSTANCE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:180:2: ( '}->' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:180:4: '}->'
            {
            match("}->"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INSTANCE

    // $ANTLR start PROPERTY
    public final void mPROPERTY() throws RecognitionException {
        try {
            int _type = PROPERTY;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:185:2: ( '--[' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:185:4: '--['
            {
            match("--["); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PROPERTY

    // $ANTLR start INSTANCE_PROPERTY
    public final void mINSTANCE_PROPERTY() throws RecognitionException {
        try {
            int _type = INSTANCE_PROPERTY;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:190:2: ( '}-[' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:190:4: '}-['
            {
            match("}-["); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INSTANCE_PROPERTY

    // $ANTLR start IMPLICATION
    public final void mIMPLICATION() throws RecognitionException {
        try {
            int _type = IMPLICATION;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:195:2: ( '==>' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:195:4: '==>'
            {
            match("==>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IMPLICATION

    // $ANTLR start IMPLICATION_PRED
    public final void mIMPLICATION_PRED() throws RecognitionException {
        try {
            int _type = IMPLICATION_PRED;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:200:2: ( '=/>' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:200:4: '=/>'
            {
            match("=/>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IMPLICATION_PRED

    // $ANTLR start IMPLICATION_RETRO
    public final void mIMPLICATION_RETRO() throws RecognitionException {
        try {
            int _type = IMPLICATION_RETRO;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:205:2: ( '=\\\\>' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:205:4: '=\\\\>'
            {
            match("=\\>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IMPLICATION_RETRO

    // $ANTLR start IMPLICATION_CONC
    public final void mIMPLICATION_CONC() throws RecognitionException {
        try {
            int _type = IMPLICATION_CONC;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:210:2: ( '=|>' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:210:4: '=|>'
            {
            match("=|>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IMPLICATION_CONC

    // $ANTLR start EQUIVALENCE
    public final void mEQUIVALENCE() throws RecognitionException {
        try {
            int _type = EQUIVALENCE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:215:2: ( '<=>' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:215:4: '<=>'
            {
            match("<=>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EQUIVALENCE

    // $ANTLR start EQUIVALENCE_PRED
    public final void mEQUIVALENCE_PRED() throws RecognitionException {
        try {
            int _type = EQUIVALENCE_PRED;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:220:2: ( '</>' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:220:4: '</>'
            {
            match("</>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EQUIVALENCE_PRED

    // $ANTLR start EQUIVALENCE_CONC
    public final void mEQUIVALENCE_CONC() throws RecognitionException {
        try {
            int _type = EQUIVALENCE_CONC;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:225:2: ( '<|>' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:225:4: '<|>'
            {
            match("<|>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EQUIVALENCE_CONC

    // $ANTLR start NOT
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:228:5: ( '!!' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:228:7: '!!'
            {
            match("!!"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NOT

    // $ANTLR start PAST
    public final void mPAST() throws RecognitionException {
        try {
            int _type = PAST;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:231:6: ( '\\\\>' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:231:8: '\\\\>'
            {
            match("\\>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PAST

    // $ANTLR start PRESENT
    public final void mPRESENT() throws RecognitionException {
        try {
            int _type = PRESENT;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:234:9: ( '|>' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:234:11: '|>'
            {
            match("|>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PRESENT

    // $ANTLR start FUTURE
    public final void mFUTURE() throws RecognitionException {
        try {
            int _type = FUTURE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:237:9: ( '/>' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:237:11: '/>'
            {
            match("/>"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end FUTURE

    // $ANTLR start CONJ
    public final void mCONJ() throws RecognitionException {
        try {
            int _type = CONJ;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:240:6: ( '&&' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:240:8: '&&'
            {
            match("&&"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CONJ

    // $ANTLR start DISJ
    public final void mDISJ() throws RecognitionException {
        try {
            int _type = DISJ;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:243:6: ( '||' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:243:8: '||'
            {
            match("||"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DISJ

    // $ANTLR start OPEN_BRACE
    public final void mOPEN_BRACE() throws RecognitionException {
        try {
            int _type = OPEN_BRACE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:247:2: ( '{' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:247:4: '{'
            {
            match('{'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OPEN_BRACE

    // $ANTLR start CLOSE_BRACE
    public final void mCLOSE_BRACE() throws RecognitionException {
        try {
            int _type = CLOSE_BRACE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:251:2: ( '}' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:251:4: '}'
            {
            match('}'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CLOSE_BRACE

    // $ANTLR start LPAREN
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:254:8: ( '(' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:254:10: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LPAREN

    // $ANTLR start RPAREN
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:257:8: ( ')' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:257:10: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RPAREN

    // $ANTLR start LBRACKET
    public final void mLBRACKET() throws RecognitionException {
        try {
            int _type = LBRACKET;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:261:2: ( '[' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:261:4: '['
            {
            match('['); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LBRACKET

    // $ANTLR start RBRACKET
    public final void mRBRACKET() throws RecognitionException {
        try {
            int _type = RBRACKET;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:265:2: ( ']' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:265:4: ']'
            {
            match(']'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RBRACKET

    // $ANTLR start PNAME_NS
    public final void mPNAME_NS() throws RecognitionException {
        try {
            int _type = PNAME_NS;
            Token p=null;

            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:271:5: ( (p= PN_PREFIX )? ':' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:271:7: (p= PN_PREFIX )? ':'
            {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:271:8: (p= PN_PREFIX )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( ((LA2_0>='A' && LA2_0<='Z')||(LA2_0>='a' && LA2_0<='z')||(LA2_0>='\u00C0' && LA2_0<='\u00D6')||(LA2_0>='\u00D8' && LA2_0<='\u00F6')||(LA2_0>='\u00F8' && LA2_0<='\u02FF')||(LA2_0>='\u0370' && LA2_0<='\u037D')||(LA2_0>='\u037F' && LA2_0<='\u1FFF')||(LA2_0>='\u200C' && LA2_0<='\u200D')||(LA2_0>='\u2070' && LA2_0<='\u218F')||(LA2_0>='\u2C00' && LA2_0<='\u2FEF')||(LA2_0>='\u3001' && LA2_0<='\uD7FF')||(LA2_0>='\uF900' && LA2_0<='\uFDCF')||(LA2_0>='\uFDF0' && LA2_0<='\uFFFD')) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:271:8: p= PN_PREFIX
                    {
                    int pStart439 = getCharIndex();
                    mPN_PREFIX(); 
                    p = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, pStart439, getCharIndex()-1);

                    }
                    break;

            }

            match(':'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PNAME_NS

    // $ANTLR start PNAME_LN
    public final void mPNAME_LN() throws RecognitionException {
        try {
            int _type = PNAME_LN;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:275:5: ( PNAME_NS PN_LOCAL )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:275:7: PNAME_NS PN_LOCAL
            {
            mPNAME_NS(); 
            mPN_LOCAL(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PNAME_LN

    // $ANTLR start TRUE
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:279:5: ( ( 'T' | 't' ) ( 'R' | 'r' ) ( 'U' | 'u' ) ( 'E' | 'e' ) )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:279:7: ( 'T' | 't' ) ( 'R' | 'r' ) ( 'U' | 'u' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end TRUE

    // $ANTLR start FALSE
    public final void mFALSE() throws RecognitionException {
        try {
            int _type = FALSE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:283:5: ( ( 'F' | 'f' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'E' | 'e' ) )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:283:7: ( 'F' | 'f' ) ( 'A' | 'a' ) ( 'L' | 'l' ) ( 'S' | 's' ) ( 'E' | 'e' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end FALSE

    // $ANTLR start IRI_REF
    public final void mIRI_REF() throws RecognitionException {
        try {
            int _type = IRI_REF;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:287:5: ( LANGLE ( options {greedy=false; } : ~ ( LANGLE | RANGLE | '\"' | OPEN_BRACE | CLOSE_BRACE | '|' | '^' | '\\\\' | '`' | ( '\\u0000' .. '\\u0020' ) ) )* RANGLE )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:287:7: LANGLE ( options {greedy=false; } : ~ ( LANGLE | RANGLE | '\"' | OPEN_BRACE | CLOSE_BRACE | '|' | '^' | '\\\\' | '`' | ( '\\u0000' .. '\\u0020' ) ) )* RANGLE
            {
            mLANGLE(); 
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:287:14: ( options {greedy=false; } : ~ ( LANGLE | RANGLE | '\"' | OPEN_BRACE | CLOSE_BRACE | '|' | '^' | '\\\\' | '`' | ( '\\u0000' .. '\\u0020' ) ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='>') ) {
                    alt3=2;
                }
                else if ( (LA3_0=='!'||(LA3_0>='#' && LA3_0<=';')||LA3_0=='='||(LA3_0>='?' && LA3_0<='[')||LA3_0==']'||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')||(LA3_0>='~' && LA3_0<='\uFFFE')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:287:42: ~ ( LANGLE | RANGLE | '\"' | OPEN_BRACE | CLOSE_BRACE | '|' | '^' | '\\\\' | '`' | ( '\\u0000' .. '\\u0020' ) )
            	    {
            	    if ( input.LA(1)=='!'||(input.LA(1)>='#' && input.LA(1)<=';')||input.LA(1)=='='||(input.LA(1)>='?' && input.LA(1)<='[')||input.LA(1)==']'||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='~' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            mRANGLE(); 
             setText(getText().substring(1, getText().length() - 1)); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IRI_REF

    // $ANTLR start LANGTAG
    public final void mLANGTAG() throws RecognitionException {
        try {
            int _type = LANGTAG;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:291:5: ( '@' ( PN_CHARS_BASE )+ ( MINUS ( PN_CHARS_BASE DIGIT )+ )* )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:291:7: '@' ( PN_CHARS_BASE )+ ( MINUS ( PN_CHARS_BASE DIGIT )+ )*
            {
            match('@'); 
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:291:11: ( PN_CHARS_BASE )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='A' && LA4_0<='Z')||(LA4_0>='a' && LA4_0<='z')||(LA4_0>='\u00C0' && LA4_0<='\u00D6')||(LA4_0>='\u00D8' && LA4_0<='\u00F6')||(LA4_0>='\u00F8' && LA4_0<='\u02FF')||(LA4_0>='\u0370' && LA4_0<='\u037D')||(LA4_0>='\u037F' && LA4_0<='\u1FFF')||(LA4_0>='\u200C' && LA4_0<='\u200D')||(LA4_0>='\u2070' && LA4_0<='\u218F')||(LA4_0>='\u2C00' && LA4_0<='\u2FEF')||(LA4_0>='\u3001' && LA4_0<='\uD7FF')||(LA4_0>='\uF900' && LA4_0<='\uFDCF')||(LA4_0>='\uFDF0' && LA4_0<='\uFFFD')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:291:11: PN_CHARS_BASE
            	    {
            	    mPN_CHARS_BASE(); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:291:26: ( MINUS ( PN_CHARS_BASE DIGIT )+ )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0=='-') ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:291:27: MINUS ( PN_CHARS_BASE DIGIT )+
            	    {
            	    mMINUS(); 
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:291:33: ( PN_CHARS_BASE DIGIT )+
            	    int cnt5=0;
            	    loop5:
            	    do {
            	        int alt5=2;
            	        int LA5_0 = input.LA(1);

            	        if ( ((LA5_0>='A' && LA5_0<='Z')||(LA5_0>='a' && LA5_0<='z')||(LA5_0>='\u00C0' && LA5_0<='\u00D6')||(LA5_0>='\u00D8' && LA5_0<='\u00F6')||(LA5_0>='\u00F8' && LA5_0<='\u02FF')||(LA5_0>='\u0370' && LA5_0<='\u037D')||(LA5_0>='\u037F' && LA5_0<='\u1FFF')||(LA5_0>='\u200C' && LA5_0<='\u200D')||(LA5_0>='\u2070' && LA5_0<='\u218F')||(LA5_0>='\u2C00' && LA5_0<='\u2FEF')||(LA5_0>='\u3001' && LA5_0<='\uD7FF')||(LA5_0>='\uF900' && LA5_0<='\uFDCF')||(LA5_0>='\uFDF0' && LA5_0<='\uFFFD')) ) {
            	            alt5=1;
            	        }


            	        switch (alt5) {
            	    	case 1 :
            	    	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:291:34: PN_CHARS_BASE DIGIT
            	    	    {
            	    	    mPN_CHARS_BASE(); 
            	    	    mDIGIT(); 

            	    	    }
            	    	    break;

            	    	default :
            	    	    if ( cnt5 >= 1 ) break loop5;
            	                EarlyExitException eee =
            	                    new EarlyExitException(5, input);
            	                throw eee;
            	        }
            	        cnt5++;
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LANGTAG

    // $ANTLR start QUERY_VAR
    public final void mQUERY_VAR() throws RecognitionException {
        try {
            int _type = QUERY_VAR;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:295:2: ( '?' PN_LOCAL )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:295:4: '?' PN_LOCAL
            {
            match('?'); 
            mPN_LOCAL(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end QUERY_VAR

    // $ANTLR start STM_VAR
    public final void mSTM_VAR() throws RecognitionException {
        try {
            int _type = STM_VAR;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:298:9: ( '#' PN_LOCAL ( '(' ( PN_LOCAL ( ',' PN_LOCAL )* )? ')' )? )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:298:11: '#' PN_LOCAL ( '(' ( PN_LOCAL ( ',' PN_LOCAL )* )? ')' )?
            {
            match('#'); 
            mPN_LOCAL(); 
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:298:24: ( '(' ( PN_LOCAL ( ',' PN_LOCAL )* )? ')' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='(') ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:298:25: '(' ( PN_LOCAL ( ',' PN_LOCAL )* )? ')'
                    {
                    match('('); 
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:298:29: ( PN_LOCAL ( ',' PN_LOCAL )* )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( ((LA8_0>='0' && LA8_0<='9')||(LA8_0>='A' && LA8_0<='Z')||LA8_0=='_'||(LA8_0>='a' && LA8_0<='z')||(LA8_0>='\u00C0' && LA8_0<='\u00D6')||(LA8_0>='\u00D8' && LA8_0<='\u00F6')||(LA8_0>='\u00F8' && LA8_0<='\u02FF')||(LA8_0>='\u0370' && LA8_0<='\u037D')||(LA8_0>='\u037F' && LA8_0<='\u1FFF')||(LA8_0>='\u200C' && LA8_0<='\u200D')||(LA8_0>='\u2070' && LA8_0<='\u218F')||(LA8_0>='\u2C00' && LA8_0<='\u2FEF')||(LA8_0>='\u3001' && LA8_0<='\uD7FF')||(LA8_0>='\uF900' && LA8_0<='\uFDCF')||(LA8_0>='\uFDF0' && LA8_0<='\uFFFD')) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:298:30: PN_LOCAL ( ',' PN_LOCAL )*
                            {
                            mPN_LOCAL(); 
                            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:298:39: ( ',' PN_LOCAL )*
                            loop7:
                            do {
                                int alt7=2;
                                int LA7_0 = input.LA(1);

                                if ( (LA7_0==',') ) {
                                    alt7=1;
                                }


                                switch (alt7) {
                            	case 1 :
                            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:298:40: ',' PN_LOCAL
                            	    {
                            	    match(','); 
                            	    mPN_LOCAL(); 

                            	    }
                            	    break;

                            	default :
                            	    break loop7;
                                }
                            } while (true);


                            }
                            break;

                    }

                    match(')'); 

                    }
                    break;

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STM_VAR

    // $ANTLR start INTEGER
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:302:5: ( ( DIGIT )+ )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:302:7: ( DIGIT )+
            {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:302:7: ( DIGIT )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='0' && LA10_0<='9')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:302:7: DIGIT
            	    {
            	    mDIGIT(); 

            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INTEGER

    // $ANTLR start DECIMAL
    public final void mDECIMAL() throws RecognitionException {
        try {
            int _type = DECIMAL;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:306:5: ( ( DIGIT )+ DOT ( DIGIT )* | DOT ( DIGIT )+ )
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( ((LA14_0>='0' && LA14_0<='9')) ) {
                alt14=1;
            }
            else if ( (LA14_0=='.') ) {
                alt14=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("305:1: DECIMAL : ( ( DIGIT )+ DOT ( DIGIT )* | DOT ( DIGIT )+ );", 14, 0, input);

                throw nvae;
            }
            switch (alt14) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:306:7: ( DIGIT )+ DOT ( DIGIT )*
                    {
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:306:7: ( DIGIT )+
                    int cnt11=0;
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( ((LA11_0>='0' && LA11_0<='9')) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:306:7: DIGIT
                    	    {
                    	    mDIGIT(); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt11 >= 1 ) break loop11;
                                EarlyExitException eee =
                                    new EarlyExitException(11, input);
                                throw eee;
                        }
                        cnt11++;
                    } while (true);

                    mDOT(); 
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:306:18: ( DIGIT )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( ((LA12_0>='0' && LA12_0<='9')) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:306:18: DIGIT
                    	    {
                    	    mDIGIT(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);


                    }
                    break;
                case 2 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:307:7: DOT ( DIGIT )+
                    {
                    mDOT(); 
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:307:11: ( DIGIT )+
                    int cnt13=0;
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( ((LA13_0>='0' && LA13_0<='9')) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:307:11: DIGIT
                    	    {
                    	    mDIGIT(); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt13 >= 1 ) break loop13;
                                EarlyExitException eee =
                                    new EarlyExitException(13, input);
                                throw eee;
                        }
                        cnt13++;
                    } while (true);


                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DECIMAL

    // $ANTLR start DOUBLE
    public final void mDOUBLE() throws RecognitionException {
        try {
            int _type = DOUBLE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:311:5: ( ( DIGIT )+ DOT ( DIGIT )* EXPONENT | DOT ( DIGIT )+ EXPONENT | ( DIGIT )+ EXPONENT )
            int alt19=3;
            alt19 = dfa19.predict(input);
            switch (alt19) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:311:7: ( DIGIT )+ DOT ( DIGIT )* EXPONENT
                    {
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:311:7: ( DIGIT )+
                    int cnt15=0;
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( ((LA15_0>='0' && LA15_0<='9')) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:311:7: DIGIT
                    	    {
                    	    mDIGIT(); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt15 >= 1 ) break loop15;
                                EarlyExitException eee =
                                    new EarlyExitException(15, input);
                                throw eee;
                        }
                        cnt15++;
                    } while (true);

                    mDOT(); 
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:311:18: ( DIGIT )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( ((LA16_0>='0' && LA16_0<='9')) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:311:18: DIGIT
                    	    {
                    	    mDIGIT(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);

                    mEXPONENT(); 

                    }
                    break;
                case 2 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:312:7: DOT ( DIGIT )+ EXPONENT
                    {
                    mDOT(); 
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:312:11: ( DIGIT )+
                    int cnt17=0;
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( ((LA17_0>='0' && LA17_0<='9')) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:312:11: DIGIT
                    	    {
                    	    mDIGIT(); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt17 >= 1 ) break loop17;
                                EarlyExitException eee =
                                    new EarlyExitException(17, input);
                                throw eee;
                        }
                        cnt17++;
                    } while (true);

                    mEXPONENT(); 

                    }
                    break;
                case 3 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:313:7: ( DIGIT )+ EXPONENT
                    {
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:313:7: ( DIGIT )+
                    int cnt18=0;
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( ((LA18_0>='0' && LA18_0<='9')) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:313:7: DIGIT
                    	    {
                    	    mDIGIT(); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt18 >= 1 ) break loop18;
                                EarlyExitException eee =
                                    new EarlyExitException(18, input);
                                throw eee;
                        }
                        cnt18++;
                    } while (true);

                    mEXPONENT(); 

                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DOUBLE

    // $ANTLR start INTEGER_POSITIVE
    public final void mINTEGER_POSITIVE() throws RecognitionException {
        try {
            int _type = INTEGER_POSITIVE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:317:5: ( PLUS INTEGER )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:317:7: PLUS INTEGER
            {
            mPLUS(); 
            mINTEGER(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INTEGER_POSITIVE

    // $ANTLR start DECIMAL_POSITIVE
    public final void mDECIMAL_POSITIVE() throws RecognitionException {
        try {
            int _type = DECIMAL_POSITIVE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:321:5: ( PLUS DECIMAL )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:321:7: PLUS DECIMAL
            {
            mPLUS(); 
            mDECIMAL(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DECIMAL_POSITIVE

    // $ANTLR start DOUBLE_POSITIVE
    public final void mDOUBLE_POSITIVE() throws RecognitionException {
        try {
            int _type = DOUBLE_POSITIVE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:325:5: ( PLUS DOUBLE )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:325:7: PLUS DOUBLE
            {
            mPLUS(); 
            mDOUBLE(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DOUBLE_POSITIVE

    // $ANTLR start INTEGER_NEGATIVE
    public final void mINTEGER_NEGATIVE() throws RecognitionException {
        try {
            int _type = INTEGER_NEGATIVE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:329:5: ( MINUS INTEGER )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:329:7: MINUS INTEGER
            {
            mMINUS(); 
            mINTEGER(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INTEGER_NEGATIVE

    // $ANTLR start DECIMAL_NEGATIVE
    public final void mDECIMAL_NEGATIVE() throws RecognitionException {
        try {
            int _type = DECIMAL_NEGATIVE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:333:5: ( MINUS DECIMAL )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:333:7: MINUS DECIMAL
            {
            mMINUS(); 
            mDECIMAL(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DECIMAL_NEGATIVE

    // $ANTLR start DOUBLE_NEGATIVE
    public final void mDOUBLE_NEGATIVE() throws RecognitionException {
        try {
            int _type = DOUBLE_NEGATIVE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:337:5: ( MINUS DOUBLE )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:337:7: MINUS DOUBLE
            {
            mMINUS(); 
            mDOUBLE(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DOUBLE_NEGATIVE

    // $ANTLR start EXPONENT
    public final void mEXPONENT() throws RecognitionException {
        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:342:5: ( ( 'e' | 'E' ) ( PLUS | MINUS )? ( DIGIT )+ )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:342:7: ( 'e' | 'E' ) ( PLUS | MINUS )? ( DIGIT )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:342:17: ( PLUS | MINUS )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0=='+'||LA20_0=='-') ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse =
                            new MismatchedSetException(null,input);
                        recover(mse);    throw mse;
                    }


                    }
                    break;

            }

            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:342:31: ( DIGIT )+
            int cnt21=0;
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>='0' && LA21_0<='9')) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:342:31: DIGIT
            	    {
            	    mDIGIT(); 

            	    }
            	    break;

            	default :
            	    if ( cnt21 >= 1 ) break loop21;
                        EarlyExitException eee =
                            new EarlyExitException(21, input);
                        throw eee;
                }
                cnt21++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end EXPONENT

    // $ANTLR start STRING_LITERAL1
    public final void mSTRING_LITERAL1() throws RecognitionException {
        try {
            int _type = STRING_LITERAL1;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:346:5: ( '\\'' ( options {greedy=false; } : ~ ( '\\u0027' | '\\u005C' | '\\u000A' | '\\u000D' ) | ECHAR )* '\\'' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:346:7: '\\'' ( options {greedy=false; } : ~ ( '\\u0027' | '\\u005C' | '\\u000A' | '\\u000D' ) | ECHAR )* '\\''
            {
            match('\''); 
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:346:12: ( options {greedy=false; } : ~ ( '\\u0027' | '\\u005C' | '\\u000A' | '\\u000D' ) | ECHAR )*
            loop22:
            do {
                int alt22=3;
                int LA22_0 = input.LA(1);

                if ( (LA22_0=='\'') ) {
                    alt22=3;
                }
                else if ( ((LA22_0>='\u0000' && LA22_0<='\t')||(LA22_0>='\u000B' && LA22_0<='\f')||(LA22_0>='\u000E' && LA22_0<='&')||(LA22_0>='(' && LA22_0<='[')||(LA22_0>=']' && LA22_0<='\uFFFE')) ) {
                    alt22=1;
                }
                else if ( (LA22_0=='\\') ) {
                    alt22=2;
                }


                switch (alt22) {
            	case 1 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:346:40: ~ ( '\\u0027' | '\\u005C' | '\\u000A' | '\\u000D' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;
            	case 2 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:346:87: ECHAR
            	    {
            	    mECHAR(); 

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            match('\''); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STRING_LITERAL1

    // $ANTLR start STRING_LITERAL2
    public final void mSTRING_LITERAL2() throws RecognitionException {
        try {
            int _type = STRING_LITERAL2;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:350:5: ( '\"' ( options {greedy=false; } : ~ ( '\\u0022' | '\\u005C' | '\\u000A' | '\\u000D' ) | ECHAR )* '\"' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:350:7: '\"' ( options {greedy=false; } : ~ ( '\\u0022' | '\\u005C' | '\\u000A' | '\\u000D' ) | ECHAR )* '\"'
            {
            match('\"'); 
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:350:12: ( options {greedy=false; } : ~ ( '\\u0022' | '\\u005C' | '\\u000A' | '\\u000D' ) | ECHAR )*
            loop23:
            do {
                int alt23=3;
                int LA23_0 = input.LA(1);

                if ( (LA23_0=='\"') ) {
                    alt23=3;
                }
                else if ( ((LA23_0>='\u0000' && LA23_0<='\t')||(LA23_0>='\u000B' && LA23_0<='\f')||(LA23_0>='\u000E' && LA23_0<='!')||(LA23_0>='#' && LA23_0<='[')||(LA23_0>=']' && LA23_0<='\uFFFE')) ) {
                    alt23=1;
                }
                else if ( (LA23_0=='\\') ) {
                    alt23=2;
                }


                switch (alt23) {
            	case 1 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:350:40: ~ ( '\\u0022' | '\\u005C' | '\\u000A' | '\\u000D' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;
            	case 2 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:350:87: ECHAR
            	    {
            	    mECHAR(); 

            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            match('\"'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STRING_LITERAL2

    // $ANTLR start STRING_LITERAL_LONG1
    public final void mSTRING_LITERAL_LONG1() throws RecognitionException {
        try {
            int _type = STRING_LITERAL_LONG1;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:354:5: ( '\\'\\'\\'' ( options {greedy=false; } : ( '\\'' | '\\'\\'' )? (~ ( '\\'' | '\\\\' ) | ECHAR ) )* '\\'\\'\\'' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:354:9: '\\'\\'\\'' ( options {greedy=false; } : ( '\\'' | '\\'\\'' )? (~ ( '\\'' | '\\\\' ) | ECHAR ) )* '\\'\\'\\''
            {
            match("\'\'\'"); 

            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:354:18: ( options {greedy=false; } : ( '\\'' | '\\'\\'' )? (~ ( '\\'' | '\\\\' ) | ECHAR ) )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0=='\'') ) {
                    int LA26_1 = input.LA(2);

                    if ( (LA26_1=='\'') ) {
                        int LA26_3 = input.LA(3);

                        if ( (LA26_3=='\'') ) {
                            alt26=2;
                        }
                        else if ( ((LA26_3>='\u0000' && LA26_3<='&')||(LA26_3>='(' && LA26_3<='\uFFFE')) ) {
                            alt26=1;
                        }


                    }
                    else if ( ((LA26_1>='\u0000' && LA26_1<='&')||(LA26_1>='(' && LA26_1<='\uFFFE')) ) {
                        alt26=1;
                    }


                }
                else if ( ((LA26_0>='\u0000' && LA26_0<='&')||(LA26_0>='(' && LA26_0<='\uFFFE')) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:354:46: ( '\\'' | '\\'\\'' )? (~ ( '\\'' | '\\\\' ) | ECHAR )
            	    {
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:354:46: ( '\\'' | '\\'\\'' )?
            	    int alt24=3;
            	    int LA24_0 = input.LA(1);

            	    if ( (LA24_0=='\'') ) {
            	        int LA24_1 = input.LA(2);

            	        if ( (LA24_1=='\'') ) {
            	            alt24=2;
            	        }
            	        else if ( ((LA24_1>='\u0000' && LA24_1<='&')||(LA24_1>='(' && LA24_1<='\uFFFE')) ) {
            	            alt24=1;
            	        }
            	    }
            	    switch (alt24) {
            	        case 1 :
            	            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:354:48: '\\''
            	            {
            	            match('\''); 

            	            }
            	            break;
            	        case 2 :
            	            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:354:55: '\\'\\''
            	            {
            	            match("\'\'"); 


            	            }
            	            break;

            	    }

            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:354:65: (~ ( '\\'' | '\\\\' ) | ECHAR )
            	    int alt25=2;
            	    int LA25_0 = input.LA(1);

            	    if ( ((LA25_0>='\u0000' && LA25_0<='&')||(LA25_0>='(' && LA25_0<='[')||(LA25_0>=']' && LA25_0<='\uFFFE')) ) {
            	        alt25=1;
            	    }
            	    else if ( (LA25_0=='\\') ) {
            	        alt25=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("354:65: (~ ( '\\'' | '\\\\' ) | ECHAR )", 25, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt25) {
            	        case 1 :
            	            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:354:67: ~ ( '\\'' | '\\\\' )
            	            {
            	            if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
            	                input.consume();

            	            }
            	            else {
            	                MismatchedSetException mse =
            	                    new MismatchedSetException(null,input);
            	                recover(mse);    throw mse;
            	            }


            	            }
            	            break;
            	        case 2 :
            	            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:354:82: ECHAR
            	            {
            	            mECHAR(); 

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

            match("\'\'\'"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STRING_LITERAL_LONG1

    // $ANTLR start STRING_LITERAL_LONG2
    public final void mSTRING_LITERAL_LONG2() throws RecognitionException {
        try {
            int _type = STRING_LITERAL_LONG2;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:358:5: ( '\"\"\"' ( options {greedy=false; } : ( '\"' | '\"\"' )? (~ ( '\"' | '\\\\' ) | ECHAR ) )* '\"\"\"' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:358:9: '\"\"\"' ( options {greedy=false; } : ( '\"' | '\"\"' )? (~ ( '\"' | '\\\\' ) | ECHAR ) )* '\"\"\"'
            {
            match("\"\"\""); 

            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:358:15: ( options {greedy=false; } : ( '\"' | '\"\"' )? (~ ( '\"' | '\\\\' ) | ECHAR ) )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( (LA29_0=='\"') ) {
                    int LA29_1 = input.LA(2);

                    if ( (LA29_1=='\"') ) {
                        int LA29_3 = input.LA(3);

                        if ( (LA29_3=='\"') ) {
                            alt29=2;
                        }
                        else if ( ((LA29_3>='\u0000' && LA29_3<='!')||(LA29_3>='#' && LA29_3<='\uFFFE')) ) {
                            alt29=1;
                        }


                    }
                    else if ( ((LA29_1>='\u0000' && LA29_1<='!')||(LA29_1>='#' && LA29_1<='\uFFFE')) ) {
                        alt29=1;
                    }


                }
                else if ( ((LA29_0>='\u0000' && LA29_0<='!')||(LA29_0>='#' && LA29_0<='\uFFFE')) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:358:43: ( '\"' | '\"\"' )? (~ ( '\"' | '\\\\' ) | ECHAR )
            	    {
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:358:43: ( '\"' | '\"\"' )?
            	    int alt27=3;
            	    int LA27_0 = input.LA(1);

            	    if ( (LA27_0=='\"') ) {
            	        int LA27_1 = input.LA(2);

            	        if ( (LA27_1=='\"') ) {
            	            alt27=2;
            	        }
            	        else if ( ((LA27_1>='\u0000' && LA27_1<='!')||(LA27_1>='#' && LA27_1<='\uFFFE')) ) {
            	            alt27=1;
            	        }
            	    }
            	    switch (alt27) {
            	        case 1 :
            	            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:358:45: '\"'
            	            {
            	            match('\"'); 

            	            }
            	            break;
            	        case 2 :
            	            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:358:51: '\"\"'
            	            {
            	            match("\"\""); 


            	            }
            	            break;

            	    }

            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:358:59: (~ ( '\"' | '\\\\' ) | ECHAR )
            	    int alt28=2;
            	    int LA28_0 = input.LA(1);

            	    if ( ((LA28_0>='\u0000' && LA28_0<='!')||(LA28_0>='#' && LA28_0<='[')||(LA28_0>=']' && LA28_0<='\uFFFE')) ) {
            	        alt28=1;
            	    }
            	    else if ( (LA28_0=='\\') ) {
            	        alt28=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("358:59: (~ ( '\"' | '\\\\' ) | ECHAR )", 28, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt28) {
            	        case 1 :
            	            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:358:61: ~ ( '\"' | '\\\\' )
            	            {
            	            if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
            	                input.consume();

            	            }
            	            else {
            	                MismatchedSetException mse =
            	                    new MismatchedSetException(null,input);
            	                recover(mse);    throw mse;
            	            }


            	            }
            	            break;
            	        case 2 :
            	            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:358:75: ECHAR
            	            {
            	            mECHAR(); 

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);

            match("\"\"\""); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STRING_LITERAL_LONG2

    // $ANTLR start ECHAR
    public final void mECHAR() throws RecognitionException {
        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:363:5: ( '\\\\' ( 't' | 'b' | 'n' | 'r' | 'f' | '\\\\' | '\"' | '\\'' ) )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:363:7: '\\\\' ( 't' | 'b' | 'n' | 'r' | 'f' | '\\\\' | '\"' | '\\'' )
            {
            match('\\'); 
            if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

        }
        finally {
        }
    }
    // $ANTLR end ECHAR

    // $ANTLR start PN_CHARS_U
    public final void mPN_CHARS_U() throws RecognitionException {
        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:368:5: ( PN_CHARS_BASE | '_' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u00C0' && input.LA(1)<='\u00D6')||(input.LA(1)>='\u00D8' && input.LA(1)<='\u00F6')||(input.LA(1)>='\u00F8' && input.LA(1)<='\u02FF')||(input.LA(1)>='\u0370' && input.LA(1)<='\u037D')||(input.LA(1)>='\u037F' && input.LA(1)<='\u1FFF')||(input.LA(1)>='\u200C' && input.LA(1)<='\u200D')||(input.LA(1)>='\u2070' && input.LA(1)<='\u218F')||(input.LA(1)>='\u2C00' && input.LA(1)<='\u2FEF')||(input.LA(1)>='\u3001' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFDCF')||(input.LA(1)>='\uFDF0' && input.LA(1)<='\uFFFD') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

        }
        finally {
        }
    }
    // $ANTLR end PN_CHARS_U

    // $ANTLR start VARNAME
    public final void mVARNAME() throws RecognitionException {
        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:373:5: ( ( PN_CHARS_U | DIGIT ) ( PN_CHARS_U | DIGIT | '\\u00B7' | '\\u0300' .. '\\u036F' | '\\u203F' .. '\\u2040' )* )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:373:7: ( PN_CHARS_U | DIGIT ) ( PN_CHARS_U | DIGIT | '\\u00B7' | '\\u0300' .. '\\u036F' | '\\u203F' .. '\\u2040' )*
            {
            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u00C0' && input.LA(1)<='\u00D6')||(input.LA(1)>='\u00D8' && input.LA(1)<='\u00F6')||(input.LA(1)>='\u00F8' && input.LA(1)<='\u02FF')||(input.LA(1)>='\u0370' && input.LA(1)<='\u037D')||(input.LA(1)>='\u037F' && input.LA(1)<='\u1FFF')||(input.LA(1)>='\u200C' && input.LA(1)<='\u200D')||(input.LA(1)>='\u2070' && input.LA(1)<='\u218F')||(input.LA(1)>='\u2C00' && input.LA(1)<='\u2FEF')||(input.LA(1)>='\u3001' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFDCF')||(input.LA(1)>='\uFDF0' && input.LA(1)<='\uFFFD') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:373:30: ( PN_CHARS_U | DIGIT | '\\u00B7' | '\\u0300' .. '\\u036F' | '\\u203F' .. '\\u2040' )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( ((LA30_0>='0' && LA30_0<='9')||(LA30_0>='A' && LA30_0<='Z')||LA30_0=='_'||(LA30_0>='a' && LA30_0<='z')||LA30_0=='\u00B7'||(LA30_0>='\u00C0' && LA30_0<='\u00D6')||(LA30_0>='\u00D8' && LA30_0<='\u00F6')||(LA30_0>='\u00F8' && LA30_0<='\u037D')||(LA30_0>='\u037F' && LA30_0<='\u1FFF')||(LA30_0>='\u200C' && LA30_0<='\u200D')||(LA30_0>='\u203F' && LA30_0<='\u2040')||(LA30_0>='\u2070' && LA30_0<='\u218F')||(LA30_0>='\u2C00' && LA30_0<='\u2FEF')||(LA30_0>='\u3001' && LA30_0<='\uD7FF')||(LA30_0>='\uF900' && LA30_0<='\uFDCF')||(LA30_0>='\uFDF0' && LA30_0<='\uFFFD')) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||input.LA(1)=='\u00B7'||(input.LA(1)>='\u00C0' && input.LA(1)<='\u00D6')||(input.LA(1)>='\u00D8' && input.LA(1)<='\u00F6')||(input.LA(1)>='\u00F8' && input.LA(1)<='\u037D')||(input.LA(1)>='\u037F' && input.LA(1)<='\u1FFF')||(input.LA(1)>='\u200C' && input.LA(1)<='\u200D')||(input.LA(1)>='\u203F' && input.LA(1)<='\u2040')||(input.LA(1)>='\u2070' && input.LA(1)<='\u218F')||(input.LA(1)>='\u2C00' && input.LA(1)<='\u2FEF')||(input.LA(1)>='\u3001' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFDCF')||(input.LA(1)>='\uFDF0' && input.LA(1)<='\uFFFD') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end VARNAME

    // $ANTLR start PN_CHARS
    public final void mPN_CHARS() throws RecognitionException {
        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:378:5: ( PN_CHARS_U | MINUS | DIGIT | '\\u00B7' | '\\u0300' .. '\\u036F' | '\\u203F' .. '\\u2040' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:
            {
            if ( input.LA(1)=='-'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||input.LA(1)=='\u00B7'||(input.LA(1)>='\u00C0' && input.LA(1)<='\u00D6')||(input.LA(1)>='\u00D8' && input.LA(1)<='\u00F6')||(input.LA(1)>='\u00F8' && input.LA(1)<='\u037D')||(input.LA(1)>='\u037F' && input.LA(1)<='\u1FFF')||(input.LA(1)>='\u200C' && input.LA(1)<='\u200D')||(input.LA(1)>='\u203F' && input.LA(1)<='\u2040')||(input.LA(1)>='\u2070' && input.LA(1)<='\u218F')||(input.LA(1)>='\u2C00' && input.LA(1)<='\u2FEF')||(input.LA(1)>='\u3001' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFDCF')||(input.LA(1)>='\uFDF0' && input.LA(1)<='\uFFFD') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

        }
        finally {
        }
    }
    // $ANTLR end PN_CHARS

    // $ANTLR start PN_PREFIX
    public final void mPN_PREFIX() throws RecognitionException {
        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:388:5: ( PN_CHARS_BASE ( ( PN_CHARS | DOT )* PN_CHARS )? )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:388:7: PN_CHARS_BASE ( ( PN_CHARS | DOT )* PN_CHARS )?
            {
            mPN_CHARS_BASE(); 
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:388:21: ( ( PN_CHARS | DOT )* PN_CHARS )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( ((LA32_0>='-' && LA32_0<='.')||(LA32_0>='0' && LA32_0<='9')||(LA32_0>='A' && LA32_0<='Z')||LA32_0=='_'||(LA32_0>='a' && LA32_0<='z')||LA32_0=='\u00B7'||(LA32_0>='\u00C0' && LA32_0<='\u00D6')||(LA32_0>='\u00D8' && LA32_0<='\u00F6')||(LA32_0>='\u00F8' && LA32_0<='\u037D')||(LA32_0>='\u037F' && LA32_0<='\u1FFF')||(LA32_0>='\u200C' && LA32_0<='\u200D')||(LA32_0>='\u203F' && LA32_0<='\u2040')||(LA32_0>='\u2070' && LA32_0<='\u218F')||(LA32_0>='\u2C00' && LA32_0<='\u2FEF')||(LA32_0>='\u3001' && LA32_0<='\uD7FF')||(LA32_0>='\uF900' && LA32_0<='\uFDCF')||(LA32_0>='\uFDF0' && LA32_0<='\uFFFD')) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:388:22: ( PN_CHARS | DOT )* PN_CHARS
                    {
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:388:22: ( PN_CHARS | DOT )*
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( (LA31_0=='-'||(LA31_0>='0' && LA31_0<='9')||(LA31_0>='A' && LA31_0<='Z')||LA31_0=='_'||(LA31_0>='a' && LA31_0<='z')||LA31_0=='\u00B7'||(LA31_0>='\u00C0' && LA31_0<='\u00D6')||(LA31_0>='\u00D8' && LA31_0<='\u00F6')||(LA31_0>='\u00F8' && LA31_0<='\u037D')||(LA31_0>='\u037F' && LA31_0<='\u1FFF')||(LA31_0>='\u200C' && LA31_0<='\u200D')||(LA31_0>='\u203F' && LA31_0<='\u2040')||(LA31_0>='\u2070' && LA31_0<='\u218F')||(LA31_0>='\u2C00' && LA31_0<='\u2FEF')||(LA31_0>='\u3001' && LA31_0<='\uD7FF')||(LA31_0>='\uF900' && LA31_0<='\uFDCF')||(LA31_0>='\uFDF0' && LA31_0<='\uFFFD')) ) {
                            int LA31_1 = input.LA(2);

                            if ( ((LA31_1>='-' && LA31_1<='.')||(LA31_1>='0' && LA31_1<='9')||(LA31_1>='A' && LA31_1<='Z')||LA31_1=='_'||(LA31_1>='a' && LA31_1<='z')||LA31_1=='\u00B7'||(LA31_1>='\u00C0' && LA31_1<='\u00D6')||(LA31_1>='\u00D8' && LA31_1<='\u00F6')||(LA31_1>='\u00F8' && LA31_1<='\u037D')||(LA31_1>='\u037F' && LA31_1<='\u1FFF')||(LA31_1>='\u200C' && LA31_1<='\u200D')||(LA31_1>='\u203F' && LA31_1<='\u2040')||(LA31_1>='\u2070' && LA31_1<='\u218F')||(LA31_1>='\u2C00' && LA31_1<='\u2FEF')||(LA31_1>='\u3001' && LA31_1<='\uD7FF')||(LA31_1>='\uF900' && LA31_1<='\uFDCF')||(LA31_1>='\uFDF0' && LA31_1<='\uFFFD')) ) {
                                alt31=1;
                            }


                        }
                        else if ( (LA31_0=='.') ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:
                    	    {
                    	    if ( (input.LA(1)>='-' && input.LA(1)<='.')||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||input.LA(1)=='\u00B7'||(input.LA(1)>='\u00C0' && input.LA(1)<='\u00D6')||(input.LA(1)>='\u00D8' && input.LA(1)<='\u00F6')||(input.LA(1)>='\u00F8' && input.LA(1)<='\u037D')||(input.LA(1)>='\u037F' && input.LA(1)<='\u1FFF')||(input.LA(1)>='\u200C' && input.LA(1)<='\u200D')||(input.LA(1)>='\u203F' && input.LA(1)<='\u2040')||(input.LA(1)>='\u2070' && input.LA(1)<='\u218F')||(input.LA(1)>='\u2C00' && input.LA(1)<='\u2FEF')||(input.LA(1)>='\u3001' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFDCF')||(input.LA(1)>='\uFDF0' && input.LA(1)<='\uFFFD') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop31;
                        }
                    } while (true);

                    mPN_CHARS(); 

                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end PN_PREFIX

    // $ANTLR start PN_LOCAL
    public final void mPN_LOCAL() throws RecognitionException {
        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:393:5: ( ( PN_CHARS_U | DIGIT ) ( ( PN_CHARS )* PN_CHARS )? )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:393:7: ( PN_CHARS_U | DIGIT ) ( ( PN_CHARS )* PN_CHARS )?
            {
            if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u00C0' && input.LA(1)<='\u00D6')||(input.LA(1)>='\u00D8' && input.LA(1)<='\u00F6')||(input.LA(1)>='\u00F8' && input.LA(1)<='\u02FF')||(input.LA(1)>='\u0370' && input.LA(1)<='\u037D')||(input.LA(1)>='\u037F' && input.LA(1)<='\u1FFF')||(input.LA(1)>='\u200C' && input.LA(1)<='\u200D')||(input.LA(1)>='\u2070' && input.LA(1)<='\u218F')||(input.LA(1)>='\u2C00' && input.LA(1)<='\u2FEF')||(input.LA(1)>='\u3001' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFDCF')||(input.LA(1)>='\uFDF0' && input.LA(1)<='\uFFFD') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:393:30: ( ( PN_CHARS )* PN_CHARS )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0=='-'||(LA34_0>='0' && LA34_0<='9')||(LA34_0>='A' && LA34_0<='Z')||LA34_0=='_'||(LA34_0>='a' && LA34_0<='z')||LA34_0=='\u00B7'||(LA34_0>='\u00C0' && LA34_0<='\u00D6')||(LA34_0>='\u00D8' && LA34_0<='\u00F6')||(LA34_0>='\u00F8' && LA34_0<='\u037D')||(LA34_0>='\u037F' && LA34_0<='\u1FFF')||(LA34_0>='\u200C' && LA34_0<='\u200D')||(LA34_0>='\u203F' && LA34_0<='\u2040')||(LA34_0>='\u2070' && LA34_0<='\u218F')||(LA34_0>='\u2C00' && LA34_0<='\u2FEF')||(LA34_0>='\u3001' && LA34_0<='\uD7FF')||(LA34_0>='\uF900' && LA34_0<='\uFDCF')||(LA34_0>='\uFDF0' && LA34_0<='\uFFFD')) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:393:31: ( PN_CHARS )* PN_CHARS
                    {
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:393:31: ( PN_CHARS )*
                    loop33:
                    do {
                        int alt33=2;
                        int LA33_0 = input.LA(1);

                        if ( (LA33_0=='-'||(LA33_0>='0' && LA33_0<='9')||(LA33_0>='A' && LA33_0<='Z')||LA33_0=='_'||(LA33_0>='a' && LA33_0<='z')||LA33_0=='\u00B7'||(LA33_0>='\u00C0' && LA33_0<='\u00D6')||(LA33_0>='\u00D8' && LA33_0<='\u00F6')||(LA33_0>='\u00F8' && LA33_0<='\u037D')||(LA33_0>='\u037F' && LA33_0<='\u1FFF')||(LA33_0>='\u200C' && LA33_0<='\u200D')||(LA33_0>='\u203F' && LA33_0<='\u2040')||(LA33_0>='\u2070' && LA33_0<='\u218F')||(LA33_0>='\u2C00' && LA33_0<='\u2FEF')||(LA33_0>='\u3001' && LA33_0<='\uD7FF')||(LA33_0>='\uF900' && LA33_0<='\uFDCF')||(LA33_0>='\uFDF0' && LA33_0<='\uFFFD')) ) {
                            int LA33_1 = input.LA(2);

                            if ( (LA33_1=='-'||(LA33_1>='0' && LA33_1<='9')||(LA33_1>='A' && LA33_1<='Z')||LA33_1=='_'||(LA33_1>='a' && LA33_1<='z')||LA33_1=='\u00B7'||(LA33_1>='\u00C0' && LA33_1<='\u00D6')||(LA33_1>='\u00D8' && LA33_1<='\u00F6')||(LA33_1>='\u00F8' && LA33_1<='\u037D')||(LA33_1>='\u037F' && LA33_1<='\u1FFF')||(LA33_1>='\u200C' && LA33_1<='\u200D')||(LA33_1>='\u203F' && LA33_1<='\u2040')||(LA33_1>='\u2070' && LA33_1<='\u218F')||(LA33_1>='\u2C00' && LA33_1<='\u2FEF')||(LA33_1>='\u3001' && LA33_1<='\uD7FF')||(LA33_1>='\uF900' && LA33_1<='\uFDCF')||(LA33_1>='\uFDF0' && LA33_1<='\uFFFD')) ) {
                                alt33=1;
                            }


                        }


                        switch (alt33) {
                    	case 1 :
                    	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:393:32: PN_CHARS
                    	    {
                    	    mPN_CHARS(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop33;
                        }
                    } while (true);

                    mPN_CHARS(); 

                    }
                    break;

            }


            }

        }
        finally {
        }
    }
    // $ANTLR end PN_LOCAL

    // $ANTLR start PN_CHARS_BASE
    public final void mPN_CHARS_BASE() throws RecognitionException {
        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:398:5: ( 'A' .. 'Z' | 'a' .. 'z' | '\\u00C0' .. '\\u00D6' | '\\u00D8' .. '\\u00F6' | '\\u00F8' .. '\\u02FF' | '\\u0370' .. '\\u037D' | '\\u037F' .. '\\u1FFF' | '\\u200C' .. '\\u200D' | '\\u2070' .. '\\u218F' | '\\u2C00' .. '\\u2FEF' | '\\u3001' .. '\\uD7FF' | '\\uF900' .. '\\uFDCF' | '\\uFDF0' .. '\\uFFFD' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u00C0' && input.LA(1)<='\u00D6')||(input.LA(1)>='\u00D8' && input.LA(1)<='\u00F6')||(input.LA(1)>='\u00F8' && input.LA(1)<='\u02FF')||(input.LA(1)>='\u0370' && input.LA(1)<='\u037D')||(input.LA(1)>='\u037F' && input.LA(1)<='\u1FFF')||(input.LA(1)>='\u200C' && input.LA(1)<='\u200D')||(input.LA(1)>='\u2070' && input.LA(1)<='\u218F')||(input.LA(1)>='\u2C00' && input.LA(1)<='\u2FEF')||(input.LA(1)>='\u3001' && input.LA(1)<='\uD7FF')||(input.LA(1)>='\uF900' && input.LA(1)<='\uFDCF')||(input.LA(1)>='\uFDF0' && input.LA(1)<='\uFFFD') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

        }
        finally {
        }
    }
    // $ANTLR end PN_CHARS_BASE

    // $ANTLR start DIGIT
    public final void mDIGIT() throws RecognitionException {
        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:415:5: ( '0' .. '9' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:415:7: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end DIGIT

    // $ANTLR start COMMENT
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:419:5: ( '//' ( options {greedy=false; } : . )* EOL )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:419:7: '//' ( options {greedy=false; } : . )* EOL
            {
            match("//"); 

            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:419:12: ( options {greedy=false; } : . )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0=='\n'||LA35_0=='\r') ) {
                    alt35=2;
                }
                else if ( ((LA35_0>='\u0000' && LA35_0<='\t')||(LA35_0>='\u000B' && LA35_0<='\f')||(LA35_0>='\u000E' && LA35_0<='\uFFFE')) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:419:39: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);

            mEOL(); 
             channel=HIDDEN; 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COMMENT

    // $ANTLR start EOL
    public final void mEOL() throws RecognitionException {
        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:424:5: ( '\\n' | '\\r' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:
            {
            if ( input.LA(1)=='\n'||input.LA(1)=='\r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

        }
        finally {
        }
    }
    // $ANTLR end EOL

    // $ANTLR start REFERENCE
    public final void mREFERENCE() throws RecognitionException {
        try {
            int _type = REFERENCE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:428:5: ( '^^' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:428:7: '^^'
            {
            match("^^"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end REFERENCE

    // $ANTLR start EXCLAMATION
    public final void mEXCLAMATION() throws RecognitionException {
        try {
            int _type = EXCLAMATION;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:431:2: ( '!' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:431:4: '!'
            {
            match('!'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EXCLAMATION

    // $ANTLR start QUESTION
    public final void mQUESTION() throws RecognitionException {
        try {
            int _type = QUESTION;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:434:2: ( '?' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:434:4: '?'
            {
            match('?'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end QUESTION

    // $ANTLR start DOT
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:436:5: ( '.' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:436:7: '.'
            {
            match('.'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DOT

    // $ANTLR start COMMA
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:439:7: ( ',' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:439:9: ','
            {
            match(','); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COMMA

    // $ANTLR start SEMICOLON
    public final void mSEMICOLON() throws RecognitionException {
        try {
            int _type = SEMICOLON;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:446:2: ( ';' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:446:4: ';'
            {
            match(';'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SEMICOLON

    // $ANTLR start AMPERSAND
    public final void mAMPERSAND() throws RecognitionException {
        try {
            int _type = AMPERSAND;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:450:2: ( '&' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:450:4: '&'
            {
            match('&'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AMPERSAND

    // $ANTLR start BAR
    public final void mBAR() throws RecognitionException {
        try {
            int _type = BAR;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:452:5: ( '|' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:452:7: '|'
            {
            match('|'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end BAR

    // $ANTLR start LANGLE
    public final void mLANGLE() throws RecognitionException {
        try {
            int _type = LANGLE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:455:8: ( '<' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:455:10: '<'
            {
            match('<'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LANGLE

    // $ANTLR start RANGLE
    public final void mRANGLE() throws RecognitionException {
        try {
            int _type = RANGLE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:458:9: ( '>' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:458:11: '>'
            {
            match('>'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RANGLE

    // $ANTLR start PERCENT
    public final void mPERCENT() throws RecognitionException {
        try {
            int _type = PERCENT;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:461:9: ( '%' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:461:11: '%'
            {
            match('%'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PERCENT

    // $ANTLR start PLUS
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:464:6: ( '+' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:464:8: '+'
            {
            match('+'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PLUS

    // $ANTLR start MINUS
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:467:7: ( '-' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:467:9: '-'
            {
            match('-'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MINUS

    // $ANTLR start STAR
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:470:6: ( '*' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:470:8: '*'
            {
            match('*'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STAR

    // $ANTLR start TILDE
    public final void mTILDE() throws RecognitionException {
        try {
            int _type = TILDE;
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:473:8: ( '~' )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:473:10: '~'
            {
            match('~'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end TILDE

    public void mTokens() throws RecognitionException {
        // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:8: ( WS | AT_IMPORT | AT_PREFIX | AT_BASE | AT_DELAY | INHERITANCE | SIMILARITY | INSTANCE | PROPERTY | INSTANCE_PROPERTY | IMPLICATION | IMPLICATION_PRED | IMPLICATION_RETRO | IMPLICATION_CONC | EQUIVALENCE | EQUIVALENCE_PRED | EQUIVALENCE_CONC | NOT | PAST | PRESENT | FUTURE | CONJ | DISJ | OPEN_BRACE | CLOSE_BRACE | LPAREN | RPAREN | LBRACKET | RBRACKET | PNAME_NS | PNAME_LN | TRUE | FALSE | IRI_REF | LANGTAG | QUERY_VAR | STM_VAR | INTEGER | DECIMAL | DOUBLE | INTEGER_POSITIVE | DECIMAL_POSITIVE | DOUBLE_POSITIVE | INTEGER_NEGATIVE | DECIMAL_NEGATIVE | DOUBLE_NEGATIVE | STRING_LITERAL1 | STRING_LITERAL2 | STRING_LITERAL_LONG1 | STRING_LITERAL_LONG2 | COMMENT | REFERENCE | EXCLAMATION | QUESTION | DOT | COMMA | SEMICOLON | AMPERSAND | BAR | LANGLE | RANGLE | PERCENT | PLUS | MINUS | STAR | TILDE )
        int alt36=66;
        alt36 = dfa36.predict(input);
        switch (alt36) {
            case 1 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:10: WS
                {
                mWS(); 

                }
                break;
            case 2 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:13: AT_IMPORT
                {
                mAT_IMPORT(); 

                }
                break;
            case 3 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:23: AT_PREFIX
                {
                mAT_PREFIX(); 

                }
                break;
            case 4 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:33: AT_BASE
                {
                mAT_BASE(); 

                }
                break;
            case 5 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:41: AT_DELAY
                {
                mAT_DELAY(); 

                }
                break;
            case 6 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:50: INHERITANCE
                {
                mINHERITANCE(); 

                }
                break;
            case 7 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:62: SIMILARITY
                {
                mSIMILARITY(); 

                }
                break;
            case 8 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:73: INSTANCE
                {
                mINSTANCE(); 

                }
                break;
            case 9 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:82: PROPERTY
                {
                mPROPERTY(); 

                }
                break;
            case 10 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:91: INSTANCE_PROPERTY
                {
                mINSTANCE_PROPERTY(); 

                }
                break;
            case 11 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:109: IMPLICATION
                {
                mIMPLICATION(); 

                }
                break;
            case 12 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:121: IMPLICATION_PRED
                {
                mIMPLICATION_PRED(); 

                }
                break;
            case 13 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:138: IMPLICATION_RETRO
                {
                mIMPLICATION_RETRO(); 

                }
                break;
            case 14 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:156: IMPLICATION_CONC
                {
                mIMPLICATION_CONC(); 

                }
                break;
            case 15 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:173: EQUIVALENCE
                {
                mEQUIVALENCE(); 

                }
                break;
            case 16 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:185: EQUIVALENCE_PRED
                {
                mEQUIVALENCE_PRED(); 

                }
                break;
            case 17 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:202: EQUIVALENCE_CONC
                {
                mEQUIVALENCE_CONC(); 

                }
                break;
            case 18 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:219: NOT
                {
                mNOT(); 

                }
                break;
            case 19 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:223: PAST
                {
                mPAST(); 

                }
                break;
            case 20 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:228: PRESENT
                {
                mPRESENT(); 

                }
                break;
            case 21 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:236: FUTURE
                {
                mFUTURE(); 

                }
                break;
            case 22 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:243: CONJ
                {
                mCONJ(); 

                }
                break;
            case 23 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:248: DISJ
                {
                mDISJ(); 

                }
                break;
            case 24 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:253: OPEN_BRACE
                {
                mOPEN_BRACE(); 

                }
                break;
            case 25 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:264: CLOSE_BRACE
                {
                mCLOSE_BRACE(); 

                }
                break;
            case 26 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:276: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 27 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:283: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 28 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:290: LBRACKET
                {
                mLBRACKET(); 

                }
                break;
            case 29 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:299: RBRACKET
                {
                mRBRACKET(); 

                }
                break;
            case 30 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:308: PNAME_NS
                {
                mPNAME_NS(); 

                }
                break;
            case 31 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:317: PNAME_LN
                {
                mPNAME_LN(); 

                }
                break;
            case 32 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:326: TRUE
                {
                mTRUE(); 

                }
                break;
            case 33 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:331: FALSE
                {
                mFALSE(); 

                }
                break;
            case 34 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:337: IRI_REF
                {
                mIRI_REF(); 

                }
                break;
            case 35 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:345: LANGTAG
                {
                mLANGTAG(); 

                }
                break;
            case 36 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:353: QUERY_VAR
                {
                mQUERY_VAR(); 

                }
                break;
            case 37 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:363: STM_VAR
                {
                mSTM_VAR(); 

                }
                break;
            case 38 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:371: INTEGER
                {
                mINTEGER(); 

                }
                break;
            case 39 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:379: DECIMAL
                {
                mDECIMAL(); 

                }
                break;
            case 40 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:387: DOUBLE
                {
                mDOUBLE(); 

                }
                break;
            case 41 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:394: INTEGER_POSITIVE
                {
                mINTEGER_POSITIVE(); 

                }
                break;
            case 42 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:411: DECIMAL_POSITIVE
                {
                mDECIMAL_POSITIVE(); 

                }
                break;
            case 43 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:428: DOUBLE_POSITIVE
                {
                mDOUBLE_POSITIVE(); 

                }
                break;
            case 44 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:444: INTEGER_NEGATIVE
                {
                mINTEGER_NEGATIVE(); 

                }
                break;
            case 45 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:461: DECIMAL_NEGATIVE
                {
                mDECIMAL_NEGATIVE(); 

                }
                break;
            case 46 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:478: DOUBLE_NEGATIVE
                {
                mDOUBLE_NEGATIVE(); 

                }
                break;
            case 47 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:494: STRING_LITERAL1
                {
                mSTRING_LITERAL1(); 

                }
                break;
            case 48 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:510: STRING_LITERAL2
                {
                mSTRING_LITERAL2(); 

                }
                break;
            case 49 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:526: STRING_LITERAL_LONG1
                {
                mSTRING_LITERAL_LONG1(); 

                }
                break;
            case 50 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:547: STRING_LITERAL_LONG2
                {
                mSTRING_LITERAL_LONG2(); 

                }
                break;
            case 51 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:568: COMMENT
                {
                mCOMMENT(); 

                }
                break;
            case 52 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:576: REFERENCE
                {
                mREFERENCE(); 

                }
                break;
            case 53 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:586: EXCLAMATION
                {
                mEXCLAMATION(); 

                }
                break;
            case 54 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:598: QUESTION
                {
                mQUESTION(); 

                }
                break;
            case 55 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:607: DOT
                {
                mDOT(); 

                }
                break;
            case 56 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:611: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 57 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:617: SEMICOLON
                {
                mSEMICOLON(); 

                }
                break;
            case 58 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:627: AMPERSAND
                {
                mAMPERSAND(); 

                }
                break;
            case 59 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:637: BAR
                {
                mBAR(); 

                }
                break;
            case 60 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:641: LANGLE
                {
                mLANGLE(); 

                }
                break;
            case 61 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:648: RANGLE
                {
                mRANGLE(); 

                }
                break;
            case 62 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:655: PERCENT
                {
                mPERCENT(); 

                }
                break;
            case 63 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:663: PLUS
                {
                mPLUS(); 

                }
                break;
            case 64 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:668: MINUS
                {
                mMINUS(); 

                }
                break;
            case 65 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:674: STAR
                {
                mSTAR(); 

                }
                break;
            case 66 :
                // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:1:679: TILDE
                {
                mTILDE(); 

                }
                break;

        }

    }


    protected DFA19 dfa19 = new DFA19(this);
    protected DFA36 dfa36 = new DFA36(this);
    static final String DFA19_eotS =
        "\5\uffff";
    static final String DFA19_eofS =
        "\5\uffff";
    static final String DFA19_minS =
        "\2\56\3\uffff";
    static final String DFA19_maxS =
        "\1\71\1\145\3\uffff";
    static final String DFA19_acceptS =
        "\2\uffff\1\2\1\1\1\3";
    static final String DFA19_specialS =
        "\5\uffff}>";
    static final String[] DFA19_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\12\1\13\uffff\1\4\37\uffff\1\4",
            "",
            "",
            ""
    };

    static final short[] DFA19_eot = DFA.unpackEncodedString(DFA19_eotS);
    static final short[] DFA19_eof = DFA.unpackEncodedString(DFA19_eofS);
    static final char[] DFA19_min = DFA.unpackEncodedStringToUnsignedChars(DFA19_minS);
    static final char[] DFA19_max = DFA.unpackEncodedStringToUnsignedChars(DFA19_maxS);
    static final short[] DFA19_accept = DFA.unpackEncodedString(DFA19_acceptS);
    static final short[] DFA19_special = DFA.unpackEncodedString(DFA19_specialS);
    static final short[][] DFA19_transition;

    static {
        int numStates = DFA19_transitionS.length;
        DFA19_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA19_transition[i] = DFA.unpackEncodedString(DFA19_transitionS[i]);
        }
    }

    class DFA19 extends DFA {

        public DFA19(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 19;
            this.eot = DFA19_eot;
            this.eof = DFA19_eof;
            this.min = DFA19_min;
            this.max = DFA19_max;
            this.accept = DFA19_accept;
            this.special = DFA19_special;
            this.transition = DFA19_transition;
        }
        public String getDescription() {
            return "310:1: DOUBLE : ( ( DIGIT )+ DOT ( DIGIT )* EXPONENT | DOT ( DIGIT )+ EXPONENT | ( DIGIT )+ EXPONENT );";
        }
    }
    static final String DFA36_eotS =
        "\3\uffff\1\51\1\60\1\63\1\uffff\1\71\1\uffff\1\74\1\uffff\1\100"+
        "\6\uffff\1\104\2\uffff\1\107\1\uffff\1\111\1\114\1\116\11\uffff"+
        "\4\47\3\uffff\1\133\37\uffff\1\146\2\uffff\1\146\1\uffff\1\150\1"+
        "\uffff\1\122\1\uffff\1\124\1\uffff\4\47\3\uffff\1\162\1\uffff\1"+
        "\162\10\uffff\1\146\1\uffff\1\171\1\uffff\1\171\2\uffff\4\47\1\uffff"+
        "\1\162\3\uffff\1\177\2\uffff\1\171\3\47\1\u0084\1\uffff\1\u0085"+
        "\2\47\1\u0088\2\uffff\1\u0089\1\u008a\3\uffff";
    static final String DFA36_eofS =
        "\u008b\uffff";
    static final String DFA36_minS =
        "\1\11\1\uffff\1\101\1\55\1\41\1\55\1\57\1\41\1\uffff\1\76\1\57\1"+
        "\46\5\uffff\1\55\1\60\2\55\1\60\1\uffff\1\56\1\60\1\56\2\0\7\uffff"+
        "\1\155\1\162\1\145\1\141\1\uffff\1\76\1\uffff\1\56\1\60\2\41\1\uffff"+
        "\1\41\2\uffff\1\76\16\uffff\3\55\2\uffff\1\55\3\uffff\1\60\2\uffff"+
        "\1\60\1\uffff\1\56\1\60\1\47\1\uffff\1\42\1\uffff\1\160\1\145\1"+
        "\154\1\163\3\uffff\1\60\1\uffff\1\60\5\uffff\2\55\1\uffff\1\60\1"+
        "\uffff\1\60\1\uffff\1\60\2\uffff\1\157\1\146\1\141\1\145\1\uffff"+
        "\1\60\3\uffff\2\55\1\uffff\1\60\1\162\1\151\1\171\1\55\1\uffff\1"+
        "\55\1\164\1\170\1\55\2\uffff\2\55\3\uffff";
    static final String DFA36_maxS =
        "\1\ufffd\1\uffff\1\ufffd\1\71\1\ufffe\1\55\1\174\1\41\1\uffff\1"+
        "\174\1\76\1\46\5\uffff\5\ufffd\1\uffff\1\145\2\71\2\ufffe\7\uffff"+
        "\1\155\1\162\1\145\1\141\1\uffff\1\133\1\uffff\1\145\1\71\2\ufffe"+
        "\1\uffff\1\ufffe\2\uffff\1\133\16\uffff\3\ufffd\2\uffff\1\ufffd"+
        "\3\uffff\1\145\2\uffff\1\145\1\uffff\1\145\1\71\1\47\1\uffff\1\42"+
        "\1\uffff\1\160\1\145\1\154\1\163\3\uffff\1\145\1\uffff\1\145\5\uffff"+
        "\2\ufffd\1\uffff\1\145\1\uffff\1\145\1\uffff\1\145\2\uffff\1\157"+
        "\1\146\1\141\1\145\1\uffff\1\145\3\uffff\2\ufffd\1\uffff\1\145\1"+
        "\162\1\151\1\171\1\ufffd\1\uffff\1\ufffd\1\164\1\170\1\ufffd\2\uffff"+
        "\2\ufffd\3\uffff";
    static final String DFA36_acceptS =
        "\1\uffff\1\1\6\uffff\1\23\3\uffff\1\30\1\32\1\33\1\34\1\35\5\uffff"+
        "\1\45\5\uffff\1\64\1\70\1\71\1\75\1\76\1\101\1\102\4\uffff\1\43"+
        "\1\uffff\1\100\4\uffff\1\21\1\uffff\1\74\1\42\1\uffff\1\31\1\14"+
        "\1\13\1\15\1\16\1\22\1\65\1\24\1\27\1\73\1\25\1\63\1\26\1\72\3\uffff"+
        "\1\36\1\37\1\uffff\1\66\1\44\1\46\1\uffff\1\50\1\67\1\uffff\1\77"+
        "\3\uffff\1\57\1\uffff\1\60\4\uffff\1\11\1\6\1\54\1\uffff\1\56\1"+
        "\uffff\1\17\1\7\1\20\1\12\1\10\2\uffff\1\47\1\uffff\1\51\1\uffff"+
        "\1\53\1\uffff\1\61\1\62\4\uffff\1\55\1\uffff\1\17\1\7\1\20\2\uffff"+
        "\1\52\5\uffff\1\40\4\uffff\1\4\1\41\2\uffff\1\5\1\2\1\3";
    static final String DFA36_specialS =
        "\u008b\uffff}>";
    static final String[] DFA36_transitionS = {
            "\2\1\2\uffff\1\1\22\uffff\1\1\1\7\1\33\1\26\1\uffff\1\40\1\13"+
            "\1\32\1\15\1\16\1\41\1\31\1\35\1\3\1\30\1\12\12\27\1\22\1\36"+
            "\1\4\1\6\1\37\1\25\1\2\5\24\1\23\15\24\1\21\6\24\1\17\1\10\1"+
            "\20\1\34\2\uffff\5\24\1\23\15\24\1\21\6\24\1\14\1\11\1\5\1\42"+
            "\101\uffff\27\24\1\uffff\37\24\1\uffff\u0208\24\160\uffff\16"+
            "\24\1\uffff\u1c81\24\14\uffff\2\24\142\uffff\u0120\24\u0a70"+
            "\uffff\u03f0\24\21\uffff\ua7ff\24\u2100\uffff\u04d0\24\40\uffff"+
            "\u020e\24",
            "",
            "\32\47\6\uffff\1\47\1\46\1\47\1\45\4\47\1\43\6\47\1\44\12\47"+
            "\105\uffff\27\47\1\uffff\37\47\1\uffff\u0208\47\160\uffff\16"+
            "\47\1\uffff\u1c81\47\14\uffff\2\47\142\uffff\u0120\47\u0a70"+
            "\uffff\u03f0\47\21\uffff\ua7ff\47\u2100\uffff\u04d0\47\40\uffff"+
            "\u020e\47",
            "\1\50\1\53\1\uffff\12\52",
            "\1\61\1\uffff\12\61\1\55\1\61\1\57\14\61\1\uffff\1\54\36\61"+
            "\1\uffff\1\61\1\uffff\1\61\1\uffff\32\61\1\uffff\1\56\1\uffff"+
            "\uff81\61",
            "\1\62",
            "\1\64\15\uffff\1\65\36\uffff\1\66\37\uffff\1\67",
            "\1\70",
            "",
            "\1\72\75\uffff\1\73",
            "\1\76\16\uffff\1\75",
            "\1\77",
            "",
            "",
            "",
            "",
            "",
            "\1\102\1\103\1\uffff\12\102\1\22\6\uffff\21\102\1\101\10\102"+
            "\4\uffff\1\102\1\uffff\21\102\1\101\10\102\74\uffff\1\102\10"+
            "\uffff\27\102\1\uffff\37\102\1\uffff\u0286\102\1\uffff\u1c81"+
            "\102\14\uffff\2\102\61\uffff\2\102\57\uffff\u0120\102\u0a70"+
            "\uffff\u03f0\102\21\uffff\ua7ff\102\u2100\uffff\u04d0\102\40"+
            "\uffff\u020e\102",
            "\12\105\7\uffff\32\105\4\uffff\1\105\1\uffff\32\105\105\uffff"+
            "\27\105\1\uffff\37\105\1\uffff\u0208\105\160\uffff\16\105\1"+
            "\uffff\u1c81\105\14\uffff\2\105\142\uffff\u0120\105\u0a70\uffff"+
            "\u03f0\105\21\uffff\ua7ff\105\u2100\uffff\u04d0\105\40\uffff"+
            "\u020e\105",
            "\1\102\1\103\1\uffff\12\102\1\22\6\uffff\1\106\31\102\4\uffff"+
            "\1\102\1\uffff\1\106\31\102\74\uffff\1\102\10\uffff\27\102\1"+
            "\uffff\37\102\1\uffff\u0286\102\1\uffff\u1c81\102\14\uffff\2"+
            "\102\61\uffff\2\102\57\uffff\u0120\102\u0a70\uffff\u03f0\102"+
            "\21\uffff\ua7ff\102\u2100\uffff\u04d0\102\40\uffff\u020e\102",
            "\1\102\1\103\1\uffff\12\102\1\22\6\uffff\32\102\4\uffff\1\102"+
            "\1\uffff\32\102\74\uffff\1\102\10\uffff\27\102\1\uffff\37\102"+
            "\1\uffff\u0286\102\1\uffff\u1c81\102\14\uffff\2\102\61\uffff"+
            "\2\102\57\uffff\u0120\102\u0a70\uffff\u03f0\102\21\uffff\ua7ff"+
            "\102\u2100\uffff\u04d0\102\40\uffff\u020e\102",
            "\12\110\7\uffff\32\110\4\uffff\1\110\1\uffff\32\110\105\uffff"+
            "\27\110\1\uffff\37\110\1\uffff\u0208\110\160\uffff\16\110\1"+
            "\uffff\u1c81\110\14\uffff\2\110\142\uffff\u0120\110\u0a70\uffff"+
            "\u03f0\110\21\uffff\ua7ff\110\u2100\uffff\u04d0\110\40\uffff"+
            "\u020e\110",
            "",
            "\1\112\1\uffff\12\27\13\uffff\1\113\37\uffff\1\113",
            "\12\115",
            "\1\120\1\uffff\12\117",
            "\12\122\1\uffff\2\122\1\uffff\31\122\1\121\uffd7\122",
            "\12\124\1\uffff\2\124\1\uffff\24\124\1\123\uffdc\124",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\125",
            "\1\126",
            "\1\127",
            "\1\130",
            "",
            "\1\132\34\uffff\1\131",
            "",
            "\1\134\1\uffff\12\52\13\uffff\1\135\37\uffff\1\135",
            "\12\136",
            "\1\61\1\uffff\31\61\1\uffff\1\61\1\137\35\61\1\uffff\1\61\1"+
            "\uffff\1\61\1\uffff\32\61\3\uffff\uff81\61",
            "\1\61\1\uffff\31\61\1\uffff\1\61\1\140\35\61\1\uffff\1\61\1"+
            "\uffff\1\61\1\uffff\32\61\3\uffff\uff81\61",
            "",
            "\1\61\1\uffff\31\61\1\uffff\1\61\1\141\35\61\1\uffff\1\61\1"+
            "\uffff\1\61\1\uffff\32\61\3\uffff\uff81\61",
            "",
            "",
            "\1\143\34\uffff\1\142",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\102\1\103\1\uffff\12\102\1\22\6\uffff\24\102\1\144\5\102"+
            "\4\uffff\1\102\1\uffff\24\102\1\144\5\102\74\uffff\1\102\10"+
            "\uffff\27\102\1\uffff\37\102\1\uffff\u0286\102\1\uffff\u1c81"+
            "\102\14\uffff\2\102\61\uffff\2\102\57\uffff\u0120\102\u0a70"+
            "\uffff\u03f0\102\21\uffff\ua7ff\102\u2100\uffff\u04d0\102\40"+
            "\uffff\u020e\102",
            "\1\102\1\103\1\uffff\12\102\1\22\6\uffff\32\102\4\uffff\1\102"+
            "\1\uffff\32\102\74\uffff\1\102\10\uffff\27\102\1\uffff\37\102"+
            "\1\uffff\u0286\102\1\uffff\u1c81\102\14\uffff\2\102\61\uffff"+
            "\2\102\57\uffff\u0120\102\u0a70\uffff\u03f0\102\21\uffff\ua7ff"+
            "\102\u2100\uffff\u04d0\102\40\uffff\u020e\102",
            "\1\102\1\103\1\uffff\12\102\7\uffff\32\102\4\uffff\1\102\1\uffff"+
            "\32\102\74\uffff\1\102\10\uffff\27\102\1\uffff\37\102\1\uffff"+
            "\u0286\102\1\uffff\u1c81\102\14\uffff\2\102\61\uffff\2\102\57"+
            "\uffff\u0120\102\u0a70\uffff\u03f0\102\21\uffff\ua7ff\102\u2100"+
            "\uffff\u04d0\102\40\uffff\u020e\102",
            "",
            "",
            "\1\102\1\103\1\uffff\12\102\1\22\6\uffff\13\102\1\145\16\102"+
            "\4\uffff\1\102\1\uffff\13\102\1\145\16\102\74\uffff\1\102\10"+
            "\uffff\27\102\1\uffff\37\102\1\uffff\u0286\102\1\uffff\u1c81"+
            "\102\14\uffff\2\102\61\uffff\2\102\57\uffff\u0120\102\u0a70"+
            "\uffff\u03f0\102\21\uffff\ua7ff\102\u2100\uffff\u04d0\102\40"+
            "\uffff\u020e\102",
            "",
            "",
            "",
            "\12\147\13\uffff\1\113\37\uffff\1\113",
            "",
            "",
            "\12\115\13\uffff\1\113\37\uffff\1\113",
            "",
            "\1\151\1\uffff\12\117\13\uffff\1\152\37\uffff\1\152",
            "\12\153",
            "\1\154",
            "",
            "\1\155",
            "",
            "\1\156",
            "\1\157",
            "\1\160",
            "\1\161",
            "",
            "",
            "",
            "\12\163\13\uffff\1\135\37\uffff\1\135",
            "",
            "\12\136\13\uffff\1\135\37\uffff\1\135",
            "",
            "",
            "",
            "",
            "",
            "\1\102\1\103\1\uffff\12\102\1\22\6\uffff\4\102\1\167\25\102"+
            "\4\uffff\1\102\1\uffff\4\102\1\167\25\102\74\uffff\1\102\10"+
            "\uffff\27\102\1\uffff\37\102\1\uffff\u0286\102\1\uffff\u1c81"+
            "\102\14\uffff\2\102\61\uffff\2\102\57\uffff\u0120\102\u0a70"+
            "\uffff\u03f0\102\21\uffff\ua7ff\102\u2100\uffff\u04d0\102\40"+
            "\uffff\u020e\102",
            "\1\102\1\103\1\uffff\12\102\1\22\6\uffff\22\102\1\170\7\102"+
            "\4\uffff\1\102\1\uffff\22\102\1\170\7\102\74\uffff\1\102\10"+
            "\uffff\27\102\1\uffff\37\102\1\uffff\u0286\102\1\uffff\u1c81"+
            "\102\14\uffff\2\102\61\uffff\2\102\57\uffff\u0120\102\u0a70"+
            "\uffff\u03f0\102\21\uffff\ua7ff\102\u2100\uffff\u04d0\102\40"+
            "\uffff\u020e\102",
            "",
            "\12\147\13\uffff\1\113\37\uffff\1\113",
            "",
            "\12\172\13\uffff\1\152\37\uffff\1\152",
            "",
            "\12\153\13\uffff\1\152\37\uffff\1\152",
            "",
            "",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "",
            "\12\163\13\uffff\1\135\37\uffff\1\135",
            "",
            "",
            "",
            "\1\102\1\103\1\uffff\12\102\1\22\6\uffff\32\102\4\uffff\1\102"+
            "\1\uffff\32\102\74\uffff\1\102\10\uffff\27\102\1\uffff\37\102"+
            "\1\uffff\u0286\102\1\uffff\u1c81\102\14\uffff\2\102\61\uffff"+
            "\2\102\57\uffff\u0120\102\u0a70\uffff\u03f0\102\21\uffff\ua7ff"+
            "\102\u2100\uffff\u04d0\102\40\uffff\u020e\102",
            "\1\102\1\103\1\uffff\12\102\1\22\6\uffff\4\102\1\u0080\25\102"+
            "\4\uffff\1\102\1\uffff\4\102\1\u0080\25\102\74\uffff\1\102\10"+
            "\uffff\27\102\1\uffff\37\102\1\uffff\u0286\102\1\uffff\u1c81"+
            "\102\14\uffff\2\102\61\uffff\2\102\57\uffff\u0120\102\u0a70"+
            "\uffff\u03f0\102\21\uffff\ua7ff\102\u2100\uffff\u04d0\102\40"+
            "\uffff\u020e\102",
            "",
            "\12\172\13\uffff\1\152\37\uffff\1\152",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\47\23\uffff\32\47\6\uffff\32\47\105\uffff\27\47\1\uffff\37"+
            "\47\1\uffff\u0208\47\160\uffff\16\47\1\uffff\u1c81\47\14\uffff"+
            "\2\47\142\uffff\u0120\47\u0a70\uffff\u03f0\47\21\uffff\ua7ff"+
            "\47\u2100\uffff\u04d0\47\40\uffff\u020e\47",
            "",
            "\1\102\1\103\1\uffff\12\102\1\22\6\uffff\32\102\4\uffff\1\102"+
            "\1\uffff\32\102\74\uffff\1\102\10\uffff\27\102\1\uffff\37\102"+
            "\1\uffff\u0286\102\1\uffff\u1c81\102\14\uffff\2\102\61\uffff"+
            "\2\102\57\uffff\u0120\102\u0a70\uffff\u03f0\102\21\uffff\ua7ff"+
            "\102\u2100\uffff\u04d0\102\40\uffff\u020e\102",
            "\1\u0086",
            "\1\u0087",
            "\1\47\23\uffff\32\47\6\uffff\32\47\105\uffff\27\47\1\uffff\37"+
            "\47\1\uffff\u0208\47\160\uffff\16\47\1\uffff\u1c81\47\14\uffff"+
            "\2\47\142\uffff\u0120\47\u0a70\uffff\u03f0\47\21\uffff\ua7ff"+
            "\47\u2100\uffff\u04d0\47\40\uffff\u020e\47",
            "",
            "",
            "\1\47\23\uffff\32\47\6\uffff\32\47\105\uffff\27\47\1\uffff\37"+
            "\47\1\uffff\u0208\47\160\uffff\16\47\1\uffff\u1c81\47\14\uffff"+
            "\2\47\142\uffff\u0120\47\u0a70\uffff\u03f0\47\21\uffff\ua7ff"+
            "\47\u2100\uffff\u04d0\47\40\uffff\u020e\47",
            "\1\47\23\uffff\32\47\6\uffff\32\47\105\uffff\27\47\1\uffff\37"+
            "\47\1\uffff\u0208\47\160\uffff\16\47\1\uffff\u1c81\47\14\uffff"+
            "\2\47\142\uffff\u0120\47\u0a70\uffff\u03f0\47\21\uffff\ua7ff"+
            "\47\u2100\uffff\u04d0\47\40\uffff\u020e\47",
            "",
            "",
            ""
    };

    static final short[] DFA36_eot = DFA.unpackEncodedString(DFA36_eotS);
    static final short[] DFA36_eof = DFA.unpackEncodedString(DFA36_eofS);
    static final char[] DFA36_min = DFA.unpackEncodedStringToUnsignedChars(DFA36_minS);
    static final char[] DFA36_max = DFA.unpackEncodedStringToUnsignedChars(DFA36_maxS);
    static final short[] DFA36_accept = DFA.unpackEncodedString(DFA36_acceptS);
    static final short[] DFA36_special = DFA.unpackEncodedString(DFA36_specialS);
    static final short[][] DFA36_transition;

    static {
        int numStates = DFA36_transitionS.length;
        DFA36_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA36_transition[i] = DFA.unpackEncodedString(DFA36_transitionS[i]);
        }
    }

    class DFA36 extends DFA {

        public DFA36(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 36;
            this.eot = DFA36_eot;
            this.eof = DFA36_eof;
            this.min = DFA36_min;
            this.max = DFA36_max;
            this.accept = DFA36_accept;
            this.special = DFA36_special;
            this.transition = DFA36_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( WS | AT_IMPORT | AT_PREFIX | AT_BASE | AT_DELAY | INHERITANCE | SIMILARITY | INSTANCE | PROPERTY | INSTANCE_PROPERTY | IMPLICATION | IMPLICATION_PRED | IMPLICATION_RETRO | IMPLICATION_CONC | EQUIVALENCE | EQUIVALENCE_PRED | EQUIVALENCE_CONC | NOT | PAST | PRESENT | FUTURE | CONJ | DISJ | OPEN_BRACE | CLOSE_BRACE | LPAREN | RPAREN | LBRACKET | RBRACKET | PNAME_NS | PNAME_LN | TRUE | FALSE | IRI_REF | LANGTAG | QUERY_VAR | STM_VAR | INTEGER | DECIMAL | DOUBLE | INTEGER_POSITIVE | DECIMAL_POSITIVE | DOUBLE_POSITIVE | INTEGER_NEGATIVE | DECIMAL_NEGATIVE | DOUBLE_NEGATIVE | STRING_LITERAL1 | STRING_LITERAL2 | STRING_LITERAL_LONG1 | STRING_LITERAL_LONG2 | COMMENT | REFERENCE | EXCLAMATION | QUESTION | DOT | COMMA | SEMICOLON | AMPERSAND | BAR | LANGLE | RANGLE | PERCENT | PLUS | MINUS | STAR | TILDE );";
        }
    }
 

}