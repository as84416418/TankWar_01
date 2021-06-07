package com.study.tankgame4;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
//    private int x; //坦克的x轴坐标
//    private int y;//坦克的y轴坐标
//    private int direct;//坦克的朝向  0上  1右  2下  3左
//    private int speed = 5;//坦克的移动速度
    private Vector<Shot> shots = new Vector<>();//地方坦克发射的子弹集合
    private boolean isLive = true;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public Vector<Shot> getShots() {
        return shots;
    }

    public void setShots(Vector<Shot> shots) {
        this.shots = shots;
    }

    /**
     * 把敌方坦克设置成一个线程，这样每个坦克各自移动时都可以看作时一个线程的运行
     * 当敌方坦克被打爆,则关闭该线程
     */
    @Override
    public void run() {
        while (true) {
            //当敌方坦克打出的子弹线程终止之后，重新发射子弹
            if (isLive && shots.size() == 0) {
                Shot s = null;
                //根据坦克的朝向，来确定子弹的初始发射位置
                switch (getDirect()) {
                    case 0:
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        s = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        s = new Shot(getX(), getY() + 20, 3);
                        break;
                }
                //创建子弹之后，把子弹加入到集合中
                shots.add(s);
                //启动新创建的子弹线程，保证他会移动
                new Thread(s).start();
            }
            switch (getDirect()) {
                case 0://向上移动
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1://向右移动
                    for (int i = 0; i < 30; i++) {
                        if (getX() + 80 < 1000) {
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2://向下移动
                    for (int i = 0; i < 30; i++) {
                        if (getY() + 100 < 750) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3://向左移动
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }

            //移动过后，随机改变方向，然后重新循环移动
            setDirect((int) (Math.random() * 4));

            //如果坦克被击中，则isLive 变为 false,此时终止此线程
            if (!isLive) {
                break;
            }
        }
    }
}
