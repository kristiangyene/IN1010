import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import java.io.File;
import javafx.scene.paint.Color;
import javafx.scene.layout.Border;
import javafx.scene.input.MouseEvent;

//Designer rute-objekter.
public class GUIRute extends Pane{
  public Rute[][] charArr;
  public boolean erHvit;
  public static Labyrint l;
  public int rad;
  public int kol;


  public GUIRute(Labyrint l, int kol, int rad){
    this.l = l;
    this.rad = rad;
    this.kol = kol;
    Rute rute = l.hentRute(rad, kol);
    charArr = l.hentCharArr();
    //Stoerrelse på rutene.
    if (l.antallRader < 30){
    setPrefHeight(30);
    setPrefWidth(30);
  }
  else{
    setPrefHeight(12);
    setPrefWidth(12);
  }
  if(charArr[kol][rad].tilTegn() == '#'){
    setStyle("-fx-background-color: black;");
    erHvit = false;
  }else{
    erHvit = true;
  }
    this.setBorder(new Border(
      new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))
    ));

    addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
      public void handle(MouseEvent mouseevent){
        reset();
        if(erHvit){
          setStyle("-fx-background-color: gold;");
          finnLosning(rute.rad, rute.kolonne);
          }
      }
    });
  }
  //Finner løsning.
  public void finnLosning(int rad, int kol){
    Liste<String> utveier = l.finnUtveiFra(kol, rad);
    while(utveier.stoerrelse() != 0){
    String vei = utveier.fjern();
    boolean[][] losningerBool = losningStringTilTabell(vei, l.antallRader, l.antallKolonner);
    for(int i = 0; i < l.antallKolonner; i++){
      for(int j = 0; j < l.antallRader; j++){
        if(losningerBool[i][j]){
          GUI.listeRute[j][i]. setStyle("-fx-background-color: gold;");
          }
        }
      }
    }
  }

//Ferdig utgitt metode.
static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
  boolean[][] losning = new boolean[hoyde][bredde];
  java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
  java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
  while (m.find()) {
      int x = Integer.parseInt(m.group(1))-1;
      int y = Integer.parseInt(m.group(2))-1;
      losning[y][x] = true;
    }
    return losning;
  }

  //Resetter fargede ruter.
  public static void reset(){
    for(int i = 0; i < l.antallKolonner; i++){
      for(int j = 0; j < l.antallRader; j++){
        if(GUI.listeRute[j][i].erHvit){
          GUI.listeRute[j][i].setStyle("-fx-background-color: white;");
        }
      }
    }
  }
}
