class Main{
  public static void main(String[] args) {
    Postkontor postkontor = new Postkontor(100);
    Thread postmann = new Thread(new Postmann(postkontor));
    postmann.start();
    Thread kunde = new Thread(new Kunde("Lily", postkontor));
    kunde.start();
  }
}
