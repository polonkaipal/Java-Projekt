package com.travelers;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;
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
        
        /*Location location1 = new Location("London", 12.345, 34.4567, 23.45, "C:\\Users\\veres\\Downloads\\balaton.jpg", "egy");
        Location location2 = new Location("Firenze", 12.345, 34.4567, 23.45, "C:\\Users\\veres\\Downloads\\balaton.jpg", "kettő");
        Location location3 = new Location("Párizs", 12.345, 34.4567, 23.45, "C:\\Users\\veres\\Downloads\\balaton.jpg", "három");
        Location location4 = new Location("Róma", 12.345, 34.4567, 23.45, "C:\\Users\\veres\\Downloads\\balaton.jpg", "négy");
        Location location5 = new Location("Debrecen", 12.345, 34.4567, 23.45, "C:\\Users\\veres\\Downloads\\balaton.jpg", "öt");
        Person p1 = new Person();
        p1.setName("Malacka");
        p1.setDateOfBirth(LocalDate.of(1999, Month.MARCH, 12));
        p1.addLocation(location1);
        
        Person p2 = new Person();
        p2.setName("Tigris");
        p2.setDateOfBirth(LocalDate.of(1999, Month.MARCH, 12));
        p2.addLocation(location2);
        p2.addLocation(location3);
        
        Person p3 = new Person();
        p3.setName("Micimackó");
        p3.setDateOfBirth(LocalDate.of(1999, Month.MARCH, 12));
        p3.addLocation(location4);
        p3.addLocation(location5);
    
        try (LocationDAO locDAO = new JpaLocationDAO(); PersonDAO personDAO = new JpaPersonDAO()) {
                locDAO.saveLocation(location1);
                locDAO.saveLocation(location2);
                locDAO.saveLocation(location3);
                locDAO.saveLocation(location4);
                locDAO.saveLocation(location5);
                personDAO.savePerson(p1);
                personDAO.savePerson(p2);
                personDAO.savePerson(p3);
                System.out.println("press enter...");
                (new Scanner(System.in)).nextLine();
                //personDAO.deletePerson(p2);
                //locDAO.deleteLocation(location2);
                //locDAO.deleteLocation(location3);
        }*/
        
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
