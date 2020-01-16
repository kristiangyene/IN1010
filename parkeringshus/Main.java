class Main{
  public static void main(String[] args) {
    Bil bil1 = new Bil();
    Bil bil2 = new Bil();
    Bil bil3 = new Bil();
    Motorsykkel moto1 = new Motorsykkel();
    Motorsykkel moto2 = new Motorsykkel();
    Motorsykkel moto3 = new Motorsykkel();
    Parkeringsplass park1 = new Parkeringsplass();
    Parkeringsplass park2 = new Parkeringsplass();
    Parkeringsplass park3 = new Parkeringsplass();
    park1.parker(bil1);
    park2.parker(moto3);
    park3.parker(bil3);
    Parkeringshus hus = new Parkeringshus();
    hus.set1(park1, 0);
    hus.set2(park2, 0);
    hus.set1(park3, 1);

  }
}
