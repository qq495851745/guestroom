package com.guestroom.bateng;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        int i=0;
        long l = new Date().getTime();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                int sum=0;
                for(int j=0;j<100000000;j++)
                    sum+=j;
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                int sum=0;
                for(int j=0;j<100000000;j++)
                    sum+=j;
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long l1= new Date().getTime();
        System.out.println(l1-l);

    }
}
