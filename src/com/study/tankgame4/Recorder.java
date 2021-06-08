package com.study.tankgame4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 该类用于记录坦克大战的相关信息,以及和文件交互
 */
public class Recorder {

    //定义变量,记录我方击毁敌人坦克数
    private static int allEnemyTank = 0;
    //定义IO对象
    private static BufferedWriter bw = null;
    private static String recordFile = "src\\myRecord.txt";

    //增加一个方法，当游戏退出时，把 allEnemyTank 的值保存到myRecord
    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTank + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static int getAllEnemyTank() {
        return allEnemyTank;
    }

    public static void setAllEnemyTank(int allEnemyTank) {
        Recorder.allEnemyTank = allEnemyTank;
    }

    //当我方坦克击毁一个敌方坦克，就应当 allEnemyTank++
    public static void add() {
        Recorder.allEnemyTank++;
    }
}
