package org.example.javaefetivo;

import java.util.concurrent.TimeUnit;

public class StopUsingVolatile {
  private static volatile boolean stopRequest;
  public static void main(String[] args) throws InterruptedException {
    Thread background = new Thread(()->{
      int i = 0;
      System.out.println("Running current thread...");
      while(!stopRequest){
        i++; //para apos um segundo por que o volatile garante que a thread pegue o valor mais recente
      }
      System.out.println("End of current thread!");
    });
    background.start();
    TimeUnit.SECONDS.sleep(1);
    stopRequest = true;
  }
}
