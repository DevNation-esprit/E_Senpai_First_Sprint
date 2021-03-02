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
public class Test extends Quiz{
    private int nbEtudiantsPasses ;
    private int nbEtudiantsAdmis ;
    
    public Test() {
        
    }
    
    public Test(int id,String sujet,ArrayList<Question> questions){
        super(id, sujet, questions);
    }
    
    public Test(int id ,String sujet,int nbEtudiantsPasses,int nbEtudiantsAdmis){
        super(id, sujet);
        this.nbEtudiantsAdmis = nbEtudiantsAdmis ;
        this.nbEtudiantsPasses = nbEtudiantsPasses ;
    }

    public Test(int nbEtudiantsPasses, int nbEtudiantsAdmis, int id, String sujet, ArrayList<Question> questions) {
        super(id, sujet, questions);
        this.nbEtudiantsPasses = nbEtudiantsPasses;
        this.nbEtudiantsAdmis = nbEtudiantsAdmis;
    } 
    
    public int getNbEtudiantsPasses() {
        return nbEtudiantsPasses;
    }

    public int getNbEtudiantsAdmis() {
        return nbEtudiantsAdmis;
    }

    public void setNbEtudiantsPasses(int nbEtudiantsPasses) {
        this.nbEtudiantsPasses = nbEtudiantsPasses;
    }

    public void setNbEtudiantsAdmis(int nbEtudiantsAdmis) {
        this.nbEtudiantsAdmis = nbEtudiantsAdmis;
    }
    
    
}
