package calculator.model;

import java.util.ArrayList;
import java.util.Stack;

// Represents a algebraic expression that is represented as a binary tree
public class ExpressionTreeAlgebra extends ExpressionTree {

    //REQUIRES: stringArrayList must be a valid mathematical statement in postfix notation
    //EFFECTS: assigns the value stringArrayList to expressionAsList, other fields set to null;
    public ExpressionTreeAlgebra(ArrayList<String> stringArrayList) {
        super(stringArrayList);
    }

    //EFFECTS: implements convertValue from ExpressionTree class. Returns converted string operand to double equivalent.
    @Override
    public double convertValue(String val) {
        double value;

        if (val.equals("pi")) {
            value = Math.PI;
        } else if (val.equals("e")) {
            value = Math.E;
        } else {
            value = Double.parseDouble(val);
        }
        return value;
    }
}
