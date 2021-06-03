package com.study.ticket;

/**
 * 使用线程模拟三个窗口进行售票
 */
public class SellTicket {
    public static void main(String[] args) {

        /*
        //测试
        SellTicket01 sellTicket01 = new SellTicket01();
        SellTicket01 sellTicket02 = new SellTicket01();
        SellTicket01 sellTicket03 = new SellTicket01();
        //这里会出现超卖现象
        sellTicket01.start();//启动售票线程
        sellTicket02.start();//启动售票线程
        sellTicket03.start();//启动售票线程
        */

        //仍会出现超卖现象
        SellTicket02 sellTicket02 = new SellTicket02();
        new Thread(sellTicket02).start();//第一个线程-窗口
        new Thread(sellTicket02).start();//第二个线程-窗口
        new Thread(sellTicket02).start();//第三个线程-窗口
    }
}

//使用Thread方式
class SellTicket01 extends Thread {
    //多个线程共享资源
    private static int ticketNum = 100;

    @Override
    public void run() {
        while (true) {
            if (ticketNum <= 0) {
                break;
            }

            //休眠100毫秒，模拟
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票，还剩 " + (--ticketNum));
        }
        System.out.println("售票结束!");
    }
}

//使用Runnable方式
class SellTicket02 implements Runnable {

    private int ticketNum = 100;

    @Override
    public void run() {
        while (true) {
            if (ticketNum <= 0) {
                break;
            }

            //休眠100毫秒，模拟
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("窗口 " + Thread.currentThread().getName() + " 售出一张票，还剩 " + (--ticketNum));
        }
        System.out.println("售票结束!");
    }
}
