package yzy.springframework.aop.framework.autoproxy;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import yzy.springframework.aop.*;
import yzy.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import yzy.springframework.aop.framework.ProxyFactory;
import yzy.springframework.beans.BeansException;
import yzy.springframework.beans.factory.BeanFactory;
import yzy.springframework.beans.factory.BeanFactoryAware;
import yzy.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import yzy.springframework.beans.factory.support.DefaultListableBeanFactory;

import java.util.Collection;

public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {


    DefaultListableBeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            this.beanFactory=(DefaultListableBeanFactory)beanFactory;
    }



    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
       if(isInfrastructureClass(beanClass))  return null;

        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) continue;
            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource=null;
        try {
                 targetSource=   new TargetSource(beanClass.getDeclaredConstructor().newInstance());
        }catch (Exception e){
            e.printStackTrace();
        }

            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);

            return new ProxyFactory(advisedSupport).getProxy();
        }
        return null;
    }


    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
