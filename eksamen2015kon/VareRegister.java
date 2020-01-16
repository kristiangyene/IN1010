import java.util.ArrayList;
public class VareRegister{
  ArrayList<Vare> beholder = new ArrayList<Vare>();
  public String leie(String vareNavn){
    for(Vare vare: beholder){
      if(!(vare.hentNavn().equals(vareNavn))){
        return "Beklager, finnes ikke.";
      }
      else if(!(vare instanceof MaskinTilUtleie) || !(vare instanceof VerktoyTilUtleie)){
        return "Varen kan ikke leies ut.";
      }
      else{
        if(!vare.ledig){
          return "Beklager, allerede utleid.";
        }
        vare.ledig = false;
        return "Vær så god, varen er din til leie";
      }
    }
    return "Gått igjennom alle varer.";
  }
  public String levereTilbake(String vareNavn){
    for(Vare vare: beholder){
      if(!(vare.hentNavn().equals(vareNavn))){
        return "Denne varen finnes ikke.";
      }
      else if(!(vare instanceof MaskinTilUtleie) || !(vare instanceof VerktoyTilUtleie) || (vare.ledig)){
        return "Beklager, denne er ikke utleid.";
      }
      else{
        vare.ledig = true;
        return "Takk for at du leverte tilbake.";
      }
    }
    return "Den er nå levert tilbake.";
  }
}
