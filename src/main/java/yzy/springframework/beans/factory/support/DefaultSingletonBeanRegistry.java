package yzy.springframework.beans.factory.support;
/*
 *@author yzy
 *@Date 2024/10/8
 * */


import org.springframework.beans.factory.ObjectFactory;
import yzy.springframework.beans.BeansException;
import yzy.springframework.beans.factory.DisposableBean;
import yzy.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * Internal marker for a null singleton object:
     * used as marker value for concurrent Maps (which don't support null values).
     */
    protected static final Object NULL_OBJECT = new Object();

    // 一级缓存，普通对象
    /**
     * Cache of singleton objects: bean name --> bean instance
     */
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    // 二级缓存，提前暴漏对象，没有完全实例化的对象
    /**
     * Cache of early singleton objects: bean name --> bean instance
     */
    protected final Map<String, Object> earlySingletonObjects = new HashMap<String, Object>();

    // 三级缓存，存放代理对象
    /**
     * Cache of singleton factories: bean name --> ObjectFactory
     */
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<String, ObjectFactory<?>>();

    private final Map<String, DisposableBean> disposableBeans = new LinkedHashMap<>();

public Object getSingleton(String beanName) {
    // 尝试从一级缓存 singletonObjects 中获取对象，一级缓存存储的是普通对象
    Object singletonObject = singletonObjects.get(beanName);
    if (null == singletonObject) {
        // 如果一级缓存中没有找到，尝试从二级缓存 earlySingletonObjects 中获取对象
        // 二级缓存存储的是提前暴漏的对象，可能是没有完全实例化的对象
        singletonObject = earlySingletonObjects.get(beanName);
        // 判断二级缓存中是否有对象，这个对象可能是代理对象
        if (null == singletonObject) {
            // 如果二级缓存中也没有找到，尝试从三级缓存 singletonFactories 中获取对象
            ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
            if (singletonFactory!= null) {
                // 从三级缓存中的 ObjectFactory 获取对象
                singletonObject = singletonFactory.getObject();
                // 把三级缓存中的代理对象中的真实对象获取出来，放入二级缓存中
                earlySingletonObjects.put(beanName, singletonObject);
                singletonFactories.remove(beanName);
            }
        }
    }
    return singletonObject;
}


    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory){
        if (!this.singletonObjects.containsKey(beanName)) {
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonObjects.remove(beanName);
        }
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
