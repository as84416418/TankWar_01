package com.study.tankgame4;

import java.util.Vector;

public class EnemyTank implements Runnable {
    private int x; //坦克的x轴坐标
    private int y;//坦克的y轴坐标
    private int direct;//坦克的朝向  0上  1右  2下  3左
    private int speed = 5;//坦克的移动速度
    private Vector<Shot> shots = new Vector<>();//地方坦克发射的子弹集合
    private boolean isLive = true;

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

    //把坦克的移动(上右下左)方法给封装到坦克类里
    public void moveUp() {
        y -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void moveLeft() {
        x -= speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public EnemyTank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 把敌方坦克设置成一个线程，这样每个坦克各自移动时都可以看作时一个线程的运行
     * 当敌方坦克被打爆,则关闭该线程
     */
    @Override
    public void run() {
        while (true) {
            switch (getDirect()) {
                case 0://向上移动
                    for (int i = 0; i < 30; i++) {
                        moveUp();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1://向右移动
                    for (int i = 0; i < 30; i++) {
                        moveRight();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2://向下移动
                    for (int i = 0; i < 30; i++) {
                        moveDown();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3://向左移动
                    for (int i = 0; i < 30; i++) {
                        moveLeft();
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
