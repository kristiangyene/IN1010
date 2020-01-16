public class Lege{
  String navn;
  public Lege(String legeNavn){
    navn = legeNavn;
  }
  public String hentNavn(){
    return navn;
  }
}
class Fastlege extends Lege implements kommuneavtale{
  protected int avtalenummer;
  public Fastlege(String legeNavn, int avtalenummer){
    super(legeNavn);
    this.avtalenummer = avtalenummer;
  }
  @Override
  public int hentAvtalenummer(){
    return avtalenummer;
  }
}

interface kommuneavtale{
  public int hentAvtalenummer();
}
