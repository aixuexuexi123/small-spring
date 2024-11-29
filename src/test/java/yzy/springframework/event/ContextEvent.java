package yzy.springframework.event;

import yzy.springframework.context.ApplicationEvent;
import yzy.springframework.context.event.ApplicationContextEvent;

public class ContextEvent extends ApplicationContextEvent {
    private long id;
    private String message;

    public ContextEvent(Object source,long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
