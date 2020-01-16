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




public class GammelGUI extends Application{
  public Labyrint lab;
  public int guiRader;
  public int guiKolonner;
  public GUIRute[][] ruter;
  public GridPane rutenett;
  public Stage stage;

  @Override
  public void start(Stage stage){
    FileChooser fileChooser = new FileChooser();
    BorderPane node = new BorderPane();
    GridPane rutenett = new GridPane();
    node.setCenter(rutenett);
    GUIRute[][] ruter = new GUIRute[guiRader][guiKolonner];
    for (int rad = 0; rad < guiRader; rad++) {
        for (int kol = 0; kol < guiKolonner; kol++) {
            ruter[rad][kol] = new GUIRute();
            rutenett.add(ruter[rad][kol], kol, rad);
      }
    }
    Button clearKnapp = new Button("fjern");
    Button valgKnapp = new Button("Velg fil...");
    Button avsluttKnapp = new Button("Avslutt");
    avsluttKnapp.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent actionevent){
        System.exit(0);
      }
    });
    valgKnapp.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent actionevent){
        File fil = fileChooser.showOpenDialog(stage);
        if (fil != null){
          try {
              lab = Labyrint.lesFraFil(fil);
              guiRader = lab.antallRader;
              guiKolonner = lab.antallKolonner;


          } catch (Exception f) {
              System.out.printf("FEIL: Kunne ikke lese fra filen");
              System.exit(1);
          }
          for (int rad = 0; rad < guiRader; rad++) {
              for (int kol = 0; kol < guiKolonner; kol++) {
                  ruter[rad][kol] = new GUIRute();
                  rutenett.add(ruter[rad][kol], kol, rad);
            }
          }
        }
      }
    });
    node.setTop(valgKnapp);
    node.setBottom(avsluttKnapp);
    stage.setScene(new Scene(node));
    stage.setTitle("PYRAMIDE GUI");
    stage.show();
  }
  public class GUIRute extends Pane{
    public boolean erHvit = true;
    public GUIRute(){
      setPrefHeight(70);
      setPrefWidth(70);
      this.setBorder(new Border(
        new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))
      ));
      byttFarge();
      addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
        public void handle(MouseEvent mouseevent){
          erHvit = false;
          byttFarge();
        }
      });
    }
    private void byttFarge() {
        if (erHvit) {
            setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        } else {
            setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        }
    }
    @Override
    public String toString() {
        /*if (erHvit) {
            return ".";
        } else {
            return "#";
        }*/
        return erHvit ? "." : "#";
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
}
