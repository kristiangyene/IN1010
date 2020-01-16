import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Labyrint{

  private static Rute[][] brett;
  private static int rad;
  private static int kolonne;
  private static boolean skriv;


  //Siden en Labyrint kun kan lages av fra fil saa er den private.
  private Labyrint(int rad, int kolonne){
    this.rad = rad;
    this.kolonne = kolonne;
    brett = new Rute[rad][kolonne];
  }

  public Rute giRute(int rad, int kol){
    return brett[rad][kol];
  }

  //Setter opp Labyrinten og definerer alle ruter
  public static Labyrint lesFraFil(File filnavn) throws FileNotFoundException{

    try{
      Scanner fil = new Scanner(filnavn);

      //Bestemmer dimensjonene paa Labyrinten.
      String beggeTall = fil.nextLine();
      String[] sArray = beggeTall.split(" ");
      int antallRader = Integer.parseInt(sArray[0]);
      int antallKolonner = Integer.parseInt(sArray[1]);
      Labyrint lab = new Labyrint(antallRader, antallKolonner);

      //Leser inn alle linjene som utgjor laberinten.
      int x = 0;
      while (fil.hasNext()){
        String linje = fil.nextLine();
        String[] linjeStykke = linje.split("");
        int y = 0;
        for(String s : linjeStykke){
          sortHvitEllerAapning(s, x, y, antallRader, antallKolonner);
          y++;
        }
        x++;
      }
      settNaboer(antallRader, antallKolonner);
      return lab;
    }catch (Exception e) {
      e.printStackTrace();
    }
    printLab();
    return null;
  }

  public int giRad(){
    return rad;
  }

  public int giKol(){
    return kolonne;
  }

  //Test for a finne ut om det er en aapning, hvit eller sort rute
  private static void sortHvitEllerAapning(String symbol, int x, int y, int maxX, int maxY){
    if (symbol.equals(".")){
      if (erAapen(x, y, maxX, maxY)){
        brett[x][y] = new Aapning(x, y);
      } else {
        brett[x][y] = new HvitRute(x, y);
      }
    } else if (symbol.equals("#")){
      brett[x][y] = new SortRute(x, y);
    } else {
      System.out.println("Noe er galt: " + symbol);
    }
  }

  public static void settMinimalUtskrift(){
    skriv = false;
  }

  private static void settNaboer(int maxX, int maxY){
    for (int x = 0; x < maxX; x++){
      for (int y = 0; y < maxY; y++){
        Rute nord = (x - 1 < 0) ? null : brett[x-1][y];
        Rute vest = (y - 1 < 0) ? null : brett[x][y-1];
        Rute sor = (x + 1 >= maxX) ? null : brett[x+1][y];
        Rute ost = (y + 1 >= maxY) ? null : brett[x][y+1];
        brett[x][y].setteAlleNaboer(nord, vest, sor, ost);
      }
    }
  }

  private void resettRuter(){
    for (int x = 0; x < rad; x++){
      for (int y = 0; y < kolonne; y++){
        brett[x][y].resett();
      }
    }
  }

  public static void printLab(){
    for (int x = 0; x < rad; x++){
      String linje = "";
      for (int y = 0; y < kolonne; y++){
        linje = linje + brett[x][y].tilTegn();
      }
      System.out.println(linje);
    }
  }


//Lager en OrdnetLenkeliste som skal leses av Obligen
  public OrdnetLenkeliste<String> finnUtveiFra(int k, int r){
    //Hvis skriv er satt til ture, sa far vi ut labyrinten
    if(skriv){printLab();}

      OrdnetLenkeliste<String> losninger = losninger = brett[k][r].finnUtvei();

    //Setter alle rutene tilbake til defalt
    resettRuter();

    return losninger;
    }

    private static boolean erAapen(int x, int y, int maxX, int maxY){
      if (x == 0||y == 0||x == maxX-1||y == maxY-1){
        return true;
      }
      return false;
    }
  }
