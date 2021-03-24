/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Formation;
import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;

import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author wajdi
 */
public class CardFormationController implements Initializable {

    @FXML
    private ImageView imageID;
    @FXML
    private Label TitreFormationId;
    @FXML
    private Label NomFormateurId;
    @FXML
    private Rating RateId;
    @FXML
    private VBox boxId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Formation formation, User user) {
        if (user.getPhoto_profil().get().equals("")) {
            Image image = new Image(getClass().getResourceAsStream("/assets/formation.jpg"));
            imageID.setImage(image);
        } else {
            Image image = new Image(getClass().getResourceAsStream(user.getPhoto_profil().get()));
            imageID.setImage(image);
        }
        TitreFormationId.setText(formation.getTitre().get());
        NomFormateurId.setText(user.getNom().get());
        RateId.setRating(formation.getNote().get());
    }

}
