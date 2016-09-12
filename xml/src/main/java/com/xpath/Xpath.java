package com.xpath;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Uladzislau_Shalamits on 8/24/2016.
 */
public class Xpath {

    public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, SAXException,
            IOException, XPathExpressionException {
        FileInputStream file = new FileInputStream(new File("src/main/resources/xpath.xml"));

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(file);

        XPath xPath =  XPathFactory.newInstance().newXPath();

        System.out.println("\n*************************");
        String expression = "/Employees/Employee[@emplid='3333']/email";
        System.out.println(expression);
        System.out.println(xPath.compile(expression).evaluate(xmlDocument));

        System.out.println("\n*************************");
        expression = "/Employees/Employee/firstname";
        System.out.println(expression);
        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
        }

        System.out.println("\n*************************");
        expression = "/Employees/Employee[@type='admin']/firstname";
        System.out.println(expression);
        nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
        }

        System.out.println("\n*************************");
        expression = "/Employees/Employee[@emplid='2222']";
        System.out.println(expression);
        Node node = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);
        if(null != node) {
            nodeList = node.getChildNodes();
            for (int i = 0;null!=nodeList && i < nodeList.getLength(); i++) {
                Node nod = nodeList.item(i);
                if(nod.getNodeType() == Node.ELEMENT_NODE)
                    System.out.println(nodeList.item(i).getNodeName() + " : " + nod.getFirstChild().getNodeValue());
            };
        }

        System.out.println("\n*************************");
        expression = "/Employees/Employee[age>40]/firstname";
        nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        System.out.println(expression);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
        }

        System.out.println("\n*************************");
        expression = "/Employees/Employee[1]/firstname";
        System.out.println(expression);
        nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
        }
        System.out.println("\n*************************");
        expression = "/Employees/Employee[position() <= 2]/firstname";
        System.out.println(expression);
        nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
        }

        System.out.println("\n*************************");
        expression = "/Employees/Employee[last()]/firstname";
        System.out.println(expression);
        nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
        }
    }

}
