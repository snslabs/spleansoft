package com.markit.dtccrelay.mtp.util;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class TradeSaverContentHandler extends DefaultHandler {

    Map<String, Entry> mapping = new LinkedHashMap<String, Entry>();

    public TradeSaverContentHandler(List<Entry> m) {
        for (Entry e : m) {
            mapping.put(e.internalField, e);
        }
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("mappingEntry") || localName.equals("creditMappingEntry") ) {
            String property = attributes.getValue("property");
            if (property != null) {
                String productTypes = attributes.getValue("productTypes");
                if(productTypes==null){
                    if(localName.equals("creditMappingEntry")){
                        productTypes = "CDS, CDI, TRN";
                    }
                }

                String transactionTypes = attributes.getValue("transactionTypes");
                Entry entry = mapping.get(property);
                if(entry != null){
                    entry.addDBMapping(
                            new Condition(
                                    productTypes,
                                    transactionTypes
                            ),
                            new DBMapping(
                                    attributes.getValue("table"),
                                    attributes.getValue("column"),
                                    attributes.getValue("updatable")
                            )
                    );
                }
            }
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
    }

    public void characters(char ch[], int start, int length) throws SAXException {
    }

    public Map<String, Entry> getMapping() {
        return mapping;
    }

    public String toString(){

        // deleting  empty entries

        StringBuffer sb = new StringBuffer();
        for (String key : mapping.keySet()) {
            if(!mapping.get(key).getDb().isEmpty()){
                // sb.append(key).append("\n").append(mapping.get(key).dbMappingToString()).append("\n");
                // <result property="messageId" column="MESSAGEID"/>
                sb.append("<result property=\"").append(key).append("\"").append(" column=\"").append(mapping.get(key).dbMappingToString2()).append("\"/>\n");
            }

        }
        return sb.toString();
    }
}
