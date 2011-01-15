package uz.sportloto.paynet.transform.impl;

import uz.sportloto.paynet.model.PaynetRequest;
import uz.sportloto.paynet.model.response.Transaction;
import uz.sportloto.paynet.transform.TransformException;
import uz.sportloto.paynet.xml.transform.sax.PaynetResponseHandler;
import uz.sportloto.paynet.xml.transform.sax.PaynetSAXHandler;

/**
 * Трансформер для обработки ответа на запрос платежа (ACT=2)  и последней успешной транзакции (ACT=3)
 */
public class PaymentTransformer extends AbstractResponseTransformer{

    PaynetResponseHandler paymentHandler = new PaynetResponseHandler();

    protected PaynetSAXHandler getSAXHandler() {
        return paymentHandler;
    }

    public void postTransform(PaynetRequest paynetRequest) throws TransformException {
        // достаём и перекладываем необходимые данные...
        final Transaction transaction = paymentHandler.getTransaction();
        if(transaction == null || transaction.getStatusCode() == null){
            final Transaction newTran = new Transaction();
            newTran.setStatusText(paynetRequest.getResponse());
            newTran.setStatusCode(-99999);
            paynetRequest.setTransaction(newTran);
            paynetRequest.setState('F'); // state - failed, cannot parse response
        }
        else if(paynetRequest.getState() != 'F'){
            paynetRequest.setTransaction(transaction);
            paynetRequest.setStatusCode(transaction.getStatusCode());
            paynetRequest.setStatusText(transaction.getStatusText());
            paynetRequest.setPaynetChequeId(transaction.getPaynetChequeId());
        }
        else{
            // failed by other reason
            final Transaction newTran = new Transaction();
            newTran.setStatusText(paynetRequest.getResponse());
            newTran.setStatusCode(-99999);
            paynetRequest.setTransaction(newTran);
        }
    }

    public Object getAsObject() {
        return paymentHandler.getTransaction();
    }
}
