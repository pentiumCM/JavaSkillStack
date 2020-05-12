// Generated from F:/develop_code/java/JavaSkillStack/src/main/java/indi/pentiumcm/antlr/hello\Hello.g4 by ANTLR 4.8
package indi.pentiumcm.antlr.hello.out;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * HelloListener.java和HelloBaseListener.java
 * <p>
 * 这两个类都是事件监听类，是留给开发者自己来定义相应的事件
 */
/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HelloParser}.
 */
public interface HelloListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by {@link HelloParser#statement}.
     * @param ctx the parse tree
     */
    void enterStatement(HelloParser.StatementContext ctx);

    /**
     * Exit a parse tree produced by {@link HelloParser#statement}.
     * @param ctx the parse tree
     */
    void exitStatement(HelloParser.StatementContext ctx);
}