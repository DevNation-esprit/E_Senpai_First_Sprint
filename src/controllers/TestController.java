/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Question;
import entities.Quiz;
import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import services.QuizDao;

/**
 * FXML Controller class
 *
 * @author damos
 */
public class TestController implements Initializable {
     User currentUser;
     Quiz q =new Quiz() ;
     int currentQuestionIndex = 0;
    @FXML
    private TextField tfSujet;
    @FXML
    private TextField tfQposee;
    @FXML
    private TextField tfRcorrecte;
    @FXML
    private TextField tfMrep1;
    @FXML
    private TextField tfMrep2;
    @FXML
    private TextField tfMrep3;
    @FXML
    private TextField tfnote;
    @FXML
    private RadioButton rbtn1;
    @FXML
    private RadioButton rbtn3;
    @FXML
    private RadioButton rbtn2;
    @FXML
    private RadioButton rbtn4;
    @FXML
    private Button btnPrev;
    @FXML
    private Button btnNext;
    @FXML
    private Button btnAddQuestion;
    @FXML
    private Button btnAddTest;
    @FXML
    private Label labelQuestion;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public void initData(User u){
        this.currentUser=u;   
    }

    @FXML
    private void prevQuestion(ActionEvent event) {
        if(currentQuestionIndex > 0 && currentQuestionIndex < q.getQuestions().size()){
            currentQuestionIndex -= 1 ;
            showQuestion(currentQuestionIndex);
        }
        else{
            showQuestion(0);
        }
    }

    @FXML
    private void nextQuestion(ActionEvent event) {
        if(currentQuestionIndex >=0 && currentQuestionIndex < q.getQuestions().size()-1){
            currentQuestionIndex += 1 ;
            showQuestion(currentQuestionIndex);
        }else{
            showQuestion(q.getQuestions().size()-1);
        }
    }

    @FXML
    private void addQuestion(ActionEvent event) {
        
        String sujet = tfSujet.getText() ;
        String qPosee = tfQposee.getText() ;
        String rCorrecte = tfRcorrecte.getText() ;
        String mRep1 = tfMrep1.getText() ;
        String mRep2 = tfMrep2.getText() ;
        String mRep3 = tfMrep3.getText() ;

        if(!qPosee.isEmpty() && !rCorrecte.isEmpty() && !mRep1.isEmpty() && !mRep2.isEmpty() && !mRep3.isEmpty()
                && !tfnote.getText().isEmpty()){
            q.setSujet(tfSujet.getText());
            
            if(!sujet.isEmpty())
                tfSujet.setEditable(false);
            
            int note = Integer.parseInt(tfnote.getText()) ;
            Question question = new Question(qPosee, rCorrecte, mRep1, mRep2, mRep3, note) ;      
            q.addQuestion(question );
            showQuestion(q.getQuestions().size()-1);
            currentQuestionIndex = q.getQuestions().size() - 1 ;

        }else{
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajouter Quiz");
            alert.setHeaderText(null);
            alert.setContentText("veuillez remplir tous les champs!");
            alert.show();
        }
    }
    

    @FXML
    private void addTest(ActionEvent event) {
        if(q.getQuestions().isEmpty() || q.getQuestions().size() < 3){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajouter Quiz");
            alert.setHeaderText(null);
            alert.setContentText("Entrez au moins 3 questions pour enregistrer!");
            alert.show();
        }else{
            QuizDao qdao = QuizDao.getInstance() ;
            qdao.insertQuiz(q, currentUser.getId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajouter Quiz");
            alert.setHeaderText(null);
            alert.setContentText("Quiz ajouter avec succès !");
            alert.show();
            
            labelQuestion.setText("");
            rbtn1.setText("");
            rbtn2.setText("");
            rbtn3.setText("");
            rbtn4.setText("");
            
            tfSujet.setEditable(true);
            tfSujet.setText("");
            tfQposee.setText("");
            tfRcorrecte.setText("");
            tfMrep1.setText("");
            tfMrep2.setText("");
            tfMrep3.setText("");
            tfnote.setText("");
            
        }
    }
    
    public void showQuestion(int index){
        Question question = q.getQuestion(index ) ;
        labelQuestion.setText(question.getQuestionPosee());
        rbtn1.setText(question.getReponseCorrecte());
        rbtn1.setSelected(true);
        rbtn2.setText(question.getReponseFausse1());
        rbtn3.setText(question.getReponseFausse2());
        rbtn4.setText(question.getReponseFausse3());
    }
    
}
