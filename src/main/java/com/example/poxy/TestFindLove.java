package com.example.poxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class TestFindLove {

    public static void main(String[] args) {

//        Object o = new XiaoEr().findLove();
        try {
            Person person = (Person)new MeiPo().getInstance(new XiaoEr());
            Object o = person.findLove();
//            System.out.println(o.toString());

            byte[] data = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Person.class});

            FileOutputStream fileOutputStream = new FileOutputStream("$Proxy0.class");

            fileOutputStream.write(data);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
