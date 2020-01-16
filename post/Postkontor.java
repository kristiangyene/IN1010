import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.lang.InterruptedException;
import java.util.*;

public class Postkontor{
  public int antallPostmenn;
  public ArrayList<String> personer = new ArrayList<String>();
  public ArrayList<Post> postList = new ArrayList<Post>();
  private Lock laas = new ReentrantLock();
  private Condition signal = laas.newCondition();

  public Postkontor(int antallPostmenn){
    this.antallPostmenn = antallPostmenn;
  }
  public void leggTil(Post post){
    laas.lock();
    try{
      if(post == null){
        antallPostmenn--;
      }
      else{
        postList.add(post);
      }
      signal.signalAll();
    }
    finally{
      laas.unlock();
    }
  }
  public Post hentPost(){
    laas.lock();
    try{
      while(postList.size() == 0){
        if(antallPostmenn == 0){
          return null;
        }
        signal.await();
      }
      return postList.remove(0);
    }
    catch(InterruptedException e){
      return null;
    }
    finally{
      laas.unlock();
    }
  }
  public void ferdig(){
    antallPostmenn--;
  }
}
