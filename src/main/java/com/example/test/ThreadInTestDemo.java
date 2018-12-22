package com.example.test;

import java.util.concurrent.TimeUnit;

public class ThreadInTestDemo {

    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
           while(!Thread.currentThread().isInterrupted()){
               i++;
           }
           System.out.println(i+"qq");
        },"inrt");

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
//        System.out.println(i);

    }
}
