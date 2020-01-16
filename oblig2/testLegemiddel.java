class testLegemiddel{
  public static void main(String[] args) {
    LegemiddelA legemiddel1 = new LegemiddelA("Pille1", 300.47, 21.2, 2);
    System.out.println("Informasjon om legemiddel1");
    System.out.println("Navn: " + legemiddel1.hentNavn());
    System.out.println("Narkotisk styrke: " + legemiddel1.hentNarkotiskStyrke());
    System.out.println("Pris: " + legemiddel1.hentPris());
    legemiddel1.settNyPris(321.2);
    System.out.println("Ny pris: " + legemiddel1.hentPris());
    System.out.println("Virkestoff: " + legemiddel1.hentVirkestoff());
    System.out.println("Id: " + legemiddel1.hentId());
    System.out.println();

    LegemiddelB legemiddel2 = new LegemiddelB("Pille2", 150.32, 11.3, 4);
    System.out.println("Informasjon om Legemiddel2");
    System.out.println("Navn: " + legemiddel2.hentNavn());
    System.out.println("Vanedannende styrke: " + legemiddel2.hentVanedannendeStyrke());
    System.out.println("Pris: " + legemiddel2.hentPris());
    legemiddel2.settNyPris(203.1);
    System.out.println("Ny pris: " + legemiddel2.hentPris());
    System.out.println("Virkestoff: " + legemiddel2.hentVirkestoff());
    System.out.println("Id: " + legemiddel2.hentId());
    System.out.println();

    LegemiddelC legemiddel3 = new LegemiddelC("Pille3", 119.9, 2.32);
    System.out.println("Informasjon om Legemiddel3");
    System.out.println("Navn: " + legemiddel3.hentNavn());
    System.out.println("Pris: " + legemiddel3.hentPris());
    legemiddel3.settNyPris(110.12);
    System.out.println("Ny pris: " + legemiddel3.hentPris());
    System.out.println("Virkestoff: " + legemiddel3.hentVirkestoff());
    System.out.println("Id: " + legemiddel3.hentId());
  }
}
