package com.study.tankgame3;

import javax.swing.*;

public class TankGame03 extends JFrame {
    //定义MyPanel
    MyPanel mp = null;

    public static void main(String[] args) {
        TankGame03 tankGame01 = new TankGame03();
    }
    public TankGame03(){
        mp = new MyPanel();
        new Thread(mp).start();
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
