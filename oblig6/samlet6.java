public class Melding implements Comparable<Melding>{
  public String beskjed;
  public int sekvensnummer;
  public int kanalID;
  public Melding sluttMelding;
  public static int teller = 0;

  public Melding(String beskjed, int kanalID, int sekvensnummer){
    this.beskjed = beskjed;
    this.kanalID = kanalID;
    this.sekvensnummer = sekvensnummer;
  }

  public String hentBeskjed(){
    return beskjed;
  }
  public int hentSekNr(){
    return sekvensnummer;
  }
  public int hentID(){
    return kanalID;
  }
  //(Trengs for å legge til i SortertLenkeliste i Operasjonsleder).
  public int compareTo(Melding x){
    return sekvensnummer - x.hentSekNr();
  }
}

public class Kryptograf implements Runnable{
  public KryptertMonitor kryptert;
  public DekryptertMonitor dekryptert;
  public Melding melding;
  public String dekryptertBeskjed;

  public Kryptograf(KryptertMonitor kryptert, DekryptertMonitor dekryptert){
    this.kryptert = kryptert;
    this.dekryptert = dekryptert;
  }

  public void run(){
    //Henter hver melding fra den krypterte monitoren og dekrypterer det.
    Melding monitorMelding = kryptert.hentMelding();
    while(monitorMelding != null){
      String nyBeskjed = Kryptografi.dekrypter(monitorMelding.hentBeskjed());
      Melding nyMelding = new Melding(nyBeskjed, monitorMelding.hentID(), monitorMelding.hentSekNr());
      //Legger til kryptert melding til monitor.
      dekryptert.leggTil(nyMelding);
      //System.out.println(nyBeskjed);
      monitorMelding = kryptert.hentMelding();

    }
    //Legger til null for å signalisere.
    dekryptert.leggTil(null);
  }
}


public class DekryptertMonitor{
  public Lenkeliste<Melding> meldingerDekrypterte = new Lenkeliste<Melding>();
  private Lock laas = new ReentrantLock();
  private Condition signal = laas.newCondition();
  public Kryptograf kryptograf;
  public int antallKryptografer;

  public DekryptertMonitor(int antallKryptografer){
    this.antallKryptografer = antallKryptografer;
  }

  public void leggTil(Melding melding){
    //Prover aa legge til melding.
    laas.lock();
    try{
      if (melding == null){
        antallKryptografer--;
      }
      else{
    meldingerDekrypterte.leggTil(melding);
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
      while(meldingerDekrypterte.stoerrelse() == 0){
        if(antallKryptografer == 0){
          return null;
        }
        signal.await();
      }
      return meldingerDekrypterte.fjern(0);
    }
    catch(InterruptedException e){
      return null;
    }
    finally{
      laas.unlock();
    }
  }
  public void ferdig(){
      //Dersom en kryptograf er ferdig, minsker antallKryptografer.
    antallKryptografer--;
  }
}

public class Telegrafist implements Runnable{
  public Kanal kanal;
  public KryptertMonitor monitor;

  public Telegrafist(Kanal kanal, KryptertMonitor monitor){
    this.kanal = kanal;
    this.monitor = monitor;
  }

  public void run(){
    //Lytter til alle beskjeder og oppretter meldinger til monitoren.
    int sekvensnummer = 0;
    String beskjed = kanal.lytt();
    while (beskjed != null){
      Melding melding = new Melding(beskjed, kanal.hentId(), sekvensnummer);
      sekvensnummer++;
      monitor.leggTil(melding);
      beskjed = kanal.lytt();
    }
    //Legger til null for å signalisere.
    monitor.leggTil(null);
  }
}


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

class Main{
  //Oppretter alle tråder som trengs.
  public static void main(String[] args) {
    int antallTelegrafister = 3;
    int antallKryptografer = 10;
    Operasjonssentral ops = new Operasjonssentral(antallTelegrafister);
    Kanal[] kanaler = ops.hentKanalArray();

    KryptertMonitor monitor1 = new KryptertMonitor(kanaler.length);
    DekryptertMonitor monitor2 = new DekryptertMonitor(antallKryptografer);
    //Oppretter telegrafister.
    for(int i = 0; i < kanaler.length; i++){
      Thread telegrafister = new Thread(new Telegrafist(kanaler[i], monitor1));
      telegrafister.start();
    }
    //Oppretter kryptografer.
    for(int i = 0; i < antallKryptografer; i++){
      Thread kryptografer = new Thread(new Kryptograf(monitor1, monitor2));
      kryptografer.start();
    }
    //Oppretter operasjonsleder.
    Thread opLeder = new Thread(new Operasjonsleder(monitor2, antallTelegrafister));
    opLeder.start();
  }
}
