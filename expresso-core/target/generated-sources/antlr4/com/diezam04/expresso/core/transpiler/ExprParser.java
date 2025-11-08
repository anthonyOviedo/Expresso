// Generated from com/diezam04/expresso/core/transpiler/Expr.g4 by ANTLR 4.13.1
package com.diezam04.expresso.core.transpiler;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ExprParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, ID=35, FLOAT_E=36, FLOAT=37, INT=38, STRING=39, 
		NEWLINE=40, WS=41, LINE_COMMENT=42, BLOCK_COMMENT=43;
	public static final int
		RULE_prog = 0, RULE_stat = 1, RULE_dataBlock = 2, RULE_constructorList = 3, 
		RULE_dataConstructor = 4, RULE_dataFieldList = 5, RULE_dataField = 6, 
		RULE_typeRef = 7, RULE_paramDeclList = 8, RULE_paramDecl = 9, RULE_type = 10, 
		RULE_comment = 11, RULE_expr = 12, RULE_matchCase = 13, RULE_pattern = 14, 
		RULE_patternParamList = 15, RULE_patternParam = 16, RULE_argumentList = 17, 
		RULE_params = 18, RULE_lambdaParam = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "stat", "dataBlock", "constructorList", "dataConstructor", "dataFieldList", 
			"dataField", "typeRef", "paramDeclList", "paramDecl", "type", "comment", 
			"expr", "matchCase", "pattern", "patternParamList", "patternParam", "argumentList", 
			"params", "lambdaParam"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'fun'", "'('", "')'", "':'", "'='", "'let'", "'print'", "'data'", 
			"'{'", "'}'", "','", "'int'", "'float'", "'string'", "'boolean'", "'any'", 
			"'-'", "'**'", "'^'", "'*'", "'/'", "'+'", "'>='", "'<='", "'>'", "'<'", 
			"'=='", "'!='", "'?'", "'->'", "'match'", "'with'", "'|'", "'_'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "ID", 
			"FLOAT_E", "FLOAT", "INT", "STRING", "NEWLINE", "WS", "LINE_COMMENT", 
			"BLOCK_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Expr.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExprParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ExprParser.EOF, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(ExprParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExprParser.NEWLINE, i);
		}
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(40);
					match(NEWLINE);
					}
					} 
				}
				setState(45);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(54);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(46);
					stat();
					setState(48); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(47);
							match(NEWLINE);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(50); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					} 
				}
				setState(56);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 14261439562182L) != 0)) {
				{
				setState(57);
				stat();
				}
			}

			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(60);
				match(NEWLINE);
				}
				}
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(66);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CommentStatContext extends StatContext {
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public CommentStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterCommentStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitCommentStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitCommentStat(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrintStatContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public PrintStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterPrintStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitPrintStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitPrintStat(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprStatContext extends StatContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public ExprStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterExprStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitExprStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitExprStat(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunStatContext extends StatContext {
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParamDeclListContext paramDeclList() {
			return getRuleContext(ParamDeclListContext.class,0);
		}
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public FunStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterFunStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitFunStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitFunStat(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LetStatContext extends StatContext {
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public LetStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterLetStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitLetStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitLetStat(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DataStatContext extends StatContext {
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public DataBlockContext dataBlock() {
			return getRuleContext(DataBlockContext.class,0);
		}
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public DataStatContext(StatContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterDataStat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitDataStat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitDataStat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stat);
		int _la;
		try {
			setState(105);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				_localctx = new FunStatContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				match(T__0);
				setState(69);
				match(ID);
				setState(70);
				match(T__1);
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(71);
					paramDeclList();
					}
				}

				setState(74);
				match(T__2);
				setState(75);
				match(T__3);
				setState(76);
				type();
				setState(77);
				match(T__4);
				setState(78);
				expr(0);
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LINE_COMMENT || _la==BLOCK_COMMENT) {
					{
					setState(79);
					comment();
					}
				}

				}
				break;
			case T__5:
				_localctx = new LetStatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(82);
				match(T__5);
				setState(83);
				match(ID);
				setState(84);
				match(T__4);
				setState(85);
				expr(0);
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LINE_COMMENT || _la==BLOCK_COMMENT) {
					{
					setState(86);
					comment();
					}
				}

				}
				break;
			case T__6:
				_localctx = new PrintStatContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(89);
				match(T__6);
				setState(90);
				expr(0);
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LINE_COMMENT || _la==BLOCK_COMMENT) {
					{
					setState(91);
					comment();
					}
				}

				}
				break;
			case T__7:
				_localctx = new DataStatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(94);
				match(T__7);
				setState(95);
				match(ID);
				setState(96);
				dataBlock();
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LINE_COMMENT || _la==BLOCK_COMMENT) {
					{
					setState(97);
					comment();
					}
				}

				}
				break;
			case T__1:
			case T__16:
			case T__18:
			case T__30:
			case ID:
			case FLOAT_E:
			case FLOAT:
			case INT:
			case STRING:
				_localctx = new ExprStatContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(100);
				expr(0);
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LINE_COMMENT || _la==BLOCK_COMMENT) {
					{
					setState(101);
					comment();
					}
				}

				}
				break;
			case LINE_COMMENT:
			case BLOCK_COMMENT:
				_localctx = new CommentStatContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(104);
				comment();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DataBlockContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(ExprParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExprParser.NEWLINE, i);
		}
		public ConstructorListContext constructorList() {
			return getRuleContext(ConstructorListContext.class,0);
		}
		public DataBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterDataBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitDataBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitDataBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataBlockContext dataBlock() throws RecognitionException {
		DataBlockContext _localctx = new DataBlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_dataBlock);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(107);
				match(T__4);
				}
			}

			}
			setState(110);
			match(T__8);
			setState(114);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(111);
					match(NEWLINE);
					}
					} 
				}
				setState(116);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(117);
				constructorList();
				}
			}

			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(120);
				match(NEWLINE);
				}
				}
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(126);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstructorListContext extends ParserRuleContext {
		public List<DataConstructorContext> dataConstructor() {
			return getRuleContexts(DataConstructorContext.class);
		}
		public DataConstructorContext dataConstructor(int i) {
			return getRuleContext(DataConstructorContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ExprParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExprParser.NEWLINE, i);
		}
		public ConstructorListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterConstructorList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitConstructorList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitConstructorList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructorListContext constructorList() throws RecognitionException {
		ConstructorListContext _localctx = new ConstructorListContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_constructorList);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			dataConstructor();
			setState(145);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(132);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEWLINE) {
						{
						{
						setState(129);
						match(NEWLINE);
						}
						}
						setState(134);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(135);
					match(T__10);
					setState(139);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEWLINE) {
						{
						{
						setState(136);
						match(NEWLINE);
						}
						}
						setState(141);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(142);
					dataConstructor();
					}
					} 
				}
				setState(147);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			setState(151);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(148);
					match(NEWLINE);
					}
					} 
				}
				setState(153);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(154);
				match(T__10);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DataConstructorContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public DataFieldListContext dataFieldList() {
			return getRuleContext(DataFieldListContext.class,0);
		}
		public DataConstructorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataConstructor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterDataConstructor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitDataConstructor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitDataConstructor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataConstructorContext dataConstructor() throws RecognitionException {
		DataConstructorContext _localctx = new DataConstructorContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_dataConstructor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(ID);
			setState(165);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				{
				setState(158);
				match(T__1);
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(159);
					dataFieldList();
					}
				}

				setState(162);
				match(T__2);
				}
				break;
			case T__3:
				{
				setState(163);
				match(T__3);
				setState(164);
				typeRef();
				}
				break;
			case T__9:
			case T__10:
			case NEWLINE:
				break;
			default:
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DataFieldListContext extends ParserRuleContext {
		public List<DataFieldContext> dataField() {
			return getRuleContexts(DataFieldContext.class);
		}
		public DataFieldContext dataField(int i) {
			return getRuleContext(DataFieldContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ExprParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExprParser.NEWLINE, i);
		}
		public DataFieldListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataFieldList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterDataFieldList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitDataFieldList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitDataFieldList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataFieldListContext dataFieldList() throws RecognitionException {
		DataFieldListContext _localctx = new DataFieldListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_dataFieldList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			dataField();
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10 || _la==NEWLINE) {
				{
				{
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(168);
					match(NEWLINE);
					}
					}
					setState(173);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(174);
				match(T__10);
				setState(178);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(175);
					match(NEWLINE);
					}
					}
					setState(180);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(181);
				dataField();
				}
				}
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DataFieldContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public DataFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataField; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterDataField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitDataField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitDataField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataFieldContext dataField() throws RecognitionException {
		DataFieldContext _localctx = new DataFieldContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_dataField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(ID);
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(188);
				match(T__3);
				setState(189);
				typeRef();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeRefContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public TypeRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterTypeRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitTypeRef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitTypeRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeRefContext typeRef() throws RecognitionException {
		TypeRefContext _localctx = new TypeRefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_typeRef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 34359865344L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamDeclListContext extends ParserRuleContext {
		public List<ParamDeclContext> paramDecl() {
			return getRuleContexts(ParamDeclContext.class);
		}
		public ParamDeclContext paramDecl(int i) {
			return getRuleContext(ParamDeclContext.class,i);
		}
		public ParamDeclListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramDeclList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterParamDeclList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitParamDeclList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitParamDeclList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamDeclListContext paramDeclList() throws RecognitionException {
		ParamDeclListContext _localctx = new ParamDeclListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_paramDeclList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			paramDecl();
			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(195);
				match(T__10);
				setState(196);
				paramDecl();
				}
				}
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamDeclContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParamDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterParamDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitParamDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitParamDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamDeclContext paramDecl() throws RecognitionException {
		ParamDeclContext _localctx = new ParamDeclContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_paramDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			match(ID);
			setState(203);
			match(T__3);
			setState(204);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 28672L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CommentContext extends ParserRuleContext {
		public TerminalNode LINE_COMMENT() { return getToken(ExprParser.LINE_COMMENT, 0); }
		public TerminalNode BLOCK_COMMENT() { return getToken(ExprParser.BLOCK_COMMENT, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitComment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitComment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_comment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			_la = _input.LA(1);
			if ( !(_la==LINE_COMMENT || _la==BLOCK_COMMENT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CallContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public CallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitCall(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CtorCallContext extends ExprContext {
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public CtorCallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterCtorCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitCtorCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitCtorCall(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MulDivContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public MulDivContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterMulDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitMulDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitMulDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddSubContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public AddSubContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterAddSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitAddSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitAddSub(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TernaryContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TernaryContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterTernary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitTernary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitTernary(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParensContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParensContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterParens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitParens(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitParens(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MatchExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<MatchCaseContext> matchCase() {
			return getRuleContexts(MatchCaseContext.class);
		}
		public MatchCaseContext matchCase(int i) {
			return getRuleContext(MatchCaseContext.class,i);
		}
		public MatchExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterMatchExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitMatchExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitMatchExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdRefContext extends ExprContext {
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public IdRefContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterIdRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitIdRef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitIdRef(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntContext extends ExprContext {
		public TerminalNode INT() { return getToken(ExprParser.INT, 0); }
		public IntContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EulerFloatContext extends ExprContext {
		public TerminalNode FLOAT_E() { return getToken(ExprParser.FLOAT_E, 0); }
		public EulerFloatContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterEulerFloat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitEulerFloat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitEulerFloat(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringLitContext extends ExprContext {
		public TerminalNode STRING() { return getToken(ExprParser.STRING, 0); }
		public StringLitContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterStringLit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitStringLit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitStringLit(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FloatContext extends ExprContext {
		public TerminalNode FLOAT() { return getToken(ExprParser.FLOAT, 0); }
		public FloatContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterFloat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitFloat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitFloat(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class RelationContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public RelationContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterRelation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitRelation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitRelation(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryMinusContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnaryMinusContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterUnaryMinus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitUnaryMinus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitUnaryMinus(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PowerContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public PowerContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterPower(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitPower(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitPower(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EqualityContext extends ExprContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public EqualityContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterEquality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitEquality(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitEquality(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LambdaContext extends ExprContext {
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public LambdaContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterLambda(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitLambda(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitLambda(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				_localctx = new UnaryMinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(211);
				match(T__16);
				setState(212);
				expr(17);
				}
				break;
			case 2:
				{
				_localctx = new CtorCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(213);
				match(T__18);
				setState(214);
				match(ID);
				setState(215);
				match(T__1);
				setState(217);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1067300028420L) != 0)) {
					{
					setState(216);
					argumentList();
					}
				}

				setState(219);
				match(T__2);
				}
				break;
			case 3:
				{
				_localctx = new LambdaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(220);
				params();
				setState(221);
				match(T__29);
				setState(222);
				expr(8);
				}
				break;
			case 4:
				{
				_localctx = new MatchExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(224);
				match(T__30);
				setState(225);
				expr(0);
				setState(226);
				match(T__31);
				setState(227);
				matchCase();
				setState(232);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(228);
						match(T__32);
						setState(229);
						matchCase();
						}
						} 
					}
					setState(234);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
				}
				}
				break;
			case 5:
				{
				_localctx = new EulerFloatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(235);
				match(FLOAT_E);
				}
				break;
			case 6:
				{
				_localctx = new FloatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(236);
				match(FLOAT);
				}
				break;
			case 7:
				{
				_localctx = new IntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(237);
				match(INT);
				}
				break;
			case 8:
				{
				_localctx = new StringLitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(238);
				match(STRING);
				}
				break;
			case 9:
				{
				_localctx = new IdRefContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(239);
				match(ID);
				}
				break;
			case 10:
				{
				_localctx = new ParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(240);
				match(T__1);
				setState(241);
				expr(0);
				setState(242);
				match(T__2);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(275);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(273);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
					case 1:
						{
						_localctx = new PowerContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(246);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(247);
						match(T__17);
						setState(248);
						expr(17);
						}
						break;
					case 2:
						{
						_localctx = new MulDivContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(249);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(250);
						((MulDivContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__19 || _la==T__20) ) {
							((MulDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(251);
						expr(14);
						}
						break;
					case 3:
						{
						_localctx = new AddSubContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(252);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(253);
						((AddSubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__16 || _la==T__21) ) {
							((AddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(254);
						expr(13);
						}
						break;
					case 4:
						{
						_localctx = new RelationContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(255);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(256);
						((RelationContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 125829120L) != 0)) ) {
							((RelationContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(257);
						expr(12);
						}
						break;
					case 5:
						{
						_localctx = new EqualityContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(258);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(259);
						((EqualityContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__26 || _la==T__27) ) {
							((EqualityContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(260);
						expr(11);
						}
						break;
					case 6:
						{
						_localctx = new TernaryContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(261);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(262);
						match(T__28);
						setState(263);
						expr(0);
						setState(264);
						match(T__3);
						setState(265);
						expr(10);
						}
						break;
					case 7:
						{
						_localctx = new CallContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(267);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(268);
						match(T__1);
						setState(270);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1067300028420L) != 0)) {
							{
							setState(269);
							argumentList();
							}
						}

						setState(272);
						match(T__2);
						}
						break;
					}
					} 
				}
				setState(277);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MatchCaseContext extends ParserRuleContext {
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public MatchCaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchCase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterMatchCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitMatchCase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitMatchCase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchCaseContext matchCase() throws RecognitionException {
		MatchCaseContext _localctx = new MatchCaseContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_matchCase);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			pattern();
			setState(279);
			match(T__29);
			setState(280);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PatternContext extends ParserRuleContext {
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
	 
		public PatternContext() { }
		public void copyFrom(PatternContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WildcardPatternContext extends PatternContext {
		public WildcardPatternContext(PatternContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterWildcardPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitWildcardPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitWildcardPattern(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConstructorPatternContext extends PatternContext {
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public PatternParamListContext patternParamList() {
			return getRuleContext(PatternParamListContext.class,0);
		}
		public ConstructorPatternContext(PatternContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterConstructorPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitConstructorPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitConstructorPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_pattern);
		int _la;
		try {
			setState(291);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__33:
				_localctx = new WildcardPatternContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(282);
				match(T__33);
				}
				break;
			case ID:
				_localctx = new ConstructorPatternContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(283);
				match(ID);
				setState(289);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(284);
					match(T__1);
					setState(286);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__33 || _la==ID) {
						{
						setState(285);
						patternParamList();
						}
					}

					setState(288);
					match(T__2);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PatternParamListContext extends ParserRuleContext {
		public List<PatternParamContext> patternParam() {
			return getRuleContexts(PatternParamContext.class);
		}
		public PatternParamContext patternParam(int i) {
			return getRuleContext(PatternParamContext.class,i);
		}
		public PatternParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patternParamList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterPatternParamList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitPatternParamList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitPatternParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternParamListContext patternParamList() throws RecognitionException {
		PatternParamListContext _localctx = new PatternParamListContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_patternParamList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			patternParam();
			setState(298);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(294);
				match(T__10);
				setState(295);
				patternParam();
				}
				}
				setState(300);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PatternParamContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public PatternParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patternParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterPatternParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitPatternParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitPatternParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternParamContext patternParam() throws RecognitionException {
		PatternParamContext _localctx = new PatternParamContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_patternParam);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			_la = _input.LA(1);
			if ( !(_la==T__33 || _la==ID) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public ArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitArgumentList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitArgumentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentListContext argumentList() throws RecognitionException {
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_argumentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			expr(0);
			setState(308);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(304);
				match(T__10);
				setState(305);
				expr(0);
				}
				}
				setState(310);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamsContext extends ParserRuleContext {
		public List<LambdaParamContext> lambdaParam() {
			return getRuleContexts(LambdaParamContext.class);
		}
		public LambdaParamContext lambdaParam(int i) {
			return getRuleContext(LambdaParamContext.class,i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_params);
		int _la;
		try {
			setState(324);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(311);
				lambdaParam();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(312);
				match(T__1);
				setState(321);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(313);
					lambdaParam();
					setState(318);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__10) {
						{
						{
						setState(314);
						match(T__10);
						setState(315);
						lambdaParam();
						}
						}
						setState(320);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(323);
				match(T__2);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LambdaParamContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public LambdaParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterLambdaParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitLambdaParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitLambdaParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaParamContext lambdaParam() throws RecognitionException {
		LambdaParamContext _localctx = new LambdaParamContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_lambdaParam);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			match(ID);
			setState(329);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(327);
				match(T__3);
				setState(328);
				type();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 12:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 16);
		case 1:
			return precpred(_ctx, 13);
		case 2:
			return precpred(_ctx, 12);
		case 3:
			return precpred(_ctx, 11);
		case 4:
			return precpred(_ctx, 10);
		case 5:
			return precpred(_ctx, 9);
		case 6:
			return precpred(_ctx, 14);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001+\u014c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0001\u0000\u0005\u0000*\b\u0000\n\u0000\f\u0000"+
		"-\t\u0000\u0001\u0000\u0001\u0000\u0004\u00001\b\u0000\u000b\u0000\f\u0000"+
		"2\u0005\u00005\b\u0000\n\u0000\f\u00008\t\u0000\u0001\u0000\u0003\u0000"+
		";\b\u0000\u0001\u0000\u0005\u0000>\b\u0000\n\u0000\f\u0000A\t\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001I\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001Q\b\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0003\u0001X\b\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001]\b\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001c\b\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001g\b\u0001\u0001\u0001\u0003\u0001j\b\u0001\u0001\u0002\u0003\u0002"+
		"m\b\u0002\u0001\u0002\u0001\u0002\u0005\u0002q\b\u0002\n\u0002\f\u0002"+
		"t\t\u0002\u0001\u0002\u0003\u0002w\b\u0002\u0001\u0002\u0005\u0002z\b"+
		"\u0002\n\u0002\f\u0002}\t\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0005\u0003\u0083\b\u0003\n\u0003\f\u0003\u0086\t\u0003\u0001\u0003"+
		"\u0001\u0003\u0005\u0003\u008a\b\u0003\n\u0003\f\u0003\u008d\t\u0003\u0001"+
		"\u0003\u0005\u0003\u0090\b\u0003\n\u0003\f\u0003\u0093\t\u0003\u0001\u0003"+
		"\u0005\u0003\u0096\b\u0003\n\u0003\f\u0003\u0099\t\u0003\u0001\u0003\u0003"+
		"\u0003\u009c\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u00a1"+
		"\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u00a6\b\u0004"+
		"\u0001\u0005\u0001\u0005\u0005\u0005\u00aa\b\u0005\n\u0005\f\u0005\u00ad"+
		"\t\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u00b1\b\u0005\n\u0005\f\u0005"+
		"\u00b4\t\u0005\u0001\u0005\u0005\u0005\u00b7\b\u0005\n\u0005\f\u0005\u00ba"+
		"\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00bf\b\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0005\b\u00c6\b\b\n\b"+
		"\f\b\u00c9\t\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003"+
		"\f\u00da\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0005\f\u00e7\b\f\n\f\f\f\u00ea\t\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003"+
		"\f\u00f5\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u010f"+
		"\b\f\u0001\f\u0005\f\u0112\b\f\n\f\f\f\u0115\t\f\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e"+
		"\u011f\b\u000e\u0001\u000e\u0003\u000e\u0122\b\u000e\u0003\u000e\u0124"+
		"\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u0129\b\u000f"+
		"\n\u000f\f\u000f\u012c\t\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0005\u0011\u0133\b\u0011\n\u0011\f\u0011\u0136\t\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012"+
		"\u013d\b\u0012\n\u0012\f\u0012\u0140\t\u0012\u0003\u0012\u0142\b\u0012"+
		"\u0001\u0012\u0003\u0012\u0145\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0003\u0013\u014a\b\u0013\u0001\u0013\u0000\u0001\u0018\u0014\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"$&\u0000\b\u0002\u0000\f\u0010##\u0001\u0000\f\u000e\u0001\u0000*+"+
		"\u0001\u0000\u0014\u0015\u0002\u0000\u0011\u0011\u0016\u0016\u0001\u0000"+
		"\u0017\u001a\u0001\u0000\u001b\u001c\u0001\u0000\"#\u0174\u0000+\u0001"+
		"\u0000\u0000\u0000\u0002i\u0001\u0000\u0000\u0000\u0004l\u0001\u0000\u0000"+
		"\u0000\u0006\u0080\u0001\u0000\u0000\u0000\b\u009d\u0001\u0000\u0000\u0000"+
		"\n\u00a7\u0001\u0000\u0000\u0000\f\u00bb\u0001\u0000\u0000\u0000\u000e"+
		"\u00c0\u0001\u0000\u0000\u0000\u0010\u00c2\u0001\u0000\u0000\u0000\u0012"+
		"\u00ca\u0001\u0000\u0000\u0000\u0014\u00ce\u0001\u0000\u0000\u0000\u0016"+
		"\u00d0\u0001\u0000\u0000\u0000\u0018\u00f4\u0001\u0000\u0000\u0000\u001a"+
		"\u0116\u0001\u0000\u0000\u0000\u001c\u0123\u0001\u0000\u0000\u0000\u001e"+
		"\u0125\u0001\u0000\u0000\u0000 \u012d\u0001\u0000\u0000\u0000\"\u012f"+
		"\u0001\u0000\u0000\u0000$\u0144\u0001\u0000\u0000\u0000&\u0146\u0001\u0000"+
		"\u0000\u0000(*\u0005(\u0000\u0000)(\u0001\u0000\u0000\u0000*-\u0001\u0000"+
		"\u0000\u0000+)\u0001\u0000\u0000\u0000+,\u0001\u0000\u0000\u0000,6\u0001"+
		"\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000.0\u0003\u0002\u0001\u0000"+
		"/1\u0005(\u0000\u00000/\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u0000"+
		"20\u0001\u0000\u0000\u000023\u0001\u0000\u0000\u000035\u0001\u0000\u0000"+
		"\u00004.\u0001\u0000\u0000\u000058\u0001\u0000\u0000\u000064\u0001\u0000"+
		"\u0000\u000067\u0001\u0000\u0000\u00007:\u0001\u0000\u0000\u000086\u0001"+
		"\u0000\u0000\u00009;\u0003\u0002\u0001\u0000:9\u0001\u0000\u0000\u0000"+
		":;\u0001\u0000\u0000\u0000;?\u0001\u0000\u0000\u0000<>\u0005(\u0000\u0000"+
		"=<\u0001\u0000\u0000\u0000>A\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000"+
		"\u0000?@\u0001\u0000\u0000\u0000@B\u0001\u0000\u0000\u0000A?\u0001\u0000"+
		"\u0000\u0000BC\u0005\u0000\u0000\u0001C\u0001\u0001\u0000\u0000\u0000"+
		"DE\u0005\u0001\u0000\u0000EF\u0005#\u0000\u0000FH\u0005\u0002\u0000\u0000"+
		"GI\u0003\u0010\b\u0000HG\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000"+
		"IJ\u0001\u0000\u0000\u0000JK\u0005\u0003\u0000\u0000KL\u0005\u0004\u0000"+
		"\u0000LM\u0003\u0014\n\u0000MN\u0005\u0005\u0000\u0000NP\u0003\u0018\f"+
		"\u0000OQ\u0003\u0016\u000b\u0000PO\u0001\u0000\u0000\u0000PQ\u0001\u0000"+
		"\u0000\u0000Qj\u0001\u0000\u0000\u0000RS\u0005\u0006\u0000\u0000ST\u0005"+
		"#\u0000\u0000TU\u0005\u0005\u0000\u0000UW\u0003\u0018\f\u0000VX\u0003"+
		"\u0016\u000b\u0000WV\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000"+
		"Xj\u0001\u0000\u0000\u0000YZ\u0005\u0007\u0000\u0000Z\\\u0003\u0018\f"+
		"\u0000[]\u0003\u0016\u000b\u0000\\[\u0001\u0000\u0000\u0000\\]\u0001\u0000"+
		"\u0000\u0000]j\u0001\u0000\u0000\u0000^_\u0005\b\u0000\u0000_`\u0005#"+
		"\u0000\u0000`b\u0003\u0004\u0002\u0000ac\u0003\u0016\u000b\u0000ba\u0001"+
		"\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000cj\u0001\u0000\u0000\u0000"+
		"df\u0003\u0018\f\u0000eg\u0003\u0016\u000b\u0000fe\u0001\u0000\u0000\u0000"+
		"fg\u0001\u0000\u0000\u0000gj\u0001\u0000\u0000\u0000hj\u0003\u0016\u000b"+
		"\u0000iD\u0001\u0000\u0000\u0000iR\u0001\u0000\u0000\u0000iY\u0001\u0000"+
		"\u0000\u0000i^\u0001\u0000\u0000\u0000id\u0001\u0000\u0000\u0000ih\u0001"+
		"\u0000\u0000\u0000j\u0003\u0001\u0000\u0000\u0000km\u0005\u0005\u0000"+
		"\u0000lk\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mn\u0001\u0000"+
		"\u0000\u0000nr\u0005\t\u0000\u0000oq\u0005(\u0000\u0000po\u0001\u0000"+
		"\u0000\u0000qt\u0001\u0000\u0000\u0000rp\u0001\u0000\u0000\u0000rs\u0001"+
		"\u0000\u0000\u0000sv\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000"+
		"uw\u0003\u0006\u0003\u0000vu\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000"+
		"\u0000w{\u0001\u0000\u0000\u0000xz\u0005(\u0000\u0000yx\u0001\u0000\u0000"+
		"\u0000z}\u0001\u0000\u0000\u0000{y\u0001\u0000\u0000\u0000{|\u0001\u0000"+
		"\u0000\u0000|~\u0001\u0000\u0000\u0000}{\u0001\u0000\u0000\u0000~\u007f"+
		"\u0005\n\u0000\u0000\u007f\u0005\u0001\u0000\u0000\u0000\u0080\u0091\u0003"+
		"\b\u0004\u0000\u0081\u0083\u0005(\u0000\u0000\u0082\u0081\u0001\u0000"+
		"\u0000\u0000\u0083\u0086\u0001\u0000\u0000\u0000\u0084\u0082\u0001\u0000"+
		"\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0087\u0001\u0000"+
		"\u0000\u0000\u0086\u0084\u0001\u0000\u0000\u0000\u0087\u008b\u0005\u000b"+
		"\u0000\u0000\u0088\u008a\u0005(\u0000\u0000\u0089\u0088\u0001\u0000\u0000"+
		"\u0000\u008a\u008d\u0001\u0000\u0000\u0000\u008b\u0089\u0001\u0000\u0000"+
		"\u0000\u008b\u008c\u0001\u0000\u0000\u0000\u008c\u008e\u0001\u0000\u0000"+
		"\u0000\u008d\u008b\u0001\u0000\u0000\u0000\u008e\u0090\u0003\b\u0004\u0000"+
		"\u008f\u0084\u0001\u0000\u0000\u0000\u0090\u0093\u0001\u0000\u0000\u0000"+
		"\u0091\u008f\u0001\u0000\u0000\u0000\u0091\u0092\u0001\u0000\u0000\u0000"+
		"\u0092\u0097\u0001\u0000\u0000\u0000\u0093\u0091\u0001\u0000\u0000\u0000"+
		"\u0094\u0096\u0005(\u0000\u0000\u0095\u0094\u0001\u0000\u0000\u0000\u0096"+
		"\u0099\u0001\u0000\u0000\u0000\u0097\u0095\u0001\u0000\u0000\u0000\u0097"+
		"\u0098\u0001\u0000\u0000\u0000\u0098\u009b\u0001\u0000\u0000\u0000\u0099"+
		"\u0097\u0001\u0000\u0000\u0000\u009a\u009c\u0005\u000b\u0000\u0000\u009b"+
		"\u009a\u0001\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c"+
		"\u0007\u0001\u0000\u0000\u0000\u009d\u00a5\u0005#\u0000\u0000\u009e\u00a0"+
		"\u0005\u0002\u0000\u0000\u009f\u00a1\u0003\n\u0005\u0000\u00a0\u009f\u0001"+
		"\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001"+
		"\u0000\u0000\u0000\u00a2\u00a6\u0005\u0003\u0000\u0000\u00a3\u00a4\u0005"+
		"\u0004\u0000\u0000\u00a4\u00a6\u0003\u000e\u0007\u0000\u00a5\u009e\u0001"+
		"\u0000\u0000\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001"+
		"\u0000\u0000\u0000\u00a6\t\u0001\u0000\u0000\u0000\u00a7\u00b8\u0003\f"+
		"\u0006\u0000\u00a8\u00aa\u0005(\u0000\u0000\u00a9\u00a8\u0001\u0000\u0000"+
		"\u0000\u00aa\u00ad\u0001\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000"+
		"\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u00ae\u0001\u0000\u0000"+
		"\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ae\u00b2\u0005\u000b\u0000"+
		"\u0000\u00af\u00b1\u0005(\u0000\u0000\u00b0\u00af\u0001\u0000\u0000\u0000"+
		"\u00b1\u00b4\u0001\u0000\u0000\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000"+
		"\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b5\u0001\u0000\u0000\u0000"+
		"\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b5\u00b7\u0003\f\u0006\u0000\u00b6"+
		"\u00ab\u0001\u0000\u0000\u0000\u00b7\u00ba\u0001\u0000\u0000\u0000\u00b8"+
		"\u00b6\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9"+
		"\u000b\u0001\u0000\u0000\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000\u00bb"+
		"\u00be\u0005#\u0000\u0000\u00bc\u00bd\u0005\u0004\u0000\u0000\u00bd\u00bf"+
		"\u0003\u000e\u0007\u0000\u00be\u00bc\u0001\u0000\u0000\u0000\u00be\u00bf"+
		"\u0001\u0000\u0000\u0000\u00bf\r\u0001\u0000\u0000\u0000\u00c0\u00c1\u0007"+
		"\u0000\u0000\u0000\u00c1\u000f\u0001\u0000\u0000\u0000\u00c2\u00c7\u0003"+
		"\u0012\t\u0000\u00c3\u00c4\u0005\u000b\u0000\u0000\u00c4\u00c6\u0003\u0012"+
		"\t\u0000\u00c5\u00c3\u0001\u0000\u0000\u0000\u00c6\u00c9\u0001\u0000\u0000"+
		"\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000\u00c7\u00c8\u0001\u0000\u0000"+
		"\u0000\u00c8\u0011\u0001\u0000\u0000\u0000\u00c9\u00c7\u0001\u0000\u0000"+
		"\u0000\u00ca\u00cb\u0005#\u0000\u0000\u00cb\u00cc\u0005\u0004\u0000\u0000"+
		"\u00cc\u00cd\u0003\u0014\n\u0000\u00cd\u0013\u0001\u0000\u0000\u0000\u00ce"+
		"\u00cf\u0007\u0001\u0000\u0000\u00cf\u0015\u0001\u0000\u0000\u0000\u00d0"+
		"\u00d1\u0007\u0002\u0000\u0000\u00d1\u0017\u0001\u0000\u0000\u0000\u00d2"+
		"\u00d3\u0006\f\uffff\uffff\u0000\u00d3\u00d4\u0005\u0011\u0000\u0000\u00d4"+
		"\u00f5\u0003\u0018\f\u0011\u00d5\u00d6\u0005\u0013\u0000\u0000\u00d6\u00d7"+
		"\u0005#\u0000\u0000\u00d7\u00d9\u0005\u0002\u0000\u0000\u00d8\u00da\u0003"+
		"\"\u0011\u0000\u00d9\u00d8\u0001\u0000\u0000\u0000\u00d9\u00da\u0001\u0000"+
		"\u0000\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db\u00f5\u0005\u0003"+
		"\u0000\u0000\u00dc\u00dd\u0003$\u0012\u0000\u00dd\u00de\u0005\u001e\u0000"+
		"\u0000\u00de\u00df\u0003\u0018\f\b\u00df\u00f5\u0001\u0000\u0000\u0000"+
		"\u00e0\u00e1\u0005\u001f\u0000\u0000\u00e1\u00e2\u0003\u0018\f\u0000\u00e2"+
		"\u00e3\u0005 \u0000\u0000\u00e3\u00e8\u0003\u001a\r\u0000\u00e4\u00e5"+
		"\u0005!\u0000\u0000\u00e5\u00e7\u0003\u001a\r\u0000\u00e6\u00e4\u0001"+
		"\u0000\u0000\u0000\u00e7\u00ea\u0001\u0000\u0000\u0000\u00e8\u00e6\u0001"+
		"\u0000\u0000\u0000\u00e8\u00e9\u0001\u0000\u0000\u0000\u00e9\u00f5\u0001"+
		"\u0000\u0000\u0000\u00ea\u00e8\u0001\u0000\u0000\u0000\u00eb\u00f5\u0005"+
		"$\u0000\u0000\u00ec\u00f5\u0005%\u0000\u0000\u00ed\u00f5\u0005&\u0000"+
		"\u0000\u00ee\u00f5\u0005\'\u0000\u0000\u00ef\u00f5\u0005#\u0000\u0000"+
		"\u00f0\u00f1\u0005\u0002\u0000\u0000\u00f1\u00f2\u0003\u0018\f\u0000\u00f2"+
		"\u00f3\u0005\u0003\u0000\u0000\u00f3\u00f5\u0001\u0000\u0000\u0000\u00f4"+
		"\u00d2\u0001\u0000\u0000\u0000\u00f4\u00d5\u0001\u0000\u0000\u0000\u00f4"+
		"\u00dc\u0001\u0000\u0000\u0000\u00f4\u00e0\u0001\u0000\u0000\u0000\u00f4"+
		"\u00eb\u0001\u0000\u0000\u0000\u00f4\u00ec\u0001\u0000\u0000\u0000\u00f4"+
		"\u00ed\u0001\u0000\u0000\u0000\u00f4\u00ee\u0001\u0000\u0000\u0000\u00f4"+
		"\u00ef\u0001\u0000\u0000\u0000\u00f4\u00f0\u0001\u0000\u0000\u0000\u00f5"+
		"\u0113\u0001\u0000\u0000\u0000\u00f6\u00f7\n\u0010\u0000\u0000\u00f7\u00f8"+
		"\u0005\u0012\u0000\u0000\u00f8\u0112\u0003\u0018\f\u0011\u00f9\u00fa\n"+
		"\r\u0000\u0000\u00fa\u00fb\u0007\u0003\u0000\u0000\u00fb\u0112\u0003\u0018"+
		"\f\u000e\u00fc\u00fd\n\f\u0000\u0000\u00fd\u00fe\u0007\u0004\u0000\u0000"+
		"\u00fe\u0112\u0003\u0018\f\r\u00ff\u0100\n\u000b\u0000\u0000\u0100\u0101"+
		"\u0007\u0005\u0000\u0000\u0101\u0112\u0003\u0018\f\f\u0102\u0103\n\n\u0000"+
		"\u0000\u0103\u0104\u0007\u0006\u0000\u0000\u0104\u0112\u0003\u0018\f\u000b"+
		"\u0105\u0106\n\t\u0000\u0000\u0106\u0107\u0005\u001d\u0000\u0000\u0107"+
		"\u0108\u0003\u0018\f\u0000\u0108\u0109\u0005\u0004\u0000\u0000\u0109\u010a"+
		"\u0003\u0018\f\n\u010a\u0112\u0001\u0000\u0000\u0000\u010b\u010c\n\u000e"+
		"\u0000\u0000\u010c\u010e\u0005\u0002\u0000\u0000\u010d\u010f\u0003\"\u0011"+
		"\u0000\u010e\u010d\u0001\u0000\u0000\u0000\u010e\u010f\u0001\u0000\u0000"+
		"\u0000\u010f\u0110\u0001\u0000\u0000\u0000\u0110\u0112\u0005\u0003\u0000"+
		"\u0000\u0111\u00f6\u0001\u0000\u0000\u0000\u0111\u00f9\u0001\u0000\u0000"+
		"\u0000\u0111\u00fc\u0001\u0000\u0000\u0000\u0111\u00ff\u0001\u0000\u0000"+
		"\u0000\u0111\u0102\u0001\u0000\u0000\u0000\u0111\u0105\u0001\u0000\u0000"+
		"\u0000\u0111\u010b\u0001\u0000\u0000\u0000\u0112\u0115\u0001\u0000\u0000"+
		"\u0000\u0113\u0111\u0001\u0000\u0000\u0000\u0113\u0114\u0001\u0000\u0000"+
		"\u0000\u0114\u0019\u0001\u0000\u0000\u0000\u0115\u0113\u0001\u0000\u0000"+
		"\u0000\u0116\u0117\u0003\u001c\u000e\u0000\u0117\u0118\u0005\u001e\u0000"+
		"\u0000\u0118\u0119\u0003\u0018\f\u0000\u0119\u001b\u0001\u0000\u0000\u0000"+
		"\u011a\u0124\u0005\"\u0000\u0000\u011b\u0121\u0005#\u0000\u0000\u011c"+
		"\u011e\u0005\u0002\u0000\u0000\u011d\u011f\u0003\u001e\u000f\u0000\u011e"+
		"\u011d\u0001\u0000\u0000\u0000\u011e\u011f\u0001\u0000\u0000\u0000\u011f"+
		"\u0120\u0001\u0000\u0000\u0000\u0120\u0122\u0005\u0003\u0000\u0000\u0121"+
		"\u011c\u0001\u0000\u0000\u0000\u0121\u0122\u0001\u0000\u0000\u0000\u0122"+
		"\u0124\u0001\u0000\u0000\u0000\u0123\u011a\u0001\u0000\u0000\u0000\u0123"+
		"\u011b\u0001\u0000\u0000\u0000\u0124\u001d\u0001\u0000\u0000\u0000\u0125"+
		"\u012a\u0003 \u0010\u0000\u0126\u0127\u0005\u000b\u0000\u0000\u0127\u0129"+
		"\u0003 \u0010\u0000\u0128\u0126\u0001\u0000\u0000\u0000\u0129\u012c\u0001"+
		"\u0000\u0000\u0000\u012a\u0128\u0001\u0000\u0000\u0000\u012a\u012b\u0001"+
		"\u0000\u0000\u0000\u012b\u001f\u0001\u0000\u0000\u0000\u012c\u012a\u0001"+
		"\u0000\u0000\u0000\u012d\u012e\u0007\u0007\u0000\u0000\u012e!\u0001\u0000"+
		"\u0000\u0000\u012f\u0134\u0003\u0018\f\u0000\u0130\u0131\u0005\u000b\u0000"+
		"\u0000\u0131\u0133\u0003\u0018\f\u0000\u0132\u0130\u0001\u0000\u0000\u0000"+
		"\u0133\u0136\u0001\u0000\u0000\u0000\u0134\u0132\u0001\u0000\u0000\u0000"+
		"\u0134\u0135\u0001\u0000\u0000\u0000\u0135#\u0001\u0000\u0000\u0000\u0136"+
		"\u0134\u0001\u0000\u0000\u0000\u0137\u0145\u0003&\u0013\u0000\u0138\u0141"+
		"\u0005\u0002\u0000\u0000\u0139\u013e\u0003&\u0013\u0000\u013a\u013b\u0005"+
		"\u000b\u0000\u0000\u013b\u013d\u0003&\u0013\u0000\u013c\u013a\u0001\u0000"+
		"\u0000\u0000\u013d\u0140\u0001\u0000\u0000\u0000\u013e\u013c\u0001\u0000"+
		"\u0000\u0000\u013e\u013f\u0001\u0000\u0000\u0000\u013f\u0142\u0001\u0000"+
		"\u0000\u0000\u0140\u013e\u0001\u0000\u0000\u0000\u0141\u0139\u0001\u0000"+
		"\u0000\u0000\u0141\u0142\u0001\u0000\u0000\u0000\u0142\u0143\u0001\u0000"+
		"\u0000\u0000\u0143\u0145\u0005\u0003\u0000\u0000\u0144\u0137\u0001\u0000"+
		"\u0000\u0000\u0144\u0138\u0001\u0000\u0000\u0000\u0145%\u0001\u0000\u0000"+
		"\u0000\u0146\u0149\u0005#\u0000\u0000\u0147\u0148\u0005\u0004\u0000\u0000"+
		"\u0148\u014a\u0003\u0014\n\u0000\u0149\u0147\u0001\u0000\u0000\u0000\u0149"+
		"\u014a\u0001\u0000\u0000\u0000\u014a\'\u0001\u0000\u0000\u0000++26:?H"+
		"PW\\bfilrv{\u0084\u008b\u0091\u0097\u009b\u00a0\u00a5\u00ab\u00b2\u00b8"+
		"\u00be\u00c7\u00d9\u00e8\u00f4\u010e\u0111\u0113\u011e\u0121\u0123\u012a"+
		"\u0134\u013e\u0141\u0144\u0149";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}