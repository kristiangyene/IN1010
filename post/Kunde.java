import java.util.*;
import java.io.*;

public class Kunde implements Runnable{
  public String navn;
  public Postkontor postkontor;
  ArrayList<Post> beskjeder = new ArrayList<Post>();

  public Kunde(String navn, Postkontor postkontor){
    this.navn = navn;
  }
  public void run(){
    Post post = postkontor.hentPost();
    while(post != null){
      String navn = post.hentMottaker();
      if(this.navn.equals(navn)){
          beskjeder.add(post);
      }
      post = postkontor.hentPost();
    }
    printBeskjed();
  }
  public void printBeskjed(){
    for (Post post: beskjeder){
      System.out.println(post.hentBeskjed());
    }
  }
}
