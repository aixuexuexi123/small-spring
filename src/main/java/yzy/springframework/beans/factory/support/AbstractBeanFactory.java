package yzy.springframework.beans.factory.support;
/*
 *@author yzy
 *@Date 2024/10/8
 * 模板工厂
 * */

import yzy.springframework.beans.factory.BeanFactory;
import yzy.springframework.beans.BeansException;
import yzy.springframework.beans.factory.config.BeanDefinition;

public abstract   class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {


    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name,null);
    }
    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }
    public  <T> T doGetBean(final String name, final Object[] args){
        /*先拿单例
         *无 然后拿bean定义 进行生产bean
         * */
        Object bean = getSingleton(name);
        if(bean!=null) return (T)bean;
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition,args);
    }
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}