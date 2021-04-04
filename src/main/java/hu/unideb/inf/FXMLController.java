package hu.unideb.inf;

import java.net.URL;
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
import javafx.scene.control.TextField;



public class FXMLController implements Initializable {
    
    //Main Scene FXML items
    
    @FXML
    private MenuItem addPersonMenuItem;

    @FXML
    private ListView personNameList;

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
        } catch(Exception e)
        {
            System.out.println("Can't load");
        }
    }
    
    @FXML
    private MenuItem deleteBtn;
    @FXML
    void delete(ActionEvent event) {
        dateOfBirthOutput.setText("hello");
    }
    
    @FXML
    void addPerson(ActionEvent event) {
        try
        {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PersonAddSceneFXML.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
        } catch(Exception e)
        {
            System.out.println("Can't load");
        }   
    }
    
    
    
    
    //Add person FXML items
    
    
    
    
    @FXML
    private Button addPersonSaveBtn;
    
    @FXML
    private TextField dateOfBirthInput;

    @FXML
    private TextField favoritePlaceInput;

    @FXML
    private TextField longitudeInput;

    @FXML
    private TextField latitudeInput;

    @FXML
    private TextField detailsIn11;

    @FXML
    private TextField detailsIn12;

    @FXML
    private TextField detailsIn21;

    @FXML
    private TextField detailsIn22;

    @FXML
    private TextField detailsIn31;

    @FXML
    private TextField detailsIn32;

    @FXML
    private TextField nameInput;

    
    
    
    //Person Details FXML items
    
    
    
    
    @FXML
    private Label detailsOut11;

    @FXML
    private Label detailsOut12;

    @FXML
    private Label detailsOut21;

    @FXML
    private Label detailsOut22;

    @FXML
    private Label detailsOut31;

    @FXML
    private Label detailsOut32;
    
    @FXML
    private Label detailsTitle;

    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //példa
        personNameList.getItems().addAll("hello", "baba", "sas");   
    }    
}
