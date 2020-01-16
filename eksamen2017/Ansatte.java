public abstract class Ansatte{
  final String ansattId;
  final String navn;

  public Ansatte(String ansattId, String navn){
    this.ansattId = ansattId;
    this.navn = navn;
  }
}
class Lege extends Ansatte{
  final int legeNummer;
  public Lege(String ansattId, String navn, int legeNummer){
    super(ansattId, navn);
    this.legeNummer = legeNummer;
  }
}
class Overlege extends Lege{
  final String spesType;
  public Overlege(String ansattId, String navn, int legeNummer, String spesType){
    super(ansattId, navn, legeNummer);
    this.spesType = spesType;
  }
}
class AdminOverlege extends Overlege{
  final String ansvarskode;
  public AdminOverlege(String ansattId, String navn, int legeNummer, String spesType, String ansvarskode){
    super(ansattId, navn, legeNummer, spesType);
    this.ansvarskode = ansvarskode;
  }
}
