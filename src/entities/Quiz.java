/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;

/**
 *
 * @author damos
 */
public class Quiz {
    protected int id ;
    protected String sujet ;
    protected ArrayList<Question> questions ;
    
    public Quiz(){
        
    }
    
    public Quiz(int id, String sujet) {
        this.id = id;
        this.sujet = sujet;
        this.questions = new ArrayList<>();
    }

    public Quiz(int id, String sujet, ArrayList<Question> questions) {
        this.id = id;
        this.sujet = sujet;
        this.questions = questions;
    }
    

    public int getId() {
        return id;
    }

    public String getSujet() {
        return sujet;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }      
    
    public boolean addQuestion(Question q){
        if(!checkQuestion(q)){
            this.questions.add(q);
            return true ;
        }
        return false ;
    }
    
    public boolean checkQuestion(Question q){
        if(this.questions.contains(q)){
            return true ;
        }
     return false ;
    }
    
    public Question getQuestion(int index){
       return this.questions.get(index);
    }
    
    public void deleteQuestion(int index){
        this.questions.remove(index );
    }
    
    public void deleteQuestion(Question q){
        this.questions.remove(q );
    }
    
    
}
