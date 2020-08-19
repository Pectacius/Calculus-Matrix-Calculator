package calculator.model;

import java.util.*;

//A mathematical expression expressed as both in its infix and postfix form
public class PostFix {
    private String infix;
    private ArrayList<String> infixList;
    private Stack<String> expStack;
    private ArrayList<String> postFix;

    //EFFECTS: creates new instances for variables
    public PostFix(String infix) {
        this.infix = infix;
        infixList = new ArrayList<>();
        expStack = new Stack<>();
        postFix = new ArrayList<>();
    }

    //REQUIRES: infixExp to be valid math expression
    //MODIFIES: this
    //EFFECTS: parses string into a list
    public void parseExpression() {
        String regex = "[ ]";
        //"?<=[-+*/()ep])|(?=[-+*/()ei]";
        String[] splitString = infix.split(regex);
        infixList.addAll(Arrays.asList(splitString));
    }

    //REQUIRES: infix is a parsed string in infix form
    //MODIFIES: this
    //EFFECTS: changes the string from infix to postfix form
    public void infixToPostFix() {
        for (String str : infixList) {
            if (str.equals("(")) {
                leftParenthesis(str);
            } else if (str.equals(")")) {
                rightParenthesis();
            } else if (OperatorOperand.isOperator(str)) {
                operator(str);
            } else {
                operand(str);
            }
        }
        clearTheStack();
    }

    //MODIFIES: this
    //EFFECTS: removes all remaining operators from the stack and appends on to the postfix list.
    public void clearTheStack() {
        int size = expStack.size();
        for (int i = size - 1; i >= 0; i--) {
            String str = expStack.get(i);
            if (!str.equals("(")) {
                postFix.add(str);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: pushes the string into the stack.
    public void leftParenthesis(String str) {
        expStack.push(str);
    }

    //MODIFIES: this
    //EFFECTS: pushes and pops operators from stack to array list based on algorithm
    public void operator(String str) {
        if (!(emptyStack()) && (OperatorOperand.operatorPrecedence(str)
                <= OperatorOperand.operatorPrecedence(expStack.lastElement()))) {
            String stackTop = expStack.lastElement();
            while ((!(stackTop.equals("("))) || (OperatorOperand.operatorPrecedence(str)
                            <= OperatorOperand.operatorPrecedence(expStack.lastElement()))) {
                postFix.add(stackTop);
                expStack.pop();
                if (!(emptyStack())) {
                    stackTop = expStack.lastElement();
                    continue;
                }
                break;
            }
        }
        expStack.push(str);
    }

    //MODIFIES: this
    //EFFECTS: adds the operand to the postfix list
    public void operand(String str) {
        postFix.add(str);
    }

    //MODIFIES: this
    //EFFECTS: removes all operators from the stack until reaching left parenthesis and appends them to the postfix list
    public void rightParenthesis() {
        int size = expStack.size();
        int occurrence = expStack.lastIndexOf("(");
        for (int i = size - 1; i >= occurrence; i--) {
            if (!(expStack.get(i).equals("("))) {
                postFix.add(expStack.get(i));
            }
            expStack.pop();
        }
    }

    //EFFECTS: check if stack is empty
    public boolean emptyStack() {
        return (expStack.size() == 0);
    }

    public String getInfix() {
        return infix;
    }

    public ArrayList<String> getInfixList() {
        return infixList;
    }

    public Stack<String> getExpStack() {
        return expStack;
    }

    public ArrayList<String> getPostFix() {
        return postFix;
    }
}
