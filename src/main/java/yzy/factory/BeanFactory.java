package yzy.factory;

import yzy.BeansException;

public interface BeanFactory {

    Object getBean(String name) throws BeansException;
}
