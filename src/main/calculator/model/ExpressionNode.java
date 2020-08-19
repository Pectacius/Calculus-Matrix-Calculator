package calculator.model;

// Represents a node of the expression binary tree. Holds a value and a right and left branch.
// INVARIANT: Nodes with children are always operators, leaves are always operands
public class ExpressionNode {
    private ExpressionNode leftBranch;
    private ExpressionNode rightBranch;
    private String expressionValue;

    //EFFECTS: creates a node with a value and no children;
    public ExpressionNode(String value) {
        expressionValue = value;
        leftBranch = null;
        rightBranch = null;
    }

    //MODIFIES: this
    //EFFECTS: add a children to the left branch
    public void addChildrenLeft(ExpressionNode node) {
        leftBranch = node;
    }

    //MODIFIES: this
    //EFFECTS: add a children to the right branch
    public void addChildrenRight(ExpressionNode node) {
        rightBranch = node;
    }

    //EFFECTS: returns the leftBranch of the node
    public ExpressionNode getLeftBranch() {
        return leftBranch;
    }

    //EFFECTS: returns the rightBranch of the node
    public ExpressionNode getRightBranch() {
        return rightBranch;
    }

    //EFFECTS: returns the value of the node
    public String getExpressionValue() {
        return expressionValue;
    }
}
