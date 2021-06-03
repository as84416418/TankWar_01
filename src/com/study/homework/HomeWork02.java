package com.study.homework;

/**
 * 作业：
 * 1.有2个用户分别从同一个卡上取钱(总额:10000)
 * 2.每次都取1000,当余额不足时,就不能取款了
 * 3.不能出现超取现象 -> 线程同步问题
 */
public class HomeWork02 {
    public static void main(String[] args) {
        T t = new T();
        new Thread(t).start();
        new Thread(t).start();
    }
}

class T implements Runnable {
    private int count = 10000;
    private boolean loop = true;

    public void getMoney() {
        synchronized (this) {
            if (count <= 0) {
                System.out.println("没有钱可以取了....");
                loop = false;
                return;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count -= 1000;
            System.out.println(Thread.currentThread().getName() + " 取走1000块,还剩: " + count);


        }
    }

    @Override
    public void run() {
        while (loop) {
            getMoney();
        }
    }
}