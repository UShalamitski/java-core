package com.xslt;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * Created by Uladzislau_Shalamits on 8/24/2016.
 */
public class XsltExample1 {

    public static void main(String args[]) throws Exception {
        StreamSource source = new StreamSource(new File("src/main/resources/xslt.xml"));
        StreamSource stylesource = new StreamSource(new File("src/main/resources/xslt_stylesheet.xml"));

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(stylesource);

        StreamResult result = new StreamResult(new File("src/main/resources/xslt_result_1.html"));
        transformer.transform(source, result);
    }
}
