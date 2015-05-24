/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.model.Article;
import com.model.Utilisateur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author nicolasthy
 */
public class ArticleViewController implements Initializable {
    
    @FXML
    private Label articleName;
    @FXML
    private Label articleRef;
    @FXML
    private Label articlePrix;
    @FXML
    private Button editArticle;
    @FXML
    private Button deleteArticle;
    
    private Utilisateur currentUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setArticle(Article article){
        articleName.setText(article.getArLabel());
        articleRef.setText(article.getArRef());
        articlePrix.setText(article.getArPrix());
        
        ACSIController acsi = new ACSIController();
        currentUser = acsi.getUtilisateur();
        
        if(currentUser.getUtIsadmin() == 1){
            deleteArticle.setVisible(true);
            editArticle.setVisible(true);
        }
    }
    
}
