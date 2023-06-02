package org.example;

public class ThreadsJoin {
  public static void main(String[] args) throws InterruptedException {
    InternalRunnable runnable1 =  new InternalRunnable("#1", 500);
    InternalRunnable runnable2 =  new InternalRunnable("#2", 700);
    InternalRunnable runnable3 =  new InternalRunnable("#3", 400);
    Thread t1 = new Thread(runnable1);
    Thread t2 = new Thread(runnable2);
    Thread t3 = new Thread(runnable3);
    t1.start();
    t2.start();
    t3.start();
    t1.join();//waits thread die
    t2.join();
    t3.join();
    System.out.println("Application successfully finished!");
  }
  private static class InternalRunnable implements Runnable {
    private String name;
    private int sleepTime;

    InternalRunnable(String name, int sleepTime){
      this.name = name;
      this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
      try {
        for (int i = 0; i < 6; i++) {
          System.out.println(name + " : " + i);
          Thread.sleep(sleepTime);
        }
        System.out.println(name + " is done!");
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
