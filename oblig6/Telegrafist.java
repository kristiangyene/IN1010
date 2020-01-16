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
