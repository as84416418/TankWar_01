package com.study.tankgame;

import javax.swing.*;
import java.awt.*;

//坦克大战的绘图区域
public class MyPanel extends JPanel {
    MyTank mt = null;

    public MyPanel() {
        mt = new MyTank(100, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //填充矩形，默认黑色
        g.fillRect(0, 0, 1000, 750);

        //画出坦克-封装方法
        drawTank(mt.getX(), mt.getY(), g, 0, 0);
    }

    /**
     *      编写方法，画出坦克
     * @param x         坦克的x轴坐标
     * @param y         坦克的y轴坐标
     * @param g
     * @param direct    坦克的朝向(上、下、左、右)
     * @param type      坦克的类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {

        //根据不同的坦克设置不同的颜色
        switch (type) {
            case 0://己方坦克
                g.setColor(Color.cyan);
                break;
            case 1://地方坦克
                g.setColor(Color.yellow);
                break;
        }

        //根绝坦克的朝向，来绘制坦克
        switch (direct) {
            case 0://方向朝上
                //画出坦克左边的轮子
                g.fill3DRect(x, y, 10, 60, false);
                //画出坦克右边边的轮子
                g.fill3DRect(x + 30, y, 10, 60, false);
                //画出坦克中间的身体
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                //画出坦克中间的圆盖
                g.fillOval(x + 10, y + 20, 20, 20);
                //画出炮管
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            default:
                break;
        }
    }
}
