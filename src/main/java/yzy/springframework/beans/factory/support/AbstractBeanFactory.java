package yzy.springframework.beans.factory.support;
/*
 *@author yzy
 *@Date 2024/10/8
 * 模板工厂
 * */

import yzy.springframework.beans.factory.BeanFactory;
import yzy.springframework.beans.BeansException;
import yzy.springframework.beans.factory.FactoryBean;
import yzy.springframework.beans.factory.config.BeanDefinition;
import yzy.springframework.beans.factory.config.BeanPostProcessor;
import yzy.springframework.beans.factory.config.ConfigurableBeanFactory;
import yzy.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

public abstract   class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

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

    private Object getObjectForBeanInstance(Object beanInstance, String beanName){

        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        Object object = getCachedObjectForFactoryBean(beanName);
        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }

        return object;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {

    }
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }

    public ClassLoader getBeanClassLoader() {
        return beanClassLoader;
    }
}
