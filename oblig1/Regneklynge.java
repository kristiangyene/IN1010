import java.io.*;
import java.util.*;

/*Legger inn nodesPrRack og en tom ArrayList der jeg kan holde styr på racks
i RegneKlynge. Metoden insertNode skal opprette et rack til aa
begynne med dersom antall racks er 0. Saa legger den inn en node i racket dersom
det er plass. Om ikke skal et nytt rack opprettes og noden skal legges inn der.
numbCpu teller alle racks og kaller paa en metode fra Rack. Bruker samme maate paa
metoden enoughMem. I numbRacks tar jeg bare lengden av ArrayList.*/

public class Regneklynge{
  //#Oppretter tom regneklynge for racks.
  ArrayList<Rack> racks = new ArrayList<Rack>();
  int nodesPrRack;
  int index = 0;
  int numbNodes;
  int numbCpu;
  int numbMem;
  //public Regneklynge(int nodes){
  //  nodesPrRack += nodes;
  //}

  //Leser inn fil og setter inn informasjon fra fil.
  public Regneklynge(String file){
    try{
    Scanner scanner = new Scanner(new File(file));
    nodesPrRack = scanner.nextInt();
    while (scanner.hasNextInt()) {
      if (index == 0) {
        numbNodes = scanner.nextInt();
        index ++;
      }
      else if (index == 1){
        numbMem = scanner.nextInt();
        index ++;
      }
      else if (index == 2) {
        numbCpu = scanner.nextInt();
        for(int i = 0; i < numbNodes; i++){
          Node node = new Node(numbMem, numbCpu);
          insertNode(node);
        }
        index = 0;
      }
    }
    }

    catch(FileNotFoundException f) {
      System.out.println("Fant ikke filen.");
      System.exit(0);
    }
  }
  //Plasserer en node inn i et rack med ledig plass, eller i et nytt.
  public void insertNode(Node node){
    if (racks.size() == 0) {
      racks.add(new Rack());
    }
    for (int counter = 0; counter < racks.size(); counter++) {
      Rack f = racks.get(counter);
      int e = f.numbNodes();
      if (e < nodesPrRack) {
        f.insertNode(node);
      }else if (racks.get(racks.size() - 1).numbNodes() == nodesPrRack) {
        racks.add(new Rack());
      }
    }
  }

  //Beregner antall noder i regneklyngen med minne over angitt grense.
  public int enoughMem(int minMem){
    int total = 0;
    for (int counter = 0; counter < racks.size(); counter++) {
      Rack f = racks.get(counter);
      total += f.enoughMem(minMem);
    }
    return total;
  }

  //Henter antall racks i regneklyngen.
  public int numbRacks(){
    return racks.size();
  }

  //Beregner totalt antall prosessorer i hele regneklyngen
  public int numbCpu(){
    int total = 0;
    for (int counter = 0; counter < racks.size(); counter++) {
      Rack f = racks.get(counter);
      total += f.numbCpu();
    }
    return total;
  }
}
