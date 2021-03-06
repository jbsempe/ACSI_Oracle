/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.dao.UtilisateurDAO;
import com.helper.Hash;
import com.model.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author nicolasthy
 */
public class ACSIController implements Initializable {
    
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label message;
    
    private List<Utilisateur> listUtilisateur = new ArrayList();
    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listUtilisateur = utilisateurDAO.listUtilisateur();
    }    
    
    @FXML
    private void loginAction(ActionEvent event) throws IOException, Exception{
        for(com.model.Utilisateur user : listUtilisateur){
            if(username.getText().toLowerCase().equals(user.getUtUsername())){
                if(password.getText().equals(Hash.decrypt(user.getUtPass()).split("-")[0])){
                    Utilisateur currentUser = new Utilisateur();
                    currentUser.setUtId(user.getUtId());
                    currentUser.setUtNom(user.getUtNom());
                    currentUser.setUtPrenom(user.getUtPrenom());
                    currentUser.setUtIsadmin(user.getUtIsadmin());
                    currentUser.setUtCp(user.getUtCp());
                    setUtilisateur(currentUser);
                    ((Node)event.getSource()).getScene().getWindow().hide();
                    Parent parent = FXMLLoader.load(getClass().getResource("view/MainView.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setTitle("ACSI - Liste articles");
                    stage.setScene(scene);
                    stage.show();
                } else{
                    message.setText("Mot de passe incorrect.");// Non géré
                }
            } else{
                message.setText("Utilisateur inconnu ou mot de passe incorrect.");
            }
        }
    }
    
    @FXML
    private void showInscriptionAction(ActionEvent event) throws IOException, Exception{
        (((Node) event.getSource()).getScene()).getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("view/InscriptionView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("ACSI - Inscription");
        stage.setScene(scene);
        stage.show();
    }
    
    public Utilisateur getUtilisateur() {
        Preferences prefs = Preferences.userNodeForPackage(ACSIController.class);
        Utilisateur user = new Utilisateur();
        
        int utId = prefs.getInt("utId", 0);
        String utNom = prefs.get("utNom", null);
        String utPrenom = prefs.get("utPrenom", null);
        int utIsadmin = prefs.getInt("utIsadmin", 0);
        String utCp = prefs.get("utCp", null);
        
        
        if(utNom != null && utPrenom != null){
            short id = (short)utId;
            user.setUtId(id);
            user.setUtNom(utNom);
            user.setUtPrenom(utPrenom);
            short isAdmin = (short)utIsadmin;
            user.setUtIsadmin(isAdmin);
            user.setUtCp(utCp);
            
            return user;
        }
        
        return null;
    }
    
    public void setUtilisateur(Utilisateur user) {
        Preferences prefs = Preferences.userNodeForPackage(ACSIController.class);
        prefs.putInt("utId", user.getUtId());
        prefs.put("utNom", user.getUtNom());
        prefs.put("utPrenom", user.getUtPrenom());
        prefs.putInt("utIsadmin", user.getUtIsadmin());
        prefs.put("utCp", user.getUtCp());
    }
}
