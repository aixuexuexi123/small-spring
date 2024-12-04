package yzy.springframework.context;


import yzy.springframework.beans.BeansException;

import java.io.IOException;

/**
 * SPI interface to be implemented by most if not all application contexts.
 * Provides facilities to configure an application context in addition
 * to the application context client methods in the
 * {@link yzy.springframework.context.ApplicationContext} interface.
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException, IOException;

    void registerShutdownHook();

    void close();

}
