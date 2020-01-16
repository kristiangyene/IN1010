import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
public final class Startknapper extends Application{
  private Desktop desktop = Desktop.getDesktop();
  public Labyrint l = null;


  @Override
  public void start(Stage stage){
    stage.setTitle("Labyrint filvelger");
    final FileChooser fileChooser = new FileChooser();
    final Button valgKnapp = new Button("Velg fil...");
    final Button avsluttKnapp = new Button("Avslutt");
    valgKnapp.setOnAction(
    new EventHandler<ActionEvent>(){

      @Override
      public void handle(ActionEvent e){
        File fil = fileChooser.showOpenDialog(stage);
        if (fil != null){
          try {
              l = Labyrint.lesFraFil(fil);
              stage.close();
          } catch (FileNotFoundException f) {
              System.out.printf("FEIL: Kunne ikke lese fra filen");
              System.exit(1);
          }
        }
      }
    });

    avsluttKnapp.setOnAction(
    new EventHandler<ActionEvent>(){
      @Override
      public void handle(ActionEvent e){
        System.exit(0);
      }
    });

    final GridPane inputPane = new GridPane();
    inputPane.setConstraints(valgKnapp, 0, 0);
    inputPane.setConstraints(avsluttKnapp, 1, 0);
    inputPane.setHgap(6);
    inputPane.setVgap(6);
    inputPane.getChildren().addAll(valgKnapp, avsluttKnapp);
    final Pane gruppe = new VBox(15);
    gruppe.getChildren().addAll(inputPane);
    gruppe.setPadding(new Insets(15, 15 ,20 ,20));
    stage.setScene(new Scene(gruppe));
    stage.show();
  }

  private void aapneFil(File fil){
    try{
      desktop.open(fil);
    }
    catch(IOException e){
      Logger.getLogger(
      Startknapper.class.getName()).log(Level.SEVERE, null, e);
    }
  }
  public Labyrint hentLabyrint(){
    return l;
  }
  public static void main(String[] args) {
    Application.launch(args);
  }
}
