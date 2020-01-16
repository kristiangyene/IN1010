import java.util.*;
import java.io.*;
public class Postmann implements Runnable{
  public Postkontor postkontor;

  public Postmann(Postkontor postkontor){
    this.postkontor = postkontor;
  }
  public void run(){
    try{
      int postId = 0;
    File fil = new File("navn.txt");
    Scanner scan = new Scanner(fil);
    while(scan.hasNextLine()){
      postkontor.leggTil(new Post(scan.nextLine(), postId));
      postId++;
    }
  }
  catch(FileNotFoundException e){
    System.out.println("Fant ikke filen.");
    System.exit(1);
    }
    postkontor.leggTil(null);
  }
}
