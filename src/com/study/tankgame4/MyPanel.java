package com.study.tankgame4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//坦克大战的绘图区域
//为了监听 键盘事件, 实现 KeyListener
//为了实现子弹的动态移动，需要 MyPanel也变成线程，然后在子弹发射时也能够不停重绘画板，实现子弹的动态移动效果
public class MyPanel extends JPanel implements KeyListener, Runnable {
    MyTank mt = null;

    //把敌人的坦克放到一个 Vector 集合中，考虑到多线程的问题
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //定义一个Vector, 用于存放炸弹
    //当子弹击中坦克时，加入一个炸弹对象
    Vector<Bomb> bombs = new Vector<>();
    int enemyTankSize = 3;

    //定义三张炸弹图片,用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel() {
        //初始化己方坦克
        mt = new MyTank(100, 100);

        //初始化敌方坦克
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank((100 * (i + 1)), 0);
            enemyTank.setDirect(2);
            enemyTanks.add(enemyTank);
            //启动敌方坦克线程,让其随机移动
            new Thread(enemyTank).start();

            //每初始化一个敌方坦克，给该坦克初始化一个子弹，并发射(启动该子弹线程)
            Shot shot = new Shot(enemyTank.getX(), enemyTank.getY(), enemyTank.getDirect());
            enemyTank.getShots().add(shot);
            new Thread(shot).start();
        }

        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.png"));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //填充矩形，默认黑色
        g.fillRect(0, 0, 1000, 750);

        //画出坦克-封装方法
        drawTank(mt.getX(), mt.getY(), g, mt.getDirect(), 0);

        //画出子弹
        if (mt.getShot() != null && mt.getShot().isLive() == true) {
            g.draw3DRect(mt.getShot().getX(), mt.getShot().getY(), 2, 2, false);
        }

        //如果bombs 集合中有对象，就画出爆炸效果
        for (int i = 0; i < bombs.size(); i++) {
            //取出炸弹
            Bomb bomb = bombs.get(i);
            System.out.println(" bombs 长度 " + bombs.size());
            //根据当前这个bomb对象的life值去画出对应的图片
            if (bomb.life > 8) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else if (bomb.life > 4) {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image3, bomb.x, bomb.y, 60, 60, this);
            }

            //让生命值减少
            bomb.lifeDown();

            //炸弹效果结束后，把炸弹对象从集合中删除
            if (bomb.life == 0) {
                bombs.remove(bomb);
                System.out.println(" bombs 长度 " + bombs.size());
            }

        }

        //画出敌方坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            //判断当前坦克是否存活
            if (enemyTank.isLive()) {//敌方坦克存活
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                //同时发射子弹
                for (int j = 0; j < enemyTank.getShots().size(); j++) {
                    //取出子弹
                    Shot shot = enemyTank.getShots().get(j);
                    //子弹线程遍历
                    if (shot.isLive()) { //isLive == true
                        g.draw3DRect(shot.getX() + 20, shot.getY() + 60, 2, 2, false);
                    } else {
                        //从Vector 中移除子弹
                        enemyTank.getShots().remove(j);
                    }
                }
            }
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

        //根据坦克的朝向，来绘制坦克
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

    /**
     * 编写子弹击中敌方坦克的方法
     *
     * @param shot
     * @param enemyTank
     */
    public void hitTank(Shot shot, EnemyTank enemyTank) {
        //判断s 击中坦克
        switch (enemyTank.getDirect()) {
            case 0://敌方坦克朝上
            case 2://敌方坦克朝下
                if (shot.getX() > enemyTank.getX() && shot.getX() < enemyTank.getX() + 40
                        && shot.getY() > enemyTank.getY() && shot.getY() < enemyTank.getY() + 60) {
                    shot.setLive(false);
                    enemyTank.setLive(false);
                    //创建Bomb对象,加入到bombs 集合
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                    System.out.println("加入炸弹对象...");
                    enemyTanks.remove(enemyTank);
                }
                break;
            case 1://敌方坦克朝右
            case 3://敌方坦克朝左
                if (shot.getX() > enemyTank.getX() && shot.getX() < enemyTank.getX() + 60
                        && shot.getY() > enemyTank.getY() && shot.getY() < enemyTank.getY() + 40) {
                    shot.setLive(false);
                    enemyTank.setLive(false);
                    //创建Bomb对象,加入到bombs 集合
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                    enemyTanks.remove(enemyTank);
                }
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
            if (mt.getY() > 0) {
                mt.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {//按下D键
            mt.setDirect(1);
            if (mt.getX() + 80 < 1000) {
                mt.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {//按下S键
            mt.setDirect(2);
            if (mt.getY() + 100 < 750) {
                mt.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {//按下A键
            mt.setDirect(3);
            if (mt.getX() > 0) {
                mt.moveLeft();
            }
        }

        //按下 J键，发射子弹
        if (e.getKeyCode() == KeyEvent.VK_J) {
            mt.shotEnemyTank();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //判断是否击中了敌方坦克
            if (mt.getShot() != null && mt.getShot().isLive()) {
                //遍历敌方坦克
                for (int m = 0; m < enemyTanks.size(); m++) {
                    hitTank(mt.getShot(), enemyTanks.get(m));
                }
            }
            this.repaint();
        }
    }
}
