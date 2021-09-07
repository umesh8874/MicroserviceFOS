package com.AN1D.an1d.config.Beans;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;

/**
 * @author Umesh.Shukla
 */

@Configuration
@EnableAsync
public class AsynchConfiguration extends AsyncConfigurerSupport{

    @Autowired
    private AsyncExceptionHandler asyncExceptionHandler;
    
    @Bean(name = "asyncExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("AsynchThread-");
        executor.initialize();
        return executor;
    }

    public AsyncExceptionHandler getAsyncUncaughtExceptionHandler(){
        return asyncExceptionHandler;
    }
}