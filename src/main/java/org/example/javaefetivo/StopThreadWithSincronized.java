package org.example.javaefetivo;

import java.util.concurrent.TimeUnit;

public class StopThreadWithSincronized {
  private static boolean stopRequest;
  private static synchronized void requestStop() {
    stopRequest = true;
  }
  private static synchronized boolean stopRequested() {
    return stopRequest;
  }
  public static void main(String[] args) throws InterruptedException {
    Thread background = new Thread(()->{
      int i = 0;
      System.out.println("Running current thread...");
      while(!stopRequested()){
        i++; //Para em exatament 1 segundo pois o acesso a variavel é sincronizado
      }
      System.out.println("End of current thread!");
    });
    background.start();
    TimeUnit.SECONDS.sleep(1);
    requestStop();
  }
}
