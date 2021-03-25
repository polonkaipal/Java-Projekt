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
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;



public class FXMLController implements Initializable {
    
    @FXML
    private MenuItem addPersonMenuItem;

    @FXML
    private ListView<?> personNameList;

    @FXML
    private ImageView personImage;

    @FXML
    void addPersonToList(ActionEvent event) {

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
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
