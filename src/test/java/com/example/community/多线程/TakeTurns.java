package com.example.community.多线程;

/**
 * @author: lichengcan
 * @date: 2023-08-02 10:56
 * @description 两个线程轮流打印数字，一直到100
 **/
public class TakeTurns extends Thread {
    private static boolean flag = true;

    private static int count = 0;


    //synchronized 同步锁
    public synchronized void print1() {
        for (int i = 0; i < 50; i++) {
            try {
                if (flag) {
                    //进行等待
                    System.out.println("小明在等-前 ");
                    wait();
                    System.out.println("小明等完了");
                }
                System.out.println("小明" + count);
                count++;
                flag = !flag;
                //释放锁
                notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void print2() {
        for (int i = 0; i <= 50; i++) {
            try {
                if (!flag) {
                    //进行等待
                    System.out.println("小美在等-前 ");
                    wait();
                    System.out.println("小美等完了");
                }
                System.out.println("小美" + count);
                count++;
                flag = !flag;
                //释放锁
                notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TakeTurns takeTurns1 = new TakeTurns();
        //一个对象-----两个线程----执行两个方法
        //Java中每一个方法都有一个隐式的锁，synchronized 保证了同步， print1() 和 print2() 方法使用了相同的锁对象
        //print1拿到锁对象 执行 | print2 就进行等待
        new Thread(() -> takeTurns1.print1()).start();

        new Thread(() -> takeTurns1.print2()).start();
    }
}
