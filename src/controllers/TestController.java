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
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import services.QuestionDao;
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
    @FXML
    private TableColumn<Quiz, Integer> colidQuiz;
    @FXML
    private TableColumn<Quiz, Integer> colIdF;
    @FXML
    private TableColumn<Quiz, String> colSujet;
    @FXML
    private TableColumn<Question, Integer> colIdquestion;
    @FXML
    private TableColumn<Question, Integer> colQuiz;
    @FXML
    private TableColumn<Question, String> colQposee;
    @FXML
    private TableColumn<Question, String> colRep;
    @FXML
    private TableColumn<Question, String> colMrep1;
    @FXML
    private TableColumn<Question, String> colMrep2;
    @FXML
    private TableColumn<Question, String> colMrep3;
    @FXML
    private TableColumn<Question, Integer> colNote;
    @FXML
    private TableView<Quiz> tvQuiz;
    @FXML
    private TableView<Question> tvQuestion;
    @FXML
    private TextField tfSujetShow;
    @FXML
    private TextField tfQposeeShow;
    @FXML
    private TextField tfRcorrShow;
    @FXML
    private TextField tfMrep1Show;
    @FXML
    private TextField tfMrep2Show;
    @FXML
    private TextField tfMrep3Show;
    @FXML
    private TextField tfMrepNoteShow;
    @FXML
    private Button btnUpdateItem;
    @FXML
    private Button btnDeleteItem;
    @FXML
    private ComboBox<String> comboApplyOn;
    @FXML
    private Tab listQuiz;
    @FXML
    private Button btnUpdateAdd;

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
            showAddedQuestion(currentQuestionIndex);
        }
        else{
            showAddedQuestion(0);
        }
    }

    @FXML
    private void nextQuestion(ActionEvent event) {
        if(currentQuestionIndex >=0 && currentQuestionIndex < q.getQuestions().size()-1){
            currentQuestionIndex += 1 ;
            showAddedQuestion(currentQuestionIndex);
        }else{
            showAddedQuestion(q.getQuestions().size()-1);
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
            if(q.addQuestion(question )){
                 showAddedQuestion(q.getQuestions().size()-1);
                 currentQuestionIndex = q.getQuestions().size() - 1 ;
            }
            else{
                q.getQuestion(currentQuestionIndex).setQuestionPosee(qPosee);
                q.getQuestion(currentQuestionIndex).setReponseCorrecte(rCorrecte);
                q.getQuestion(currentQuestionIndex).setReponseFausse1(mRep1);
                q.getQuestion(currentQuestionIndex).setReponseFausse2(mRep2);
                q.getQuestion(currentQuestionIndex).setReponseFausse3(mRep3);
                q.getQuestion(currentQuestionIndex).setNote(note);
                
            }
            
            tfQposee.setText("");
            tfRcorrecte.setText("");
            tfMrep1.setText("");
            tfMrep2.setText("");
            tfMrep3.setText("");
            tfnote.setText("");
           

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
            
            q = new  Quiz() ;
            
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
    
    public void showAddedQuestion(int index){
        Question question = q.getQuestion(index ) ;
        labelQuestion.setText(question.getQuestionPosee());
        rbtn1.setText(question.getReponseCorrecte());
        rbtn1.setSelected(true);
        rbtn2.setText(question.getReponseFausse1());
        rbtn3.setText(question.getReponseFausse2());
        rbtn4.setText(question.getReponseFausse3());
    }
    
     @FXML
    private void displayTest(Event event) {
        showTest();
        ObservableList<String> options = FXCollections.observableArrayList("Quiz","Question");
        comboApplyOn.setItems(options);
    }
    
    public void showTest(){
       QuizDao qdao = QuizDao.getInstance() ;
       ObservableList<Quiz> listQuiz = qdao.displayQuizList(currentUser.getId() );
       
       tvQuiz.setItems(listQuiz);
       colidQuiz.setCellValueFactory(new PropertyValueFactory<>("id"));
       colSujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
       colIdF.setCellValueFactory(new PropertyValueFactory<>("id"));
       
        ArrayList<Question> questions = new ArrayList<>() ;
       
       for(Quiz quiz : listQuiz)
          questions.addAll(quiz.getQuestions()) ;
       
       ObservableList<Question> listQuestion = FXCollections.observableArrayList();
       
       for(Question qt : questions)
           listQuestion.add(qt );
       
       
        tvQuestion.setItems(listQuestion);
        colIdquestion.setCellValueFactory(new PropertyValueFactory<>("id"));
        colQposee.setCellValueFactory(new PropertyValueFactory<>("questionPosee"));
        colRep.setCellValueFactory(new PropertyValueFactory<>("reponseCorrecte"));
        colMrep1.setCellValueFactory(new PropertyValueFactory<>("reponseFausse1"));
        colMrep2.setCellValueFactory(new PropertyValueFactory<>("reponseFausse2"));
        colMrep3.setCellValueFactory(new PropertyValueFactory<>("reponseFausse3"));
        colNote.setCellValueFactory(new PropertyValueFactory<>("note"));
       
     //   System.out.println(listQuestion.size());
        
       
    }

    @FXML
    private void updateTest(ActionEvent event) {
        String option = comboApplyOn.getValue() ;
        if(option == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Quiz");
            alert.setHeaderText(null);
            alert.setContentText("veullez selectionner l'objet !!!");
            alert.show();
        }
        else{
            if(option.toLowerCase().equals("question")){
                  String qPosee = tfQposeeShow.getText() ;
                  String rCorrecte = tfRcorrShow.getText() ;
                  String mRep1 = tfMrep1Show.getText() ;
                  String mRep2 = tfMrep2Show.getText() ;
                  String mRep3 = tfMrep3Show.getText() ;
                  
                  if(!qPosee.isEmpty() && !rCorrecte.isEmpty() && !mRep1.isEmpty() && !mRep2.isEmpty() && !mRep3.isEmpty()
                            && !tfMrepNoteShow.getText().isEmpty()){                      

                        int note = Integer.parseInt(tfMrepNoteShow.getText()) ;
                        MouseEvent e = null ;
                        Question selectedQuestion = showSelectedItem(e); 
                        Question question = new Question(qPosee, rCorrecte, mRep1, mRep2, mRep3, note) ;
                        question.setId(selectedQuestion.getId());
                        QuestionDao qqdao = QuestionDao.getInstance() ;
                        qqdao.updateQuestion(question, "quiz") ;
                        showTest();
                        
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("update Question");
                        alert.setHeaderText(null);
                        alert.setContentText("Question mise à jour !");
                        alert.show();

                        tfQposeeShow.setText("");
                        tfRcorrShow.setText("");
                        tfMrep1Show.setText("");
                        tfMrep2Show.setText("");
                        tfMrep3Show.setText("");
                        tfMrepNoteShow.setText("");

                    }else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("modifier Question");
                        alert.setHeaderText(null);
                        alert.setContentText("veuillez remplir tous les champs!");
                        alert.show();
                    }                 
             }
            
            if(option.toLowerCase().equals("quiz")){
                 if(tfSujetShow.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Update Quiz");
                    alert.setHeaderText(null);
                    alert.setContentText("veullez selectionner le quiz à modifier !!!");
                    alert.show();
                }
                else{
                     Quiz quiz = new Quiz() ;
                     MouseEvent e = null ;
                     Quiz selectedQuiz = showSelectedQuiz(e) ;
                     quiz.setSujet(tfSujetShow.getText());
                     quiz.setId(selectedQuiz.getId());                       
                     
                     QuizDao qdao = QuizDao.getInstance() ;
                     if(qdao.updateQuiz(quiz, currentUser.getId())){
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                         alert.setTitle("update Quiz");
                         alert.setHeaderText(null);
                         alert.setContentText("Quiz mis à jour !");
                         alert.show();
                         showTest();
                     }
                 }
            }
            
        }          
    }

    @FXML
    private void deleteTest(ActionEvent event) {
         String option = comboApplyOn.getValue() ;
        if(option == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Quiz");
            alert.setHeaderText(null);
            alert.setContentText("veullez selectionner l'objet !!!");
            alert.show();
        }
        else{
            if(option.toLowerCase().equals("quiz")){
                if(tfSujetShow.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Delete Quiz");
                    alert.setHeaderText(null);
                    alert.setContentText("veullez selectionner le quiz à supprimer !!!");
                    alert.show();
                }
                else{
                    MouseEvent e = null ;
                    Quiz quiz = showSelectedQuiz(e);
                   
                    Alert.AlertType type = Alert.AlertType.CONFIRMATION ;
                    Alert alert = new Alert(type,"" );
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.getDialogPane().setContentText("Cette action suprimera le quiz et toutes ces questions");
                    alert.getDialogPane().setHeaderText("Supprimer Quiz");
                   // alert.show(); 
                    
                    Optional<ButtonType> result =  alert.showAndWait() ;
                    if(result.get() == ButtonType.OK){
                        QuizDao qdao = QuizDao.getInstance() ;
                        qdao.deleteQuiz(quiz);
                        showTest();
                        
                        tfQposeeShow.setText("");
                        tfRcorrShow.setText("");
                        tfMrep1Show.setText("");
                        tfMrep2Show.setText("");
                        tfMrep3Show.setText("");
                        tfMrepNoteShow.setText("");
                    }
                }
            }
            
           if(option.toLowerCase().equals("question")){
                  String qPosee = tfQposeeShow.getText() ;
                  String rCorrecte = tfRcorrShow.getText() ;
                  String mRep1 = tfMrep1Show.getText() ;
                  String mRep2 = tfMrep2Show.getText() ;
                  String mRep3 = tfMrep3Show.getText() ;                  
                  
                  if(qPosee.isEmpty() && rCorrecte.isEmpty() && mRep1.isEmpty() && mRep2.isEmpty() && mRep3.isEmpty()
                            && tfnote.getText().isEmpty()){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Delete Question");
                        alert.setHeaderText(null);
                        alert.setContentText("veullez selectionner la question à supprimer !!!");
                        alert.show();
                  }
                  else{
                        MouseEvent e = null ;
                        Question question = showSelectedItem(e);

                        Alert.AlertType type = Alert.AlertType.CONFIRMATION ;
                        Alert alert = new Alert(type,"" );
                        alert.initModality(Modality.APPLICATION_MODAL);
                        alert.getDialogPane().setContentText("Cette action suprimera la question");
                        alert.getDialogPane().setHeaderText("Supprimer Question");
                       // alert.show(); 

                        Optional<ButtonType> result =  alert.showAndWait() ;
                        if(result.get() == ButtonType.OK){
                            QuestionDao qdao = QuestionDao.getInstance();
                            qdao.deleteQuestion(question, "quiz");
                            showTest();
                            
                            tfQposeeShow.setText("");
                            tfRcorrShow.setText("");
                            tfMrep1Show.setText("");
                            tfMrep2Show.setText("");
                            tfMrep3Show.setText("");
                            tfMrepNoteShow.setText("");
                            
                    }                 
                }             
            }
        }
    }

    @FXML
    private Question showSelectedItem(MouseEvent event) {
        Question question = tvQuestion.getSelectionModel().getSelectedItem() ;
        
       tfQposeeShow.setText(question.getQuestionPosee());
       tfRcorrShow.setText(question.getReponseCorrecte());
       tfMrep1Show.setText(question.getReponseFausse1());
       tfMrep2Show.setText(question.getReponseFausse2());
       tfMrep3Show.setText(question.getReponseFausse3());
       tfMrepNoteShow.setText(question.getNote()+"");
       
       return question ;
                   
    }

    @FXML
    private Quiz showSelectedQuiz(MouseEvent event) {
        Quiz quiz = tvQuiz.getSelectionModel().getSelectedItem() ;
        ArrayList<Question> questions = quiz.getQuestions() ;
        Question quest = questions.get(0) ;
       
       tfSujetShow.setText(quiz.getSujet());
       /*tfQposeeShow.setText(quest.getQuestionPosee());
       tfRcorrShow.setText(quest.getReponseCorrecte());
       tfMrep1Show.setText(quest.getReponseFausse1());
       tfMrep2Show.setText(quest.getReponseFausse2());
       tfMrep3Show.setText(quest.getReponseFausse3());
       tfMrepNoteShow.setText(quest.getNote()+"");*/
       
       return quiz ;
        
    }   

    @FXML
    private void updateAddQuestion(ActionEvent event) {
        if(tfSujetShow.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ajouter Question");
            alert.setHeaderText(null);
            alert.setContentText("veullez selectionner le quiz où ajouter la question !!!");
            alert.show();
        }
        else{
            Quiz quiz = showSelectedQuiz(null) ;
            
            String qPosee = tfQposeeShow.getText() ;
            String rCorrecte = tfRcorrShow.getText() ;
            String mRep1 = tfMrep1Show.getText() ;
            String mRep2 = tfMrep2Show.getText() ;
            String mRep3 = tfMrep3Show.getText() ;
            
            boolean exists = false ;
            for(Question qq : quiz.getQuestions()){
                if(qq.getQuestionPosee().equals(qPosee) && qq.getReponseCorrecte().equals(rCorrecte) &&
                        qq.getReponseFausse1().equals(mRep1) && qq.getReponseFausse2().equals(mRep2)){
                    exists = true ;
                   // break ;
                }
            }
            
            if(exists){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ajouter Question");
                alert.setHeaderText(null);
                alert.setContentText("la question existe déjà!!!");
                alert.show();
            }
            if(!exists){
                if(!qPosee.isEmpty() && !rCorrecte.isEmpty() && !mRep1.isEmpty() && !mRep2.isEmpty() && !mRep3.isEmpty()
                            && !tfMrepNoteShow.getText().isEmpty()){
                    int note = Integer.parseInt(tfMrepNoteShow.getText()) ;
                    
                    Question question = new Question(qPosee, rCorrecte, mRep1, mRep2, mRep3, note) ;
                    QuestionDao qdao = QuestionDao.getInstance() ;
                    qdao.insertQuestion(question, quiz.getId(), "quiz");
                    showTest(); 
                    
                    tfQposeeShow.setText("");
                    tfRcorrShow.setText("");
                    tfMrep1Show.setText("");
                    tfMrep2Show.setText("");
                    tfMrep3Show.setText("");
                    tfMrepNoteShow.setText("");
                    tfSujetShow.setText("");
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ajouter Question");
                    alert.setHeaderText(null);
                    alert.setContentText("veuillez rensigner tous les champs!!!");
                    alert.show();
                }
            }
        }
    }
      
}
