/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Formation;
import entities.Slide;
import entities.User;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.AbonnementService;
import services.FormationService;
import services.SlideService;
import services.UserService;

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
    private Button blogBtn;
    @FXML
    private SplitPane splitPane;
    @FXML
    private AnchorPane leftPane;
    @FXML
    private AnchorPane rightPane;
    @FXML
    private ImageView notifications;
    @FXML
    private HBox hboxHeader;
    @FXML
    private AnchorPane profilPane;
    @FXML
    private AnchorPane formationsPane;
    @FXML
    private AnchorPane testPane;
    @FXML
    private AnchorPane accueilPane;
    @FXML
    private AnchorPane chatPane;
    @FXML
    private AnchorPane blogPane;
    @FXML
    private Label nameLabel;
    @FXML
    private Label bioLabel;
    @FXML
    private ImageView editBtn;
    @FXML
    private AnchorPane profilImagePane;
    @FXML
    private Button testbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accueilPane.setVisible(true);
        profilPane.setVisible(false);
        formationsPane.setVisible(false);
        testPane.setVisible(false);
        chatPane.setVisible(false);
        blogPane.setVisible(false);
    }

    public void initData(User u) {
        ImageView imageprofil;
        ImageView profilPic;
        this.currentUser = u;
        if (currentUser.getRole().get().toLowerCase().equals("formateur")) {
            if (currentUser.getSexe().get().toLowerCase().equals("homme")) {
                welcomeLabel.setText("Bienvenue Mr " + currentUser.getNom().get());
            } else {
                welcomeLabel.setText("Bienvenue Mme " + currentUser.getNom().get());
            }
        } else {
            welcomeLabel.setText("Bienvenue " + currentUser.getNom().get());
        }

        if (currentUser.getPhoto_profil().get().equals("")) {
            imageprofil = new ImageView("/assets/userRandom.png");
        } else {
            imageprofil = new ImageView(currentUser.getPhoto_profil().get());
        }
        profilPic = imageprofil;
        imageprofil.setFitHeight(30.0);
        imageprofil.setFitWidth(30.0);
        hboxHeader.getChildren().add(imageprofil);
        profilPic.setFitHeight(129);
        profilPic.setFitWidth(127);
        nameLabel.setText(currentUser.getNom().get());

        profilImagePane.getChildren().add(profilPic);
    }

    @FXML
    private void handleAccueilBtn(ActionEvent event) {
        accueilPane.setVisible(true);
        profilPane.setVisible(false);
        formationsPane.setVisible(false);
        testPane.setVisible(false);
        chatPane.setVisible(false);
        blogPane.setVisible(false);

    }

    @FXML
    private void handleTestQuizBtn(ActionEvent event) {
        accueilPane.setVisible(false);
        profilPane.setVisible(false);
        formationsPane.setVisible(false);
        testPane.setVisible(true);
        chatPane.setVisible(false);
        blogPane.setVisible(false);

    }

    @FXML
    private void handleChatBtn(ActionEvent event) {
        accueilPane.setVisible(false);
        profilPane.setVisible(false);
        formationsPane.setVisible(false);
        testPane.setVisible(false);
        chatPane.setVisible(true);
        blogPane.setVisible(false);
    }

    @FXML
    private void handleDeconnectBtn(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Authentification.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(
                    new Scene(loader.load())
            );
            stage.setTitle("E-SENPAI | E-Learning Platform");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/icon.png")));
            stage.setResizable(false);

            AuthentificationController controller = loader.getController();
            controller.changeConnected();

            Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            oldStage.close();

            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleBlogBtn(ActionEvent event) {
        accueilPane.setVisible(false);
        profilPane.setVisible(false);
        formationsPane.setVisible(false);
        testPane.setVisible(false);
        chatPane.setVisible(false);
        blogPane.setVisible(true);

    }

    @FXML
    private void handleNotifications(MouseEvent event) {
    }

    @FXML
    private void handleProfilBtn(ActionEvent event) {
        accueilPane.setVisible(false);
        profilPane.setVisible(true);
        formationsPane.setVisible(false);
        testPane.setVisible(false);
        chatPane.setVisible(false);
        blogPane.setVisible(false);
    }

    @FXML
    private void HandleFormationsBtn(ActionEvent event) {
        accueilPane.setVisible(false);
        profilPane.setVisible(false);
        formationsPane.setVisible(true);
        testPane.setVisible(false);
        chatPane.setVisible(false);
        blogPane.setVisible(false);

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/Formation.fxml"));
            Parent page2 = loader.load();
            FormationController controller = loader.getController();
             controller.initDataa(currentUser);
            Scene scene = new Scene(page2);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
              
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleEditBtn(MouseEvent event) {
    }

    @FXML
    private void handletest(ActionEvent event) {
        FormationService fr = FormationService.getInstance();
        Formation f = new Formation(2, "web", 6, "html css");
        if (event.getSource() == testbtn) {
            fr.insertFormation(f);
        }
    }

}
