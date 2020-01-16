public class Node{
  //Oppretter variabler/egenskaper.
  private int memory;
  private int cpu;

  public Node(int numb1, int numb2){
    //Oppdaterer variablene.
    memory = numb1;
    cpu = numb2;
  }
  //Henter antall prosessorer.
  public int numbCpu(){
    return cpu;
  }
  //Sjekker om noden har tilstrekkelig minne for programmet.
  public boolean enoughMem(int minMem){
    if (memory >= minMem) {
      return true;
    } else {
      return false;
    }
  }
}


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


class main{
  //Oppretter Regneklynge.
  public static void main(String[] args) {
    //Regneklynge abel = new Regneklynge(2);
    Regneklynge abel = new Regneklynge("regneklynge.txt");

    //Printer ut kriteriene.
    System.out.println("Noder med minst 32 GB: " + abel.enoughMem(32));
    System.out.println("Noder med minst 64 GB: " + abel.enoughMem(64));
    System.out.println("Noder med minst 128 GB: " + abel.enoughMem(128));
    System.out.println("Antall prosessorer: " + abel.numbCpu());
    System.out.println("Antall racks: " + abel.numbRacks());
  }
}
