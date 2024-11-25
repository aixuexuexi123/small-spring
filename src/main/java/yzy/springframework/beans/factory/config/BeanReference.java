package yzy.springframework.beans.factory.config;
/*
 *@author yzy
 *@Date 2024/10/8
 * */

public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
