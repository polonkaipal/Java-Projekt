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
import javafx.scene.input.MouseEvent;

public class FXMLController implements Initializable {

    //Main Scene FXML items
    private boolean stageLoadedAddPerson = false;
    private boolean stageLoadedPersonDetail = false;
    private List<Person> szemelyek = new ArrayList<>();

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
        if (!stageLoadedPersonDetail) {
            try {
                stageLoadedPersonDetail = true;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PersonDetailsSceneFXML.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                PersonDetailsSceneFXMLController personDetailsController = fxmlLoader.getController();
                Datas.personDetailsController = personDetailsController;
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();

            } catch (IOException e) {
                System.out.println("Can't load");
            }
        } else {
            System.out.println("The Person Details window has been already opened!");
        }

    }

    @FXML
    private MenuItem deleteBtn;

    @FXML
    void delete(ActionEvent event) {
        //dateOfBirthOutput.setText(Datas.name);
    }

    @FXML
    void addPerson(ActionEvent event) {
        if (!stageLoadedAddPerson) {
            try {
                stageLoadedAddPerson = true;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PersonAddSceneFXML.fxml"));
                Parent root1 = fxmlLoader.load();
                PersonAddSceneFXMLController addPersonController = fxmlLoader.getController();
                Datas.addPersonControllers = addPersonController;
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("Person Adder");
                stage.show();
            } catch (IOException e) {
                System.out.println("Can't load");
            }
        } else {
            System.out.println("Az oldal már be van töltve");
        }

    }

    @FXML
    void PersonAdder(String name) { 
        personNameList.getItems().add(name);
        stageLoadedAddPerson = false;
    }
    
    @FXML
    void listViewHandleClick(MouseEvent event) {
        String chosenPerson = personNameList.getSelectionModel().getSelectedItem();
        System.out.println("clicked on "+ chosenPerson);
      
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //példa
        
        szemelyek.add(new Person());
        szemelyek.get(0).setName("gyuri");
        personNameList.getItems().add(szemelyek.get(0).getName());
    }
}
