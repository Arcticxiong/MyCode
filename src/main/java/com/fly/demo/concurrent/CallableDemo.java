package com.fly.demo.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("**********come in callable");
        TimeUnit.SECONDS.sleep(5);
        return 1024;
    }
}

/**
 * 多线程中，第3种获得多线程的方式
 *  Callable 有返回值,会抛异常,实现的是call（）方法
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Thread t1 = new Thread();
//        t1.start();

        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread2());

        new Thread(futureTask,"AA").start();
        new Thread(futureTask,"BB").start();//同一个futuretask 起多个线程，会复用，任务只做一次，如果希望有多个则起多个不同的futuretask
        int result1 = 100;

//        while (!futureTask.isDone()){
//
//        }

        int result2 = futureTask.get();//建议放在最后,要求获得callable线程的计算结果，如果没有计算完成就要去强求，会导致堵塞，直到计算完成
        System.out.println("*****result:"+(result1+result2));

    }
}
