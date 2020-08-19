package calculator.test;

import calculator.model.ExpressionNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ExpressionNodeTest {
    ExpressionNode testNode;
    @BeforeEach
    void runBefore(){
        testNode = new ExpressionNode("+");
    }

    @Test
    void testExpressionNode(){
        assertEquals("+", testNode.getExpressionValue());
        assertNull(testNode.getLeftBranch());
        assertNull(testNode.getRightBranch());
    }

    @Test
    void testAddChildrenLeft(){
        testNode.addChildrenLeft(new ExpressionNode("5"));

        assertEquals("+", testNode.getExpressionValue());
        assertEquals("5", testNode.getLeftBranch().getExpressionValue());
        assertNull(testNode.getRightBranch());
        assertNull(testNode.getLeftBranch().getLeftBranch());
        assertNull(testNode.getLeftBranch().getRightBranch());
    }

    @Test
    void testAddChildrenRight(){
        testNode.addChildrenRight(new ExpressionNode("-"));

        assertEquals("+", testNode.getExpressionValue());
        assertNull(testNode.getLeftBranch());
        assertEquals("-", testNode.getRightBranch().getExpressionValue());
        assertNull(testNode.getRightBranch().getLeftBranch());
        assertNull(testNode.getRightBranch().getRightBranch());
    }
}
