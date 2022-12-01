import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("2048.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()){
                    case W:
                        controller.Up();
                        break;
                    case S:
                        controller.Down();
                        break;
                    case A:
                        controller.Left();
                        break;
                    case D:
                        controller.Right();
                        break;
                }
            }
        });
        stage.setScene(scene);
        stage.setTitle("2048");
        stage.setScene(scene);
        stage.show();
    }
}
