package calculator.test;

import calculator.model.OperatorOperand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperatorOperandTest {
    OperatorOperand op = new OperatorOperand();

    @Test
    void testIsOperatorTrue() {
        assertTrue(op.isOperator("+"));
        assertTrue(op.isOperator("-"));
        assertTrue(op.isOperator("*"));
        assertTrue(op.isOperator("/"));
        assertTrue(op.isOperator("^"));
        assertTrue(op.isOperator("sin"));
        assertTrue(op.isOperator("cos"));
        assertTrue(op.isOperator("tan"));
        assertTrue(op.isOperator("arcsin"));
        assertTrue(op.isOperator("arccos"));
        assertTrue(op.isOperator("arctan"));
        assertTrue(op.isOperator("log"));

    }

    @Test
    void testIsOperatorFalse() {
        assertFalse(op.isOperator("1"));
        assertFalse(op.isOperator("e"));
        assertFalse(op.isOperator("pi"));
    }

    @Test
    void testIsTranscendentalTrue() {
        assertTrue(op.isTranscendental("sin"));
        assertTrue(op.isTranscendental("cos"));
        assertTrue(op.isTranscendental("tan"));
        assertTrue(op.isTranscendental("arcsin"));
        assertTrue(op.isTranscendental("arccos"));
        assertTrue(op.isTranscendental("arctan"));
        assertTrue(op.isTranscendental("log"));
    }

    @Test
    void testIsTranscendentalFalse() {
        assertFalse(op.isTranscendental("e"));
        assertFalse(op.isTranscendental("+"));
        assertFalse(op.isTranscendental("/"));
    }

    @Test
    void testIsNonTranscendentalTrue() {
        assertTrue(op.isNonTranscendental("+"));
        assertTrue(op.isNonTranscendental("-"));
        assertTrue(op.isNonTranscendental("*"));
        assertTrue(op.isNonTranscendental("/"));
        assertTrue(op.isNonTranscendental("^"));
    }

    @Test
    void testIsNonTranscendentalFalse() {
        assertFalse(op.isNonTranscendental("1"));
        assertFalse(op.isNonTranscendental("e"));
        assertFalse(op.isNonTranscendental("pi"));
        assertFalse(op.isNonTranscendental("sin"));
        assertFalse(op.isNonTranscendental("cos"));
    }

    @Test
    void testOperatorPrecedence() {
        assertEquals(1, op.operatorPrecedence("+"));
        assertEquals(1, op.operatorPrecedence("-"));
        assertEquals(2, op.operatorPrecedence("*"));
        assertEquals(2, op.operatorPrecedence("/"));
        assertEquals(3, op.operatorPrecedence("^"));
        assertEquals(4, op.operatorPrecedence("sin"));
        assertEquals(4, op.operatorPrecedence("cos"));
        assertEquals(4, op.operatorPrecedence("tan"));
        assertEquals(4, op.operatorPrecedence("arcsin"));
        assertEquals(4, op.operatorPrecedence("arccos"));
        assertEquals(4, op.operatorPrecedence("arctan"));
        assertEquals(4, op.operatorPrecedence("log"));
    }
}
