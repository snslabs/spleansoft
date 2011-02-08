package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.ClickableElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ops.ScriptFailure;

import java.io.IOException;

/**
 * ������������� ����� �� �������� �������
 */
public class ShiftClickOn extends ClickOn {
    protected Object execute(HtmlElement htmlEl, ScriptContext scriptContext) {
        if (htmlEl instanceof ClickableElement) {
            try {
                Page page = ((ClickableElement) htmlEl).click(true,false,false);
                setCurrentPage(page, scriptContext);
                return page;
            }
            catch (IOException e) {
                e.printStackTrace();
                throw new ScriptFailure(e);
            }
        }
        else {
            return false;
        }
    }
}