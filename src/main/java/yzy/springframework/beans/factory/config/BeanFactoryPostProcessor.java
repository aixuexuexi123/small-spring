package yzy.springframework.beans.factory.config;

import yzy.springframework.beans.BeansException;
import yzy.springframework.beans.factory.ConfigurableListableBeanFactory;

import java.io.IOException;


/**
 * Allows for custom modification of an application context's bean definitions,
 * adapting the bean property values of the context's underlying bean factory.
 *
 * 允许自定义修改 BeanDefinition 属性信息

 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException, IOException;

}
