import java.util.ArrayList;
/*Oppretter en arrayliste der nodene skal settes inn. Metoden insertNode setter
inn noden i rack. Metoden numbNodes skal finne ut hvor mange noder det er i racket.
Metoden numbCpu legger bruker en teller for å finne ut antall cpu i racken.
enoughMem teller hvor mange noder som har stoerre eller likt antall paakrevd
minne.*/

public class Rack{
  //Oppretter en arraylist der det skal plasseres noder.
  ArrayList<Node> rack = new ArrayList<Node>();
  public Rack(){
  }
  //Plasserer en node i rack.
  public void insertNode(Node node){
    rack.add(node);
  }
  //Antall noder.
  public int numbNodes(){
    int arrayLength = rack.size();
    return arrayLength;
  }
  //Bruker teller til å finne antall prosessorer.
  public int numbCpu(){
    int numb = 0;
    for (int counter = 0; counter < rack.size(); counter++){
      Node e = rack.get(counter);
      int f = e.numbCpu();
      numb += f;
    }
    return numb;
  }
  //Beregner antall noder i racket med minne over gitt grense.
  public int enoughMem(int minMem){
    int nodes = 0;
    for (int counter = 0; counter < rack.size(); counter++){
      Node f = rack.get(counter);
      if (f.enoughMem(minMem) == true) {
        nodes++;
      }
    }
    return nodes;
  }
}
