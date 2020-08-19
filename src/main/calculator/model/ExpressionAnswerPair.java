package calculator.model;

public class ExpressionAnswerPair {
    private String expression;
    private double result;

    public ExpressionAnswerPair(String expression, double result) {
        this.expression = expression;
        this.result = result;
    }

    public String getExpression() {
        return expression;
    }

    public double getResult() {
        return result;
    }
}
