package uz.sportloto.paynet;

import junit.framework.TestCase;
import uz.sportloto.paynet.transform.impl.PaymentTransformer;
import uz.sportloto.paynet.model.PaynetRequest;

public class PaymentResponseTransformerTest extends TestCase {

    public void testTransform() throws Exception{
        PaymentTransformer transformer = new PaymentTransformer();
        final PaynetRequest paynetRequest = new PaynetRequest();
        paynetRequest.setResponse("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<response>"+
"    <transaction>"+
"        <status>0</status>"+
"        <status_text>Платеж проведен успешно</status_text>"+
"        <receipt>"+
"            <provider_name label=\"Оператор\">МТС</provider_name>"+
"            <service_name label=\"Услуга\">Пополнение счета</service_name>"+
"            <time label=\"Время\">14.03.2008 14:57:04</time>"+
"            <terminal_id label=\"Номер терминала\">9000000</terminal_id>"+
"            <transaction_id label=\"Номер чека\">70657841</transaction_id>"+
"            <phone_number label=\"Номер телефона\">3008903</phone_number>"+
"            <summa label=\"Оплачено\">2000 сум</summa>"+
"            <comission label=\"Комиссия агента\">40 сум</comission>"+
"            <purchased_amount label=\"Ваш баланс пополнен на\">1960 сум</purchased_amount>"+
"            <agent_cheque_message label=\"\">Test message from agent</agent_cheque_message>"+
"        </receipt>"+
"    </transaction>"+
"</response>");
        transformer.transform(paynetRequest);
    }
}
