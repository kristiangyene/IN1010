import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.lang.InterruptedException;

public class KryptertMonitor{
  public Lenkeliste<Melding> meldingerKrypterte = new Lenkeliste<Melding>();
  private Lock laas = new ReentrantLock();
  private Condition signal = laas.newCondition();
  public Kryptograf kryptograf;
  public int antallTelegrafister;

  public KryptertMonitor(int antallTelegrafister){
    this.antallTelegrafister = antallTelegrafister;
  }

  public void leggTil(Melding melding){
    //Prover aa legge til melding.
    laas.lock();
    try{
      if (melding == null){
        antallTelegrafister--;
      }
      else{
    meldingerKrypterte.leggTil(melding);
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
      while(meldingerKrypterte.stoerrelse() == 0){
        if(antallTelegrafister == 0){
          return null;
        }
        signal.await();
      }
      return meldingerKrypterte.fjern(0);
    }
    catch(InterruptedException e){
      return null;
    }
    finally{
      laas.unlock();
    }
  }
  public void ferdig(){
    //Dersom en telegrafist er ferdig, minsker antallTelegrafister.
    antallTelegrafister--;
  }
}
