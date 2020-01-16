public class Lege implements Comparable<Lege>{
  String navn;
  int avtalenummer = 0;
  Lenkeliste<Resept> resepter = new Lenkeliste<Resept>();

  public Lege(String legeNavn){
    navn = legeNavn;
  }
  public String hentNavn(){
    return navn;
  }
  //Må gjøres om
  public int compareTo(Lege x){
    return navn.compareTo(x.hentNavn());
  }

  public Lenkeliste<Resept> hentResepter(){
    return resepter;
  }
  public void leggTil(Resept resept){
    resepter.leggTil(resept);
  }
  public int hentAvtalenummer(){
    return avtalenummer;
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
