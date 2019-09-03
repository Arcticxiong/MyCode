package com.fly.demo.jvm;


/**
 * 如何查看一个正在运行的java程序，它的某个JVM参数是否开启？具体值是多少？
 * jps -l 查看后台进程
 * jinfo 查看正在运行中java程序的各种信息
 * 例 jinfo -flag PrintGCDetails 5568
 *  -XX:-PrintGCDetails
 */
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("****HelloGC");
        Thread.sleep(Integer.MAX_VALUE);
//        long totalMemory = Runtime.getRuntime().totalMemory();//返回java虚拟机中的内存总量(初始堆内存，物理内存的1/64)
//        long maxMemory = Runtime.getRuntime().maxMemory();//java虚拟机试图使用的最大内存量（最大堆内存，物理内存的1/4）
//        System.out.println("TOTAL_MEMORY(-Xms) = "+ totalMemory + "(字节)、"+(totalMemory/(double)1024/1024)+"MB");
//        System.out.println("MAX_MEMORY(-Xmx) = " + maxMemory + "(字节)、"+(maxMemory/(double)1024/1024)+"MB");
    }
}
