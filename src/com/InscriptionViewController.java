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
import java.math.BigDecimal;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jean-Baptiste
 */
public class InscriptionViewController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cp;
    @FXML
    private TextField password;
    @FXML
    private TextField password2;
    @FXML
    private CheckBox isAdmin;
    @FXML
    private Label message2;
    
    
    private List<Utilisateur> listUtilisateur = new ArrayList();
    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listUtilisateur = utilisateurDAO.listUtilisateur();
    } 
    
    
    @FXML
    private void inscriptionAction(ActionEvent event) throws IOException, Exception{
        
        boolean isAdmin = this.isAdmin.isSelected();
        short admin = 0;

        if(isAdmin == true){
            admin = 1;
        }
        
        int id_user = 0;
        for(com.model.Utilisateur user_list : listUtilisateur){
            if(id_user <= user_list.getUtId()){
                id_user ++;          
            }
        }
        short s1 = (short) id_user;
        if(nom.getText().length() == 0 || prenom.getText().length() == 0 || cp.getText().length() == 0 || password.getText().length() == 0){
            message2.setText("Veuillez remplir tous les champs.");
            
        }else if(password.getText() == null ? password2.getText() != null : !password.getText().equals(password2.getText())){
            message2.setText("Les mots de passes ne correspondent pas.");
        }else{    
            Date date = new Date();
            Utilisateur user = new Utilisateur();
            user.setUtId(s1);
            user.setUtNom(nom.getText());
            user.setUtPrenom(prenom.getText());
            user.setUtCp(cp.getText());
            user.setUtPass(Hash.encrypt(password.getText()));
            user.setUtHash(password.getText());
            user.setUtIsadmin(admin);
            user.setUtDateinscri(date);

            utilisateurDAO.create(user);

            (((Node) event.getSource()).getScene()).getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("view/ACSI.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("ACSI - Connexion");
            stage.setScene(scene);
            stage.show();
        }
    }
    
    @FXML
    private void cancelInscriptionAction(ActionEvent event) throws IOException{
        (((Node) event.getSource()).getScene()).getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("view/ACSI.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("ACSI - Connexion");
        stage.setScene(scene);
        stage.show();
    }
}
