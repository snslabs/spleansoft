package ru.snslabs.clicker.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class ScriptingNamespaceHandler extends NamespaceHandlerSupport {
    /**
     * registers bean definition parsers for elements that are available in custom schema
     */
    public void init() {
        registerBeanDefinitionParser("script", new ScriptBeanDefinitionParser());
        registerBeanDefinitionParser("open", new OpenBeanDefinitionParser());
        registerBeanDefinitionParser("print", new PrintBeanDefinitionParser());
        registerBeanDefinitionParser("click-through", new ClickThroughBeanDefinitionParser());
        registerBeanDefinitionParser("type-into", new TypeIntoBeanDefinitionParser());
        registerBeanDefinitionParser("click-on", new ClickOnBeanDefinitionParser());

    }
}
