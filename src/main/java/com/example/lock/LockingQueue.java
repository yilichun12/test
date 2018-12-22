package com.example.lock;

import java.util.ArrayList;
import java.util.List;

public class LockingQueue {
    //队列底层的存储方式
    List<String> list = new ArrayList<String>();

    //线程同步锁
    private Object lock = new Object();


    boolean flag = false;

    //存储数据
    public void put(){
        synchronized (lock){
            for (int i = 1; i < 10 ; i ++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add("lichunyi" + i);
                System.out.println("添加到了" + Thread.currentThread().getName() + "里面，第 " + i + "个元素");
                if (list.size() == 6) {
                    lock.notify();//发出通知，但不释放锁
                    System.out.println("   线程" + Thread.currentThread().getName() + "发出通知" );
                }
            }
            //需要添加完成后需要通常其他线程来去数据
        }

    }
    //获取数据
    public Object get(){
        synchronized (lock){
            //wait使得当前线程 进入等待状态，并且释放锁
            System.out.println("线程" + Thread.currentThread().getName() + "进入等待状态");
            try {
                lock.wait();//等待，一旦调用wait方法，就会释放锁
                System.out.println("线程" + Thread.currentThread().getName()  + "重新唤醒");
                for (String s: list) {
                    System.out.println("取出元素微：" + s);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static void main(String[] args) {
        final LockingQueue lockingQueue = new LockingQueue();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lockingQueue.get();
            }
        },"getList").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                lockingQueue.put();
            }
        },"putList").start();




    }

}
