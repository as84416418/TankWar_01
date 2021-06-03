package com.study.method;

/**
 * 守护线程：当用户线程执行完毕之后，不论守护线程处于何种状态，都直接结束掉守护线程
 */
public class ThreadMethod03 {
    public static void main(String[] args) throws InterruptedException {
        MyDaemonThread myDaemonThread = new MyDaemonThread();
        //如果我们希望当main线程结束后，子线程自动结束
        //只需要将子线程设为守护线程，然后再启动即可
        myDaemonThread.setDaemon(true);
        myDaemonThread.start();

        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println("苦主在外辛苦工作....");
        }
        System.out.println("苦主下班回家了...");
    }
}

class MyDaemonThread extends Thread {
    @Override
    public void run() {
        for (; ; ) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("女主在和黄毛愉快的交流....");
        }
    }
}
