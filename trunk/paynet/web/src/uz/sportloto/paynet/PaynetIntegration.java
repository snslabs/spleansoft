package uz.sportloto.paynet;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.util.Map;
import java.io.IOException;

import uz.sportloto.paynet.comm.PaynetCommException;
import uz.sportloto.paynet.transform.TransformException;

public interface PaynetIntegration {

    String callPaynet() throws SAXException, ParserConfigurationException, IOException, MandatoryParameterMissing, PaynetCommException, TransformException;
}
