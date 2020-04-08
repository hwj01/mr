package com.client.client;

public class User {
    public static void main(String[] args) {
        ThreadLocal<String> objectThreadLocal = new ThreadLocal<>();
        new Thread(()->{
            objectThreadLocal.set("111");
            System.out.println(objectThreadLocal.get());
        }).start();
        new Thread(()->{
            objectThreadLocal.set("aaa");
            System.out.println(objectThreadLocal.get());
        }).start();
    }
}
