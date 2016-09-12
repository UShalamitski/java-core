package com.parser.dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import java.io.File;
import java.io.IOException;

/**
 * Created by Uladzislau_Shalamits on 8/25/2016.
 */
public class DomModifyDemo {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new File("src/main/resources/com/parser/dom/input_modify.xml"));

        Node cars = doc.getFirstChild();
        Node supercar = doc.getElementsByTagName("supercars").item(0);
        // update supercar attribute
        NamedNodeMap attr = supercar.getAttributes();
        Node nodeAttr = attr.getNamedItem("company");
        nodeAttr.setTextContent("Lamborigini");

        // loop the supercar child node
        NodeList list = supercar.getChildNodes();
        for (int temp = 0; temp < list.getLength(); temp++) {
            Node node = list.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                if ("carname".equals(eElement.getNodeName())) {
                    if ("Ferrari 101".equals(eElement.getTextContent())) {
                        eElement.setTextContent("Lamborigini 001");
                    }
                    if ("Ferrari 202".equals(eElement.getTextContent()))
                        eElement.setTextContent("Lamborigini 002");
                }
            }
        }

        NodeList childNodes = cars.getChildNodes();
        for (int count = 0; count < childNodes.getLength(); count++) {
            Node node = childNodes.item(count);
            if ("luxurycars".equals(node.getNodeName())) {
                cars.removeChild(node);
            }
        }

        // write the content on console
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);

        // Output to file
        StreamResult result = new StreamResult(new File("src/main/resources/com/parser/dom/output_modify.xml"));
        transformer.transform(source, result);

        // Output to console for testing
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);

    }
}
