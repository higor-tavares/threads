package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class UsingVolatile {

  private static volatile int  i = 0;
  private static volatile boolean prepared = false;
  public static void main(String[] args) throws InterruptedException {
    while(true) {
      InternalRunnable runnable = new InternalRunnable();
      Thread t0 = new Thread(runnable);
      Thread t1 = new Thread(runnable);
      Thread t2 = new Thread(runnable);
      t0.start();
      t1.start();
      t2.start();
      i = 42;
      prepared = true;
      while (t0.getState() != Thread.State.TERMINATED || t1.getState() != Thread.State.TERMINATED || t2.getState() != Thread.State.TERMINATED) {
        //wait
      }
      i = 0;
      prepared = false;
    }
  }
  public static class InternalRunnable implements Runnable {

    @Override
    public void run() {
      while(!prepared) {
        Thread.yield(); //libera o processador.
      }
      System.out.println(i);
      if(i != 42){
        throw new RuntimeException("O valor é diferente de 42!");
      }
    }
  }
}
