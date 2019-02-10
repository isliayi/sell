package com.moon.sell.genericity;

/**
 * @author moonglade on 2019-01-14.
 * @version 1.0
 */
public class KeyPersonThread extends Thread {

    public void run(){
        System.out.println("陈咬金加入了战斗");
        for (int i = 0; i < 10; i++) {
            System.out.println("关键先生--陈咬金厮杀中");
        }
        System.out.println("陈咬金结束了战斗");
    }
}
