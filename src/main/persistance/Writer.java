package persistance;

import calculator.model.ExpressionAnswerPair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class Writer {
    DocumentBuilderFactory fact;
    DocumentBuilder builder;
    Document doc;
    String filePath;
    Element root;

    //EFFECTS: creates new file
    public Writer(String fileName) {
        filePath = fileName;
    }

    //MODIFIES: this
    //EFFECTS: converts memory object into XML format
    public void write(ArrayList<ExpressionAnswerPair> memory)
            throws TransformerException, ParserConfigurationException {
        fact = DocumentBuilderFactory.newInstance();
        builder = fact.newDocumentBuilder();
        doc = builder.newDocument();
        root = doc.createElement("storedmemory");
        doc.appendChild(root);
        createNodes(memory);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
    }

    public void createNodes(ArrayList<ExpressionAnswerPair> memory) {
        for (ExpressionAnswerPair pair : memory) {
            Element expressionanswerpair = doc.createElement("expressionanswerpair");

            Element expression = doc.createElement("expression");
            Text expressionval = doc.createTextNode(pair.getExpression());
            expression.appendChild(expressionval);

            Element result = doc.createElement("result");
            Text resultval = doc.createTextNode(Double.toString(pair.getResult()));
            result.appendChild(resultval);

            expressionanswerpair.appendChild(expression);
            expressionanswerpair.appendChild(result);

            root.appendChild(expressionanswerpair);
        }
    }
}
