package yzy.springframework.beans.factory.config;

/*
*单例注册
* */
public interface SingletonBeanRegistry {

   //   拿单例
   Object getSingleton(String beanName);
}
