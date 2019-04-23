package com.guestroom.bateng;

import java.util.Date;

public class Test1 {
    public static void main(String[] args) {
        long l = new Date().getTime();
        int sum=0;
        for(int j=0;j<10000000;j++)
            sum+=j;

        int sum1=0;
        for(int j=0;j<10000000;j++)
            sum1+=j;
        long l1= new Date().getTime();
        System.out.println(l1-l);
    }
}
