/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dahmani
 */
public class AccueilController implements Initializable {

    @FXML
    private Button accueilBtn;
    @FXML
    private Button profilBtn;
    @FXML
    private Button formationsBtn;
    @FXML
    private Button test_quizBtn;
    @FXML
    private Button chatBtn;
    @FXML
    private Button deconnectBtn;
    @FXML
    private Label welcomeLabel;

    User currentUser;
    @FXML
    private Button btnEvent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initData(User u) {
        this.currentUser = u;
        welcomeLabel.setText("Bienvenue " + currentUser.getNom());
    }

    @FXML
    private void handleAccueilBtn(ActionEvent event) {
    }

    @FXML
    private void HandleAccueilBtn(ActionEvent event) {
    }

    @FXML
    private void handleTestQuizBtn(ActionEvent event) {
    }

    @FXML
    private void handleChatBtn(ActionEvent event) {
    }

    @FXML
    private void handleDeconnectBtn(ActionEvent event) {
    }

    @FXML
    private void handleEvent(ActionEvent event) {

        try {
            if ("Admin".equals(this.currentUser.getRole())) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Evenement.fxml"));
                Scene scene = new Scene(loader.load());
                EvenementController controller = loader.getController();
                controller.initData(this.currentUser);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else if ("Etudiant".equals(this.currentUser.getRole()) || "Formateur".equals(this.currentUser.getRole())) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Participation.fxml"));
                Scene scene = new Scene(loader.load());
                ParticipationController controller = loader.getController();
                controller.initData(this.currentUser);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
