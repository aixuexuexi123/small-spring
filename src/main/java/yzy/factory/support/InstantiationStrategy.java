package yzy.factory.support;
/*
 *@author yzy
 *@Date 2024/10/8
 * */

import yzy.BeansException;
import yzy.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
