public class Student{
  String navn;
  int score;
  int antallQ;

  public Student(String navn, int score, int antallQ){
    this.navn = navn;
    this.score = score;
    this.antallQ = antallQ;
  }
  public String getName(){
    return navn;
  }
  public int hentTotalScore(){
    return score;
  }
  public void leggTilQuizScore(int score){
    this.score += score;
    antallQ++;
  }
  public double hentGjennomsnittligScore(){
    return score/antallQ;
  }
}
