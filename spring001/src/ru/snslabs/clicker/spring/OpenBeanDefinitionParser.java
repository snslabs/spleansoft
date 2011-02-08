package ru.snslabs.clicker.spring;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import ru.snslabs.clicker.script.ops.html.OpenPage;

public class OpenBeanDefinitionParser extends AbstractScriptingBeanDefinitionParser {

    protected Class getBeanClass(Element element) {
        return OpenPage.class;
    }

    protected void doCustomParse(Element element, BeanDefinitionBuilder beanDefinitionBuilder, ParserContext parserContext) {
        beanDefinitionBuilder.addPropertyValue("url", element.getAttribute("url"));
    }

}