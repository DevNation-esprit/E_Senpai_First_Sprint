/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.Database;
import entities.Question;
import entities.Test;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author damos
 */
public class TestDao {
    
    private static TestDao instance ;
    private Statement st ;
    private ResultSet rs ;
    
    private TestDao(){
        Database db = Database.getInstance() ;
        try {
            st = db.getConnection().createStatement() ;
        } catch (SQLException ex) {
            Logger.getLogger(TestDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static TestDao getInstance(){
        if(instance == null){
            instance = new TestDao() ;
        }
        return instance ;
    }
    
    public void insertTest(Test t,int idFormateur) {
        String query = "INSERT INTO test(id_formateur,sujet) VALUES("+ idFormateur +",'"+ t.getSujet() +"')" ;
        try {
            st.executeUpdate(query );
           
        } catch (SQLException ex) {
            Logger.getLogger(QuizDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         QuestionDao qdao = QuestionDao.getInstance() ;
            for (Question question : t.getQuestions()) {
                qdao.insertQuestion(question, t.getId(), "test");
            }
        
    }
    
    public void deleteTest(Test t) {
      String query = "DELETE from test WHERE id = "+ t.getId() +" ";
      Test test = this.getTestById(t.getId()) ;
      if(test != null){
          try {
              QuestionDao questDao = QuestionDao.getInstance() ;
              for(Question question : t.getQuestions()){
                  questDao.deleteQuestion(question, "test");
              }
              st.executeUpdate(query );
          } catch (SQLException ex) {
              Logger.getLogger(QuizDao.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    }
    
    public List<Test> displayAllTest(int idFormateur) {
        String query = "SELECT *FROM test WHERE id_formateur = "+ idFormateur+" " ;
        ArrayList<Test> listTest = new ArrayList<>() ;       
        try {
            rs = st.executeQuery(query) ;
            QuestionDao qdao = QuestionDao.getInstance() ;
            while(rs.next()){
                ArrayList<Question> questions = (ArrayList<Question>) qdao.displayAllQuestions("test", rs.getInt(1)) ;
                Test t = new Test(rs.getInt(1), rs.getString(3), questions);
                listTest.add(t) ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return listTest ;
    }
    
    public Test getTestById(int id) {
        String query = "SELECT *FROM test WHERE id = "+id+" " ;
        Test test = new Test() ;
        try {
            rs = st.executeQuery(query) ;
            QuestionDao qdao = QuestionDao.getInstance() ;
            while (rs.next()) {  
                ArrayList<Question> questions = (ArrayList<Question>) qdao.displayAllQuestions("test", rs.getInt(1)) ;
                test.setId(rs.getInt(1));
                test.setSujet(rs.getString(3));
                test.setQuestions(questions);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       return test ;
    }
    
    public boolean updateTest(Test t, int idFromateur) {
        String query = "UPDATE test SET sujet = '"+ t.getSujet() +"' " ;
        try {
            int updatedRow = st.executeUpdate(query );
            QuestionDao qdao = QuestionDao.getInstance() ;
            for(Question question : t.getQuestions()){
                qdao.updateQuestion(question, "quiz", t.getId()) ;
            }
            if(updatedRow > 0)
                return true ;
        } catch (SQLException ex) {
            Logger.getLogger(QuizDao.class.getName()).log(Level.SEVERE, null, ex);
        }
      return false ;
    }
    
}
