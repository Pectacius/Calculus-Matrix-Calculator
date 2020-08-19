package calculator.test;

import calculator.model.ExpressionAnswerPair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpressionAnswerPairTest {
    ExpressionAnswerPair answerPair;
    @BeforeEach
    void runBefore(){
        answerPair = new ExpressionAnswerPair("1 + 2", 3);
    }

    @Test
    void testExpressionAnswerPair() {
        assertEquals("1 + 2", answerPair.getExpression());
        assertEquals(3, answerPair.getResult());
    }
}