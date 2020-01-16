abstract class Vare implements NavnOgPris{
  public String navn;
  public int pris;
  public double nettopris;
  public boolean ledig;
  public Vare(String navn, int pris){
    this.navn = navn;
    this.pris = pris;
    nettopris = pris;

  }
@Override
public String hentNavn(){
  return navn;
}
@Override
public double hentPris(){
  return nettopris;
  }
}
interface NavnOgPris{
  public String hentNavn();
  public double hentPris();
}
