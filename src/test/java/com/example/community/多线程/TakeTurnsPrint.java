package com.example.community.多线程;

/**
 * 两个线程轮流打印数字，一直到100
 *
 * Java的wait()、notify()学习：
 */
public class TakeTurnsPrint {

    public static class TakeTurns {

        //是否执行
        private static boolean flag = true;

        //计数
        private static int count = 0;

        public synchronized void print1() {
            //一人冲50发
            for (int i = 1; i <= 50; i++) {
                while (!flag) {
                    try {
                        //拿到锁，等待
                        System.out.println("print1: wait before");
                        wait();
                        System.out.println("print1: wait after");
                    } catch (InterruptedException e) {
                    }
                }
                //打印
                System.out.println("print1: " + ++count);
                flag = !flag;
                //释放锁
                notifyAll();
            }
        }

        public synchronized void print2() {
            for (int i = 1; i <= 50; i++) {
                while (flag) {
                    try {
                        System.out.println("print2: wait before");
                        wait();
                        System.out.println("print2: wait after");
                    } catch (InterruptedException e) {
                    }
                }

                System.out.println("print2: " + ++count);
                flag = !flag;
                notifyAll();
            }
        }
    }

    public static void main(String[] args){

        //创建实例
        TakeTurns takeTurns = new TakeTurns();

        //线程1
        new Thread(() -> takeTurns.print1()).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                takeTurns.print2();
            }
        }).start();

    }

}

