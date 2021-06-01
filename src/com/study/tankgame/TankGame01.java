package com.study.tankgame;

import javax.swing.*;

public class TankGame01 extends JFrame {
    //定义MyPanel
    MyPanel mp = null;

    public static void main(String[] args) {
        TankGame01 tankGame01 = new TankGame01();
    }
    public TankGame01(){
        mp = new MyPanel();
        this.add(mp);
        //设置窗口的大小
        this.setSize(1000, 750);
        //可以显示出来
        this.setVisible(true);
        //设置, 当点击窗口的关闭后，程序同时被停止
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
