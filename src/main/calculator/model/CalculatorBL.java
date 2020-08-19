package calculator.model;

public class CalculatorBL {
    private PostFix postfix;

    public String calculateExpression(String mathExpression) {

        postfix = new PostFix(mathExpression);
        postfix.parseExpression();
        postfix.infixToPostFix();

        ExpressionTreeAlgebra treeNumber = new ExpressionTreeAlgebra(postfix.getPostFix());
        treeNumber.createExpressionTree(treeNumber.getExpressionAsList());
        double result = treeNumber.solveExpressionTreeBranch(treeNumber.getTree());

        return Double.toString(result);
    }

    public Double evaluateFunction(String mathFunction, String value) {
        postfix = new PostFix(mathFunction);
        postfix.parseExpression();
        postfix.infixToPostFix();

        ExpressionTreeFunction treeFunction = new ExpressionTreeFunction(postfix.getPostFix());
        treeFunction.createExpressionTree(treeFunction.getExpressionAsList());

        return treeFunction.evaluateAt(treeFunction.getTree(), Double.parseDouble(value));
    }

    public String differentiate(String mathFunction) {
        postfix = new PostFix(mathFunction);
        postfix.parseExpression();
        postfix.infixToPostFix();

        ExpressionTreeFunction treeFunction = new ExpressionTreeFunction(postfix.getPostFix());
        treeFunction.createExpressionTree(treeFunction.getExpressionAsList());
        return treeFunction.findDerivative(treeFunction.getTree());
    }

    public Double integrate(String mathFunction, double lowerBound, double upperBound) {
        postfix = new PostFix(mathFunction);
        postfix.parseExpression();
        postfix.infixToPostFix();

        ExpressionTreeFunction treeFunction = new ExpressionTreeFunction(postfix.getPostFix());
        treeFunction.createExpressionTree(treeFunction.getExpressionAsList());
        return treeFunction.findDefiniteIntegral(treeFunction.getTree(), lowerBound, upperBound, 1000);
    }

    public String convertToBinary(String result) {
        int val = (int) Double.parseDouble(result);
        return Integer.toBinaryString(val);
    }
}
