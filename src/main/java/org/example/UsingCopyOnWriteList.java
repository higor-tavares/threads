package org.example;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class UsingCopyOnWriteList {
  private static final List<String> threadSafeList = new CopyOnWriteArrayList<>();
  private static final Map<String, String> threadSafeMap = new ConcurrentHashMap<>();
  public static void main(String[] args) throws InterruptedException {
    InternalRunnable runnable = new InternalRunnable();
    Thread t0 = new Thread(runnable);
    Thread t1 = new Thread(runnable);
    Thread t2 = new Thread(runnable);
    t0.start();
    t1.start();
    t2.start();
    Thread.sleep(500);
    System.out.println(threadSafeList);
    System.out.println(threadSafeMap);
  }
  public static class InternalRunnable implements Runnable {

    @Override
    public void run() {
      threadSafeList.add("I already say this!");
      String name = Thread.currentThread().getName();
      threadSafeMap.put(name, "This is a map value");
      System.out.println("Thread "+name + " inserted on list!");
    }
  }
}
