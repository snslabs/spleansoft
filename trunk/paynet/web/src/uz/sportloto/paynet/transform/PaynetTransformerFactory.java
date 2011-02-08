package uz.sportloto.paynet.transform;

import uz.sportloto.paynet.transform.impl.PaymentTransformer;
import uz.sportloto.paynet.transform.impl.ProvidersTransformer;
import uz.sportloto.paynet.transform.impl.ReportTransformer;

/**
 * Фабрика для создания трансформеров. Работает в зависимости от кода операции (ACT)
 */
public class PaynetTransformerFactory {
    /**
     * Создаёт трансформер для заданного типа операции
     * @param actionCode тип операции (1,2,3,4)
     * @return трансформер для обработки ответа от пейнета
     */
    public static PaynetResponseTransformer getTransformer(int actionCode) {
        switch (actionCode) {
            case 1:
                return new ProvidersTransformer();
            case 2:
            case 3:
                return new PaymentTransformer();
            case 4:
                return new ReportTransformer();
            default:
                throw new IllegalArgumentException("Unknown operation code " + actionCode + "! " +
                        "Cannot find appropriate response transformer");
        }
    }
}
