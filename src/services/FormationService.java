/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import controllers.AccueilController;
import database.Database;
import entities.Abonnement;
import entities.Formation;
import entities.Slide;
import entities.User;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author wajdi
 */
public class FormationService {

    private static FormationService instance;
    private Statement st;
    private ResultSet rs;

    public FormationService() {
        Database cs = Database.getInstance();
        try {
            st = cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static FormationService getInstance() {
        if (instance == null) {
            instance = new FormationService();
        }
        return instance;
    }

    public List<Formation> getAllFormation() {
        String req = "select * from formation";
        List<Formation> list = new ArrayList<>();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Formation p = new Formation();
                p.setId_formateur(rs.getInt(2));
                p.setId(rs.getInt(1));
                p.setTitre(rs.getString("titre"));
                p.setNote(rs.getInt("note"));
                p.setDescription(rs.getString("description"));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<Formation> getAllFormationObservable() {
        String req = "select * from formation";
        ObservableList<Formation> list = FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Formation p = new Formation();
                p.setId(rs.getInt(1));
                p.setId_formateur(rs.getInt(2));
                p.setTitre(rs.getString("titre"));
                p.setNote(rs.getInt("note"));
                p.setDescription(rs.getString("description"));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<Formation> getFormationByIdFormateur(int id_formateur) {
        String req = "select * from formation  where id_formateur  =" + id_formateur;
        ObservableList<Formation> list = FXCollections.observableArrayList();
        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Formation p = new Formation();
                p.setId(rs.getInt(1));
                p.setId_formateur(rs.getInt(2));
                p.setTitre(rs.getString("titre"));
                p.setNote(rs.getInt("note"));
                p.setDescription(rs.getString("description"));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Formation getFormationMaxByIdFormateur(int id_formateur) {
        String req = "select MAX(id) from formation where id_formateur = " + id_formateur ;
        Formation f = new Formation();
        try {
            rs = st.executeQuery(req);
            rs.next();
            f.setId(rs.getInt(1));

        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return f;
    }

    public void insertFormation(Formation f) {
        String req = "insert into formation (id_formateur ,titre,note,description)values ('" + f.getId_formateur().get() + "','" + f.getTitre().get() + "','" + f.getNote().get() + "','" + f.getDescription().get() + "')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
 
    

    public void DeleteFormation(int id, int id_formateur) {
        String req = "DELETE FROM formation WHERE id  = " + id + "&& id_formateur  = " + id_formateur;
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void UpdateFormation(Formation f) {
        String req = "update formation set id_formateur ='" + f.getId_formateur().get() + "',titre ='" + f.getTitre().get() + "',note ='" + f.getNote().get() + "',description ='" + f.getDescription().get() + "'where id='" + f.getId().get() + "'";

        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public ObservableList<Formation> getFormationByTitreObservable(String titre) {
        String req = "select * from formation where titre LIKE '%" + titre + "%'";
        ObservableList<Formation> list = FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Formation p = new Formation();
                p.setId(rs.getInt(1));
                p.setId_formateur(rs.getInt(2));
                p.setTitre(rs.getString("titre"));
                p.setNote(rs.getInt("note"));
                p.setDescription(rs.getString("description"));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<Formation> getFormationUserByTitreObservable(String titre, int id_formateur) {
        String req = "select * from formation where titre LIKE '%" + titre + "%'&& id_formateur  = " + id_formateur;
        ObservableList<Formation> list = FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Formation p = new Formation();
                p.setId(rs.getInt(1));
                p.setId_formateur(rs.getInt(2));
                p.setTitre(rs.getString("titre"));
                p.setNote(rs.getInt("note"));
                p.setDescription(rs.getString("description"));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FormationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
