package persistance.test;

import calculator.model.ExpressionAnswerPair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import persistance.Reader;
import persistance.Writer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILE_PATH = "./data/testSavedMemory.xml";
    private Writer testWriter;
    private ArrayList<ExpressionAnswerPair> testList = new ArrayList<>();

    @BeforeEach
    void runBefore() {
        testWriter = new Writer(TEST_FILE_PATH);
        makeTestList();
    }

    @Test
    void testWriteData() {
        try {
            testWriter.write(testList);
        } catch (TransformerException e) {
            fail("TransformerException should not have been thrown");
        } catch (ParserConfigurationException e) {
            fail("ParserConfigurationException should not have been thrown");
        }

        try {
            testList = Reader.readFile(TEST_FILE_PATH);
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

    public void makeTestList() {
        ExpressionAnswerPair pair;
        pair = new ExpressionAnswerPair("( 1 + 3.7 ) * 197", (1 + 3.7)*197);
        testList.add(pair);
        pair = new ExpressionAnswerPair("( tan pi ) - 3", Math.tan(Math.PI) - 3);
        testList.add(pair);
    }
}
