package com.study.tankgame4;

/**
 * 爆炸效果图
 */
public class Bomb {
    int x,y;//炸弹坐标
    int life = 12;//炸弹生命周期，用于爆炸效果的图片切换
    boolean isLive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //减少生命值
    public void lifeDown(){
        if(life > 0){
            life--;
        }else {
            isLive = false;
        }
    }
}
