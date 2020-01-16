abstract class Verktoy extends Vare{
  int vekt;
  public Verktoy(String navn, int pris, int vekt){
  super(navn, pris);
  this.vekt = vekt;
  }
}
class VerktoyTilSalgs extends Verktoy{
  public VerktoyTilSalgs(String navn, int pris, int vekt){
    super(navn, pris, vekt);
  }
}
class VerktoyTilUtleie extends Verktoy{
  protected boolean ledig = true;
  public VerktoyTilUtleie(String navn, int pris, int vekt){
    super(navn, pris, vekt);
    nettopris *= 1.5;
  }
}
