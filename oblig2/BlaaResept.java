class BlaaResept extends Resept{
  protected String typeResept = "Blaa";
  public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    super(legemiddel, utskrivendeLege, pasientId, reit);
  }
  public String farge(){
    return typeResept;
  }
  @Override
  public double prisAaBetale(){
    double totalt = legemiddel.hentPris();
    totalt *= 0.25;
    return totalt;
  }
}
