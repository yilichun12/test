package com.example.test;

import java.util.concurrent.TimeUnit;

public class TestThreadDemo {

    public static void main(String[] args) {
        Thread s = new Thread(()->{
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"newThreadStatus");
        s.interrupt();
    }
}
