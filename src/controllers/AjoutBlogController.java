/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Blog;
import entities.Post;
import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.BlogService;
import services.PostService;

/**
 * FXML Controller class
 *
 * @author Methnani
 */
public class AjoutBlogController implements Initializable {

    @FXML
    private Button ajoutOk;
    @FXML
    private TextField inputContenu;
    @FXML
    private TextField inputTitre;
    @FXML
    private TextField inputImage;
    
    int User_id;
    
    int idBlog;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleAjoutOk(ActionEvent event) {
        String titre = inputTitre.getText();
        String contenu = inputContenu.getText();
        String image_post = inputImage.getText();
        Blog p = new Blog();
        if(idBlog!=0){
            p.setId_blog(idBlog);
        }
        p.setcontenu(contenu);
        p.settitre(titre);
        p.setImage_blog(image_post);
        p.setId_user(User_id);
        BlogService ps = BlogService.getInstance();
        if(ajoutOk.getText().toLowerCase().equals("ajouter")){
            ps.ajouterBlog(p);
        }
        else{
            ps.modifierBlog(p);
        }
    }
    
    public void initData(User u, String titre, String contenu,String url, String action, int idBlog) {
          User_id=u.getId().get();
          this.inputTitre.setText(titre);
          this.inputContenu.setText(contenu);
          this.inputImage.setText(url);
          ajoutOk.setText(action);
          this.idBlog=idBlog;
    }
}
