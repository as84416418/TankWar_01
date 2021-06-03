package com.study.method;

public class ThreadMethod02 {
    public static void main(String[] args) throws InterruptedException {

        T2 t2 = new T2();
        t2.start();

        for (int i = 1; i <= 20; i++) {
            Thread.sleep(1000);
            System.out.println("一蜉一(主线程) 吃了 " + i + " 串烤肉");

            if (i == 5) {
                System.out.println("一蜉一(主线程) 让 刘子浪(子线程) 先吃");
                //join() 线程插队
//                t2.join();//这里相当于让 t2 线程先执行完毕，再继续执行主线程
                Thread.yield();
                System.out.println("刘子浪(子线程) 吃完了");
            }
        }
    }
}

class T2 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("刘子浪(子线程) 吃了 " + i + " 串烤肉");
        }
    }
}
