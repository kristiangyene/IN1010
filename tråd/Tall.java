import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class NteTall{

    Condition ikkjeNeste;
    Condition provIgjen;
    Lock talLaas;

   NteTall(){
     talLaas = new ReentrantLock();
     ikkjeNeste = talLaas.newCondition();
     provIgjen = talLaas.newCondition();
     NteMonitor m = new NteMonitor();

     for(int i = 0; i<10; i++){
       new Thread(new MinRun(i, 0, 1000, m)).start();
     }

   }
  public static void main(String[] args){
    new NteTall();

  }


  class NteMonitor{
      int tal = -1; //neste tal som skal skrivast ut

      public void skrivUt(int i){
        talLaas.lock();
        try{
          while(i-1 != tal){
            //viss tråden gir frå seg eit tal som ikkje er den neste i rekkja,
            //sei at den må vente
            ikkjeNeste.await();
          }

          //Tråd gir frå seg talet som er den neste i rekkja:
          tal = i;
          System.out.println(tal);
          ikkjeNeste.signalAll();

        }
        catch(InterruptedException e){
          System.out.println("feil");
        }
        finally{
          talLaas.unlock();
        }

    }
  }
}
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MinRun implements Runnable{
  private final int SLUTT;
  private final int START;
  private final int N;
  private int teller = 0;
  private final NteTall.NteMonitor monitor;

  private final Lock lock = new ReentrantLock();

  public MinRun(int n, int start, int slutt, NteTall.NteMonitor m){
    this.SLUTT = slutt;
    this.START = start;
    this.N = n;
    this.monitor = m;
  }

  public void run(){
    lock.lock();
    try{
      while(teller < SLUTT){
        if(teller % 10 == N){
          monitor.skrivUt(teller);
        }
        teller++;
        }
      }finally{
        lock.unlock();
      }
    }
  }
