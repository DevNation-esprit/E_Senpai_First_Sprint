/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esenpai.main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.Mailing;

/**
 *
 * @author Dahmani
 */
public class Esenpai extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException, Exception {
        
        FXMLLoader laoder = new FXMLLoader(getClass().getResource("/views/Authentification.fxml"));
        Parent root = laoder.load();
        
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        
        primaryStage.setTitle("E-SENPAI | E-Learning Platform");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/icon.png")));
        primaryStage.setResizable(false);
        
        primaryStage.show();
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
