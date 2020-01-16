class mainStudent{
  public static void main(String[] args) {
    Student kristian = new Student("Kristian", 0, 0);
    System.out.println(kristian.getName());
    System.out.println(kristian.hentTotalScore());
    kristian.leggTilQuizScore(30);
    kristian.leggTilQuizScore(20);
    System.out.println(kristian.hentTotalScore());
    System.out.println(kristian.hentGjennomsnittligScore());

  }
}
