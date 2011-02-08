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
    public Object execute(ScriptContext scriptContext) {
        Page currentPage = getCurrentPage(scriptContext);
        printPage((HtmlPage) currentPage, "");
        return false;
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
}
