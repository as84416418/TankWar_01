package com.study.draw;

import javax.swing.*;
import java.awt.*;

public class DrawCircle extends JFrame { // JFrame 对应窗口, 可以理解成是一个画框

    // 定义一个面板，用于画画
    MyPanel mp = null;

    public static void main(String[] args) {
        new DrawCircle();
    }

    //初始化面板
    public DrawCircle() {
        mp = new MyPanel();
        //把面板放入窗口(画框)
        this.add(mp);
        //设置窗口的大小
        this.setSize(400, 300);
        //可以显示出来
        this.setVisible(true);
        //设置, 当点击窗口的关闭后，程序同时被停止
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

//1. 先定义一个MyPanel, 继承JPanel类, 画图形, 就在面板上画
class MyPanel extends JPanel {

    //说明:
    //1. MyPanel 对象相当于是一个用于画画的画板
    //2. Graphics g  把 g 理解成一直画笔
    //3. Graphics 提供了很多绘图的方法
    @Override
    public void paint(Graphics g) {
        super.paint(g);//调用父类的方法完成初始化
        System.out.println("paint 方法 被调用了!");
        //画出一个圆
        //g.drawOval(10, 10, 100, 100);

        //所有图形绘制方法 演示
        //1.画直线 drawLine(起始 int x1 坐标, 起始 int y1 坐标, 终点 int x2 坐标, 终点 int y2 坐标);
//        g.drawLine(10, 10, 100, 100);
        //2.画矩形边框 drawRect(int x, int y, int width, int height)
//        g.drawRect(10, 10, 100, 100);
        //3.画椭圆边框 drawOval(int x, int y, int width, int height)
        //4.填充矩形 fillRect(int x, int y, int width, int height)
        //5.填充椭圆 fillOval(int x, int y, int width, int height)
        //6.画图片 drawImage(Image img, int x, int y, ..)
        //(1.获取图片资源, /bg.jpg 表示在该项目的根目录去获取 bg.jpg 图片资源
//          Image image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bg.jpg"));
//          g.drawImage(image, 10 ,10, 210, 240, this);
        //7.画字符串 drawString(String str, int x, int y)
        //（1.设置颜色和字体
//        g.setColor(Color.red);
//        g.setFont(new Font("隶书", Font.BOLD, 50));
        //这里的 100， 100 是字符串的 左下角坐标
//        g.drawString("坦克大战！", 100, 100);
        //8.设置画笔的字体 setFont(Font font)
        //9.设置画笔的颜色 setColor(Color c)

        //尝试画出坦克
        g.setColor(Color.cyan);
        g.fillRect(10, 10, 20, 100);
        g.fillRect(70, 10, 20, 100);
        g.fillRect(30,20, 40,80);
        g.setColor(Color.blue);
        g.fillOval(30, 40, 40, 40);
        g.drawLine(50, 10, 50, 60);


    }
}


