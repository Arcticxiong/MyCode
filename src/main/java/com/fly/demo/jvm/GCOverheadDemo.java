package com.fly.demo.jvm;

import java.util.ArrayList;

public class GCOverheadDemo {
    public static void main(String[] args) {
        int i=0;
        ArrayList<String> list = new ArrayList<>();

        try {
            while (true){
                list.add(String.valueOf(++i).intern());
            }
        }catch (Exception e){
            System.out.println("**************i:"+i);
            e.printStackTrace();
        }
    }
}
