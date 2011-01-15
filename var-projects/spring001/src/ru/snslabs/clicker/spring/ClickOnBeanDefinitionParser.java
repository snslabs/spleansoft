package ru.snslabs.clicker.spring;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import ru.snslabs.clicker.script.ops.html.ClickOn;

public class ClickOnBeanDefinitionParser extends AbstractElementOperationBeanDefinitionParser {

    protected Class getBeanClass(Element element) {
        return ClickOn.class;
    }

    protected void doCustomParseForElements(Element element, BeanDefinitionBuilder beanDefinitionBuilder, ParserContext parserContext) {
        beanDefinitionBuilder.addPropertyValue("x", element.getAttribute("x"));
        beanDefinitionBuilder.addPropertyValue("y", element.getAttribute("y"));
    }

}