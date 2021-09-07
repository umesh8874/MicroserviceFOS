package com.AN1D.an1d.config.Beans;
import org.slf4j.*;
import java.util.Arrays;
import java.lang.reflect.Method;
import org.springframework.stereotype.Component;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

/**
 * @author Umesh.Shukla
 */
@Component
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler{

    private final static Logger LOG = LoggerFactory.getLogger(AsyncExceptionHandler.class);

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... arg) {
        LOG.error("Method name : "+method.getName()+" : " +Arrays.toString(arg)+ " Error message : "+ex.getMessage());
    }
}
