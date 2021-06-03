package com.study.exit;

public class ThreadExit {
    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();
        //如果希望main线程去控制t1 线程的种植，必须可以修改 loop
        //让 t1 退出 run方法，从而种植 t1线程 -> 通知方式

        //让主线程休眠 10 秒，再通知 t1线程退出
        System.out.println("main线程休眠10秒...");
        Thread.sleep(10000);
        t.setLoop(false);
    }
}
class T extends Thread {
    private int count = 0;

    private boolean loop = true;

    @Override
    public void run() {
        while (loop){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("T 运行中..." + (++count));
        }
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
