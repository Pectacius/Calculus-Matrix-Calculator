package calculator.test;

import calculator.model.MathOperations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathOperationsTest {

    @Test
    void testOperateAdd() {
        assertEquals(MathOperations.evaluate("+", 1.5, 4), (1.5 + 4));
    }

    @Test
    void testOperateSubtract() {
        assertEquals(MathOperations.evaluate("-", 1.5, 4), (1.5 - 4));
    }

    @Test
    void testOperateMultiply() {
        assertEquals(MathOperations.evaluate("*", 1.5, 4), (1.5 * 4));
    }

    @Test
    void testOperateDivide() {
        assertEquals(MathOperations.evaluate("/", 1.5, 4), (1.5 / 4));
    }

    @Test
    void testOperatePower() {
        assertEquals(MathOperations.evaluate("^", 1.5, 4), Math.pow(1.5, 4));
    }

    @Test
    void testEvaluateTranscendental() {
        assertEquals(MathOperations.evaluate("sin", Math.PI/2), 1);
        assertEquals(MathOperations.evaluate("cos", Math.PI), -1);
        assertEquals(MathOperations.evaluate("tan", 0), 0);
        assertEquals(MathOperations.evaluate("arcsin", 1), Math.PI/2);
        assertEquals(MathOperations.evaluate("arccos", 1), 0);
        assertEquals(MathOperations.evaluate("arctan", 1), Math.PI/4);
        assertEquals(MathOperations.evaluate("log", Math.E), 1);
    }

    @Test
    void testDifferentiate() {
        String u = "x ^ 2";
        String du = "2 * x";
        String v = "x";
        String dv = "1";

        String result = MathOperations.differentiate(u, du, v, dv, "*");
        assertEquals("( x ^ 2 ) * ( 1 ) + ( x ) * ( 2 * x )", result);

        result = MathOperations.differentiate(u, du, v, dv, "/");
        assertEquals("( ( 2 * x ) * ( x ) - ( x ^ 2 ) * ( 1 ) ) / ( x ) ^ 2", result);

        result = MathOperations.differentiate(u, du, v, dv, "^");
        assertEquals("( x ) * ( x ^ 2 ) ^ ( x - 1 ) * ( 2 * x ) + ( x ^ 2 ) ^ ( x ) * log ( x ^ 2 ) * ( 1 )", result);

        result = MathOperations.differentiate(u, du, v, dv, "+");
        assertEquals("( 2 * x ) + ( 1 )", result);

        result = MathOperations.differentiate(u, du, v, dv, "-");
        assertEquals("( 2 * x ) - ( 1 )", result);

    }

    @Test
    void testTranscendentalDifferentiate() {
        String u = "x ^ 2";
        String du = "2 * x";

        String result = MathOperations.transcendentalDifferentiate(u, du, "sin");
        assertEquals("cos ( x ^ 2 ) * ( 2 * x )", result);

        result = MathOperations.transcendentalDifferentiate(u, du, "cos");
        assertEquals("-1 * sin ( x ^ 2 ) * ( 2 * x )", result);

        result = MathOperations.transcendentalDifferentiate(u, du, "tan");
        assertEquals("( sec ( x ^ 2 ) ) ^ 2 * ( 2 * x )", result);

        result = MathOperations.transcendentalDifferentiate(u, du, "arcsin");
        assertEquals("1 / ( 1 - ( x ^ 2 ) ^ 2 ) ^ ( 1 / 2 ) * ( 2 * x )", result);

        result = MathOperations.transcendentalDifferentiate(u, du, "arccos");
        assertEquals("-1 / ( 1 - ( x ^ 2 ) ^ 2 ) ^ ( 1 / 2 ) * ( 2 * x )", result);

        result = MathOperations.transcendentalDifferentiate(u, du, "arctan");
        assertEquals("1 / ( 1 + ( x ^ 2 ) ^ 2 ) * ( 2 * x )", result);

        result = MathOperations.transcendentalDifferentiate(u, du, "log");
        assertEquals("1 / ( x ^ 2 ) * ( 2 * x )", result);
    }
}

