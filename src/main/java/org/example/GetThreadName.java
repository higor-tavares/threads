package org.example;

public class GetThreadName {
  public static void main(String[] args){
    Thread thread = Thread.currentThread();
    System.out.println("Thead name:" + thread.getName());
    Thread thread2 = new Thread(()->{
      System.out.println("Thread "+ Thread.currentThread().getName()+" rodando!");
    });
    System.out.println("Prepearando pra inicializar a thread:"+ thread2.getName());
    //thread2.run() chama o runnable mas não inicia a nova thread. rodaria na main
    thread2.start();
    //thread2.start();2 chamadas ao start causa java.lang.IllegalThreadStateException
  }
}
