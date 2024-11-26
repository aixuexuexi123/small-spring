package yzy.springframework;
/*
 *@author yzy
 *@Date 2024/10/8
 * */

import cn.hutool.core.io.IoUtil;
import org.junit.Before;
import org.junit.Test;
import yzy.springframework.bean.UserDao;
import yzy.springframework.bean.UserService;
import yzy.springframework.beans.PropertyValue;
import yzy.springframework.beans.PropertyValues;
import yzy.springframework.beans.factory.config.BeanDefinition;
import yzy.springframework.beans.factory.config.BeanReference;
import yzy.springframework.beans.factory.support.DefaultListableBeanFactory;
import yzy.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import yzy.springframework.context.support.ClassPathXmlApplicationContext;
import yzy.springframework.core.io.DefaultResourceLoader;
import yzy.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));
        // 4. 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }


    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }


    @Test
    public void test_xml() {

        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }
}
