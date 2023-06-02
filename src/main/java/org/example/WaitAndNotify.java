package org.example;

public class WaitAndNotify {
  public static void main(String[] args) throws InterruptedException {
    TikTak tiktak = new TikTak();
    InternalRunnable r1 = new InternalRunnable("tik", tiktak);
    InternalRunnable r2 = new InternalRunnable("tak", tiktak);
    r1.thread.join();
    r2.thread.join();
  }
  private static class InternalRunnable implements Runnable {
    TikTak tikTak;
    Thread thread;
    final int num = 5;
    InternalRunnable(String name, TikTak tikTak){
      this.tikTak = tikTak;
      thread = new Thread(this, name);
      thread.start();
    }
    @Override
    public void run() {
      if(thread.getName().equals("tik")){
        for(int i = 0; i< num; i++){
          tikTak.tik(true);
        }
        tikTak.tik(false);
      }
      else {
        for(int i = 0; i< num; i++){
          tikTak.tak(true);
        }
        tikTak.tak(false);
      }
    }
  }
  private static class TikTak {
    private boolean tik;

    synchronized void tik(boolean isRunning){
      if(!isRunning){
        tik = true;
        notify();
        return;
      }
      System.out.println("Tik");
      tik = true;
      notify();
      try {
        while(tik) {
          wait();
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    synchronized void tak(boolean isRunning){
      if(!isRunning){
        tik = false;
        notify();
        return;
      }
      System.out.println("Tak");
      tik = false;
      notify();
      try {
        while(!tik) {
          wait();
        }
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
