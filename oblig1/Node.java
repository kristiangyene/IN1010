/*Oppretter en klasse Node. Oppretter to variabler memory og cpu.
Metoden numbCpu skal kun returnere antall prosessorer.
Metoden enoughMem skal returnere True hvis minnet er storre eller likt et
paakrevd minne.*/

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
