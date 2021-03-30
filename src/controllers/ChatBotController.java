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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import services.ChatBot;
import java.lang.Math;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author 21656
 */
public class ChatBotController implements Initializable {

    @FXML
    private VBox vbox_msg;
    @FXML
    private TextField text_field;
    @FXML
    private Button retour_btn;
    @FXML
    private Button envoi_msg;

    private ChatBot ChatBot;

    String[][] str;

    User currentUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.ChatBot = new ChatBot();
        str = ChatBot.getChat();
    }

    public void current(User u) {
        currentUser = u;
    }

    @FXML
    private void handleRetourBtn(ActionEvent event) {
    }

    @FXML
    private void handleEnvoiMsg(ActionEvent event) {
    }

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String msg = text_field.getText();
            text_field.setText("");

            byte response = 0;

            int j = 0; //Le groupe en question
            while (response == 0) {
                if (ChatBot.in(msg.toLowerCase(), str[j * 2])) {

                    response = 2;
                    int r = (int) Math.floor(Math.random() * str[(j * 2) + 1].length);
                    Label lbl = new Label("Walid : " + str[(j * 2) + 1][r]);
                    vbox_msg.getChildren().add(lbl);
                }
                j++;
                if (j * 2 == str.length - 1 && response == 0) {
                    response = 1;
                }
            }
//            Default
            if (response == 1) {
                int r = (int) Math.floor(Math.random() * str[str.length - 1].length);
                Label lbl = new Label("Walid : " + str[str.length - 1][r]);
                vbox_msg.getChildren().add(lbl);

            }

        }
    }

}
