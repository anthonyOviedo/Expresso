// Generated from com/diezam04/expresso/core/transpiler/Expr.g4 by ANTLR 4.13.1
package com.diezam04.expresso.core.transpiler;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprParser}.
 */
public interface ExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExprParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(ExprParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(ExprParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funStat}
	 * labeled alternative in {@link ExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterFunStat(ExprParser.FunStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funStat}
	 * labeled alternative in {@link ExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitFunStat(ExprParser.FunStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code letStat}
	 * labeled alternative in {@link ExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterLetStat(ExprParser.LetStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code letStat}
	 * labeled alternative in {@link ExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitLetStat(ExprParser.LetStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printStat}
	 * labeled alternative in {@link ExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterPrintStat(ExprParser.PrintStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printStat}
	 * labeled alternative in {@link ExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitPrintStat(ExprParser.PrintStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dataStat}
	 * labeled alternative in {@link ExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterDataStat(ExprParser.DataStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dataStat}
	 * labeled alternative in {@link ExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitDataStat(ExprParser.DataStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprStat}
	 * labeled alternative in {@link ExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterExprStat(ExprParser.ExprStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprStat}
	 * labeled alternative in {@link ExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitExprStat(ExprParser.ExprStatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code commentStat}
	 * labeled alternative in {@link ExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterCommentStat(ExprParser.CommentStatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code commentStat}
	 * labeled alternative in {@link ExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitCommentStat(ExprParser.CommentStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#dataBlock}.
	 * @param ctx the parse tree
	 */
	void enterDataBlock(ExprParser.DataBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#dataBlock}.
	 * @param ctx the parse tree
	 */
	void exitDataBlock(ExprParser.DataBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#constructorList}.
	 * @param ctx the parse tree
	 */
	void enterConstructorList(ExprParser.ConstructorListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#constructorList}.
	 * @param ctx the parse tree
	 */
	void exitConstructorList(ExprParser.ConstructorListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#dataConstructor}.
	 * @param ctx the parse tree
	 */
	void enterDataConstructor(ExprParser.DataConstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#dataConstructor}.
	 * @param ctx the parse tree
	 */
	void exitDataConstructor(ExprParser.DataConstructorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#dataFieldList}.
	 * @param ctx the parse tree
	 */
	void enterDataFieldList(ExprParser.DataFieldListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#dataFieldList}.
	 * @param ctx the parse tree
	 */
	void exitDataFieldList(ExprParser.DataFieldListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#dataField}.
	 * @param ctx the parse tree
	 */
	void enterDataField(ExprParser.DataFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#dataField}.
	 * @param ctx the parse tree
	 */
	void exitDataField(ExprParser.DataFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#typeRef}.
	 * @param ctx the parse tree
	 */
	void enterTypeRef(ExprParser.TypeRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#typeRef}.
	 * @param ctx the parse tree
	 */
	void exitTypeRef(ExprParser.TypeRefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#paramDeclList}.
	 * @param ctx the parse tree
	 */
	void enterParamDeclList(ExprParser.ParamDeclListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#paramDeclList}.
	 * @param ctx the parse tree
	 */
	void exitParamDeclList(ExprParser.ParamDeclListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#paramDecl}.
	 * @param ctx the parse tree
	 */
	void enterParamDecl(ExprParser.ParamDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#paramDecl}.
	 * @param ctx the parse tree
	 */
	void exitParamDecl(ExprParser.ParamDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(ExprParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(ExprParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(ExprParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(ExprParser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Call}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCall(ExprParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Call}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCall(ExprParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ctorCall}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCtorCall(ExprParser.CtorCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ctorCall}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCtorCall(ExprParser.CtorCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(ExprParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(ExprParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(ExprParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(ExprParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Ternary}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTernary(ExprParser.TernaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Ternary}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTernary(ExprParser.TernaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParens(ExprParser.ParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParens(ExprParser.ParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code matchExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMatchExpr(ExprParser.MatchExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code matchExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMatchExpr(ExprParser.MatchExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdRef}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIdRef(ExprParser.IdRefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdRef}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIdRef(ExprParser.IdRefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Int}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(ExprParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(ExprParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EulerFloat}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEulerFloat(ExprParser.EulerFloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EulerFloat}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEulerFloat(ExprParser.EulerFloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringLit}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStringLit(ExprParser.StringLitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringLit}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStringLit(ExprParser.StringLitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Float}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFloat(ExprParser.FloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Float}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFloat(ExprParser.FloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Relation}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRelation(ExprParser.RelationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Relation}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRelation(ExprParser.RelationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryMinus}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryMinus(ExprParser.UnaryMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryMinus}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryMinus(ExprParser.UnaryMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code power}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPower(ExprParser.PowerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code power}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPower(ExprParser.PowerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEquality(ExprParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEquality(ExprParser.EqualityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TypeCast}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTypeCast(ExprParser.TypeCastContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TypeCast}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTypeCast(ExprParser.TypeCastContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Lambda}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLambda(ExprParser.LambdaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Lambda}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLambda(ExprParser.LambdaContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#matchCase}.
	 * @param ctx the parse tree
	 */
	void enterMatchCase(ExprParser.MatchCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#matchCase}.
	 * @param ctx the parse tree
	 */
	void exitMatchCase(ExprParser.MatchCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#matchArrow}.
	 * @param ctx the parse tree
	 */
	void enterMatchArrow(ExprParser.MatchArrowContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#matchArrow}.
	 * @param ctx the parse tree
	 */
	void exitMatchArrow(ExprParser.MatchArrowContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#matchCaseSeparator}.
	 * @param ctx the parse tree
	 */
	void enterMatchCaseSeparator(ExprParser.MatchCaseSeparatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#matchCaseSeparator}.
	 * @param ctx the parse tree
	 */
	void exitMatchCaseSeparator(ExprParser.MatchCaseSeparatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code wildcardPattern}
	 * labeled alternative in {@link ExprParser#pattern}.
	 * @param ctx the parse tree
	 */
	void enterWildcardPattern(ExprParser.WildcardPatternContext ctx);
	/**
	 * Exit a parse tree produced by the {@code wildcardPattern}
	 * labeled alternative in {@link ExprParser#pattern}.
	 * @param ctx the parse tree
	 */
	void exitWildcardPattern(ExprParser.WildcardPatternContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constructorPattern}
	 * labeled alternative in {@link ExprParser#pattern}.
	 * @param ctx the parse tree
	 */
	void enterConstructorPattern(ExprParser.ConstructorPatternContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constructorPattern}
	 * labeled alternative in {@link ExprParser#pattern}.
	 * @param ctx the parse tree
	 */
	void exitConstructorPattern(ExprParser.ConstructorPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#patternParamList}.
	 * @param ctx the parse tree
	 */
	void enterPatternParamList(ExprParser.PatternParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#patternParamList}.
	 * @param ctx the parse tree
	 */
	void exitPatternParamList(ExprParser.PatternParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#patternParam}.
	 * @param ctx the parse tree
	 */
	void enterPatternParam(ExprParser.PatternParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#patternParam}.
	 * @param ctx the parse tree
	 */
	void exitPatternParam(ExprParser.PatternParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(ExprParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(ExprParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(ExprParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(ExprParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#lambdaParam}.
	 * @param ctx the parse tree
	 */
	void enterLambdaParam(ExprParser.LambdaParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#lambdaParam}.
	 * @param ctx the parse tree
	 */
	void exitLambdaParam(ExprParser.LambdaParamContext ctx);
}