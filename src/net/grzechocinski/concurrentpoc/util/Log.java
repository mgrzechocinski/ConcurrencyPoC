package net.grzechocinski.concurrentpoc.util;

/**
 * @author Mateusz Grzechociński <mateusz.grzechocinski@polidea.com>
 */
public class Log {

   public static void i(String msg){
       System.out.println(Thread.currentThread() + ": " + msg);
   }
}
