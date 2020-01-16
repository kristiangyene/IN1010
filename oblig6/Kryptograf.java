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
