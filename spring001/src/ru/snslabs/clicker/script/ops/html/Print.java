package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ops.HtmlScriptOperation;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Print extends HtmlScriptOperation {
    private String filename;
    private String mode;

    public static final String MODE_DOM = "dom";
    public static final String MODE_HTML = "html";

    public Object execute(ScriptContext scriptContext) {
        Page currentPage = getCurrentPage(scriptContext);
        if(MODE_DOM.equalsIgnoreCase(mode) ){
            printPageDom((HtmlPage) currentPage);
        }
        else{
            printPage((HtmlPage) currentPage, "");
        }
        return false;
    }

    private void printPageDom(HtmlPage htmlPage) {
        /*
        for( Object o : htmlPage.getTabbableElements()){
            System.out.println("Tabbed element: " + o.getClass() + " : " + o.toString());
        }
        */
    }

    private void printPage(HtmlPage currentPage, String prefix) {
        int index = 0;
        if(currentPage.getFrames().size() > 0){
            for(Object o : currentPage.getFrames()){
                if(o instanceof FrameWindow){
                    FrameWindow frameWindow = (FrameWindow) o;
                    printPage((HtmlPage) frameWindow.getEnclosedPage(), prefix+"-"+(index++));
                }
            }
        }
        else{
            print(currentPage, prefix);
        }

    }

    private void print(Object o, String prefix){
        String str;
        if(filename == null){
            if(o instanceof HtmlPage){
                str = ((HtmlPage) o).asXml();
            }
            else{
                str = o.toString();
            }
            System.out.println(str);
        }
        else{
            try {
                final String fileName = filename + prefix + ".html";
                File file = new File(fileName);
                if(file.exists()){
                    print(o, prefix+"x");
                    return;
                }
                if(o instanceof HtmlPage){
                    str = ((HtmlPage) o).asXml();
                }
                else{
                    str = o.toString();
                }
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.write(str);
                fileWriter.flush();
                fileWriter.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
