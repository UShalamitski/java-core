package com.parser.stax;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Created by Uladzislau_Shalamits on 8/25/2016.
 */
public class StaxCreateDemo {

    public static void main(String[] args) throws XMLStreamException, IOException {

        StringWriter stringWriter = new StringWriter();
        XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);

        xMLStreamWriter.writeStartDocument();
            xMLStreamWriter.writeStartElement("cars");

                xMLStreamWriter.writeStartElement("supercars");
                xMLStreamWriter.writeAttribute("company", "Ferrari");

                    xMLStreamWriter.writeStartElement("carname");
                        xMLStreamWriter.writeAttribute("type", "formula one");
                        xMLStreamWriter.writeCharacters("Ferrari 101");
                    xMLStreamWriter.writeEndElement();

                    xMLStreamWriter.writeStartElement("carname");
                        xMLStreamWriter.writeAttribute("type", "sports");
                        xMLStreamWriter.writeCharacters("Ferrari 202");
                    xMLStreamWriter.writeEndElement();

                xMLStreamWriter.writeEndElement();

            xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeEndDocument();

        xMLStreamWriter.flush();
        xMLStreamWriter.close();

        String xmlString = stringWriter.getBuffer().toString();
        BufferedWriter br = new BufferedWriter(new FileWriter(new File("src/main/resources/com/parser/stax/output_create.xml")));
        br.write(xmlString);
        br.close();
        System.out.println(xmlString);
        stringWriter.close();
    }

}
