package yzy.factory.support;
/*
 *@author yzy
 *@Date 2024/10/8
 * 模板工厂
 * */

import yzy.BeansException;
import yzy.factory.BeanFactory;
import yzy.factory.config.BeanDefinition;

public abstract   class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {


    @Override
    public Object getBean(String name) throws BeansException {
        /*先拿单例
         *无 然后拿bean定义 进行生产bean
         * */
        Object singleton = getSingleton(name);
        if(singleton!=null) return singleton;
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition);

        return bean;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
