package ru.snslabs.clicker.spring;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import ru.snslabs.clicker.script.ops.html.TypeValueInto;

public class TypeIntoBeanDefinitionParser extends AbstractElementOperationBeanDefinitionParser {

    protected Class getBeanClass(Element element) {
        return TypeValueInto.class;
    }

    protected void doCustomParseForElements(Element element, BeanDefinitionBuilder beanDefinitionBuilder, ParserContext parserContext) {
        beanDefinitionBuilder.addPropertyValue("value", element.getAttribute("value"));
    }

}