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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author hiche
 */
public class ConsulterEvenementController implements Initializable {

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
    private Button btnEvenement;
    @FXML
    private Label labelTitre;
    @FXML
    private Label labelEmplacement;
    @FXML
    private Label labelPrix;
    @FXML
    private Label labelDate;
    @FXML
    private Label labelFondation;
    @FXML
    private Label labelDuree;
    @FXML
    private Label labelParticipant;
    private ImageView imageViewEvent;

    private Evenement ev;
    @FXML
    private AnchorPane imagePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initData(Evenement E) {
        this.ev = E;
        labelTitre.setText(ev.getTitre().get());
        labelTitre.setFont(new Font(60));
        labelEmplacement.setText(ev.getEmplacement().get());
        labelPrix.setText("" + ev.getPrix().get());
        labelDate.setText(ev.getDate_event().get());
        labelFondation.setText(ev.getFondation().get());
        labelDuree.setText(ev.getDuree().get());
        labelParticipant.setText("" + ev.getNbMaxParticipants().get());
        Image img = new Image(ev.getImage_event().get());
        Circle c = new Circle(90, 90, 120);
        c.setFill(new ImagePattern(img));
        imagePane.getChildren().add(c);
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

}
