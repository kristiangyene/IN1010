class HvitResept extends Resept{
  protected String typeResept = "hvit";
  public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit){
    super(legemiddel, utskrivendeLege, p, reit);
  }
  public String farge(){
    return typeResept;
  }
  public double prisAaBetale(){
    double totalt = legemiddel.hentPris();
    return totalt;
  }
}

class Militarresept extends HvitResept{
  public Militarresept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit){
    super(legemiddel, utskrivendeLege, p, reit);
  }
  @Override
  public double prisAaBetale(){
    double totalt = legemiddel.hentPris() * 0;
    return totalt;
    /*legemiddel.settNyPris(0);*/
  }
}
class Presept extends HvitResept{
  public Presept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit){
    super(legemiddel, utskrivendeLege, p, 3);
  }
  @Override
  public double prisAaBetale(){
    double totalt = legemiddel.hentPris();
    if (totalt >= 116) {
      totalt -= 116;
    } else {
      totalt = 0;
    }
    return totalt;
  }
  /*legemiddel.settNyPris(pris);*/
}
