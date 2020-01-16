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


public class LagLabyrint extends Application{
  public Labyrint lab;
  public int guiRader;
  public int guiKolonner;
  public static GUIRute[][] ruter;
  public GridPane rutenett;
  public Stage stage;
  public BorderPane groot = new BorderPane();

public static void main(String[] args) {
  Application.launch(args);
}

  @Override
  public void start(Stage stage){
    this.stage = stage;
    groot.setTop(startTopp());
    Scene scene = new Scene(groot);
    stage.setTitle("LABYRINT");
    stage.setScene(scene);
    stage.show();



  }
  public class GUIRute extends Pane{
    public boolean erHvit = true;
    public GUIRute(Labyrint l, int kol, int rad){
      setPrefHeight(70);
      setPrefWidth(70);
      this.setBorder(new Border(
        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))
      ));

      addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
        public void handle(MouseEvent mouseevent){
          erHvit = false;

        }
      });
    }
    public void reset(){
      return;
    }
  }
  static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
    boolean[][] losning = new boolean[hoyde][bredde];
    java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
    java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
    while (m.find()) {
        int x = Integer.parseInt(m.group(1));
        int y = Integer.parseInt(m.group(2));
        losning[y][x] = true;
      }
      return losning;
    }
  public HBox startTopp(){
    Button lastFil = new Button("Velg fil...");
    lastFil.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        System.out.println("Kjører FileChooser");

        //Lar bruker velge fil.
        FileChooser filVelger = new FileChooser();
        filVelger.setTitle("VELG EN LABYRINT");
        File valgtFil = filVelger.showOpenDialog(stage);

        if (valgtFil != null){
          try{
            lab = Labyrint.lesFraFil(valgtFil);
            rutenett = gridPane(lab);
            groot.setCenter(rutenett);
            stage.sizeToScene();
          } catch(Exception d){
            System.out.println("Feil, kunne ikke lese fra valgt fil.");
            System.exit(0);
          }
        }else{
          System.out.println("Ingen fil ble valgt.");
        }
      }
    });
    return new HBox(lastFil);
  }
  private GridPane gridPane(Labyrint lab){
    GridPane lagNett = new GridPane();
    ruter = new GUIRute[lab.antallRader][lab.antallKolonner];
    for(int rad = 0; rad < lab.antallRader; rad++){
      for(int kol = 0; kol < lab.antallKolonner; kol++){
        ruter[rad][kol] = new GUIRute(lab, kol, rad);
        lagNett.add(ruter[rad][kol], kol, rad);
      }
    }
    return lagNett;
  }
  public HBox nyTopp(){
    Button lastFil = new Button("Bytt labyrint...");
    lastFil.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent a){
        System.out.println("Kjører FileChooser");
        FileChooser filVelger = new FileChooser();
        filVelger.setTitle("VELG EN LABYRINT");
        filVelger.getExtensionFilters().addAll(
        new ExtensionFilter("Labyrintfiler", "*.in"));
        File valgtFil = filVelger.showOpenDialog(stage);
        if(valgtFil != null){
          try{
            lab = Labyrint.lesFraFil(valgtFil);
            rutenett = gridPane(lab);
            groot.setCenter(rutenett);
            groot.setTop(nyTopp());
            stage.sizeToScene();
          } catch(Exception e){
            System.out.println("Feil, kunne ikke lese fra valgt fil.");
            System.exit(0);
          }
        }else{
          System.out.println("Ingen fil ble valgt.");
        }
      }
    });
    Button reset = new Button("Reset");
    reset.setOnAction(new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent i){
        startTopp();
      }
    });
    double tall = 0;
    if(lab.antallRader < 6) tall = lab.antallRader*20;
    else if(lab.antallRader < 20) tall = lab.antallRader*36;
    else if(lab.antallRader < 30) tall = lab.antallRader*22.3;
    else tall = lab.antallRader*5.5;
    return new HBox(tall, lastFil, reset);

  }
}
