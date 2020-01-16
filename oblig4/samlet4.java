public abstract class Legemiddel{
  private String navn;
  private double pris;
  private static int count = 0;
  private int legemiddelId = 0;
  private double virkestoff;

  public Legemiddel(String nyttNavn, double nyPris, double nyttVirkestoff){
    navn = nyttNavn;
    pris = nyPris;
    virkestoff = nyttVirkestoff;
    legemiddelId = count++;
  }
  public int hentId(){
    return legemiddelId;
  }
  public String hentNavn(){
    return navn;
  }
  public double hentPris(){
    return pris;
  }
  public double hentVirkestoff(){
    return virkestoff;
  }
  public void settNyPris(double nyPris){
    pris = nyPris;
  }
}

class LegemiddelA extends Legemiddel{
  //narkotisk
  protected int styrke;
  public LegemiddelA(String nyttNavn, double nyPris, double nyttVirkestoff, int nyStyrke){
    super(nyttNavn, nyPris, nyttVirkestoff);
    styrke = nyStyrke;
  }
  public int hentNarkotiskStyrke(){
    return styrke;
  }
}

class LegemiddelB extends Legemiddel{
  //vanedannende
  protected int styrke;
  public LegemiddelB(String nyttNavn, double nyPris, double nyttVirkestoff, int nyStyrke){
    super(nyttNavn, nyPris, nyttVirkestoff);
    styrke = nyStyrke;
  }
  public int hentVanedannendeStyrke(){
    return styrke;
  }
}

class LegemiddelC extends Legemiddel{
  //Har ingen tilleggsegenskaper.
  public LegemiddelC(String nyttNavn, double nyPris, double nyttVirkestoff){
    super(nyttNavn, nyPris, nyttVirkestoff);
  }
}

public class Pasient{
  private String navn;
  private String fodselsnummer;
  private static int count = 0;
  private int pasientID = 0;
  Stabel<Resept> resepter = new Stabel<Resept>();

  public Pasient(String navn, String fodselsnummer){
    this.navn = navn;
    this.fodselsnummer = fodselsnummer;
    pasientID = count++;
  }

  public void leggTilResept(Resept resept){
    resepter.leggPaa(resept);
  }
  public Stabel<Resept> hentResepter(){
    return resepter;
  }
  public String hentFodselsnummer(){
    return fodselsnummer;
  }
  public String hentNavn(){
    return navn;
  }
  public int hentId(){
    return pasientID;
  }
}


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


