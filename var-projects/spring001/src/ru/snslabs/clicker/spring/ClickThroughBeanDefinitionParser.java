package ru.snslabs.clicker.spring;

import org.w3c.dom.Element;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import ru.snslabs.clicker.script.ops.html.Print;
import ru.snslabs.clicker.script.ops.html.ClickThrough;

public class ClickThroughBeanDefinitionParser extends AbstractScriptingBeanDefinitionParser {

    protected Class getBeanClass(Element element) {
        return ClickThrough.class;
    }

    protected void doCustomParse(Element element, BeanDefinitionBuilder beanDefinitionBuilder, ParserContext parserContext) {
        beanDefinitionBuilder.addPropertyValue("regexp", element.getAttribute("regexp"));
    }

}
