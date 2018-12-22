package com.example.singleton;

import io.netty.handler.codec.base64.Base64Encoder;

public class Singleton {

    private static class LazyHolder{
        private static final Singleton INSTANCE = new Singleton();
    }

    private Singleton(){

    }
    public static final Singleton getInstance(){
        return LazyHolder.INSTANCE;
    }


    public static void main(String[] args) {
    }
}
