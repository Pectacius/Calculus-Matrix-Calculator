package calculator.model;

//Class with static math methods
public class MathOperations {
    //REQUIRES: operator can only be one of + - * / ^
    //EFFECTS: operates on the two operands via the operator.
    public static double evaluate(String operator, double leftChild, double rightChild) {
        double value;
        switch (operator) {
            case "+":
                value = leftChild + rightChild;
                break;
            case "-":
                value = (leftChild - rightChild);
                break;
            case "*":
                value = (leftChild * rightChild);
                break;
            case "/":
                value = (leftChild / rightChild);
                break;
            //case "^":
            default:
                value = Math.pow(leftChild, rightChild);
                break;
        }
        return value;
    }

    //REQUIRES: operator can only be a transcendental
    public static double evaluate(String operator, double leftChild) {
        switch (operator) {
            case "sin":
                return Math.sin(leftChild);
            case "cos":
                return Math.cos(leftChild);
            case "tan":
                return Math.tan(leftChild);
            case "arcsin":
                return Math.asin(leftChild);
            case "arccos":
                return Math.acos(leftChild);
            case "arctan":
                return Math.atan(leftChild);
            default:
                return Math.log(leftChild);
        }
    }

    //REQUIRES: operator is a transcendental function
    //Default is "log"
    public static String transcendentalDifferentiate(String u, String du, String operator) {
        switch (operator) {
            case "sin":
                return "cos ( " + u + " ) * ( " + du + " )";
            case "cos":
                return "-1 * sin ( " + u + " ) * ( " + du + " )";
            case "tan":
                return "( sec ( " + u + " ) ) ^ 2 * ( " + du + " )";
            case "arcsin":
                return "1 / ( 1 - ( " + u + " ) ^ 2 ) ^ ( 1 / 2 ) * ( " + du + " )";
            case "arccos":
                return "-1 / ( 1 - ( " + u + " ) ^ 2 ) ^ ( 1 / 2 ) * ( " + du + " )";
            case "arctan":
                return "1 / ( 1 + ( " + u + " ) ^ 2 ) * ( " + du + " )";
            default:
                return "1 / ( " + u + " ) * ( " + du + " )";
        }
    }

    //Default is "+" or "-"
    public static String differentiate(String u, String du, String v, String dv, String operator) {
        switch (operator) {
            case "*":
                return "( " + u + " )"  + " * " + "( " + dv  + " )"
                        + " + " + "( " + v + " )" + " * " + "( " +  du + " )";
            case "/":
                return "( " + "( " + du + " )" + " * " + "( " + v + " )" + " - " + "( " + u + " )"
                        + " * " + "( " +  dv + " )" + " )" + " / " + "( " +  v + " )" + " ^ 2";
            case "^":
                return "( " + v + " )" + " * " + "( " + u + " )" + " ^ " + "( " + v + " - 1 )" + " * "
                        + "( " + du + " )" + " + " + "( " + u + " )" + " ^ " + "( " + v + " )" + " * "
                        + "log ( " + u + " )" + " * " + "( " +  dv + " )";
            default:
                return "( " + du + " ) " + operator + " ( " + dv + " )";
        }
    }
}
