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

public class GUI extends Application{
  public static GUIRute[][] listeRute;
  BorderPane groot = new BorderPane();
  GridPane rutenett;
  Stage stage;
  Labyrint lab;

  @Override
  public void start(Stage stage){
    this.stage = stage;
    groot.setTop(lagTopp());
    Scene scene = new Scene(groot);
    stage.setTitle("Meny");
    stage.setScene(scene);
    stage.show();
  }

  //Lager meny for å velge fil.
  private HBox lagTopp(){
    Button hentFil = new Button("Velg fil...");

    hentFil.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent e){
        System.out.println("Starter FileChooser.");

        //Lar bruker velge fil.
        FileChooser filVelger = new FileChooser();
        filVelger.setTitle("VELG LABYRINT");
        File valgtFil = filVelger.showOpenDialog(stage);

        if(valgtFil != null){
          try {
            lab = Labyrint.lesFraFil(valgtFil);
            rutenett = lagGrid(lab);
            groot.setCenter(rutenett);
            groot.setTop(nyTopp());
            stage.sizeToScene();

          } catch (Exception n) {
            System.out.println(n);
            System.out.printf("Kunne ikke lese fra valgt fil.");
            System.exit(0);
          }
        }else{
          System.out.println("Ingen fil ble valgt.");
        }
      }
    });
    return new HBox(hentFil);
  }

  //Setter opp labyrinten.
  private GridPane lagGrid(Labyrint l){
    GridPane grid = new GridPane();
    listeRute = new GUIRute[l.antallRader][l.antallKolonner];

    for (int rad  = 0; rad < l.antallRader; rad++){
      for (int kol = 0; kol < l.antallKolonner; kol++){
        listeRute[rad][kol] = new GUIRute(l, kol, rad);
        grid.add(listeRute[rad][kol], rad, kol);
      }
    }
    return grid;
  }

  //Lager labyrintvindu.
  private HBox nyTopp(){
    Button hentFil = new Button("Velg fil...");

    hentFil.setOnAction(new EventHandler<ActionEvent>() {

      //Lar bruker velge fil.
      @Override
      public void handle(ActionEvent e){
        System.out.println("Starter FileChooser.");

        FileChooser filVelger = new FileChooser();
        filVelger.setTitle("VELG LABYRINT");
        filVelger.getExtensionFilters().addAll(
        new ExtensionFilter("Labyrinter", "*.in"));
        File valgtFil = filVelger.showOpenDialog(stage);

        if(valgtFil != null){
          try {
            lab = Labyrint.lesFraFil(valgtFil);
            rutenett = lagGrid(lab);
            groot.setCenter(rutenett);
            groot.setTop(nyTopp());
            stage.sizeToScene();

          } catch (Exception n) {
            System.out.println(n);
            System.out.printf("Kunne ikke lese fra valgt fil.");
            System.exit(0);
          }

        }else{
          System.out.println("Ingen fil ble valgt");
        }
      }
    });
    //Lager en avsluttknapp.
    Button avslutt = new Button("Avslutt");
      avslutt.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e){
          System.out.println("Avslutter.");
          System.exit(1);
        }
      });

    return new HBox(lab.antallRader, hentFil, avslutt);
  }


}
