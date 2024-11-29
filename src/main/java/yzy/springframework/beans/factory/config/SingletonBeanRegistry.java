package yzy.springframework.beans.factory.config;

/*
*单例注册
* */
public interface SingletonBeanRegistry {

   //   拿单例
   Object getSingleton(String beanName);

   /**
    * 销毁单例对象
    */
   void destroySingletons();

   void registerSingleton(String beanName, Object singletonObject);
}
