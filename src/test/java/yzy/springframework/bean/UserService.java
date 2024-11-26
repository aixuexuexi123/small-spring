package yzy.springframework.bean;
/*
 *@author yzy
 *@Date 2024/10/8
 * */

import yzy.springframework.beans.BeansException;
import yzy.springframework.beans.factory.*;
import yzy.springframework.context.ApplicationContext;
import yzy.springframework.context.ApplicationContextAware;

public class UserService implements  BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {
    private String uId;

    private UserDao userDao;

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name is：" + name);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader：" + classLoader);
    }

    public String getName() {
        return uId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setName(String name) {
        this.uId = name;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public String queryUserInfo() {
        return userDao.queryUserName(uId);
    }



}
