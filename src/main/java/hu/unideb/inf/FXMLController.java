package hu.unideb.inf;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.MenuItem;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private boolean stageLoadedAddPersonAndLocation = false;
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
    private Label altitudeOutput;
    

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
                
                //Az input átadása a Details controllernek
                String chosenPerson = personNameList.getSelectionModel().getSelectedItem();
                String chosenLocation = locationNameList.getSelectionModel().getSelectedItem();
                if (!locationNameList.getItems().isEmpty() && chosenLocation!=null && chosenPerson!=null) {
                int chosenLocationIndex = locationNameList.getSelectionModel().getSelectedIndex();
                int chosenPersonIndex = personNameList.getSelectionModel().getSelectedIndex();
                personDetailsController.LoadDetails(persons.get(chosenPersonIndex).getLocations().get(chosenLocationIndex).getDetails());
                }
                
                Stage stage = new Stage();
                stage.setTitle("Location Details");
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
        if (!stageLoadedAddPersonAndLocation) {
            try {
                stageLoadedAddPersonAndLocation = true;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PersonAddSceneFXML.fxml"));
                Parent root1 = fxmlLoader.load();
                PersonAddSceneFXMLController addPersonController = fxmlLoader.getController();
                Datas.addPersonControllers = addPersonController;
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("Person Adder");

                //Person adder close handler
                stage.setOnCloseRequest((eventt) -> {
                    stageLoadedAddPersonAndLocation = false;
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
    void PersonAdder(String name, LocalDate date, String favoritePlace, double latitude, double longitude, double altitude, String img, String textArea) {
        ObservableList<String> locationsListNames = FXCollections.observableArrayList();
        System.out.println(longitude);
        locationsListNames.add(favoritePlace);
        List<Location> locations = new ArrayList<>();
        locations.add(new Location(favoritePlace, latitude, longitude, altitude, img, textArea));
        persons.add(new Person(name, date, locations, locationsListNames));
        
        System.out.println("person"+ locationsListNames);
        personNameList.getItems().add(name);
        stageLoadedAddPersonAndLocation = false;
    }

    @FXML
    void LocationAdder(String favoritePlace, double latitude, double longitude, double altitude, String img, String textArea) {
        Location location = new Location(favoritePlace, longitude, latitude, altitude, img, textArea);
        
        int chosenPersonIndex = personNameList.getSelectionModel().getSelectedIndex();
        
        persons.get(chosenPersonIndex).getLocations().add(location);
        persons.get(chosenPersonIndex).getLocationListNames().add(favoritePlace);
        
        System.out.println("location"+ persons.get(chosenPersonIndex).getLocationListNames());
        stageLoadedAddPersonAndLocation = false;
    }
    
    @FXML
    void DetailsAdder(int row, String description, String answer){
        switch (row) {
            case 1: ;
                     break;
            default: ;
                     break;
        }
        
    }

    @FXML
    void personListViewHandleClick(MouseEvent event) {
        String chosenPerson = personNameList.getSelectionModel().getSelectedItem();
        
        if (!personNameList.getItems().isEmpty() && chosenPerson!=null) {
            int chosenPersonIndex = personNameList.getSelectionModel().getSelectedIndex();
            dateOfBirthOutput.setText(persons.get(chosenPersonIndex).getBirthOfYear().toString());
            
            //Átírja a LocationListet az aktuális személy helyek listájára
            locationNameList.setItems(persons.get(chosenPersonIndex).getLocationListNames());
        }

        System.out.println("clicked on " + chosenPerson);
    }

    @FXML
    void locationListViewHandleClick(MouseEvent event) {
        String chosenPerson = personNameList.getSelectionModel().getSelectedItem();
        String chosenLocation = locationNameList.getSelectionModel().getSelectedItem();
        
        if (!locationNameList.getItems().isEmpty() && chosenLocation!=null && chosenPerson!=null) {
            int chosenLocationIndex = locationNameList.getSelectionModel().getSelectedIndex();
            int chosenPersonIndex = personNameList.getSelectionModel().getSelectedIndex();
            
            favoritePlaceOutput.setText(String.valueOf(persons.get(chosenPersonIndex).getLocations().get(chosenLocationIndex).getName()));
            latitudeOutput.setText(String.valueOf(persons.get(chosenPersonIndex).getLocations().get(chosenLocationIndex).getLatitude()));
            longitudeOutput.setText(String.valueOf(persons.get(chosenPersonIndex).getLocations().get(chosenLocationIndex).getLongitude()));
            altitudeOutput.setText(String.valueOf(persons.get(chosenPersonIndex).getLocations().get(chosenLocationIndex).getAltitude()));
            
            String img = persons.get(chosenPersonIndex).getLocations().get(chosenLocationIndex).getImage();
            if (img != null) {
            Image image = new Image(img);
            personImage.setImage(image);
            
            
        }
            
        }
        System.out.println("clicked on " + chosenLocation);
    }

    @FXML
    void addLocationClicked(ActionEvent event) {
        if ((!stageLoadedAddPersonAndLocation) & (!personNameList.getItems().isEmpty())& personNameList.getSelectionModel().getSelectedItem()!=null) {
            try {
                
                stageLoadedAddPersonAndLocation = true;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PersonAddSceneFXML.fxml"));
                Parent root1 = fxmlLoader.load();
                PersonAddSceneFXMLController addLocationController = fxmlLoader.getController();
                Datas.addPersonControllers = addLocationController;
                
                int chosenPersonIndex = personNameList.getSelectionModel().getSelectedIndex();
                
                addLocationController.addJustLocation(persons.get(chosenPersonIndex).getName(), persons.get(chosenPersonIndex).getBirthOfYear());
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("Person Adder");

                //Person adder close handler
                stage.setOnCloseRequest((eventt) -> {
                    stageLoadedAddPersonAndLocation = false;
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

    }
}
