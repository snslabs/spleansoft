package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ops.ScriptFailure;

public class SelectValue extends AbstractElementOperation {

    private String value;
    private int index = -1;

    Object execute(HtmlElement htmlEl, ScriptContext scriptContext) {
        HtmlSelect htmlSelect = (HtmlSelect) htmlEl;
        htmlSelect.focus();

        HtmlOption optionToSelect = null;
        if(value != null){
            optionToSelect = htmlSelect.getOptionByValue(value);
        }
        else if(index != -1){
            optionToSelect = (HtmlOption) htmlSelect.getOptions().get(index);
        }
        else{
            throw new ScriptFailure("Please specify value or index to select value from combobox " + htmlSelect);
        }
        optionToSelect.setSelected(true);
        htmlSelect.blur();
        return null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}