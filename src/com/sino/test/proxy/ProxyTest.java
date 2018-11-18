package com.sino.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Hover Kan
 * @create 2018-11-15 下午10:00
 */
public class ProxyTest {
    public static void main(String[] args) {
        Target target = new Target00();
            Target proxy = (Target) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("这是之前的");
                    Object result = method.invoke(target, args);
                    System.out.println("这是后来的");
                    return result;
                }
        });

        proxy.show();
    }

}
