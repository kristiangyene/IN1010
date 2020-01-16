import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
public class Sjakkbrett  extends Application{
  @Override
  public void start(Stage stage){
    GridPane gp = new GridPane();
    int teljar = 0;
    for(int i = 0; i<8; i++){
      for(int j = 0; j<8; j++){
        Rectangle r = new Rectangle();
        r.setWidth(50);
        r.setHeight(50);
        if(((i+j) % 2) != 0){
          r.setFill(Color.WHITE);
        }else{
          r.setFill(Color.BLACK);
        }
        gp.add(r, i, j);
      }
    }
    Pane pane = new Pane();
    pane.setPrefSize(400,400);
    pane.getChildren().add(gp);

    Scene scene = new Scene(pane);
    stage.setTitle("Sjakkbrett");
    stage.setScene(scene);
    stage.show();
  }
  public static void main(String[] args){
    Application.launch();
  }
}
