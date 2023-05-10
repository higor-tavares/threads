package org.example.domain;

import static org.example.SincronizedTest.i;


public class SampleRunnable implements Runnable {

  @Override
  public void run() {
    String threadName = Thread.currentThread().getName();
    int j;
    synchronized(this){
      i++;
      j = i *2;
    }
    double jElevadoA10 = Math.pow(j, 10);
    double sqrt = Math.sqrt(jElevadoA10);
    System.out.printf("Thread: %s value: %.2f  counter : %d%n", threadName, sqrt, i);
  }
}
