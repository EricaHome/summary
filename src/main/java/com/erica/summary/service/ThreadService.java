package com.erica.summary.service;

import org.apache.poi.hssf.record.DVALRecord;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @author Erica
 * @date 2021/3/25 23:41
 * @description 线程练习
 */
public class ThreadService {

    private static volatile int target = 1;

    private static Semaphore s1 = new Semaphore(1);
    private static Semaphore s2 = new Semaphore(1);
    private static Semaphore s3 = new Semaphore(1);

    /**
     * 三个线程同步执行
     */
    public void threadSync() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 3; i++) {
            new Thread(() -> { // 线程i在门前等待
                try {
                    countDownLatch.await();
                    System.out.println(System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(5000); // 为了让三个线程有准备的时间
        countDownLatch.countDown();


    }

    /**
     * 打印结果为：
     * 1616687641920
     * 1616687641920
     * 1616687641920
     *
     * 可见三个线程几乎同时到达门口，通过countDown方法一起执行
    */

    /**
     * 三个线程按顺序执行
     */
    public void threadSeq() {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (target == 1) {
                    for (int i = 0;i < 10; i++) {
                        System.out.println("a" + i);
                    }
                    target = 2;
                    return;
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (true) {
                if (target == 2) {
                    for (int i = 0;i < 10; i++) {
                        System.out.println("b" + i);
                    }
                    target = 3;
                    return;
                }
            }
        });
        Thread t3 = new Thread(() -> {
            while (true) {
                if (target == 3) {
                    for (int i = 0;i < 10; i++) {
                        System.out.println("c" + i);
                    }
                    target = 1;
                    return;
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
    }

    /**
     * 三个线程交替执行
     */
    public void threadAlter() {
        try {
            s1.acquire();
            s2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            while (true) {
                try {
                    s1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A");
                s2.release();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    s2.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B");
                s3.release();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    s3.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("C");
                s1.release();
            }
        }).start();
    }

}
