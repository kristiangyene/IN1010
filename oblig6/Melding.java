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
