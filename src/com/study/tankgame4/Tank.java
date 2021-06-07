package com.study.tankgame4;

public class Tank {
    private int x; //坦克的x轴坐标
    private int y;//坦克的y轴坐标
    private int direct;//坦克的朝向  0上  1右  2下  3左
    private int speed = 5;//坦克的移动速度

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    private boolean isLive = true;

    //把坦克的移动(上右下左)方法给封装到坦克类里
    public void moveUp(){
            y -= speed;
    }
    public void moveRight(){
            x += speed;
    }
    public void moveDown(){
            y += speed;
    }
    public void moveLeft(){
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

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
