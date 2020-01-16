public abstract class Legemiddel{
  private String navn;
  private double pris;
  private static int count = 0;
  private int legemiddelId = 0;
  private double virkestoff;

  public Legemiddel(String nyttNavn, double nyPris, double nyttVirkestoff){
    navn = nyttNavn;
    pris = nyPris;
    virkestoff = nyttVirkestoff;
    legemiddelId = count++;
  }
  public int hentId(){
    return legemiddelId;
  }
  public String hentNavn(){
    return navn;
  }
  public double hentPris(){
    return pris;
  }
  public double hentVirkestoff(){
    return virkestoff;
  }
  public void settNyPris(double nyPris){
    pris = nyPris;
  }
}

class LegemiddelA extends Legemiddel{
  //narkotisk
  protected int styrke;
  public LegemiddelA(String nyttNavn, double nyPris, double nyttVirkestoff, int nyStyrke){
    super(nyttNavn, nyPris, nyttVirkestoff);
    styrke = nyStyrke;
  }
  public int hentNarkotiskStyrke(){
    return styrke;
  }
}

class LegemiddelB extends Legemiddel{
  //vanedannende
  protected int styrke;
  public LegemiddelB(String nyttNavn, double nyPris, double nyttVirkestoff, int nyStyrke){
    super(nyttNavn, nyPris, nyttVirkestoff);
    styrke = nyStyrke;
  }
  public int hentVanedannendeStyrke(){
    return styrke;
  }
}

class LegemiddelC extends Legemiddel{
  //Har ingen tilleggsegenskaper.
  public LegemiddelC(String nyttNavn, double nyPris, double nyttVirkestoff){
    super(nyttNavn, nyPris, nyttVirkestoff);
  }
}
