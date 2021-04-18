package hu.unideb.inf;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.MenuItem;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;

public class FXMLController implements Initializable {
    
    //Main Scene FXML items
    
    @FXML
    private MenuItem addPersonMenuItem;

    @FXML
    private ListView<String> personNameList;

    @FXML
    private ImageView personImage;
    
    @FXML
    private Label dateOfBirthOutput;

    @FXML
    private Label favoritePlaceOutput;

    @FXML
    private Label longitudeOutput;

    @FXML
    private Label latitudeOutput;
    
    @FXML
    private Button detailsViewBtn;

    @FXML
    void addPersonToList(ActionEvent event) {

    }
    
    @FXML
    void detailsViewBtnClick(ActionEvent event) {
        try
        {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PersonDetailsSceneFXML.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
        } catch(IOException e)
        {
            System.out.println("Can't load");
        }
    }
    
    @FXML
    private MenuItem deleteBtn;
    @FXML
    void delete(ActionEvent event) {
        //dateOfBirthOutput.setText("hello");
    }
    
    @FXML
    void addPerson(ActionEvent event) {
        try
        {
        //personNameList.getItems().add("gyagya");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PersonAddSceneFXML.fxml"));
        Parent root1 = fxmlLoader.load();
        PersonAddSceneFXMLController addPersonController = fxmlLoader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1)); 
        stage.setTitle("Person Adder");
        stage.show();
        } catch(IOException e)
        {
            System.out.println("Can't load");
        }   
    }
    @FXML
    void PersonAdder(String name) {
        
        personNameList.getItems().add(name);
        System.out.println(name);
        System.out.println(personNameList.getItems());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //példa
        List<Person> szemelyek = new ArrayList<>();
        szemelyek.add(new Person());
        szemelyek.get(0).setName("gyuri");
    
        personNameList.getItems().add(szemelyek.get(0).getName());   
    }    
}
