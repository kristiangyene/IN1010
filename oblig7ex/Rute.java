public abstract class Rute{
  protected int x, y;
  protected Rute nord, sor, ost, vest;
  protected boolean paaVeien;
  protected static OrdnetLenkeliste<String> loseliste = new OrdnetLenkeliste<String> ();

  public Rute(int x, int y){
    this.x = x;
    this.y = y;
    this.paaVeien = false;
  }

  public int giRad(){
    return x;
  }

  public int giKol(){
    return y;
  }

  public abstract char tilTegn();

  @Override
  public String toString(){
    int kolonne = x+1;
    int rad = y+1;
    return "(" + kolonne + ", "+ rad + ")";
  }


  public void setteAlleNaboer(Rute nord, Rute vest, Rute sor, Rute ost){
    this.nord = nord;
    this.vest = vest;
    this.sor = sor;
    this.ost = ost;
  }

  //Setter at dette er en besokt rute
  public void harBesokt(){
    paaVeien = true;
  }

  public void resett(){
    paaVeien = false;
  }

  //Returnerer paaVei.
  public boolean giPaaVeien(){
    return paaVeien;
  }

  //Oppretter et array for a gjore det lettere a ga igjennom
  public Rute[] lagRuterArray(){
    Rute[] r = new Rute[4];
    r[0] = sor;
    r[1] = ost;
    r[2] = nord;
    r[3] = vest;
    return r;
  }

  //Gaar igjennom alle
  public void gaa(Rute r, String gaar){
    r.harBesokt();
    if (r instanceof SortRute){
      loseliste.settInn("Ingen utveier.");
      return;
    }

    if (r instanceof Aapning){
      gaar = gaar + r.toString();
      loseliste.settInn(gaar);
      return;
    }

    Rute[] liste = r.lagRuterArray();
    for (int i = 0; i < liste.length; i++){
      if (liste[i] instanceof HvitRute && !(liste[i].giPaaVeien())){
        gaar = gaar + r.toString();
        gaa(liste[i], gaar = gaar + " --> ");
        int s = gaar.length();
        int k = (s - 13 > 0) ? s - 13 : s - 11;
        gaar = gaar.substring(0, k);
      }
    }
  }

  public OrdnetLenkeliste<String> finnUtvei(){
    gaa(this, "");
    OrdnetLenkeliste<String> n = new OrdnetLenkeliste<String>();
    while(loseliste.storrelse() > 0){
    n.settInn(loseliste.fjern());
    }
    return n;
    }

}
