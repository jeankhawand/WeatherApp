import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main method extends Application from JAVA FX and load the GUI
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
            primaryStage.setTitle("Project");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();



    }


    public static void main(String[] args) {
        // launch is a constractor of Application class
        launch(args);
    }
}
