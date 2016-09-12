package com.parser.stax;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Uladzislau_Shalamits on 8/25/2016.
 */
public class StaxParserDemo {

    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException, XMLStreamException {

        boolean bFirstName = false;
        boolean bLastName = false;
        boolean bNickName = false;
        boolean bMarks = false;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader(
                new FileInputStream(new File("src/main/resources/com/parser/dom/input_parse.xml")));

        while(eventReader.hasNext()){
            XMLEvent event = eventReader.nextEvent();
            switch(event.getEventType()){
                case XMLStreamConstants.START_ELEMENT: {
                    StartElement startElement = event.asStartElement();
                    String qName = startElement.getName().getLocalPart();
                    if (qName.equalsIgnoreCase("student")) {
                        System.out.println("Start Element : student");
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        String rollNo = attributes.next().getValue();
                        System.out.println("Roll No : " + rollNo);
                    } else if (qName.equalsIgnoreCase("firstname")) {
                        bFirstName = true;
                    } else if (qName.equalsIgnoreCase("lastname")) {
                        bLastName = true;
                    } else if (qName.equalsIgnoreCase("nickname")) {
                        bNickName = true;
                    } else if (qName.equalsIgnoreCase("marks")) {
                        bMarks = true;
                    }
                    break;
                }
                case XMLStreamConstants.CHARACTERS: {
                    Characters characters = event.asCharacters();
                    if (bFirstName) {
                        System.out.println("First Name: " + characters.getData());
                        bFirstName = false;
                    }
                    if (bLastName) {
                        System.out.println("Last Name: " + characters.getData());
                        bLastName = false;
                    }
                    if (bNickName) {
                        System.out.println("Nick Name: " + characters.getData());
                        bNickName = false;
                    }
                    if (bMarks) {
                        System.out.println("Marks: " + characters.getData());
                        bMarks = false;
                    }
                    break;
                }
                case  XMLStreamConstants.END_ELEMENT: {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equalsIgnoreCase("student")) {
                        System.out.println("End Element : student\n");
                    }
                    break;
                }
            }
        }
    }


}
