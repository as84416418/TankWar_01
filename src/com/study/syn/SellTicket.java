package com.study.syn;

/**
 * 使用线程模拟三个窗口进行售票
 * 使用synchronized 加锁
 */
public class SellTicket {
    public static void main(String[] args) {


        //超卖现象没有了
        SellTicket02 sellTicket02 = new SellTicket02();
        new Thread(sellTicket02).start();//第一个线程-窗口
        new Thread(sellTicket02).start();//第二个线程-窗口
        new Thread(sellTicket02).start();//第三个线程-窗口
    }
}


//使用Runnable方式，使用 synchronized 实现线程同步
class SellTicket02 implements Runnable {

    private int ticketNum = 100;//让多个线程共享资源

    private boolean loop = true;//控制run方法变量

    /**
     * 说明：
     * 1.public synchronized void sell() {} 就是一个同步方法，这时锁在 this 对象上
     * 2.也可以在代码块上写 synchronized， 同步代码块
     */
    public /*synchronized*/ void sell() {//同步方法，在同一时刻，只能右一个线程来执行sell方法
        synchronized (this) {
            if (ticketNum <= 0) {
                System.out.println("售票结束!");
                loop = false;
                return;
            }

            //休眠100毫秒，模拟
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票，还剩 " + (--ticketNum));
        }
    }

    /**
     * 说明：
     *  1.public synchronized static void m1() {} 锁是加在 SellTicket02.class
     *  2.如果在静态方法中，想要实现同步代码块的话：synchronized (SellTicket02.class) {}
     */
    public synchronized static void m1() {
        synchronized (SellTicket02.class) {
            System.out.println("hello world!");
        }
    }


    @Override
    public void run() {
        while (loop) {
            sell();
        }
    }
}

