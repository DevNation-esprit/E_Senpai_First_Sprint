/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.Database;
import entities.Participation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hiche
 */
public class ParticipationService {

    private Statement st;
    private ResultSet rs;
    private static ParticipationService instance;

    private ParticipationService() {
        Database cs = Database.getInstance();
        try {
            st = cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ParticipationService getInstance() {
        if (instance == null) {
            instance = new ParticipationService();
        }
        return instance;
    }

    public List<Participation> getAllParticipation() {
        String req = "select * from participation";
        List<Participation> list = new ArrayList<>();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Participation P = new Participation();
                P.setId_user(rs.getInt("id_user"));
                P.setId_event(rs.getInt("id_evenement"));
                list.add(P);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
        public List<Participation> getAllParticipationObservable() {
        String req = "select * from participation";
            ObservableList<Participation> list=FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Participation P = new Participation();
                P.setId_user(rs.getInt("id_user"));
                P.setId_event(rs.getInt("id_evenement"));
                list.add(P);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        
        
       public void insertParticipation(Participation p) {
        String req = "insert into participation (id_user,id_evenement) values ('" + p.getId_user()+ "','" + p.getId_event()+  "')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);

        }

    } 
        
        
public void deleteParticipation(Participation P) {
        String req = "delete from participation where id=" + P.getId_event();
        if (P != null) {
            try {

                st.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Service n'existe pas");
        }

    }




 public boolean updateParticipation(Participation P) {
        String req = "Update participation set id_user='" + P.getId_user() + "'id_evenement='" + P.getId_event() + "'where id_evenement="+P.getId_event()+"'and id_user='"+P.getId_user();
        try {
            if (st.executeUpdate(req) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }


}
