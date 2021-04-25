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
    private MenuItem editLocation;

    @FXML
    private MenuItem editPerson;

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
                //kiválasztás
                String chosenPerson = personNameList.getSelectionModel().getSelectedItem();
                String chosenLocation = locationNameList.getSelectionModel().getSelectedItem();
                //átadás, ha létezik, és ki van választva a person
                if (!locationNameList.getItems().isEmpty() && chosenLocation != null && chosenPerson != null) {
                    int chosenLocationIndex = locationNameList.getSelectionModel().getSelectedIndex();
                    int chosenPersonIndex = personNameList.getSelectionModel().getSelectedIndex();
                    //adat betöltése a details controllerbe
                    personDetailsController.LoadDetails(persons.get(chosenPersonIndex).getLocations().get(chosenLocationIndex).getDetails());
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
    private MenuItem deleteBtn;

    @FXML
    void deletePerson(ActionEvent event) {

        String chosenPerson = personNameList.getSelectionModel().getSelectedItem();

        //ha a személy lista nem üres, és ki lett választva a személy
        if (!personNameList.getItems().isEmpty() && chosenPerson != null) {
            int chosenPersonIndex = personNameList.getSelectionModel().getSelectedIndex();
            //kitörli a listviewből a kiválasztott személy nevét
            personNameList.getItems().remove(chosenPersonIndex);
            //kitörli a személyt
            persons.remove(chosenPersonIndex);
            locationNameList.getItems().clear();

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
    void PersonAdder(String name, LocalDate date, String favoritePlace, double latitude, double longitude, double altitude, String img, String textArea) {
        //Az observableList létrehozása, amellyel a listViewet feltöltjük
        ObservableList<String> locationsListNames = FXCollections.observableArrayList();
        //Személyhez kötött listview nevek feltöltése az adott értékkel
        locationsListNames.add(favoritePlace);
        List<Location> locations = new ArrayList<>();
        //Location létrehozása
        if (img == null) {
            img = "";
        }
        locations.add(new Location(favoritePlace, latitude, longitude, altitude, img, textArea));
        //Person létrehozása
        persons.add(new Person(name, date, locations, locationsListNames));

        //A személyhez tartozó listviewhez hozzáadjuk a személy nevét
        personNameList.getItems().add(name);
        //mentés után újra megnyithatóvá tesszük az addPerson folyamatot
        stageLoadedAddPersonAndLocation = false;
    }

    @FXML
    void LocationAdder(String favoritePlace, double latitude, double longitude, double altitude, String img, String textArea) {
        //Location létrehozása
        if (img == null) {
            img = "";
        }
        Location location = new Location(favoritePlace, longitude, latitude, altitude, img, textArea);
        //választott személy indexének lekérdezése
        int chosenPersonIndex = personNameList.getSelectionModel().getSelectedIndex();
        //választott személyhez hozzáadjuk a locationt
        persons.get(chosenPersonIndex).getLocations().add(location);
        persons.get(chosenPersonIndex).getLocationListNames().add(favoritePlace);

        System.out.println("location" + persons.get(chosenPersonIndex).getLocationListNames());
        //újranyithatóság engedélyezése
        stageLoadedAddPersonAndLocation = false;
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

        String chosenPerson = personNameList.getSelectionModel().getSelectedItem();
        //Ha a listview nem üres és ki van jelölve egy személy
        if (!personNameList.getItems().isEmpty() && chosenPerson != null) {
            int chosenPersonIndex = personNameList.getSelectionModel().getSelectedIndex();
            dateOfBirthOutput.setText(persons.get(chosenPersonIndex).getBirthOfYear().toString());

            //Átírja a LocationListet az aktuális személy helyek listájára
            locationNameList.setItems(persons.get(chosenPersonIndex).getLocationListNames());
        }

        System.out.println("clicked on " + chosenPerson);
    }

    @FXML
    void locationListViewHandleClick(MouseEvent event) {
        //minden locationListView kattintásnál a kép üres alapértelmezetten, ha a felhasználó töltött fel képet, akkor a program később betölti
        personImage.setImage(null);
        String chosenPerson = personNameList.getSelectionModel().getSelectedItem();
        String chosenLocation = locationNameList.getSelectionModel().getSelectedItem();

        //Ha kattintottak személyre a LocationListView nem üres, kattintottak locationra
        if (!locationNameList.getItems().isEmpty() && chosenLocation != null && chosenPerson != null) {
            int chosenLocationIndex = locationNameList.getSelectionModel().getSelectedIndex();
            int chosenPersonIndex = personNameList.getSelectionModel().getSelectedIndex();

            //Adatok betöltése
            favoritePlaceOutput.setText(String.valueOf(persons.get(chosenPersonIndex).getLocations().get(chosenLocationIndex).getName()));
            latitudeOutput.setText(String.valueOf(persons.get(chosenPersonIndex).getLocations().get(chosenLocationIndex).getLatitude()));
            longitudeOutput.setText(String.valueOf(persons.get(chosenPersonIndex).getLocations().get(chosenLocationIndex).getLongitude()));
            altitudeOutput.setText(String.valueOf(persons.get(chosenPersonIndex).getLocations().get(chosenLocationIndex).getAltitude()));
            //kép betöltése
            String img = persons.get(chosenPersonIndex).getLocations().get(chosenLocationIndex).getImage();
            if (!img.isEmpty()) {
                Image image = new Image(img);
                personImage.setImage(image);
            } else {
                personImage.setImage(null);
            }

        }
        System.out.println("clicked on " + chosenLocation);
    }

    @FXML
    void addLocationClicked(ActionEvent event) {
        //Ha Nincs más adder/editor window megnyitva, a person lista nem üres, ki van ott jelölve egy személy
        if ((!stageLoadedAddPersonAndLocation) & (!personNameList.getItems().isEmpty()) & personNameList.getSelectionModel().getSelectedItem() != null) {
            try {
                //Más adder window megnyitásának megakadályozása
                stageLoadedAddPersonAndLocation = true;

                //Controller scene betöltés
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PersonAddSceneFXML.fxml"));
                Parent root1 = fxmlLoader.load();
                PersonAddSceneFXMLController addLocationController = fxmlLoader.getController();
                Datas.addPersonControllers = addLocationController;

                int chosenPersonIndex = personNameList.getSelectionModel().getSelectedIndex();

                //Személy név-kor átadás, így azt a felhasználó nem módosíthatja
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
            System.out.println("Az oldal már be van töltve vagy nincsenek kijelölve a megfelelő adatok a megjelenítéshez!");
        }
    }

    @FXML
    void deleteLocationClicked(ActionEvent event) {
        String chosenPerson = personNameList.getSelectionModel().getSelectedItem();
        String chosenPersonLocation = locationNameList.getSelectionModel().getSelectedItem();

        //Ha a personListView nem üres, személy ki van jelölve, LocationListView nem üres, location ki van jelölve
        if (!personNameList.getItems().isEmpty() && chosenPerson != null && chosenPersonLocation != null && !locationNameList.getItems().isEmpty()) {
            int chosenPersonLocationIndex = locationNameList.getSelectionModel().getSelectedIndex();
            int chosenPersonIndex = personNameList.getSelectionModel().getSelectedIndex();
            //kitörli a listviewből a kiválasztott location nevét
            locationNameList.getItems().remove(chosenPersonLocationIndex);
            //kitörli a locationt
            persons.get(chosenPersonIndex).removeLocation(persons.get(chosenPersonIndex).getLocations().get(chosenPersonLocationIndex));
        }
    }

    @FXML
    void editPersonClicked(ActionEvent event) {
        //Nincs másik adder/editor window megnyitva, personListView nem üres, személy ki van jelölve
        if ((!stageLoadedAddPersonAndLocation) & (!personNameList.getItems().isEmpty()) & personNameList.getSelectionModel().getSelectedItem() != null) {
            try {
                //Más adder window megnyitásának megakadályozása
                stageLoadedAddPersonAndLocation = true;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PersonAddSceneFXML.fxml"));
                Parent root1 = fxmlLoader.load();
                PersonAddSceneFXMLController addLocationController = fxmlLoader.getController();
                Datas.addPersonControllers = addLocationController;

                int chosenPersonIndex = personNameList.getSelectionModel().getSelectedIndex();
                //Adatok átadása, ezek módosíthatóak lesznek
                addLocationController.editPerson(persons.get(chosenPersonIndex).getName(), persons.get(chosenPersonIndex).getBirthOfYear());
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
        String chosenPerson = personNameList.getSelectionModel().getSelectedItem();
        int chosenPersonIndex = personNameList.getSelectionModel().getSelectedIndex();
        persons.get(chosenPersonIndex).setName(Name);

        //Átírja a nevet a personListView-ben
        personNameList.getItems().remove(chosenPersonIndex);
        personNameList.getItems().add(chosenPersonIndex, Name);
        //person date(kor) átírása
        persons.get(chosenPersonIndex).setBirthOfYear(date);
        dateOfBirthOutput.setText(persons.get(chosenPersonIndex).getBirthOfYear().toString());
        stageLoadedAddPersonAndLocation = false;
    }

    @FXML
    void editLocationClicked(ActionEvent event) {
        String chosenPerson = personNameList.getSelectionModel().getSelectedItem();
        String chosenPersonLocation = locationNameList.getSelectionModel().getSelectedItem();
        //Ha Nincs más adder/editor window megnyitva, nem üres a PersonListView, van személy kijelölve, nem üres a hozzá tartozó locationListView, van location kijelölve
        if ((!stageLoadedAddPersonAndLocation) & (!personNameList.getItems().isEmpty() && chosenPerson != null && chosenPersonLocation != null && !locationNameList.getItems().isEmpty())) {
            try {
                //Más adder window megnyitásának megakadályozása
                stageLoadedAddPersonAndLocation = true;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PersonAddSceneFXML.fxml"));
                Parent root1 = fxmlLoader.load();
                PersonAddSceneFXMLController addLocationController = fxmlLoader.getController();
                Datas.addPersonControllers = addLocationController;

                int chosenPersonIndex = personNameList.getSelectionModel().getSelectedIndex();
                int chosenPersonLocationIndex = locationNameList.getSelectionModel().getSelectedIndex();
                Location location = persons.get(chosenPersonIndex).getLocations().get(chosenPersonLocationIndex);
                //Adatok átadása, a néven/koron kívűl minden adat módosítható lesz
                addLocationController.editLocation(persons.get(chosenPersonIndex).getName(), persons.get(chosenPersonIndex).getBirthOfYear(), location.getName(), location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getImage(), location.getDetails());

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
    void editLocation(String favoriePlace, double latitude, double longitude, double altitude, String img, String details) {
        int chosenPersonIndex = personNameList.getSelectionModel().getSelectedIndex();
        int chosenPersonLocationIndex = locationNameList.getSelectionModel().getSelectedIndex();

        Location location = new Location(favoriePlace, latitude, longitude, altitude, img, details);
        persons.get(chosenPersonIndex).getLocations().set(chosenPersonLocationIndex, location);
        //Adatok kiírása a fő lapon
        //location nevének átírása a locationListViewben
        locationNameList.getItems().remove(chosenPersonLocationIndex);
        locationNameList.getItems().add(chosenPersonLocationIndex, location.getName());
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

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {

    }
}
