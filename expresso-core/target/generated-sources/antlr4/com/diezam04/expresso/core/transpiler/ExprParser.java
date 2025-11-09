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
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, PRINT=38, 
		ID=39, FLOAT_E=40, FLOAT=41, INT=42, STRING=43, NEWLINE=44, WS=45, LINE_COMMENT=46, 
		BLOCK_COMMENT=47;
	public static final int
		RULE_prog = 0, RULE_stat = 1, RULE_dataBlock = 2, RULE_constructorList = 3, 
		RULE_dataConstructor = 4, RULE_dataFieldList = 5, RULE_dataField = 6, 
		RULE_typeRef = 7, RULE_functionType = 8, RULE_typeAtom = 9, RULE_paramDeclList = 10, 
		RULE_paramDecl = 11, RULE_type = 12, RULE_comment = 13, RULE_expr = 14, 
		RULE_matchCase = 15, RULE_matchArrow = 16, RULE_matchCaseSeparator = 17, 
		RULE_pattern = 18, RULE_patternParamList = 19, RULE_patternParam = 20, 
		RULE_argumentList = 21, RULE_params = 22, RULE_lambdaParam = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "stat", "dataBlock", "constructorList", "dataConstructor", "dataFieldList", 
			"dataField", "typeRef", "functionType", "typeAtom", "paramDeclList", 
			"paramDecl", "type", "comment", "expr", "matchCase", "matchArrow", "matchCaseSeparator", 
			"pattern", "patternParamList", "patternParam", "argumentList", "params", 
			"lambdaParam"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'fun'", "'('", "')'", "':'", "'='", "'let'", "'data'", "'{'", 
			"'}'", "','", "'->'", "'int'", "'float'", "'double'", "'string'", "'boolean'", 
			"'any'", "'-'", "'**'", "'^'", "'*'", "'/'", "'+'", "'>='", "'<='", "'>'", 
			"'<'", "'=='", "'!='", "'?'", "'match'", "'with'", "'|'", "'case'", "'default'", 
			"'=>'", "'_'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, "PRINT", "ID", "FLOAT_E", "FLOAT", "INT", "STRING", "NEWLINE", 
			"WS", "LINE_COMMENT", "BLOCK_COMMENT"
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
			setState(51);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(48);
					match(NEWLINE);
					}
					} 
				}
				setState(53);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(62);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(54);
					stat();
					setState(56); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(55);
							match(NEWLINE);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(58); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					} 
				}
				setState(64);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 228425689465030L) != 0)) {
				{
				setState(65);
				stat();
				}
			}

			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(68);
				match(NEWLINE);
				}
				}
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(74);
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
		public TerminalNode PRINT() { return getToken(ExprParser.PRINT, 0); }
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
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParamDeclListContext paramDeclList() {
			return getRuleContext(ParamDeclListContext.class,0);
		}
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ExprParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExprParser.NEWLINE, i);
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
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(ExprParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExprParser.NEWLINE, i);
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
			setState(131);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				_localctx = new FunStatContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				match(T__0);
				setState(77);
				match(ID);
				setState(78);
				match(T__1);
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(79);
					paramDeclList();
					}
				}

				setState(82);
				match(T__2);
				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(83);
					match(T__3);
					setState(84);
					typeRef();
					}
				}

				setState(87);
				match(T__4);
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(88);
					match(NEWLINE);
					}
					}
					setState(93);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(94);
				expr(0);
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LINE_COMMENT || _la==BLOCK_COMMENT) {
					{
					setState(95);
					comment();
					}
				}

				}
				break;
			case 2:
				_localctx = new LetStatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(98);
				match(T__5);
				setState(99);
				match(ID);
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(100);
					match(T__3);
					setState(101);
					typeRef();
					}
				}

				setState(104);
				match(T__4);
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(105);
					match(NEWLINE);
					}
					}
					setState(110);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(111);
				expr(0);
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LINE_COMMENT || _la==BLOCK_COMMENT) {
					{
					setState(112);
					comment();
					}
				}

				}
				break;
			case 3:
				_localctx = new PrintStatContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(115);
				match(PRINT);
				setState(116);
				expr(0);
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LINE_COMMENT || _la==BLOCK_COMMENT) {
					{
					setState(117);
					comment();
					}
				}

				}
				break;
			case 4:
				_localctx = new DataStatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(120);
				match(T__6);
				setState(121);
				match(ID);
				setState(122);
				dataBlock();
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LINE_COMMENT || _la==BLOCK_COMMENT) {
					{
					setState(123);
					comment();
					}
				}

				}
				break;
			case 5:
				_localctx = new ExprStatContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(126);
				expr(0);
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LINE_COMMENT || _la==BLOCK_COMMENT) {
					{
					setState(127);
					comment();
					}
				}

				}
				break;
			case 6:
				_localctx = new CommentStatContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(130);
				comment();
				}
				break;
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
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(133);
				match(T__4);
				}
			}

			}
			setState(136);
			match(T__7);
			setState(140);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(137);
					match(NEWLINE);
					}
					} 
				}
				setState(142);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(143);
				constructorList();
				}
			}

			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(146);
				match(NEWLINE);
				}
				}
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(152);
			match(T__8);
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
			setState(154);
			dataConstructor();
			setState(171);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(158);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEWLINE) {
						{
						{
						setState(155);
						match(NEWLINE);
						}
						}
						setState(160);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(161);
					match(T__9);
					setState(165);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEWLINE) {
						{
						{
						setState(162);
						match(NEWLINE);
						}
						}
						setState(167);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(168);
					dataConstructor();
					}
					} 
				}
				setState(173);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}
			setState(177);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(174);
					match(NEWLINE);
					}
					} 
				}
				setState(179);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			setState(181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(180);
				match(T__9);
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
			setState(183);
			match(ID);
			setState(191);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				{
				setState(184);
				match(T__1);
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(185);
					dataFieldList();
					}
				}

				setState(188);
				match(T__2);
				}
				break;
			case T__3:
				{
				setState(189);
				match(T__3);
				setState(190);
				typeRef();
				}
				break;
			case T__8:
			case T__9:
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
			setState(193);
			dataField();
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9 || _la==NEWLINE) {
				{
				{
				setState(197);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(194);
					match(NEWLINE);
					}
					}
					setState(199);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(200);
				match(T__9);
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(201);
					match(NEWLINE);
					}
					}
					setState(206);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(207);
				dataField();
				}
				}
				setState(212);
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
			setState(213);
			match(ID);
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(214);
				match(T__3);
				setState(215);
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
		public FunctionTypeContext functionType() {
			return getRuleContext(FunctionTypeContext.class,0);
		}
		public TypeAtomContext typeAtom() {
			return getRuleContext(TypeAtomContext.class,0);
		}
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
		try {
			setState(220);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				functionType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(219);
				typeAtom();
				}
				break;
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
	public static class FunctionTypeContext extends ParserRuleContext {
		public TypeAtomContext typeAtom() {
			return getRuleContext(TypeAtomContext.class,0);
		}
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public FunctionTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterFunctionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitFunctionType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitFunctionType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionTypeContext functionType() throws RecognitionException {
		FunctionTypeContext _localctx = new FunctionTypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_functionType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			typeAtom();
			setState(223);
			match(T__10);
			setState(224);
			typeRef();
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
	public static class TypeAtomContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public List<TypeRefContext> typeRef() {
			return getRuleContexts(TypeRefContext.class);
		}
		public TypeRefContext typeRef(int i) {
			return getRuleContext(TypeRefContext.class,i);
		}
		public TypeAtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeAtom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterTypeAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitTypeAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitTypeAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeAtomContext typeAtom() throws RecognitionException {
		TypeAtomContext _localctx = new TypeAtomContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_typeAtom);
		int _la;
		try {
			setState(243);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__11:
				enterOuterAlt(_localctx, 1);
				{
				setState(226);
				match(T__11);
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 2);
				{
				setState(227);
				match(T__12);
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 3);
				{
				setState(228);
				match(T__13);
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 4);
				{
				setState(229);
				match(T__14);
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 5);
				{
				setState(230);
				match(T__15);
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 6);
				{
				setState(231);
				match(T__16);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 7);
				{
				setState(232);
				match(ID);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 8);
				{
				setState(233);
				match(T__1);
				setState(234);
				typeRef();
				setState(237); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(235);
					match(T__9);
					setState(236);
					typeRef();
					}
					}
					setState(239); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__9 );
				setState(241);
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
		enterRule(_localctx, 20, RULE_paramDeclList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			paramDecl();
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(246);
				match(T__9);
				setState(247);
				paramDecl();
				}
				}
				setState(252);
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
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
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
		enterRule(_localctx, 22, RULE_paramDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			match(ID);
			setState(254);
			match(T__3);
			setState(255);
			typeRef();
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
		enterRule(_localctx, 24, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 45056L) != 0)) ) {
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
		enterRule(_localctx, 26, RULE_comment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
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
		public List<TerminalNode> NEWLINE() { return getTokens(ExprParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExprParser.NEWLINE, i);
		}
		public List<MatchCaseSeparatorContext> matchCaseSeparator() {
			return getRuleContexts(MatchCaseSeparatorContext.class);
		}
		public MatchCaseSeparatorContext matchCaseSeparator(int i) {
			return getRuleContext(MatchCaseSeparatorContext.class,i);
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
	public static class TypeCastContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
		}
		public TypeCastContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterTypeCast(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitTypeCast(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitTypeCast(this);
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
	@SuppressWarnings("CheckReturnValue")
	public static class PrintExprContext extends ExprContext {
		public TerminalNode PRINT() { return getToken(ExprParser.PRINT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PrintExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterPrintExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitPrintExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitPrintExpr(this);
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
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(344);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				_localctx = new UnaryMinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(262);
				match(T__17);
				setState(263);
				expr(19);
				}
				break;
			case 2:
				{
				_localctx = new CtorCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(264);
				match(T__19);
				setState(265);
				match(ID);
				setState(266);
				match(T__1);
				setState(268);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17319456931844L) != 0)) {
					{
					setState(267);
					argumentList();
					}
				}

				setState(270);
				match(T__2);
				}
				break;
			case 3:
				{
				_localctx = new PrintExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(271);
				match(PRINT);
				setState(272);
				expr(14);
				}
				break;
			case 4:
				{
				_localctx = new LambdaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(273);
				params();
				setState(274);
				match(T__10);
				setState(275);
				expr(8);
				}
				break;
			case 5:
				{
				_localctx = new MatchExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(277);
				match(T__30);
				setState(278);
				expr(0);
				setState(282);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(279);
					match(NEWLINE);
					}
					}
					setState(284);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(333);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__31:
					{
					setState(285);
					match(T__31);
					setState(289);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEWLINE) {
						{
						{
						setState(286);
						match(NEWLINE);
						}
						}
						setState(291);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(292);
					matchCase();
					setState(309);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(296);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==NEWLINE) {
								{
								{
								setState(293);
								match(NEWLINE);
								}
								}
								setState(298);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							setState(299);
							match(T__32);
							setState(303);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==NEWLINE) {
								{
								{
								setState(300);
								match(NEWLINE);
								}
								}
								setState(305);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							setState(306);
							matchCase();
							}
							} 
						}
						setState(311);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
					}
					}
					break;
				case T__7:
					{
					setState(312);
					match(T__7);
					setState(316);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEWLINE) {
						{
						{
						setState(313);
						match(NEWLINE);
						}
						}
						setState(318);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(319);
					matchCase();
					setState(325);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(320);
							matchCaseSeparator();
							setState(321);
							matchCase();
							}
							} 
						}
						setState(327);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
					}
					setState(329);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__9 || _la==NEWLINE) {
						{
						setState(328);
						matchCaseSeparator();
						}
					}

					setState(331);
					match(T__8);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 6:
				{
				_localctx = new EulerFloatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(335);
				match(FLOAT_E);
				}
				break;
			case 7:
				{
				_localctx = new FloatContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(336);
				match(FLOAT);
				}
				break;
			case 8:
				{
				_localctx = new IntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(337);
				match(INT);
				}
				break;
			case 9:
				{
				_localctx = new StringLitContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(338);
				match(STRING);
				}
				break;
			case 10:
				{
				_localctx = new IdRefContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(339);
				match(ID);
				}
				break;
			case 11:
				{
				_localctx = new ParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(340);
				match(T__1);
				setState(341);
				expr(0);
				setState(342);
				match(T__2);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(378);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(376);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
					case 1:
						{
						_localctx = new PowerContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(346);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(347);
						match(T__18);
						setState(348);
						expr(19);
						}
						break;
					case 2:
						{
						_localctx = new MulDivContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(349);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(350);
						((MulDivContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__20 || _la==T__21) ) {
							((MulDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(351);
						expr(14);
						}
						break;
					case 3:
						{
						_localctx = new AddSubContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(352);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(353);
						((AddSubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__17 || _la==T__22) ) {
							((AddSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(354);
						expr(13);
						}
						break;
					case 4:
						{
						_localctx = new RelationContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(355);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(356);
						((RelationContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 251658240L) != 0)) ) {
							((RelationContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(357);
						expr(12);
						}
						break;
					case 5:
						{
						_localctx = new EqualityContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(358);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(359);
						((EqualityContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__27 || _la==T__28) ) {
							((EqualityContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(360);
						expr(11);
						}
						break;
					case 6:
						{
						_localctx = new TernaryContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(361);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(362);
						match(T__29);
						setState(363);
						expr(0);
						setState(364);
						match(T__3);
						setState(365);
						expr(10);
						}
						break;
					case 7:
						{
						_localctx = new CallContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(367);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(368);
						match(T__1);
						setState(370);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 17319456931844L) != 0)) {
							{
							setState(369);
							argumentList();
							}
						}

						setState(372);
						match(T__2);
						}
						break;
					case 8:
						{
						_localctx = new TypeCastContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(373);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(374);
						match(T__3);
						setState(375);
						typeRef();
						}
						break;
					}
					} 
				}
				setState(380);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
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
		public MatchArrowContext matchArrow() {
			return getRuleContext(MatchArrowContext.class,0);
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
		enterRule(_localctx, 30, RULE_matchCase);
		int _la;
		try {
			setState(392);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__33:
			case T__36:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(382);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__33) {
					{
					setState(381);
					match(T__33);
					}
				}

				setState(384);
				pattern();
				setState(385);
				matchArrow();
				setState(386);
				expr(0);
				}
				break;
			case T__34:
				enterOuterAlt(_localctx, 2);
				{
				setState(388);
				match(T__34);
				setState(389);
				matchArrow();
				setState(390);
				expr(0);
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
	public static class MatchArrowContext extends ParserRuleContext {
		public MatchArrowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchArrow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterMatchArrow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitMatchArrow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitMatchArrow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchArrowContext matchArrow() throws RecognitionException {
		MatchArrowContext _localctx = new MatchArrowContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_matchArrow);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(394);
			_la = _input.LA(1);
			if ( !(_la==T__10 || _la==T__35) ) {
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
	public static class MatchCaseSeparatorContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(ExprParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(ExprParser.NEWLINE, i);
		}
		public MatchCaseSeparatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchCaseSeparator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).enterMatchCaseSeparator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprListener ) ((ExprListener)listener).exitMatchCaseSeparator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprVisitor ) return ((ExprVisitor<? extends T>)visitor).visitMatchCaseSeparator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchCaseSeparatorContext matchCaseSeparator() throws RecognitionException {
		MatchCaseSeparatorContext _localctx = new MatchCaseSeparatorContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_matchCaseSeparator);
		int _la;
		try {
			setState(417);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NEWLINE:
				enterOuterAlt(_localctx, 1);
				{
				setState(397); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(396);
					match(NEWLINE);
					}
					}
					setState(399); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==NEWLINE );
				setState(408);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__9) {
					{
					setState(401);
					match(T__9);
					setState(405);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEWLINE) {
						{
						{
						setState(402);
						match(NEWLINE);
						}
						}
						setState(407);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(410);
				match(T__9);
				setState(414);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(411);
					match(NEWLINE);
					}
					}
					setState(416);
					_errHandler.sync(this);
					_la = _input.LA(1);
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
		enterRule(_localctx, 36, RULE_pattern);
		int _la;
		try {
			setState(428);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__36:
				_localctx = new WildcardPatternContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(419);
				match(T__36);
				}
				break;
			case ID:
				_localctx = new ConstructorPatternContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(420);
				match(ID);
				setState(426);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(421);
					match(T__1);
					setState(423);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__36 || _la==ID) {
						{
						setState(422);
						patternParamList();
						}
					}

					setState(425);
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
		enterRule(_localctx, 38, RULE_patternParamList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(430);
			patternParam();
			setState(435);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(431);
				match(T__9);
				setState(432);
				patternParam();
				}
				}
				setState(437);
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
		enterRule(_localctx, 40, RULE_patternParam);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(438);
			_la = _input.LA(1);
			if ( !(_la==T__36 || _la==ID) ) {
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
		enterRule(_localctx, 42, RULE_argumentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(440);
			expr(0);
			setState(445);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(441);
				match(T__9);
				setState(442);
				expr(0);
				}
				}
				setState(447);
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
		enterRule(_localctx, 44, RULE_params);
		int _la;
		try {
			setState(461);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(448);
				lambdaParam();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(449);
				match(T__1);
				setState(458);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(450);
					lambdaParam();
					setState(455);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__9) {
						{
						{
						setState(451);
						match(T__9);
						setState(452);
						lambdaParam();
						}
						}
						setState(457);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(460);
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
		public TypeRefContext typeRef() {
			return getRuleContext(TypeRefContext.class,0);
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
		enterRule(_localctx, 46, RULE_lambdaParam);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(463);
			match(ID);
			setState(466);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(464);
				match(T__3);
				setState(465);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 14:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 18);
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
			return precpred(_ctx, 16);
		case 7:
			return precpred(_ctx, 15);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001/\u01d5\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0001\u0000\u0005\u0000"+
		"2\b\u0000\n\u0000\f\u00005\t\u0000\u0001\u0000\u0001\u0000\u0004\u0000"+
		"9\b\u0000\u000b\u0000\f\u0000:\u0005\u0000=\b\u0000\n\u0000\f\u0000@\t"+
		"\u0000\u0001\u0000\u0003\u0000C\b\u0000\u0001\u0000\u0005\u0000F\b\u0000"+
		"\n\u0000\f\u0000I\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001Q\b\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0003\u0001V\b\u0001\u0001\u0001\u0001\u0001\u0005\u0001"+
		"Z\b\u0001\n\u0001\f\u0001]\t\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"a\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"g\b\u0001\u0001\u0001\u0001\u0001\u0005\u0001k\b\u0001\n\u0001\f\u0001"+
		"n\t\u0001\u0001\u0001\u0001\u0001\u0003\u0001r\b\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001w\b\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001}\b\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001\u0081\b\u0001\u0001\u0001\u0003\u0001\u0084\b\u0001\u0001\u0002"+
		"\u0003\u0002\u0087\b\u0002\u0001\u0002\u0001\u0002\u0005\u0002\u008b\b"+
		"\u0002\n\u0002\f\u0002\u008e\t\u0002\u0001\u0002\u0003\u0002\u0091\b\u0002"+
		"\u0001\u0002\u0005\u0002\u0094\b\u0002\n\u0002\f\u0002\u0097\t\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0005\u0003\u009d\b\u0003\n"+
		"\u0003\f\u0003\u00a0\t\u0003\u0001\u0003\u0001\u0003\u0005\u0003\u00a4"+
		"\b\u0003\n\u0003\f\u0003\u00a7\t\u0003\u0001\u0003\u0005\u0003\u00aa\b"+
		"\u0003\n\u0003\f\u0003\u00ad\t\u0003\u0001\u0003\u0005\u0003\u00b0\b\u0003"+
		"\n\u0003\f\u0003\u00b3\t\u0003\u0001\u0003\u0003\u0003\u00b6\b\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u00bb\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0003\u0004\u00c0\b\u0004\u0001\u0005\u0001\u0005\u0005"+
		"\u0005\u00c4\b\u0005\n\u0005\f\u0005\u00c7\t\u0005\u0001\u0005\u0001\u0005"+
		"\u0005\u0005\u00cb\b\u0005\n\u0005\f\u0005\u00ce\t\u0005\u0001\u0005\u0005"+
		"\u0005\u00d1\b\u0005\n\u0005\f\u0005\u00d4\t\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0003\u0006\u00d9\b\u0006\u0001\u0007\u0001\u0007\u0003\u0007"+
		"\u00dd\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0004"+
		"\t\u00ee\b\t\u000b\t\f\t\u00ef\u0001\t\u0001\t\u0003\t\u00f4\b\t\u0001"+
		"\n\u0001\n\u0001\n\u0005\n\u00f9\b\n\n\n\f\n\u00fc\t\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0003\u000e\u010d\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0005\u000e\u0119\b\u000e\n\u000e\f\u000e\u011c\t\u000e\u0001\u000e\u0001"+
		"\u000e\u0005\u000e\u0120\b\u000e\n\u000e\f\u000e\u0123\t\u000e\u0001\u000e"+
		"\u0001\u000e\u0005\u000e\u0127\b\u000e\n\u000e\f\u000e\u012a\t\u000e\u0001"+
		"\u000e\u0001\u000e\u0005\u000e\u012e\b\u000e\n\u000e\f\u000e\u0131\t\u000e"+
		"\u0001\u000e\u0005\u000e\u0134\b\u000e\n\u000e\f\u000e\u0137\t\u000e\u0001"+
		"\u000e\u0001\u000e\u0005\u000e\u013b\b\u000e\n\u000e\f\u000e\u013e\t\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u0144\b\u000e"+
		"\n\u000e\f\u000e\u0147\t\u000e\u0001\u000e\u0003\u000e\u014a\b\u000e\u0001"+
		"\u000e\u0001\u000e\u0003\u000e\u014e\b\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0003\u000e\u0159\b\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u0173\b\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u0179\b\u000e\n\u000e\f\u000e"+
		"\u017c\t\u000e\u0001\u000f\u0003\u000f\u017f\b\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0003\u000f\u0189\b\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0004"+
		"\u0011\u018e\b\u0011\u000b\u0011\f\u0011\u018f\u0001\u0011\u0001\u0011"+
		"\u0005\u0011\u0194\b\u0011\n\u0011\f\u0011\u0197\t\u0011\u0003\u0011\u0199"+
		"\b\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u019d\b\u0011\n\u0011\f\u0011"+
		"\u01a0\t\u0011\u0003\u0011\u01a2\b\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0003\u0012\u01a8\b\u0012\u0001\u0012\u0003\u0012\u01ab"+
		"\b\u0012\u0003\u0012\u01ad\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0005\u0013\u01b2\b\u0013\n\u0013\f\u0013\u01b5\t\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u01bc\b\u0015\n"+
		"\u0015\f\u0015\u01bf\t\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0005\u0016\u01c6\b\u0016\n\u0016\f\u0016\u01c9\t\u0016"+
		"\u0003\u0016\u01cb\b\u0016\u0001\u0016\u0003\u0016\u01ce\b\u0016\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u01d3\b\u0017\u0001\u0017\u0000"+
		"\u0001\u001c\u0018\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u001c\u001e \"$&(*,.\u0000\b\u0002\u0000\f\r\u000f"+
		"\u000f\u0001\u0000./\u0001\u0000\u0015\u0016\u0002\u0000\u0012\u0012\u0017"+
		"\u0017\u0001\u0000\u0018\u001b\u0001\u0000\u001c\u001d\u0002\u0000\u000b"+
		"\u000b$$\u0002\u0000%%\'\'\u0217\u00003\u0001\u0000\u0000\u0000\u0002"+
		"\u0083\u0001\u0000\u0000\u0000\u0004\u0086\u0001\u0000\u0000\u0000\u0006"+
		"\u009a\u0001\u0000\u0000\u0000\b\u00b7\u0001\u0000\u0000\u0000\n\u00c1"+
		"\u0001\u0000\u0000\u0000\f\u00d5\u0001\u0000\u0000\u0000\u000e\u00dc\u0001"+
		"\u0000\u0000\u0000\u0010\u00de\u0001\u0000\u0000\u0000\u0012\u00f3\u0001"+
		"\u0000\u0000\u0000\u0014\u00f5\u0001\u0000\u0000\u0000\u0016\u00fd\u0001"+
		"\u0000\u0000\u0000\u0018\u0101\u0001\u0000\u0000\u0000\u001a\u0103\u0001"+
		"\u0000\u0000\u0000\u001c\u0158\u0001\u0000\u0000\u0000\u001e\u0188\u0001"+
		"\u0000\u0000\u0000 \u018a\u0001\u0000\u0000\u0000\"\u01a1\u0001\u0000"+
		"\u0000\u0000$\u01ac\u0001\u0000\u0000\u0000&\u01ae\u0001\u0000\u0000\u0000"+
		"(\u01b6\u0001\u0000\u0000\u0000*\u01b8\u0001\u0000\u0000\u0000,\u01cd"+
		"\u0001\u0000\u0000\u0000.\u01cf\u0001\u0000\u0000\u000002\u0005,\u0000"+
		"\u000010\u0001\u0000\u0000\u000025\u0001\u0000\u0000\u000031\u0001\u0000"+
		"\u0000\u000034\u0001\u0000\u0000\u00004>\u0001\u0000\u0000\u000053\u0001"+
		"\u0000\u0000\u000068\u0003\u0002\u0001\u000079\u0005,\u0000\u000087\u0001"+
		"\u0000\u0000\u00009:\u0001\u0000\u0000\u0000:8\u0001\u0000\u0000\u0000"+
		":;\u0001\u0000\u0000\u0000;=\u0001\u0000\u0000\u0000<6\u0001\u0000\u0000"+
		"\u0000=@\u0001\u0000\u0000\u0000><\u0001\u0000\u0000\u0000>?\u0001\u0000"+
		"\u0000\u0000?B\u0001\u0000\u0000\u0000@>\u0001\u0000\u0000\u0000AC\u0003"+
		"\u0002\u0001\u0000BA\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000"+
		"CG\u0001\u0000\u0000\u0000DF\u0005,\u0000\u0000ED\u0001\u0000\u0000\u0000"+
		"FI\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000GH\u0001\u0000\u0000"+
		"\u0000HJ\u0001\u0000\u0000\u0000IG\u0001\u0000\u0000\u0000JK\u0005\u0000"+
		"\u0000\u0001K\u0001\u0001\u0000\u0000\u0000LM\u0005\u0001\u0000\u0000"+
		"MN\u0005\'\u0000\u0000NP\u0005\u0002\u0000\u0000OQ\u0003\u0014\n\u0000"+
		"PO\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000"+
		"\u0000RU\u0005\u0003\u0000\u0000ST\u0005\u0004\u0000\u0000TV\u0003\u000e"+
		"\u0007\u0000US\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000VW\u0001"+
		"\u0000\u0000\u0000W[\u0005\u0005\u0000\u0000XZ\u0005,\u0000\u0000YX\u0001"+
		"\u0000\u0000\u0000Z]\u0001\u0000\u0000\u0000[Y\u0001\u0000\u0000\u0000"+
		"[\\\u0001\u0000\u0000\u0000\\^\u0001\u0000\u0000\u0000][\u0001\u0000\u0000"+
		"\u0000^`\u0003\u001c\u000e\u0000_a\u0003\u001a\r\u0000`_\u0001\u0000\u0000"+
		"\u0000`a\u0001\u0000\u0000\u0000a\u0084\u0001\u0000\u0000\u0000bc\u0005"+
		"\u0006\u0000\u0000cf\u0005\'\u0000\u0000de\u0005\u0004\u0000\u0000eg\u0003"+
		"\u000e\u0007\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000"+
		"gh\u0001\u0000\u0000\u0000hl\u0005\u0005\u0000\u0000ik\u0005,\u0000\u0000"+
		"ji\u0001\u0000\u0000\u0000kn\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000"+
		"\u0000lm\u0001\u0000\u0000\u0000mo\u0001\u0000\u0000\u0000nl\u0001\u0000"+
		"\u0000\u0000oq\u0003\u001c\u000e\u0000pr\u0003\u001a\r\u0000qp\u0001\u0000"+
		"\u0000\u0000qr\u0001\u0000\u0000\u0000r\u0084\u0001\u0000\u0000\u0000"+
		"st\u0005&\u0000\u0000tv\u0003\u001c\u000e\u0000uw\u0003\u001a\r\u0000"+
		"vu\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000w\u0084\u0001\u0000"+
		"\u0000\u0000xy\u0005\u0007\u0000\u0000yz\u0005\'\u0000\u0000z|\u0003\u0004"+
		"\u0002\u0000{}\u0003\u001a\r\u0000|{\u0001\u0000\u0000\u0000|}\u0001\u0000"+
		"\u0000\u0000}\u0084\u0001\u0000\u0000\u0000~\u0080\u0003\u001c\u000e\u0000"+
		"\u007f\u0081\u0003\u001a\r\u0000\u0080\u007f\u0001\u0000\u0000\u0000\u0080"+
		"\u0081\u0001\u0000\u0000\u0000\u0081\u0084\u0001\u0000\u0000\u0000\u0082"+
		"\u0084\u0003\u001a\r\u0000\u0083L\u0001\u0000\u0000\u0000\u0083b\u0001"+
		"\u0000\u0000\u0000\u0083s\u0001\u0000\u0000\u0000\u0083x\u0001\u0000\u0000"+
		"\u0000\u0083~\u0001\u0000\u0000\u0000\u0083\u0082\u0001\u0000\u0000\u0000"+
		"\u0084\u0003\u0001\u0000\u0000\u0000\u0085\u0087\u0005\u0005\u0000\u0000"+
		"\u0086\u0085\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000"+
		"\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u008c\u0005\b\u0000\u0000\u0089"+
		"\u008b\u0005,\u0000\u0000\u008a\u0089\u0001\u0000\u0000\u0000\u008b\u008e"+
		"\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008c\u008d"+
		"\u0001\u0000\u0000\u0000\u008d\u0090\u0001\u0000\u0000\u0000\u008e\u008c"+
		"\u0001\u0000\u0000\u0000\u008f\u0091\u0003\u0006\u0003\u0000\u0090\u008f"+
		"\u0001\u0000\u0000\u0000\u0090\u0091\u0001\u0000\u0000\u0000\u0091\u0095"+
		"\u0001\u0000\u0000\u0000\u0092\u0094\u0005,\u0000\u0000\u0093\u0092\u0001"+
		"\u0000\u0000\u0000\u0094\u0097\u0001\u0000\u0000\u0000\u0095\u0093\u0001"+
		"\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096\u0098\u0001"+
		"\u0000\u0000\u0000\u0097\u0095\u0001\u0000\u0000\u0000\u0098\u0099\u0005"+
		"\t\u0000\u0000\u0099\u0005\u0001\u0000\u0000\u0000\u009a\u00ab\u0003\b"+
		"\u0004\u0000\u009b\u009d\u0005,\u0000\u0000\u009c\u009b\u0001\u0000\u0000"+
		"\u0000\u009d\u00a0\u0001\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000"+
		"\u0000\u009e\u009f\u0001\u0000\u0000\u0000\u009f\u00a1\u0001\u0000\u0000"+
		"\u0000\u00a0\u009e\u0001\u0000\u0000\u0000\u00a1\u00a5\u0005\n\u0000\u0000"+
		"\u00a2\u00a4\u0005,\u0000\u0000\u00a3\u00a2\u0001\u0000\u0000\u0000\u00a4"+
		"\u00a7\u0001\u0000\u0000\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a5"+
		"\u00a6\u0001\u0000\u0000\u0000\u00a6\u00a8\u0001\u0000\u0000\u0000\u00a7"+
		"\u00a5\u0001\u0000\u0000\u0000\u00a8\u00aa\u0003\b\u0004\u0000\u00a9\u009e"+
		"\u0001\u0000\u0000\u0000\u00aa\u00ad\u0001\u0000\u0000\u0000\u00ab\u00a9"+
		"\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u00b1"+
		"\u0001\u0000\u0000\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ae\u00b0"+
		"\u0005,\u0000\u0000\u00af\u00ae\u0001\u0000\u0000\u0000\u00b0\u00b3\u0001"+
		"\u0000\u0000\u0000\u00b1\u00af\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001"+
		"\u0000\u0000\u0000\u00b2\u00b5\u0001\u0000\u0000\u0000\u00b3\u00b1\u0001"+
		"\u0000\u0000\u0000\u00b4\u00b6\u0005\n\u0000\u0000\u00b5\u00b4\u0001\u0000"+
		"\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\u0007\u0001\u0000"+
		"\u0000\u0000\u00b7\u00bf\u0005\'\u0000\u0000\u00b8\u00ba\u0005\u0002\u0000"+
		"\u0000\u00b9\u00bb\u0003\n\u0005\u0000\u00ba\u00b9\u0001\u0000\u0000\u0000"+
		"\u00ba\u00bb\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001\u0000\u0000\u0000"+
		"\u00bc\u00c0\u0005\u0003\u0000\u0000\u00bd\u00be\u0005\u0004\u0000\u0000"+
		"\u00be\u00c0\u0003\u000e\u0007\u0000\u00bf\u00b8\u0001\u0000\u0000\u0000"+
		"\u00bf\u00bd\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000"+
		"\u00c0\t\u0001\u0000\u0000\u0000\u00c1\u00d2\u0003\f\u0006\u0000\u00c2"+
		"\u00c4\u0005,\u0000\u0000\u00c3\u00c2\u0001\u0000\u0000\u0000\u00c4\u00c7"+
		"\u0001\u0000\u0000\u0000\u00c5\u00c3\u0001\u0000\u0000\u0000\u00c5\u00c6"+
		"\u0001\u0000\u0000\u0000\u00c6\u00c8\u0001\u0000\u0000\u0000\u00c7\u00c5"+
		"\u0001\u0000\u0000\u0000\u00c8\u00cc\u0005\n\u0000\u0000\u00c9\u00cb\u0005"+
		",\u0000\u0000\u00ca\u00c9\u0001\u0000\u0000\u0000\u00cb\u00ce\u0001\u0000"+
		"\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000"+
		"\u0000\u0000\u00cd\u00cf\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000"+
		"\u0000\u0000\u00cf\u00d1\u0003\f\u0006\u0000\u00d0\u00c5\u0001\u0000\u0000"+
		"\u0000\u00d1\u00d4\u0001\u0000\u0000\u0000\u00d2\u00d0\u0001\u0000\u0000"+
		"\u0000\u00d2\u00d3\u0001\u0000\u0000\u0000\u00d3\u000b\u0001\u0000\u0000"+
		"\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d5\u00d8\u0005\'\u0000\u0000"+
		"\u00d6\u00d7\u0005\u0004\u0000\u0000\u00d7\u00d9\u0003\u000e\u0007\u0000"+
		"\u00d8\u00d6\u0001\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000\u0000"+
		"\u00d9\r\u0001\u0000\u0000\u0000\u00da\u00dd\u0003\u0010\b\u0000\u00db"+
		"\u00dd\u0003\u0012\t\u0000\u00dc\u00da\u0001\u0000\u0000\u0000\u00dc\u00db"+
		"\u0001\u0000\u0000\u0000\u00dd\u000f\u0001\u0000\u0000\u0000\u00de\u00df"+
		"\u0003\u0012\t\u0000\u00df\u00e0\u0005\u000b\u0000\u0000\u00e0\u00e1\u0003"+
		"\u000e\u0007\u0000\u00e1\u0011\u0001\u0000\u0000\u0000\u00e2\u00f4\u0005"+
		"\f\u0000\u0000\u00e3\u00f4\u0005\r\u0000\u0000\u00e4\u00f4\u0005\u000e"+
		"\u0000\u0000\u00e5\u00f4\u0005\u000f\u0000\u0000\u00e6\u00f4\u0005\u0010"+
		"\u0000\u0000\u00e7\u00f4\u0005\u0011\u0000\u0000\u00e8\u00f4\u0005\'\u0000"+
		"\u0000\u00e9\u00ea\u0005\u0002\u0000\u0000\u00ea\u00ed\u0003\u000e\u0007"+
		"\u0000\u00eb\u00ec\u0005\n\u0000\u0000\u00ec\u00ee\u0003\u000e\u0007\u0000"+
		"\u00ed\u00eb\u0001\u0000\u0000\u0000\u00ee\u00ef\u0001\u0000\u0000\u0000"+
		"\u00ef\u00ed\u0001\u0000\u0000\u0000\u00ef\u00f0\u0001\u0000\u0000\u0000"+
		"\u00f0\u00f1\u0001\u0000\u0000\u0000\u00f1\u00f2\u0005\u0003\u0000\u0000"+
		"\u00f2\u00f4\u0001\u0000\u0000\u0000\u00f3\u00e2\u0001\u0000\u0000\u0000"+
		"\u00f3\u00e3\u0001\u0000\u0000\u0000\u00f3\u00e4\u0001\u0000\u0000\u0000"+
		"\u00f3\u00e5\u0001\u0000\u0000\u0000\u00f3\u00e6\u0001\u0000\u0000\u0000"+
		"\u00f3\u00e7\u0001\u0000\u0000\u0000\u00f3\u00e8\u0001\u0000\u0000\u0000"+
		"\u00f3\u00e9\u0001\u0000\u0000\u0000\u00f4\u0013\u0001\u0000\u0000\u0000"+
		"\u00f5\u00fa\u0003\u0016\u000b\u0000\u00f6\u00f7\u0005\n\u0000\u0000\u00f7"+
		"\u00f9\u0003\u0016\u000b\u0000\u00f8\u00f6\u0001\u0000\u0000\u0000\u00f9"+
		"\u00fc\u0001\u0000\u0000\u0000\u00fa\u00f8\u0001\u0000\u0000\u0000\u00fa"+
		"\u00fb\u0001\u0000\u0000\u0000\u00fb\u0015\u0001\u0000\u0000\u0000\u00fc"+
		"\u00fa\u0001\u0000\u0000\u0000\u00fd\u00fe\u0005\'\u0000\u0000\u00fe\u00ff"+
		"\u0005\u0004\u0000\u0000\u00ff\u0100\u0003\u000e\u0007\u0000\u0100\u0017"+
		"\u0001\u0000\u0000\u0000\u0101\u0102\u0007\u0000\u0000\u0000\u0102\u0019"+
		"\u0001\u0000\u0000\u0000\u0103\u0104\u0007\u0001\u0000\u0000\u0104\u001b"+
		"\u0001\u0000\u0000\u0000\u0105\u0106\u0006\u000e\uffff\uffff\u0000\u0106"+
		"\u0107\u0005\u0012\u0000\u0000\u0107\u0159\u0003\u001c\u000e\u0013\u0108"+
		"\u0109\u0005\u0014\u0000\u0000\u0109\u010a\u0005\'\u0000\u0000\u010a\u010c"+
		"\u0005\u0002\u0000\u0000\u010b\u010d\u0003*\u0015\u0000\u010c\u010b\u0001"+
		"\u0000\u0000\u0000\u010c\u010d\u0001\u0000\u0000\u0000\u010d\u010e\u0001"+
		"\u0000\u0000\u0000\u010e\u0159\u0005\u0003\u0000\u0000\u010f\u0110\u0005"+
		"&\u0000\u0000\u0110\u0159\u0003\u001c\u000e\u000e\u0111\u0112\u0003,\u0016"+
		"\u0000\u0112\u0113\u0005\u000b\u0000\u0000\u0113\u0114\u0003\u001c\u000e"+
		"\b\u0114\u0159\u0001\u0000\u0000\u0000\u0115\u0116\u0005\u001f\u0000\u0000"+
		"\u0116\u011a\u0003\u001c\u000e\u0000\u0117\u0119\u0005,\u0000\u0000\u0118"+
		"\u0117\u0001\u0000\u0000\u0000\u0119\u011c\u0001\u0000\u0000\u0000\u011a"+
		"\u0118\u0001\u0000\u0000\u0000\u011a\u011b\u0001\u0000\u0000\u0000\u011b"+
		"\u014d\u0001\u0000\u0000\u0000\u011c\u011a\u0001\u0000\u0000\u0000\u011d"+
		"\u0121\u0005 \u0000\u0000\u011e\u0120\u0005,\u0000\u0000\u011f\u011e\u0001"+
		"\u0000\u0000\u0000\u0120\u0123\u0001\u0000\u0000\u0000\u0121\u011f\u0001"+
		"\u0000\u0000\u0000\u0121\u0122\u0001\u0000\u0000\u0000\u0122\u0124\u0001"+
		"\u0000\u0000\u0000\u0123\u0121\u0001\u0000\u0000\u0000\u0124\u0135\u0003"+
		"\u001e\u000f\u0000\u0125\u0127\u0005,\u0000\u0000\u0126\u0125\u0001\u0000"+
		"\u0000\u0000\u0127\u012a\u0001\u0000\u0000\u0000\u0128\u0126\u0001\u0000"+
		"\u0000\u0000\u0128\u0129\u0001\u0000\u0000\u0000\u0129\u012b\u0001\u0000"+
		"\u0000\u0000\u012a\u0128\u0001\u0000\u0000\u0000\u012b\u012f\u0005!\u0000"+
		"\u0000\u012c\u012e\u0005,\u0000\u0000\u012d\u012c\u0001\u0000\u0000\u0000"+
		"\u012e\u0131\u0001\u0000\u0000\u0000\u012f\u012d\u0001\u0000\u0000\u0000"+
		"\u012f\u0130\u0001\u0000\u0000\u0000\u0130\u0132\u0001\u0000\u0000\u0000"+
		"\u0131\u012f\u0001\u0000\u0000\u0000\u0132\u0134\u0003\u001e\u000f\u0000"+
		"\u0133\u0128\u0001\u0000\u0000\u0000\u0134\u0137\u0001\u0000\u0000\u0000"+
		"\u0135\u0133\u0001\u0000\u0000\u0000\u0135\u0136\u0001\u0000\u0000\u0000"+
		"\u0136\u014e\u0001\u0000\u0000\u0000\u0137\u0135\u0001\u0000\u0000\u0000"+
		"\u0138\u013c\u0005\b\u0000\u0000\u0139\u013b\u0005,\u0000\u0000\u013a"+
		"\u0139\u0001\u0000\u0000\u0000\u013b\u013e\u0001\u0000\u0000\u0000\u013c"+
		"\u013a\u0001\u0000\u0000\u0000\u013c\u013d\u0001\u0000\u0000\u0000\u013d"+
		"\u013f\u0001\u0000\u0000\u0000\u013e\u013c\u0001\u0000\u0000\u0000\u013f"+
		"\u0145\u0003\u001e\u000f\u0000\u0140\u0141\u0003\"\u0011\u0000\u0141\u0142"+
		"\u0003\u001e\u000f\u0000\u0142\u0144\u0001\u0000\u0000\u0000\u0143\u0140"+
		"\u0001\u0000\u0000\u0000\u0144\u0147\u0001\u0000\u0000\u0000\u0145\u0143"+
		"\u0001\u0000\u0000\u0000\u0145\u0146\u0001\u0000\u0000\u0000\u0146\u0149"+
		"\u0001\u0000\u0000\u0000\u0147\u0145\u0001\u0000\u0000\u0000\u0148\u014a"+
		"\u0003\"\u0011\u0000\u0149\u0148\u0001\u0000\u0000\u0000\u0149\u014a\u0001"+
		"\u0000\u0000\u0000\u014a\u014b\u0001\u0000\u0000\u0000\u014b\u014c\u0005"+
		"\t\u0000\u0000\u014c\u014e\u0001\u0000\u0000\u0000\u014d\u011d\u0001\u0000"+
		"\u0000\u0000\u014d\u0138\u0001\u0000\u0000\u0000\u014e\u0159\u0001\u0000"+
		"\u0000\u0000\u014f\u0159\u0005(\u0000\u0000\u0150\u0159\u0005)\u0000\u0000"+
		"\u0151\u0159\u0005*\u0000\u0000\u0152\u0159\u0005+\u0000\u0000\u0153\u0159"+
		"\u0005\'\u0000\u0000\u0154\u0155\u0005\u0002\u0000\u0000\u0155\u0156\u0003"+
		"\u001c\u000e\u0000\u0156\u0157\u0005\u0003\u0000\u0000\u0157\u0159\u0001"+
		"\u0000\u0000\u0000\u0158\u0105\u0001\u0000\u0000\u0000\u0158\u0108\u0001"+
		"\u0000\u0000\u0000\u0158\u010f\u0001\u0000\u0000\u0000\u0158\u0111\u0001"+
		"\u0000\u0000\u0000\u0158\u0115\u0001\u0000\u0000\u0000\u0158\u014f\u0001"+
		"\u0000\u0000\u0000\u0158\u0150\u0001\u0000\u0000\u0000\u0158\u0151\u0001"+
		"\u0000\u0000\u0000\u0158\u0152\u0001\u0000\u0000\u0000\u0158\u0153\u0001"+
		"\u0000\u0000\u0000\u0158\u0154\u0001\u0000\u0000\u0000\u0159\u017a\u0001"+
		"\u0000\u0000\u0000\u015a\u015b\n\u0012\u0000\u0000\u015b\u015c\u0005\u0013"+
		"\u0000\u0000\u015c\u0179\u0003\u001c\u000e\u0013\u015d\u015e\n\r\u0000"+
		"\u0000\u015e\u015f\u0007\u0002\u0000\u0000\u015f\u0179\u0003\u001c\u000e"+
		"\u000e\u0160\u0161\n\f\u0000\u0000\u0161\u0162\u0007\u0003\u0000\u0000"+
		"\u0162\u0179\u0003\u001c\u000e\r\u0163\u0164\n\u000b\u0000\u0000\u0164"+
		"\u0165\u0007\u0004\u0000\u0000\u0165\u0179\u0003\u001c\u000e\f\u0166\u0167"+
		"\n\n\u0000\u0000\u0167\u0168\u0007\u0005\u0000\u0000\u0168\u0179\u0003"+
		"\u001c\u000e\u000b\u0169\u016a\n\t\u0000\u0000\u016a\u016b\u0005\u001e"+
		"\u0000\u0000\u016b\u016c\u0003\u001c\u000e\u0000\u016c\u016d\u0005\u0004"+
		"\u0000\u0000\u016d\u016e\u0003\u001c\u000e\n\u016e\u0179\u0001\u0000\u0000"+
		"\u0000\u016f\u0170\n\u0010\u0000\u0000\u0170\u0172\u0005\u0002\u0000\u0000"+
		"\u0171\u0173\u0003*\u0015\u0000\u0172\u0171\u0001\u0000\u0000\u0000\u0172"+
		"\u0173\u0001\u0000\u0000\u0000\u0173\u0174\u0001\u0000\u0000\u0000\u0174"+
		"\u0179\u0005\u0003\u0000\u0000\u0175\u0176\n\u000f\u0000\u0000\u0176\u0177"+
		"\u0005\u0004\u0000\u0000\u0177\u0179\u0003\u000e\u0007\u0000\u0178\u015a"+
		"\u0001\u0000\u0000\u0000\u0178\u015d\u0001\u0000\u0000\u0000\u0178\u0160"+
		"\u0001\u0000\u0000\u0000\u0178\u0163\u0001\u0000\u0000\u0000\u0178\u0166"+
		"\u0001\u0000\u0000\u0000\u0178\u0169\u0001\u0000\u0000\u0000\u0178\u016f"+
		"\u0001\u0000\u0000\u0000\u0178\u0175\u0001\u0000\u0000\u0000\u0179\u017c"+
		"\u0001\u0000\u0000\u0000\u017a\u0178\u0001\u0000\u0000\u0000\u017a\u017b"+
		"\u0001\u0000\u0000\u0000\u017b\u001d\u0001\u0000\u0000\u0000\u017c\u017a"+
		"\u0001\u0000\u0000\u0000\u017d\u017f\u0005\"\u0000\u0000\u017e\u017d\u0001"+
		"\u0000\u0000\u0000\u017e\u017f\u0001\u0000\u0000\u0000\u017f\u0180\u0001"+
		"\u0000\u0000\u0000\u0180\u0181\u0003$\u0012\u0000\u0181\u0182\u0003 \u0010"+
		"\u0000\u0182\u0183\u0003\u001c\u000e\u0000\u0183\u0189\u0001\u0000\u0000"+
		"\u0000\u0184\u0185\u0005#\u0000\u0000\u0185\u0186\u0003 \u0010\u0000\u0186"+
		"\u0187\u0003\u001c\u000e\u0000\u0187\u0189\u0001\u0000\u0000\u0000\u0188"+
		"\u017e\u0001\u0000\u0000\u0000\u0188\u0184\u0001\u0000\u0000\u0000\u0189"+
		"\u001f\u0001\u0000\u0000\u0000\u018a\u018b\u0007\u0006\u0000\u0000\u018b"+
		"!\u0001\u0000\u0000\u0000\u018c\u018e\u0005,\u0000\u0000\u018d\u018c\u0001"+
		"\u0000\u0000\u0000\u018e\u018f\u0001\u0000\u0000\u0000\u018f\u018d\u0001"+
		"\u0000\u0000\u0000\u018f\u0190\u0001\u0000\u0000\u0000\u0190\u0198\u0001"+
		"\u0000\u0000\u0000\u0191\u0195\u0005\n\u0000\u0000\u0192\u0194\u0005,"+
		"\u0000\u0000\u0193\u0192\u0001\u0000\u0000\u0000\u0194\u0197\u0001\u0000"+
		"\u0000\u0000\u0195\u0193\u0001\u0000\u0000\u0000\u0195\u0196\u0001\u0000"+
		"\u0000\u0000\u0196\u0199\u0001\u0000\u0000\u0000\u0197\u0195\u0001\u0000"+
		"\u0000\u0000\u0198\u0191\u0001\u0000\u0000\u0000\u0198\u0199\u0001\u0000"+
		"\u0000\u0000\u0199\u01a2\u0001\u0000\u0000\u0000\u019a\u019e\u0005\n\u0000"+
		"\u0000\u019b\u019d\u0005,\u0000\u0000\u019c\u019b\u0001\u0000\u0000\u0000"+
		"\u019d\u01a0\u0001\u0000\u0000\u0000\u019e\u019c\u0001\u0000\u0000\u0000"+
		"\u019e\u019f\u0001\u0000\u0000\u0000\u019f\u01a2\u0001\u0000\u0000\u0000"+
		"\u01a0\u019e\u0001\u0000\u0000\u0000\u01a1\u018d\u0001\u0000\u0000\u0000"+
		"\u01a1\u019a\u0001\u0000\u0000\u0000\u01a2#\u0001\u0000\u0000\u0000\u01a3"+
		"\u01ad\u0005%\u0000\u0000\u01a4\u01aa\u0005\'\u0000\u0000\u01a5\u01a7"+
		"\u0005\u0002\u0000\u0000\u01a6\u01a8\u0003&\u0013\u0000\u01a7\u01a6\u0001"+
		"\u0000\u0000\u0000\u01a7\u01a8\u0001\u0000\u0000\u0000\u01a8\u01a9\u0001"+
		"\u0000\u0000\u0000\u01a9\u01ab\u0005\u0003\u0000\u0000\u01aa\u01a5\u0001"+
		"\u0000\u0000\u0000\u01aa\u01ab\u0001\u0000\u0000\u0000\u01ab\u01ad\u0001"+
		"\u0000\u0000\u0000\u01ac\u01a3\u0001\u0000\u0000\u0000\u01ac\u01a4\u0001"+
		"\u0000\u0000\u0000\u01ad%\u0001\u0000\u0000\u0000\u01ae\u01b3\u0003(\u0014"+
		"\u0000\u01af\u01b0\u0005\n\u0000\u0000\u01b0\u01b2\u0003(\u0014\u0000"+
		"\u01b1\u01af\u0001\u0000\u0000\u0000\u01b2\u01b5\u0001\u0000\u0000\u0000"+
		"\u01b3\u01b1\u0001\u0000\u0000\u0000\u01b3\u01b4\u0001\u0000\u0000\u0000"+
		"\u01b4\'\u0001\u0000\u0000\u0000\u01b5\u01b3\u0001\u0000\u0000\u0000\u01b6"+
		"\u01b7\u0007\u0007\u0000\u0000\u01b7)\u0001\u0000\u0000\u0000\u01b8\u01bd"+
		"\u0003\u001c\u000e\u0000\u01b9\u01ba\u0005\n\u0000\u0000\u01ba\u01bc\u0003"+
		"\u001c\u000e\u0000\u01bb\u01b9\u0001\u0000\u0000\u0000\u01bc\u01bf\u0001"+
		"\u0000\u0000\u0000\u01bd\u01bb\u0001\u0000\u0000\u0000\u01bd\u01be\u0001"+
		"\u0000\u0000\u0000\u01be+\u0001\u0000\u0000\u0000\u01bf\u01bd\u0001\u0000"+
		"\u0000\u0000\u01c0\u01ce\u0003.\u0017\u0000\u01c1\u01ca\u0005\u0002\u0000"+
		"\u0000\u01c2\u01c7\u0003.\u0017\u0000\u01c3\u01c4\u0005\n\u0000\u0000"+
		"\u01c4\u01c6\u0003.\u0017\u0000\u01c5\u01c3\u0001\u0000\u0000\u0000\u01c6"+
		"\u01c9\u0001\u0000\u0000\u0000\u01c7\u01c5\u0001\u0000\u0000\u0000\u01c7"+
		"\u01c8\u0001\u0000\u0000\u0000\u01c8\u01cb\u0001\u0000\u0000\u0000\u01c9"+
		"\u01c7\u0001\u0000\u0000\u0000\u01ca\u01c2\u0001\u0000\u0000\u0000\u01ca"+
		"\u01cb\u0001\u0000\u0000\u0000\u01cb\u01cc\u0001\u0000\u0000\u0000\u01cc"+
		"\u01ce\u0005\u0003\u0000\u0000\u01cd\u01c0\u0001\u0000\u0000\u0000\u01cd"+
		"\u01c1\u0001\u0000\u0000\u0000\u01ce-\u0001\u0000\u0000\u0000\u01cf\u01d2"+
		"\u0005\'\u0000\u0000\u01d0\u01d1\u0005\u0004\u0000\u0000\u01d1\u01d3\u0003"+
		"\u000e\u0007\u0000\u01d2\u01d0\u0001\u0000\u0000\u0000\u01d2\u01d3\u0001"+
		"\u0000\u0000\u0000\u01d3/\u0001\u0000\u0000\u0000A3:>BGPU[`flqv|\u0080"+
		"\u0083\u0086\u008c\u0090\u0095\u009e\u00a5\u00ab\u00b1\u00b5\u00ba\u00bf"+
		"\u00c5\u00cc\u00d2\u00d8\u00dc\u00ef\u00f3\u00fa\u010c\u011a\u0121\u0128"+
		"\u012f\u0135\u013c\u0145\u0149\u014d\u0158\u0172\u0178\u017a\u017e\u0188"+
		"\u018f\u0195\u0198\u019e\u01a1\u01a7\u01aa\u01ac\u01b3\u01bd\u01c7\u01ca"+
		"\u01cd\u01d2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}