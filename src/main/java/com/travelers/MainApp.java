package com.travelers;

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
    
    @Override
    public void stop() {
        FXMLController.locDAO.close();
        FXMLController.personDAO.close();
        shutDownDatabase();
        System.out.println("#---Close Program---#");
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void startDatabase() throws SQLException {
        new Server().runTool("-tcp", "-web", "-ifNotExists");
    }

    private void shutDownDatabase() {
        new Server().shutdown();
    }
}
