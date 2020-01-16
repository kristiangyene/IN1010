//Oppretter klasse med de forskjellige instansene.
class integrasjonstest{
  public static void main(String[] args) {
    LegemiddelC prevensjon = new LegemiddelC("P-piller", 120.9, 2.32);
    LegemiddelB sobril = new LegemiddelB("Sobril", 154, 4.4, 5);
    LegemiddelA sufen = new LegemiddelA("Sufentanilum", 322.1, 3.2, 8);
    Fastlege thomas = new Fastlege("Thomas", 321154667);
    Lege ane = new Lege("Ane");

    Presept reseptP = new Presept(prevensjon, ane, 22, 0);
    System.out.println("Informasjon om P-resepten");
    System.out.println("Navn på legemiddel: " + reseptP.hentLegemiddel().hentNavn());
    System.out.println("Pasient ID: " + reseptP.hentPasientId());
    System.out.println("Lege som skriver ut: " + reseptP.hentLege().hentNavn());
    System.out.println("Prøver å bruke resepten: " + reseptP.bruk());
    System.out.println("Pris å betale: " + reseptP.prisAaBetale());
    System.out.println("Resept ID: " + reseptP.hentId());
    System.out.println();

    BlaaResept reseptSob = new BlaaResept(sobril, ane, 23, 12);
    System.out.println("Informasjon om Beroligende");
    System.out.println("Navn på legemiddel: " + reseptSob.hentLegemiddel().hentNavn());
    System.out.println("Pasient ID: " + reseptSob.hentPasientId());
    System.out.println("Lege som skriver ut: " + reseptSob.hentLege().hentNavn());
    System.out.println("Prøver å bruke resepten: " + reseptSob.bruk());
    System.out.println("Pris å betale: " + reseptSob.prisAaBetale());
    System.out.println("Resept ID: " + reseptSob.hentId());
    System.out.println();

    Militarresept reseptMil = new Militarresept(sufen, thomas, 21, 9);
    System.out.println("Informasjon om Narkotisk");
    System.out.println("Navn på legemiddel: " + reseptMil.hentLegemiddel().hentNavn());
    System.out.println("Pasient ID: " + reseptMil.hentPasientId());
    System.out.println("Lege som skriver ut: " + reseptMil.hentLege().hentNavn());
    System.out.println("Avtalenummer: " + thomas.hentAvtalenummer());
    System.out.println("Prøver å bruke resepten: " + reseptMil.bruk());
    System.out.println("Pris å betale: " + reseptMil.prisAaBetale());
    System.out.println("Resept ID: " + reseptMil.hentId());
  }
}
