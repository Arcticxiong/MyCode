package com.fly.demo.jvm;

import java.util.ArrayList;

/**
 * jdk自带的jvisualvm监控堆内存变化,观察visual gc 插件
 */
public class JvisualVm {
    byte[] a = new byte[1024*100];

    public static void main(String[] args) throws InterruptedException {
        ArrayList<JvisualVm> heapTests = new ArrayList<>();
        while (true){
            heapTests.add(new JvisualVm());
            Thread.sleep(50);
        }
    }
}
