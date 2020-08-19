package calculator.test;

import calculator.model.ExpressionNode;
import calculator.model.ExpressionTreeFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionTreeFunctionTest {
    ExpressionTreeFunction testExpressionTreeFunction;
    ArrayList<String> testList = new ArrayList<>();

    @BeforeEach
    void runBefore() {
        testExpressionTreeFunction = new ExpressionTreeFunction(makeList());
    }

    @Test
    void testConvertValue() {
        testExpressionTreeFunction.setValue(6.8);
        assertEquals(testExpressionTreeFunction.convertValue("pi"), Math.PI);
        assertEquals(testExpressionTreeFunction.convertValue("e"), Math.E);
        assertEquals(testExpressionTreeFunction.convertValue("x"), testExpressionTreeFunction.getValue());
        assertEquals(testExpressionTreeFunction.convertValue("5"), 5);
        assertEquals(testExpressionTreeFunction.convertValue("10.987"), 10.987);
    }

    @Test
    void testEvaluateAt() {
        testExpressionTreeFunction.createExpressionTree(makeList());
        double value = testExpressionTreeFunction.evaluateAt(testExpressionTreeFunction.getTree(), 10);
        assertEquals((Math.sin(10 - 5))/Math.log(10), value);
    }

    @Test
    void testFindDerivative() {
        testExpressionTreeFunction.createExpressionTree(testFunction());
        String derivative = testExpressionTreeFunction.findDerivative(testExpressionTreeFunction.getTree());
        assertEquals("( ( 3 ) * ( ( 2 ) * ( x ) ^ ( 2 - 1 ) * ( 1 ) + ( x ) ^ ( 2 )" +
                " * log ( x ) * ( 0 ) ) + ( x ^ 2 ) * ( 0 ) ) + ( cos ( x ) * ( 1 ) )", derivative);
    }

    @Test
    void testFindDerivativeBaseCase() {
        ExpressionNode baseNode = new ExpressionNode("3");
        assertEquals("0", testExpressionTreeFunction.findDerivative(baseNode));
    }

    @Test
    void testExpressionNodeToStringBaseCase() {
        ExpressionNode baseNode = new ExpressionNode("3");
        assertEquals("3", testExpressionTreeFunction.expressionNodeToString(baseNode, 4));
    }

    @Test
    void testExpressionNodeToStringTranscendental() {
        ExpressionNode transcendentalNode = new ExpressionNode("sin");
        transcendentalNode.addChildrenLeft(new ExpressionNode("x"));
        assertEquals("sin ( x )" ,testExpressionTreeFunction.expressionNodeToString(transcendentalNode, 0));
    }

    @Test
    void testExpressionNodeToStringMultiLong() {
        ExpressionNode testNode = new ExpressionNode("+");
        testNode.addChildrenLeft(new ExpressionNode("sin"));
        testNode.getLeftBranch().addChildrenLeft(new ExpressionNode("+"));
        testNode.getLeftBranch().getLeftBranch().addChildrenLeft(new ExpressionNode("x"));
        testNode.getLeftBranch().getLeftBranch().addChildrenRight(new ExpressionNode("1"));
        testNode.addChildrenRight(new ExpressionNode("*"));
        testNode.getRightBranch().addChildrenLeft(new ExpressionNode("-"));
        testNode.getRightBranch().addChildrenRight(new ExpressionNode("3"));
        testNode.getRightBranch().getLeftBranch().addChildrenLeft(new ExpressionNode("x"));
        testNode.getRightBranch().getLeftBranch().addChildrenRight(new ExpressionNode("1"));

        assertEquals("sin ( ( x + 1 ) ) + ( x - 1 ) * 3", testExpressionTreeFunction.expressionNodeToString(testNode, 0));
    }

    @Test
    void testNormalToString() {
        ExpressionNode normalNode = new ExpressionNode("+");
        normalNode.addChildrenLeft(new ExpressionNode("x"));
        normalNode.addChildrenRight(new ExpressionNode("5"));
        assertEquals("( x + 5 )", testExpressionTreeFunction.normalToString(normalNode, 3));
        assertEquals("x + 5", testExpressionTreeFunction.normalToString(normalNode, 1));
    }

    @Test
    void testTranscendentalToString() {
        ExpressionNode transcendentalNode = new ExpressionNode("sin");
        transcendentalNode.addChildrenLeft(new ExpressionNode("x"));
        assertEquals("sin ( x )", testExpressionTreeFunction.transcendentalToString(transcendentalNode));
    }

    @Test
    void testBaseCase() {
        ExpressionNode testNodeZero = new ExpressionNode("10");
        testNodeZero.addChildrenLeft(null);
        testNodeZero.addChildrenRight(null);

        assertEquals("0", testExpressionTreeFunction.baseCase(testNodeZero));

        ExpressionNode testNodeOne = new ExpressionNode("x");
        testNodeOne.addChildrenLeft(null);
        testNodeOne.addChildrenRight(null);

        assertEquals("1", testExpressionTreeFunction.baseCase(testNodeOne));
    }

    @Test
    void testFindDefiniteIntegral() {
        testExpressionTreeFunction.createExpressionTree(testFunction());
        double value = testExpressionTreeFunction.findDefiniteIntegral(testExpressionTreeFunction.getTree(),
                0, Math.PI, 10);
        assertEquals(28.493890099165796, value);
    }


    public ArrayList<String> makeList() {
        testList.add("x");
        testList.add("5");
        testList.add("-");
        testList.add("sin");
        testList.add("x");
        testList.add("log");
        testList.add("/");
        return testList;
    }

    public ArrayList<String> testFunction() {
        ArrayList<String> function = new ArrayList<>();
        function.add("3");
        function.add("x");
        function.add("2");
        function.add("^");
        function.add("*");
        function.add("x");
        function.add("sin");
        function.add("+");
        return function;
    }
}
