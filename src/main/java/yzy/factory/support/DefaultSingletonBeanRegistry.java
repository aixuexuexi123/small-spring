package yzy.factory.support;
/*
 *@author yzy
 *@Date 2024/10/8
 * */

import yzy.factory.config.BeanDefinition;
import yzy.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.concurrent.ConcurrentMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /*map  放 拿
    */

 private final HashMap<String, Object> singletonMap= new HashMap<>();
    @Override
    public Object getSingleton(String beanName) {
        return singletonMap.get(beanName);
    }

    public void addSingleton(String beanName,Object singleton) {
       singletonMap.put(beanName,singleton);
    }
}
