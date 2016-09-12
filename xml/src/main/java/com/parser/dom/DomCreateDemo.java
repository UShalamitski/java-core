package com.parser.dom;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Created by Uladzislau_Shalamits on 8/24/2016.
 */
public class DomCreateDemo {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        // root element
        Element rootElement = doc.createElement("cars");
        doc.appendChild(rootElement);

        //  supercars element
        Element supercar = doc.createElement("supercars");
        rootElement.appendChild(supercar);

        // setting attribute to element
        Attr attr = doc.createAttribute("company");
        attr.setValue("Ferrari");
        supercar.setAttributeNode(attr);

        // carname element
        Element carname = doc.createElement("carname");
        carname.setAttribute("type", "type1");
        carname.appendChild(doc.createTextNode("456 Italia"));
        supercar.appendChild(carname);

        // carname element
        Element carname2 = doc.createElement("carname");
        carname2.setAttribute("type", "type2");
        carname2.appendChild(doc.createTextNode("Enzo"));
        supercar.appendChild(carname2);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);

        // Output to file
        StreamResult result = new StreamResult(new File("src/main/resources/com/parser/dom/output_create.xml"));
        transformer.transform(source, result);

        // Output to console for testing
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }
}
