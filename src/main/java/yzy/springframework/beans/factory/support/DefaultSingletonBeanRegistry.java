package yzy.springframework.beans.factory.support;
/*
 *@author yzy
 *@Date 2024/10/8
 * */

import yzy.springframework.beans.BeansException;
import yzy.springframework.beans.factory.DisposableBean;
import yzy.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected static final Object NULL_OBJECT = new Object();

    /*map  放 拿
    */

    private final HashMap<String, Object> singletonMap= new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();
    @Override
    public Object getSingleton(String beanName) {
        return singletonMap.get(beanName);
    }


    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    @Override
    public void destroySingletons() {
        Set<String> keySet = disposableBeans.keySet();
        for (String key : keySet) {
            DisposableBean disposableBean = disposableBeans.remove(key);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + key + "' threw an exception", e);
            }
        }


    }

    public void addSingleton(String beanName,Object singleton) {
       singletonMap.put(beanName,singleton);
    }
}
