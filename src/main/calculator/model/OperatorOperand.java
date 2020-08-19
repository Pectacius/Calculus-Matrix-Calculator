package calculator.model;

//Methods to operate on operator and operand strings
public class OperatorOperand {

    //EFFECTS: returns true if str is operator, else return false
    public static boolean isOperator(String str) {
        return isTranscendental(str) || isNonTranscendental(str);
    }

    //EFFECTS: returns true if operator is a transcendental, otherwise false
    public static boolean isTranscendental(String operator) {
        switch (operator) {
            case "sin":
            case "cos":
            case "tan":
            case "arcsin":
            case "arccos":
            case "arctan":
            case "log":
                return true;
            default:
                return false;
        }
    }


    //EFFECTS: returns true if operator is +, -, *, /, ^, otherwise false
    public static boolean isNonTranscendental(String operator) {
        switch (operator) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "^":
                return true;
            default:
                return false;
        }
    }

    //REQUIRES: operator can only be a math operator
    //EFFECTS: returns the precedence of operator in integer, higher number, higher precedence
    public static int operatorPrecedence(String operator) {
        int precedence = -1;

        switch (operator) {
            case "+":
            case "-":
                precedence = 1;
                break;
            case "*":
            case "/":
                precedence = 2;
                break;
            case "^":
                precedence = 3;
                break;
            case "sin":
            case "cos":
            case "tan":
            case "arcsin":
            case "arccos":
            case "arctan":
            case "log":
                precedence = 4;
        }
        return precedence;
    }
}
