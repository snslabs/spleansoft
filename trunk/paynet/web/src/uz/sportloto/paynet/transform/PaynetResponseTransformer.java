package uz.sportloto.paynet.transform;

import uz.sportloto.paynet.model.PaynetRequest;

/**
 * Интерфейс трансформации ответа от пейнета. 
 */
public interface PaynetResponseTransformer {
    String transform(PaynetRequest paynetRequest) throws TransformException;
    Object getAsObject();
}
