package yzy.springframework.context;


import yzy.springframework.beans.factory.HierarchicalBeanFactory;
import yzy.springframework.beans.factory.ListableBeanFactory;

/**
 * Central interface to provide configuration for an application.
 * This is read-only while the application is running, but may be
 * reloaded if the implementation supports this.
 *
 * 应用上下文
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory,ApplicationEventPublisher {
}
