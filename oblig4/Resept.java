public abstract class Resept{
  public Legemiddel legemiddel;
  private static int count = 0;
  private int reseptId = 0;
  public Pasient p;
  private int reit;
  public Lege lege;

  public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit){
    this.legemiddel = legemiddel;
    this.p = p;
    this.reit = reit;
    lege = utskrivendeLege;
    reseptId = count++;
  }
  public int hentId(){
    return reseptId;
  }
  public Legemiddel hentLegemiddel(){
    return legemiddel;
  }
  public Lege hentLege(){
    return lege;
  }
  public Pasient hentPasient(){
    return p;
  }
  public int hentReit(){
    return reit;
  }
  public boolean bruk(){
    reit--;
    if (reit < 0) {
      reit = 0;
      return false;
    }else{
      return true;
    }
  }
    /*if (reit == 0) {
      return false;
    }else {
      return true;
    }
  }*/
  abstract public String farge();
  abstract public double prisAaBetale();
}
