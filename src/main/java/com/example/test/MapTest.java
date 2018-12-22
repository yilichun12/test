package com.example.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) throws Exception{
//        String s1 = "Programming";
//        String s2 = new String("Programming");
//        String s3 = "Program";
//        String s4 = "ming";
//        String s5 = "Program" + "ming";
//        String s6=s3+s4;
//        System.out.println(s1 == s2);
//        System.out.println(s1 == s5);
//        System.out.println(s1 == s6);
//        System.out.println(s1==s6.intern()); //true
//        System.out.println(s2 == s2.intern()); //false
//        sst();
//        lista();
//        Integer ss = new Integer(12);
//        int sd = 13;
//        ints(sd);
//        System.out.println(sd);

        Map<String,String> map = new HashMap<>();

        map.put("1","123");
        map.put("2","223");
        map.put("3","323");
        for (String key : map.keySet()){
            System.out.println(map.get(key));
        }

        Iterator<Map.Entry<String,String>> iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String,String> entry = iterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }


        System.out.println("-------------");
        for (Map.Entry<String,String> entry : map.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println("-------------");
        map.forEach((k,v) -> {
            if (k.equals("1")) {
                System.out.println("l来了老弟");
            } else if (k.equals("2")) {
                System.out.println("你好呀");
            } else {
                System.out.println("00000");
            }
        });
    }


    public static void sst(){
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.MONTH)); // 0 - 11 6.
        System.out.println(cal.get(Calendar.DATE));
        System.out.println(cal.get(Calendar.HOUR_OF_DAY));
        System.out.println(cal.get(Calendar.MINUTE));
        System.out.println(cal.get(Calendar.SECOND));
        // Java 8
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt.getYear());
        System.out.println(dt.getMonthValue()); // 1 - 12 14.
        System.out.println(dt.getDayOfMonth());
        System.out.println(dt.getHour());
        System.out.println(dt.getMinute());
        System.out.println(dt.getSecond());
    }

    public static void lista(){
        final List<String> list = new ArrayList<String>();
         List<String> proxyInstance =
                (List<String>) Proxy.newProxyInstance(list.getClass().getClassLoader(),
                list.getClass().getInterfaces(),
                        new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(list, args);
            }
        });
        proxyInstance.add("你好");
        proxyInstance.add("你好2");
        System.out.println(list);

        String str = "1,2,3,4,5";
        String strProxy = (String) Proxy.newProxyInstance(str.getClass().getClassLoader(), str.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return method.invoke(str,args);

            }
        });
        String [] strArr = strProxy.split(",");
        System.out.println(strArr);
    }

    public static void ints(int ints){
       String str1 = "hello";
       String str2 = "he" + new String("llo");
        System.out.println(str1 == str2);
        ints = ints +1;
    }
}
