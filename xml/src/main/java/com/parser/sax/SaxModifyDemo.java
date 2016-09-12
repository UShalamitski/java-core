package com.parser.sax;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by Uladzislau_Shalamits on 8/25/2016.
 */
public class SaxModifyDemo extends DefaultHandler {
    static String displayText[] = new String[1000];
    static int numberLines = 0;
    static String indentation = "";

    public static void main(String args[]) {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SaxModifyDemo obj = new SaxModifyDemo();
            obj.childLoop(new File("src/main/resources/com/parser/dom/input_parse.xml"));
            FileWriter filewriter = new FileWriter("src/main/resources/com/parser/sax/output_modify.xml");
            for (int loopIndex = 0; loopIndex < numberLines; loopIndex++) {
                filewriter.write(displayText[loopIndex].toCharArray());
                filewriter.write('\n');
                System.out.println(displayText[loopIndex].toString());
            }
            filewriter.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    public void childLoop(File input) {
        DefaultHandler handler = this;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(input, handler);
        } catch (Throwable t) {
        }
    }

    public void startDocument() {
        displayText[numberLines] = indentation;
        displayText[numberLines] += "<?xml version=\"1.0\" encoding=\"" +
                "UTF-8" + "\"?>";
        numberLines++;
    }

    public void processingInstruction(String target, String data) {
        displayText[numberLines] = indentation;
        displayText[numberLines] += "<?";
        displayText[numberLines] += target;
        if (data != null && data.length() > 0) {
            displayText[numberLines] += ' ';
            displayText[numberLines] += data;
        }
        displayText[numberLines] += "?>";
        numberLines++;
    }

    public void startElement(String uri, String localName, String qualifiedName, Attributes attributes) {
        displayText[numberLines] = indentation;
        indentation += "    ";

        displayText[numberLines] += '<';
        displayText[numberLines] += qualifiedName;
        if (attributes != null) {
            int numberAttributes = attributes.getLength();
            for (int loopIndex = 0; loopIndex < numberAttributes; loopIndex++) {
                displayText[numberLines] += ' ';
                displayText[numberLines] += attributes.getQName(loopIndex);
                displayText[numberLines] += "=\"";
                displayText[numberLines] += attributes.getValue(loopIndex);
                displayText[numberLines] += '"';
            }
        }
        displayText[numberLines] += '>';
        numberLines++;
    }

    public void characters(char characters[], int start, int length) {
        String characterData = (new String(characters, start, length)).trim();
        if (characterData.indexOf("\n") < 0 && characterData.length() > 0) {
            displayText[numberLines] = indentation;
            displayText[numberLines] += characterData;
            numberLines++;
        }
    }

    public void endElement(String uri, String localName, String qualifiedName) {
        indentation = indentation.substring(0, indentation.length() - 4);
        displayText[numberLines] = indentation;
        displayText[numberLines] += "</";
        displayText[numberLines] += qualifiedName;
        displayText[numberLines] += '>';
        numberLines++;

        if (qualifiedName.equals("marks")) {
            startElement("", "Result", "Result", null);
            characters("Pass".toCharArray(), 0, "Pass".length());
            endElement("", "Result", "Result");
        }
    }
}