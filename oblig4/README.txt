hei, har ikke fått jobbet så mye som ønsket med oppgaven av personlige grunner, så
sitter litt fast med å få iteratoren min til å fungere som også gjør at jeg får
følgefeil i Legesystem. bare jeg får til den skal jeg nok klare løse oppgaven,
så lurte på om jeg kan få litt tips til å fikse den.

try {
  x
}
catch(UgyldigListeIndeks e) {
  System.out.println(e);
  System.out.println("Vennligst proev igjen med en gyldig indeks");

  LegemiddelA legemiddel1 = new LegemiddelA("pille1", 300.47, 21.2, 2);
  LegemiddelB legemiddel2 = new LegemiddelB("pille2", 200.32, 11.3, 4);
  legemidler.leggTil(legemiddel1);
  legemidler.leggTil(legemiddel2);
  Lege lege2 = new Lege("Kristian");
  Fastlege lege1 = new Fastlege("Ano", 123456);
  leger.leggTil(lege2);
  leger.leggTil(lege1);
  Pasient kristian = new Pasient("Kristian", "200398");
  pasienter.leggTil(kristian);
  Militarresept militar = new Militarresept(legemiddel1, lege1, kristian, 4);
  Presept presept = new Presept(legemiddel2, lege2, kristian, 0);
  resepter.leggTil(militar);
  resepter.leggTil(presept);
