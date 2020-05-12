// Generated from F:/develop_code/java/JavaSkillStack/src/main/java/indi/pentiumcm/antlr/sql\MysqlQuery.g4 by ANTLR 4.8
package indi.pentiumcm.antlr.sql.out;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MysqlQueryParser}.
 */
public interface MysqlQueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(MysqlQueryParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(MysqlQueryParser.TableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#column_name}.
	 * @param ctx the parse tree
	 */
	void enterColumn_name(MysqlQueryParser.Column_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#column_name}.
	 * @param ctx the parse tree
	 */
	void exitColumn_name(MysqlQueryParser.Column_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#function_name}.
	 * @param ctx the parse tree
	 */
	void enterFunction_name(MysqlQueryParser.Function_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#function_name}.
	 * @param ctx the parse tree
	 */
	void exitFunction_name(MysqlQueryParser.Function_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectStatement(MysqlQueryParser.SelectStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectStatement(MysqlQueryParser.SelectStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void enterSelectElements(MysqlQueryParser.SelectElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void exitSelectElements(MysqlQueryParser.SelectElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#tableSources}.
	 * @param ctx the parse tree
	 */
	void enterTableSources(MysqlQueryParser.TableSourcesContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#tableSources}.
	 * @param ctx the parse tree
	 */
	void exitTableSources(MysqlQueryParser.TableSourcesContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(MysqlQueryParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(MysqlQueryParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#logicExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicExpression(MysqlQueryParser.LogicExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#logicExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicExpression(MysqlQueryParser.LogicExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#groupByCaluse}.
	 * @param ctx the parse tree
	 */
	void enterGroupByCaluse(MysqlQueryParser.GroupByCaluseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#groupByCaluse}.
	 * @param ctx the parse tree
	 */
	void exitGroupByCaluse(MysqlQueryParser.GroupByCaluseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#havingCaluse}.
	 * @param ctx the parse tree
	 */
	void enterHavingCaluse(MysqlQueryParser.HavingCaluseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#havingCaluse}.
	 * @param ctx the parse tree
	 */
	void exitHavingCaluse(MysqlQueryParser.HavingCaluseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#orderByClause}.
	 * @param ctx the parse tree
	 */
	void enterOrderByClause(MysqlQueryParser.OrderByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#orderByClause}.
	 * @param ctx the parse tree
	 */
	void exitOrderByClause(MysqlQueryParser.OrderByClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#limitClause}.
	 * @param ctx the parse tree
	 */
	void enterLimitClause(MysqlQueryParser.LimitClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#limitClause}.
	 * @param ctx the parse tree
	 */
	void exitLimitClause(MysqlQueryParser.LimitClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#orderByExpression}.
	 * @param ctx the parse tree
	 */
	void enterOrderByExpression(MysqlQueryParser.OrderByExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#orderByExpression}.
	 * @param ctx the parse tree
	 */
	void exitOrderByExpression(MysqlQueryParser.OrderByExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#groupByItem}.
	 * @param ctx the parse tree
	 */
	void enterGroupByItem(MysqlQueryParser.GroupByItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#groupByItem}.
	 * @param ctx the parse tree
	 */
	void exitGroupByItem(MysqlQueryParser.GroupByItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOperator(MysqlQueryParser.LogicalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOperator(MysqlQueryParser.LogicalOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(MysqlQueryParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(MysqlQueryParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(MysqlQueryParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(MysqlQueryParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#decimalLiteral}.
	 * @param ctx the parse tree
	 */
	void enterDecimalLiteral(MysqlQueryParser.DecimalLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#decimalLiteral}.
	 * @param ctx the parse tree
	 */
	void exitDecimalLiteral(MysqlQueryParser.DecimalLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#textLiteral}.
	 * @param ctx the parse tree
	 */
	void enterTextLiteral(MysqlQueryParser.TextLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#textLiteral}.
	 * @param ctx the parse tree
	 */
	void exitTextLiteral(MysqlQueryParser.TextLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectColumnElement}
	 * labeled alternative in {@link MysqlQueryParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void enterSelectColumnElement(MysqlQueryParser.SelectColumnElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectColumnElement}
	 * labeled alternative in {@link MysqlQueryParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void exitSelectColumnElement(MysqlQueryParser.SelectColumnElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selectFunctionElement}
	 * labeled alternative in {@link MysqlQueryParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void enterSelectFunctionElement(MysqlQueryParser.SelectFunctionElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selectFunctionElement}
	 * labeled alternative in {@link MysqlQueryParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void exitSelectFunctionElement(MysqlQueryParser.SelectFunctionElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#fullColumnName}.
	 * @param ctx the parse tree
	 */
	void enterFullColumnName(MysqlQueryParser.FullColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#fullColumnName}.
	 * @param ctx the parse tree
	 */
	void exitFullColumnName(MysqlQueryParser.FullColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code aggregateFunctionCall}
	 * labeled alternative in {@link MysqlQueryParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterAggregateFunctionCall(MysqlQueryParser.AggregateFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code aggregateFunctionCall}
	 * labeled alternative in {@link MysqlQueryParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitAggregateFunctionCall(MysqlQueryParser.AggregateFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#aggregateWindowedFunction}.
	 * @param ctx the parse tree
	 */
	void enterAggregateWindowedFunction(MysqlQueryParser.AggregateWindowedFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#aggregateWindowedFunction}.
	 * @param ctx the parse tree
	 */
	void exitAggregateWindowedFunction(MysqlQueryParser.AggregateWindowedFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#functionArg}.
	 * @param ctx the parse tree
	 */
	void enterFunctionArg(MysqlQueryParser.FunctionArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#functionArg}.
	 * @param ctx the parse tree
	 */
	void exitFunctionArg(MysqlQueryParser.FunctionArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#functionArgs}.
	 * @param ctx the parse tree
	 */
	void enterFunctionArgs(MysqlQueryParser.FunctionArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#functionArgs}.
	 * @param ctx the parse tree
	 */
	void exitFunctionArgs(MysqlQueryParser.FunctionArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MysqlQueryParser#uid}.
	 * @param ctx the parse tree
	 */
	void enterUid(MysqlQueryParser.UidContext ctx);
	/**
	 * Exit a parse tree produced by {@link MysqlQueryParser#uid}.
	 * @param ctx the parse tree
	 */
	void exitUid(MysqlQueryParser.UidContext ctx);
}