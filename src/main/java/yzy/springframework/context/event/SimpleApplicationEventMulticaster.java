package yzy.springframework.context.event;

import yzy.springframework.beans.factory.BeanFactory;
import yzy.springframework.context.ApplicationEvent;
import yzy.springframework.context.ApplicationListener;

public class SimpleApplicationEventMulticaster  extends AbstractApplicationEventMulticaster{
    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }


}
