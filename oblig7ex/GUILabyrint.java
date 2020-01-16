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


public class GUILabyrint extends Application {
  public static GUIRute[][] RUTER;
  BorderPane groot = new BorderPane();
  GridPane rutenett;
  Stage primaryStage;
  Labyrint l;
  int radIGUI, kolonneIGUI;


  @Override
  public void start(Stage primaryStage){
    this.primaryStage = primaryStage;
    groot.setTop(settTopp());

    Scene scene = new Scene(groot);

    primaryStage.setTitle("Labyrint");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  //Setter programmet slikt det ser ut i starten
  private HBox settTopp(){
    Button hentFil = new Button("Velg en Labyrint fil");

    hentFil.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent e){
        System.out.println("Starter FileChooser");

        //Setter opp en filchooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Velg en Labyrint fil");
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if(selectedFile != null){
          System.out.println(selectedFile.getPath());
          try {
            l = Labyrint.lesFraFil(selectedFile);
            rutenett = lagGridPane(l);
            groot.setCenter(rutenett);
            groot.setTop(settNyTopp());
            primaryStage.sizeToScene();
          } catch (Exception n) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", selectedFile.getPath());
            System.exit(1);
          }

        }else{
          System.out.println("Ingen fil ble valgt");
        }
      }
    });
    return new HBox(hentFil);
  }

  //Setter opp Labyrinten i midten av programmet
  private GridPane lagGridPane(Labyrint l){
    GridPane nett = new GridPane();
    RUTER = new GUIRute[l.giRad()][l.giKol()];

    for (int rad  = 0; rad < l.giRad(); rad++){
      for (int kol = 0; kol < l.giKol(); kol++){
        RUTER[rad][kol] = new GUIRute(l, kol, rad);
        nett.add(RUTER[rad][kol], kol, rad);
      }
    }
    return nett;
  }

  //Nar det er en apnet labyrint sa blir toppen satt til dette
  private HBox settNyTopp(){
    Button hentFil = new Button("Bytt Labyrint");

    hentFil.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent e){
        System.out.println("Starter FileChooser");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Velg en Labyrint fil");
        fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("Labyrint Filer", "*.in"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if(selectedFile != null){
          System.out.println(selectedFile.getPath());
          try {
            l = Labyrint.lesFraFil(selectedFile);
            rutenett = lagGridPane(l);
            groot.setCenter(rutenett);
            groot.setTop(settNyTopp());
            primaryStage.sizeToScene();
          } catch (Exception n) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", selectedFile.getPath());
            System.exit(1);
          }

        }else{
          System.out.println("Ingen fil ble valgt");
        }
      }
    });

    Button resett = new Button("Resett");
      resett.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e){
          GUIRute.RESETT();
        }
      });

      double buffer = 0;
      if (l.giRad() < 6) buffer = l.giRad()*20;
      else if (l.giRad() < 20) buffer = l.giRad()*36;
      else if (l.giRad() < 30) buffer = l.giRad()*22.3;
      else buffer = l.giRad()*5.5;

    return new HBox(buffer, hentFil, resett);
  }
}
