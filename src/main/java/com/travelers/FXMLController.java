package com.travelers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Szondi
 */
public class FXMLController implements Initializable {

    //Main Scene FXML items
    private boolean stageLoadedAddPersonAndLocation = false;
    private boolean stageLoadedPersonDetail = false;
    
    public static LocationDAO locDAO = new JpaLocationDAO();
    public static PersonDAO personDAO = new JpaPersonDAO();
    
    @FXML
    private ListView<Person> personListView;

    @FXML
    private ListView<Location> locationListView;

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
    void PersonAdder(String name, LocalDate date, String favoritePlace, double latitude, double longitude, double altitude, String img, String textArea) throws IOException {
        
        if (img == null) img = "";

        //Location létrehozása
        List<Location> locations = new ArrayList<>();
        Location location = new Location(favoritePlace, latitude, longitude, altitude, img, textArea);
        locations.add(location);
        
        //Person létrehozása
        Person p = new Person(name, date, locations);
        personListView.getItems().add(p);
        locDAO.saveLocation(location);
        personDAO.savePerson(p);
        
        //mentés után újra megnyithatóvá tesszük az addPerson folyamatot
        stageLoadedAddPersonAndLocation = false;
    }

    @FXML
    void deletePerson(ActionEvent event) throws IOException {
        Person chosenPerson = personListView.getSelectionModel().getSelectedItem();

        //ha a személy lista nem üres, és ki lett választva a személy
        if (!personListView.getItems().isEmpty() && chosenPerson != null) {
            int chosenPersonIndex = personListView.getSelectionModel().getSelectedIndex();
            
            personDAO.deletePerson(chosenPerson);
            
            List<Location> locList = locDAO.getLocations();
            for (Location loc: locList) {
                if (chosenPerson.getLocations().indexOf(loc) != -1) {
                    locDAO.deleteLocation(loc);
                    File img = new File(loc.getImg()); 
                    img.delete();
                }
            }
            //kitörli a kiválasztott személyt
            personListView.getItems().remove(chosenPersonIndex);
            locationListView.getItems().clear();
        }
    }

