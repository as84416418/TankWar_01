package com.study.threaduse;

/**
 * 通过实现接口 Runnable 来开发线程
 */
public class Thread02 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        //dog.start();  这里不能调用start()方法
        //创建了Thread对象， 把 dog对象(实现了Runnable接口)，放入 Thread
        Thread thread = new Thread(dog);
        thread.start();
    }
}

//线程代理类, 模拟了一个很简单的 Thread类
class ThreadProxy implements Runnable {
    private Runnable target = null;

    @Override
    public void run() {
        if (target != null) {
            target.run();//动态绑定(运行类型)
        }
    }

    public ThreadProxy(Runnable target) {
        this.target = target;
    }

    public void start() {
        start0();//这个方法是真正实现多线程的方法
    }

    public void start0() {
        run();
    }
}


class Dog implements Runnable {
    int count = 0;

    @Override
    public void run() {
        while (true) {
            System.out.println("旺旺旺！.." + (++count) + Thread.currentThread().getName());
            //休眠一秒
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
