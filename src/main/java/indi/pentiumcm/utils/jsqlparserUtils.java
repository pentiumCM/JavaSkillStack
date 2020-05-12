package indi.pentiumcm.utils;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.parser.ParseException;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.util.AddAliasesVisitor;
import net.sf.jsqlparser.util.SelectUtils;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.util.List;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.utils
 * @className: jsqlparserUtils
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2020/5/11 22:22
 * @describe: jsqlparser工具类
 */
public class jsqlparserUtils {


    public static void main(String[] args) throws JSQLParserException, ParseException {

        //1、简单的语句解析
        // 1.1 单个语句
        String sql = "SELECT * FROM TABLE1";
        //方法1
        Statement statement = CCJSqlParserUtil.parse(sql);
        //方法2
        CCJSqlParser ccjSqlParser = new CCJSqlParser(sql);
        Statement statement2 = ccjSqlParser.Statement();

        // 1.2 多个语句
        String sqls = "SELECT * FROM TABLE1;SELECT * FROM TABLE2";
        //方法1
        Statements statements = CCJSqlParserUtil.parseStatements(sqls);
        //方法2
        CCJSqlParser ccjSqlParser2 = new CCJSqlParser(sqls);
//        Statements statements3 = ccjSqlParser.Statements();

        List<Statement> statementList = statements.getStatements();


        // 2、简单的表达式解析
        //表达式
        Expression expression = CCJSqlParserUtil.parseExpression("a+b*c");
        //条件表达式
        Expression expression2 = CCJSqlParserUtil.parseCondExpression("A='123'");


        // 3、从SQL中提取表名
        String sql3 = "SELECT * FROM TABLE1";
        Statement statement3 = CCJSqlParserUtil.parse(sql);
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(statement);


        // 4、将别名应用于所有表达式
        Select select = (Select) CCJSqlParserUtil.parse("SELECT A,B,C FROM TABLE1");//此处的运行时类是Select
        SelectBody selectBody = select.getSelectBody();
        AddAliasesVisitor addAliasesVisitor = new AddAliasesVisitor();
        addAliasesVisitor.setPrefix("B");//设置前缀（如不进行设置默认为“A”）
        selectBody.accept(addAliasesVisitor);
        System.out.println(selectBody.toString());//SELECT A AS B1, B AS B2, C AS B3 FROM TABLE1


        // 5、向SELECT添加一列或表达式
        Select select5 = (Select) CCJSqlParserUtil.parse("SELECT A FROM TABLE1");
        SelectUtils.addExpression(select, new Column("B"));
        System.out.println(select);//SELECT A, B FROM TABLE1

        System.out.print("hello");
    }
}
