/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Szondi
 */
public class PersonAddSceneFXMLController implements Initializable {

    //Ez az érték határozza meg, hogy bizonyos gombok megnyomásakor a personAdder controller mely folyamata fusson le
    private int whichButtonPushed = 0;

    @FXML
    private TextField altitudeInput;

    @FXML
    private DatePicker dateOfBirthInput;

    @FXML
    private TextField favoritePlaceInput;

    @FXML
    private TextField longitudeInput;

    @FXML
    private TextField latitudeInput;

    @FXML
    private TextArea addPersonDetailsIn;

    @FXML
    private TextField nameInput;

    @FXML
    private Button addPersonSavebtn;

    @FXML
    private Button uploadImagebtn;

    @FXML
    private ImageView addPersonImage;

    private String fileName;

    @FXML
    private Label fileSelected;

    @FXML
    public void editPerson(String name, LocalDate date) {
        whichButtonPushed = 2;
        nameInput.setText(name);
        dateOfBirthInput.setValue(date);
        favoritePlaceInput.setVisible(false);
        latitudeInput.setVisible(false);
        longitudeInput.setVisible(false);
        altitudeInput.setVisible(false);
        addPersonDetailsIn.setVisible(false);
        uploadImagebtn.setVisible(false);
    }

    @FXML
    public void editLocation(String name, LocalDate date, String favoritePlace, double latitude, double longitude, double altitude, String img, String description) {
        whichButtonPushed = 3;
        nameInput.setText(name);
        nameInput.setEditable(false);
        dateOfBirthInput.setValue(date);
        dateOfBirthInput.setEditable(false);

        favoritePlaceInput.setText(favoritePlace);
        latitudeInput.setText(String.valueOf(latitude));
        longitudeInput.setText(String.valueOf(longitude));
        altitudeInput.setText(String.valueOf(altitude));
        addPersonDetailsIn.setText(description);
        if (!img.isEmpty()) {
            fileName = img;
            Image image = new Image(img);
            addPersonImage.setImage(image);
        } else {
            fileName = img;
            addPersonImage.setImage(null);
        }
        System.out.println("IMAGE" + img.toString());

    }

    @FXML
    public void addJustLocation(String name, LocalDate date) {
        System.out.println("lefut a location");
        whichButtonPushed = 1;

        nameInput.setText(name);
        nameInput.setEditable(false);

        dateOfBirthInput.setValue(date);
        dateOfBirthInput.setEditable(false);
    }

    @FXML
    public void uploadImageBtnClicked(ActionEvent actionEvent) throws MalformedURLException {
        fileSelected.setText("");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif")); // limit fileChooser options to image files
        File selectedFile = fileChooser.showOpenDialog(fileSelected.getScene().getWindow());

        if (selectedFile != null) {

            fileName = selectedFile.toURI().toURL().toString();

            Image image = new Image(fileName);
            addPersonImage.setImage(image);
        } else {
            fileSelected.setText("Image file selection cancelled.");
        }
    }
    
    private boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        }
        catch(NumberFormatException e){
              return false;  
        }  
    }

    @FXML
    private Label errorText;

    @FXML
    void addPersonSavebtnPressed(ActionEvent event) {
        switch (whichButtonPushed) {
            //Ha a program alapértelmezetten fut le, azaz új személyt adunk az adatbázishoz
            case 0:
                errorText.setText("");
                if (nameInput.getText() == null || nameInput.getText().trim().isEmpty()
                        || dateOfBirthInput.getValue() == null
                        || favoritePlaceInput.getText() == null || favoritePlaceInput.getText().trim().isEmpty()
                        || longitudeInput.getText() == null || longitudeInput.getText().trim().isEmpty() || !isNumeric(longitudeInput.getText())
                        || altitudeInput.getText() == null || altitudeInput.getText().trim().isEmpty() || !isNumeric(altitudeInput.getText())
                        || latitudeInput.getText() == null || latitudeInput.getText().trim().isEmpty() || !isNumeric(latitudeInput.getText())) {
                    errorText.setText("Error: Fill in the obligatory inputs!");
                } else {
                    try {
                        //Controller visszaadása a fő Scene-nek
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        FXMLController controller = Datas.fxmlController;
                        controller.PersonAdder(nameInput.getText(), dateOfBirthInput.getValue(), favoritePlaceInput.getText(), Double.parseDouble(latitudeInput.getText()), Double.parseDouble(longitudeInput.getText()), Double.parseDouble(altitudeInput.getText()), fileName, addPersonDetailsIn.getText());

                        Stage stage = (Stage) addPersonSavebtn.getScene().getWindow();
                        stage.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
                break;
            //Ha egy adott személyhez egy új locationt adunk
            case 1:
                errorText.setText("");
                if (favoritePlaceInput.getText() == null || favoritePlaceInput.getText().trim().isEmpty()
                        || longitudeInput.getText() == null || longitudeInput.getText().trim().isEmpty() || !isNumeric(longitudeInput.getText())
                        || altitudeInput.getText() == null || altitudeInput.getText().trim().isEmpty() || !isNumeric(altitudeInput.getText())
                        || latitudeInput.getText() == null || latitudeInput.getText().trim().isEmpty() || !isNumeric(latitudeInput.getText())) {
                    errorText.setText("Error: Fill in the obligatory inputs!");
                } else {
                    try {
                        //Controller visszaadása a fő Scene-nek
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        FXMLController controller = Datas.fxmlController;
                        controller.LocationAdder(favoritePlaceInput.getText(), Double.parseDouble(latitudeInput.getText()), Double.parseDouble(longitudeInput.getText()), Double.parseDouble(altitudeInput.getText()), fileName, addPersonDetailsIn.getText());
                        Stage stage = (Stage) addPersonSavebtn.getScene().getWindow();
                        stage.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
                break;

            //Ha egy létező személyt módosítani akarunk
            case 2: {
                errorText.setText("");
                if (nameInput.getText() == null || nameInput.getText().trim().isEmpty()
                        || dateOfBirthInput.getValue() == null) {
                    errorText.setText("Error: Fill in the obligatory inputs!");
                } else {
                    try {
                        //Controller visszaadása a fő Scene-nek
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        FXMLController controller = Datas.fxmlController;
                        controller.editPerson(nameInput.getText(), dateOfBirthInput.getValue());
                        Stage stage = (Stage) addPersonSavebtn.getScene().getWindow();
                        stage.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
            }
            break;
            //Ha egy létező személynek a location/locationjai közül módosítani akarjuk valamelyiket
            case 3: {
                errorText.setText("");
                if (favoritePlaceInput.getText() == null || favoritePlaceInput.getText().trim().isEmpty()
                        || longitudeInput.getText() == null || longitudeInput.getText().trim().isEmpty() || !isNumeric(longitudeInput.getText())
                        || altitudeInput.getText() == null || altitudeInput.getText().trim().isEmpty() || !isNumeric(altitudeInput.getText())
                        || latitudeInput.getText() == null || latitudeInput.getText().trim().isEmpty() || !isNumeric(latitudeInput.getText())) {
                    errorText.setText("Error: Fill in the obligatory inputs!");
                } else {
                    try {
                        //Controller visszaadása a fő Scene-nek
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        FXMLController controller = Datas.fxmlController;
                        controller.editLocation(favoritePlaceInput.getText(), Double.parseDouble(latitudeInput.getText()), Double.parseDouble(longitudeInput.getText()), Double.parseDouble(altitudeInput.getText()), fileName, addPersonDetailsIn.getText());
                        Stage stage = (Stage) addPersonSavebtn.getScene().getWindow();
                        stage.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
            }
            break;
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }
}
