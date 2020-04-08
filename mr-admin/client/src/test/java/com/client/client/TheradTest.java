package com.client.client;

import java.util.concurrent.*;

public class TheradTest {
    public static int sum;

    public static int counter() {
        for (int i = 0; i < 10; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("线程池满了");
                    }
                });
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i <100 ; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("thread1");
                }
            });
            threadPoolExecutor.execute(thread);
            System.out.println(i);
        }
        executorService.shutdown();
    }
}
