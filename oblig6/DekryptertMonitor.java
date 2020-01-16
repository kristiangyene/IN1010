import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.lang.InterruptedException;

public class DekryptertMonitor{
  public Lenkeliste<Melding> meldingerDekrypterte = new Lenkeliste<Melding>();
  private Lock laas = new ReentrantLock();
  private Condition signal = laas.newCondition();
  public Kryptograf kryptograf;
  public int antallKryptografer;

  public DekryptertMonitor(int antallKryptografer){
    this.antallKryptografer = antallKryptografer;
  }

  public void leggTil(Melding melding){
    //Prover aa legge til melding.
    laas.lock();
    try{
      if (melding == null){
        antallKryptografer--;
      }
      else{
    meldingerDekrypterte.leggTil(melding);
    }
    signal.signalAll();
  }
  finally{
    laas.unlock();
    }
  }

  public Melding hentMelding(){
    //Returnerer og fjerner melding i lenkeliste om listen ikke er tom.
    laas.lock();
    try{
      while(meldingerDekrypterte.stoerrelse() == 0){
        if(antallKryptografer == 0){
          return null;
        }
        signal.await();
      }
      return meldingerDekrypterte.fjern(0);
    }
    catch(InterruptedException e){
      return null;
    }
    finally{
      laas.unlock();
    }
  }
  public void ferdig(){
      //Dersom en kryptograf er ferdig, minsker antallKryptografer.
    antallKryptografer--;
  }
}
