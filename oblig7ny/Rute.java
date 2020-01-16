public abstract class Rute{
  protected Rute ruteNord = null;
  protected Rute ruteSor = null;
  protected Rute ruteOst = null;
  protected Rute ruteVest = null;
  protected Labyrint labyrint;
  protected int rad;
  protected int kolonne;
  protected String veiUt = "";
  protected boolean brukt = false;

  public Rute(int rad, int kolonne, Labyrint labyrint){
    this.rad = rad;
    this.kolonne = kolonne;
    this.labyrint = labyrint;
  }

  //Sjekker alle veier fra ruta.
  public void gaa(String rute){
    veiUt = rute;
    int printKolonne = kolonne+1;
    int printRad = rad+1;

    //Markerer startruta som brukt.
    this.brukt = true;
    //Sjekker om ruta allerede er aapning ved aa sjekke om en av naborutene ikke er en rute.
    if(this.tilTegn() == '.' && (ruteNord == null || ruteOst == null || ruteSor == null || ruteVest == null)){
        labyrint.losninger.leggTil(veiUt + "(" + printKolonne + ", " + printRad + ")");
        return;

    }
    //folger nord om det er en vei.
    if(ruteNord.tilTegn() == '.' && !(ruteNord.brukt)){
      ruteNord.brukt = true;
      ruteNord.gaa(veiUt + "(" + printKolonne + ", " + printRad + ") --> ");
      ruteNord.brukt = false;
    }
    //folger sor om det er en vei.
    if(ruteSor.tilTegn() == '.' && !(ruteSor.brukt)){
      ruteSor.brukt = true;
      ruteSor.gaa(veiUt + "(" + printKolonne + ", " + printRad + ") --> ");
      ruteSor.brukt = false;
    }
    //folger vest om det er en vei.
    if(ruteVest.tilTegn() == '.' && !(ruteVest.brukt)){
      ruteVest.brukt = true;
      ruteVest.gaa(veiUt + "(" + printKolonne + ", " + printRad + ") --> ");
      ruteVest.brukt = false;
    }
    //folger ost om det er en vei.
    if(ruteOst.tilTegn() == '.' && !(ruteOst.brukt)){
      ruteOst.brukt = true;
      ruteOst.gaa(veiUt + "(" + printKolonne + ", " + printRad + ") --> ");
      ruteOst.brukt = false;
    }
  }

  public void finnUtvei(){
    gaa(veiUt);
  }

  public abstract char tilTegn();
  }


class HvitRute extends Rute{
  public HvitRute(int rad, int kolonne, Labyrint labyrint){
    super(rad, kolonne, labyrint);
  }

  public char tilTegn(){
    return '.';
  }
}

class SortRute extends Rute{
  public SortRute(int rad, int kolonne, Labyrint labyrint){
    super(rad, kolonne, labyrint);
  }

  public char tilTegn(){
    return '#';
  }
}

class Aapning extends HvitRute{
  public Aapning(int rad, int kolonne, Labyrint labyrint){
    super(rad, kolonne, labyrint);
  }
}
