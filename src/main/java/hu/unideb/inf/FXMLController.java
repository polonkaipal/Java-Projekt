package hu.unideb.inf;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import javafx.scene.control.MenuItem;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class FXMLController implements Initializable {

    //Main Scene FXML items
    private int personCreatedIndex = 0;
    private boolean stageLoadedAddPerson = false;
    private boolean stageLoadedPersonDetail = false;
    private List<Person> persons = new ArrayList<>();

    @FXML
    private MenuItem addLocation;

    @FXML
    private MenuItem deleteLocation;

    @FXML
    private MenuItem addPersonMenuItem;

    @FXML
    private ListView<String> personNameList;

    @FXML
    private ListView<String> locationNameList;

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

                stage.setOnCloseRequest((eventt) -> {
                    stageLoadedPersonDetail = false;
                });
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
    void deletePerson(ActionEvent event) {
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

                //Person adder close handler
                stage.setOnCloseRequest((eventt) -> {
                    stageLoadedAddPerson = false;
                });
                stage.show();
            } catch (IOException e) {
                System.out.println("Can't load");
            }
        } else {
            System.out.println("Az oldal már be van töltve");
        }

    }

    @FXML
    void PersonAdder(String name, LocalDate date, String favouritePlace, double Longitude, double Latitude, String img) {
        Set<Location> locations = new LinkedHashSet<>();
        //locations.add(new Location());
        //locations.get(0).setLongitude(Longitude);
        //locations.get(0).setLatitude(Latitude);

        persons.add(new Person());
        persons.get(personCreatedIndex).setName(name);
        //persons.get(personCreatedIndex).setBirthOfYear(age);
        persons.get(personCreatedIndex).setLocations(locations);
        persons.get(personCreatedIndex).setName(name);
        System.out.println("a dátum "+date);
        if (img != null) {
            Image image = new Image(img);
            personImage.setImage(image);
        }

        personNameList.getItems().add(name);
        personCreatedIndex++;
        stageLoadedAddPerson = false;
    }

    @FXML
    void personListViewHandleClick(MouseEvent event) {
        String chosenPerson = personNameList.getSelectionModel().getSelectedItem();
        System.out.println("clicked on " + chosenPerson);
    }

    @FXML
    void locationListViewHandleClick(MouseEvent event) {
        String chosenPerson = locationNameList.getSelectionModel().getSelectedItem();
        System.out.println("clicked on " + chosenPerson);
    }

    @FXML
    void addLocationClicked(ActionEvent event) {
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

                //Person adder close handler
                stage.setOnCloseRequest((eventt) -> {
                    stageLoadedAddPerson = false;
                });
                stage.show();
            } catch (IOException e) {
                System.out.println("Can't load");
            }
        } else {
            System.out.println("Az oldal már be van töltve");
        }
    }

    @FXML
    void deleteLocationClicked(ActionEvent event) {
        System.out.println("deleteLocation clicked");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //példa
        //szemelyek.add(new Person());
        //szemelyek.get(0).setName("gyuri");
        //personNameList.getItems().add(szemelyek.get(0).getName());
        locationNameList.getItems().add("Rome");
    }
}
