package persistance.test;

import calculator.model.ExpressionAnswerPair;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import persistance.Reader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    @Test
    void testReadFile() {
        try {
            ArrayList<ExpressionAnswerPair> testList = Reader.readFile("./data/testSavedMemory.xml");

            ExpressionAnswerPair pair1 = testList.get(0);
            assertEquals("( 1 + 3.7 ) * 197", pair1.getExpression());
            assertEquals((1 + 3.7)*197, pair1.getResult());

            ExpressionAnswerPair pair2 = testList.get(1);
            assertEquals("( tan pi ) - 3", pair2.getExpression());
            assertEquals(Math.tan(Math.PI) - 3, pair2.getResult());

        } catch (ParserConfigurationException e) {
            fail("ParserConfigurationException should not have been thrown");
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        } catch (SAXException e) {
            fail("SAXException should not have been thrown");
        }

    }
}
