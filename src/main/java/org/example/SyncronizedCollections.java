package org.example;

import static java.lang.Thread.sleep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SyncronizedCollections {
  public static List<String> data = new ArrayList<>();
  public static void main(String[] args) throws InterruptedException {
    LocalRunnable localRunnable = new LocalRunnable();
    data = Collections.synchronizedList(data);
    Thread t0 = new Thread(localRunnable);
    Thread t1 = new Thread(localRunnable);
    Thread t2 = new Thread(localRunnable);
    t0.start();
    t1.start();
    t2.start();
    sleep(500);
    System.out.println(data);
  }
  public static class LocalRunnable implements Runnable {

    @Override
    public void run() {
      data.add("{nome: Test; idade: 27}");
      String name = Thread.currentThread().getName();
      System.out.printf("%neA thread: %s inseriu dados na lista!%n", name);
    }
  }
}
