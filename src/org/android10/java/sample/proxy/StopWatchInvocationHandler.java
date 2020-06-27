/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package org.android10.java.sample.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Class implementing {@link java.lang.reflect.InvocationHandler} to intercept method calls for
 * adding elapsed execution time of a method.
 */
public class StopWatchInvocationHandler implements InvocationHandler {

    private final Object objectProxied;

    public StopWatchInvocationHandler(Object objectProxied) {
        this.objectProxied = objectProxied;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        Object result = method.invoke(objectProxied, args);
        long endTime = System.nanoTime();

        String message = method.getName() +
                " took " +
                TimeUnit.NANOSECONDS.toMillis(endTime - startTime) +
                " millis";
        System.out.println(message);

        return result;
    }
}
