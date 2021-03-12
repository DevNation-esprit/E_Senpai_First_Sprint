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
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
    private SplitPane splitPane;
    @FXML
    private AnchorPane leftPane;
    @FXML
    private Button blogBtn;
    @FXML
    private AnchorPane rightPane;
    @FXML
    private AnchorPane accueilPane;

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
    private void handleTestQuizBtn(ActionEvent event) {
    }

    @FXML
    private void handleChatBtn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Discussion.fxml"));

            Scene scene = new Scene(loader.load());

            DiscussionController ds = loader.getController();
            ds.current(currentUser);

            Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            oldStage.setScene(scene);
            oldStage.show();

        } catch (IOException ex) {
            Logger.getLogger(DiscussionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleDeconnectBtn(ActionEvent event) {
    }

    @FXML
    private void handleProfilBtn(ActionEvent event) {
    }

    @FXML
    private void HandleFormationsBtn(ActionEvent event) {
    }

    @FXML
    private void handleBlogBtn(ActionEvent event) {
    }

}
