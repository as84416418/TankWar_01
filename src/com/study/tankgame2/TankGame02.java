package com.study.tankgame2;

import javax.swing.*;

public class TankGame02 extends JFrame {
    //定义MyPanel
    MyPanel mp = null;

    public static void main(String[] args) {
        TankGame02 tankGame01 = new TankGame02();
    }
    public TankGame02(){
        mp = new MyPanel();
        this.add(mp);
        //设置窗口的大小
        this.setSize(1000, 750);
        //可以显示出来
        this.setVisible(true);
        //设置, 当点击窗口的关闭后，程序同时被停止
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(mp);
    }
}
