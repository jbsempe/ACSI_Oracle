/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.dao.ConsulterDAO;
import com.dao.UtilisateurDAO;
import com.model.Consulter;
import com.model.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nicolasthy
 */
public class UtilisateurStatsViewController implements Initializable {
    
    @FXML
    private Label usersToday;
    @FXML
    private Label totalUsers;
    @FXML
    private Label averageTime;
    @FXML
    private Label averageTimeLabel;
    
    private List<Utilisateur> listUtilisateur = new ArrayList();
    private List<Utilisateur> listUtilisateurToday = new ArrayList();
    private List<Consulter> listConsulter = new ArrayList();
    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    private ConsulterDAO consulterDAO = new ConsulterDAO();
    
    private long averageDateDiff;

    private Utilisateur currentUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ACSIController acsi = new ACSIController();
        currentUser = acsi.getUtilisateur();
        if(currentUser.getUtIsadmin() == 1){
            averageTime.setVisible(true);
            averageTimeLabel.setVisible(true);
        }
        listUtilisateur = utilisateurDAO.listUtilisateur();
        totalUsers.setText(""+listUtilisateur.size());
        
        Date date = new Date();
        listUtilisateurToday = utilisateurDAO.listUtilisateurByDate(date);
        usersToday.setText(""+listUtilisateurToday.size());
        
        getAverageTime();
    }    
    
    public void getAverageTime(){
        listConsulter = consulterDAO.listConsulter();
        this.averageDateDiff = 0;
        for(com.model.Consulter consulter : listConsulter){
            if(consulter.getDatefinvisite() != null){
                this.averageDateDiff = this.averageDateDiff + (consulter.getDatefinvisite().getTime() - consulter.getDatedebutvisite().getTime());
            }
        }
        System.out.println(this.averageDateDiff +", "+ listConsulter.size(  ));
        long averageDateTime = this.averageDateDiff / listConsulter.size();
        long second = (averageDateTime / 1000) % 60;
        long minute = (averageDateTime / (1000 * 60)) % 60;
        long hour = (averageDateTime / (1000 * 60 * 60)) % 24;

        String time = String.format("%02d h %02d min %02d sec", hour, minute, second);
        averageTime.setText(time+" ");
    }
    
    @FXML
    public void backAction(ActionEvent event) throws IOException{
        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("view/MainView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("ACSI - Liste articles");
        stage.setScene(scene);
        stage.show();
    }
    
}
