/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Post;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.PostService;

/**
 * FXML Controller class
 *
 * @author Methnani
 */
public class PostController implements Initializable {

    @FXML
    private TextField caption;
    @FXML
    private TextField image;
    @FXML
    private Button addbutton;

    int User_id;
    
    int idPost;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AddPost(ActionEvent event) {

        String Caption = caption.getText();
        String image_post = image.getText();
        Post p = new Post();
        if(idPost!=0){
            p.setId_post(idPost);
        }
        p.setCaption(Caption);
        p.setImage_poste(image_post);
        p.setId_user(User_id);
        PostService ps = PostService.getInstance();
        if(addbutton.getText().toLowerCase().equals("ajouter")){
            ps.ajouterPost(p);
            Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    oldStage.close();
        }
        else{
            ps.modifierPost(p);
            Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    oldStage.close();
        }

//            Parent loader = FXMLLoader.load(getClass().getResource("Showpost.fxml"));
//            //  Parent root  = loader.load();
//            Scene  scene = new Scene(loader);
//            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            window.setTitle("Les Posts");
//            window.setScene(scene);
//            window.show();
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();

    }

    public void initData(User u, String caption, String url, String action, int idPost) {
          User_id=u.getId().get();
          this.caption.setText(caption);
          this.image.setText(url);
          addbutton.setText(action);
          this.idPost=idPost;
    }

}
