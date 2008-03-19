package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.html.FocusableElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import ru.snslabs.clicker.script.ScriptContext;

public class FillTextarea extends AbstractElementOperationEx {

    private Object value = "<a href=\"http://www.AWSurveys.com/HomeMain.cfm?RefID=snslabs\"><img src=\"http://www.AWSurveys.com/Pictures/AWS_ad3_150by150.jpg\" width=\"150\" height=\"150\"></a>\n" +
            "\n" +
            "Привет! Заработать 27$ за 10 мин! Не веришь? Реально! Займет всего 10 мин!\n" +
            "Переходим по ссылке http://www.AWSurveys.com/HomeMain.cfm?RefID=snslabs\n" +
            "Инсткукция:\n" +
            "1. Нажимаем Create A Free Account\n" +
            "UserName - имя пользователя\n" +
            "Password - пароль 7-15 знаков\n" +
            "First Name - имя\n" +
            "Last Name - Фамилия\n" +
            "Email Address - почтовый ящик\n" +
            "Далее вводим цифорки-буковки, нажимаем кнопку Create A Free Account\n" +
            "Мы зарегистрировались!\n" +
            "2. Далее видим таблицу, имеющую такой вид:\n" +
            "The Following Surveys are Available:\n" +
            "Welcome Survey -- A $6. 00 Website Evaluation is Available.\n" +
            "A $4. 00 Website Evaluation is Available.\n" +
            "A $4. 00 Website Evaluation is Available.\n" +
            "A $4. 00 Website Evaluation is Available.\n" +
            "A $4. 00 Website Evaluation is Available.\n" +
            "A $4. 00 Website Evaluation is Available.\n" +
            "A February Bonus Website Evaluation is Available.\n" +
            "3. Щелкаем по одной из ссылок на этой странице, попадаем на следующую страницу (тут нам радостно сообщают, что нам заплатят 6$) и там щелкаем по надписи \"Start Survey Now\"\n" +
            "4. тут 2 ссылки и 2 поля, в них надо написать отзыв о сайте на англ языке.\n" +
            "Кто не силен, пишу шаблон-пример:It is an excellent site, I shall advise its all!\n" +
            "5. Щелкаем по кнопке внизу \"Click to submit . . . \" Переместились на страницу, на которой нам сообщают, что на наш баланс зачислено 6$. Щелкаем по ссылке \"Click Here to go Home and. . . \"\n" +
            "6. И так далее по ссылочкам.\n" +
            "7. Для вывода денег воспользуйтесь кнопкой \"Redeem Money\"\n" +
            "Один день 27$ за 10 мин без проблем!\n" +
            "\n" +
            "Вниманию модераторов! Если Вас ОЧЕНЬ раздражает моя реклама, прошу прислать URL своего сообщества мне на почтовый ящик - и я исключу его из рассылки";

    protected Object execute(HtmlElement htmlEl, ScriptContext scriptContext) {
        if(htmlEl ==null){
            log.warn("No element found");
        }
        else{
            ((FocusableElement)htmlEl).focus();
            final String string = resolveToString(value, scriptContext);
            HtmlTextArea area = (HtmlTextArea) htmlEl;
            area.setText(substParams(string, scriptContext));
            ((FocusableElement)htmlEl).blur();
        }
        return null;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}