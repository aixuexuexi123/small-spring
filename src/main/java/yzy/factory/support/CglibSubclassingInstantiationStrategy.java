package yzy.factory.support;
/*
 *@author yzy
 *@Date 2024/10/8
 * CGLIB
 * */

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import yzy.BeansException;
import yzy.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{


    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        /*Enhancer实例：Enhancer是CGLIB库中的一个核心类，用于动态创建一个类的子类。
        *CGLIB基于继承 所以enhancer.setSuperclass
        *enhancer.create()
        * */
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp () {
        @Override
        public int hashCode() {
            return super.hashCode();
        }
        });
        if(ctor==null) enhancer.create();
        return enhancer.create(ctor.getParameterTypes(),args);
    }
}
