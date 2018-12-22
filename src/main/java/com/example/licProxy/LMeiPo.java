package com.example.licProxy;

import com.example.poxy.Person;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LMeiPo implements LInvocationHandler{

    private Person taeget;

    //获取被代理人的信息
    public Object getInstance(Person target) throws Exception{

        this.taeget = target;
        Class clazz = target.getClass();
        Object object = LProxy.newProxyInstance(new LClassLoader(),clazz.getInterfaces(),this);
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