class BlaaResept extends Resept{
  protected String typeResept = "Blaa";
  public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient p, int reit){
    super(legemiddel, utskrivendeLege, p, reit);
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


public class Lege implements Comparable<Lege>{
  String navn;
  int avtalenummer = 0;
  Lenkeliste<Resept> resepter = new Lenkeliste<Resept>();

  public Lege(String legeNavn){
    navn = legeNavn;
  }
  public String hentNavn(){
    return navn;
  }
  //Må gjøres om
  public int compareTo(Lege x){
    return navn.compareTo(x.hentNavn());
  }

  public Lenkeliste<Resept> hentResepter(){
    return resepter;
  }
  public void leggTil(Resept resept){
    resepter.leggTil(resept);
  }
  public int hentAvtalenummer(){
    return avtalenummer;
  }
}
class Fastlege extends Lege implements kommuneavtale{
  protected int avtalenummer;
  public Fastlege(String legeNavn, int avtalenummer){
    super(legeNavn);
    this.avtalenummer = avtalenummer;
  }
  @Override
  public int hentAvtalenummer(){
    return avtalenummer;
  }
}

interface kommuneavtale{
  public int hentAvtalenummer();
}


class Legesystem{
  static boolean systemOnline = true;
  static Lenkeliste<Pasient> pasienter = new Lenkeliste<Pasient>();
  static Lenkeliste<Legemiddel> legemidler = new Lenkeliste<Legemiddel>();
  static Lenkeliste<Resept> resepter = new Lenkeliste<Resept>();
  static SortertLenkeliste<Lege> leger = new SortertLenkeliste<Lege>();

  public static void main(String[] args) {
  Legesystem legesystem = new Legesystem();
  while (systemOnline){
    legesystem.menu();
    }
  }

public void menu(){
  int valg;
  System.out.println("***Hovedmeny***");
  System.out.println("Alternativer:\n1: Printe ut oversikt\n2: Legge til nye elementer\n3: Bruke resept\n4: Avslutt");
  Legesystem.skrivStatistikk();
  System.out.println("Velg et alternativ");
  valg = parserInt();
  switch (valg) {
    //skriv ut alt.
    case 1:
      Legesystem.skrivUtSystem();
      break;
    //Legg til/opprett nye elementer.
    case 2:
      Legesystem.endreSystem();
      break;
    //Bruke en resept.
    case 3:
      Legesystem.brukResept();
      break;
    //Skrive ut statistikk.
    case 4:
      systemOnline = false;
      break;
    default:
    System.out.println("Ugyldig operasjon.");
    }
  }

  private static void skrivUtSystem(){
    System.out.println("***Oversikt i systemet***\nPasienter:");
    //Printer pasienter.
    for (Pasient pasient: pasienter){
      System.out.println(pasient.hentNavn() + "(ID: " + pasient.hentId() + ")");
    }
    //Printer leger.
    System.out.println("Leger:");
    for (Lege lege: leger){
      System.out.println(lege.hentNavn() + "(avtnr: " + lege.hentAvtalenummer() + ")");
    }
    //Printer legemidler.
    System.out.println("Legemidler:");
    for (Legemiddel legemiddel: legemidler) {
      System.out.println(legemiddel.hentNavn() + "(ID: " + legemiddel.hentId() + ")");
    }

    System.out.println();
    //Printer oversikt over resepter(informasjon).
    System.out.println("Resepter:");
    for (Resept resept: resepter){
      System.out.println("ID: " + resept.hentId());
      System.out.println("Pasient: " + resept.hentPasient().hentNavn());
      System.out.println("Lege: " + resept.hentLege().hentNavn());
      System.out.println("Legemiddel: " + resept.hentLegemiddel().hentNavn());
      System.out.println("Antall reit: " + resept.hentReit());
      System.out.println();
    }
  }

  private static void endreSystem(){
    int valg;
    System.out.println("Alternativer:\n1: Legg til lege\n2: Legg til pasient\n3: legg til resept\n4: legg til legemiddel\n5: Tilbake til meny\n>");
    valg = parserInt();

    switch (valg) {
      //Legger til lege.
      case 1:
        Legesystem.leggTilLege();
        break;
      //Legger til pasient.
      case 2:
        Legesystem.leggTilPasient();
        break;
      //Legger til resept.
      case 3:
        Legesystem.leggTilResept();
        break;
      //Legger til legemiddel.
      case 4:
        Legesystem.leggTilLegemiddel();
        break;
        //Til meny.
      case 5:
        break;
      default:
      System.out.println("Ugyldig operasjon.");
    }
  }

  private static void brukResept(){
    int valg;
    System.out.println("Hvilken pasient vil du se resepter for?");
    //Henter valgt pasient.
    for (Pasient pasient: pasienter){
      int teller = 1;
      System.out.println(teller + ": " + pasient.hentNavn() + "(" + pasient.hentFodselsnummer() + ")");
      teller ++;
    }

    valg = parserInt();
    System.out.println();
    Pasient valgtPasient = pasienter.hent(valg-1);
    System.out.println("Valgt pasient: " + valgtPasient.hentNavn() + "(fnr " + valgtPasient.hentFodselsnummer() + ")");
    Stabel<Resept> pasientResepter = valgtPasient.hentResepter();
    System.out.println("Hvilken resept vil du bruke?");
    //Henter reseptene til valgt pasient.
    for (Resept resept: resepter){
      int teller = 1;
      System.out.println(teller + ": " + resept.hentLegemiddel().hentNavn() + " (" + resept.hentReit() + "reit)");
      teller ++;
    }

    valg = parserInt();
    Resept valgtResept = pasientResepter.hent(valg-1);
    //Bruker resepten om mulig.
    boolean kanBrukes = valgtResept.bruk();
    if (kanBrukes){
      System.out.println("Brukte resept paa " + valgtResept.hentLegemiddel().hentNavn() + ". Antall gjenvaerende reit: " + valgtResept.hentReit());
    }
    else{
      System.out.println("Kunne ikke bruke resept paa " + valgtResept.hentLegemiddel().hentNavn() + " (ingen gjenvaerende reit).");
    }
  }

  private static void skrivStatistikk(){
    int antallLegemidler = 0;
    int antallVanedannede = 0;
    int antallVanedannedeMil = 0;
    int antallNarkotiskLege = 0;
    int antallNarkotiskPas = 0;
    System.out.println();
    System.out.println("Statistikk");
    //Instanceof.
    //Sjekker om legemiddelet er vanedannende.
    for(Resept resept: resepter){
      antallLegemidler++;
      Legemiddel legemiddel = resept.hentLegemiddel();
      if (legemiddel instanceof LegemiddelB){
        antallVanedannede++;
      if (resept instanceof Militarresept && legemiddel instanceof LegemiddelB){
        antallVanedannedeMil++;
        }
      }
    }

    System.out.println("Mulig misbruk av narkotiske stoffer:");
    for (Lege lege: leger){
      Lenkeliste<Resept> valgtResepter = lege.hentResepter();
      for (Resept resept: valgtResepter){
        if (resept.hentLegemiddel() instanceof LegemiddelA){
          antallNarkotiskLege++;
        }
      }
      System.out.println("Legenavn: " + lege.hentNavn() + "(antall narkotiske stoffer: " + antallNarkotiskLege + ")");
    }
    for (Pasient pasient: pasienter){
      Lenkeliste<Resept> valgtResepter = pasient.hentResepter();
      for (Resept resept: valgtResepter){
      if (resept.hentLegemiddel() instanceof LegemiddelA){
        antallNarkotiskPas++;
      }
    }
    System.out.println("Pasient med narkotisk stoff: " + pasient.hentNavn() + "(antall narkotiske stoffer: " + antallNarkotiskLege + ")" );
  }



    System.out.println("Antall vanedannende legemidler: " + antallVanedannede + "/" + antallLegemidler);
    System.out.println("Antall vanedannede til militar: " + antallVanedannedeMil + "/" + antallLegemidler);
  }

  private static void leggTilLege(){
    int valg;
    //Legger til lege.
    System.out.println("Legge til lege eller fastlege?\n1: Lege\n2: Fastlege\n3: Meny\n>");
    valg = parserInt();
    System.out.println("Navn:\n>");
    String navn = innleser();
    if (valg == 1){
    leger.leggTil(new Lege(navn));
  }

  else if (valg == 2){
    //Legger til fastlege.
    System.out.println(("Avtalenummer:\n>"));
    int avtalenummer = parserInt();
    leger.leggTil(new Fastlege(navn, avtalenummer));
    }

    else{
      System.out.println("Ugyldig operasjon.");
    }
  }

  private static void leggTilPasient(){
    //Legger til pasient.
    System.out.println("Legge til pasient");
    System.out.println("Navn:\n>");
    String navn = innleser();
    System.out.println("Fodselsnummer\n>");
    String fodselsnummer = innleser();
    pasienter.leggTil(new Pasient(navn, fodselsnummer));
  }

  private static void leggTilResept(){
    int teller;
    int valg;
    int valg2;
    System.out.println("Hvilken type resept?\n1: Hvit resept\n2: P-resept\n3: Militarresept\n4: Blaa Resept\n>");
    valg = parserInt();
    if (leger.stoerrelse() != 0 && legemidler.stoerrelse() != 0 && pasienter.stoerrelse() != 0){
      teller = 1;
      System.out.println("Hvilket legemiddel onsker du resept for?\n");
      for (Legemiddel legemiddel: legemidler) {
        System.out.println(teller + ": " + legemiddel.hentNavn());
        teller++;
      }
      valg2 = parserInt();
      Legemiddel valgtLegemiddel = legemidler.hent(valg2-1);
      teller = 1;
      System.out.println("Hvem skal resepten vaere for?\n");
      for (Pasient pasient: pasienter){
        System.out.println(teller + ": " + pasient.hentNavn());
        teller++;
      }
      valg2 = parserInt();
      Pasient valgtPasient = pasienter.hent(valg2-1);
      teller = 1;
      System.out.println("Hvem er uskrivende lege?\n");
      for (Lege lege: leger){
        System.out.println(teller + ": " + lege.hentNavn());
        teller++;
      }
      valg2 = parserInt();
      Lege valgtLege = leger.hent(valg2-1);
      System.out.println("Hvor mange reit?\n>");
      valg2 = parserInt();
      int reit = valg2;

      if (valg == 1){
        //Legger til hvit resept.
        HvitResept nyResept = new HvitResept(valgtLegemiddel, valgtLege, valgtPasient, reit);
        resepter.leggTil(nyResept);
        valgtPasient.leggTilResept(nyResept);
        valgtLege.leggTil(nyResept);

      }
      else if (valg == 2){
        //Legger til p-resept.
        Presept nyResept = new Presept(valgtLegemiddel, valgtLege, valgtPasient, reit);
        resepter.leggTil(nyResept);
        valgtPasient.leggTilResept(nyResept);
        valgtLege.leggTil(nyResept);
      }
      else if (valg == 3){
        //Legger til militarresept.
        Militarresept nyResept = new Militarresept(valgtLegemiddel, valgtLege, valgtPasient, reit);
        resepter.leggTil(nyResept);
        valgtPasient.leggTilResept(nyResept);
        valgtLege.leggTil(nyResept);
      }
      else if (valg == 4){
        //Legger til blaa resept.
        BlaaResept nyResept = new BlaaResept(valgtLegemiddel, valgtLege, valgtPasient, reit);
        resepter.leggTil(nyResept);
        valgtPasient.leggTilResept(nyResept);
        valgtLege.leggTil(nyResept);
      }
      else{
        System.out.println("Ugyldig operasjon.");
      }
    }
    else{
      System.out.println("Kan ikke opprette resept(mangler enkelte objekter).");
      System.out.println("Ønsker du aa legge til objekter?\n1: ja\n2: nei\n>");
      valg = parserInt();
      if (valg == 1){
        Legesystem.endreSystem();
      }
      else{
        System.out.println("Gaar tilbake til meny");
      }
  }
}

  private static void leggTilLegemiddel(){
    int valg;
    System.out.println("Hvilken type legemiddel?\n1: Vanlig\n2: Vanedannende\n3: Narkotisk\n>");
    valg = parserInt();
    System.out.println("Navn\n>");
    String navn = innleser();
    System.out.println("Pris\n>");
    double pris = parserDouble();
    System.out.println("Virkestoff\n>");
    double virkestoff = parserDouble();

    if (valg == 1){
      //Legger til vanlig legemiddel.
      legemidler.leggTil(new LegemiddelC(navn, pris, virkestoff));
    }

    else if (valg == 2){
      //legger til vanedannende legemiddel.
      System.out.println("Styrke\n>");
      int styrke = parserInt();
      legemidler.leggTil(new LegemiddelB(navn, pris, virkestoff, styrke));
    }

    else if (valg == 3){
      //legger til narkotisk legemiddel.
      System.out.println("Styrke\n>");
      int styrke = parserInt();
      legemidler.leggTil(new LegemiddelA(navn, pris, virkestoff, styrke));
    }

    else{
      System.out.println("Ugyldig operasjon.");
    }
  }
  private static int parserInt(){
    boolean bool = true;
    int tall = 0;
    while (bool){
      try{
        tall = Integer.parseInt(innleser());
        bool = false;
      }
      catch(NumberFormatException e){
        System.out.println("Skriv et gyldig tall");
      }
    }
    return tall;
  }
  private static double parserDouble(){
    boolean bool = true;
    double tall = 0;
    while (bool){
      try{
        tall = Double.parseDouble(innleser());
        bool = false;
      }
      catch(NumberFormatException e){
        System.out.println("Skriv et gyldig tall");
      }
    }
    return tall;
  }
  private static String innleser(){
    Scanner scanner = new Scanner(System.in);
    String input = "";
    while(input.equals("")){
      input = scanner.nextLine();
    }
    return input;
  }
}
