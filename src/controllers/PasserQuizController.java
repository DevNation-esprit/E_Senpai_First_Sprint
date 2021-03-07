/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Question;
import entities.Quiz;
import entities.Test;
import entities.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author damos
 */
public class PasserQuizController implements Initializable {
    
    private Quiz quiz ;
    private User currentUser ;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Label lbSujet;
    @FXML
    private Label qposee;
    @FXML
    private RadioButton rbtn1;
    @FXML
    private ToggleGroup rbtn;
    @FXML
    private RadioButton rbtn2;
    @FXML
    private RadioButton rbtn3;
    @FXML
    private RadioButton rbtn4;
    @FXML
    private Button btnPrev;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnValider;
    int currentQuestionIndex = 0;
    private HashSet<String> reponses ;
    private int note = 0 , nbrReponse = 0;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
 
    public void setQuiz(Quiz q){
        if(q instanceof Quiz){
            quiz = q ;
            lbSujet.setText(q.getSujet());
            showQuestion(currentQuestionIndex) ;
            reponses = new HashSet<>(quiz.getQuestions().size()) ;
        }
        else if(q instanceof Test){
            quiz = (Test)q ;        }
    }   
    
    public void setUser(User u){
     /*  currentUser = u ;
       welcomeLabel.setText("Welcome "+ currentUser.getNom() + " " + currentUser.getPrenom());*/
    }

    @FXML
    private void showPrevQuestion(ActionEvent event) {
       if(currentQuestionIndex > 0 && currentQuestionIndex < quiz.getQuestions().size()){
            currentQuestionIndex -= 1 ;
            showQuestion(currentQuestionIndex);
            btnPrev.setDisable(false);
            btnNext.setDisable(false);
        }
        else{
            btnPrev.setDisable(true);
            btnNext.setDisable(false);
            showQuestion(0);
            
        }
    }

    @FXML
    private void showNextQuestion(ActionEvent event) {
        if(currentQuestionIndex >=0 && currentQuestionIndex < quiz.getQuestions().size()-1){
            currentQuestionIndex += 1 ;
            showQuestion(currentQuestionIndex);
            btnNext.setDisable(false);
            btnPrev.setDisable(false);
        }else{
            btnPrev.setDisable(false);
            btnNext.setDisable(true);
            showQuestion(quiz.getQuestions().size()-1);          
        }
    }

    @FXML
    private void validerReponses(ActionEvent event) {
        
    }

    private void showQuestion(int index){
        Question question = quiz.getQuestion(index );
        
        qposee.setText(index + 1 +"/ " + question.getQuestionPosee());
         Random rd = new Random() ;
         int randomNumber =  rd.nextInt(4) ;
        switch (randomNumber) {
            case 0:
                rbtn1.setText(question.getReponseCorrecte());
                rbtn2.setText(question.getReponseFausse1());
                rbtn3.setText(question.getReponseFausse2());
                rbtn4.setText(question.getReponseFausse3());
                break;
            case 1:
                rbtn2.setText(question.getReponseCorrecte());
                rbtn1.setText(question.getReponseFausse1());
                rbtn3.setText(question.getReponseFausse2());
                rbtn4.setText(question.getReponseFausse3());
                break;
            case 2:
                rbtn3.setText(question.getReponseCorrecte());
                rbtn1.setText(question.getReponseFausse1());
                rbtn2.setText(question.getReponseFausse2());
                rbtn4.setText(question.getReponseFausse3());
                break;
             case 3:
                rbtn4.setText(question.getReponseCorrecte());
                rbtn1.setText(question.getReponseFausse1());
                rbtn3.setText(question.getReponseFausse2());
                rbtn2.setText(question.getReponseFausse3());
                break;   
             
            default:
                break;
        }
        
        rbtn1.setSelected(false);
        rbtn2.setSelected(false);
        rbtn3.setSelected(false);
        rbtn4.setSelected(false);
        
    }

    @FXML
    private void radioButtonChanged(ActionEvent event) {
         String reponse ="" ;
        if(rbtn.getSelectedToggle().equals(rbtn1)){
            reponse = rbtn1.getText() ;
            rbtn1.setSelected(true);
        }
        
        if(rbtn.getSelectedToggle().equals(rbtn2)){
            reponse = rbtn2.getText() ;
            rbtn2.setSelected(true);
        }
        
        if(rbtn.getSelectedToggle().equals(rbtn3)){
            reponse = rbtn3.getText() ;
            rbtn3.setSelected(true);
        }
        
        if(rbtn.getSelectedToggle().equals(rbtn4)){
            reponse = rbtn4.getText() ;
            rbtn4.setSelected(true);
        }
        
       Question question = quiz.getQuestions().get(currentQuestionIndex) ;
       
       reponses.add(question.getReponseCorrecte()) ;
       
       if(reponses.contains(reponse)){
           note += question.getNote() ;
           System.out.println(note);
       }
        System.out.println(reponses.size());
       
    }

    
   
    
}
