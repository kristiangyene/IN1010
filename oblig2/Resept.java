public abstract class Resept{
  public Legemiddel legemiddel;
  private static int reseptId = -1;
  private int pasientId;
  private int reit;
  public Lege lege;

  public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    this.legemiddel = legemiddel;
    this.pasientId = pasientId;
    this.reit = reit;
    lege = utskrivendeLege;
    reseptId += 1;
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
  public int hentPasientId(){
    return pasientId;
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
