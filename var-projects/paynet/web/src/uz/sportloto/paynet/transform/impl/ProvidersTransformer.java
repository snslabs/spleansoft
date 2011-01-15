package uz.sportloto.paynet.transform.impl;

import uz.sportloto.paynet.model.PaynetRequest;
import uz.sportloto.paynet.transform.TransformException;
import uz.sportloto.paynet.xml.transform.sax.PaynetProvidersHandler;
import uz.sportloto.paynet.xml.transform.sax.PaynetSAXHandler;

/**
 * Трансформер для обработки ответа на запрос списка провайдеров (ACT=1)
 */
public class ProvidersTransformer extends AbstractResponseTransformer{

    PaynetProvidersHandler providersHandler = new PaynetProvidersHandler();

    protected PaynetSAXHandler getSAXHandler() {
        return providersHandler;
    }

    public void postTransform(PaynetRequest paynetRequest) throws TransformException {

    }

    /***
     * Return java.util.Collection&lt;Provider>
     * @return all providers parsed as Collection&lt;Provider>
     */
    public Object getAsObject() {
        return providersHandler.getProvidersMap().values();
    }
}