package yzy.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import yzy.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import yzy.springframework.beans.factory.config.BeanDefinition;
import yzy.springframework.beans.factory.support.BeanDefinitionRegistry;
import yzy.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Set;

public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider{

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }



    public void doScan(String...basePackages){

        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidates) {
                // 解析 Bean 的作用域 singleton、prototype
                String beanScope = resolveBeanScope(beanDefinition);
                if (StrUtil.isNotEmpty(beanScope)){
                    beanDefinition.setScope(beanScope);
                }
                registry.registerBeanDefinition(determineBeanName(beanDefinition),beanDefinition);

            }
        }
        // 注册处理注解的 BeanPostProcessor（@Autowired、@Value）
        registry.registerBeanDefinition("yzy.springframework.context.annotation.internalAutowiredAnnotationProcessor",new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }

    //    判断单例 原型
    private String resolveBeanScope(BeanDefinition beanDefinition){
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (null != scope) return scope.value();
        return StrUtil.EMPTY;
    }

//    bean名字
    private String determineBeanName(BeanDefinition beanDefinition){
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)) {
           value= StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return  value;
    }
}
