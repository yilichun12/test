package com.example.test;

import javax.sound.midi.SoundbankResource;
import java.lang.reflect.Field;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class test1 {

    public static void main(String [] arge) throws UnknownHostException {
        /*InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress.getAddress());
        System.out.println(inetAddress.getHostName());
        System.out.println(inetAddress.getHostAddress());

        InetAddress inetAddress2=InetAddress.getByName("120.24.154.123");

        System.out.println(inetAddress2.getAddress());
        System.out.println(inetAddress2.getHostName());
        System.out.println(inetAddress2.getHostAddress());
        System.out.println(inetAddress2);*/

        Field []fields =  test1.class.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
        }
        int s = 1;

        Integer i = new Integer(1);

        System.out.println(s == i);



        System.out.println(i.equals(s));

        String a = "abc";

        String b = new String("abc");

        String c = "a" + "bc";

        String d = "a" + new String("bc");

        System.out.println(a.equals(b));

        System.out.println(a == b);

        System.out.println(a == c);

        System.out.println(a.equals(c));

        System.out.println(a.equals(d));

        System.out.println(a == d);


        Observer observer = new Observer() {
            @Override
            public void update(Observable o, Object arg) {

            }
        };

        Vector<String> vector = new Vector<>();

        vector.add("q1");
        vector.add("q2");
        vector.add("q3");

        Iterator iterator = vector.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        for (String str : vector) {
            System.out.println(str);
        }

        MyObservable observable = new MyObservable();

        observable.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object value) {
                System.out.println(value);
            }
        });
        observable.setChanged();

        observable.notifyObservers("Hello Word!");



    }

    public static class MyObservable extends Observable{

        @Override
        public synchronized void setChanged() {
            super.setChanged();
        }
    }
}


