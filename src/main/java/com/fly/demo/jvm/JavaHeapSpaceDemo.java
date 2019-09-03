package com.fly.demo.jvm;

import java.util.Random;

/**
 * 把内存调小点
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        String str = "aaas";

        while (true){
            str += str + new Random().nextInt(1111111)+new Random().nextInt(2222222);
            str.intern();
        }
        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    }
}
