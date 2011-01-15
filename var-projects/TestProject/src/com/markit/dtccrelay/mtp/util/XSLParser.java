package com.markit.dtccrelay.mtp.util;

import com.markit.dtccrelay.model.DtccTrade;
import com.markit.dtccrelay.model.CreditSwap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.*;
import java.lang.reflect.Field;
import java.io.FileInputStream;

import org.xml.sax.helpers.ParserFactory;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.Parser;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;

/**
 * this class will convert incoming xslt template into pairs (InternalField, xPath)
 */
public class XSLParser {
    private List<Entry> creditMapping;

    public void process() throws Exception{

        // loading fields list
        creditMapping = getFieldsForClass(CreditSwap.class);

        /*
        // loading xsl templates - XPaths
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        XSLContentHandler xslContentHandler = new XSLContentHandler(creditMapping);
        xmlReader.setContentHandler(xslContentHandler);
        xmlReader.parse(new InputSource( new FileInputStream("C:\\SVNRoot\\MARKIT\\DTCCRELAY\\sm-project\\src\\conf\\xsl\\reply\\credit.xsl") ));
        Map<String,Map<Condition,String>> xPathEntries = xslContentHandler.getEntries();
        // storing XPaths into mapping table
        for(Entry e : creditMapping){
            e.addAllXpath(xPathEntries.get( e.internalField ) );
        }
        */
        // loading DB Mapping
        XMLReader xmlReader2 = XMLReaderFactory.createXMLReader();
        TradeSaverContentHandler tradeSaverContentHandler = new TradeSaverContentHandler(creditMapping);
        xmlReader2.setContentHandler(tradeSaverContentHandler);
        xmlReader2.parse(new InputSource( new FileInputStream("C:\\SVNRoot\\MARKIT\\DTCCRELAY\\sm-project\\src\\conf\\su\\spring.trade-saver.xml") ));
        Map<String,Entry> dbMapping = tradeSaverContentHandler.getMapping();

        System.out.println(tradeSaverContentHandler.toString());


//        printMapping(creditMapping);
    }

    private void printMapping(List<Entry> creditMapping) {
        for (Entry entry : creditMapping) {
            System.out.println(entry);
        }
    }

    private List<Entry> getFieldsForClass(Class<? extends DtccTrade> tradeClass){
        List<Field> fields = new ArrayList<Field>();
        fields.addAll( Arrays.asList(tradeClass.getDeclaredFields()));
        System.out.println("loading fields for "+tradeClass);
        Class superClass = tradeClass;
        while(!(superClass = superClass.getSuperclass()).equals(Object.class)){
            System.out.println("loading fields for "+superClass);
            fields.addAll( Arrays.asList(superClass.getDeclaredFields() ));
        }

        List<Entry> entries = new ArrayList<Entry>();
        for (Field field : fields) {
            entries.add(new Entry(field));
        }
        Collections.sort(entries);
        return entries;
    }

    public List<Entry> getCreditMapping() {
        return creditMapping;
    }
}
