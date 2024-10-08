package yzy;
/*
 *@author yzy
 *@Date 2024/10/8
 * */

import org.junit.Test;
import yzy.bean.UserService;
import yzy.factory.config.BeanDefinition;
import yzy.factory.support.DefaultListableBeanFactory;

public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 3. 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 4.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService", "yue");
        userService.queryUserInfo();
    }
}
