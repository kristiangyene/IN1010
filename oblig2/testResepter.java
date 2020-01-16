class testResepter{
  public static void main(String[] args) {
    LegemiddelA legemiddel1 = new LegemiddelA("pille1", 300.47, 21.2, 2);
    LegemiddelB legemiddel2 = new LegemiddelB("pille2", 200.32, 11.3, 4);
    LegemiddelC legemiddel3 = new LegemiddelC("pille3", 119.9, 2.32);
    Lege lege1 = new Lege("Ano");

    Militarresept militar = new Militarresept(legemiddel1, lege1, 3, 4);
    System.out.println("Informasjon om militarresepten");
    System.out.println("Farge: " +  militar.farge());
    System.out.println("Pris: " + militar.prisAaBetale());
    System.out.println("Lege: " + militar.hentLege().hentNavn());
    System.out.println("Pasient id: " + militar.hentId());
    System.out.println("Om det er noe igjen: " + militar.bruk());
    System.out.println("Antall reit igjen: " + militar.hentReit());
    System.out.println();

    Presept presept = new Presept(legemiddel2, lege1, 4, 0);
    System.out.println("Informasjon om p-resepten");
    System.out.println("Farge: " +  presept.farge());
    System.out.println("Pris: " + presept.prisAaBetale());
    System.out.println("Lege: " + presept.hentLege().hentNavn());
    System.out.println("Pasient id: " + presept.hentPasientId());
    System.out.println("Antall reit igjen: " + presept.hentReit());
    System.out.println("(Bruker resepten én gang)");
    presept.bruk();
    System.out.println("Antall reit igjen: " + presept.hentReit());
    System.out.println();

    HvitResept hvit = new HvitResept(legemiddel3, lege1, 5, 0);
    System.out.println("Informasjon om den hvite resepten");
    System.out.println("Farge: " +  hvit.farge());
    System.out.println("Pris: " + hvit.prisAaBetale());
    System.out.println("Lege: " + hvit.hentLege().hentNavn());
    System.out.println("Pasient id: " + hvit.hentPasientId());
    System.out.println("Antall reit igjen: " + hvit.hentReit());
    System.out.println("(Bruker resepten én gang)");
    hvit.bruk();
    System.out.println("Antall reit igjen: " + hvit.hentReit());
    System.out.println();

    BlaaResept blaa = new BlaaResept(legemiddel1, lege1, 6, 10);
    System.out.println("Informasjon om den blaa resepten");
    System.out.println("Farge: " +  blaa.farge());
    System.out.println("Pris: " + blaa.prisAaBetale());
    System.out.println("Lege: " + blaa.hentLege().hentNavn());
    System.out.println("Pasient id: " + blaa.hentPasientId());
    System.out.println("Antall reit igjen: " + blaa.hentReit());
    System.out.println("(Bruker resepten én gang)");
    blaa.bruk();
    System.out.println("Antall reit igjen: " + blaa.hentReit());
  }
}
