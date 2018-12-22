package com.example.poxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//代理
public class MeiPo implements InvocationHandler {

    private Person taeget;

    //获取被代理人的信息
    public Object getInstance(Person target) throws Exception{

        this.taeget = target;
        Class clazz = target.getClass();
        Object object = Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
        return object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是媒婆，你是" );
        System.out.println("你是一名生");
        System.out.println("我是媒婆现在帮你寻找。。。");
        this.taeget.findLove();
        return null;
    }
}
