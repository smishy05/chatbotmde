package org.sctest.scdsl.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.sctest.scdsl.services.SCDslGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSCDslParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'Contract'", "':'", "'Platform'", "'{'", "'Name'", "'List'", "'}'", "'Participant'", "'Creator'", "'T'", "'Asset'", "'Type'", "'Parameter'", "'Identifier'", "'Transaction'", "'Mostrar'", "'Condition'", "'ConditionType'", "'Relationship'", "'From'", "'To'", "'Event'", "'Description'", "'TranRel'", "'Selected'", "'AssetRel'", "'Create'", "'Read'", "'Update'", "'Delete'", "'All'", "'IBM'", "'Azure'", "'Solidity'", "'Pre'", "'Post'"
    };
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int RULE_ID=4;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalSCDslParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalSCDslParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalSCDslParser.tokenNames; }
    public String getGrammarFileName() { return "InternalSCDsl.g"; }



     	private SCDslGrammarAccess grammarAccess;

        public InternalSCDslParser(TokenStream input, SCDslGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "SContract";
       	}

       	@Override
       	protected SCDslGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleSContract"
    // InternalSCDsl.g:65:1: entryRuleSContract returns [EObject current=null] : iv_ruleSContract= ruleSContract EOF ;
    public final EObject entryRuleSContract() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSContract = null;


        try {
            // InternalSCDsl.g:65:50: (iv_ruleSContract= ruleSContract EOF )
            // InternalSCDsl.g:66:2: iv_ruleSContract= ruleSContract EOF
            {
             newCompositeNode(grammarAccess.getSContractRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSContract=ruleSContract();

            state._fsp--;

             current =iv_ruleSContract; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSContract"


    // $ANTLR start "ruleSContract"
    // InternalSCDsl.g:72:1: ruleSContract returns [EObject current=null] : (otherlv_0= 'Contract' otherlv_1= ':' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'Platform' otherlv_4= ':' ( (lv_platform_5_0= ruleTargetPlatform ) ) ( (lv_element_6_0= ruleElement ) )* ) ;
    public final EObject ruleSContract() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Enumerator lv_platform_5_0 = null;

        EObject lv_element_6_0 = null;



        	enterRule();

        try {
            // InternalSCDsl.g:78:2: ( (otherlv_0= 'Contract' otherlv_1= ':' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'Platform' otherlv_4= ':' ( (lv_platform_5_0= ruleTargetPlatform ) ) ( (lv_element_6_0= ruleElement ) )* ) )
            // InternalSCDsl.g:79:2: (otherlv_0= 'Contract' otherlv_1= ':' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'Platform' otherlv_4= ':' ( (lv_platform_5_0= ruleTargetPlatform ) ) ( (lv_element_6_0= ruleElement ) )* )
            {
            // InternalSCDsl.g:79:2: (otherlv_0= 'Contract' otherlv_1= ':' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'Platform' otherlv_4= ':' ( (lv_platform_5_0= ruleTargetPlatform ) ) ( (lv_element_6_0= ruleElement ) )* )
            // InternalSCDsl.g:80:3: otherlv_0= 'Contract' otherlv_1= ':' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'Platform' otherlv_4= ':' ( (lv_platform_5_0= ruleTargetPlatform ) ) ( (lv_element_6_0= ruleElement ) )*
            {
            otherlv_0=(Token)match(input,11,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getSContractAccess().getContractKeyword_0());
            		
            otherlv_1=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getSContractAccess().getColonKeyword_1());
            		
            // InternalSCDsl.g:88:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalSCDsl.g:89:4: (lv_name_2_0= RULE_ID )
            {
            // InternalSCDsl.g:89:4: (lv_name_2_0= RULE_ID )
            // InternalSCDsl.g:90:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_5); 

            					newLeafNode(lv_name_2_0, grammarAccess.getSContractAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSContractRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,13,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getSContractAccess().getPlatformKeyword_3());
            		
            otherlv_4=(Token)match(input,12,FOLLOW_6); 

            			newLeafNode(otherlv_4, grammarAccess.getSContractAccess().getColonKeyword_4());
            		
            // InternalSCDsl.g:114:3: ( (lv_platform_5_0= ruleTargetPlatform ) )
            // InternalSCDsl.g:115:4: (lv_platform_5_0= ruleTargetPlatform )
            {
            // InternalSCDsl.g:115:4: (lv_platform_5_0= ruleTargetPlatform )
            // InternalSCDsl.g:116:5: lv_platform_5_0= ruleTargetPlatform
            {

            					newCompositeNode(grammarAccess.getSContractAccess().getPlatformTargetPlatformEnumRuleCall_5_0());
            				
            pushFollow(FOLLOW_7);
            lv_platform_5_0=ruleTargetPlatform();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSContractRule());
            					}
            					set(
            						current,
            						"platform",
            						lv_platform_5_0,
            						"org.sctest.scdsl.SCDsl.TargetPlatform");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalSCDsl.g:133:3: ( (lv_element_6_0= ruleElement ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==18||LA1_0==21||LA1_0==25) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalSCDsl.g:134:4: (lv_element_6_0= ruleElement )
            	    {
            	    // InternalSCDsl.g:134:4: (lv_element_6_0= ruleElement )
            	    // InternalSCDsl.g:135:5: lv_element_6_0= ruleElement
            	    {

            	    					newCompositeNode(grammarAccess.getSContractAccess().getElementElementParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_7);
            	    lv_element_6_0=ruleElement();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getSContractRule());
            	    					}
            	    					add(
            	    						current,
            	    						"element",
            	    						lv_element_6_0,
            	    						"org.sctest.scdsl.SCDsl.Element");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSContract"


    // $ANTLR start "entryRuleElement"
    // InternalSCDsl.g:156:1: entryRuleElement returns [EObject current=null] : iv_ruleElement= ruleElement EOF ;
    public final EObject entryRuleElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElement = null;


        try {
            // InternalSCDsl.g:156:48: (iv_ruleElement= ruleElement EOF )
            // InternalSCDsl.g:157:2: iv_ruleElement= ruleElement EOF
            {
             newCompositeNode(grammarAccess.getElementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleElement=ruleElement();

            state._fsp--;

             current =iv_ruleElement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleElement"


    // $ANTLR start "ruleElement"
    // InternalSCDsl.g:163:1: ruleElement returns [EObject current=null] : ( (this_Participant_0= ruleParticipant | this_Asset_1= ruleAsset | this_Transaction_2= ruleTransaction ) otherlv_3= '{' otherlv_4= 'Name' otherlv_5= ':' ( (lv_name_6_0= RULE_ID ) ) otherlv_7= 'List' otherlv_8= ':' ( (lv_list_9_0= RULE_ID ) ) ( (lv_relationship_10_0= ruleRelationship ) )* ( (lv_compositeelement_11_0= ruleCompositeElement ) )* ( (lv_parameters_12_0= ruleParameter ) )* otherlv_13= '}' ) ;
    public final EObject ruleElement() throws RecognitionException {
        EObject current = null;

        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token lv_name_6_0=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token lv_list_9_0=null;
        Token otherlv_13=null;
        EObject this_Participant_0 = null;

        EObject this_Asset_1 = null;

        EObject this_Transaction_2 = null;

        EObject lv_relationship_10_0 = null;

        EObject lv_compositeelement_11_0 = null;

        EObject lv_parameters_12_0 = null;



        	enterRule();

        try {
            // InternalSCDsl.g:169:2: ( ( (this_Participant_0= ruleParticipant | this_Asset_1= ruleAsset | this_Transaction_2= ruleTransaction ) otherlv_3= '{' otherlv_4= 'Name' otherlv_5= ':' ( (lv_name_6_0= RULE_ID ) ) otherlv_7= 'List' otherlv_8= ':' ( (lv_list_9_0= RULE_ID ) ) ( (lv_relationship_10_0= ruleRelationship ) )* ( (lv_compositeelement_11_0= ruleCompositeElement ) )* ( (lv_parameters_12_0= ruleParameter ) )* otherlv_13= '}' ) )
            // InternalSCDsl.g:170:2: ( (this_Participant_0= ruleParticipant | this_Asset_1= ruleAsset | this_Transaction_2= ruleTransaction ) otherlv_3= '{' otherlv_4= 'Name' otherlv_5= ':' ( (lv_name_6_0= RULE_ID ) ) otherlv_7= 'List' otherlv_8= ':' ( (lv_list_9_0= RULE_ID ) ) ( (lv_relationship_10_0= ruleRelationship ) )* ( (lv_compositeelement_11_0= ruleCompositeElement ) )* ( (lv_parameters_12_0= ruleParameter ) )* otherlv_13= '}' )
            {
            // InternalSCDsl.g:170:2: ( (this_Participant_0= ruleParticipant | this_Asset_1= ruleAsset | this_Transaction_2= ruleTransaction ) otherlv_3= '{' otherlv_4= 'Name' otherlv_5= ':' ( (lv_name_6_0= RULE_ID ) ) otherlv_7= 'List' otherlv_8= ':' ( (lv_list_9_0= RULE_ID ) ) ( (lv_relationship_10_0= ruleRelationship ) )* ( (lv_compositeelement_11_0= ruleCompositeElement ) )* ( (lv_parameters_12_0= ruleParameter ) )* otherlv_13= '}' )
            // InternalSCDsl.g:171:3: (this_Participant_0= ruleParticipant | this_Asset_1= ruleAsset | this_Transaction_2= ruleTransaction ) otherlv_3= '{' otherlv_4= 'Name' otherlv_5= ':' ( (lv_name_6_0= RULE_ID ) ) otherlv_7= 'List' otherlv_8= ':' ( (lv_list_9_0= RULE_ID ) ) ( (lv_relationship_10_0= ruleRelationship ) )* ( (lv_compositeelement_11_0= ruleCompositeElement ) )* ( (lv_parameters_12_0= ruleParameter ) )* otherlv_13= '}'
            {
            // InternalSCDsl.g:171:3: (this_Participant_0= ruleParticipant | this_Asset_1= ruleAsset | this_Transaction_2= ruleTransaction )
            int alt2=3;
            switch ( input.LA(1) ) {
            case 18:
                {
                alt2=1;
                }
                break;
            case 21:
                {
                alt2=2;
                }
                break;
            case 25:
                {
                alt2=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalSCDsl.g:172:4: this_Participant_0= ruleParticipant
                    {

                    				newCompositeNode(grammarAccess.getElementAccess().getParticipantParserRuleCall_0_0());
                    			
                    pushFollow(FOLLOW_8);
                    this_Participant_0=ruleParticipant();

                    state._fsp--;


                    				current = this_Participant_0;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 2 :
                    // InternalSCDsl.g:181:4: this_Asset_1= ruleAsset
                    {

                    				newCompositeNode(grammarAccess.getElementAccess().getAssetParserRuleCall_0_1());
                    			
                    pushFollow(FOLLOW_8);
                    this_Asset_1=ruleAsset();

                    state._fsp--;


                    				current = this_Asset_1;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 3 :
                    // InternalSCDsl.g:190:4: this_Transaction_2= ruleTransaction
                    {

                    				newCompositeNode(grammarAccess.getElementAccess().getTransactionParserRuleCall_0_2());
                    			
                    pushFollow(FOLLOW_8);
                    this_Transaction_2=ruleTransaction();

                    state._fsp--;


                    				current = this_Transaction_2;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;

            }

            otherlv_3=(Token)match(input,14,FOLLOW_9); 

            			newLeafNode(otherlv_3, grammarAccess.getElementAccess().getLeftCurlyBracketKeyword_1());
            		
            otherlv_4=(Token)match(input,15,FOLLOW_3); 

            			newLeafNode(otherlv_4, grammarAccess.getElementAccess().getNameKeyword_2());
            		
            otherlv_5=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_5, grammarAccess.getElementAccess().getColonKeyword_3());
            		
            // InternalSCDsl.g:211:3: ( (lv_name_6_0= RULE_ID ) )
            // InternalSCDsl.g:212:4: (lv_name_6_0= RULE_ID )
            {
            // InternalSCDsl.g:212:4: (lv_name_6_0= RULE_ID )
            // InternalSCDsl.g:213:5: lv_name_6_0= RULE_ID
            {
            lv_name_6_0=(Token)match(input,RULE_ID,FOLLOW_10); 

            					newLeafNode(lv_name_6_0, grammarAccess.getElementAccess().getNameIDTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getElementRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_6_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_7=(Token)match(input,16,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getElementAccess().getListKeyword_5());
            		
            otherlv_8=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_8, grammarAccess.getElementAccess().getColonKeyword_6());
            		
            // InternalSCDsl.g:237:3: ( (lv_list_9_0= RULE_ID ) )
            // InternalSCDsl.g:238:4: (lv_list_9_0= RULE_ID )
            {
            // InternalSCDsl.g:238:4: (lv_list_9_0= RULE_ID )
            // InternalSCDsl.g:239:5: lv_list_9_0= RULE_ID
            {
            lv_list_9_0=(Token)match(input,RULE_ID,FOLLOW_11); 

            					newLeafNode(lv_list_9_0, grammarAccess.getElementAccess().getListIDTerminalRuleCall_7_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getElementRule());
            					}
            					setWithLastConsumed(
            						current,
            						"list",
            						lv_list_9_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalSCDsl.g:255:3: ( (lv_relationship_10_0= ruleRelationship ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==29) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalSCDsl.g:256:4: (lv_relationship_10_0= ruleRelationship )
            	    {
            	    // InternalSCDsl.g:256:4: (lv_relationship_10_0= ruleRelationship )
            	    // InternalSCDsl.g:257:5: lv_relationship_10_0= ruleRelationship
            	    {

            	    					newCompositeNode(grammarAccess.getElementAccess().getRelationshipRelationshipParserRuleCall_8_0());
            	    				
            	    pushFollow(FOLLOW_11);
            	    lv_relationship_10_0=ruleRelationship();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getElementRule());
            	    					}
            	    					add(
            	    						current,
            	    						"relationship",
            	    						lv_relationship_10_0,
            	    						"org.sctest.scdsl.SCDsl.Relationship");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // InternalSCDsl.g:274:3: ( (lv_compositeelement_11_0= ruleCompositeElement ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==15) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalSCDsl.g:275:4: (lv_compositeelement_11_0= ruleCompositeElement )
            	    {
            	    // InternalSCDsl.g:275:4: (lv_compositeelement_11_0= ruleCompositeElement )
            	    // InternalSCDsl.g:276:5: lv_compositeelement_11_0= ruleCompositeElement
            	    {

            	    					newCompositeNode(grammarAccess.getElementAccess().getCompositeelementCompositeElementParserRuleCall_9_0());
            	    				
            	    pushFollow(FOLLOW_12);
            	    lv_compositeelement_11_0=ruleCompositeElement();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getElementRule());
            	    					}
            	    					add(
            	    						current,
            	    						"compositeelement",
            	    						lv_compositeelement_11_0,
            	    						"org.sctest.scdsl.SCDsl.CompositeElement");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // InternalSCDsl.g:293:3: ( (lv_parameters_12_0= ruleParameter ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==23) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalSCDsl.g:294:4: (lv_parameters_12_0= ruleParameter )
            	    {
            	    // InternalSCDsl.g:294:4: (lv_parameters_12_0= ruleParameter )
            	    // InternalSCDsl.g:295:5: lv_parameters_12_0= ruleParameter
            	    {

            	    					newCompositeNode(grammarAccess.getElementAccess().getParametersParameterParserRuleCall_10_0());
            	    				
            	    pushFollow(FOLLOW_13);
            	    lv_parameters_12_0=ruleParameter();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getElementRule());
            	    					}
            	    					add(
            	    						current,
            	    						"parameters",
            	    						lv_parameters_12_0,
            	    						"org.sctest.scdsl.SCDsl.Parameter");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            otherlv_13=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_13, grammarAccess.getElementAccess().getRightCurlyBracketKeyword_11());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleElement"


    // $ANTLR start "entryRuleCompositeElement"
    // InternalSCDsl.g:320:1: entryRuleCompositeElement returns [EObject current=null] : iv_ruleCompositeElement= ruleCompositeElement EOF ;
    public final EObject entryRuleCompositeElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCompositeElement = null;


        try {
            // InternalSCDsl.g:320:57: (iv_ruleCompositeElement= ruleCompositeElement EOF )
            // InternalSCDsl.g:321:2: iv_ruleCompositeElement= ruleCompositeElement EOF
            {
             newCompositeNode(grammarAccess.getCompositeElementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCompositeElement=ruleCompositeElement();

            state._fsp--;

             current =iv_ruleCompositeElement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCompositeElement"


    // $ANTLR start "ruleCompositeElement"
    // InternalSCDsl.g:327:1: ruleCompositeElement returns [EObject current=null] : (otherlv_0= 'Name' otherlv_1= ':' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'List' otherlv_4= ':' ( (lv_list_5_0= RULE_ID ) ) ) ;
    public final EObject ruleCompositeElement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_list_5_0=null;


        	enterRule();

        try {
            // InternalSCDsl.g:333:2: ( (otherlv_0= 'Name' otherlv_1= ':' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'List' otherlv_4= ':' ( (lv_list_5_0= RULE_ID ) ) ) )
            // InternalSCDsl.g:334:2: (otherlv_0= 'Name' otherlv_1= ':' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'List' otherlv_4= ':' ( (lv_list_5_0= RULE_ID ) ) )
            {
            // InternalSCDsl.g:334:2: (otherlv_0= 'Name' otherlv_1= ':' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'List' otherlv_4= ':' ( (lv_list_5_0= RULE_ID ) ) )
            // InternalSCDsl.g:335:3: otherlv_0= 'Name' otherlv_1= ':' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= 'List' otherlv_4= ':' ( (lv_list_5_0= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,15,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getCompositeElementAccess().getNameKeyword_0());
            		
            otherlv_1=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getCompositeElementAccess().getColonKeyword_1());
            		
            // InternalSCDsl.g:343:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalSCDsl.g:344:4: (lv_name_2_0= RULE_ID )
            {
            // InternalSCDsl.g:344:4: (lv_name_2_0= RULE_ID )
            // InternalSCDsl.g:345:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_10); 

            					newLeafNode(lv_name_2_0, grammarAccess.getCompositeElementAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCompositeElementRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,16,FOLLOW_3); 

            			newLeafNode(otherlv_3, grammarAccess.getCompositeElementAccess().getListKeyword_3());
            		
            otherlv_4=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getCompositeElementAccess().getColonKeyword_4());
            		
            // InternalSCDsl.g:369:3: ( (lv_list_5_0= RULE_ID ) )
            // InternalSCDsl.g:370:4: (lv_list_5_0= RULE_ID )
            {
            // InternalSCDsl.g:370:4: (lv_list_5_0= RULE_ID )
            // InternalSCDsl.g:371:5: lv_list_5_0= RULE_ID
            {
            lv_list_5_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_list_5_0, grammarAccess.getCompositeElementAccess().getListIDTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCompositeElementRule());
            					}
            					setWithLastConsumed(
            						current,
            						"list",
            						lv_list_5_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCompositeElement"


    // $ANTLR start "entryRuleParticipant"
    // InternalSCDsl.g:391:1: entryRuleParticipant returns [EObject current=null] : iv_ruleParticipant= ruleParticipant EOF ;
    public final EObject entryRuleParticipant() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParticipant = null;


        try {
            // InternalSCDsl.g:391:52: (iv_ruleParticipant= ruleParticipant EOF )
            // InternalSCDsl.g:392:2: iv_ruleParticipant= ruleParticipant EOF
            {
             newCompositeNode(grammarAccess.getParticipantRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParticipant=ruleParticipant();

            state._fsp--;

             current =iv_ruleParticipant; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParticipant"


    // $ANTLR start "ruleParticipant"
    // InternalSCDsl.g:398:1: ruleParticipant returns [EObject current=null] : ( () otherlv_1= 'Participant' (otherlv_2= 'Creator' otherlv_3= ':' ( (lv_creator_4_0= 'T' ) ) )? ) ;
    public final EObject ruleParticipant() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_creator_4_0=null;


        	enterRule();

        try {
            // InternalSCDsl.g:404:2: ( ( () otherlv_1= 'Participant' (otherlv_2= 'Creator' otherlv_3= ':' ( (lv_creator_4_0= 'T' ) ) )? ) )
            // InternalSCDsl.g:405:2: ( () otherlv_1= 'Participant' (otherlv_2= 'Creator' otherlv_3= ':' ( (lv_creator_4_0= 'T' ) ) )? )
            {
            // InternalSCDsl.g:405:2: ( () otherlv_1= 'Participant' (otherlv_2= 'Creator' otherlv_3= ':' ( (lv_creator_4_0= 'T' ) ) )? )
            // InternalSCDsl.g:406:3: () otherlv_1= 'Participant' (otherlv_2= 'Creator' otherlv_3= ':' ( (lv_creator_4_0= 'T' ) ) )?
            {
            // InternalSCDsl.g:406:3: ()
            // InternalSCDsl.g:407:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getParticipantAccess().getParticipantAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,18,FOLLOW_14); 

            			newLeafNode(otherlv_1, grammarAccess.getParticipantAccess().getParticipantKeyword_1());
            		
            // InternalSCDsl.g:417:3: (otherlv_2= 'Creator' otherlv_3= ':' ( (lv_creator_4_0= 'T' ) ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==19) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalSCDsl.g:418:4: otherlv_2= 'Creator' otherlv_3= ':' ( (lv_creator_4_0= 'T' ) )
                    {
                    otherlv_2=(Token)match(input,19,FOLLOW_3); 

                    				newLeafNode(otherlv_2, grammarAccess.getParticipantAccess().getCreatorKeyword_2_0());
                    			
                    otherlv_3=(Token)match(input,12,FOLLOW_15); 

                    				newLeafNode(otherlv_3, grammarAccess.getParticipantAccess().getColonKeyword_2_1());
                    			
                    // InternalSCDsl.g:426:4: ( (lv_creator_4_0= 'T' ) )
                    // InternalSCDsl.g:427:5: (lv_creator_4_0= 'T' )
                    {
                    // InternalSCDsl.g:427:5: (lv_creator_4_0= 'T' )
                    // InternalSCDsl.g:428:6: lv_creator_4_0= 'T'
                    {
                    lv_creator_4_0=(Token)match(input,20,FOLLOW_2); 

                    						newLeafNode(lv_creator_4_0, grammarAccess.getParticipantAccess().getCreatorTKeyword_2_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getParticipantRule());
                    						}
                    						setWithLastConsumed(current, "creator", lv_creator_4_0 != null, "T");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParticipant"


    // $ANTLR start "entryRuleAsset"
    // InternalSCDsl.g:445:1: entryRuleAsset returns [EObject current=null] : iv_ruleAsset= ruleAsset EOF ;
    public final EObject entryRuleAsset() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAsset = null;


        try {
            // InternalSCDsl.g:445:46: (iv_ruleAsset= ruleAsset EOF )
            // InternalSCDsl.g:446:2: iv_ruleAsset= ruleAsset EOF
            {
             newCompositeNode(grammarAccess.getAssetRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAsset=ruleAsset();

            state._fsp--;

             current =iv_ruleAsset; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAsset"


    // $ANTLR start "ruleAsset"
    // InternalSCDsl.g:452:1: ruleAsset returns [EObject current=null] : (otherlv_0= 'Asset' otherlv_1= 'Type' otherlv_2= ':' ( (lv_type_3_0= RULE_ID ) ) ) ;
    public final EObject ruleAsset() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_type_3_0=null;


        	enterRule();

        try {
            // InternalSCDsl.g:458:2: ( (otherlv_0= 'Asset' otherlv_1= 'Type' otherlv_2= ':' ( (lv_type_3_0= RULE_ID ) ) ) )
            // InternalSCDsl.g:459:2: (otherlv_0= 'Asset' otherlv_1= 'Type' otherlv_2= ':' ( (lv_type_3_0= RULE_ID ) ) )
            {
            // InternalSCDsl.g:459:2: (otherlv_0= 'Asset' otherlv_1= 'Type' otherlv_2= ':' ( (lv_type_3_0= RULE_ID ) ) )
            // InternalSCDsl.g:460:3: otherlv_0= 'Asset' otherlv_1= 'Type' otherlv_2= ':' ( (lv_type_3_0= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,21,FOLLOW_16); 

            			newLeafNode(otherlv_0, grammarAccess.getAssetAccess().getAssetKeyword_0());
            		
            otherlv_1=(Token)match(input,22,FOLLOW_3); 

            			newLeafNode(otherlv_1, grammarAccess.getAssetAccess().getTypeKeyword_1());
            		
            otherlv_2=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getAssetAccess().getColonKeyword_2());
            		
            // InternalSCDsl.g:472:3: ( (lv_type_3_0= RULE_ID ) )
            // InternalSCDsl.g:473:4: (lv_type_3_0= RULE_ID )
            {
            // InternalSCDsl.g:473:4: (lv_type_3_0= RULE_ID )
            // InternalSCDsl.g:474:5: lv_type_3_0= RULE_ID
            {
            lv_type_3_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_type_3_0, grammarAccess.getAssetAccess().getTypeIDTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAssetRule());
            					}
            					setWithLastConsumed(
            						current,
            						"type",
            						lv_type_3_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAsset"


    // $ANTLR start "entryRuleParameter"
    // InternalSCDsl.g:494:1: entryRuleParameter returns [EObject current=null] : iv_ruleParameter= ruleParameter EOF ;
    public final EObject entryRuleParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameter = null;


        try {
            // InternalSCDsl.g:494:50: (iv_ruleParameter= ruleParameter EOF )
            // InternalSCDsl.g:495:2: iv_ruleParameter= ruleParameter EOF
            {
             newCompositeNode(grammarAccess.getParameterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParameter=ruleParameter();

            state._fsp--;

             current =iv_ruleParameter; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameter"


    // $ANTLR start "ruleParameter"
    // InternalSCDsl.g:501:1: ruleParameter returns [EObject current=null] : (otherlv_0= 'Parameter' otherlv_1= '{' otherlv_2= 'Name' otherlv_3= ':' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'Type' otherlv_6= ':' ( (lv_type_7_0= RULE_ID ) ) (otherlv_8= 'Identifier' otherlv_9= ':' ( (lv_identifier_10_0= 'T' ) ) )? otherlv_11= '}' ) ;
    public final EObject ruleParameter() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_name_4_0=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token lv_type_7_0=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token lv_identifier_10_0=null;
        Token otherlv_11=null;


        	enterRule();

        try {
            // InternalSCDsl.g:507:2: ( (otherlv_0= 'Parameter' otherlv_1= '{' otherlv_2= 'Name' otherlv_3= ':' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'Type' otherlv_6= ':' ( (lv_type_7_0= RULE_ID ) ) (otherlv_8= 'Identifier' otherlv_9= ':' ( (lv_identifier_10_0= 'T' ) ) )? otherlv_11= '}' ) )
            // InternalSCDsl.g:508:2: (otherlv_0= 'Parameter' otherlv_1= '{' otherlv_2= 'Name' otherlv_3= ':' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'Type' otherlv_6= ':' ( (lv_type_7_0= RULE_ID ) ) (otherlv_8= 'Identifier' otherlv_9= ':' ( (lv_identifier_10_0= 'T' ) ) )? otherlv_11= '}' )
            {
            // InternalSCDsl.g:508:2: (otherlv_0= 'Parameter' otherlv_1= '{' otherlv_2= 'Name' otherlv_3= ':' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'Type' otherlv_6= ':' ( (lv_type_7_0= RULE_ID ) ) (otherlv_8= 'Identifier' otherlv_9= ':' ( (lv_identifier_10_0= 'T' ) ) )? otherlv_11= '}' )
            // InternalSCDsl.g:509:3: otherlv_0= 'Parameter' otherlv_1= '{' otherlv_2= 'Name' otherlv_3= ':' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'Type' otherlv_6= ':' ( (lv_type_7_0= RULE_ID ) ) (otherlv_8= 'Identifier' otherlv_9= ':' ( (lv_identifier_10_0= 'T' ) ) )? otherlv_11= '}'
            {
            otherlv_0=(Token)match(input,23,FOLLOW_8); 

            			newLeafNode(otherlv_0, grammarAccess.getParameterAccess().getParameterKeyword_0());
            		
            otherlv_1=(Token)match(input,14,FOLLOW_9); 

            			newLeafNode(otherlv_1, grammarAccess.getParameterAccess().getLeftCurlyBracketKeyword_1());
            		
            otherlv_2=(Token)match(input,15,FOLLOW_3); 

            			newLeafNode(otherlv_2, grammarAccess.getParameterAccess().getNameKeyword_2());
            		
            otherlv_3=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_3, grammarAccess.getParameterAccess().getColonKeyword_3());
            		
            // InternalSCDsl.g:525:3: ( (lv_name_4_0= RULE_ID ) )
            // InternalSCDsl.g:526:4: (lv_name_4_0= RULE_ID )
            {
            // InternalSCDsl.g:526:4: (lv_name_4_0= RULE_ID )
            // InternalSCDsl.g:527:5: lv_name_4_0= RULE_ID
            {
            lv_name_4_0=(Token)match(input,RULE_ID,FOLLOW_16); 

            					newLeafNode(lv_name_4_0, grammarAccess.getParameterAccess().getNameIDTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getParameterRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_4_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_5=(Token)match(input,22,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getParameterAccess().getTypeKeyword_5());
            		
            otherlv_6=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_6, grammarAccess.getParameterAccess().getColonKeyword_6());
            		
            // InternalSCDsl.g:551:3: ( (lv_type_7_0= RULE_ID ) )
            // InternalSCDsl.g:552:4: (lv_type_7_0= RULE_ID )
            {
            // InternalSCDsl.g:552:4: (lv_type_7_0= RULE_ID )
            // InternalSCDsl.g:553:5: lv_type_7_0= RULE_ID
            {
            lv_type_7_0=(Token)match(input,RULE_ID,FOLLOW_17); 

            					newLeafNode(lv_type_7_0, grammarAccess.getParameterAccess().getTypeIDTerminalRuleCall_7_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getParameterRule());
            					}
            					setWithLastConsumed(
            						current,
            						"type",
            						lv_type_7_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalSCDsl.g:569:3: (otherlv_8= 'Identifier' otherlv_9= ':' ( (lv_identifier_10_0= 'T' ) ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==24) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalSCDsl.g:570:4: otherlv_8= 'Identifier' otherlv_9= ':' ( (lv_identifier_10_0= 'T' ) )
                    {
                    otherlv_8=(Token)match(input,24,FOLLOW_3); 

                    				newLeafNode(otherlv_8, grammarAccess.getParameterAccess().getIdentifierKeyword_8_0());
                    			
                    otherlv_9=(Token)match(input,12,FOLLOW_15); 

                    				newLeafNode(otherlv_9, grammarAccess.getParameterAccess().getColonKeyword_8_1());
                    			
                    // InternalSCDsl.g:578:4: ( (lv_identifier_10_0= 'T' ) )
                    // InternalSCDsl.g:579:5: (lv_identifier_10_0= 'T' )
                    {
                    // InternalSCDsl.g:579:5: (lv_identifier_10_0= 'T' )
                    // InternalSCDsl.g:580:6: lv_identifier_10_0= 'T'
                    {
                    lv_identifier_10_0=(Token)match(input,20,FOLLOW_18); 

                    						newLeafNode(lv_identifier_10_0, grammarAccess.getParameterAccess().getIdentifierTKeyword_8_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getParameterRule());
                    						}
                    						setWithLastConsumed(current, "identifier", lv_identifier_10_0 != null, "T");
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_11=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_11, grammarAccess.getParameterAccess().getRightCurlyBracketKeyword_9());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameter"


    // $ANTLR start "entryRuleTransaction"
    // InternalSCDsl.g:601:1: entryRuleTransaction returns [EObject current=null] : iv_ruleTransaction= ruleTransaction EOF ;
    public final EObject entryRuleTransaction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTransaction = null;


        try {
            // InternalSCDsl.g:601:52: (iv_ruleTransaction= ruleTransaction EOF )
            // InternalSCDsl.g:602:2: iv_ruleTransaction= ruleTransaction EOF
            {
             newCompositeNode(grammarAccess.getTransactionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTransaction=ruleTransaction();

            state._fsp--;

             current =iv_ruleTransaction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTransaction"


    // $ANTLR start "ruleTransaction"
    // InternalSCDsl.g:608:1: ruleTransaction returns [EObject current=null] : ( () otherlv_1= 'Transaction' (otherlv_2= 'Mostrar' otherlv_3= ':' ( (lv_mostrar_4_0= 'T' ) ) )? otherlv_5= '{' ( (lv_condition_6_0= ruleCondition ) )* otherlv_7= '}' ) ;
    public final EObject ruleTransaction() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_mostrar_4_0=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_condition_6_0 = null;



        	enterRule();

        try {
            // InternalSCDsl.g:614:2: ( ( () otherlv_1= 'Transaction' (otherlv_2= 'Mostrar' otherlv_3= ':' ( (lv_mostrar_4_0= 'T' ) ) )? otherlv_5= '{' ( (lv_condition_6_0= ruleCondition ) )* otherlv_7= '}' ) )
            // InternalSCDsl.g:615:2: ( () otherlv_1= 'Transaction' (otherlv_2= 'Mostrar' otherlv_3= ':' ( (lv_mostrar_4_0= 'T' ) ) )? otherlv_5= '{' ( (lv_condition_6_0= ruleCondition ) )* otherlv_7= '}' )
            {
            // InternalSCDsl.g:615:2: ( () otherlv_1= 'Transaction' (otherlv_2= 'Mostrar' otherlv_3= ':' ( (lv_mostrar_4_0= 'T' ) ) )? otherlv_5= '{' ( (lv_condition_6_0= ruleCondition ) )* otherlv_7= '}' )
            // InternalSCDsl.g:616:3: () otherlv_1= 'Transaction' (otherlv_2= 'Mostrar' otherlv_3= ':' ( (lv_mostrar_4_0= 'T' ) ) )? otherlv_5= '{' ( (lv_condition_6_0= ruleCondition ) )* otherlv_7= '}'
            {
            // InternalSCDsl.g:616:3: ()
            // InternalSCDsl.g:617:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTransactionAccess().getTransactionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,25,FOLLOW_19); 

            			newLeafNode(otherlv_1, grammarAccess.getTransactionAccess().getTransactionKeyword_1());
            		
            // InternalSCDsl.g:627:3: (otherlv_2= 'Mostrar' otherlv_3= ':' ( (lv_mostrar_4_0= 'T' ) ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==26) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalSCDsl.g:628:4: otherlv_2= 'Mostrar' otherlv_3= ':' ( (lv_mostrar_4_0= 'T' ) )
                    {
                    otherlv_2=(Token)match(input,26,FOLLOW_3); 

                    				newLeafNode(otherlv_2, grammarAccess.getTransactionAccess().getMostrarKeyword_2_0());
                    			
                    otherlv_3=(Token)match(input,12,FOLLOW_15); 

                    				newLeafNode(otherlv_3, grammarAccess.getTransactionAccess().getColonKeyword_2_1());
                    			
                    // InternalSCDsl.g:636:4: ( (lv_mostrar_4_0= 'T' ) )
                    // InternalSCDsl.g:637:5: (lv_mostrar_4_0= 'T' )
                    {
                    // InternalSCDsl.g:637:5: (lv_mostrar_4_0= 'T' )
                    // InternalSCDsl.g:638:6: lv_mostrar_4_0= 'T'
                    {
                    lv_mostrar_4_0=(Token)match(input,20,FOLLOW_8); 

                    						newLeafNode(lv_mostrar_4_0, grammarAccess.getTransactionAccess().getMostrarTKeyword_2_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getTransactionRule());
                    						}
                    						setWithLastConsumed(current, "mostrar", lv_mostrar_4_0 != null, "T");
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,14,FOLLOW_20); 

            			newLeafNode(otherlv_5, grammarAccess.getTransactionAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalSCDsl.g:655:3: ( (lv_condition_6_0= ruleCondition ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==27) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalSCDsl.g:656:4: (lv_condition_6_0= ruleCondition )
            	    {
            	    // InternalSCDsl.g:656:4: (lv_condition_6_0= ruleCondition )
            	    // InternalSCDsl.g:657:5: lv_condition_6_0= ruleCondition
            	    {

            	    					newCompositeNode(grammarAccess.getTransactionAccess().getConditionConditionParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_20);
            	    lv_condition_6_0=ruleCondition();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getTransactionRule());
            	    					}
            	    					add(
            	    						current,
            	    						"condition",
            	    						lv_condition_6_0,
            	    						"org.sctest.scdsl.SCDsl.Condition");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            otherlv_7=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_7, grammarAccess.getTransactionAccess().getRightCurlyBracketKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTransaction"


    // $ANTLR start "entryRuleCondition"
    // InternalSCDsl.g:682:1: entryRuleCondition returns [EObject current=null] : iv_ruleCondition= ruleCondition EOF ;
    public final EObject entryRuleCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCondition = null;


        try {
            // InternalSCDsl.g:682:50: (iv_ruleCondition= ruleCondition EOF )
            // InternalSCDsl.g:683:2: iv_ruleCondition= ruleCondition EOF
            {
             newCompositeNode(grammarAccess.getConditionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCondition=ruleCondition();

            state._fsp--;

             current =iv_ruleCondition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCondition"


    // $ANTLR start "ruleCondition"
    // InternalSCDsl.g:689:1: ruleCondition returns [EObject current=null] : (otherlv_0= 'Condition' otherlv_1= '{' otherlv_2= 'Name' otherlv_3= ':' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'Condition' otherlv_6= ':' ( (lv_condition_7_0= RULE_ID ) ) otherlv_8= 'ConditionType' otherlv_9= ':' ( (lv_conditionType_10_0= ruleConditionTypes ) ) otherlv_11= '}' ) ;
    public final EObject ruleCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_name_4_0=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token lv_condition_7_0=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Enumerator lv_conditionType_10_0 = null;



        	enterRule();

        try {
            // InternalSCDsl.g:695:2: ( (otherlv_0= 'Condition' otherlv_1= '{' otherlv_2= 'Name' otherlv_3= ':' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'Condition' otherlv_6= ':' ( (lv_condition_7_0= RULE_ID ) ) otherlv_8= 'ConditionType' otherlv_9= ':' ( (lv_conditionType_10_0= ruleConditionTypes ) ) otherlv_11= '}' ) )
            // InternalSCDsl.g:696:2: (otherlv_0= 'Condition' otherlv_1= '{' otherlv_2= 'Name' otherlv_3= ':' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'Condition' otherlv_6= ':' ( (lv_condition_7_0= RULE_ID ) ) otherlv_8= 'ConditionType' otherlv_9= ':' ( (lv_conditionType_10_0= ruleConditionTypes ) ) otherlv_11= '}' )
            {
            // InternalSCDsl.g:696:2: (otherlv_0= 'Condition' otherlv_1= '{' otherlv_2= 'Name' otherlv_3= ':' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'Condition' otherlv_6= ':' ( (lv_condition_7_0= RULE_ID ) ) otherlv_8= 'ConditionType' otherlv_9= ':' ( (lv_conditionType_10_0= ruleConditionTypes ) ) otherlv_11= '}' )
            // InternalSCDsl.g:697:3: otherlv_0= 'Condition' otherlv_1= '{' otherlv_2= 'Name' otherlv_3= ':' ( (lv_name_4_0= RULE_ID ) ) otherlv_5= 'Condition' otherlv_6= ':' ( (lv_condition_7_0= RULE_ID ) ) otherlv_8= 'ConditionType' otherlv_9= ':' ( (lv_conditionType_10_0= ruleConditionTypes ) ) otherlv_11= '}'
            {
            otherlv_0=(Token)match(input,27,FOLLOW_8); 

            			newLeafNode(otherlv_0, grammarAccess.getConditionAccess().getConditionKeyword_0());
            		
            otherlv_1=(Token)match(input,14,FOLLOW_9); 

            			newLeafNode(otherlv_1, grammarAccess.getConditionAccess().getLeftCurlyBracketKeyword_1());
            		
            otherlv_2=(Token)match(input,15,FOLLOW_3); 

            			newLeafNode(otherlv_2, grammarAccess.getConditionAccess().getNameKeyword_2());
            		
            otherlv_3=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_3, grammarAccess.getConditionAccess().getColonKeyword_3());
            		
            // InternalSCDsl.g:713:3: ( (lv_name_4_0= RULE_ID ) )
            // InternalSCDsl.g:714:4: (lv_name_4_0= RULE_ID )
            {
            // InternalSCDsl.g:714:4: (lv_name_4_0= RULE_ID )
            // InternalSCDsl.g:715:5: lv_name_4_0= RULE_ID
            {
            lv_name_4_0=(Token)match(input,RULE_ID,FOLLOW_21); 

            					newLeafNode(lv_name_4_0, grammarAccess.getConditionAccess().getNameIDTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getConditionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_4_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_5=(Token)match(input,27,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getConditionAccess().getConditionKeyword_5());
            		
            otherlv_6=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_6, grammarAccess.getConditionAccess().getColonKeyword_6());
            		
            // InternalSCDsl.g:739:3: ( (lv_condition_7_0= RULE_ID ) )
            // InternalSCDsl.g:740:4: (lv_condition_7_0= RULE_ID )
            {
            // InternalSCDsl.g:740:4: (lv_condition_7_0= RULE_ID )
            // InternalSCDsl.g:741:5: lv_condition_7_0= RULE_ID
            {
            lv_condition_7_0=(Token)match(input,RULE_ID,FOLLOW_22); 

            					newLeafNode(lv_condition_7_0, grammarAccess.getConditionAccess().getConditionIDTerminalRuleCall_7_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getConditionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"condition",
            						lv_condition_7_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_8=(Token)match(input,28,FOLLOW_3); 

            			newLeafNode(otherlv_8, grammarAccess.getConditionAccess().getConditionTypeKeyword_8());
            		
            otherlv_9=(Token)match(input,12,FOLLOW_23); 

            			newLeafNode(otherlv_9, grammarAccess.getConditionAccess().getColonKeyword_9());
            		
            // InternalSCDsl.g:765:3: ( (lv_conditionType_10_0= ruleConditionTypes ) )
            // InternalSCDsl.g:766:4: (lv_conditionType_10_0= ruleConditionTypes )
            {
            // InternalSCDsl.g:766:4: (lv_conditionType_10_0= ruleConditionTypes )
            // InternalSCDsl.g:767:5: lv_conditionType_10_0= ruleConditionTypes
            {

            					newCompositeNode(grammarAccess.getConditionAccess().getConditionTypeConditionTypesEnumRuleCall_10_0());
            				
            pushFollow(FOLLOW_18);
            lv_conditionType_10_0=ruleConditionTypes();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getConditionRule());
            					}
            					set(
            						current,
            						"conditionType",
            						lv_conditionType_10_0,
            						"org.sctest.scdsl.SCDsl.ConditionTypes");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_11=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_11, grammarAccess.getConditionAccess().getRightCurlyBracketKeyword_11());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCondition"


    // $ANTLR start "entryRuleRelationship"
    // InternalSCDsl.g:792:1: entryRuleRelationship returns [EObject current=null] : iv_ruleRelationship= ruleRelationship EOF ;
    public final EObject entryRuleRelationship() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationship = null;


        try {
            // InternalSCDsl.g:792:53: (iv_ruleRelationship= ruleRelationship EOF )
            // InternalSCDsl.g:793:2: iv_ruleRelationship= ruleRelationship EOF
            {
             newCompositeNode(grammarAccess.getRelationshipRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRelationship=ruleRelationship();

            state._fsp--;

             current =iv_ruleRelationship; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRelationship"


    // $ANTLR start "ruleRelationship"
    // InternalSCDsl.g:799:1: ruleRelationship returns [EObject current=null] : (otherlv_0= 'Relationship' (this_TranRel_1= ruleTranRel | this_AssetRel_2= ruleAssetRel ) otherlv_3= '{' otherlv_4= 'Name' otherlv_5= ':' ( (lv_name_6_0= RULE_ID ) ) otherlv_7= 'From' otherlv_8= ':' ( (otherlv_9= RULE_ID ) ) otherlv_10= 'To' otherlv_11= ':' ( (otherlv_12= RULE_ID ) ) ( (lv_event_13_0= ruleEvent ) )* otherlv_14= '}' ) ;
    public final EObject ruleRelationship() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token lv_name_6_0=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        EObject this_TranRel_1 = null;

        EObject this_AssetRel_2 = null;

        EObject lv_event_13_0 = null;



        	enterRule();

        try {
            // InternalSCDsl.g:805:2: ( (otherlv_0= 'Relationship' (this_TranRel_1= ruleTranRel | this_AssetRel_2= ruleAssetRel ) otherlv_3= '{' otherlv_4= 'Name' otherlv_5= ':' ( (lv_name_6_0= RULE_ID ) ) otherlv_7= 'From' otherlv_8= ':' ( (otherlv_9= RULE_ID ) ) otherlv_10= 'To' otherlv_11= ':' ( (otherlv_12= RULE_ID ) ) ( (lv_event_13_0= ruleEvent ) )* otherlv_14= '}' ) )
            // InternalSCDsl.g:806:2: (otherlv_0= 'Relationship' (this_TranRel_1= ruleTranRel | this_AssetRel_2= ruleAssetRel ) otherlv_3= '{' otherlv_4= 'Name' otherlv_5= ':' ( (lv_name_6_0= RULE_ID ) ) otherlv_7= 'From' otherlv_8= ':' ( (otherlv_9= RULE_ID ) ) otherlv_10= 'To' otherlv_11= ':' ( (otherlv_12= RULE_ID ) ) ( (lv_event_13_0= ruleEvent ) )* otherlv_14= '}' )
            {
            // InternalSCDsl.g:806:2: (otherlv_0= 'Relationship' (this_TranRel_1= ruleTranRel | this_AssetRel_2= ruleAssetRel ) otherlv_3= '{' otherlv_4= 'Name' otherlv_5= ':' ( (lv_name_6_0= RULE_ID ) ) otherlv_7= 'From' otherlv_8= ':' ( (otherlv_9= RULE_ID ) ) otherlv_10= 'To' otherlv_11= ':' ( (otherlv_12= RULE_ID ) ) ( (lv_event_13_0= ruleEvent ) )* otherlv_14= '}' )
            // InternalSCDsl.g:807:3: otherlv_0= 'Relationship' (this_TranRel_1= ruleTranRel | this_AssetRel_2= ruleAssetRel ) otherlv_3= '{' otherlv_4= 'Name' otherlv_5= ':' ( (lv_name_6_0= RULE_ID ) ) otherlv_7= 'From' otherlv_8= ':' ( (otherlv_9= RULE_ID ) ) otherlv_10= 'To' otherlv_11= ':' ( (otherlv_12= RULE_ID ) ) ( (lv_event_13_0= ruleEvent ) )* otherlv_14= '}'
            {
            otherlv_0=(Token)match(input,29,FOLLOW_24); 

            			newLeafNode(otherlv_0, grammarAccess.getRelationshipAccess().getRelationshipKeyword_0());
            		
            // InternalSCDsl.g:811:3: (this_TranRel_1= ruleTranRel | this_AssetRel_2= ruleAssetRel )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==34) ) {
                alt10=1;
            }
            else if ( (LA10_0==36) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // InternalSCDsl.g:812:4: this_TranRel_1= ruleTranRel
                    {

                    				newCompositeNode(grammarAccess.getRelationshipAccess().getTranRelParserRuleCall_1_0());
                    			
                    pushFollow(FOLLOW_8);
                    this_TranRel_1=ruleTranRel();

                    state._fsp--;


                    				current = this_TranRel_1;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;
                case 2 :
                    // InternalSCDsl.g:821:4: this_AssetRel_2= ruleAssetRel
                    {

                    				newCompositeNode(grammarAccess.getRelationshipAccess().getAssetRelParserRuleCall_1_1());
                    			
                    pushFollow(FOLLOW_8);
                    this_AssetRel_2=ruleAssetRel();

                    state._fsp--;


                    				current = this_AssetRel_2;
                    				afterParserOrEnumRuleCall();
                    			

                    }
                    break;

            }

            otherlv_3=(Token)match(input,14,FOLLOW_9); 

            			newLeafNode(otherlv_3, grammarAccess.getRelationshipAccess().getLeftCurlyBracketKeyword_2());
            		
            otherlv_4=(Token)match(input,15,FOLLOW_3); 

            			newLeafNode(otherlv_4, grammarAccess.getRelationshipAccess().getNameKeyword_3());
            		
            otherlv_5=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_5, grammarAccess.getRelationshipAccess().getColonKeyword_4());
            		
            // InternalSCDsl.g:842:3: ( (lv_name_6_0= RULE_ID ) )
            // InternalSCDsl.g:843:4: (lv_name_6_0= RULE_ID )
            {
            // InternalSCDsl.g:843:4: (lv_name_6_0= RULE_ID )
            // InternalSCDsl.g:844:5: lv_name_6_0= RULE_ID
            {
            lv_name_6_0=(Token)match(input,RULE_ID,FOLLOW_25); 

            					newLeafNode(lv_name_6_0, grammarAccess.getRelationshipAccess().getNameIDTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRelationshipRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_6_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_7=(Token)match(input,30,FOLLOW_3); 

            			newLeafNode(otherlv_7, grammarAccess.getRelationshipAccess().getFromKeyword_6());
            		
            otherlv_8=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_8, grammarAccess.getRelationshipAccess().getColonKeyword_7());
            		
            // InternalSCDsl.g:868:3: ( (otherlv_9= RULE_ID ) )
            // InternalSCDsl.g:869:4: (otherlv_9= RULE_ID )
            {
            // InternalSCDsl.g:869:4: (otherlv_9= RULE_ID )
            // InternalSCDsl.g:870:5: otherlv_9= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRelationshipRule());
            					}
            				
            otherlv_9=(Token)match(input,RULE_ID,FOLLOW_26); 

            					newLeafNode(otherlv_9, grammarAccess.getRelationshipAccess().getFromElementCrossReference_8_0());
            				

            }


            }

            otherlv_10=(Token)match(input,31,FOLLOW_3); 

            			newLeafNode(otherlv_10, grammarAccess.getRelationshipAccess().getToKeyword_9());
            		
            otherlv_11=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_11, grammarAccess.getRelationshipAccess().getColonKeyword_10());
            		
            // InternalSCDsl.g:889:3: ( (otherlv_12= RULE_ID ) )
            // InternalSCDsl.g:890:4: (otherlv_12= RULE_ID )
            {
            // InternalSCDsl.g:890:4: (otherlv_12= RULE_ID )
            // InternalSCDsl.g:891:5: otherlv_12= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRelationshipRule());
            					}
            				
            otherlv_12=(Token)match(input,RULE_ID,FOLLOW_27); 

            					newLeafNode(otherlv_12, grammarAccess.getRelationshipAccess().getToElementCrossReference_11_0());
            				

            }


            }

            // InternalSCDsl.g:902:3: ( (lv_event_13_0= ruleEvent ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==32) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalSCDsl.g:903:4: (lv_event_13_0= ruleEvent )
            	    {
            	    // InternalSCDsl.g:903:4: (lv_event_13_0= ruleEvent )
            	    // InternalSCDsl.g:904:5: lv_event_13_0= ruleEvent
            	    {

            	    					newCompositeNode(grammarAccess.getRelationshipAccess().getEventEventParserRuleCall_12_0());
            	    				
            	    pushFollow(FOLLOW_27);
            	    lv_event_13_0=ruleEvent();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getRelationshipRule());
            	    					}
            	    					add(
            	    						current,
            	    						"event",
            	    						lv_event_13_0,
            	    						"org.sctest.scdsl.SCDsl.Event");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            otherlv_14=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_14, grammarAccess.getRelationshipAccess().getRightCurlyBracketKeyword_13());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRelationship"


    // $ANTLR start "entryRuleEvent"
    // InternalSCDsl.g:929:1: entryRuleEvent returns [EObject current=null] : iv_ruleEvent= ruleEvent EOF ;
    public final EObject entryRuleEvent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEvent = null;


        try {
            // InternalSCDsl.g:929:46: (iv_ruleEvent= ruleEvent EOF )
            // InternalSCDsl.g:930:2: iv_ruleEvent= ruleEvent EOF
            {
             newCompositeNode(grammarAccess.getEventRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEvent=ruleEvent();

            state._fsp--;

             current =iv_ruleEvent; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEvent"


    // $ANTLR start "ruleEvent"
    // InternalSCDsl.g:936:1: ruleEvent returns [EObject current=null] : (otherlv_0= 'Event' otherlv_1= '{' otherlv_2= 'Description' otherlv_3= ':' ( (lv_description_4_0= RULE_ID ) ) otherlv_5= 'Name' otherlv_6= ':' ( (lv_name_7_0= RULE_ID ) ) otherlv_8= '}' ) ;
    public final EObject ruleEvent() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_description_4_0=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token lv_name_7_0=null;
        Token otherlv_8=null;


        	enterRule();

        try {
            // InternalSCDsl.g:942:2: ( (otherlv_0= 'Event' otherlv_1= '{' otherlv_2= 'Description' otherlv_3= ':' ( (lv_description_4_0= RULE_ID ) ) otherlv_5= 'Name' otherlv_6= ':' ( (lv_name_7_0= RULE_ID ) ) otherlv_8= '}' ) )
            // InternalSCDsl.g:943:2: (otherlv_0= 'Event' otherlv_1= '{' otherlv_2= 'Description' otherlv_3= ':' ( (lv_description_4_0= RULE_ID ) ) otherlv_5= 'Name' otherlv_6= ':' ( (lv_name_7_0= RULE_ID ) ) otherlv_8= '}' )
            {
            // InternalSCDsl.g:943:2: (otherlv_0= 'Event' otherlv_1= '{' otherlv_2= 'Description' otherlv_3= ':' ( (lv_description_4_0= RULE_ID ) ) otherlv_5= 'Name' otherlv_6= ':' ( (lv_name_7_0= RULE_ID ) ) otherlv_8= '}' )
            // InternalSCDsl.g:944:3: otherlv_0= 'Event' otherlv_1= '{' otherlv_2= 'Description' otherlv_3= ':' ( (lv_description_4_0= RULE_ID ) ) otherlv_5= 'Name' otherlv_6= ':' ( (lv_name_7_0= RULE_ID ) ) otherlv_8= '}'
            {
            otherlv_0=(Token)match(input,32,FOLLOW_8); 

            			newLeafNode(otherlv_0, grammarAccess.getEventAccess().getEventKeyword_0());
            		
            otherlv_1=(Token)match(input,14,FOLLOW_28); 

            			newLeafNode(otherlv_1, grammarAccess.getEventAccess().getLeftCurlyBracketKeyword_1());
            		
            otherlv_2=(Token)match(input,33,FOLLOW_3); 

            			newLeafNode(otherlv_2, grammarAccess.getEventAccess().getDescriptionKeyword_2());
            		
            otherlv_3=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_3, grammarAccess.getEventAccess().getColonKeyword_3());
            		
            // InternalSCDsl.g:960:3: ( (lv_description_4_0= RULE_ID ) )
            // InternalSCDsl.g:961:4: (lv_description_4_0= RULE_ID )
            {
            // InternalSCDsl.g:961:4: (lv_description_4_0= RULE_ID )
            // InternalSCDsl.g:962:5: lv_description_4_0= RULE_ID
            {
            lv_description_4_0=(Token)match(input,RULE_ID,FOLLOW_9); 

            					newLeafNode(lv_description_4_0, grammarAccess.getEventAccess().getDescriptionIDTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEventRule());
            					}
            					setWithLastConsumed(
            						current,
            						"description",
            						lv_description_4_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_5=(Token)match(input,15,FOLLOW_3); 

            			newLeafNode(otherlv_5, grammarAccess.getEventAccess().getNameKeyword_5());
            		
            otherlv_6=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_6, grammarAccess.getEventAccess().getColonKeyword_6());
            		
            // InternalSCDsl.g:986:3: ( (lv_name_7_0= RULE_ID ) )
            // InternalSCDsl.g:987:4: (lv_name_7_0= RULE_ID )
            {
            // InternalSCDsl.g:987:4: (lv_name_7_0= RULE_ID )
            // InternalSCDsl.g:988:5: lv_name_7_0= RULE_ID
            {
            lv_name_7_0=(Token)match(input,RULE_ID,FOLLOW_18); 

            					newLeafNode(lv_name_7_0, grammarAccess.getEventAccess().getNameIDTerminalRuleCall_7_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEventRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_7_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_8=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getEventAccess().getRightCurlyBracketKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEvent"


    // $ANTLR start "entryRuleTranRel"
    // InternalSCDsl.g:1012:1: entryRuleTranRel returns [EObject current=null] : iv_ruleTranRel= ruleTranRel EOF ;
    public final EObject entryRuleTranRel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTranRel = null;


        try {
            // InternalSCDsl.g:1012:48: (iv_ruleTranRel= ruleTranRel EOF )
            // InternalSCDsl.g:1013:2: iv_ruleTranRel= ruleTranRel EOF
            {
             newCompositeNode(grammarAccess.getTranRelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTranRel=ruleTranRel();

            state._fsp--;

             current =iv_ruleTranRel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTranRel"


    // $ANTLR start "ruleTranRel"
    // InternalSCDsl.g:1019:1: ruleTranRel returns [EObject current=null] : ( () otherlv_1= 'TranRel' (otherlv_2= 'Selected' otherlv_3= ':' ( (lv_selected_4_0= 'T' ) ) )? ) ;
    public final EObject ruleTranRel() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_selected_4_0=null;


        	enterRule();

        try {
            // InternalSCDsl.g:1025:2: ( ( () otherlv_1= 'TranRel' (otherlv_2= 'Selected' otherlv_3= ':' ( (lv_selected_4_0= 'T' ) ) )? ) )
            // InternalSCDsl.g:1026:2: ( () otherlv_1= 'TranRel' (otherlv_2= 'Selected' otherlv_3= ':' ( (lv_selected_4_0= 'T' ) ) )? )
            {
            // InternalSCDsl.g:1026:2: ( () otherlv_1= 'TranRel' (otherlv_2= 'Selected' otherlv_3= ':' ( (lv_selected_4_0= 'T' ) ) )? )
            // InternalSCDsl.g:1027:3: () otherlv_1= 'TranRel' (otherlv_2= 'Selected' otherlv_3= ':' ( (lv_selected_4_0= 'T' ) ) )?
            {
            // InternalSCDsl.g:1027:3: ()
            // InternalSCDsl.g:1028:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTranRelAccess().getTranRelAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,34,FOLLOW_29); 

            			newLeafNode(otherlv_1, grammarAccess.getTranRelAccess().getTranRelKeyword_1());
            		
            // InternalSCDsl.g:1038:3: (otherlv_2= 'Selected' otherlv_3= ':' ( (lv_selected_4_0= 'T' ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==35) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalSCDsl.g:1039:4: otherlv_2= 'Selected' otherlv_3= ':' ( (lv_selected_4_0= 'T' ) )
                    {
                    otherlv_2=(Token)match(input,35,FOLLOW_3); 

                    				newLeafNode(otherlv_2, grammarAccess.getTranRelAccess().getSelectedKeyword_2_0());
                    			
                    otherlv_3=(Token)match(input,12,FOLLOW_15); 

                    				newLeafNode(otherlv_3, grammarAccess.getTranRelAccess().getColonKeyword_2_1());
                    			
                    // InternalSCDsl.g:1047:4: ( (lv_selected_4_0= 'T' ) )
                    // InternalSCDsl.g:1048:5: (lv_selected_4_0= 'T' )
                    {
                    // InternalSCDsl.g:1048:5: (lv_selected_4_0= 'T' )
                    // InternalSCDsl.g:1049:6: lv_selected_4_0= 'T'
                    {
                    lv_selected_4_0=(Token)match(input,20,FOLLOW_2); 

                    						newLeafNode(lv_selected_4_0, grammarAccess.getTranRelAccess().getSelectedTKeyword_2_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getTranRelRule());
                    						}
                    						setWithLastConsumed(current, "selected", lv_selected_4_0 != null, "T");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTranRel"


    // $ANTLR start "entryRuleAssetRel"
    // InternalSCDsl.g:1066:1: entryRuleAssetRel returns [EObject current=null] : iv_ruleAssetRel= ruleAssetRel EOF ;
    public final EObject entryRuleAssetRel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssetRel = null;


        try {
            // InternalSCDsl.g:1066:49: (iv_ruleAssetRel= ruleAssetRel EOF )
            // InternalSCDsl.g:1067:2: iv_ruleAssetRel= ruleAssetRel EOF
            {
             newCompositeNode(grammarAccess.getAssetRelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAssetRel=ruleAssetRel();

            state._fsp--;

             current =iv_ruleAssetRel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAssetRel"


    // $ANTLR start "ruleAssetRel"
    // InternalSCDsl.g:1073:1: ruleAssetRel returns [EObject current=null] : ( () otherlv_1= 'AssetRel' (otherlv_2= 'Create' otherlv_3= ':' ( (lv_create_4_0= 'T' ) ) )? (otherlv_5= 'Read' otherlv_6= ':' ( (lv_read_7_0= 'T' ) ) )? (otherlv_8= 'Update' otherlv_9= ':' ( (lv_update_10_0= 'T' ) ) )? (otherlv_11= 'Delete' otherlv_12= ':' ( (lv_delete_13_0= 'T' ) ) )? (otherlv_14= 'All' otherlv_15= ':' ( (lv_all_16_0= 'T' ) ) )? ) ;
    public final EObject ruleAssetRel() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_create_4_0=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token lv_read_7_0=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token lv_update_10_0=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token lv_delete_13_0=null;
        Token otherlv_14=null;
        Token otherlv_15=null;
        Token lv_all_16_0=null;


        	enterRule();

        try {
            // InternalSCDsl.g:1079:2: ( ( () otherlv_1= 'AssetRel' (otherlv_2= 'Create' otherlv_3= ':' ( (lv_create_4_0= 'T' ) ) )? (otherlv_5= 'Read' otherlv_6= ':' ( (lv_read_7_0= 'T' ) ) )? (otherlv_8= 'Update' otherlv_9= ':' ( (lv_update_10_0= 'T' ) ) )? (otherlv_11= 'Delete' otherlv_12= ':' ( (lv_delete_13_0= 'T' ) ) )? (otherlv_14= 'All' otherlv_15= ':' ( (lv_all_16_0= 'T' ) ) )? ) )
            // InternalSCDsl.g:1080:2: ( () otherlv_1= 'AssetRel' (otherlv_2= 'Create' otherlv_3= ':' ( (lv_create_4_0= 'T' ) ) )? (otherlv_5= 'Read' otherlv_6= ':' ( (lv_read_7_0= 'T' ) ) )? (otherlv_8= 'Update' otherlv_9= ':' ( (lv_update_10_0= 'T' ) ) )? (otherlv_11= 'Delete' otherlv_12= ':' ( (lv_delete_13_0= 'T' ) ) )? (otherlv_14= 'All' otherlv_15= ':' ( (lv_all_16_0= 'T' ) ) )? )
            {
            // InternalSCDsl.g:1080:2: ( () otherlv_1= 'AssetRel' (otherlv_2= 'Create' otherlv_3= ':' ( (lv_create_4_0= 'T' ) ) )? (otherlv_5= 'Read' otherlv_6= ':' ( (lv_read_7_0= 'T' ) ) )? (otherlv_8= 'Update' otherlv_9= ':' ( (lv_update_10_0= 'T' ) ) )? (otherlv_11= 'Delete' otherlv_12= ':' ( (lv_delete_13_0= 'T' ) ) )? (otherlv_14= 'All' otherlv_15= ':' ( (lv_all_16_0= 'T' ) ) )? )
            // InternalSCDsl.g:1081:3: () otherlv_1= 'AssetRel' (otherlv_2= 'Create' otherlv_3= ':' ( (lv_create_4_0= 'T' ) ) )? (otherlv_5= 'Read' otherlv_6= ':' ( (lv_read_7_0= 'T' ) ) )? (otherlv_8= 'Update' otherlv_9= ':' ( (lv_update_10_0= 'T' ) ) )? (otherlv_11= 'Delete' otherlv_12= ':' ( (lv_delete_13_0= 'T' ) ) )? (otherlv_14= 'All' otherlv_15= ':' ( (lv_all_16_0= 'T' ) ) )?
            {
            // InternalSCDsl.g:1081:3: ()
            // InternalSCDsl.g:1082:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getAssetRelAccess().getAssetRelAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,36,FOLLOW_30); 

            			newLeafNode(otherlv_1, grammarAccess.getAssetRelAccess().getAssetRelKeyword_1());
            		
            // InternalSCDsl.g:1092:3: (otherlv_2= 'Create' otherlv_3= ':' ( (lv_create_4_0= 'T' ) ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==37) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalSCDsl.g:1093:4: otherlv_2= 'Create' otherlv_3= ':' ( (lv_create_4_0= 'T' ) )
                    {
                    otherlv_2=(Token)match(input,37,FOLLOW_3); 

                    				newLeafNode(otherlv_2, grammarAccess.getAssetRelAccess().getCreateKeyword_2_0());
                    			
                    otherlv_3=(Token)match(input,12,FOLLOW_15); 

                    				newLeafNode(otherlv_3, grammarAccess.getAssetRelAccess().getColonKeyword_2_1());
                    			
                    // InternalSCDsl.g:1101:4: ( (lv_create_4_0= 'T' ) )
                    // InternalSCDsl.g:1102:5: (lv_create_4_0= 'T' )
                    {
                    // InternalSCDsl.g:1102:5: (lv_create_4_0= 'T' )
                    // InternalSCDsl.g:1103:6: lv_create_4_0= 'T'
                    {
                    lv_create_4_0=(Token)match(input,20,FOLLOW_31); 

                    						newLeafNode(lv_create_4_0, grammarAccess.getAssetRelAccess().getCreateTKeyword_2_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAssetRelRule());
                    						}
                    						setWithLastConsumed(current, "create", lv_create_4_0 != null, "T");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalSCDsl.g:1116:3: (otherlv_5= 'Read' otherlv_6= ':' ( (lv_read_7_0= 'T' ) ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==38) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalSCDsl.g:1117:4: otherlv_5= 'Read' otherlv_6= ':' ( (lv_read_7_0= 'T' ) )
                    {
                    otherlv_5=(Token)match(input,38,FOLLOW_3); 

                    				newLeafNode(otherlv_5, grammarAccess.getAssetRelAccess().getReadKeyword_3_0());
                    			
                    otherlv_6=(Token)match(input,12,FOLLOW_15); 

                    				newLeafNode(otherlv_6, grammarAccess.getAssetRelAccess().getColonKeyword_3_1());
                    			
                    // InternalSCDsl.g:1125:4: ( (lv_read_7_0= 'T' ) )
                    // InternalSCDsl.g:1126:5: (lv_read_7_0= 'T' )
                    {
                    // InternalSCDsl.g:1126:5: (lv_read_7_0= 'T' )
                    // InternalSCDsl.g:1127:6: lv_read_7_0= 'T'
                    {
                    lv_read_7_0=(Token)match(input,20,FOLLOW_32); 

                    						newLeafNode(lv_read_7_0, grammarAccess.getAssetRelAccess().getReadTKeyword_3_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAssetRelRule());
                    						}
                    						setWithLastConsumed(current, "read", lv_read_7_0 != null, "T");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalSCDsl.g:1140:3: (otherlv_8= 'Update' otherlv_9= ':' ( (lv_update_10_0= 'T' ) ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==39) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalSCDsl.g:1141:4: otherlv_8= 'Update' otherlv_9= ':' ( (lv_update_10_0= 'T' ) )
                    {
                    otherlv_8=(Token)match(input,39,FOLLOW_3); 

                    				newLeafNode(otherlv_8, grammarAccess.getAssetRelAccess().getUpdateKeyword_4_0());
                    			
                    otherlv_9=(Token)match(input,12,FOLLOW_15); 

                    				newLeafNode(otherlv_9, grammarAccess.getAssetRelAccess().getColonKeyword_4_1());
                    			
                    // InternalSCDsl.g:1149:4: ( (lv_update_10_0= 'T' ) )
                    // InternalSCDsl.g:1150:5: (lv_update_10_0= 'T' )
                    {
                    // InternalSCDsl.g:1150:5: (lv_update_10_0= 'T' )
                    // InternalSCDsl.g:1151:6: lv_update_10_0= 'T'
                    {
                    lv_update_10_0=(Token)match(input,20,FOLLOW_33); 

                    						newLeafNode(lv_update_10_0, grammarAccess.getAssetRelAccess().getUpdateTKeyword_4_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAssetRelRule());
                    						}
                    						setWithLastConsumed(current, "update", lv_update_10_0 != null, "T");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalSCDsl.g:1164:3: (otherlv_11= 'Delete' otherlv_12= ':' ( (lv_delete_13_0= 'T' ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==40) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalSCDsl.g:1165:4: otherlv_11= 'Delete' otherlv_12= ':' ( (lv_delete_13_0= 'T' ) )
                    {
                    otherlv_11=(Token)match(input,40,FOLLOW_3); 

                    				newLeafNode(otherlv_11, grammarAccess.getAssetRelAccess().getDeleteKeyword_5_0());
                    			
                    otherlv_12=(Token)match(input,12,FOLLOW_15); 

                    				newLeafNode(otherlv_12, grammarAccess.getAssetRelAccess().getColonKeyword_5_1());
                    			
                    // InternalSCDsl.g:1173:4: ( (lv_delete_13_0= 'T' ) )
                    // InternalSCDsl.g:1174:5: (lv_delete_13_0= 'T' )
                    {
                    // InternalSCDsl.g:1174:5: (lv_delete_13_0= 'T' )
                    // InternalSCDsl.g:1175:6: lv_delete_13_0= 'T'
                    {
                    lv_delete_13_0=(Token)match(input,20,FOLLOW_34); 

                    						newLeafNode(lv_delete_13_0, grammarAccess.getAssetRelAccess().getDeleteTKeyword_5_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAssetRelRule());
                    						}
                    						setWithLastConsumed(current, "delete", lv_delete_13_0 != null, "T");
                    					

                    }


                    }


                    }
                    break;

            }

            // InternalSCDsl.g:1188:3: (otherlv_14= 'All' otherlv_15= ':' ( (lv_all_16_0= 'T' ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==41) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalSCDsl.g:1189:4: otherlv_14= 'All' otherlv_15= ':' ( (lv_all_16_0= 'T' ) )
                    {
                    otherlv_14=(Token)match(input,41,FOLLOW_3); 

                    				newLeafNode(otherlv_14, grammarAccess.getAssetRelAccess().getAllKeyword_6_0());
                    			
                    otherlv_15=(Token)match(input,12,FOLLOW_15); 

                    				newLeafNode(otherlv_15, grammarAccess.getAssetRelAccess().getColonKeyword_6_1());
                    			
                    // InternalSCDsl.g:1197:4: ( (lv_all_16_0= 'T' ) )
                    // InternalSCDsl.g:1198:5: (lv_all_16_0= 'T' )
                    {
                    // InternalSCDsl.g:1198:5: (lv_all_16_0= 'T' )
                    // InternalSCDsl.g:1199:6: lv_all_16_0= 'T'
                    {
                    lv_all_16_0=(Token)match(input,20,FOLLOW_2); 

                    						newLeafNode(lv_all_16_0, grammarAccess.getAssetRelAccess().getAllTKeyword_6_2_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAssetRelRule());
                    						}
                    						setWithLastConsumed(current, "all", lv_all_16_0 != null, "T");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAssetRel"


    // $ANTLR start "ruleTargetPlatform"
    // InternalSCDsl.g:1216:1: ruleTargetPlatform returns [Enumerator current=null] : ( (enumLiteral_0= 'IBM' ) | (enumLiteral_1= 'Azure' ) | (enumLiteral_2= 'Solidity' ) ) ;
    public final Enumerator ruleTargetPlatform() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalSCDsl.g:1222:2: ( ( (enumLiteral_0= 'IBM' ) | (enumLiteral_1= 'Azure' ) | (enumLiteral_2= 'Solidity' ) ) )
            // InternalSCDsl.g:1223:2: ( (enumLiteral_0= 'IBM' ) | (enumLiteral_1= 'Azure' ) | (enumLiteral_2= 'Solidity' ) )
            {
            // InternalSCDsl.g:1223:2: ( (enumLiteral_0= 'IBM' ) | (enumLiteral_1= 'Azure' ) | (enumLiteral_2= 'Solidity' ) )
            int alt18=3;
            switch ( input.LA(1) ) {
            case 42:
                {
                alt18=1;
                }
                break;
            case 43:
                {
                alt18=2;
                }
                break;
            case 44:
                {
                alt18=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }

            switch (alt18) {
                case 1 :
                    // InternalSCDsl.g:1224:3: (enumLiteral_0= 'IBM' )
                    {
                    // InternalSCDsl.g:1224:3: (enumLiteral_0= 'IBM' )
                    // InternalSCDsl.g:1225:4: enumLiteral_0= 'IBM'
                    {
                    enumLiteral_0=(Token)match(input,42,FOLLOW_2); 

                    				current = grammarAccess.getTargetPlatformAccess().getIBMEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getTargetPlatformAccess().getIBMEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalSCDsl.g:1232:3: (enumLiteral_1= 'Azure' )
                    {
                    // InternalSCDsl.g:1232:3: (enumLiteral_1= 'Azure' )
                    // InternalSCDsl.g:1233:4: enumLiteral_1= 'Azure'
                    {
                    enumLiteral_1=(Token)match(input,43,FOLLOW_2); 

                    				current = grammarAccess.getTargetPlatformAccess().getAzureEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getTargetPlatformAccess().getAzureEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalSCDsl.g:1240:3: (enumLiteral_2= 'Solidity' )
                    {
                    // InternalSCDsl.g:1240:3: (enumLiteral_2= 'Solidity' )
                    // InternalSCDsl.g:1241:4: enumLiteral_2= 'Solidity'
                    {
                    enumLiteral_2=(Token)match(input,44,FOLLOW_2); 

                    				current = grammarAccess.getTargetPlatformAccess().getSolidityEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getTargetPlatformAccess().getSolidityEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTargetPlatform"


    // $ANTLR start "ruleConditionTypes"
    // InternalSCDsl.g:1251:1: ruleConditionTypes returns [Enumerator current=null] : ( (enumLiteral_0= 'Pre' ) | (enumLiteral_1= 'Post' ) ) ;
    public final Enumerator ruleConditionTypes() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalSCDsl.g:1257:2: ( ( (enumLiteral_0= 'Pre' ) | (enumLiteral_1= 'Post' ) ) )
            // InternalSCDsl.g:1258:2: ( (enumLiteral_0= 'Pre' ) | (enumLiteral_1= 'Post' ) )
            {
            // InternalSCDsl.g:1258:2: ( (enumLiteral_0= 'Pre' ) | (enumLiteral_1= 'Post' ) )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==45) ) {
                alt19=1;
            }
            else if ( (LA19_0==46) ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // InternalSCDsl.g:1259:3: (enumLiteral_0= 'Pre' )
                    {
                    // InternalSCDsl.g:1259:3: (enumLiteral_0= 'Pre' )
                    // InternalSCDsl.g:1260:4: enumLiteral_0= 'Pre'
                    {
                    enumLiteral_0=(Token)match(input,45,FOLLOW_2); 

                    				current = grammarAccess.getConditionTypesAccess().getPreEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getConditionTypesAccess().getPreEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalSCDsl.g:1267:3: (enumLiteral_1= 'Post' )
                    {
                    // InternalSCDsl.g:1267:3: (enumLiteral_1= 'Post' )
                    // InternalSCDsl.g:1268:4: enumLiteral_1= 'Post'
                    {
                    enumLiteral_1=(Token)match(input,46,FOLLOW_2); 

                    				current = grammarAccess.getConditionTypesAccess().getPostEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getConditionTypesAccess().getPostEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConditionTypes"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x00001C0000000000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000002240002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000020828000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000828000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000820000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000001020000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000004004000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000008020000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000600000000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000001400000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000100020000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x000003E000000002L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x000003C000000002L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000038000000002L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000030000000002L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000020000000002L});

}