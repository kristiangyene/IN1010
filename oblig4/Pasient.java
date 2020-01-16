import java.util.ArrayList;
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
