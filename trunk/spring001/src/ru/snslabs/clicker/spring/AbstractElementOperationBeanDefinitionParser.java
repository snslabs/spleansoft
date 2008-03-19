package ru.snslabs.clicker.spring;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import ru.snslabs.clicker.script.ops.html.OpenPage;

abstract public class AbstractElementOperationBeanDefinitionParser extends AbstractScriptingBeanDefinitionParser {

    protected void doCustomParse(Element element, BeanDefinitionBuilder beanDefinitionBuilder, ParserContext parserContext) {
        beanDefinitionBuilder.addPropertyValue("idAttr", element.getAttribute("idAttr"));
        beanDefinitionBuilder.addPropertyValue("nameAttr", element.getAttribute("nameAttr"));
        beanDefinitionBuilder.addPropertyValue("attribute", element.getAttribute("attribute"));
        beanDefinitionBuilder.addPropertyValue("substr", element.getAttribute("substr"));
        doCustomParseForElements(element, beanDefinitionBuilder, parserContext);
    }

    abstract protected void doCustomParseForElements(Element element, BeanDefinitionBuilder beanDefinitionBuilder, ParserContext parserContext);

}