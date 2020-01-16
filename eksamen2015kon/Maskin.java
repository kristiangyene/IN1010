abstract class Maskin extends Vare{
  int henstekrefter;
  public Maskin(String navn, int pris, int henstekrefter){
    super(navn, pris);
    this.henstekrefter = henstekrefter;
  }
}
class MaskinTilSalgs extends Maskin{
  public MaskinTilSalgs(String navn, int pris, int henstekrefter){
    super(navn, pris, henstekrefter);
    nettopris = pris + (pris * 0.25);
  }
}
class MaskinTilUtleie extends Maskin{
  protected boolean ledig = true;
  public MaskinTilUtleie(String navn, int pris, int henstekrefter){
    super(navn, pris, henstekrefter);
    nettopris *= 2;
    }
  public void leiUt(){
    ledig = false;
  }
  public void lever(){
    ledig = true;
  }
  public boolean sjekkStatus(){
    if(ledig){
      System.out.println("Maskinen er ledig");
      return ledig;
    }
    else{
      System.out.println("maskinen er allerede i bruk");
      return ledig;
      }
    }
  }
