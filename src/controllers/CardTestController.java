/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Quiz;
import entities.Test;
import entities.User;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import services.QuizDao;

/**
 * FXML Controller class
 *
 * @author damos
 */
public class CardTestController implements Initializable {

    @FXML
    private VBox testBox;
    @FXML
    private Label lbType;
    @FXML
    private Label lbSujet;
    @FXML
    private Label totalQuestion;
    @FXML
    private Label formateur;
    @FXML
    private ImageView formateurImage;
    @FXML
    private Label testId;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(Quiz q,User u){
        if(q instanceof Quiz){
            lbType.setText("Quiz");
            lbSujet.setText(q.getSujet());
            totalQuestion.setText(q.getQuestions().size() + "  Questions");
            formateur.setText("par Mr/Mme " + u.getNom() +" " + u.getPrenom());
            testId.setText(q.getId()+ "");
            testId.setVisible(false);
        }
        else if(q instanceof Test){
            Test t = (Test)q ;
            lbType.setText("Test");
            lbSujet.setText(t.getSujet());
            totalQuestion.setText(t.getQuestions().size()+ " Questions");
            formateur.setText("par Mr/Mme " + u.getNom() +" " + u.getPrenom());
            testId.setText(t.getId()+"");
            testId.setVisible(false);
            
        }
    }

    @FXML
    private void getClickedQuiz(MouseEvent event) {
        int idTest = Integer.parseInt(testId.getText()) ;
        String type,sujet,nomFormateur ;
        type = lbType.getText() ;
        sujet = lbSujet.getText() ;
        nomFormateur = formateur.getText() ;
        
        
        Alert.AlertType alertType = Alert.AlertType.CONFIRMATION ;
        Alert alert = new Alert(alertType,"" );
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.getDialogPane().setContentText("Veuillez comfirmez pour passer ce " + type);
        alert.getDialogPane().setHeaderText("Passer Quiz/Test");
        
        Optional<ButtonType> result =  alert.showAndWait() ;
        if(result.get() == ButtonType.OK){
          if(type.toLowerCase().equals("quiz")){
              QuizDao qdao = QuizDao.getInstance() ;
              Quiz quiz = qdao.getQuizById(idTest) ;
              System.out.println(quiz.getQuestions().size());
          }
        }
    }
}
