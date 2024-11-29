package yzy.springframework.event;

import yzy.springframework.context.ApplicationListener;
import yzy.springframework.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭"+this.getClass().getName());
    }
}
