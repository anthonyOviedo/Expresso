// Generated from com/diezam04/expresso/core/transpiler/Expr.g4 by ANTLR 4.13.1
package com.diezam04.expresso.core.transpiler;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExprVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExprParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(ExprParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funStat}
	 * labeled alternative in {@link ExprParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunStat(ExprParser.FunStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code letStat}
	 * labeled alternative in {@link ExprParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetStat(ExprParser.LetStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printStat}
	 * labeled alternative in {@link ExprParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStat(ExprParser.PrintStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dataStat}
	 * labeled alternative in {@link ExprParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataStat(ExprParser.DataStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprStat}
	 * labeled alternative in {@link ExprParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStat(ExprParser.ExprStatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code commentStat}
	 * labeled alternative in {@link ExprParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommentStat(ExprParser.CommentStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#dataBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataBlock(ExprParser.DataBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#constructorList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorList(ExprParser.ConstructorListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#dataConstructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataConstructor(ExprParser.DataConstructorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#dataFieldList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataFieldList(ExprParser.DataFieldListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#dataField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataField(ExprParser.DataFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#typeRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeRef(ExprParser.TypeRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#paramDeclList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamDeclList(ExprParser.ParamDeclListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#paramDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamDecl(ExprParser.ParamDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(ExprParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#comment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(ExprParser.CommentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Call}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(ExprParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ctorCall}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCtorCall(ExprParser.CtorCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDiv(ExprParser.MulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSub(ExprParser.AddSubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Ternary}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernary(ExprParser.TernaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Parens}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(ExprParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code matchExpr}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchExpr(ExprParser.MatchExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdRef}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdRef(ExprParser.IdRefContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(ExprParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EulerFloat}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEulerFloat(ExprParser.EulerFloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringLit}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLit(ExprParser.StringLitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Float}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloat(ExprParser.FloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Relation}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation(ExprParser.RelationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryMinus}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryMinus(ExprParser.UnaryMinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code power}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPower(ExprParser.PowerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Equality}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality(ExprParser.EqualityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Lambda}
	 * labeled alternative in {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambda(ExprParser.LambdaContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#matchCase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchCase(ExprParser.MatchCaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code wildcardPattern}
	 * labeled alternative in {@link ExprParser#pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWildcardPattern(ExprParser.WildcardPatternContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constructorPattern}
	 * labeled alternative in {@link ExprParser#pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorPattern(ExprParser.ConstructorPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#patternParamList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternParamList(ExprParser.PatternParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#patternParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternParam(ExprParser.PatternParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(ExprParser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(ExprParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#lambdaParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaParam(ExprParser.LambdaParamContext ctx);
}