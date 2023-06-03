package org.example;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ThreadPool {
  private static final int SEED = 1500;
  private static final int TIMEOUT =  500;

  public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
    ExecutorService executor = null;
    try {
     // executor = Executors.newCachedThreadPool();
      executor = Executors.newFixedThreadPool(2);
      List<Future<String>> promises = executor.invokeAll(List.of(new HTTPRequest(), new HTTPRequest(), new HTTPRequest()));
      for(Future<String> f : promises) {
        System.out.println(f.isDone());
        try {
          String response = f.get(TIMEOUT, TimeUnit.MILLISECONDS);//timeout is not throwing
          System.out.println(response);
        } catch (TimeoutException e) {
          f.cancel(true);
          e.printStackTrace();
        }
        catch (Exception e) {
          e.printStackTrace();
          throw new RuntimeException(e);
        }
      }
    } finally {
      if(executor != null) {
        executor.shutdownNow();
      }
    }
  }

  public static class HTTPRequest implements Callable<String> {

    @Override
    public String call() throws Exception {
      int time = new Random().nextInt(SEED);
      Thread.sleep(time);
      return "Response from thread: " + Thread.currentThread().getName() + " Response time : "+ time;
    }
  }
}
