package com.study.threaduse;

/**
 * main线程下启动两个线程
 */
public class Thread03 {
    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        thread1.start();
        thread2.start();
    }
}
class T1 implements Runnable{

    int count = 0;

    @Override
    public void run() {
        while (true) {
            //每隔1秒就发一次"hello world!",10次以后退出
            System.out.println("hello world!.." + (++count) + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 10) {
                break;
            }
        }
    }
}
class T2 implements Runnable{

    int count = 0;

    @Override
    public void run() {
        while (true) {
            //每隔1秒就发一次"hi!",5次以后退出
            System.out.println("hi!.." + (++count) + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5) {
                break;
            }
        }
    }
}