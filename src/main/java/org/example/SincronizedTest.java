package org.example;

import org.example.domain.SampleRunnable;

public class SincronizedTest {
  public static int i = 0;

  public static void main(String[] args){
    SampleRunnable runnable = new SampleRunnable();
    Thread t0 = new Thread(runnable);
    Thread t1 = new Thread(runnable);
    Thread t2 = new Thread(runnable);
    Thread t3 = new Thread(runnable);
    Thread t4 = new Thread(runnable);
    t0.start();
    t1.start();
    t2.start();
    t3.start();
    t4.start();
  }
}
