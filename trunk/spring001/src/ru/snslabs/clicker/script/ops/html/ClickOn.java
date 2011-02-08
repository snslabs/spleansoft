package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ops.ScriptFailure;

import java.io.IOException;

/**
 * Устанавливает фокус на найденый элемент
 */
public class ClickOn extends AbstractElementOperationEx {

    private int x;
    private int y;

    protected Object execute(HtmlElement htmlEl, ScriptContext scriptContext) {
        if(htmlEl == null){
            System.out.println("element cannot be clicked : " + htmlEl);
        }
        if (htmlEl != null) {
            try {
                long start = System.currentTimeMillis();
                System.out.println("Processing click ....  ");
                Page page = htmlEl.click();
                System.out.println("... processed in " + (System.currentTimeMillis() - start) + " ms");
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}