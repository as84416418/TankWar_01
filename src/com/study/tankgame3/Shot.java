package com.study.tankgame3;

/**
 * 添加 己方坦克发射子弹的功能
 * 把子弹看作一个线程，当按下 J键时，启动一个子弹线程, 相当于发射一颗子弹
 * 当子弹碰到地方坦克或者飞出画板规定边界时，终止线程
 */
public class Shot implements Runnable {

    private int x;//子弹的x坐标
    private int y;//子弹的y坐标
    private int direct;//子弹的朝向   0-上 1-右 2-下 3-左
    private int speed = 10;//子弹的飞行速度
    private boolean isLive = true;//用于外界判断子弹线程是否终止

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
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

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //这时判断子弹的方向
            switch (direct) {
                case 0: //子弹向上
                    y -= speed;
                    break;
                case 1: //子弹向右
                    x += speed;
                    break;
                case 2: //子弹向下
                    y += speed;
                    break;
                case 3: //子弹向左
                    x -= speed;
                    break;
            }

            //测试，观察子弹的坐标变化是否与预期相同
            System.out.println("子弹坐标  x=" + x + " y=" +y);
            //判断 如果子弹飞出画板边界，则终止线程
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750)) {
                //这里可以给子弹一个变量用于外界判断子弹线程是否终止
                System.out.println("子弹飞出边界...");
                isLive = false;
                break;
            }
        }
    }
}
