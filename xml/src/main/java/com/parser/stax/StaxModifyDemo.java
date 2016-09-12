package com.parser.stax;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;



/**
 * Created by Uladzislau_Shalamits on 8/25/2016.
 */
public class StaxModifyDemo {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {

        XMLEventFactory factory = XMLEventFactory.newInstance();
        XMLEventReader reader = XMLInputFactory.newInstance().createXMLEventReader(
                new FileInputStream("src/main/resources/com/parser/dom/input_parse.xml"));
        XMLEventWriter writer = XMLOutputFactory.newInstance().createXMLEventWriter(
                new FileOutputStream("src/main/resources/com/parser/stax/output_modify.xml"));

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            switch (event.getEventType()) {
                case XMLStreamConstants.CHARACTERS: {
                    Characters cr = event.asCharacters();
                    event = factory.createCharacters(cr.getData().toUpperCase());
                    break;
                }
                case XMLStreamConstants.START_ELEMENT: {
                    StartElement se = event.asStartElement();
                    if (se.getName().equals(new QName("student"))) {
                        event = factory.createStartElement("", "", "people");
                    }
                    break;
                }
            }
            writer.add(event);
        }

        reader.close();
        writer.close();
    }
}
