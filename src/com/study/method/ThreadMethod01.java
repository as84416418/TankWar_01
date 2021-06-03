package com.study.method;

public class ThreadMethod01 {
    public static void main(String[] args) throws InterruptedException {
        //测试相关的方法
        T t = new T();
        t.setName("刘子浪");
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();

        //主线程打印5次语句，然后使用interrupt 方法中断 子线程的休眠
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println("hello! " + i);
        }
        t.interrupt();
        System.out.println(t.getName() + " 的优先级是 " + t.getPriority());
    }
}

class T extends Thread {//自定义的线程类

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 100; i++) {
                //Thread.currentThread().getName() 获取当前的线程名称
                System.out.println(Thread.currentThread().getName() + " 吃烤肉~~~~" + i);
            }
            try {
                System.out.println(Thread.currentThread().getName() + " 休眠中~~~~");
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                //当该线程执行到一个interrupt 方法时，就会catch 一个 一场，可以加入自己的业务代码
                System.out.println(Thread.currentThread().getName() + " 被 interrupt 了");
            }

        }
    }
}
