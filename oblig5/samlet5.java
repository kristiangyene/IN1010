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
        labyrint.losninger.leggTil(veiUt + "(" + printRad + "," + printKolonne + ")");
        return;

    }
    //folger nord om det er en vei.
    if(ruteNord.tilTegn() == '.' && !(ruteNord.brukt)){
      ruteNord.brukt = true;
      ruteNord.gaa(veiUt + "(" + printRad + "," + printKolonne + ")-->");
      ruteNord.brukt = false;
    }
    //folger sor om det er en vei.
    if(ruteSor.tilTegn() == '.' && !(ruteSor.brukt)){
      ruteSor.brukt = true;
      ruteSor.gaa(veiUt + "(" + printRad + "," + printKolonne + ")-->");
      ruteSor.brukt = false;
    }
    //folger vest om det er en vei.
    if(ruteVest.tilTegn() == '.' && !(ruteVest.brukt)){
      ruteVest.brukt = true;
      ruteVest.gaa(veiUt + "(" + printRad + "," + printKolonne + ")-->");
      ruteVest.brukt = false;
    }
    //folger ost om det er en vei.
    if(ruteOst.tilTegn() == '.' && !(ruteOst.brukt)){
      ruteOst.brukt = true;
      ruteOst.gaa(veiUt + "(" + printRad + "," + printKolonne + ")-->");
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


class Labyrint{
  protected static int antallRader;
  protected static int antallKolonner;
  protected static Rute[][] charArr;
  protected static Liste<String> losninger = new Lenkeliste<String>();

  private Labyrint(int kolonner, int rader, Rute[][] charArr){
    antallRader = rader;
    antallKolonner = kolonner;
    this.charArr = charArr;
  }


  static Labyrint lesFraFil(File fil) throws FileNotFoundException{
    Scanner scan = new Scanner(fil);
    int tempKolonner = scan.nextInt();
    int tempRader = scan.nextInt();
    //Oppretter todimensjonalt Rute-array.
    Rute[][] ruteArray = new Rute[tempKolonner][tempRader];
    //Bruker variablene til aa opprette labyrint.
    Labyrint labyrint = new Labyrint(tempKolonner, tempRader, ruteArray);
    for (int i = 0; i < tempKolonner; i++){
      String linje = scan.next();
      for (int j = 0; j < tempRader; j++){
        char tegn = linje.charAt(j);

        //Sjekker om det er aapent.
        if (tegn == '.'){
          ruteArray[i][j] = new HvitRute(i, j, labyrint);
          System.out.print(".");
        //Sjekker om det er en aapning.
        if (i == 0 || i == tempRader || j == 0 || j == tempKolonner){
          ruteArray[i][j] = new Aapning(i, j, labyrint);
        }
      }
      //Sjekker om det er lukket.
      else if (tegn == '#'){
        ruteArray[i][j] = new SortRute(i, j, labyrint);
        System.out.print("#");
        }
      }
      System.out.println();
    }
    labyrint.charArr = ruteArray;

    //Sjekker naborutene.
    for (int i = 0; i < tempKolonner; i++){
      for (int j = 0; j < tempRader; j++){
        //Sjekker under.
				if (i+1 < tempKolonner) {
          ruteArray[i][j].ruteSor = ruteArray[i+1][j];
        }
        //Sjekker over.
        if (i-1 >= 0) {
          ruteArray[i][j].ruteNord = ruteArray[i-1][j];
        }
				//Sjekker venstre.
				if (j-1 >= 0) {
          ruteArray[i][j].ruteVest = ruteArray[i][j-1];
        }
				//Sjekker hoyre.
				if (j+1 < tempRader) {
          ruteArray[i][j].ruteOst = ruteArray[i][j+1];
        }
      }
    }
    //ruteArray[3][3].finnUtvei();
    return labyrint;
  }

  static Liste<String> finnUtveiFra(int kolonne, int rad){

    //Tar bort alt for aa kunne kalle paa det flere ganger.
    while(losninger.stoerrelse() != 0) {
			losninger.fjern();
		}
		for (int i = 0; i < kolonne ; i++ ) {
			for (int j = 0; j < rad; j++ ) {
				charArr[i][j].brukt = false;
				charArr[i][j].veiUt = "";
			}
		}
    //Sjekker om svart rute.
    if (charArr[kolonne-1][rad-1] instanceof SortRute){
      losninger.leggTil("Svart rute.");
    }
    //Sjekker om aapning.
    else if (charArr[kolonne-1][rad-1] instanceof Aapning){
      losninger.leggTil("Dette er allerede en aapning.");
      charArr[kolonne-1][rad-1].finnUtvei();
    }
    //Sjekker om hvit rute.
    else{
      charArr[kolonne-1][rad-1].finnUtvei();
    }
    return losninger;
  }
}


class Main {
    public static void main(String[] args) {
        String filnavn = null;

        if (args.length > 0) {
            filnavn = args[0];
        } else {
            System.out.println("FEIL! Riktig bruk av programmet: "
                               +"java Oblig5 <labyrintfil>");
            return;
        }
        File fil = new File(filnavn);
        Labyrint l = null;
        try {
            l = Labyrint.lesFraFil(fil);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", filnavn);
            System.exit(1);
        }

        // les start-koordinater fra standard input
        Scanner inn = new Scanner(System.in);
        System.out.println("Skriv inn koordinater <kolonne> <rad> ('a' for aa avslutte)");
        String[] ord = inn.nextLine().split(" ");
        while (!ord[0].equals("a")) {

            try {
                int startKol = Integer.parseInt(ord[0]);
                int startRad = Integer.parseInt(ord[1]);

                Liste<String> utveier = l.finnUtveiFra(startKol, startRad);
                if (utveier.stoerrelse() != 0) {
                    for (String s : utveier) {
                        System.out.println(s);
                    }
                } else {
                    System.out.println("Ingen utveier.");
                }
                System.out.println();
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig input!");
            }

            System.out.println("Skriv inn nye koordinater ('a' for aa avslutte)");
            ord = inn.nextLine().split(" ");
        }
    }
}
