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
            if(username.getText().equals(user.getUtUsername())){
                if(password.getText().equals(Hash.decrypt(user.getUtPass()).split("-")[0])){
                    Utilisateur currentUser = new Utilisateur();
                    currentUser.setUtNom(user.getUtNom());
                    currentUser.setUtPrenom(user.getUtPrenom());
                    currentUser.setUtIsadmin(user.getUtIsadmin());
                    ((Node)event.getSource()).getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("view/MainView.fxml"));
                    loader.load();
                    Parent p = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(p));
                    MainViewController mainView = loader.getController();
                    mainView.setCurrentUser(currentUser);
                    stage.show();
                } else{
                    message.setText("Mot de passe incorrect.");// Non géré
                }
            } else{
                message.setText("Utilisateur inconnu ou mot de passe incorrect.");
            }
        }
    }
    
}