    @FXML
    void addPerson(ActionEvent event) {
        //ha nincs már egyszer megnyitva az ablak
        if (!stageLoadedAddPersonAndLocation) {
            try {
                //ablak megnyitottságának rögzítése
                stageLoadedAddPersonAndLocation = true;
                //addPerson Controller lekérdezés, scene létrehozás
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PersonAddSceneFXML.fxml"));
                Parent root1 = fxmlLoader.load();
                PersonAddSceneFXMLController addPersonController = fxmlLoader.getController();
                Datas.addPersonControllers = addPersonController;

                //stage betöltés
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("Person Adder");

                //Ha bezárták az add person controllert mentés nélkül
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
    void LocationAdder(String favoritePlace, double latitude, double longitude, double altitude, String img, String textArea) throws IOException {
        
        if (img == null) img = "";
        //Location létrehozása
        Location location = new Location(favoritePlace, longitude, latitude, altitude, img, textArea);
        //választott személy indexének lekérdezése
        int chosenPersonIndex = personListView.getSelectionModel().getSelectedIndex();
        //választott személyhez hozzáadjuk a locationt
        locDAO.saveLocation(location);
        personDAO.getPersons().get(chosenPersonIndex).addLocation(location);
        personDAO.updatePerson(personDAO.getPersons().get(chosenPersonIndex));
        
        personListView.refresh();
        
        Person chosenPerson = personListView.getSelectionModel().getSelectedItem();
        ListView<Location> locationNames = new ListView<>();
        locationNames.getItems().addAll(chosenPerson.getLocations());
        locationListView.setItems(locationNames.getItems());
        
        //újranyithatóság engedélyezése
        stageLoadedAddPersonAndLocation = false;
    }

    @FXML
    void addLocationClicked(ActionEvent event) {
        //Ha Nincs más adder/editor window megnyitva, a person lista nem üres, ki van ott jelölve egy személy
        if ((!stageLoadedAddPersonAndLocation) &&
            (!personListView.getItems().isEmpty()) &&
              personListView.getSelectionModel().getSelectedItem() != null) {
            try {
                //Más adder window megnyitásának megakadályozása
                stageLoadedAddPersonAndLocation = true;

                //Controller scene betöltés
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PersonAddSceneFXML.fxml"));
                Parent root1 = fxmlLoader.load();
                PersonAddSceneFXMLController addLocationController = fxmlLoader.getController();
                Datas.addPersonControllers = addLocationController;

                //Személy név-kor átadás, így azt a felhasználó nem módosíthatja
                Person chosenPerson = personListView.getSelectionModel().getSelectedItem();
                addLocationController.addJustLocation(chosenPerson.getName(), chosenPerson.getDateOfBirth());
                
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
            System.out.println("Az oldal már be van töltve vagy nincsenek kijelölve a megfelelő adatok a megjelenítéshez!");
        }
    }

    @FXML
    void deleteLocationClicked(ActionEvent event) throws IOException {
        Person chosenPerson = personListView.getSelectionModel().getSelectedItem();
        Location delLocation = chosenPerson.getLocations().get(locationListView.getSelectionModel().getSelectedIndex());

        //Ha a personListView nem üres, személy ki van jelölve, LocationListView nem üres, location ki van jelölve
        if (!personListView.getItems().isEmpty() && chosenPerson != null && delLocation != null && !locationListView.getItems().isEmpty()) {
            int chosenPersonLocationIndex = locationListView.getSelectionModel().getSelectedIndex();
            int chosenPersonIndex = personListView.getSelectionModel().getSelectedIndex();
            //kitörli a listviewből a kiválasztott location nevét
            locationListView.getItems().remove(chosenPersonLocationIndex);
            //kitörli a locationt
            List<Location> locList = locDAO.getLocations();
            for (Location loc: locList) {
                if (loc.getId() == delLocation.getId()) {
                    locDAO.deleteLocation(loc);
                    File img = new File(loc.getImg()); 
                    img.delete();
                }
            }
            personDAO.getPersons().get(chosenPersonIndex).removeLocation(delLocation);
            personListView.refresh();
            
            favoritePlaceOutput.setText("...");
            dateOfBirthOutput.setText("...");
            latitudeOutput.setText("...");
            longitudeOutput.setText("...");
            altitudeOutput.setText("...");
            personImage.setImage(null);
        }
    }

    @FXML
    void editPersonClicked(ActionEvent event) {
        //Nincs másik adder/editor window megnyitva, personListView nem üres, személy ki van jelölve
        if ((!stageLoadedAddPersonAndLocation) & (!personListView.getItems().isEmpty()) & personListView.getSelectionModel().getSelectedItem() != null) {
            try {
                //Más adder window megnyitásának megakadályozása
                stageLoadedAddPersonAndLocation = true;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PersonAddSceneFXML.fxml"));
                Parent root1 = fxmlLoader.load();
                PersonAddSceneFXMLController addLocationController = fxmlLoader.getController();
                Datas.addPersonControllers = addLocationController;

                Person chosenPerson = personListView.getSelectionModel().getSelectedItem();
                //Adatok átadása, ezek módosíthatóak lesznek
                addLocationController.editPerson(chosenPerson.getName(), chosenPerson.getDateOfBirth());
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
            System.out.println("Az oldal már be van töltve vagy nincsenek kijelölve a megfelelő adatok a megjelenítéshez!");
        }
    }

    @FXML
    void editPerson(String Name, LocalDate date) {
        //Az addPerson Controllerből kapja az adatokat
        Person chosenPerson = personListView.getSelectionModel().getSelectedItem();
        chosenPerson.setName(Name);
        chosenPerson.setDateOfBirth(date);

        //person date(kor) átírása
        dateOfBirthOutput.setText(chosenPerson.getDateOfBirth().toString());
        
        personDAO.updatePerson(chosenPerson);
        
        personListView.refresh();
        stageLoadedAddPersonAndLocation = false;
    }

    @FXML
    void editLocationClicked(ActionEvent event) {
        Person chosenPerson = personListView.getSelectionModel().getSelectedItem();
        Location chosenPersonLocation = chosenPerson.getLocations().get(locationListView.getSelectionModel().getSelectedIndex());
        //Ha Nincs más adder/editor window megnyitva, nem üres a PersonListView, van személy kijelölve, nem üres a hozzá tartozó locationListView, van location kijelölve
        if ((!stageLoadedAddPersonAndLocation) & (!personListView.getItems().isEmpty() && chosenPerson != null && chosenPersonLocation != null && !locationListView.getItems().isEmpty())) {
            try {
                //Más adder window megnyitásának megakadályozása
                stageLoadedAddPersonAndLocation = true;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PersonAddSceneFXML.fxml"));
                Parent root1 = fxmlLoader.load();
                PersonAddSceneFXMLController addLocationController = fxmlLoader.getController();
                Datas.addPersonControllers = addLocationController;

                //Adatok átadása, a néven/koron kívűl minden adat módosítható lesz
                addLocationController.editLocation(chosenPerson.getName(), chosenPerson.getDateOfBirth(),
                                                   chosenPersonLocation.getName(), chosenPersonLocation.getLatitude(),
                                                   chosenPersonLocation.getLongitude(), chosenPersonLocation.getAltitude(),
                                                   "file:"+chosenPersonLocation.getImg(), chosenPersonLocation.getDetails());

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
    void editLocation(String favoriePlace, double latitude, double longitude, double altitude, String img, String details) throws IOException {
        Person chosenPerson = personListView.getSelectionModel().getSelectedItem();
        Location chosenLocation = chosenPerson.getLocations().get(locationListView.getSelectionModel().getSelectedIndex());
        
        List<Location> locList = locDAO.getLocations();
        
        for (Location l : locList) {
            if (l.getId() == chosenLocation.getId()) {
                l.setAltitude(altitude);
                l.setDetails(details);
                l.setLatitude(latitude);
                l.setLongitude(longitude);
                l.setImg(img);
                l.setName(favoriePlace);
                locDAO.updateLocation(l);
            }
        }
        
        Location location = new Location(favoriePlace, latitude, longitude, altitude, img, details);
        int chosenPersonIndex = personListView.getSelectionModel().getSelectedIndex();
        int chosenLocationIndex = locationListView.getSelectionModel().getSelectedIndex();
        personDAO.getPersons().get(chosenPersonIndex).getLocations().get(chosenLocationIndex).setName(favoriePlace);
        personDAO.getPersons().get(chosenPersonIndex).getLocations().get(chosenLocationIndex).setLatitude(latitude);
        personDAO.getPersons().get(chosenPersonIndex).getLocations().get(chosenLocationIndex).setLongitude(longitude);
        personDAO.getPersons().get(chosenPersonIndex).getLocations().get(chosenLocationIndex).setAltitude(altitude);
        personDAO.getPersons().get(chosenPersonIndex).getLocations().get(chosenLocationIndex).setImg(img);
        personDAO.getPersons().get(chosenPersonIndex).getLocations().get(chosenLocationIndex).setDetails(details);
        
        personListView.refresh();

        //Adatok kiírása a fő lapon
        ListView<Location> locationNames = new ListView<>();
        locationNames.getItems().addAll(chosenPerson.getLocations());
        locationListView.setItems(locationNames.getItems());
        favoritePlaceOutput.setText(location.getName());
        latitudeOutput.setText(String.valueOf(location.getLatitude()));
        longitudeOutput.setText(String.valueOf(location.getLongitude()));
        altitudeOutput.setText(String.valueOf(location.getAltitude()));
        if (!img.isEmpty()) {
            Image image = new Image(img);
            personImage.setImage(image);
        } else {
            personImage.setImage(null);
        }

        stageLoadedAddPersonAndLocation = false;
    }
    
    @FXML
    void detailsViewBtnClick(ActionEvent event) {
        if (!stageLoadedPersonDetail) {
            try {
                //A window csak egyszer megnyitható
                stageLoadedPersonDetail = true;

                //A details scene controllerének létrehozása
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PersonDetailsSceneFXML.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                PersonDetailsSceneFXMLController personDetailsController = fxmlLoader.getController();
                Datas.personDetailsController = personDetailsController;

                //Az input kiválasztása, majd átadása a Details controllernek
                Person chosenPerson = personListView.getSelectionModel().getSelectedItem();
                Location chosenLocation = chosenPerson.getLocations().get(locationListView.getSelectionModel().getSelectedIndex());
                //átadás, ha létezik, és ki van választva a person
                if (!locationListView.getItems().isEmpty() && chosenLocation != null && chosenPerson != null) {
                    //adat betöltése a details controllerbe
                    personDetailsController.LoadDetails(chosenLocation.getDetails());
                }
                //scene betöltés
                Stage stage = new Stage();
                stage.setTitle("Location Details");
                stage.setScene(new Scene(root1));

                //érzékelje, ha bezárták az ablakot
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
    void personListViewHandleClick(MouseEvent event) {
        //Ha a person listán elkattintunk, akkor az adatok alapjáraton legyenek üresek
        favoritePlaceOutput.setText("...");
        dateOfBirthOutput.setText("...");
        latitudeOutput.setText("...");
        longitudeOutput.setText("...");
        altitudeOutput.setText("...");
        personImage.setImage(null);

        Person chosenPerson = personListView.getSelectionModel().getSelectedItem();
        //Ha a listview nem üres és ki van jelölve egy személy
        if (!personListView.getItems().isEmpty() && chosenPerson != null) {
            dateOfBirthOutput.setText(chosenPerson.getDateOfBirth().toString());
            ListView<Location> locationNames = new ListView<>();
            locationNames.getItems().addAll(chosenPerson.getLocations());
            locationListView.setItems(locationNames.getItems());
        }
    }
    
    @FXML
    void locationListViewHandleClick(MouseEvent event) throws IOException {
        //minden locationListView kattintásnál a kép üres alapértelmezetten, ha a felhasználó töltött fel képet, akkor a program később betölti
        personImage.setImage(null);
        Person chosenPerson = personListView.getSelectionModel().getSelectedItem();
        Location chosenLocation = chosenPerson.getLocations().get(locationListView.getSelectionModel().getSelectedIndex());

        //Ha kattintottak személyre a LocationListView nem üres, kattintottak locationra
        if (!locationListView.getItems().isEmpty() && chosenLocation != null && chosenPerson != null) {
            //Adatok betöltése
            favoritePlaceOutput.setText(String.valueOf(chosenLocation.getName()));
            latitudeOutput.setText(String.valueOf(chosenLocation.getLatitude()));
            longitudeOutput.setText(String.valueOf(chosenLocation.getLongitude()));
            altitudeOutput.setText(String.valueOf(chosenLocation.getAltitude()));
            //kép betöltése
            String img = chosenLocation.getImg();
            if (!img.isEmpty()) {
                Image image = new Image("file:" + img);
                personImage.setImage(image);
            } else {
                personImage.setImage(null);
            }
        }
    }
    
    //AboutMenu bezárását figyeli
    private boolean stageLoadedAbout = false;
    @FXML
    void aboutMenuClicked(ActionEvent event)
    {
        if (!stageLoadedAbout) {
            try {
                stageLoadedAbout = true;

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AboutSceneFXML.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();

                Stage stage = new Stage();
                stage.setTitle("About");
                stage.setScene(new Scene(root1));

                stage.setOnCloseRequest((eventt) -> {
                    stageLoadedAbout = false;
                });
                stage.show();

            } catch (IOException e) {
                System.out.println("Can't load");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("---------Init---------");
        personListView.getItems().addAll(personDAO.getPersons());
    }
}