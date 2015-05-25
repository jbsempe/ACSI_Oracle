/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.dao.ArticleDAO;
import com.dao.ConsulterDAO;
import com.model.Article;
import com.model.Consulter;
import com.model.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author nicolasthy
 */
public class MainViewController implements Initializable {
    
    @FXML
    private MenuBar menuBar;
    @FXML 
    private Hyperlink newArticleLink;
    @FXML
    private Label welcomeMessage;
    @FXML
    private TableView<Article> articleTable;
    @FXML
    private TableColumn<Article, Image> articleImageColumn;
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
        welcomeMessage.setText("Bonjour "+currentUser.ucfirst(currentUser.getUtPrenom()));
        if(currentUser.getUtIsadmin() == 1){
            newArticleLink.setVisible(true);
        }
        
        listArticle = articleDAO.listArticle();
        articleNameColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("arLabel"));
        articleRefColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("arRef"));
        articlePriceColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("arPrixWithEuro"));
        articleImageColumn.setCellValueFactory(new PropertyValueFactory<Article, Image>("arImageView"));
        

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
        Parent parent = FXMLLoader.load(getClass().getResource("view/ACSI.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) menuBar.getScene().getWindow();
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
    
    public void addArticleAction(ActionEvent event) throws IOException{
        (((Node) event.getSource()).getScene()).getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("view/AddArticleView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("ACSI - Inscription");
        stage.setScene(scene);
        stage.show();
    }
    
    public void showArticleView(Article article) throws IOException{
        
        Consulter consulter = new Consulter();
        java.util.Date date= new java.util.Date();
        Timestamp currentTimestamp = new Timestamp(date.getTime());
        System.out.println(currentTimestamp);
        consulter.setDatedebutvisite(currentTimestamp);
        consulter.setArId(article);
        consulter.setUtId(currentUser);
        
        
        ConsulterDAO consulterDAO = new ConsulterDAO();
        consulterDAO.create(consulter);
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/ArticleView.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        ArticleViewController articleView = loader.getController();
        articleView.setArticle(article);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                java.util.Date endDate= new java.util.Date();
                consulter.setDatefinvisite(new Timestamp(endDate.getTime()));
                consulterDAO.update(consulter);
                System.out.println(currentTimestamp);
            }
        });      
        stage.show();
    }
    
    @FXML
    public void openMenuConsult(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("view/HitParadeView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("ACSI - Consultations");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void openMenuUserStats(ActionEvent event) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("view/UtilisateurStatsView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setTitle("ACSI - Statistiques Utilisateur");
        stage.setScene(scene);
        stage.show();
    }
    
}
