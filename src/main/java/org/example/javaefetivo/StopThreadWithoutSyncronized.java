package org.example.javaefetivo;

import java.util.concurrent.TimeUnit;

public class StopThreadWithoutSyncronized {
  private static boolean stopRequest;
  public static void main(String[] args) throws InterruptedException {
    Thread background = new Thread(()->{
      int i = 0;
      System.out.println("Running current thread...");
      while(!stopRequest){
        i++; //roda infinitamente pois a JVM faz hoisting
      }
      System.out.println("End of current thread!");//nunca sera impresso
    });
    background.start();
    TimeUnit.SECONDS.sleep(1);
    stopRequest = true;
  }
}
