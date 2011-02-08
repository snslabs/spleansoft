package uz.sportloto.paynet.xml.transform.sax;

import org.xml.sax.helpers.DefaultHandler;

public abstract class PaynetSAXHandler extends DefaultHandler {
    public abstract String toTextMode();
}
