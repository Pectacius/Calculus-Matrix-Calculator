package calculator.model;

import java.util.ArrayList;

public class ExpressionTreeFunction extends ExpressionTree {
    private double value;

    public ExpressionTreeFunction(ArrayList<String> stringArrayList) {
        super(stringArrayList);
    }

    //EFFECT: implements convertValue method from ExpressionTree, returns converted string operand to double value.
    //        If "x" returns field value
    @Override
    public double convertValue(String val) {
        double value;

        switch (val) {
            case "pi":
                value = Math.PI;
                break;
            case "e":
                value = Math.E;
                break;
            case "x":
                value = this.value;
                break;
            default:
                value = Double.parseDouble(val);
                break;
        }
        return value;
    }

    //EFFECTS: returns the evaluated function at specific value;
    public double evaluateAt(ExpressionNode node, double number) {
        setValue(number);
        return solveExpressionTreeBranch(node);
    }

    public String findDerivative(ExpressionNode node) {
        if (node.getLeftBranch() == null) {
            return baseCase(node);
        } else {
            String operator = node.getExpressionValue();
            int precedence = OperatorOperand.operatorPrecedence(operator);

            String u = expressionNodeToString(node.getLeftBranch(), precedence);
            String du = findDerivative(node.getLeftBranch());

            if (node.getRightBranch() == null) {
                return MathOperations.transcendentalDifferentiate(u, du, operator);
            } else {
                String v = expressionNodeToString(node.getRightBranch(), precedence);
                String dv = findDerivative(node.getRightBranch());
                return MathOperations.differentiate(u, du, v, dv, operator);
            }
        }
    }


    public String baseCase(ExpressionNode node) {
        if (node.getExpressionValue().equals("x")) {
            return "1";
        } else {
            return "0";
        }
    }

    public String expressionNodeToString(ExpressionNode node, int order) {
        if (node.getLeftBranch() == null) {
            return node.getExpressionValue();
        } else if (node.getRightBranch() == null) {
            return transcendentalToString(node);
        } else {
            return normalToString(node, order);
        }
    }

    public String normalToString(ExpressionNode node, int precedence) {
        int currPre = OperatorOperand.operatorPrecedence(node.getExpressionValue());
        if (precedence > currPre) {
            return "( " + expressionNodeToString(node.getLeftBranch(), currPre) + " " + node.getExpressionValue() + " "
                    + expressionNodeToString(node.getRightBranch(), currPre) + " )";
        } else {
            return expressionNodeToString(node.getLeftBranch(), currPre) + " " + node.getExpressionValue() + " "
                    + expressionNodeToString(node.getRightBranch(), currPre);
        }
    }

    public String transcendentalToString(ExpressionNode node) {
        int currPre = OperatorOperand.operatorPrecedence(node.getExpressionValue());
        return node.getExpressionValue() + " ( " + expressionNodeToString(node.getLeftBranch(),
                currPre) + " )";
    }

    //EFFECTS: finds definite integral of expression node
    public double findDefiniteIntegral(ExpressionNode node, double lowerbound, double upperbound, int divisions) {
        double deltaX = (upperbound - lowerbound) / divisions;
        double sum = 0;
        for (int i = 0; i < divisions; i++) {
            sum = sum + (evaluateAt(node, lowerbound + (deltaX * i)) * deltaX);
        }
        return sum;
    }


    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
