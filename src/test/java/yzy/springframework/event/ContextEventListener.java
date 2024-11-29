package yzy.springframework.event;


import yzy.springframework.context.ApplicationListener;
import yzy.springframework.context.ApplicationListener;


public class ContextEventListener implements ApplicationListener<ContextEvent> {
    @Override
    public void onApplicationEvent(ContextEvent event) {
        System.out.println(event.getId() + event.getMessage());
    }

}

