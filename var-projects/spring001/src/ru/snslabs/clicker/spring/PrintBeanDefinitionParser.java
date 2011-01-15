package ru.snslabs.clicker.spring;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import ru.snslabs.clicker.script.ops.html.Print;

public class PrintBeanDefinitionParser extends AbstractScriptingBeanDefinitionParser {

    protected Class getBeanClass(Element element) {
        return Print.class;
    }

    protected void doCustomParse(Element element, BeanDefinitionBuilder beanDefinitionBuilder, ParserContext parserContext) {
        beanDefinitionBuilder.addPropertyValue("filename", "".equals(element.getAttribute("filename"))?null:element.getAttribute("filename"));
    }

}