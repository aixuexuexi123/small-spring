package yzy.springframework.beans.factory.config;

import yzy.springframework.beans.PropertyValues;

public class BeanDefinition {

    private Class beanClass;
    private PropertyValues propertyValues;

    private String initMethodName;

    private String destroyMethodName;



    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues=new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }
}
