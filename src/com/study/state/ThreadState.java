package com.study.state;

/**
 * 线程的生命周期：
 * New -> Runnable -> Timed_Waiting -> Waiting -> Blocked -> Terminated
 * NEW
 * 尚未启动的线程处于此状态。
 * Runnable
 * 在Java虚拟机中执行的线程处于此状态。
 * Blocked
 * 被阻塞等待监视器锁定的线程处于此状态。
 * Waiting
 * 正在等待另一个线程执行特定动作的线程处于此状态。
 * Timed_Waiting
 * 正在等待另一个线程执行动作达到指定等待时间的线程处于此状态。
 * Terminated
 * 已退出的线程处于此状态。
 * <p>
 * <p>
 * 用程序简单的查看线程的状态
 */
public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        System.out.println(t.getName() + " 的状态 -> " + t.getState());
        t.start();

        //Terminated 表示线程结束，当子线程没有结束时，就一直监视输出 子线程的线程状态
        while (Thread.State.TERMINATED != t.getState()) {
            System.out.println(t.getName() + " 的状态 -> " + t.getState());
            Thread.sleep(1000);
        }
        System.out.println(t.getName() + " 的状态 -> " + t.getState());
    }
}

class T extends Thread {
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 10; i++) {
                System.out.println("hi! " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            break;
        }
    }
}
