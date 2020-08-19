package persistance;

import calculator.model.ExpressionAnswerPair;

import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.ArrayList;

public class Reader {
    private static ArrayList<ExpressionAnswerPair> memory = new ArrayList<>();

    public static ArrayList<ExpressionAnswerPair> readFile(String filename)
            throws ParserConfigurationException, IOException, SAXException {

        File file = new File(filename);
        DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = fact.newDocumentBuilder();
        Document doc = builder.parse(file);
        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("expressionanswerpair");

        for (int i = 0; i < nodeList.getLength(); i++) {
            String expression;
            Double answer;
            ExpressionAnswerPair expressionAnswerPair;

            Node node = nodeList.item(i);
            Element element = (Element) node;
            expression = element.getElementsByTagName("expression").item(0).getTextContent();
            answer = Double.parseDouble(element.getElementsByTagName("result").item(0).getTextContent());

            expressionAnswerPair = new ExpressionAnswerPair(expression, answer);
            memory.add(expressionAnswerPair);
        }
        return memory;
    }
}
