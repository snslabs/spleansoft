package uz.sportloto.paynet.transform.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import uz.sportloto.paynet.model.PaynetRequest;
import uz.sportloto.paynet.transform.PaynetResponseTransformer;
import uz.sportloto.paynet.transform.TransformException;
import uz.sportloto.paynet.xml.transform.sax.PaynetSAXHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.CharArrayReader;

/**
 * Абстрактный класс трансформера. Осуществляет основную операцию XML трансформации получая SAX Handler от класса
 * его наследующего
 */
abstract public class AbstractResponseTransformer implements PaynetResponseTransformer {

    private static Log log = LogFactory.getLog(AbstractResponseTransformer.class);

    protected abstract PaynetSAXHandler getSAXHandler();

    public String transform(PaynetRequest paynetRequest) throws TransformException {
        SAXParser parser = null;
        System.out.println(paynetRequest.getResponse());
        if(log.isDebugEnabled()){
            log.debug("Response got from paynet:\n"+paynetRequest.getResponse());
        }
        try {
            parser = SAXParserFactory.newInstance().newSAXParser();
            PaynetSAXHandler responseHandler = getSAXHandler();
            final char[] data = paynetRequest.getResponse().toCharArray();
            parser.parse(new InputSource(new CharArrayReader(data)), responseHandler);

            // после трансформации даём возможность каждому трансформеру сделать что-то своё...
            postTransform(paynetRequest);

            String result = responseHandler.toTextMode();
            if (log.isDebugEnabled()) {
                log.debug("Paynet response to be sent to terminal:\n" + result);
            }
            return result;
        } catch (ParserConfigurationException e) {
            log.error(e);
            throw new TransformException(e);
        } catch (SAXException e) {
            log.error(e);
            throw new TransformException(e);
        } catch (IOException e) {
            log.error(e);
            throw new TransformException(e);
        }

    }
    
    protected abstract void postTransform(PaynetRequest paynetRequest) throws TransformException;

}
