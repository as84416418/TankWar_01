package com.study.method;

/**
 * 线程插队的练习：
 * 1.主线程每隔1s，输出 hi，一共10次
 * 2.当输出到 hi 5 时，启动一个子线程(要求实现Runnable)，每隔1s输出 hello，等该线程输出10次 hello后，退出
 * 3.主线程继续输出 hi，直到主线程退出
 */
public class ThreadMethodExercise {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            Thread.sleep(1000);
            System.out.println("hi! " + i);
            if (i == 5) {
                Thread t1 = new Thread(new T3());
                t1.start();
                t1.join();
            }
        }
        System.out.println("主线程结束....");
    }
}

class T3 implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello! " + i);
        }
        System.out.println("子线程结束....");
    }
}
