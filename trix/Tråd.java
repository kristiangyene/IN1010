import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
class Tråd implements Runnable{
  private int start;
  private int slutt;
  private int maks;
  private int n;
  private int teller;
  Lock lock = new ReentrantLock();

  public static void main(String[] args) {
    new Tråd();
  }



  public Tråd(int n, int start, int maks){
    this.n = n;
    this.start = start;
    this.maks = maks;
  }

  public void run(){
    lock.lock();
    try{
      while(teller < slutt){
        if(teller % 10 == n){
          System.out.println(teller);
          //monitor.skrivUt(teller);
        }
        teller++;
        }
      }finally{
        lock.unlock();
    }
  }
}
