/*I hovedprogram oppretter jeg et RegneKlynge-objekt, abel og skriver hvilken
fil jeg ønsker å bruke. Til slutt printer jeg ut det jeg trenger og kaller paa
metoden enoughMem() for aa telle opp.*/

class main{
  //Oppretter Regneklynge.
  public static void main(String[] args) {
    //Regneklynge abel = new Regneklynge(2);
    Regneklynge abel = new Regneklynge("regneklynge.txt");
    /*abel.insertNode(new Node(16, 1));
    abel.insertNode(new Node(16, 1));
    abel.insertNode(new Node(128, 2));*/
    /*for (int i = 0; i < 650; i++){
      abel.insertNode(new Node(64, 1));
    }
    for (int i = 0; i < 16; i++){
      abel.insertNode(new Node(1024, 2));
    }*/

    //Printer ut kriteriene.
    System.out.println("Noder med minst 32 GB: " + abel.enoughMem(32));
    System.out.println("Noder med minst 64 GB: " + abel.enoughMem(64));
    System.out.println("Noder med minst 128 GB: " + abel.enoughMem(128));
    System.out.println("Antall prosessorer: " + abel.numbCpu());
    System.out.println("Antall racks: " + abel.numbRacks());
  }
}
