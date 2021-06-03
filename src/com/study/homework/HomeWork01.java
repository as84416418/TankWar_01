package com.study.homework;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Scanner;

/**
 * 作业：
 * 1.在mian方法中启动两个线程
 * 2.第一个线程循环随机打印100以内的数
 * 3.直到第二个线程从键盘读取了 “Q” 命令
 */
public class HomeWork01 {
    public static void main(String[] args) {
        A a = new A();
        B b = new B(a);
        a.start();
        b.start();

    }

}

class A extends Thread {

    private boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println((int) (Math.random() * 100 + 1));
        }
        System.out.println("A线程退出...");
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

class B extends Thread {
    private A a;
    private Scanner scanner = new Scanner(System.in);

    public B(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        while (true) {
            //接收到用户的输入
            System.out.println("需要停止线程请输入(Q):");
            char key = scanner.next().toUpperCase().charAt(0);
            if (key == 'Q') {
                //通知A线程停止
                a.setFlag(false);
                //结束B线程
                System.out.println("B线程退出....");
                break;
            }
        }
    }


}
