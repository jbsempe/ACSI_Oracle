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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author nicolasthy
 */
public class MainViewController implements Initializable {
    
    @FXML 
    private Hyperlink newArticleLink;
    @FXML
    private Label welcomeMessage;
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
    @FXML
    private TableColumn<Article, Boolean> articleActionColumn;
    
    private List<Article> listArticle = new ArrayList();
    private ArticleDAO articleDAO = new ArticleDAO();
    
    private Utilisateur currentUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ACSIController acsi = new ACSIController();
        currentUser = acsi.getUtilisateur();
        welcomeMessage.setText("Bonjour "+currentUser.getUtPrenom());
        if(currentUser.getUtIsadmin() == 1){
            newArticleLink.setVisible(true);
        }
        
        listArticle = articleDAO.listArticle();
        articleNameColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("arLabel"));
        articleRefColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("arRef"));
        articlePriceColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("arPrix"));
        

        articleTable.getItems().setAll(listArticle);
        
        articleTable.setRowFactory( tv -> {
            TableRow<Article> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Article article = row.getItem();
                    try {
                        showArticleView(article);
                    } catch (IOException ex) {
                        Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            return row ;
        });
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
    
    public void showArticleView(Article article) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/ArticleView.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        ArticleViewController articleView = loader.getController();
        articleView.setArticle(article);
        stage.show();
    }
    
}
