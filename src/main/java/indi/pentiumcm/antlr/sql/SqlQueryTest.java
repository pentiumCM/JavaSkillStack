package indi.pentiumcm.antlr.sql;

import indi.pentiumcm.antlr.sql.out.MysqlQueryLexer;
import indi.pentiumcm.antlr.sql.out.MysqlQueryParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.antlr.sql
 * @className: SqlQueryTest
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2020/5/12 14:15
 * @describe: SQL 词法/语法分析测试
 */
public class SqlQueryTest {

    public static void run(String expr) throws Exception {
        //对每一个输入的字符串，构造一个 ANTLRStringStream 流 in
        ANTLRInputStream in = new ANTLRInputStream(expr);

        //用 in 构造词法分析器 lexer，词法分析的作用是产生记号
        MysqlQueryLexer lexer = new MysqlQueryLexer(in);

        //用词法分析器 lexer 构造一个记号流 tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        //再使用 tokens 构造语法分析器 parser,至此已经完成词法分析和语法分析的准备工作
        MysqlQueryParser parser = new MysqlQueryParser(tokens);

        //最终调用语法分析器的规则，完成对表达式的验证
        MysqlQueryParser.SelectStatementContext selectStatementContext = parser.selectStatement();
        String res = selectStatementContext.toStringTree(parser);
        System.out.println(res);
    }

    public static void main(String[] args) throws Exception {
        String[] testStr = {"select a from b where c =1"};
        for (String s : testStr) {
            System.out.println("Input expr:" + s);
            run(s);
        }
    }
}
