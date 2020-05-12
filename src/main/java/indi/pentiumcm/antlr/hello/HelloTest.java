package indi.pentiumcm.antlr.hello;

import indi.pentiumcm.antlr.hello.out.HelloLexer;
import indi.pentiumcm.antlr.hello.out.HelloParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * @projName: JavaSkillStack
 * @packgeName: indi.pentiumcm.antlr.hello
 * @className: HelloTest
 * @author： pentiumCM
 * @email： 842679178@qq.com
 * @date: 2020/5/12 11:11
 * @describe: 相关测试代码
 */
public class HelloTest {


    public static void main(String[] args) {
        HelloLexer lexer = new HelloLexer(CharStreams.fromString("a = 1"));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        HelloParser parser = new HelloParser(tokenStream);
        System.out.println(parser.statement().toStringTree(parser));
    }
}
