package yzy.springframework.event;

import yzy.springframework.context.ApplicationListener;
import yzy.springframework.context.event.ContextRefreshedEvent;

public class ContexRefreshListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新");
    }
}
