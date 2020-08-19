package calculator.model;

import java.util.ArrayList;
import java.util.Stack;

//Represents a math expression, algebraic or a function as a binary tree
//and operations that can be performed on the expression
public abstract class ExpressionTree {
    protected ExpressionNode tree;
    protected ArrayList<String> expressionAsList;
    protected Stack<ExpressionNode> expressionStack;

    public ExpressionTree(ArrayList<String> stringArrayList) {
        tree = null;
        expressionAsList = stringArrayList;
        expressionStack = new Stack<>();
    }

    //INVARIANT: if operand called, the stack will have two other items
    //MODIFIES: this
    //EFFECTS: converts expressionAsList to a binary tree format
    public void createExpressionTree(ArrayList<String> expressionAsList) {
        for (String str : expressionAsList) {
            ExpressionNode createdNode = new ExpressionNode(str);
            ExpressionNode poppedNode1;
            ExpressionNode poppedNode2;

            if (OperatorOperand.isNonTranscendental(str)) {
                poppedNode1 = expressionStack.pop();
                poppedNode2 = expressionStack.pop();
                createdNode.addChildrenRight(poppedNode1);
                createdNode.addChildrenLeft(poppedNode2);
            } else if (OperatorOperand.isTranscendental(str)) {
                poppedNode1 = expressionStack.pop();
                createdNode.addChildrenLeft(poppedNode1);
                createdNode.addChildrenRight(null);
            }
            expressionStack.push(createdNode);
        }

        tree = expressionStack.pop();
    }

    //INVARIANT: A node either has one non-null left branch or two null branches.
    //EFFECTS: returns the value of a branch of a node, if left and right branches are null then
    //         return expression value of node
    public double solveExpressionTreeBranch(ExpressionNode node) {
        if ((node.getLeftBranch() == null)) {
            return convertValue(node.getExpressionValue());
        } else if ((node.getRightBranch() == null)) {
            return MathOperations.evaluate(node.getExpressionValue(), solveExpressionTreeBranch(node.getLeftBranch()));
        } else {
            return MathOperations.evaluate(node.getExpressionValue(), solveExpressionTreeBranch(node.getLeftBranch()),
                    solveExpressionTreeBranch(node.getRightBranch()));
        }
    }

    //REQUIRES: val is an operand
    //EFFECTS: converts the string operand its double equivalent
    public  abstract double convertValue(String val);

    //EFFECTS: returns tree
    public ExpressionNode getTree() {
        return tree;
    }

    //EFFECTS: returns expressionAsList
    public ArrayList<String> getExpressionAsList() {
        return expressionAsList;
    }
}
