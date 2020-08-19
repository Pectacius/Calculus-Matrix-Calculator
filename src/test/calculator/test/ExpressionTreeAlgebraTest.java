package calculator.test;

import calculator.model.ExpressionNode;
import calculator.model.ExpressionTreeAlgebra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


public class ExpressionTreeAlgebraTest {
    ExpressionTreeAlgebra testExpressionNode;
    ArrayList<String> testList = new ArrayList<>();
    ExpressionNode testTree;

    @BeforeEach
    void runBefore() {
        testExpressionNode = new ExpressionTreeAlgebra(makeList());
    }

    @Test
    void testExpressionTree() {
        assertEquals(testList.size(), testExpressionNode.getExpressionAsList().size());
        for(int i = 0; i < testList.size(); i++) {
            assertEquals(testList.get(i), testExpressionNode.getExpressionAsList().get(i));
        }
    }

    @Test
    void testCreateExpressionTreeOperator() {
        testList = new ArrayList<>();
        testList.add("5");
        testList.add("9");
        testList.add("+");
        testExpressionNode.createExpressionTree(testList);
        ExpressionNode rootNode = testExpressionNode.getTree();

        assertEquals("+", rootNode.getExpressionValue());
        assertEquals("5", rootNode.getLeftBranch().getExpressionValue());
        assertNull(rootNode.getLeftBranch().getLeftBranch());
        assertNull(rootNode.getRightBranch().getRightBranch());
        assertEquals("9", rootNode.getRightBranch().getExpressionValue());
        assertNull(rootNode.getLeftBranch().getLeftBranch());
        assertNull(rootNode.getRightBranch().getRightBranch());
    }

    @Test
    void testCreateExpressionTreeOperand() {
        testList = new ArrayList<>();
        testList.add("5");
        testExpressionNode.createExpressionTree(testList);
        ExpressionNode rootNode = testExpressionNode.getTree();

        assertEquals("5", rootNode.getExpressionValue());
        assertNull(rootNode.getLeftBranch());
        assertNull(rootNode.getRightBranch());
    }

    @Test
    void testCreateExpressionTreeTranscendental() {
        testList = new ArrayList<>();
        testList.add("1");
        testList.add("log");
        testExpressionNode.createExpressionTree(testList);
        ExpressionNode rootNode = testExpressionNode.getTree();

        assertEquals("log", rootNode.getExpressionValue());
        assertEquals("1", rootNode.getLeftBranch().getExpressionValue());
        assertNull(rootNode.getRightBranch());
        assertNull(rootNode.getLeftBranch().getRightBranch());
        assertNull(rootNode.getLeftBranch().getLeftBranch());
    }

    @Test
    void testSolveExpressionTreeBranchBaseCase() {
        ExpressionNode baseNode = new ExpressionNode("5");
        double val = 5;

        assertEquals(val, testExpressionNode.solveExpressionTreeBranch(baseNode));
    }

    @Test
    void testSolveExpressionTreeBranchTwoLong() {
        ExpressionNode baseNodeOne = new ExpressionNode("3");
        ExpressionNode baseNodeTwo = new ExpressionNode("9");
        ExpressionNode rootNode = new ExpressionNode("+");
        rootNode.addChildrenRight(baseNodeOne);
        rootNode.addChildrenLeft(baseNodeTwo);
        double val = 12;

        assertEquals(val, testExpressionNode.solveExpressionTreeBranch(rootNode));
    }

    @Test
    void testSolveExpressionTreeBranchUneven() {
        ExpressionNode baseNode = new ExpressionNode("1");
        ExpressionNode rootNode = new ExpressionNode("log");
        rootNode.addChildrenLeft(baseNode);
        double val = 0;

        assertEquals(val, testExpressionNode.solveExpressionTreeBranch(rootNode));
    }

    @Test
    void testSolveExpressionTreeBranchDecentlyLong() {
        testExpressionNode.createExpressionTree(makeList());
        ExpressionNode rootNode = testExpressionNode.getTree();
        double val = Math.log((81 * 2.156 - 9)/5.5 + Math.PI);

        assertEquals(val, testExpressionNode.solveExpressionTreeBranch(rootNode));
    }

    @Test
    void testConvertValuePI() {
        double value = testExpressionNode.convertValue("pi");
        assertEquals(value , Math.PI);
    }

    @Test
    void testConvertValueE() {
        double value = testExpressionNode.convertValue("e");
        assertEquals(value , Math.E);
    }

    @Test
    void testConvertValueOther() {
        double val = 7.23;
        double value = testExpressionNode.convertValue("7.23");
        assertEquals(value , val);
    }

    public ArrayList<String> makeList() {
        testList.add("3");
        testList.add("4");
        testList.add("^");
        testList.add("2.156");
        testList.add("*");
        testList.add("9");
        testList.add("-");
        testList.add("5.5");
        testList.add("/");
        testList.add("pi");
        testList.add("+");
        testList.add("log");
        return testList;
    }
}
