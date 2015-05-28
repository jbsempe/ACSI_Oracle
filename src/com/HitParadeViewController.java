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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sébastien
 */
public class HitParadeViewController implements Initializable {
    
    @FXML
    private Hyperlink backLink;
    @FXML
    private Hyperlink advanceStatsLink;
    @FXML
    private Label totalVisites;
    @FXML
    private Label totalVisitesLabel;
    @FXML
    private TableView<Article> articleStatTable;
    @FXML
    private TableColumn<Article, String> articleNameStatColumn;
    @FXML
    private TableColumn<Article, String> articleRefStatColumn;
    @FXML
    private TableColumn<Article, Integer> articleViewsStatsColumn;
    
    private List<Article> listArticle = new ArrayList();
    private List<Consulter> listConsulter = new ArrayList();
    private ArticleDAO articleDAO = new ArticleDAO();
    private ConsulterDAO consulterDAO = new ConsulterDAO();
    
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
            totalVisites.setVisible(true);
            totalVisitesLabel.setVisible(true);
        }
        
        listArticle = articleDAO.listArticle();
        listConsulter = consulterDAO.listConsulter();
        articleNameStatColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("arLabel"));
        articleRefStatColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("arRef"));
        articleViewsStatsColumn.setCellValueFactory(new PropertyValueFactory<Article, Integer>("nbVisite"));
        
        articleStatTable.getItems().setAll(listArticle);
        
        totalVisites.setText(""+listConsulter.size());
        
    }
    
    @FXML
    public void openAdvanceStats(ActionEvent event) throws IOException{
        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("view/ArticleUtilisateurStatsView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("ACSI - Statistiques avancées");
        stage.setScene(scene);
        stage.show();
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
