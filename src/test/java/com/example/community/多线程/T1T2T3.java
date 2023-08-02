package com.example.community.多线程;

import static java.lang.Thread.sleep;

/**
 * 1、要求线程a执行完才开始线程b, 线程b执行完才开始线程
 */
public class T1T2T3 {
//    Wait()和notify():如果条件不满足，则等待。当条件满足时，等待该条件的线程将被唤醒。一般用在synchronized机制中。
//
//    例如:线程A
//
//    synchronized(obj) {
//        while(!condition) {
//            obj.wait();
//
//        }
//
//        obj.doSomething();
//
//    }
//
//    当线程A获得了obj锁后，发现条件condition不满足，无法继续下一处理，于是线程A就wait()。在另一线程B中，如果B更改了某些条件，使得线程A的condition条件满足了，就可以唤醒线程A。
//
//    线程B
//
//    synchronized(obj) {
//        condition = true;
//
//        obj.notify();
//
//    }
//
//    需要注意的概念是：
//
//            1.调用obj的wait()， notify()方法前，必须获得obj锁，也就是必须写在synchronized(obj) {……} 代码段内。
//
//            2.调用obj.wait()后，线程A就释放了obj的锁，否则线程B无法获得obj锁，也就无法在synchronized(obj) {……} 代码段内唤醒A.
//
//   3.当obj.wait()方法返回后，线程A需要再次获得obj锁，才能继续执行。
//
//            4.如果A1，A2，A3都在obj.wait()，则B调用obj.notify()只能唤醒A1，A2，A3中的一个（具体哪一个由JVM决定）。
//
//            5.obj.notifyAll()则能全部唤醒A1，A2，A3，但是要继续执行obj.wait()的下一条语句，必须获得obj锁，因此，A1，A2，A3只有一个有机会获得锁继续执行，例如A1，其余的需要等待A1释放obj锁之后才能继续执行。
//
//            6.当B调用obj.notify/notifyAll的时候，B正持有obj锁，因此，A1，A2，A3虽被唤醒，但是仍无法获得obj锁。直到B退出synchronized块，释放obj锁后，A1，A2，A3中的一个才有机会获得锁继续执行。
    public static class PrintThread extends Thread {

        PrintThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(getName() + " : " + i);
            }
        }
    }

    public static void main(String[] args) {

        PrintThread t1 = new PrintThread("a");
        PrintThread t2 = new PrintThread("b");
        PrintThread t3 = new PrintThread("c");

        try {
            System.out.println("小灿天下第一");
            //t1线程开始
            t1.start();
            //其他业务逻辑
            System.out.println("小灿天下第二");
            t1.join();
            //join 需要等t1线程结束
            System.out.println("小灿天下第三");

            sleep(1000);
            t2.start();
            t2.join();
            sleep(1000);
            t3.start();
            t3.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//  我对于join方法的理解：
//
//                join() 的源码：
//                public final void join(long millis) throws InterruptedException {
//          synchronized(lock) {
//          ...
//
//            while (isAlive()) {
//               lock.wait(0);
//            }
//         ...
//          }
//     }
//
//             其实就是main()线程调用join()后，synchronized(lock)语句块，获得lock的锁，
//
//                然后判断如果t1线程isAlive(), 就一直lock.wait(), 让自己（main()线程）阻塞住，
//
//                直到t1线程 !isAlive 后才不wait, 等待着被notify(), 然后t1 die后会调用lock.notifyAll()。
//
//
//                注意：这里lock.wait(0)虽然在t1.join()内，但是join()内的代码不是运行在t1线程中，而是运行在main()线程中，
//                      t1线程中运行的是其run()方法内的代码。

}
