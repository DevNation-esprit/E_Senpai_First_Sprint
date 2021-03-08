/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Question;
import entities.Quiz;
import entities.Session;
import entities.Test;
import entities.User;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;

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
    private HashMap<String,String> reponses ;
    private int note = 0 , nbrReponse = 0;
    @FXML
    private Label lbResultat;
    @FXML
    private Button btnReessayer;
    @FXML
    private Button btnVoirReponses;
    @FXML
    private Label lbpoints;
    @FXML
    private Label lbRemaining;
    @FXML
    private Button btnPrevAnswer;
    @FXML
    private Button btnNextAnswer;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Session s = new Session() ;
        System.out.println(Session.getUser().getNom() + " "+ Session.getUser().getPrenom() +
                " "+ Session.getUser().getId());
        
    }
 
    public void setQuiz(Quiz q){
        if(q instanceof Quiz){
            quiz = q ;
            lbSujet.setText(q.getSujet());
            showQuestion(currentQuestionIndex) ;
            reponses = new HashMap<>(quiz.getQuestions().size()) ;
        }
        else if(q instanceof Test){
            quiz = (Test)q ;       
        }
        rbtn1.setSelected(false);
        rbtn2.setSelected(false);
        rbtn3.setSelected(false);
        rbtn4.setSelected(false);
        
        lbResultat.setVisible(false);
        btnReessayer.setVisible(false);
        btnVoirReponses.setVisible(false);
        btnValider.setDisable(false);
        btnNextAnswer.setVisible(false);
        btnPrevAnswer.setVisible(false);
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
        updateSelectedRadioButton() ;
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
        
        updateSelectedRadioButton() ;
    }

    @FXML
    private void validerReponses(ActionEvent event) {
            
            Alert.AlertType type = Alert.AlertType.CONFIRMATION ;
            Alert alert = new Alert(type,"" );
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setContentText("vous avez repondu à "+ reponses.size() +"/" +quiz.getQuestions().size() +
                    " question(s) \n" + " clickez sur ok pour valider vos réponses ?");
            alert.getDialogPane().setHeaderText("validez Quiz");
            Optional<ButtonType> result =  alert.showAndWait() ;
            
            if(result.get() == ButtonType.OK){
                 btnValider.setDisable(true);
                 
                 for (Map.Entry<String, String> entry : reponses.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    
                     for (int i = 0; i < quiz.getQuestions().size(); i++) {
                         if(value.equals(quiz.getQuestion(i).getReponseCorrecte()) &&
                                 key.equals(quiz.getQuestion(i).getQuestionPosee())){
                             note += quiz.getQuestion(i).getNote() ;
                             nbrReponse += 1 ;
                         }
                         
                     }
                    
                }
                 float percent = (nbrReponse*100)/quiz.getQuestions().size() ;
                 lbResultat.setText("Pourcentage bonnes reponses "+ percent +"%" +
                                   "\n Note obténue : " + note + "/"+ quiz.getTotalNote());
                 lbResultat.setVisible(true);
                 
                 if(percent < 100){
                     btnReessayer.setVisible(true);
                     btnVoirReponses.setVisible(true);
                 }
            }
        
    }

    private void showQuestion(int index){
        Question question = quiz.getQuestion(index );
        
        qposee.setText(index + 1 +"/ " + question.getQuestionPosee());
        lbpoints.setText(question.getNote() +" point(s)");
        lbRemaining.setText((index +1) + "/" + quiz.getQuestions().size() + " total questions");
        btnNext.setVisible(true);
        btnPrev.setVisible(true);
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
       
       reponses.put(question.getQuestionPosee(),reponse) ;
    }

    private void updateSelectedRadioButton(){
         if(reponses.containsValue(rbtn1.getText())){
            rbtn1.setSelected(true);
        }
        if(reponses.containsValue(rbtn2.getText())){
            rbtn2.setSelected(true);
        }
        if(reponses.containsValue(rbtn3.getText())){
            rbtn3.setSelected(true);
        }
        if(reponses.containsValue(rbtn4.getText())){
            rbtn4.setSelected(true);
        }
    }

    @FXML
    private void ressayer(ActionEvent event) {
        btnReessayer.setVisible(false);
        btnVoirReponses.setVisible(false);
        lbResultat.setVisible(false);
        btnValider.setDisable(false);
        currentQuestionIndex = 0 ;
        showQuestion(currentQuestionIndex);
        note = 0 ;
        nbrReponse = 0 ;
        reponses = new HashMap<>() ;
    }

    @FXML
    private void afficherReponses(ActionEvent event) {
        btnValider.setDisable(true);
        btnReessayer.setVisible(false);
        btnVoirReponses.setVisible(false);
        lbResultat.setVisible(false);
        btnNextAnswer.setVisible(true);
        btnPrevAnswer.setVisible(true);
        btnNext.setVisible(false);
        btnPrev.setVisible(false);
        currentQuestionIndex = 0 ;
        note = 0;
        nbrReponse = 0 ;
        reponses = new HashMap<>() ;
        showCorrectAnswers(currentQuestionIndex);
    }
   
    private void showCorrectAnswers(int index){
        Question question = quiz.getQuestion(index );
        
        qposee.setText(index + 1 +"/ " + question.getQuestionPosee());
        lbpoints.setText(question.getNote() +" point(s)");
        lbRemaining.setText((index +1) + "/" + quiz.getQuestions().size() + " total questions");
         Random rd = new Random() ;
         int randomNumber =  rd.nextInt(4) ;
        switch (randomNumber) {
            case 0:
                rbtn1.setText(question.getReponseCorrecte());
                rbtn1.setSelected(true);
                rbtn2.setText(question.getReponseFausse1());
                rbtn3.setText(question.getReponseFausse2());
                rbtn4.setText(question.getReponseFausse3());
                break;
            case 1:
                rbtn2.setText(question.getReponseCorrecte());
                rbtn2.setSelected(true);
                rbtn1.setText(question.getReponseFausse1());
                rbtn3.setText(question.getReponseFausse2());
                rbtn4.setText(question.getReponseFausse3());
                break;
            case 2:
                rbtn3.setText(question.getReponseCorrecte());
                rbtn3.setSelected(true);
                rbtn1.setText(question.getReponseFausse1());
                rbtn2.setText(question.getReponseFausse2());
                rbtn4.setText(question.getReponseFausse3());
                break;
             case 3:
                rbtn4.setText(question.getReponseCorrecte());
                rbtn4.setSelected(true);
                rbtn1.setText(question.getReponseFausse1());
                rbtn3.setText(question.getReponseFausse2());
                rbtn2.setText(question.getReponseFausse3());
                break;   
             
            default:
                break;
        }
        
        rbtn1.setDisable(true);
        rbtn2.setDisable(true);
        rbtn3.setDisable(true);
        rbtn4.setDisable(true);
        
    }

    @FXML
    private void showPrevAnswer(ActionEvent event) {
        if(currentQuestionIndex > 0 && currentQuestionIndex < quiz.getQuestions().size()){
            currentQuestionIndex -= 1 ;
            showCorrectAnswers(currentQuestionIndex);
            btnPrevAnswer.setDisable(false);
            btnNextAnswer.setDisable(false);
        }
        else{
            btnPrevAnswer.setDisable(true);
            btnNextAnswer.setDisable(false);
            showCorrectAnswers(0);
            
        }
    }

    @FXML
    private void showNextAnswer(ActionEvent event) {
         if(currentQuestionIndex >=0 && currentQuestionIndex < quiz.getQuestions().size()-1){
            currentQuestionIndex += 1 ;
            showCorrectAnswers(currentQuestionIndex);
            btnNextAnswer.setDisable(false);
            btnPrevAnswer.setDisable(false);
        }else{
            btnPrevAnswer.setDisable(false);
            btnNextAnswer.setDisable(true);
            showCorrectAnswers(quiz.getQuestions().size()-1);          
        }
    }
}
