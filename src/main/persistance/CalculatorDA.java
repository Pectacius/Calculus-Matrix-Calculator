package persistance;

import calculator.model.ExpressionAnswerPair;
import org.xml.sax.SAXException;
import persistance.Reader;
import persistance.Writer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class CalculatorDA {
    private ArrayList<ExpressionAnswerPair> memory;
    private String filePath = "./data/SavedMemory.xml";

    public CalculatorDA() {
        memory = new ArrayList<>();
    }

    public void loadSaveState() throws ParserConfigurationException, IOException, SAXException {
        memory = Reader.readFile(filePath);
    }

    public void saveMemory() {
        Writer writingFile = new Writer(filePath);
        try {
            writingFile.write(memory);
            System.out.println("saved all memory to file...");
        } catch (TransformerException | ParserConfigurationException e) {
            System.out.println("failed to save memory...sorry");
        }
    }

    public String expressionAnswerPairToString(ExpressionAnswerPair pair) {
        return pair.getExpression() + " " + " = " + pair.getResult();
    }

    public void storeValInMemory(String mathExpression, String result) {
        ExpressionAnswerPair answerPair = new ExpressionAnswerPair(mathExpression, Double.parseDouble(result));
        memory.add(answerPair);
    }

    public ArrayList<ExpressionAnswerPair> getMemory() {
        return memory;
    }
}
