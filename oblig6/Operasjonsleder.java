import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;


public class Operasjonsleder implements Runnable{
  public DekryptertMonitor monitor;
  public int antallKanaler;
  public Lenkeliste<SortertLenkeliste<Melding>> alleMeldinger;
  public SortertLenkeliste<Melding> liste1;
  public SortertLenkeliste<Melding> liste2;
  public SortertLenkeliste<Melding> liste3;
  PrintWriter printwriter1;
  PrintWriter printwriter2;
  PrintWriter printwriter3;

  public Operasjonsleder(DekryptertMonitor monitor, int antallKanaler){
    this.monitor = monitor;
    this.antallKanaler = antallKanaler;

    //Lager et sted å lagre alle meldinger i.
    //Vet at det er 3 kanaler.
    alleMeldinger = new Lenkeliste<SortertLenkeliste<Melding>>();
    liste1 = new SortertLenkeliste<Melding>();
    liste2 = new SortertLenkeliste<Melding>();
    liste3 = new SortertLenkeliste<Melding>();
    alleMeldinger.leggTil(liste1);
    alleMeldinger.leggTil(liste2);
    alleMeldinger.leggTil(liste3);
  }
  public void tilFil(){
    File file1 = new File("Utfil1.txt");
    File file2 = new File("Utfil2.txt");
    File file3 = new File("Utfil3.txt");

    try{
    printwriter1 = new PrintWriter(file1, "utf-8");
    printwriter2 = new PrintWriter(file2, "utf-8");
    printwriter3 = new PrintWriter(file3, "utf-8");
  }
  catch(FileNotFoundException e){}

  catch(UnsupportedEncodingException s){}

    for (Melding melding: alleMeldinger.hent(0)){
      printwriter1.println(melding.hentBeskjed());
      printwriter1.print("\n\n");
    }
    printwriter1.close();
    for (Melding melding: alleMeldinger.hent(1)){
      printwriter2.println(melding.hentBeskjed());
      printwriter2.print("\n\n");
    }
    printwriter2.close();
    for (Melding melding: alleMeldinger.hent(2)){
      printwriter3.println(melding.hentBeskjed());
      printwriter3.print("\n\n");
    }
    printwriter3.close();
  }

  public void run(){
    //Legger til meldinger i listene.
    Melding monitorMelding = monitor.hentMelding();
    while(monitorMelding != null){
      int kanalID = monitorMelding.hentID();
      alleMeldinger.hent(kanalID-1).leggTil(monitorMelding);
      //System.out.println(monitorMelding.hentBeskjed());
      monitorMelding = monitor.hentMelding();
    }
    tilFil();
    System.out.println("Ingen fler meldinger.");
  }
}
