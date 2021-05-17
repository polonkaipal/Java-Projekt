package hu.unideb.inf;

import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        startDatabase();
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        FXMLController fxmlController = fxmlLoader.getController();
        Datas.fxmlController = fxmlController;
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("Travellers");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private static void startDatabase() throws SQLException {
        new Server().runTool("-tcp", "-web", "-ifNotExists");
    }
}
