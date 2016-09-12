package com.parser.dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

/**
 * Created by Uladzislau_Shalamits on 8/24/2016.
 */
public class DomParserDemo {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("src/main/resources/com/parser/dom/input_parse.xml"));

        NodeList nList = doc.getElementsByTagName("student");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element : " + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                System.out.println("Student roll no : " + eElement.getAttribute("rollno"));
                System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
                System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                System.out.println("Marks : " + eElement.getElementsByTagName("marks").item(0).getTextContent());
            }
        }

    }
}
