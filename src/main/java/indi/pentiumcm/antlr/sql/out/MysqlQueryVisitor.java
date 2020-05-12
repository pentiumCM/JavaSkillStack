// Generated from F:/develop_code/java/JavaSkillStack/src/main/java/indi/pentiumcm/antlr/sql\MysqlQuery.g4 by ANTLR 4.8
package indi.pentiumcm.antlr.sql.out;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MysqlQueryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MysqlQueryVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#tableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableName(MysqlQueryParser.TableNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#column_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumn_name(MysqlQueryParser.Column_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#function_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_name(MysqlQueryParser.Function_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectStatement(MysqlQueryParser.SelectStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#selectElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectElements(MysqlQueryParser.SelectElementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#tableSources}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSources(MysqlQueryParser.TableSourcesContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#whereClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereClause(MysqlQueryParser.WhereClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#logicExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicExpression(MysqlQueryParser.LogicExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#groupByCaluse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupByCaluse(MysqlQueryParser.GroupByCaluseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#havingCaluse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHavingCaluse(MysqlQueryParser.HavingCaluseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#orderByClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderByClause(MysqlQueryParser.OrderByClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#limitClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimitClause(MysqlQueryParser.LimitClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#orderByExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderByExpression(MysqlQueryParser.OrderByExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#groupByItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupByItem(MysqlQueryParser.GroupByItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#logicalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOperator(MysqlQueryParser.LogicalOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(MysqlQueryParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(MysqlQueryParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#decimalLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimalLiteral(MysqlQueryParser.DecimalLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#textLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextLiteral(MysqlQueryParser.TextLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectColumnElement}
	 * labeled alternative in {@link MysqlQueryParser#selectElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectColumnElement(MysqlQueryParser.SelectColumnElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selectFunctionElement}
	 * labeled alternative in {@link MysqlQueryParser#selectElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectFunctionElement(MysqlQueryParser.SelectFunctionElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#fullColumnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullColumnName(MysqlQueryParser.FullColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code aggregateFunctionCall}
	 * labeled alternative in {@link MysqlQueryParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregateFunctionCall(MysqlQueryParser.AggregateFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#aggregateWindowedFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregateWindowedFunction(MysqlQueryParser.AggregateWindowedFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#functionArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionArg(MysqlQueryParser.FunctionArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#functionArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionArgs(MysqlQueryParser.FunctionArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MysqlQueryParser#uid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUid(MysqlQueryParser.UidContext ctx);
}