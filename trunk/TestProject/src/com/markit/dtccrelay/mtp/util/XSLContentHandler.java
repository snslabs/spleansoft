package com.markit.dtccrelay.mtp.util;

import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class XSLContentHandler extends DefaultHandler {
    Map<String, Map<Condition, String>> entries;
    public static final int UNKNOWN = 0;
    public static final int INTEMPLATE = 1;
    public static final int INFIELD = 2;


    private int state = UNKNOWN;
    private String fieldName;
    private String templateName;
    private StringBuffer fieldContent;
    private List<Condition> conditionStack = new ArrayList<Condition>();

    private boolean chooseMode = false;



    public XSLContentHandler(List<Entry> ent) {
        entries = new LinkedHashMap<String, Map<Condition, String>>();
        for (Entry entry : ent) {
            Map<Condition, String> old = entries.put(entry.internalField, new HashMap<Condition, String>());
            if (old != null) {
                System.out.println("WARNING!! Duplicating key entry " + entry);
            }
        }
    }

    public Map<String, Map<Condition, String>> getEntries() {
        return entries;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("template")) {
            state = INTEMPLATE;
            templateName = attributes.getValue("name");

            pushCondition(parseCondition(templateName));

        }

        // choose-when element
        else if(localName.equals("choose")){
            chooseMode = true;
        }
        else if(chooseMode && localName.equals("when")){
            String test = attributes.getValue("test");
            String productTypes = getCurrentCondition().getProductTypesAsString();
            if(test.contains("CDS")){
                productTypes = "CDS";
            }
            else if(test.contains("CDI")){
                productTypes = "CDI";
            }
            else if(test.contains("TRN")){
                productTypes = "TRN";
            }
            else if(test.contains("IRS")){
                productTypes = "IRS";
            }
            else if(test.contains("SWO")){
                productTypes = "SWO";
            }
            else if(test.contains("CROTH")){
                productTypes = "CROTH";
            }
            else if(test.contains("Credit")){
                productTypes = "CDS,CDI,TRN";
            }
            else if(test.contains("InterestRate")){
                productTypes = "IRS,SWO";
            }

            String transactionTypes = getCurrentCondition().getTransactionTypesAsString();


            pushCondition(new Condition(productTypes, transactionTypes));
        }

        // internal model field element
        else if (entries.get(localName) != null) {
            state = INFIELD;
            fieldName = localName;
            fieldContent = new StringBuffer().append("(").append(templateName).append(")");
        }
        // intenral model field element content
        else if (state == INFIELD) {
            fieldContent.append("<").append(localName);
            // adding all attributes
            for (int i = 0; i < attributes.getLength(); i++) {
                fieldContent.append(attributes.getQName(i)).append("=\"").append(attributes.getValue(i)).append("\" ");
            }
            fieldContent.append(">");
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("template")) {
            state = UNKNOWN;
            popCondition();
        }
        else if(localName.equals("choose")){
            chooseMode = false;
        }
        else if(localName.equals("when")){
            popCondition();
        }
        else if (localName.equals(fieldName)) {
            state = INTEMPLATE;
            entries.get(fieldName).put(getCurrentCondition(), fieldContent.toString());
        }
        else if (state == INFIELD) {
            fieldContent.append("</").append(localName).append(">");
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        if (state == INFIELD) {
            fieldContent.append(new String(ch, start, length));
        }
    }

    private Condition getCurrentCondition() {
        if (conditionStack.size() == 0) {
            conditionStack.add(new Condition());
        }
        return conditionStack.get(conditionStack.size() - 1);
    }

    private void pushCondition(Condition condition) {
        conditionStack.add(condition);
    }

    private Condition popCondition() {
        if (conditionStack.size() > 0) {
            return conditionStack.remove(conditionStack.size() - 1);
        }
        else {
            return null;
        }
    }

    private Condition parseCondition(String templateName){
        String productTypes;
        if (templateName.contains("credit")) {
            productTypes = "CDS,TRN,CDI";
        }
        else {
            productTypes = "";
        }

        String transactionTypes;
        if (templateName.contains("posttrade")) {
            transactionTypes = "U,A";
        }
        else if (templateName.contains("termination")) {
            transactionTypes = "U";
        }
        else if (templateName.contains("assignment")) {
            transactionTypes = "A";
        }
        else if (templateName.contains("trade")) {
            transactionTypes = "N";
        }
        else {
            transactionTypes = "";
        }
        return new Condition(productTypes, transactionTypes);
    }
}
