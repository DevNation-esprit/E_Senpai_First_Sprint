/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Discussion;
import entities.Message;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.Icon;
import services.MessageService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author 21656
 */
public class MessageController implements Initializable {

    @FXML
    private Button envoi_msg;
    @FXML
    private TextField text_field;
    @FXML
    private VBox vbox_msg;
    private Discussion currentDiscussion;
    private User currentUser;
    @FXML
    private Button retour_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        this.initData();
    }

    public void current(Discussion d, User u) {
        MessageService ms = MessageService.getInstance();
        List<Message> list = new ArrayList<Message>();
        list = ms.getAllMessage(d.getId());
        for (Message m : list) {
            HBox hbox = new HBox(8);
            Button btn = new Button();
            Image img = new Image("assets/annuler.png");
            ImageView view = new ImageView(img);
            view.setFitHeight(10);
            view.setPreserveRatio(true);
            btn.setGraphic(view);
            btn.setOnAction(actionEvent -> {
                ms.suppMsg(m.getId());
                vbox_msg.getChildren().remove(hbox);

            });
            Label lbl = new Label();
            lbl.setText("" + m.getContenu() + " " + m.getDate_msg());
            Label lbl_nom = new Label();
            if (u.getId() == m.getUser()) {

                lbl_nom.setText("Ena " + u.getPrenom() + " : ");

            } else {

                lbl_nom.setText("Ena lekher : ");
            }
            hbox.getChildren().add(lbl_nom);
            hbox.getChildren().add(lbl);
            hbox.getChildren().add(btn);
            vbox_msg.getChildren().add(hbox);
        }
        this.currentDiscussion = d;
        this.currentUser = u;
        System.out.println("controllers.MessageController.current()" + d.toString() + u.getNom() + u.getPrenom());
    }

    public void initData(Message m) {

        MessageService ms = MessageService.getInstance();
        UserService us = UserService.getInstance();
        HBox hbox = new HBox(8);
            Button btn = new Button();
            Image img = new Image("assets/annuler.png");
            ImageView view = new ImageView(img);
            view.setFitHeight(10);
            view.setPreserveRatio(true);
            btn.setGraphic(view);
            btn.setOnAction(actionEvent -> {
                ms.suppMsg(m.getId());
                vbox_msg.getChildren().remove(hbox);

            });
            Label lbl = new Label();
            lbl.setText("" + m.getContenu() + " " + m.getDate_msg());
            Label lbl_nom = new Label();
            if (currentUser.getId() == m.getUser()) {

                lbl_nom.setText("Ena " + currentUser.getPrenom() + " : ");

            } else {

                lbl_nom.setText("Ena lekher : ");
            }
            hbox.getChildren().add(lbl_nom);
            hbox.getChildren().add(lbl);
            hbox.getChildren().add(btn);
            vbox_msg.getChildren().add(hbox);
    }

    @FXML
    private void handleEnvoiMsg(ActionEvent event) {
        MessageService ds = MessageService.getInstance();
        Message m = new Message();

        m.setContenu(text_field.getText());
        m.setDiscussion(currentDiscussion.getId());
        if (currentUser.getId() == currentDiscussion.getUser1()) {
            m.setUser(currentDiscussion.getUser1());
        } else {
            m.setUser(currentDiscussion.getUser2());
        }
        
        ds.sendMsg(m);
        text_field.setText("");
        m.setId(ds.getLastMsg(currentDiscussion.getId()).getId());
        m.setDate_msg(ds.getLastMsg(currentDiscussion.getId()).getDate_msg());
        this.initData(m);

    }

//    private void HandleAfficherMsg(ActionEvent event) {
//        MessageService ds = MessageService.getInstance();
//        List<Message> l = ds.getAllMessage(currentDiscussion.getId());
//        for (Message m : l) {
//            System.out.println(m.toString());
//        }
//    }

    @FXML
    private void handleRetourBtn(ActionEvent event) {
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

}
