/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Blog;
import entities.Post;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.BlogService;
import services.PostService;

/**
 * FXML Controller class
 *
 * @author Methnani
 */
public class ConsulterBlogController implements Initializable {

    @FXML
    private SplitPane splitPane;
    @FXML
    private AnchorPane leftPane;
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
    private Button blogBtn;
    @FXML
    private Button deconnectBtn;
    @FXML
    private AnchorPane rightPane;
    @FXML
    private AnchorPane profilPane;
    @FXML
    private VBox blogsVbox;
    @FXML
    private Button ajouterBlogBtn;
    
    private User currentUser;

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
    private void handleProfilBtn(ActionEvent event) {
    }

    @FXML
    private void HandleFormationsBtn(ActionEvent event) {
    }

    @FXML
    private void handleTestQuizBtn(ActionEvent event) {
    }

    @FXML
    private void handleChatBtn(ActionEvent event) {
    }

    @FXML
    private void handleBlogBtn(ActionEvent event) {
    }

    @FXML
    private void handleDeconnectBtn(ActionEvent event) {
    }

    @FXML
    private void handleAjouterBlog(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ajoutBlog.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );
        stage.setTitle("E-SENPAI | E-Learning Platform");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/icon.png")));
        stage.setResizable(false);

        AjoutBlogController controller = loader.getController();
        controller.initData(currentUser, "", "","","Ajouter",0);

        stage.show();
    }
    
    public void initData(User u){
        this.currentUser=u;
        BlogService ps = BlogService.getInstance();
        List<Blog> blogs = ps.getBlogsByUser(currentUser.getId().get());
        for (Blog p : blogs) {
            HBox hbox = new HBox(15);
            ImageView imagepost = new ImageView(p.getImage_blog());
            imagepost.setFitHeight(250);
            imagepost.setFitWidth(600);
            Label titre = new Label(p.gettitre());
            Label contenu = new Label(p.getcontenu());
            contenu.setWrapText(true);
            contenu.setMaxHeight(200);
            Button modif = new Button("Modifier");
            Button supp = new Button("Supprimer");
            hbox.getChildren().add(contenu);
            
            blogsVbox.getChildren().add(imagepost);
            blogsVbox.getChildren().add(hbox);
            blogsVbox.getChildren().add(modif);
            blogsVbox.getChildren().add(supp);
            modif.setOnAction(e -> {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ajoutBlog.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                try {
                    stage.setScene(
                            new Scene(loader.load())
                    );
                } catch (IOException ex) {
                    Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                }
                stage.setTitle("E-SENPAI | E-Learning Platform");
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/icon.png")));
                stage.setResizable(false);

                AjoutBlogController controller = loader.getController();
                controller.initData(currentUser, p.gettitre(), p.getcontenu(),p.getImage_blog(),"Modifier",p.getId_blog());

                stage.show();
            }
            );
            supp.setOnAction(e -> {
                ps.supprimerBlog(p);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/suppBlog.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                try {
                    stage.setScene(
                            new Scene(loader.load())
                    );
                } catch (IOException ex) {
                    Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                }
                stage.setTitle("E-SENPAI | E-Learning Platform");
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/icon.png")));
                stage.setResizable(false);
                stage.show();
            });

        }
//        for (Blog p : blogs) {
//            ImageView imageblog = new ImageView(p.getImage_blog());
//            Label titre = new Label(p.gettitre());
//            Label contenu = new Label(p.getcontenu());
//            blogsVbox.getChildren().add(titre);
//            blogsVbox.getChildren().add(contenu);
//            blogsVbox.getChildren().add(imageblog);
//            
//            
//        }
    }
    
}
