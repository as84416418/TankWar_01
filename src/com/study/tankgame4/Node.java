package com.study.tankgame4;

/**
 * 一个 Node 对象可以看作是一个坦克，用于读取文件中的坦克信息
 */
public class Node {
    private int x;
    private int y;
    private int direct;

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

    public Node(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }


}
