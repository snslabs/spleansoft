package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.html.FocusableElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import ru.snslabs.clicker.script.ScriptContext;

public class FillTextarea extends AbstractElementOperationEx {

    private Object value = "<a href=\"http://www.AWSurveys.com/HomeMain.cfm?RefID=snslabs\"><img src=\"http://www.AWSurveys.com/Pictures/AWS_ad3_150by150.jpg\" width=\"150\" height=\"150\"></a>\n" +
            "\n" +
            "������! ���������� 27$ �� 10 ���! �� ������? �������! ������ ����� 10 ���!\n" +
            "��������� �� ������ http://www.AWSurveys.com/HomeMain.cfm?RefID=snslabs\n" +
            "����������:\n" +
            "1. �������� Create A Free Account\n" +
            "UserName - ��� ������������\n" +
            "Password - ������ 7-15 ������\n" +
            "First Name - ���\n" +
            "Last Name - �������\n" +
            "Email Address - �������� ����\n" +
            "����� ������ �������-�������, �������� ������ Create A Free Account\n" +
            "�� ������������������!\n" +
            "2. ����� ����� �������, ������� ����� ���:\n" +
            "The Following Surveys are Available:\n" +
            "Welcome Survey -- A $6. 00 Website Evaluation is Available.\n" +
            "A $4. 00 Website Evaluation is Available.\n" +
            "A $4. 00 Website Evaluation is Available.\n" +
            "A $4. 00 Website Evaluation is Available.\n" +
            "A $4. 00 Website Evaluation is Available.\n" +
            "A $4. 00 Website Evaluation is Available.\n" +
            "A February Bonus Website Evaluation is Available.\n" +
            "3. ������� �� ����� �� ������ �� ���� ��������, �������� �� ��������� �������� (��� ��� �������� ��������, ��� ��� �������� 6$) � ��� ������� �� ������� \"Start Survey Now\"\n" +
            "4. ��� 2 ������ � 2 ����, � ��� ���� �������� ����� � ����� �� ���� �����.\n" +
            "��� �� �����, ���� ������-������:It is an excellent site, I shall advise its all!\n" +
            "5. ������� �� ������ ����� \"Click to submit . . . \" ������������� �� ��������, �� ������� ��� ��������, ��� �� ��� ������ ��������� 6$. ������� �� ������ \"Click Here to go Home and. . . \"\n" +
            "6. � ��� ����� �� ���������.\n" +
            "7. ��� ������ ����� �������������� ������� \"Redeem Money\"\n" +
            "���� ���� 27$ �� 10 ��� ��� �������!\n" +
            "\n" +
            "�������� �����������! ���� ��� ����� ���������� ��� �������, ����� �������� URL ������ ���������� ��� �� �������� ���� - � � ������� ��� �� ��������";

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