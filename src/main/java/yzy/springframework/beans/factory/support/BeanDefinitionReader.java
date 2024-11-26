package yzy.springframework.beans.factory.support;

import yzy.springframework.beans.BeansException;
import yzy.springframework.core.io.Resource;
import yzy.springframework.core.io.ResourceLoader;

/**
 * Simple interface for bean definition readers.
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;

}
