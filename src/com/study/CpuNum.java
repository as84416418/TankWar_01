package com.study;

public class CpuNum {

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        int cpuNum = runtime.availableProcessors();

        System.out.println("当前电脑一共有 " + cpuNum + " 个CPU");
    }
}
