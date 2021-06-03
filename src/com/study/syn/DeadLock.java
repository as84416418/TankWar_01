package com.study.syn;

/**
 * 模拟一个死锁的程序
 */
public class DeadLock {
    public static void main(String[] args) {
        //模拟死锁现象
        DeadLockDemon A = new DeadLockDemon(true);
        A.setName("线程A");
        DeadLockDemon B = new DeadLockDemon(false);
        B.setName("线程B");
        A.start();
        B.start();
    }
}

class DeadLockDemon extends Thread{
    static Object o1 = new Object();
    static Object o2 = new Object();
    private boolean flag;

    public DeadLockDemon(Boolean flag){
        this.flag = flag;
    }

    /**
     * 分析：
     *      1.如果flag 为 T, 线程A 就会先得到/持有 o1 对象锁, 然后尝试去获取 o2 对象锁
     *      2.如果线程A 得不到 o2 对象锁, 就会进入Blocked状态
     *      3.如果flag 为 F, 线程B 就会先得到/持有 o2 对象锁, 然后尝试去获取 o3 对象锁
     *      4.如果线程B 得不到 o1 对象锁, 就会进入Blocked状态
     */
    @Override
    public void run() {
        if(flag){
            synchronized (o1){
                System.out.println(Thread.currentThread().getName() + " 进入1");
                synchronized (o2){
                    System.out.println(Thread.currentThread().getName() + " 进入2");
                }
            }
        }else {
            synchronized (o2){
                System.out.println(Thread.currentThread().getName() + " 进入3");
                synchronized (o1){
                    System.out.println(Thread.currentThread().getName() + " 进入4");
                }
            }
        }
    }
}
