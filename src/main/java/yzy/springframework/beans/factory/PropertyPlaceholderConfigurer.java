package yzy.springframework.beans.factory;

import yzy.springframework.beans.BeansException;
import yzy.springframework.beans.PropertyValue;
import yzy.springframework.beans.PropertyValues;
import yzy.springframework.beans.factory.config.BeanDefinition;
import yzy.springframework.beans.factory.config.BeanFactoryPostProcessor;
import yzy.springframework.core.io.DefaultResourceLoader;
import yzy.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    /**
     * Default placeholder prefix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * Default placeholder suffix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    public void setLocation(String location) {
        this.location = location;
    }



    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException, IOException {
        // 加载属性文件
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        Resource resource = defaultResourceLoader.getResource(location);
        Properties properties = new Properties();
        properties.load(resource.getInputStream());

        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                Object value = propertyValue.getValue();
                if (!(value instanceof String)) continue;
                String strVal = (String) value;
                StringBuilder buffer = new StringBuilder(strVal);
                int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
                int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
                if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
                    String propKey = strVal.substring(startIdx + 2, stopIdx);
                    String propVal = properties.getProperty(propKey);
                    buffer.replace(startIdx, stopIdx + 1, propVal);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), buffer.toString()));
                }
            }
        }
    }
}
