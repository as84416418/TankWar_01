package com.study.tankgame3;

/**
 * 把子弹发射的方法写到坦克中
 */
public class MyTank extends Tank {
    private Shot shot = null;
    public MyTank(int x, int y) {
        super(x, y);
    }

    public void shotEnemyTank(){
        shot = new Shot(getX(),getY(),getDirect());
        //需要根据此时坦克的朝向，调整炮管(确定子弹的起始发射位置)
        switch (getDirect()){
            case 0://向上
                shot.setX(getX() + 20);
                shot.setY(getY());
                break;
            case 1://向右
                shot.setX(getX() + 60);
                shot.setY(getY() + 20);
                break;
            case 2://向下
                shot.setX(getX() + 20);
                shot.setY(getY() + 60);
                break;
            case 3://向左
                shot.setX(getX());
                shot.setY(getY() + 20);
                break;
        }
        //发射子弹(启动子弹线程)
        new Thread(shot).start();
    }

    public Shot getShot() {
        return shot;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
    }
}
