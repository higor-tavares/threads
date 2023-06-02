package org.example;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SampleExecutor {
  private static final int SEED = 1500;
  private static final int TIMEOUT =  1;

  public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
    ExecutorService executor = null;
    try {
      executor = Executors.newSingleThreadExecutor();
      Future<String> promisse = executor.submit(new HTTPRequest());
      System.out.println(promisse.isDone());
      System.out.println(promisse.get(TIMEOUT, TimeUnit.SECONDS));
    } catch (Exception e) {
      throw e;
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
