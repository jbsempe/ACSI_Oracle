/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.dao.UtilisateurDAO;
import com.model.Utilisateur;
import java.io.IOException;
import java.net.URL;
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
    
    private List<Utilisateur> listUtilisateur = new ArrayList();
    private List<Utilisateur> listUtilisateurToday = new ArrayList();
    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listUtilisateur = utilisateurDAO.listUtilisateur();
        totalUsers.setText(""+listUtilisateur.size());
        
        Date date = new Date();
        listUtilisateurToday = utilisateurDAO.listUtilisateurByDate(date);
        usersToday.setText(""+listUtilisateurToday.size());
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
