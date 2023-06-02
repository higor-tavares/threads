package org.example;


import java.util.concurrent.atomic.AtomicInteger;

public class AtomicClasses {

  static final AtomicInteger i = new AtomicInteger(-1);
  public static void main(String[] args) throws InterruptedException {
    InternalRunnable runnable = new InternalRunnable();
    Thread t0 = new Thread(runnable);
    Thread t1 = new Thread(runnable);
    Thread t2 = new Thread(runnable);
    t0.start();
    t1.start();
    t2.start();
  }
  public static class InternalRunnable implements Runnable {

    @Override
    public void run() {
      String name = Thread.currentThread().getName();
      System.out.println("Thread " +name + " : " +i.incrementAndGet());
    }
  }

}
