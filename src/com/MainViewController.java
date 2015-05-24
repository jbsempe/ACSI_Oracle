/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.dao.ArticleDAO;
import com.model.Article;
import com.model.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nicolasthy
 */
public class MainViewController implements Initializable {
    
    @FXML 
    private Hyperlink newArticleLink;
    @FXML
    private TableView<Article> articleTable;
    @FXML
    private TableColumn<Article, String> articleImageColumn;
    @FXML
    private TableColumn<Article, String> articleNameColumn;
    @FXML
    private TableColumn<Article, String> articleRefColumn;
    @FXML
    private TableColumn<Article, String> articlePriceColumn;
    
    private List<Article> listArticle = new ArrayList();
    private ArticleDAO articleDAO = new ArticleDAO();
    
    private Utilisateur currentUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listArticle = articleDAO.listArticle();
        articleNameColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("arLabel"));
        articleRefColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("arRef"));
        articlePriceColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("arPrix"));
        articleTable.getItems().setAll(listArticle);
    }    
    
    public void logoutUtilisateur(ActionEvent event) throws IOException{
        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("view/ACSI.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("ACSI - Connexion");
        stage.setScene(scene);
        stage.show();
    }
    
    public void setCurrentUser(Utilisateur user){
        if(user.getUtIsadmin() == 1){
            newArticleLink.setVisible(true);
        }
        this.currentUser = user;
    }
    
    public void addArticleAction(){
        
    }
    
}
