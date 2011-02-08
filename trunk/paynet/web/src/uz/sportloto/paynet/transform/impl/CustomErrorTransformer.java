package uz.sportloto.paynet.transform.impl;

import uz.sportloto.paynet.xml.transform.sax.PaynetSAXHandler;
import uz.sportloto.paynet.model.PaynetRequest;
import uz.sportloto.paynet.transform.TransformException;
import uz.sportloto.paynet.transform.PaynetResponseTransformer;

public class CustomErrorTransformer implements PaynetResponseTransformer {
    private long statusCode;
    private String statusText;

    public CustomErrorTransformer(long statusCode, String statusText) {
        this.statusCode = statusCode;
        this.statusText = statusText;
    }

    public String transform(PaynetRequest paynetRequest) throws TransformException {
        StringBuffer sb = new StringBuffer("_PaynetResponse = {\n");
        sb.append("\tstatus_code = ").append(statusCode).append(",\n");
        sb.append("\tstatus_text = \"").append(statusText).append("\",\n");
        sb.append("\tresponse_text = nil\n");
        return sb.append("\n}").toString();
    }

    public Object getAsObject() {
        return null;
    }
}
