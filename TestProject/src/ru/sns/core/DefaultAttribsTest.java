package ru.sns.core;

import junit.framework.TestCase;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.dom.DOMResult;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.File;

public class DefaultAttribsTest extends TestCase {

    public void testDefaultAttributes() throws Exception {

        final SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        final Schema schema = schemaFactory.newSchema(new File("C:\\Serge\\Projects\\TestProject\\src\\test-schema.xsd"));



        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        InputStream asStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.xml");
        Document document = documentBuilder.parse(asStream);
        logXml(document);
        DOMResult domResult = new DOMResult();
        schema.newValidator().validate(new DOMSource(document), domResult);

        logXml((Document) domResult.getNode());


    }

    public static void logXml(Document document) {
        try {
            OutputFormat of = new OutputFormat("XML", "UTF-8", true);
            ByteArrayOutputStream os = new ByteArrayOutputStream(2048);
            XMLSerializer xmlSerializer = new XMLSerializer(System.out, of);
            xmlSerializer.serialize(document);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
