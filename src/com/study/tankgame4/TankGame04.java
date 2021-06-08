package com.study.tankgame4;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankGame04 extends JFrame {
    //定义MyPanel
    MyPanel mp = null;

    public static void main(String[] args) {
        TankGame04 tankGame01 = new TankGame04();
    }
    public TankGame04(){
        mp = new MyPanel();
        new Thread(mp).start();
        this.add(mp);
        //设置窗口的大小
        this.setSize(1300, 750);
        //可以显示出来
        this.setVisible(true);
        //设置, 当点击窗口的关闭后，程序同时被停止
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(mp);

        //再JFrame 中增加响应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
