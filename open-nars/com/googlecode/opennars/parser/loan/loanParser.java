package com.googlecode.opennars.parser.loan;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class loanParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "AT_BASE", "IRI_REF", "DOT", "AT_IMPORT", "AT_PREFIX", "PNAME_NS", "AT_DELAY", "LPAREN", "INTEGER", "RPAREN", "EXCLAMATION", "QUESTION", "PERCENT", "DECIMAL", "SEMICOLON", "CONJ", "COMMA", "DISJ", "NOT", "PAST", "PRESENT", "FUTURE", "INHERITANCE", "SIMILARITY", "INSTANCE", "PROPERTY", "INSTANCE_PROPERTY", "IMPLICATION", "IMPLICATION_PRED", "IMPLICATION_RETRO", "IMPLICATION_CONC", "EQUIVALENCE", "EQUIVALENCE_PRED", "EQUIVALENCE_CONC", "AMPERSAND", "BAR", "OPEN_BRACE", "CLOSE_BRACE", "LBRACKET", "RBRACKET", "MINUS", "TILDE", "STAR", "QUERY_VAR", "STM_VAR", "DOUBLE", "INTEGER_POSITIVE", "DECIMAL_POSITIVE", "DOUBLE_POSITIVE", "INTEGER_NEGATIVE", "DECIMAL_NEGATIVE", "DOUBLE_NEGATIVE", "TRUE", "FALSE", "STRING_LITERAL1", "STRING_LITERAL2", "STRING_LITERAL_LONG1", "STRING_LITERAL_LONG2", "PNAME_LN", "EOL", "WS", "PN_PREFIX", "PN_LOCAL", "LANGLE", "RANGLE", "PN_CHARS_BASE", "DIGIT", "LANGTAG", "EXPONENT", "PLUS", "ECHAR", "PN_CHARS_U", "VARNAME", "PN_CHARS", "COMMENT", "REFERENCE"
    };
    public static final int COMMA=20;
    public static final int PN_CHARS_U=75;
    public static final int MINUS=44;
    public static final int PERCENT=16;
    public static final int AT_PREFIX=8;
    public static final int OPEN_BRACE=40;
    public static final int DOUBLE=49;
    public static final int AT_BASE=4;
    public static final int EQUIVALENCE=35;
    public static final int FALSE=57;
    public static final int PN_CHARS_BASE=69;
    public static final int EQUIVALENCE_CONC=37;
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
    public static final int INTEGER_NEGATIVE=53;
    public static final int PNAME_NS=9;
    public static final int PAST=23;
    public static final int SIMILARITY=27;
    public static final int PROPERTY=29;
    public static final int DECIMAL_POSITIVE=51;
    public static final int STRING_LITERAL_LONG1=60;
    public static final int IMPLICATION_PRED=32;
    public static final int WS=64;
    public static final int PNAME_LN=62;
    public static final int LANGTAG=71;
    public static final int EXCLAMATION=14;
    public static final int PN_LOCAL=66;
    public static final int VARNAME=76;
    public static final int CLOSE_BRACE=41;
    public static final int COMMENT=78;
    public static final int PN_CHARS=77;
    public static final int PRESENT=24;
    public static final int AT_IMPORT=7;
    public static final int STRING_LITERAL_LONG2=61;
    public static final int DOUBLE_NEGATIVE=55;
    public static final int EXPONENT=72;
    public static final int SEMICOLON=18;
    public static final int BAR=39;
    public static final int DECIMAL_NEGATIVE=54;
    public static final int EOF=-1;
    public static final int IRI_REF=5;
    public static final int DOUBLE_POSITIVE=52;
    public static final int REFERENCE=79;
    public static final int EOL=63;
    public static final int INTEGER_POSITIVE=50;
    public static final int STAR=46;
    public static final int NOT=22;
    public static final int STRING_LITERAL2=59;
    public static final int TRUE=56;
    public static final int STRING_LITERAL1=58;
    public static final int IMPLICATION_CONC=34;

        public loanParser(TokenStream input) {
            super(input);
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "/Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g"; }


    public static class document_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start document
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:9:1: document : ( base_rule )? ( at_rule | sentence )* EOF ;
    public final document_return document() throws RecognitionException {
        document_return retval = new document_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EOF4=null;
        base_rule_return base_rule1 = null;

        at_rule_return at_rule2 = null;

        sentence_return sentence3 = null;


        Object EOF4_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:10:2: ( ( base_rule )? ( at_rule | sentence )* EOF )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:10:4: ( base_rule )? ( at_rule | sentence )* EOF
            {
            root_0 = (Object)adaptor.nil();

            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:10:4: ( base_rule )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==AT_BASE) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:10:4: base_rule
                    {
                    pushFollow(FOLLOW_base_rule_in_document27);
                    base_rule1=base_rule();
                    _fsp--;

                    adaptor.addChild(root_0, base_rule1.getTree());

                    }
                    break;

            }

            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:10:15: ( at_rule | sentence )*
            loop2:
            do {
                int alt2=3;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=AT_IMPORT && LA2_0<=AT_PREFIX)||LA2_0==AT_DELAY) ) {
                    alt2=1;
                }
                else if ( (LA2_0==IRI_REF||LA2_0==PNAME_NS||(LA2_0>=LPAREN && LA2_0<=INTEGER)||LA2_0==DECIMAL||(LA2_0>=NOT && LA2_0<=FUTURE)||LA2_0==OPEN_BRACE||LA2_0==LBRACKET||(LA2_0>=QUERY_VAR && LA2_0<=PNAME_LN)) ) {
                    alt2=2;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:10:16: at_rule
            	    {
            	    pushFollow(FOLLOW_at_rule_in_document31);
            	    at_rule2=at_rule();
            	    _fsp--;

            	    adaptor.addChild(root_0, at_rule2.getTree());

            	    }
            	    break;
            	case 2 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:10:26: sentence
            	    {
            	    pushFollow(FOLLOW_sentence_in_document35);
            	    sentence3=sentence();
            	    _fsp--;

            	    adaptor.addChild(root_0, sentence3.getTree());

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            EOF4=(Token)input.LT(1);
            match(input,EOF,FOLLOW_EOF_in_document39); 
            EOF4_tree = (Object)adaptor.create(EOF4);
            adaptor.addChild(root_0, EOF4_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end document

    public static class base_rule_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start base_rule
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:12:1: base_rule : AT_BASE IRI_REF DOT ;
    public final base_rule_return base_rule() throws RecognitionException {
        base_rule_return retval = new base_rule_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AT_BASE5=null;
        Token IRI_REF6=null;
        Token DOT7=null;

        Object AT_BASE5_tree=null;
        Object IRI_REF6_tree=null;
        Object DOT7_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:13:2: ( AT_BASE IRI_REF DOT )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:13:4: AT_BASE IRI_REF DOT
            {
            root_0 = (Object)adaptor.nil();

            AT_BASE5=(Token)input.LT(1);
            match(input,AT_BASE,FOLLOW_AT_BASE_in_base_rule50); 
            AT_BASE5_tree = (Object)adaptor.create(AT_BASE5);
            root_0 = (Object)adaptor.becomeRoot(AT_BASE5_tree, root_0);

            IRI_REF6=(Token)input.LT(1);
            match(input,IRI_REF,FOLLOW_IRI_REF_in_base_rule53); 
            IRI_REF6_tree = (Object)adaptor.create(IRI_REF6);
            adaptor.addChild(root_0, IRI_REF6_tree);

            DOT7=(Token)input.LT(1);
            match(input,DOT,FOLLOW_DOT_in_base_rule55); 

            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end base_rule

    public static class at_rule_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start at_rule
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:16:1: at_rule : ( AT_IMPORT IRI_REF DOT | AT_PREFIX PNAME_NS IRI_REF DOT | AT_DELAY LPAREN INTEGER RPAREN DOT );
    public final at_rule_return at_rule() throws RecognitionException {
        at_rule_return retval = new at_rule_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AT_IMPORT8=null;
        Token IRI_REF9=null;
        Token DOT10=null;
        Token AT_PREFIX11=null;
        Token PNAME_NS12=null;
        Token IRI_REF13=null;
        Token DOT14=null;
        Token AT_DELAY15=null;
        Token LPAREN16=null;
        Token INTEGER17=null;
        Token RPAREN18=null;
        Token DOT19=null;

        Object AT_IMPORT8_tree=null;
        Object IRI_REF9_tree=null;
        Object DOT10_tree=null;
        Object AT_PREFIX11_tree=null;
        Object PNAME_NS12_tree=null;
        Object IRI_REF13_tree=null;
        Object DOT14_tree=null;
        Object AT_DELAY15_tree=null;
        Object LPAREN16_tree=null;
        Object INTEGER17_tree=null;
        Object RPAREN18_tree=null;
        Object DOT19_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:16:9: ( AT_IMPORT IRI_REF DOT | AT_PREFIX PNAME_NS IRI_REF DOT | AT_DELAY LPAREN INTEGER RPAREN DOT )
            int alt3=3;
            switch ( input.LA(1) ) {
            case AT_IMPORT:
                {
                alt3=1;
                }
                break;
            case AT_PREFIX:
                {
                alt3=2;
                }
                break;
            case AT_DELAY:
                {
                alt3=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("16:1: at_rule : ( AT_IMPORT IRI_REF DOT | AT_PREFIX PNAME_NS IRI_REF DOT | AT_DELAY LPAREN INTEGER RPAREN DOT );", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:16:11: AT_IMPORT IRI_REF DOT
                    {
                    root_0 = (Object)adaptor.nil();

                    AT_IMPORT8=(Token)input.LT(1);
                    match(input,AT_IMPORT,FOLLOW_AT_IMPORT_in_at_rule66); 
                    AT_IMPORT8_tree = (Object)adaptor.create(AT_IMPORT8);
                    root_0 = (Object)adaptor.becomeRoot(AT_IMPORT8_tree, root_0);

                    IRI_REF9=(Token)input.LT(1);
                    match(input,IRI_REF,FOLLOW_IRI_REF_in_at_rule69); 
                    IRI_REF9_tree = (Object)adaptor.create(IRI_REF9);
                    adaptor.addChild(root_0, IRI_REF9_tree);

                    DOT10=(Token)input.LT(1);
                    match(input,DOT,FOLLOW_DOT_in_at_rule71); 

                    }
                    break;
                case 2 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:17:4: AT_PREFIX PNAME_NS IRI_REF DOT
                    {
                    root_0 = (Object)adaptor.nil();

                    AT_PREFIX11=(Token)input.LT(1);
                    match(input,AT_PREFIX,FOLLOW_AT_PREFIX_in_at_rule77); 
                    AT_PREFIX11_tree = (Object)adaptor.create(AT_PREFIX11);
                    root_0 = (Object)adaptor.becomeRoot(AT_PREFIX11_tree, root_0);

                    PNAME_NS12=(Token)input.LT(1);
                    match(input,PNAME_NS,FOLLOW_PNAME_NS_in_at_rule80); 
                    PNAME_NS12_tree = (Object)adaptor.create(PNAME_NS12);
                    adaptor.addChild(root_0, PNAME_NS12_tree);

                    IRI_REF13=(Token)input.LT(1);
                    match(input,IRI_REF,FOLLOW_IRI_REF_in_at_rule82); 
                    IRI_REF13_tree = (Object)adaptor.create(IRI_REF13);
                    adaptor.addChild(root_0, IRI_REF13_tree);

                    DOT14=(Token)input.LT(1);
                    match(input,DOT,FOLLOW_DOT_in_at_rule84); 

                    }
                    break;
                case 3 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:18:4: AT_DELAY LPAREN INTEGER RPAREN DOT
                    {
                    root_0 = (Object)adaptor.nil();

                    AT_DELAY15=(Token)input.LT(1);
                    match(input,AT_DELAY,FOLLOW_AT_DELAY_in_at_rule90); 
                    AT_DELAY15_tree = (Object)adaptor.create(AT_DELAY15);
                    root_0 = (Object)adaptor.becomeRoot(AT_DELAY15_tree, root_0);

                    LPAREN16=(Token)input.LT(1);
                    match(input,LPAREN,FOLLOW_LPAREN_in_at_rule93); 
                    INTEGER17=(Token)input.LT(1);
                    match(input,INTEGER,FOLLOW_INTEGER_in_at_rule96); 
                    INTEGER17_tree = (Object)adaptor.create(INTEGER17);
                    adaptor.addChild(root_0, INTEGER17_tree);

                    RPAREN18=(Token)input.LT(1);
                    match(input,RPAREN,FOLLOW_RPAREN_in_at_rule98); 
                    DOT19=(Token)input.LT(1);
                    match(input,DOT,FOLLOW_DOT_in_at_rule101); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end at_rule

    public static class sentence_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start sentence
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:21:1: sentence : statement ( judgement | question | goal ) ;
    public final sentence_return sentence() throws RecognitionException {
        sentence_return retval = new sentence_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        statement_return statement20 = null;

        judgement_return judgement21 = null;

        question_return question22 = null;

        goal_return goal23 = null;



        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:22:2: ( statement ( judgement | question | goal ) )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:22:4: statement ( judgement | question | goal )
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_statement_in_sentence114);
            statement20=statement();
            _fsp--;

            adaptor.addChild(root_0, statement20.getTree());
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:22:14: ( judgement | question | goal )
            int alt4=3;
            switch ( input.LA(1) ) {
            case DOT:
                {
                alt4=1;
                }
                break;
            case QUESTION:
                {
                alt4=2;
                }
                break;
            case EXCLAMATION:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("22:14: ( judgement | question | goal )", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:22:15: judgement
                    {
                    pushFollow(FOLLOW_judgement_in_sentence117);
                    judgement21=judgement();
                    _fsp--;

                    root_0 = (Object)adaptor.becomeRoot(judgement21.getTree(), root_0);

                    }
                    break;
                case 2 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:22:26: question
                    {
                    pushFollow(FOLLOW_question_in_sentence120);
                    question22=question();
                    _fsp--;

                    root_0 = (Object)adaptor.becomeRoot(question22.getTree(), root_0);

                    }
                    break;
                case 3 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:22:36: goal
                    {
                    pushFollow(FOLLOW_goal_in_sentence123);
                    goal23=goal();
                    _fsp--;

                    root_0 = (Object)adaptor.becomeRoot(goal23.getTree(), root_0);

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end sentence

    public static class judgement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start judgement
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:25:1: judgement : DOT ( truthvalue )? ;
    public final judgement_return judgement() throws RecognitionException {
        judgement_return retval = new judgement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token DOT24=null;
        truthvalue_return truthvalue25 = null;


        Object DOT24_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:26:2: ( DOT ( truthvalue )? )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:26:4: DOT ( truthvalue )?
            {
            root_0 = (Object)adaptor.nil();

            DOT24=(Token)input.LT(1);
            match(input,DOT,FOLLOW_DOT_in_judgement138); 
            DOT24_tree = (Object)adaptor.create(DOT24);
            root_0 = (Object)adaptor.becomeRoot(DOT24_tree, root_0);

            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:26:9: ( truthvalue )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==PERCENT) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:26:9: truthvalue
                    {
                    pushFollow(FOLLOW_truthvalue_in_judgement141);
                    truthvalue25=truthvalue();
                    _fsp--;

                    adaptor.addChild(root_0, truthvalue25.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end judgement

    public static class goal_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start goal
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:29:1: goal : EXCLAMATION ( truthvalue )? ;
    public final goal_return goal() throws RecognitionException {
        goal_return retval = new goal_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token EXCLAMATION26=null;
        truthvalue_return truthvalue27 = null;


        Object EXCLAMATION26_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:29:6: ( EXCLAMATION ( truthvalue )? )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:29:8: EXCLAMATION ( truthvalue )?
            {
            root_0 = (Object)adaptor.nil();

            EXCLAMATION26=(Token)input.LT(1);
            match(input,EXCLAMATION,FOLLOW_EXCLAMATION_in_goal153); 
            EXCLAMATION26_tree = (Object)adaptor.create(EXCLAMATION26);
            root_0 = (Object)adaptor.becomeRoot(EXCLAMATION26_tree, root_0);

            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:29:21: ( truthvalue )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==PERCENT) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:29:21: truthvalue
                    {
                    pushFollow(FOLLOW_truthvalue_in_goal156);
                    truthvalue27=truthvalue();
                    _fsp--;

                    adaptor.addChild(root_0, truthvalue27.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end goal

    public static class question_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start question
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:32:1: question : QUESTION ;
    public final question_return question() throws RecognitionException {
        question_return retval = new question_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token QUESTION28=null;

        Object QUESTION28_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:33:2: ( QUESTION )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:33:4: QUESTION
            {
            root_0 = (Object)adaptor.nil();

            QUESTION28=(Token)input.LT(1);
            match(input,QUESTION,FOLLOW_QUESTION_in_question170); 
            QUESTION28_tree = (Object)adaptor.create(QUESTION28);
            root_0 = (Object)adaptor.becomeRoot(QUESTION28_tree, root_0);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end question

    public static class truthvalue_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start truthvalue
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:36:1: truthvalue : PERCENT ( DECIMAL | INTEGER ) ( SEMICOLON DECIMAL )? PERCENT ;
    public final truthvalue_return truthvalue() throws RecognitionException {
        truthvalue_return retval = new truthvalue_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token PERCENT29=null;
        Token set30=null;
        Token SEMICOLON31=null;
        Token DECIMAL32=null;
        Token PERCENT33=null;

        Object PERCENT29_tree=null;
        Object set30_tree=null;
        Object SEMICOLON31_tree=null;
        Object DECIMAL32_tree=null;
        Object PERCENT33_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:37:2: ( PERCENT ( DECIMAL | INTEGER ) ( SEMICOLON DECIMAL )? PERCENT )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:37:4: PERCENT ( DECIMAL | INTEGER ) ( SEMICOLON DECIMAL )? PERCENT
            {
            root_0 = (Object)adaptor.nil();

            PERCENT29=(Token)input.LT(1);
            match(input,PERCENT,FOLLOW_PERCENT_in_truthvalue184); 
            set30=(Token)input.LT(1);
            if ( input.LA(1)==INTEGER||input.LA(1)==DECIMAL ) {
                input.consume();
                adaptor.addChild(root_0, adaptor.create(set30));
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_truthvalue187);    throw mse;
            }

            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:37:33: ( SEMICOLON DECIMAL )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==SEMICOLON) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:37:34: SEMICOLON DECIMAL
                    {
                    SEMICOLON31=(Token)input.LT(1);
                    match(input,SEMICOLON,FOLLOW_SEMICOLON_in_truthvalue196); 
                    SEMICOLON31_tree = (Object)adaptor.create(SEMICOLON31);
                    adaptor.addChild(root_0, SEMICOLON31_tree);

                    DECIMAL32=(Token)input.LT(1);
                    match(input,DECIMAL,FOLLOW_DECIMAL_in_truthvalue198); 
                    DECIMAL32_tree = (Object)adaptor.create(DECIMAL32);
                    adaptor.addChild(root_0, DECIMAL32_tree);


                    }
                    break;

            }

            PERCENT33=(Token)input.LT(1);
            match(input,PERCENT,FOLLOW_PERCENT_in_truthvalue202); 

            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end truthvalue

    public static class statement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start statement
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:39:1: statement : unary_statement ( ( CONJ | SEMICOLON | COMMA | DISJ ) unary_statement )* ;
    public final statement_return statement() throws RecognitionException {
        statement_return retval = new statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token CONJ35=null;
        Token SEMICOLON36=null;
        Token COMMA37=null;
        Token DISJ38=null;
        unary_statement_return unary_statement34 = null;

        unary_statement_return unary_statement39 = null;


        Object CONJ35_tree=null;
        Object SEMICOLON36_tree=null;
        Object COMMA37_tree=null;
        Object DISJ38_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:40:2: ( unary_statement ( ( CONJ | SEMICOLON | COMMA | DISJ ) unary_statement )* )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:40:4: unary_statement ( ( CONJ | SEMICOLON | COMMA | DISJ ) unary_statement )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_unary_statement_in_statement213);
            unary_statement34=unary_statement();
            _fsp--;

            adaptor.addChild(root_0, unary_statement34.getTree());
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:40:20: ( ( CONJ | SEMICOLON | COMMA | DISJ ) unary_statement )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>=SEMICOLON && LA9_0<=DISJ)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:40:21: ( CONJ | SEMICOLON | COMMA | DISJ ) unary_statement
            	    {
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:40:21: ( CONJ | SEMICOLON | COMMA | DISJ )
            	    int alt8=4;
            	    switch ( input.LA(1) ) {
            	    case CONJ:
            	        {
            	        alt8=1;
            	        }
            	        break;
            	    case SEMICOLON:
            	        {
            	        alt8=2;
            	        }
            	        break;
            	    case COMMA:
            	        {
            	        alt8=3;
            	        }
            	        break;
            	    case DISJ:
            	        {
            	        alt8=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("40:21: ( CONJ | SEMICOLON | COMMA | DISJ )", 8, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt8) {
            	        case 1 :
            	            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:40:22: CONJ
            	            {
            	            CONJ35=(Token)input.LT(1);
            	            match(input,CONJ,FOLLOW_CONJ_in_statement217); 
            	            CONJ35_tree = (Object)adaptor.create(CONJ35);
            	            root_0 = (Object)adaptor.becomeRoot(CONJ35_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:40:30: SEMICOLON
            	            {
            	            SEMICOLON36=(Token)input.LT(1);
            	            match(input,SEMICOLON,FOLLOW_SEMICOLON_in_statement222); 
            	            SEMICOLON36_tree = (Object)adaptor.create(SEMICOLON36);
            	            root_0 = (Object)adaptor.becomeRoot(SEMICOLON36_tree, root_0);


            	            }
            	            break;
            	        case 3 :
            	            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:40:43: COMMA
            	            {
            	            COMMA37=(Token)input.LT(1);
            	            match(input,COMMA,FOLLOW_COMMA_in_statement227); 
            	            COMMA37_tree = (Object)adaptor.create(COMMA37);
            	            root_0 = (Object)adaptor.becomeRoot(COMMA37_tree, root_0);


            	            }
            	            break;
            	        case 4 :
            	            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:40:52: DISJ
            	            {
            	            DISJ38=(Token)input.LT(1);
            	            match(input,DISJ,FOLLOW_DISJ_in_statement232); 
            	            DISJ38_tree = (Object)adaptor.create(DISJ38);
            	            root_0 = (Object)adaptor.becomeRoot(DISJ38_tree, root_0);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_unary_statement_in_statement236);
            	    unary_statement39=unary_statement();
            	    _fsp--;

            	    adaptor.addChild(root_0, unary_statement39.getTree());

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end statement

    public static class unary_statement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start unary_statement
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:43:1: unary_statement : ( NOT simple_statement | PAST simple_statement | PRESENT simple_statement | FUTURE simple_statement | simple_statement );
    public final unary_statement_return unary_statement() throws RecognitionException {
        unary_statement_return retval = new unary_statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token NOT40=null;
        Token PAST42=null;
        Token PRESENT44=null;
        Token FUTURE46=null;
        simple_statement_return simple_statement41 = null;

        simple_statement_return simple_statement43 = null;

        simple_statement_return simple_statement45 = null;

        simple_statement_return simple_statement47 = null;

        simple_statement_return simple_statement48 = null;


        Object NOT40_tree=null;
        Object PAST42_tree=null;
        Object PRESENT44_tree=null;
        Object FUTURE46_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:44:2: ( NOT simple_statement | PAST simple_statement | PRESENT simple_statement | FUTURE simple_statement | simple_statement )
            int alt10=5;
            switch ( input.LA(1) ) {
            case NOT:
                {
                alt10=1;
                }
                break;
            case PAST:
                {
                alt10=2;
                }
                break;
            case PRESENT:
                {
                alt10=3;
                }
                break;
            case FUTURE:
                {
                alt10=4;
                }
                break;
            case IRI_REF:
            case PNAME_NS:
            case LPAREN:
            case INTEGER:
            case DECIMAL:
            case OPEN_BRACE:
            case LBRACKET:
            case QUERY_VAR:
            case STM_VAR:
            case DOUBLE:
            case INTEGER_POSITIVE:
            case DECIMAL_POSITIVE:
            case DOUBLE_POSITIVE:
            case INTEGER_NEGATIVE:
            case DECIMAL_NEGATIVE:
            case DOUBLE_NEGATIVE:
            case TRUE:
            case FALSE:
            case STRING_LITERAL1:
            case STRING_LITERAL2:
            case STRING_LITERAL_LONG1:
            case STRING_LITERAL_LONG2:
            case PNAME_LN:
                {
                alt10=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("43:1: unary_statement : ( NOT simple_statement | PAST simple_statement | PRESENT simple_statement | FUTURE simple_statement | simple_statement );", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:44:4: NOT simple_statement
                    {
                    root_0 = (Object)adaptor.nil();

                    NOT40=(Token)input.LT(1);
                    match(input,NOT,FOLLOW_NOT_in_unary_statement251); 
                    NOT40_tree = (Object)adaptor.create(NOT40);
                    adaptor.addChild(root_0, NOT40_tree);

                    pushFollow(FOLLOW_simple_statement_in_unary_statement253);
                    simple_statement41=simple_statement();
                    _fsp--;

                    adaptor.addChild(root_0, simple_statement41.getTree());

                    }
                    break;
                case 2 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:45:4: PAST simple_statement
                    {
                    root_0 = (Object)adaptor.nil();

                    PAST42=(Token)input.LT(1);
                    match(input,PAST,FOLLOW_PAST_in_unary_statement258); 
                    PAST42_tree = (Object)adaptor.create(PAST42);
                    adaptor.addChild(root_0, PAST42_tree);

                    pushFollow(FOLLOW_simple_statement_in_unary_statement260);
                    simple_statement43=simple_statement();
                    _fsp--;

                    adaptor.addChild(root_0, simple_statement43.getTree());

                    }
                    break;
                case 3 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:46:4: PRESENT simple_statement
                    {
                    root_0 = (Object)adaptor.nil();

                    PRESENT44=(Token)input.LT(1);
                    match(input,PRESENT,FOLLOW_PRESENT_in_unary_statement265); 
                    PRESENT44_tree = (Object)adaptor.create(PRESENT44);
                    adaptor.addChild(root_0, PRESENT44_tree);

                    pushFollow(FOLLOW_simple_statement_in_unary_statement267);
                    simple_statement45=simple_statement();
                    _fsp--;

                    adaptor.addChild(root_0, simple_statement45.getTree());

                    }
                    break;
                case 4 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:47:4: FUTURE simple_statement
                    {
                    root_0 = (Object)adaptor.nil();

                    FUTURE46=(Token)input.LT(1);
                    match(input,FUTURE,FOLLOW_FUTURE_in_unary_statement272); 
                    FUTURE46_tree = (Object)adaptor.create(FUTURE46);
                    adaptor.addChild(root_0, FUTURE46_tree);

                    pushFollow(FOLLOW_simple_statement_in_unary_statement274);
                    simple_statement47=simple_statement();
                    _fsp--;

                    adaptor.addChild(root_0, simple_statement47.getTree());

                    }
                    break;
                case 5 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:48:4: simple_statement
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_simple_statement_in_unary_statement279);
                    simple_statement48=simple_statement();
                    _fsp--;

                    adaptor.addChild(root_0, simple_statement48.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end unary_statement

    public static class simple_statement_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start simple_statement
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:51:1: simple_statement : term ( ( INHERITANCE | SIMILARITY | INSTANCE | PROPERTY | INSTANCE_PROPERTY | IMPLICATION | IMPLICATION_PRED | IMPLICATION_RETRO | IMPLICATION_CONC | EQUIVALENCE | EQUIVALENCE_PRED | EQUIVALENCE_CONC ) term )? ;
    public final simple_statement_return simple_statement() throws RecognitionException {
        simple_statement_return retval = new simple_statement_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token INHERITANCE50=null;
        Token SIMILARITY51=null;
        Token INSTANCE52=null;
        Token PROPERTY53=null;
        Token INSTANCE_PROPERTY54=null;
        Token IMPLICATION55=null;
        Token IMPLICATION_PRED56=null;
        Token IMPLICATION_RETRO57=null;
        Token IMPLICATION_CONC58=null;
        Token EQUIVALENCE59=null;
        Token EQUIVALENCE_PRED60=null;
        Token EQUIVALENCE_CONC61=null;
        term_return term49 = null;

        term_return term62 = null;


        Object INHERITANCE50_tree=null;
        Object SIMILARITY51_tree=null;
        Object INSTANCE52_tree=null;
        Object PROPERTY53_tree=null;
        Object INSTANCE_PROPERTY54_tree=null;
        Object IMPLICATION55_tree=null;
        Object IMPLICATION_PRED56_tree=null;
        Object IMPLICATION_RETRO57_tree=null;
        Object IMPLICATION_CONC58_tree=null;
        Object EQUIVALENCE59_tree=null;
        Object EQUIVALENCE_PRED60_tree=null;
        Object EQUIVALENCE_CONC61_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:52:2: ( term ( ( INHERITANCE | SIMILARITY | INSTANCE | PROPERTY | INSTANCE_PROPERTY | IMPLICATION | IMPLICATION_PRED | IMPLICATION_RETRO | IMPLICATION_CONC | EQUIVALENCE | EQUIVALENCE_PRED | EQUIVALENCE_CONC ) term )? )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:52:4: term ( ( INHERITANCE | SIMILARITY | INSTANCE | PROPERTY | INSTANCE_PROPERTY | IMPLICATION | IMPLICATION_PRED | IMPLICATION_RETRO | IMPLICATION_CONC | EQUIVALENCE | EQUIVALENCE_PRED | EQUIVALENCE_CONC ) term )?
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_term_in_simple_statement292);
            term49=term();
            _fsp--;

            adaptor.addChild(root_0, term49.getTree());
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:52:9: ( ( INHERITANCE | SIMILARITY | INSTANCE | PROPERTY | INSTANCE_PROPERTY | IMPLICATION | IMPLICATION_PRED | IMPLICATION_RETRO | IMPLICATION_CONC | EQUIVALENCE | EQUIVALENCE_PRED | EQUIVALENCE_CONC ) term )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0>=INHERITANCE && LA12_0<=EQUIVALENCE_CONC)) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:52:10: ( INHERITANCE | SIMILARITY | INSTANCE | PROPERTY | INSTANCE_PROPERTY | IMPLICATION | IMPLICATION_PRED | IMPLICATION_RETRO | IMPLICATION_CONC | EQUIVALENCE | EQUIVALENCE_PRED | EQUIVALENCE_CONC ) term
                    {
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:52:10: ( INHERITANCE | SIMILARITY | INSTANCE | PROPERTY | INSTANCE_PROPERTY | IMPLICATION | IMPLICATION_PRED | IMPLICATION_RETRO | IMPLICATION_CONC | EQUIVALENCE | EQUIVALENCE_PRED | EQUIVALENCE_CONC )
                    int alt11=12;
                    switch ( input.LA(1) ) {
                    case INHERITANCE:
                        {
                        alt11=1;
                        }
                        break;
                    case SIMILARITY:
                        {
                        alt11=2;
                        }
                        break;
                    case INSTANCE:
                        {
                        alt11=3;
                        }
                        break;
                    case PROPERTY:
                        {
                        alt11=4;
                        }
                        break;
                    case INSTANCE_PROPERTY:
                        {
                        alt11=5;
                        }
                        break;
                    case IMPLICATION:
                        {
                        alt11=6;
                        }
                        break;
                    case IMPLICATION_PRED:
                        {
                        alt11=7;
                        }
                        break;
                    case IMPLICATION_RETRO:
                        {
                        alt11=8;
                        }
                        break;
                    case IMPLICATION_CONC:
                        {
                        alt11=9;
                        }
                        break;
                    case EQUIVALENCE:
                        {
                        alt11=10;
                        }
                        break;
                    case EQUIVALENCE_PRED:
                        {
                        alt11=11;
                        }
                        break;
                    case EQUIVALENCE_CONC:
                        {
                        alt11=12;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("52:10: ( INHERITANCE | SIMILARITY | INSTANCE | PROPERTY | INSTANCE_PROPERTY | IMPLICATION | IMPLICATION_PRED | IMPLICATION_RETRO | IMPLICATION_CONC | EQUIVALENCE | EQUIVALENCE_PRED | EQUIVALENCE_CONC )", 11, 0, input);

                        throw nvae;
                    }

                    switch (alt11) {
                        case 1 :
                            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:52:11: INHERITANCE
                            {
                            INHERITANCE50=(Token)input.LT(1);
                            match(input,INHERITANCE,FOLLOW_INHERITANCE_in_simple_statement296); 
                            INHERITANCE50_tree = (Object)adaptor.create(INHERITANCE50);
                            root_0 = (Object)adaptor.becomeRoot(INHERITANCE50_tree, root_0);


                            }
                            break;
                        case 2 :
                            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:52:26: SIMILARITY
                            {
                            SIMILARITY51=(Token)input.LT(1);
                            match(input,SIMILARITY,FOLLOW_SIMILARITY_in_simple_statement301); 
                            SIMILARITY51_tree = (Object)adaptor.create(SIMILARITY51);
                            root_0 = (Object)adaptor.becomeRoot(SIMILARITY51_tree, root_0);


                            }
                            break;
                        case 3 :
                            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:52:40: INSTANCE
                            {
                            INSTANCE52=(Token)input.LT(1);
                            match(input,INSTANCE,FOLLOW_INSTANCE_in_simple_statement306); 
                            INSTANCE52_tree = (Object)adaptor.create(INSTANCE52);
                            root_0 = (Object)adaptor.becomeRoot(INSTANCE52_tree, root_0);


                            }
                            break;
                        case 4 :
                            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:52:52: PROPERTY
                            {
                            PROPERTY53=(Token)input.LT(1);
                            match(input,PROPERTY,FOLLOW_PROPERTY_in_simple_statement311); 
                            PROPERTY53_tree = (Object)adaptor.create(PROPERTY53);
                            root_0 = (Object)adaptor.becomeRoot(PROPERTY53_tree, root_0);


                            }
                            break;
                        case 5 :
                            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:52:64: INSTANCE_PROPERTY
                            {
                            INSTANCE_PROPERTY54=(Token)input.LT(1);
                            match(input,INSTANCE_PROPERTY,FOLLOW_INSTANCE_PROPERTY_in_simple_statement316); 
                            INSTANCE_PROPERTY54_tree = (Object)adaptor.create(INSTANCE_PROPERTY54);
                            root_0 = (Object)adaptor.becomeRoot(INSTANCE_PROPERTY54_tree, root_0);


                            }
                            break;
                        case 6 :
                            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:52:85: IMPLICATION
                            {
                            IMPLICATION55=(Token)input.LT(1);
                            match(input,IMPLICATION,FOLLOW_IMPLICATION_in_simple_statement321); 
                            IMPLICATION55_tree = (Object)adaptor.create(IMPLICATION55);
                            root_0 = (Object)adaptor.becomeRoot(IMPLICATION55_tree, root_0);


                            }
                            break;
                        case 7 :
                            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:52:100: IMPLICATION_PRED
                            {
                            IMPLICATION_PRED56=(Token)input.LT(1);
                            match(input,IMPLICATION_PRED,FOLLOW_IMPLICATION_PRED_in_simple_statement326); 
                            IMPLICATION_PRED56_tree = (Object)adaptor.create(IMPLICATION_PRED56);
                            root_0 = (Object)adaptor.becomeRoot(IMPLICATION_PRED56_tree, root_0);


                            }
                            break;
                        case 8 :
                            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:52:120: IMPLICATION_RETRO
                            {
                            IMPLICATION_RETRO57=(Token)input.LT(1);
                            match(input,IMPLICATION_RETRO,FOLLOW_IMPLICATION_RETRO_in_simple_statement331); 
                            IMPLICATION_RETRO57_tree = (Object)adaptor.create(IMPLICATION_RETRO57);
                            root_0 = (Object)adaptor.becomeRoot(IMPLICATION_RETRO57_tree, root_0);


                            }
                            break;
                        case 9 :
                            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:52:141: IMPLICATION_CONC
                            {
                            IMPLICATION_CONC58=(Token)input.LT(1);
                            match(input,IMPLICATION_CONC,FOLLOW_IMPLICATION_CONC_in_simple_statement336); 
                            IMPLICATION_CONC58_tree = (Object)adaptor.create(IMPLICATION_CONC58);
                            root_0 = (Object)adaptor.becomeRoot(IMPLICATION_CONC58_tree, root_0);


                            }
                            break;
                        case 10 :
                            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:52:161: EQUIVALENCE
                            {
                            EQUIVALENCE59=(Token)input.LT(1);
                            match(input,EQUIVALENCE,FOLLOW_EQUIVALENCE_in_simple_statement341); 
                            EQUIVALENCE59_tree = (Object)adaptor.create(EQUIVALENCE59);
                            root_0 = (Object)adaptor.becomeRoot(EQUIVALENCE59_tree, root_0);


                            }
                            break;
                        case 11 :
                            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:52:176: EQUIVALENCE_PRED
                            {
                            EQUIVALENCE_PRED60=(Token)input.LT(1);
                            match(input,EQUIVALENCE_PRED,FOLLOW_EQUIVALENCE_PRED_in_simple_statement346); 
                            EQUIVALENCE_PRED60_tree = (Object)adaptor.create(EQUIVALENCE_PRED60);
                            root_0 = (Object)adaptor.becomeRoot(EQUIVALENCE_PRED60_tree, root_0);


                            }
                            break;
                        case 12 :
                            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:52:196: EQUIVALENCE_CONC
                            {
                            EQUIVALENCE_CONC61=(Token)input.LT(1);
                            match(input,EQUIVALENCE_CONC,FOLLOW_EQUIVALENCE_CONC_in_simple_statement351); 
                            EQUIVALENCE_CONC61_tree = (Object)adaptor.create(EQUIVALENCE_CONC61);
                            root_0 = (Object)adaptor.becomeRoot(EQUIVALENCE_CONC61_tree, root_0);


                            }
                            break;

                    }

                    pushFollow(FOLLOW_term_in_simple_statement355);
                    term62=term();
                    _fsp--;

                    adaptor.addChild(root_0, term62.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end simple_statement

    public static class term_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start term
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:56:1: term : difference ( ( AMPERSAND | BAR ) difference )* ;
    public final term_return term() throws RecognitionException {
        term_return retval = new term_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token AMPERSAND64=null;
        Token BAR65=null;
        difference_return difference63 = null;

        difference_return difference66 = null;


        Object AMPERSAND64_tree=null;
        Object BAR65_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:56:7: ( difference ( ( AMPERSAND | BAR ) difference )* )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:56:9: difference ( ( AMPERSAND | BAR ) difference )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_difference_in_term371);
            difference63=difference();
            _fsp--;

            adaptor.addChild(root_0, difference63.getTree());
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:56:20: ( ( AMPERSAND | BAR ) difference )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>=AMPERSAND && LA14_0<=BAR)) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:56:21: ( AMPERSAND | BAR ) difference
            	    {
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:56:21: ( AMPERSAND | BAR )
            	    int alt13=2;
            	    int LA13_0 = input.LA(1);

            	    if ( (LA13_0==AMPERSAND) ) {
            	        alt13=1;
            	    }
            	    else if ( (LA13_0==BAR) ) {
            	        alt13=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("56:21: ( AMPERSAND | BAR )", 13, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt13) {
            	        case 1 :
            	            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:56:22: AMPERSAND
            	            {
            	            AMPERSAND64=(Token)input.LT(1);
            	            match(input,AMPERSAND,FOLLOW_AMPERSAND_in_term375); 
            	            AMPERSAND64_tree = (Object)adaptor.create(AMPERSAND64);
            	            root_0 = (Object)adaptor.becomeRoot(AMPERSAND64_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:56:35: BAR
            	            {
            	            BAR65=(Token)input.LT(1);
            	            match(input,BAR,FOLLOW_BAR_in_term380); 
            	            BAR65_tree = (Object)adaptor.create(BAR65);
            	            root_0 = (Object)adaptor.becomeRoot(BAR65_tree, root_0);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_difference_in_term384);
            	    difference66=difference();
            	    _fsp--;

            	    adaptor.addChild(root_0, difference66.getTree());

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end term

    public static class ext_set_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start ext_set
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:59:1: ext_set : OPEN_BRACE ( term ( COMMA term )* )? CLOSE_BRACE ;
    public final ext_set_return ext_set() throws RecognitionException {
        ext_set_return retval = new ext_set_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token OPEN_BRACE67=null;
        Token COMMA69=null;
        Token CLOSE_BRACE71=null;
        term_return term68 = null;

        term_return term70 = null;


        Object OPEN_BRACE67_tree=null;
        Object COMMA69_tree=null;
        Object CLOSE_BRACE71_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:59:9: ( OPEN_BRACE ( term ( COMMA term )* )? CLOSE_BRACE )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:59:11: OPEN_BRACE ( term ( COMMA term )* )? CLOSE_BRACE
            {
            root_0 = (Object)adaptor.nil();

            OPEN_BRACE67=(Token)input.LT(1);
            match(input,OPEN_BRACE,FOLLOW_OPEN_BRACE_in_ext_set396); 
            OPEN_BRACE67_tree = (Object)adaptor.create(OPEN_BRACE67);
            root_0 = (Object)adaptor.becomeRoot(OPEN_BRACE67_tree, root_0);

            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:59:23: ( term ( COMMA term )* )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==IRI_REF||LA16_0==PNAME_NS||(LA16_0>=LPAREN && LA16_0<=INTEGER)||LA16_0==DECIMAL||LA16_0==OPEN_BRACE||LA16_0==LBRACKET||(LA16_0>=QUERY_VAR && LA16_0<=PNAME_LN)) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:59:24: term ( COMMA term )*
                    {
                    pushFollow(FOLLOW_term_in_ext_set400);
                    term68=term();
                    _fsp--;

                    adaptor.addChild(root_0, term68.getTree());
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:59:29: ( COMMA term )*
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==COMMA) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:59:30: COMMA term
                    	    {
                    	    COMMA69=(Token)input.LT(1);
                    	    match(input,COMMA,FOLLOW_COMMA_in_ext_set403); 
                    	    pushFollow(FOLLOW_term_in_ext_set406);
                    	    term70=term();
                    	    _fsp--;

                    	    adaptor.addChild(root_0, term70.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop15;
                        }
                    } while (true);


                    }
                    break;

            }

            CLOSE_BRACE71=(Token)input.LT(1);
            match(input,CLOSE_BRACE,FOLLOW_CLOSE_BRACE_in_ext_set412); 

            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end ext_set

    public static class int_set_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start int_set
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:62:1: int_set : LBRACKET ( term ( COMMA term )* )? RBRACKET ;
    public final int_set_return int_set() throws RecognitionException {
        int_set_return retval = new int_set_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LBRACKET72=null;
        Token COMMA74=null;
        Token RBRACKET76=null;
        term_return term73 = null;

        term_return term75 = null;


        Object LBRACKET72_tree=null;
        Object COMMA74_tree=null;
        Object RBRACKET76_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:62:9: ( LBRACKET ( term ( COMMA term )* )? RBRACKET )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:62:11: LBRACKET ( term ( COMMA term )* )? RBRACKET
            {
            root_0 = (Object)adaptor.nil();

            LBRACKET72=(Token)input.LT(1);
            match(input,LBRACKET,FOLLOW_LBRACKET_in_int_set424); 
            LBRACKET72_tree = (Object)adaptor.create(LBRACKET72);
            root_0 = (Object)adaptor.becomeRoot(LBRACKET72_tree, root_0);

            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:62:21: ( term ( COMMA term )* )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==IRI_REF||LA18_0==PNAME_NS||(LA18_0>=LPAREN && LA18_0<=INTEGER)||LA18_0==DECIMAL||LA18_0==OPEN_BRACE||LA18_0==LBRACKET||(LA18_0>=QUERY_VAR && LA18_0<=PNAME_LN)) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:62:22: term ( COMMA term )*
                    {
                    pushFollow(FOLLOW_term_in_int_set428);
                    term73=term();
                    _fsp--;

                    adaptor.addChild(root_0, term73.getTree());
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:62:27: ( COMMA term )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==COMMA) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:62:28: COMMA term
                    	    {
                    	    COMMA74=(Token)input.LT(1);
                    	    match(input,COMMA,FOLLOW_COMMA_in_int_set431); 
                    	    pushFollow(FOLLOW_term_in_int_set434);
                    	    term75=term();
                    	    _fsp--;

                    	    adaptor.addChild(root_0, term75.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);


                    }
                    break;

            }

            RBRACKET76=(Token)input.LT(1);
            match(input,RBRACKET,FOLLOW_RBRACKET_in_int_set440); 

            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end int_set

    public static class difference_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start difference
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:65:1: difference : product ( ( MINUS | TILDE ) product )* ;
    public final difference_return difference() throws RecognitionException {
        difference_return retval = new difference_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token MINUS78=null;
        Token TILDE79=null;
        product_return product77 = null;

        product_return product80 = null;


        Object MINUS78_tree=null;
        Object TILDE79_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:66:2: ( product ( ( MINUS | TILDE ) product )* )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:66:4: product ( ( MINUS | TILDE ) product )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_product_in_difference454);
            product77=product();
            _fsp--;

            adaptor.addChild(root_0, product77.getTree());
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:66:12: ( ( MINUS | TILDE ) product )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( ((LA20_0>=MINUS && LA20_0<=TILDE)) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:66:13: ( MINUS | TILDE ) product
            	    {
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:66:13: ( MINUS | TILDE )
            	    int alt19=2;
            	    int LA19_0 = input.LA(1);

            	    if ( (LA19_0==MINUS) ) {
            	        alt19=1;
            	    }
            	    else if ( (LA19_0==TILDE) ) {
            	        alt19=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("66:13: ( MINUS | TILDE )", 19, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt19) {
            	        case 1 :
            	            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:66:14: MINUS
            	            {
            	            MINUS78=(Token)input.LT(1);
            	            match(input,MINUS,FOLLOW_MINUS_in_difference458); 
            	            MINUS78_tree = (Object)adaptor.create(MINUS78);
            	            root_0 = (Object)adaptor.becomeRoot(MINUS78_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:66:23: TILDE
            	            {
            	            TILDE79=(Token)input.LT(1);
            	            match(input,TILDE,FOLLOW_TILDE_in_difference463); 
            	            TILDE79_tree = (Object)adaptor.create(TILDE79);
            	            root_0 = (Object)adaptor.becomeRoot(TILDE79_tree, root_0);


            	            }
            	            break;

            	    }

            	    pushFollow(FOLLOW_product_in_difference467);
            	    product80=product();
            	    _fsp--;

            	    adaptor.addChild(root_0, product80.getTree());

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end difference

    public static class product_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start product
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:69:1: product : atomic_term ( STAR atomic_term )* ;
    public final product_return product() throws RecognitionException {
        product_return retval = new product_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token STAR82=null;
        atomic_term_return atomic_term81 = null;

        atomic_term_return atomic_term83 = null;


        Object STAR82_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:69:9: ( atomic_term ( STAR atomic_term )* )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:69:11: atomic_term ( STAR atomic_term )*
            {
            root_0 = (Object)adaptor.nil();

            pushFollow(FOLLOW_atomic_term_in_product480);
            atomic_term81=atomic_term();
            _fsp--;

            adaptor.addChild(root_0, atomic_term81.getTree());
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:69:23: ( STAR atomic_term )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==STAR) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:69:24: STAR atomic_term
            	    {
            	    STAR82=(Token)input.LT(1);
            	    match(input,STAR,FOLLOW_STAR_in_product483); 
            	    STAR82_tree = (Object)adaptor.create(STAR82);
            	    root_0 = (Object)adaptor.becomeRoot(STAR82_tree, root_0);

            	    pushFollow(FOLLOW_atomic_term_in_product486);
            	    atomic_term83=atomic_term();
            	    _fsp--;

            	    adaptor.addChild(root_0, atomic_term83.getTree());

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end product

    public static class atomic_term_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start atomic_term
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:72:1: atomic_term : ( ext_set | int_set | LPAREN statement RPAREN | variable | iriRef | literal );
    public final atomic_term_return atomic_term() throws RecognitionException {
        atomic_term_return retval = new atomic_term_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token LPAREN86=null;
        Token RPAREN88=null;
        ext_set_return ext_set84 = null;

        int_set_return int_set85 = null;

        statement_return statement87 = null;

        variable_return variable89 = null;

        iriRef_return iriRef90 = null;

        literal_return literal91 = null;


        Object LPAREN86_tree=null;
        Object RPAREN88_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:73:2: ( ext_set | int_set | LPAREN statement RPAREN | variable | iriRef | literal )
            int alt22=6;
            switch ( input.LA(1) ) {
            case OPEN_BRACE:
                {
                alt22=1;
                }
                break;
            case LBRACKET:
                {
                alt22=2;
                }
                break;
            case LPAREN:
                {
                alt22=3;
                }
                break;
            case QUERY_VAR:
            case STM_VAR:
                {
                alt22=4;
                }
                break;
            case IRI_REF:
            case PNAME_NS:
            case PNAME_LN:
                {
                alt22=5;
                }
                break;
            case INTEGER:
            case DECIMAL:
            case DOUBLE:
            case INTEGER_POSITIVE:
            case DECIMAL_POSITIVE:
            case DOUBLE_POSITIVE:
            case INTEGER_NEGATIVE:
            case DECIMAL_NEGATIVE:
            case DOUBLE_NEGATIVE:
            case TRUE:
            case FALSE:
            case STRING_LITERAL1:
            case STRING_LITERAL2:
            case STRING_LITERAL_LONG1:
            case STRING_LITERAL_LONG2:
                {
                alt22=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("72:1: atomic_term : ( ext_set | int_set | LPAREN statement RPAREN | variable | iriRef | literal );", 22, 0, input);

                throw nvae;
            }

            switch (alt22) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:73:4: ext_set
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_ext_set_in_atomic_term500);
                    ext_set84=ext_set();
                    _fsp--;

                    adaptor.addChild(root_0, ext_set84.getTree());

                    }
                    break;
                case 2 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:74:4: int_set
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_int_set_in_atomic_term505);
                    int_set85=int_set();
                    _fsp--;

                    adaptor.addChild(root_0, int_set85.getTree());

                    }
                    break;
                case 3 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:75:4: LPAREN statement RPAREN
                    {
                    root_0 = (Object)adaptor.nil();

                    LPAREN86=(Token)input.LT(1);
                    match(input,LPAREN,FOLLOW_LPAREN_in_atomic_term510); 
                    pushFollow(FOLLOW_statement_in_atomic_term513);
                    statement87=statement();
                    _fsp--;

                    adaptor.addChild(root_0, statement87.getTree());
                    RPAREN88=(Token)input.LT(1);
                    match(input,RPAREN,FOLLOW_RPAREN_in_atomic_term515); 

                    }
                    break;
                case 4 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:76:4: variable
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_variable_in_atomic_term521);
                    variable89=variable();
                    _fsp--;

                    adaptor.addChild(root_0, variable89.getTree());

                    }
                    break;
                case 5 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:77:4: iriRef
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_iriRef_in_atomic_term526);
                    iriRef90=iriRef();
                    _fsp--;

                    adaptor.addChild(root_0, iriRef90.getTree());

                    }
                    break;
                case 6 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:78:4: literal
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_literal_in_atomic_term531);
                    literal91=literal();
                    _fsp--;

                    adaptor.addChild(root_0, literal91.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end atomic_term

    public static class variable_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start variable
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:81:1: variable : ( query_variable | statement_variable );
    public final variable_return variable() throws RecognitionException {
        variable_return retval = new variable_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        query_variable_return query_variable92 = null;

        statement_variable_return statement_variable93 = null;



        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:82:2: ( query_variable | statement_variable )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==QUERY_VAR) ) {
                alt23=1;
            }
            else if ( (LA23_0==STM_VAR) ) {
                alt23=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("81:1: variable : ( query_variable | statement_variable );", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:82:4: query_variable
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_query_variable_in_variable544);
                    query_variable92=query_variable();
                    _fsp--;

                    adaptor.addChild(root_0, query_variable92.getTree());

                    }
                    break;
                case 2 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:83:4: statement_variable
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_statement_variable_in_variable549);
                    statement_variable93=statement_variable();
                    _fsp--;

                    adaptor.addChild(root_0, statement_variable93.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end variable

    public static class query_variable_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start query_variable
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:86:1: query_variable : QUERY_VAR ;
    public final query_variable_return query_variable() throws RecognitionException {
        query_variable_return retval = new query_variable_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token QUERY_VAR94=null;

        Object QUERY_VAR94_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:87:2: ( QUERY_VAR )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:87:4: QUERY_VAR
            {
            root_0 = (Object)adaptor.nil();

            QUERY_VAR94=(Token)input.LT(1);
            match(input,QUERY_VAR,FOLLOW_QUERY_VAR_in_query_variable562); 
            QUERY_VAR94_tree = (Object)adaptor.create(QUERY_VAR94);
            adaptor.addChild(root_0, QUERY_VAR94_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end query_variable

    public static class statement_variable_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start statement_variable
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:90:1: statement_variable : STM_VAR ;
    public final statement_variable_return statement_variable() throws RecognitionException {
        statement_variable_return retval = new statement_variable_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token STM_VAR95=null;

        Object STM_VAR95_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:91:2: ( STM_VAR )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:91:4: STM_VAR
            {
            root_0 = (Object)adaptor.nil();

            STM_VAR95=(Token)input.LT(1);
            match(input,STM_VAR,FOLLOW_STM_VAR_in_statement_variable575); 
            STM_VAR95_tree = (Object)adaptor.create(STM_VAR95);
            adaptor.addChild(root_0, STM_VAR95_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end statement_variable

    public static class literal_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start literal
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:96:1: literal : ( numericLiteral | booleanLiteral | string );
    public final literal_return literal() throws RecognitionException {
        literal_return retval = new literal_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        numericLiteral_return numericLiteral96 = null;

        booleanLiteral_return booleanLiteral97 = null;

        string_return string98 = null;



        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:97:2: ( numericLiteral | booleanLiteral | string )
            int alt24=3;
            switch ( input.LA(1) ) {
            case INTEGER:
            case DECIMAL:
            case DOUBLE:
            case INTEGER_POSITIVE:
            case DECIMAL_POSITIVE:
            case DOUBLE_POSITIVE:
            case INTEGER_NEGATIVE:
            case DECIMAL_NEGATIVE:
            case DOUBLE_NEGATIVE:
                {
                alt24=1;
                }
                break;
            case TRUE:
            case FALSE:
                {
                alt24=2;
                }
                break;
            case STRING_LITERAL1:
            case STRING_LITERAL2:
            case STRING_LITERAL_LONG1:
            case STRING_LITERAL_LONG2:
                {
                alt24=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("96:1: literal : ( numericLiteral | booleanLiteral | string );", 24, 0, input);

                throw nvae;
            }

            switch (alt24) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:97:4: numericLiteral
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_numericLiteral_in_literal591);
                    numericLiteral96=numericLiteral();
                    _fsp--;

                    adaptor.addChild(root_0, numericLiteral96.getTree());

                    }
                    break;
                case 2 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:98:4: booleanLiteral
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_booleanLiteral_in_literal596);
                    booleanLiteral97=booleanLiteral();
                    _fsp--;

                    adaptor.addChild(root_0, booleanLiteral97.getTree());

                    }
                    break;
                case 3 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:99:4: string
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_string_in_literal601);
                    string98=string();
                    _fsp--;

                    adaptor.addChild(root_0, string98.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end literal

    public static class numericLiteral_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start numericLiteral
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:102:1: numericLiteral : ( numericLiteralUnsigned | numericLiteralPositive | numericLiteralNegative );
    public final numericLiteral_return numericLiteral() throws RecognitionException {
        numericLiteral_return retval = new numericLiteral_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        numericLiteralUnsigned_return numericLiteralUnsigned99 = null;

        numericLiteralPositive_return numericLiteralPositive100 = null;

        numericLiteralNegative_return numericLiteralNegative101 = null;



        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:103:5: ( numericLiteralUnsigned | numericLiteralPositive | numericLiteralNegative )
            int alt25=3;
            switch ( input.LA(1) ) {
            case INTEGER:
            case DECIMAL:
            case DOUBLE:
                {
                alt25=1;
                }
                break;
            case INTEGER_POSITIVE:
            case DECIMAL_POSITIVE:
            case DOUBLE_POSITIVE:
                {
                alt25=2;
                }
                break;
            case INTEGER_NEGATIVE:
            case DECIMAL_NEGATIVE:
            case DOUBLE_NEGATIVE:
                {
                alt25=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("102:1: numericLiteral : ( numericLiteralUnsigned | numericLiteralPositive | numericLiteralNegative );", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:103:7: numericLiteralUnsigned
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_numericLiteralUnsigned_in_numericLiteral616);
                    numericLiteralUnsigned99=numericLiteralUnsigned();
                    _fsp--;

                    adaptor.addChild(root_0, numericLiteralUnsigned99.getTree());

                    }
                    break;
                case 2 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:103:32: numericLiteralPositive
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_numericLiteralPositive_in_numericLiteral620);
                    numericLiteralPositive100=numericLiteralPositive();
                    _fsp--;

                    adaptor.addChild(root_0, numericLiteralPositive100.getTree());

                    }
                    break;
                case 3 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:103:57: numericLiteralNegative
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_numericLiteralNegative_in_numericLiteral624);
                    numericLiteralNegative101=numericLiteralNegative();
                    _fsp--;

                    adaptor.addChild(root_0, numericLiteralNegative101.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end numericLiteral

    public static class numericLiteralUnsigned_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start numericLiteralUnsigned
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:106:1: numericLiteralUnsigned : ( INTEGER | DECIMAL | DOUBLE );
    public final numericLiteralUnsigned_return numericLiteralUnsigned() throws RecognitionException {
        numericLiteralUnsigned_return retval = new numericLiteralUnsigned_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set102=null;

        Object set102_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:107:5: ( INTEGER | DECIMAL | DOUBLE )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:
            {
            root_0 = (Object)adaptor.nil();

            set102=(Token)input.LT(1);
            if ( input.LA(1)==INTEGER||input.LA(1)==DECIMAL||input.LA(1)==DOUBLE ) {
                input.consume();
                adaptor.addChild(root_0, adaptor.create(set102));
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_numericLiteralUnsigned0);    throw mse;
            }


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end numericLiteralUnsigned

    public static class numericLiteralPositive_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start numericLiteralPositive
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:112:1: numericLiteralPositive : ( INTEGER_POSITIVE | DECIMAL_POSITIVE | DOUBLE_POSITIVE );
    public final numericLiteralPositive_return numericLiteralPositive() throws RecognitionException {
        numericLiteralPositive_return retval = new numericLiteralPositive_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set103=null;

        Object set103_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:113:5: ( INTEGER_POSITIVE | DECIMAL_POSITIVE | DOUBLE_POSITIVE )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:
            {
            root_0 = (Object)adaptor.nil();

            set103=(Token)input.LT(1);
            if ( (input.LA(1)>=INTEGER_POSITIVE && input.LA(1)<=DOUBLE_POSITIVE) ) {
                input.consume();
                adaptor.addChild(root_0, adaptor.create(set103));
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_numericLiteralPositive0);    throw mse;
            }


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end numericLiteralPositive

    public static class numericLiteralNegative_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start numericLiteralNegative
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:118:1: numericLiteralNegative : ( INTEGER_NEGATIVE | DECIMAL_NEGATIVE | DOUBLE_NEGATIVE );
    public final numericLiteralNegative_return numericLiteralNegative() throws RecognitionException {
        numericLiteralNegative_return retval = new numericLiteralNegative_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set104=null;

        Object set104_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:119:5: ( INTEGER_NEGATIVE | DECIMAL_NEGATIVE | DOUBLE_NEGATIVE )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:
            {
            root_0 = (Object)adaptor.nil();

            set104=(Token)input.LT(1);
            if ( (input.LA(1)>=INTEGER_NEGATIVE && input.LA(1)<=DOUBLE_NEGATIVE) ) {
                input.consume();
                adaptor.addChild(root_0, adaptor.create(set104));
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_numericLiteralNegative0);    throw mse;
            }


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end numericLiteralNegative

    public static class booleanLiteral_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start booleanLiteral
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:124:1: booleanLiteral : ( TRUE | FALSE );
    public final booleanLiteral_return booleanLiteral() throws RecognitionException {
        booleanLiteral_return retval = new booleanLiteral_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set105=null;

        Object set105_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:125:5: ( TRUE | FALSE )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:
            {
            root_0 = (Object)adaptor.nil();

            set105=(Token)input.LT(1);
            if ( (input.LA(1)>=TRUE && input.LA(1)<=FALSE) ) {
                input.consume();
                adaptor.addChild(root_0, adaptor.create(set105));
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_booleanLiteral0);    throw mse;
            }


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end booleanLiteral

    public static class string_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start string
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:129:1: string : ( STRING_LITERAL1 | STRING_LITERAL2 | STRING_LITERAL_LONG1 | STRING_LITERAL_LONG2 );
    public final string_return string() throws RecognitionException {
        string_return retval = new string_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set106=null;

        Object set106_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:130:5: ( STRING_LITERAL1 | STRING_LITERAL2 | STRING_LITERAL_LONG1 | STRING_LITERAL_LONG2 )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:
            {
            root_0 = (Object)adaptor.nil();

            set106=(Token)input.LT(1);
            if ( (input.LA(1)>=STRING_LITERAL1 && input.LA(1)<=STRING_LITERAL_LONG2) ) {
                input.consume();
                adaptor.addChild(root_0, adaptor.create(set106));
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_string0);    throw mse;
            }


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end string

    public static class iriRef_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start iriRef
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:136:1: iriRef : ( IRI_REF | prefixedName );
    public final iriRef_return iriRef() throws RecognitionException {
        iriRef_return retval = new iriRef_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token IRI_REF107=null;
        prefixedName_return prefixedName108 = null;


        Object IRI_REF107_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:137:5: ( IRI_REF | prefixedName )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==IRI_REF) ) {
                alt26=1;
            }
            else if ( (LA26_0==PNAME_NS||LA26_0==PNAME_LN) ) {
                alt26=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("136:1: iriRef : ( IRI_REF | prefixedName );", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:137:7: IRI_REF
                    {
                    root_0 = (Object)adaptor.nil();

                    IRI_REF107=(Token)input.LT(1);
                    match(input,IRI_REF,FOLLOW_IRI_REF_in_iriRef806); 
                    IRI_REF107_tree = (Object)adaptor.create(IRI_REF107);
                    adaptor.addChild(root_0, IRI_REF107_tree);


                    }
                    break;
                case 2 :
                    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:138:7: prefixedName
                    {
                    root_0 = (Object)adaptor.nil();

                    pushFollow(FOLLOW_prefixedName_in_iriRef814);
                    prefixedName108=prefixedName();
                    _fsp--;

                    adaptor.addChild(root_0, prefixedName108.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end iriRef

    public static class prefixedName_return extends ParserRuleReturnScope {
        Object tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start prefixedName
    // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:141:1: prefixedName : ( PNAME_LN | PNAME_NS );
    public final prefixedName_return prefixedName() throws RecognitionException {
        prefixedName_return retval = new prefixedName_return();
        retval.start = input.LT(1);

        Object root_0 = null;

        Token set109=null;

        Object set109_tree=null;

        try {
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:142:5: ( PNAME_LN | PNAME_NS )
            // /Users/jgeldart/Documents/Eclipse/open-nars/com/googlecode/opennars/parser/loan/loan.g:
            {
            root_0 = (Object)adaptor.nil();

            set109=(Token)input.LT(1);
            if ( input.LA(1)==PNAME_NS||input.LA(1)==PNAME_LN ) {
                input.consume();
                adaptor.addChild(root_0, adaptor.create(set109));
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_prefixedName0);    throw mse;
            }


            }

            retval.stop = input.LT(-1);

                retval.tree = (Object)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end prefixedName


 

    public static final BitSet FOLLOW_base_rule_in_document27 = new BitSet(new long[]{0x7FFF850003C21FA0L});
    public static final BitSet FOLLOW_at_rule_in_document31 = new BitSet(new long[]{0x7FFF850003C21FA0L});
    public static final BitSet FOLLOW_sentence_in_document35 = new BitSet(new long[]{0x7FFF850003C21FA0L});
    public static final BitSet FOLLOW_EOF_in_document39 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_BASE_in_base_rule50 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IRI_REF_in_base_rule53 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_base_rule55 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_IMPORT_in_at_rule66 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IRI_REF_in_at_rule69 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_at_rule71 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_PREFIX_in_at_rule77 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_PNAME_NS_in_at_rule80 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_IRI_REF_in_at_rule82 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_at_rule84 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_AT_DELAY_in_at_rule90 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_LPAREN_in_at_rule93 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_INTEGER_in_at_rule96 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_at_rule98 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_DOT_in_at_rule101 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_in_sentence114 = new BitSet(new long[]{0x000000000000C040L});
    public static final BitSet FOLLOW_judgement_in_sentence117 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_question_in_sentence120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_goal_in_sentence123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DOT_in_judgement138 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_truthvalue_in_judgement141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXCLAMATION_in_goal153 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_truthvalue_in_goal156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUESTION_in_question170 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PERCENT_in_truthvalue184 = new BitSet(new long[]{0x0000000000021000L});
    public static final BitSet FOLLOW_set_in_truthvalue187 = new BitSet(new long[]{0x0000000000050000L});
    public static final BitSet FOLLOW_SEMICOLON_in_truthvalue196 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_DECIMAL_in_truthvalue198 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_PERCENT_in_truthvalue202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_statement_in_statement213 = new BitSet(new long[]{0x00000000003C0002L});
    public static final BitSet FOLLOW_CONJ_in_statement217 = new BitSet(new long[]{0x7FFF850003C21A20L});
    public static final BitSet FOLLOW_SEMICOLON_in_statement222 = new BitSet(new long[]{0x7FFF850003C21A20L});
    public static final BitSet FOLLOW_COMMA_in_statement227 = new BitSet(new long[]{0x7FFF850003C21A20L});
    public static final BitSet FOLLOW_DISJ_in_statement232 = new BitSet(new long[]{0x7FFF850003C21A20L});
    public static final BitSet FOLLOW_unary_statement_in_statement236 = new BitSet(new long[]{0x00000000003C0002L});
    public static final BitSet FOLLOW_NOT_in_unary_statement251 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_simple_statement_in_unary_statement253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PAST_in_unary_statement258 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_simple_statement_in_unary_statement260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PRESENT_in_unary_statement265 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_simple_statement_in_unary_statement267 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUTURE_in_unary_statement272 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_simple_statement_in_unary_statement274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_simple_statement_in_unary_statement279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_simple_statement292 = new BitSet(new long[]{0x0000003FFC000002L});
    public static final BitSet FOLLOW_INHERITANCE_in_simple_statement296 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_SIMILARITY_in_simple_statement301 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_INSTANCE_in_simple_statement306 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_PROPERTY_in_simple_statement311 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_INSTANCE_PROPERTY_in_simple_statement316 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_IMPLICATION_in_simple_statement321 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_IMPLICATION_PRED_in_simple_statement326 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_IMPLICATION_RETRO_in_simple_statement331 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_IMPLICATION_CONC_in_simple_statement336 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_EQUIVALENCE_in_simple_statement341 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_EQUIVALENCE_PRED_in_simple_statement346 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_EQUIVALENCE_CONC_in_simple_statement351 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_term_in_simple_statement355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_difference_in_term371 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_AMPERSAND_in_term375 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_BAR_in_term380 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_difference_in_term384 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_OPEN_BRACE_in_ext_set396 = new BitSet(new long[]{0x7FFF870000021A20L});
    public static final BitSet FOLLOW_term_in_ext_set400 = new BitSet(new long[]{0x0000020000100000L});
    public static final BitSet FOLLOW_COMMA_in_ext_set403 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_term_in_ext_set406 = new BitSet(new long[]{0x0000020000100000L});
    public static final BitSet FOLLOW_CLOSE_BRACE_in_ext_set412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_int_set424 = new BitSet(new long[]{0x7FFF8D0000021A20L});
    public static final BitSet FOLLOW_term_in_int_set428 = new BitSet(new long[]{0x0000080000100000L});
    public static final BitSet FOLLOW_COMMA_in_int_set431 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_term_in_int_set434 = new BitSet(new long[]{0x0000080000100000L});
    public static final BitSet FOLLOW_RBRACKET_in_int_set440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_product_in_difference454 = new BitSet(new long[]{0x0000300000000002L});
    public static final BitSet FOLLOW_MINUS_in_difference458 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_TILDE_in_difference463 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_product_in_difference467 = new BitSet(new long[]{0x0000300000000002L});
    public static final BitSet FOLLOW_atomic_term_in_product480 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_STAR_in_product483 = new BitSet(new long[]{0x7FFF850000021A20L});
    public static final BitSet FOLLOW_atomic_term_in_product486 = new BitSet(new long[]{0x0000400000000002L});
    public static final BitSet FOLLOW_ext_set_in_atomic_term500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_int_set_in_atomic_term505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_atomic_term510 = new BitSet(new long[]{0x7FFF850003C21A20L});
    public static final BitSet FOLLOW_statement_in_atomic_term513 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RPAREN_in_atomic_term515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_in_atomic_term521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_iriRef_in_atomic_term526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_atomic_term531 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_query_variable_in_variable544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_statement_variable_in_variable549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_QUERY_VAR_in_query_variable562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STM_VAR_in_statement_variable575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numericLiteral_in_literal591 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_booleanLiteral_in_literal596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_string_in_literal601 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numericLiteralUnsigned_in_numericLiteral616 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numericLiteralPositive_in_numericLiteral620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_numericLiteralNegative_in_numericLiteral624 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_numericLiteralUnsigned0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_numericLiteralPositive0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_numericLiteralNegative0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_booleanLiteral0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_string0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IRI_REF_in_iriRef806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_prefixedName_in_iriRef814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_prefixedName0 = new BitSet(new long[]{0x0000000000000002L});

}