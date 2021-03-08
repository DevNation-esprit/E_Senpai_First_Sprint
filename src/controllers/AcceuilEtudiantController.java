/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Quiz;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import services.QuizDao;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author damos
 */
public class AcceuilEtudiantController implements Initializable {
    
    private User currentUser ;
    
    @FXML
    private Label welcomeLabel;
    @FXML
    private GridPane testContainer;
    
    private List<Quiz> quiz ;
    @FXML
    private Label idEtudiant;  
    
    public void initData(User u){
        welcomeLabel.setText("Bienvenue "+u.getNom() +"  "+ u.getPrenom());   
        idEtudiant.setText(u.getId()+"");
        idEtudiant.setVisible(false);     
    }
    
    public User getCurrentUser(){
        return currentUser ;
    }
    
    private List<Quiz> getAllQuiz_Test(){
       List<Quiz> list  ;
       
        QuizDao qdao = QuizDao.getInstance() ;
        list = qdao.getAllQuiz() ;
       
       return list ;
    }
    
    private void setData(){
         quiz = new ArrayList<>(getAllQuiz_Test()) ;
        int column = 0;
        int row = 1 ;
         try {
               for(Quiz q : quiz){
                    FXMLLoader fxmlloader = new FXMLLoader() ;
                    fxmlloader.setLocation(getClass().getResource("/views/cardTest.fxml"));
                    VBox testBox = fxmlloader.load() ;
                    CardTestController cardTestController = fxmlloader.getController() ;
                    UserService us = UserService.getInstance() ;
                    User u =  us.getUserById(q.getIdFormateur()) ;
                    cardTestController.setData(q,u) ;
                    
                    if(column == 3){
                    column = 0 ;
                    ++row ;
                   }
                    
                    testContainer.add(testBox, column++, row);
                    GridPane.setMargin(testBox, new Insets(10));

                } 
              // System.out.println(idEtudiant.getText() + "j");
               
        } catch (IOException ex) {
                Logger.getLogger(AcceuilEtudiantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       setData() ;
    }
    
}

    

