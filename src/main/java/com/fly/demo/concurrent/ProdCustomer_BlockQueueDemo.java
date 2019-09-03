package com.fly.demo.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{
    private volatile boolean flag = true;//默认开启,进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception{
        String data = null;
        boolean retValue;
        while (flag){
            data = atomicInteger.incrementAndGet()+"";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName()+"\t插入队列" + data + "成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t大老板叫停了，表示flag=false，生产结束");
    }

    public void myConsumer() throws Exception{
        String data = null;
        while (flag){
            data = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(null == data || data.equalsIgnoreCase("")){
                flag = false;
                System.out.println(Thread.currentThread().getName()+"\t超过2秒没有取到，消费退出");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t消费队列" + data + "成功");

        }
    }

    public void stop() throws Exception{
        flag = false;
    }
}

/**
 *
 */
public class ProdCustomer_BlockQueueDemo {

    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"prod").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t消费线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("5秒时间到，大老板main线程叫停，活动结束");
        myResource.stop();
    }
}
