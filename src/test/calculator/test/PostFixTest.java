package calculator.test;

import calculator.model.PostFix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PostFixTest {
    PostFix testPostFix;

    @BeforeEach
    void runBefore(){
        testPostFix = new PostFix("pi * ( 1.47 - 2 ) ^ 3 - 1.7 / 10");
    }

    @Test
    void testPostFix(){
        assertEquals("pi * ( 1.47 - 2 ) ^ 3 - 1.7 / 10", testPostFix.getInfix());
        assertEquals(0, testPostFix.getInfixList().size());
        assertEquals(0, testPostFix.getExpStack().size());
        assertEquals(0, testPostFix.getPostFix().size());
    }

    @Test
    void testParseExpression(){
        testPostFix.parseExpression();
        ArrayList<String> parsedList = new ArrayList<>();
        parsedList.add("pi");
        parsedList.add("*");
        parsedList.add("(");
        parsedList.add("1.47");
        parsedList.add("-");
        parsedList.add("2");
        parsedList.add(")");
        parsedList.add("^");
        parsedList.add("3");
        parsedList.add("-");
        parsedList.add("1.7");
        parsedList.add("/");
        parsedList.add("10");

        for(int i = 0; i < parsedList.size(); i++) {
            assertEquals(parsedList.get(i), testPostFix.getInfixList().get(i));
        }
    }

    @Test
    void testInfixToPostfixBig(){
        testPostFix.parseExpression();
        testPostFix.infixToPostFix();

        assertEquals("pi", testPostFix.getPostFix().get(0));
        assertEquals("1.47", testPostFix.getPostFix().get(1));
        assertEquals("2", testPostFix.getPostFix().get(2));
        assertEquals("-", testPostFix.getPostFix().get(3));
        assertEquals("3", testPostFix.getPostFix().get(4));
        assertEquals("^", testPostFix.getPostFix().get(5));
        assertEquals("*", testPostFix.getPostFix().get(6));
        assertEquals("1.7", testPostFix.getPostFix().get(7));
        assertEquals("10", testPostFix.getPostFix().get(8));
        assertEquals("/", testPostFix.getPostFix().get(9));
        assertEquals("-", testPostFix.getPostFix().get(10));
    }

    @Test
    void testClearTheStack(){
        testPostFix.getExpStack().push("-");
        testPostFix.getExpStack().push("(");
        testPostFix.getExpStack().push("+");
        testPostFix.getExpStack().push("/");
        testPostFix.clearTheStack();

        assertEquals(3, testPostFix.getPostFix().size());
        assertEquals("/", testPostFix.getPostFix().get(0));
        assertEquals("+", testPostFix.getPostFix().get(1));
        assertEquals("-", testPostFix.getPostFix().get(2));

    }

    @Test
    void testOperatorEmpty(){
        testPostFix.operator("*");

        assertEquals(1, testPostFix.getExpStack().size());
        assertEquals("*", testPostFix.getExpStack().firstElement());
        assertEquals(0, testPostFix.getPostFix().size());
    }

    @Test
    void testOperatorRemoveNone(){
        testPostFix.getExpStack().push("-");
        testPostFix.operator("*");

        assertEquals(2, testPostFix.getExpStack().size());
        assertEquals("-", testPostFix.getExpStack().get(0));
        assertEquals("*", testPostFix.getExpStack().get(1));
        assertEquals(0, testPostFix.getPostFix().size());
    }

    @Test
    void testOperatorRemoveOne(){
        testPostFix.getExpStack().push("*");
        testPostFix.operator("-");

        assertEquals(1, testPostFix.getExpStack().size());
        assertEquals("-", testPostFix.getExpStack().get(0));
        assertEquals("*", testPostFix.getPostFix().get(0));
        assertEquals(1, testPostFix.getPostFix().size());

    }

    @Test
    void testOperatorRemoveTwo(){
        testPostFix.getExpStack().push("-");
        testPostFix.getExpStack().push("*");
        testPostFix.operator("/");

        assertEquals(1, testPostFix.getExpStack().size());
        assertEquals("/", testPostFix.getExpStack().get(0));
        assertEquals("*", testPostFix.getPostFix().get(0));
        assertEquals("-", testPostFix.getPostFix().get(1));
        assertEquals(2, testPostFix.getPostFix().size());

    }

    @Test
    void testOperatorLeftParEquals(){
        testPostFix.getExpStack().push("-");
        testPostFix.getExpStack().push("(");
        testPostFix.getExpStack().push("*");
        testPostFix.operator("/");

        assertEquals(3, testPostFix.getExpStack().size());
        assertEquals("-", testPostFix.getExpStack().get(0));
        assertEquals("(", testPostFix.getExpStack().get(1));
        assertEquals("/", testPostFix.getExpStack().get(2));
        assertEquals("*", testPostFix.getPostFix().get(0));
        assertEquals(1, testPostFix.getPostFix().size());
    }

    @Test
    void testOperatorLeftParLess(){
        testPostFix.getExpStack().push("-");
        testPostFix.getExpStack().push("(");
        testPostFix.getExpStack().push("*");
        testPostFix.operator("+");

        assertEquals(3, testPostFix.getExpStack().size());
        assertEquals("-", testPostFix.getExpStack().get(0));
        assertEquals("(", testPostFix.getExpStack().get(1));
        assertEquals("+", testPostFix.getExpStack().get(2));
        assertEquals("*", testPostFix.getPostFix().get(0));
        assertEquals(1, testPostFix.getPostFix().size());
    }

    @Test
    void testOperatorLeftParGreater() {
        testPostFix.getExpStack().push("-");
        testPostFix.getExpStack().push("(");
        testPostFix.getExpStack().push("+");
        testPostFix.operator("*");

        assertEquals(4, testPostFix.getExpStack().size());
        assertEquals("-", testPostFix.getExpStack().get(0));
        assertEquals("(", testPostFix.getExpStack().get(1));
        assertEquals("+", testPostFix.getExpStack().get(2));
        assertEquals("*", testPostFix.getExpStack().get(3));
        assertEquals(0, testPostFix.getPostFix().size());
    }

    @Test
    void testLeftParenthesis(){
        testPostFix.leftParenthesis("(");
        assertEquals(1, testPostFix.getExpStack().size());
        assertEquals("(", testPostFix.getExpStack().get(0));
    }

    @Test
    void testRightParenthesis(){
        testPostFix.getExpStack().push("-");
        testPostFix.getExpStack().push("(");
        testPostFix.getExpStack().push("+");
        testPostFix.getExpStack().push("/");

        testPostFix.rightParenthesis();
        assertEquals(2, testPostFix.getPostFix().size());
        assertEquals("/", testPostFix.getPostFix().get(0));
        assertEquals("+", testPostFix.getPostFix().get(1));
        assertEquals(1, testPostFix.getExpStack().size());
        assertEquals("-", testPostFix.getExpStack().get(0));

    }

    @Test
    void testOperand(){
        testPostFix.operand("9");
        assertEquals(1, testPostFix.getPostFix().size());
        assertEquals("9", testPostFix.getPostFix().get(0));
    }

    @Test
    void testEmptyStack(){
        assertTrue(testPostFix.emptyStack());

        testPostFix.getExpStack().add("3");
        assertFalse(testPostFix.emptyStack());
    }

}
