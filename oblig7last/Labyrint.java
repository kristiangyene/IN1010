import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

class Labyrint{
  protected static int antallRader;
  protected static int antallKolonner;
  public static Rute[][] charArr;
  public static Lenkeliste<String> losninger = new Lenkeliste<String>();

  private Labyrint(int kolonner, int rader, Rute[][] charArr){
    antallRader = rader;
    antallKolonner = kolonner;
    this.charArr = charArr;
  }
  public Rute hentRute(int rad, int kol){
    return charArr[rad][kol];
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

  static Lenkeliste<String> finnUtveiFra(int kolonne, int rad){

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
  public Rute[][] hentCharArr(){
    return charArr;
  }
}
