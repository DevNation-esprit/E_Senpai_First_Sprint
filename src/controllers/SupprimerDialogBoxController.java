/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Dahmani
 */
public class SupprimerDialogBoxController implements Initializable {

    @FXML
    private Label suppNameLabel;
    @FXML
    private Button suppUserOk;
    @FXML
    private Button annulerSuppUser;
    
    private User currentUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void handleSuppUserOk(ActionEvent event) {
        UserService us = UserService.getInstance();
        us.deleteUser(currentUser.getId().get());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleAnnulerSuppUser(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    
    public void initData(User u){
        this.currentUser=u;
        suppNameLabel.setText(currentUser.getNom().get());
    }
    
}
