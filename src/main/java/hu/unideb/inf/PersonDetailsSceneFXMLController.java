/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author Szondi
 */
public class PersonDetailsSceneFXMLController {
    
    
      @FXML
    private Label detailsTitle;

    @FXML
    private Label personDetailsOut;
    
    @FXML
    void LoadDetails(String input)
    {
        if(input.isEmpty())
        {
            detailsTitle.setText("A felhasználó nem adott meg részleteket.");
        }
        else
        {
            personDetailsOut.setText(input);
        }
    }

}
