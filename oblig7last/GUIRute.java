import javafx.scene.layout.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.layout.Border;
import javafx.scene.input.MouseEvent;

public class GUIRute extends Pane{

  public static Labyrint l;
  private Boolean erHvit;
  private Boolean merket = false;
  private int kolonne, rader;

  public GUIRute(Labyrint l, int kol, int rad){
    this.l = l;
    Rute r = l.hentRute(rad, kol);
    rader = rad;
    kolonne = kol;

    if (r instanceof HvitRute){
      setBackground(new Background(
      new BackgroundFill(Color.WHITE, null, null)));
      erHvit = true;
    } else if (r instanceof SortRute){
      setBackground(new Background(
      new BackgroundFill(Color.BLACK, null, null)));
      erHvit = false;
    }

    setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
    null, new BorderWidths(0.5))));


    //Setter storrelsene til rutene
    if (l.antallRader < 20){
      settStorrelse(50);
    } else if (l.antallRader < 30){
      settStorrelse(30);
    } else{
      settStorrelse(10);
    }


    //Handterer musetrykk og kaller pa losning
    addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
      public void handle(MouseEvent event){
        RESETT();
        if (erHvit){
          if (!merket){

            //Skifter Backgroundsfarge til gronn
            setBackground(new Background(
            new BackgroundFill(Color.GREEN, null, null)));

            /*Setter markert til true, slik at den ikke
            mister fargen hvis jeg beveger musa over*/
            merket = true;

            //Kaller pa metoden finnLosning.
            finnLosning(r.kolonne, r.rad);
          }
        }
      }
    });

    //Nar musa gar over ruter
    addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>(){
      public void handle(MouseEvent event){
        if (erHvit){
          if (merket){
            setBackground(new Background(
            new BackgroundFill(Color.DARKGREEN, null, null)));
          }else{
            setBackground(new Background(
            new BackgroundFill(Color.GRAY, null, null)));
          }
        }
      }
    });

    //Nar musa forlater en rute
    addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
      public void handle(MouseEvent event){
        if (erHvit){
          if (merket){
            setBackground(new Background(
            new BackgroundFill(Color.GREEN, null, null)));
          } else {
            setBackground(new Background(
            new BackgroundFill(Color.WHITE, null, null)));
          }
        }
      }
    });
  }

  //Setter dimensjonene til ruta
  private void settStorrelse(int i){
    setMinWidth(i);
    setMinHeight(i);
  }

  //Finner alle losninger og farger utveiene gronne.
  private void finnLosning(int rad, int kol){
    Liste<String> utveier = l.finnUtveiFra(kol, rad);
    while (utveier.stoerrelse() != 0){
      String s = utveier.fjern();
      boolean[][] bool = losningStringTilTabell(s, l.antallRader, l.antallKolonner);
      for (int x = 0; x < l.antallKolonner; x++){
        for (int y = 0; y < l.antallRader; y++){
          if (bool[x][y]){
            GUILabyrint.RUTER[y][x].setBackground(new Background(
            new BackgroundFill(Color.GREEN, null, null)));
            GUILabyrint.RUTER[y][x].merket = true;
          }
        }
      }
    }
  }

  //Gitt kode
  static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
    boolean[][] losning = new boolean[hoyde][bredde];
    java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
    java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
    while(m.find()) {
      int x = Integer.parseInt(m.group(1))-1;
      int y = Integer.parseInt(m.group(2))-1;
      losning[y][x] = true;
    }
    return losning;
  }

  //Setter alle hvite ruter tilbake til a vere hvite
  public static void RESETT(){
    for (int x = 0; x < l.antallKolonner; x++){
      for (int y = 0; y < l.antallRader; y++){
        if (GUILabyrint.RUTER[y][x].erHvit){
          GUILabyrint.RUTER[y][x].setBackground(new Background(
          new BackgroundFill(Color.WHITE, null, null)));
          GUILabyrint.RUTER[y][x].merket = false;
        }
      }
    }
  }

}
