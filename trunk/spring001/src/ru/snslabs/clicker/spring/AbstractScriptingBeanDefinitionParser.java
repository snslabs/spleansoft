package ru.snslabs.clicker.spring;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * От этого класса необходимо наследовать все BeanDefinitionParser для тэгов в scripting
 */
abstract public class AbstractScriptingBeanDefinitionParser extends AbstractBeanDefinitionParser {
    /**
     * возвращает имя класса bean
     * @param element
     * @return
     */
    abstract protected Class getBeanClass(Element element);

    /**
     * Выполняет парсинг специфичный для бина
     * @param element
     * @param beanDefinitionBuilder
     * @param parserContext
     */
    abstract protected void doCustomParse(Element element, BeanDefinitionBuilder beanDefinitionBuilder, ParserContext parserContext);

    protected void doParse(Element element, BeanDefinitionBuilder beanDefinitionBuilder, ParserContext parserContext) {

        doCustomParse(element, beanDefinitionBuilder, parserContext);

        // parsing internal property elements
        BeanDefinitionParserDelegate delegate = new BeanDefinitionParserDelegate(parserContext.getReaderContext());
        delegate.parsePropertyElements(element, beanDefinitionBuilder.getBeanDefinition());

    }


    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(getBeanClass(element));
        doParse(element, definitionBuilder, parserContext);
        return definitionBuilder.getBeanDefinition();
    }


}
