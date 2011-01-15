package ru.snslabs.clicker.script.ext.impl;

import com.gargoylesoftware.htmlunit.WebClient;
import ru.snslabs.clicker.script.ext.CaptchaResolver;
import ru.snslabs.clicker.script.ext.impl.CaptchaResolverForm;
import ru.snslabs.clicker.script.ScriptContext;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResolveCaptchaInDialog extends CaptchaResolver {

    public Object resolveCaptcha(ScriptContext scriptContext) {
        return callResolutionDialog(scriptContext);
    }

    private Object callResolutionDialog(ScriptContext scriptContext) {
        JFrame dialog = new JFrame();
        dialog.setSize(300,300);
        CaptchaResolverForm crf = new CaptchaResolverForm(dialog);
        dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dialog.setContentPane(crf.getMainPanel());
        System.out.println("Calling dialog to resolve captcha");
        dialog.show(true);
        try {

            BufferedImage img = ImageIO.read(webClient.getPage(
                    resolveToString(srcUrl,scriptContext)
            ).getWebResponse().getContentAsStream());
            crf.setImg(img);
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        while(dialog.isVisible()){
            try {
                System.out.println("Waiting for user input...");
                Thread.sleep(5000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Captcha resolved to : " + crf.getCaptchaValue());
        String result = crf.getCaptchaValue();
        dialog.dispose();
        return result;
    }

}
