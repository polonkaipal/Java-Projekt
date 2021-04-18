/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Szondi
 */
public class PersonAddSceneFXMLController{
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

    @FXML
    private Button addPersonSavebtn;
    
    @FXML
    private Label errorText;
    @FXML
    void SaddPersonSavebtn(ActionEvent event) {
        errorText.setText("");
        if (nameInput.getText() == null || nameInput.getText().trim().isEmpty() ||
            dateOfBirthInput.getText() == null || dateOfBirthInput.getText().trim().isEmpty() ||
            favoritePlaceInput.getText() == null || favoritePlaceInput.getText().trim().isEmpty() ||
                longitudeInput.getText() == null || longitudeInput.getText().trim().isEmpty() ||
                latitudeInput.getText() == null || latitudeInput.getText().trim().isEmpty()) 
        {
           errorText.setText("Error: Fill in the obligatory inputs!");
        }
        else
        {
            
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                FXMLController controller = fxmlLoader.getController();
                controller.PersonAdder(nameInput.getText());
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
