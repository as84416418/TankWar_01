package com.study.tankgame4;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {

    private Vector<Shot> shots = new Vector<>();//地方坦克发射的子弹集合
    private boolean isLive = true;
    //添加一个EnemyTanks集合，用于当前敌方坦克和其他敌方坦克进行比较，是否发生重叠
    private Vector<EnemyTank> enemyTanks = new Vector<>();

    public Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

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
     * 编写方法，判断当前敌方坦克，是否和其他敌方坦克 发生碰撞 或出现 重叠情况
     *
     * @return
     */
    public boolean isTouchEnemyTank() {
        //首先判断当前坦克的朝向
        //由于画出坦克时，是以坦克的左上角作为原点画出坦克的，
        //所以在做比较时，要根据具体的朝向来判断坦克是否重叠或相碰撞
        switch (this.getDirect()) {
            case 0://上
                //当前坦克朝上时，遍历其他所有敌方坦克，进行比较，是否发生重叠or碰撞
                for (int i = 0; i < this.getEnemyTanks().size(); i++) {
                    EnemyTank enemyTank = this.getEnemyTanks().get(i);
                    //进行比较时，需要排除掉跟自己进行比较
                    if (enemyTank != this) {
                        //由于画出的坦克是长宽不相等的矩形，上/下 和 左/右 朝向的长宽需要分开考虑
                        //所以需要根据被比较坦克的运动朝向，来判断是否重叠or碰撞
                        //此时的比较依据是：
                        //  当前坦克的左上角坐标以及右上角坐标出现在被比较坦克的图形范围内
                        //  则视为发生碰撞or重叠情况

                        //被比较坦克的朝向为 上/下
                        //此时的 x范围 [enemyTank.getX(), enemyTank.getX() + 40]
                        //此时的 y范围 [enemyTank.getY(), enemyTank.getY() + 60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //先判断左上角坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                //发生碰撞or重叠,返回 true
                                return true;
                            }
                            //在判断右上角坐标 [this.getX() + 40, this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                //发生碰撞or重叠,返回 true
                                return true;
                            }
                        }
                        //被比较坦克的朝向为 右/左
                        //此时的 x范围 [enemyTank.getX(), enemyTank.getX() + 60]
                        //此时的 y范围 [enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //先判断左上角坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                //发生碰撞or重叠,返回 true
                                return true;
                            }
                            //在判断右上角坐标 [this.getX() + 40, this.getY()]
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                //发生碰撞or重叠,返回 true
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1://右
                for (int i = 0; i < this.getEnemyTanks().size(); i++) {
                    EnemyTank enemyTank = this.getEnemyTanks().get(i);
                    //进行比较时，需要排除掉跟自己进行比较
                    if (enemyTank != this) {
                        //此时的比较依据是：
                        //  当前坦克的右上角坐标以及右下角坐标出现在被比较坦克的图形范围内
                        //  则视为发生碰撞or重叠情况

                        //被比较坦克的朝向为 上/下
                        //此时的 x范围 [enemyTank.getX(), enemyTank.getX() + 40]
                        //此时的 y范围 [enemyTank.getY(), enemyTank.getY() + 60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //先判断右上角坐标 [this.getX() + 60, this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                //发生碰撞or重叠,返回 true
                                return true;
                            }
                            //在判断右下角坐标 [this.getX() + 60, this.getY() + 40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                //发生碰撞or重叠,返回 true
                                return true;
                            }
                        }
                        //被比较坦克的朝向为 右/左
                        //此时的 x范围 [enemyTank.getX(), enemyTank.getX() + 60]
                        //此时的 y范围 [enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //先判断右上角坐标 [this.getX() + 60, this.getY()]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                //发生碰撞or重叠,返回 true
                                return true;
                            }
                            //在判断右下角坐标 [this.getX() + 60, this.getY() + 40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                //发生碰撞or重叠,返回 true
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2://下
                for (int i = 0; i < this.getEnemyTanks().size(); i++) {
                    EnemyTank enemyTank = this.getEnemyTanks().get(i);
                    //进行比较时，需要排除掉跟自己进行比较
                    if (enemyTank != this) {
                        //此时的比较依据是：
                        //  当前坦克的左下角坐标以及右下角坐标出现在被比较坦克的图形范围内
                        //  则视为发生碰撞or重叠情况

                        //被比较坦克的朝向为 上/下
                        //此时的 x范围 [enemyTank.getX(), enemyTank.getX() + 40]
                        //此时的 y范围 [enemyTank.getY(), enemyTank.getY() + 60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //先判断左下角坐标 [this.getX(), this.getY() + 60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                //发生碰撞or重叠,返回 true
                                return true;
                            }
                            //在判断右下角坐标 [this.getX() + 60, this.getY() + 40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                //发生碰撞or重叠,返回 true
                                return true;
                            }
                        }
                        //被比较坦克的朝向为 右/左
                        //此时的 x范围 [enemyTank.getX(), enemyTank.getX() + 60]
                        //此时的 y范围 [enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //先判断左下角坐标 [this.getX(), this.getY() + 60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                //发生碰撞or重叠,返回 true
                                return true;
                            }
                            //在判断右下角坐标 [this.getX() + 60, this.getY() + 40]
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                //发生碰撞or重叠,返回 true
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3://左
                for (int i = 0; i < this.getEnemyTanks().size(); i++) {
                    EnemyTank enemyTank = this.getEnemyTanks().get(i);
                    //进行比较时，需要排除掉跟自己进行比较
                    if (enemyTank != this) {
                        //此时的比较依据是：
                        //  当前坦克的左上角坐标以及左下角坐标出现在被比较坦克的图形范围内
                        //  则视为发生碰撞or重叠情况

                        //被比较坦克的朝向为 上/下
                        //此时的 x范围 [enemyTank.getX(), enemyTank.getX() + 40]
                        //此时的 y范围 [enemyTank.getY(), enemyTank.getY() + 60]
                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //先判断左上角坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                //发生碰撞or重叠,返回 true
                                return true;
                            }
                            //在判断左下角坐标 [this.getX(), this.getY() + 60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                //发生碰撞or重叠,返回 true
                                return true;
                            }
                        }
                        //被比较坦克的朝向为 右/左
                        //此时的 x范围 [enemyTank.getX(), enemyTank.getX() + 60]
                        //此时的 y范围 [enemyTank.getY(), enemyTank.getY() + 40]
                        if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                            //先判断左上角坐标 [this.getX(), this.getY()]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                //发生碰撞or重叠,返回 true
                                return true;
                            }
                            //在判断左下角坐标 [this.getX(), this.getY() + 60]
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                //发生碰撞or重叠,返回 true
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        //没有碰撞or重叠情况，返回false
        return false;

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

            //根据坦克的方向继续移动
            switch (getDirect()) {
                case 0://向上移动
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0 && !isTouchEnemyTank()) {
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
                        if (getX() + 80 < 1000 && !isTouchEnemyTank()) {
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
                        if (getY() + 100 < 750 && !isTouchEnemyTank()) {
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
                        if (getX() > 0 && !isTouchEnemyTank()) {
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
