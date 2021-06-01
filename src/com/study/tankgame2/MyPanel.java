package com.study.tankgame2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//坦克大战的绘图区域
//为了监听 键盘事件, 实现 KeyListener
public class MyPanel extends JPanel implements KeyListener {
    MyTank mt = null;

    //把敌人的坦克放到一个 Vector 集合中，考虑到多线程的问题
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyTankSize = 3;
    public MyPanel() {
        //初始化己方坦克
        mt = new MyTank(100, 100);

        //初始化敌方坦克
        for(int i = 0; i < enemyTankSize; i++){
            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
            enemyTank.setDirect(2);
            enemyTanks.add(enemyTank);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //填充矩形，默认黑色
        g.fillRect(0, 0, 1000, 750);

        //画出坦克-封装方法
        drawTank(mt.getX(), mt.getY(), g, mt.getDirect(), 0);

        //画出敌方坦克
        for(int i = 0; i < enemyTanks.size(); i++){
            EnemyTank enemyTank = enemyTanks.get(i);
            drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),1);
        }
    }

    /**
     * 编写方法，画出坦克
     *
     * @param x      坦克的x轴坐标
     * @param y      坦克的y轴坐标
     * @param g
     * @param direct 坦克的朝向(上、下、左、右)
     * @param type   坦克的类型
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
                //画出坦克右边的轮子
                g.fill3DRect(x + 30, y, 10, 60, false);
                //画出坦克中间的身体
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                //画出坦克中间的圆盖
                g.fillOval(x + 10, y + 20, 20, 20);
                //画出炮管
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1://方向朝右
                //画出坦克上边的轮子
                g.fill3DRect(x, y, 60, 10, false);
                //画出坦克下边的轮子
                g.fill3DRect(x, y + 30, 60, 10, false);
                //画出坦克中间的身体
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                //画出坦克中间的圆盖
                g.fillOval(x + 20, y + 10, 20, 20);
                //画出炮管
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2://方向朝下
                //画出坦克左边的轮子
                g.fill3DRect(x, y, 10, 60, false);
                //画出坦克右边的轮子
                g.fill3DRect(x + 30, y, 10, 60, false);
                //画出坦克中间的身体
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                //画出坦克中间的圆盖
                g.fillOval(x + 10, y + 20, 20, 20);
                //画出炮管
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3://方向朝左
                //画出坦克上边的轮子
                g.fill3DRect(x, y, 60, 10, false);
                //画出坦克下边的轮子
                g.fill3DRect(x, y + 30, 60, 10, false);
                //画出坦克中间的身体
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                //画出坦克中间的圆盖
                g.fillOval(x + 20, y + 10, 20, 20);
                //画出炮管
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //处理 WASD 键按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {//按下W键
            //改变坦克的方向
            mt.setDirect(0);
            mt.moveUp();
        } else if(e.getKeyCode() == KeyEvent.VK_D) {//按下D键
            mt.setDirect(1);
            mt.moveRight();
        }else if(e.getKeyCode() == KeyEvent.VK_S) {//按下S键
            mt.setDirect(2);
            mt.moveDown();
        }else if(e.getKeyCode() == KeyEvent.VK_A) {//按下A键
            mt.setDirect(3);
            mt.moveLeft();
        }

        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
