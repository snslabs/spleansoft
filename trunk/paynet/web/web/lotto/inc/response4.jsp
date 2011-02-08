<%@ page pageEncoding="UTF-8" contentType="text/xml; UTF-8" %><%
    String outData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<response>\n" +
"     <transaction>\n" +
"          <status>0</status>\n" +
"          <status_text>Платеж проведен успешно</status_text>\n" +
"          <receipt>\n" +
"               <provider_name label=\"Оператор\">Platinum Connect</provider_name>\n" +
"               <service_name label=\"Услуга\">Platinum-3 000 сум</service_name>\n" +
"               <time label=\"Время\">25.04.2008      12:21:28</time>\n" +
"               <terminal_id label=\"Номер терминала\">9000004</terminal_id>\n" +
"               <transaction_id label=\"Номер чека\">105541714</transaction_id>\n" +
"               <summa label=\"Сумма\">3000 сум</summa>\n" +
"               <activation_code label=\"ПИН Код\">9740 6995 109 052</activation_code>\n" +
"               <activation_serial label=\"Серийный\">2274831</activation_serial>\n" +
"               <instruction_text label=\"\">Доступ на международную телефон-\n" +
"ную связь по телефону в г.Ташкенте: \n" +
"238-5500, 238-5700 и 212-6560\n" +
"в регионах 8-805-140-0000\n" +
"Доступ к Интернет по телефону \n" +
"в Ташкенте\n" +
"238-5505, 238-5510, 238-5705 и 212-6563\n" +
"в регионах 8-805-140-0003\n" +
"Служба поддержки: 238-5999 \n" +
"(круглосуточно)</instruction_text>\n" +
"               <loyalty_id label=\"Paynet ID\">10640993667</loyalty_id>\n" +
"          </receipt>\n" +
"     </transaction>\n" +
"</response>";
        %><%= outData %>