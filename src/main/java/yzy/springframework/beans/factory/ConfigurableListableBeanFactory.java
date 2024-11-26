package yzy.springframework.beans.factory;


import yzy.springframework.beans.BeansException;
import yzy.springframework.beans.factory.config.AutowireCapableBeanFactory;
import yzy.springframework.beans.factory.config.BeanDefinition;
import yzy.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * Configuration interface to be implemented by most listable bean factories.
 * In addition to {@link ConfigurableBeanFactory}, it provides facilities to
 * analyze and modify bean definitions, and to pre-instantiate singletons.
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

}
