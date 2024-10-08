package yzy.factory.support;
/*
 *@author yzy
 *@Date 2024/10/8
 * */

import yzy.BeansException;
import yzy.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
       /*
       * getDeclaredConstructor 本身  getConstructor 包括父类
       *
       * */
        Class beanClass = beanDefinition.getBeanClass();
        try {
            if(ctor!=null){
        return  beanClass.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }else {
        return  beanClass.getDeclaredConstructor().newInstance();
            }

        }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e   ){
            throw new BeansException("Failed to instantiate [" + beanClass.getName() + "]", e);
        }

    }
}
