package com.study.threaduse;

/**
 * 继承Thread类 ，熟悉线程运行
 */
public class Thread01 {
    public static void main(String[] args) throws InterruptedException {

        //创建Cat对象，可以当作线程使用
        Cat cat = new Cat();
        /*
            如果直接调用 cat.run()，则相当于直接在主线程(main)内执行cat对象内的run方法，
            并没有启动一个新的线程来执行这个run()方法
         */
        cat.start();//启动线程 -> 最终会执行cat的run方法
        //说明:当main线程启动一个子线程 Thread-0， 主线程不会阻塞，而是继续执行
        //这时，主线程和子线程是交替执行的
        System.out.println("主线程继续执行 ，主线程名称: " + Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程 i=" + i);
            Thread.sleep(1000);
        }
    }
}

/**
 * 1.当一个类继承了 Thread 类，该类就可以当作线程使用
 * 2.我们会重写run方法，写上自己的业务代码
 * 3.run Thread 类 实现了 Runnable 接口的 run 方法
 *
 * @Override public void run() {
 * if (target != null) {
 * target.run();
 * }
 * }
 */
class Cat extends Thread {

    int times;

    @Override
    public void run() {//重写run方法， 写上自己的业务逻辑
        while (true) {
            //该线程每隔1秒，在控制台输出 ”喵喵，我是一直小猫咪“
            System.out.println("喵喵，我是一直小猫咪 " + (++times));
            //让该线程休眠1秒
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (times == 8) {
                break;//当times 达到 8 次，结束循环，同时线程退出
            }
        }

    }
}
