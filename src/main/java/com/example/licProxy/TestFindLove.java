package com.example.licProxy;

import com.example.poxy.MeiPo;
import com.example.poxy.Person;
import com.example.poxy.XiaoEr;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

public class TestFindLove {

    public static void main(String[] args) {

//        Object o = new XiaoEr().findLove();
        try {
            Person person = (Person)new LMeiPo().getInstance(new XiaoEr());
            Object o = person.findLove();
            System.out.println(o.toString());

////            byte[] data = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Person.class});
//
//            FileOutputStream fileOutputStream = new FileOutputStream("$Proxy0.class");
//
//            fileOutputStream.write(data);
//            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
