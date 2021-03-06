/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Evenement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.EvenementService;

/**
 * FXML Controller class
 *
 * @author hiche
 */
public class ModifierEvenementController implements Initializable {

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
    private Button btnEvent;
    @FXML
    private TextField textPrix;
    @FXML
    private TextField textEmplacement;
    @FXML
    private TextField textTitre;
    @FXML
    private TextField textDate;
    @FXML
    private TextField textFondation;
    @FXML
    private TextField textNbMax;
    @FXML
    private TextField textDuree;
    @FXML
    private Button btnAnnuler;
    @FXML
    private Button btnAjout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    }

    @FXML
    private void HandleAnnuler(ActionEvent event) {
    }

    @FXML
    private void handleModifier(ActionEvent event) {
        EvenementService es=EvenementService.getInstance();
      Evenement e=new Evenement();
      e.setTitre(textTitre.getText());
      e.setEmplacement(textEmplacement.getText());
      e.setPrix(Integer.parseInt(textPrix.getText()));
      e.setFondation(textFondation.getText());
      e.setDate_event(textDate.getText());
      e.setDuree(textDuree.getText());
      e.setNbMaxParticipants(Integer.parseInt(textNbMax.getText()));
      e.setImage_event("test/test");
      es.updateEvenement(e);
    }
    
}
