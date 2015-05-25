/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.dao.ArticleDAO;
import com.dao.ConsulterDAO;
import com.dao.StatistiqueDAO;
import com.model.Article;
import com.model.Consulter;
import com.model.Statistique;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    private ImageView articleImage;
    @FXML
    private Button editArticle;
    @FXML
    private Button deleteArticle;
    
    private Utilisateur currentUser;
    
    private Article article;
    private List<Statistique> listStatistique = new ArrayList();
    private StatistiqueDAO statistiqueDAO = new StatistiqueDAO();
    private Statistique statistique;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listStatistique = statistiqueDAO.listStatistique();
    }    
    
    public void setArticle(Article article){
        articleName.setText(article.getArLabel());
        articleRef.setText(article.getArRef());
        articlePrix.setText(article.getArPrixWithEuro());
        articleImage.setImage(new Image(article.getArImage()));
        
        
        ACSIController acsi = new ACSIController();
        currentUser = acsi.getUtilisateur();
        
        if(currentUser.getUtIsadmin() == 1){
            deleteArticle.setVisible(true);
            editArticle.setVisible(true);
        }
        
        this.article = article;
    }
    
    @FXML
    public void editArticleAction(ActionEvent event) throws IOException{        
        (((Node) event.getSource()).getScene()).getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/EditArticleView.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        EditArticleViewController articleView = loader.getController();
        articleView.getArticle(this.article);
        stage.show();
    }
    
    @FXML
    public void deleteArticleAction(ActionEvent event) throws IOException{
        if (listStatistique.isEmpty()){
            Statistique statistique = new Statistique();
            statistique.setStId(1);
            statistique.setStNbcree(0);
            statistique.setStNbmodif(0);
            statistique.setStNbsuppr(1);

            statistiqueDAO.create(statistique);
        } else {
            Statistique statistique = listStatistique.get(0);
            int nb_sup = 0;
            for(com.model.Statistique statistiqueList : listStatistique){
                nb_sup = statistiqueList.getStNbsuppr();
            }
            nb_sup = nb_sup + 1;
            statistique.setStNbsuppr(nb_sup);
            statistiqueDAO.edit(statistique);
        }
        
        ArticleDAO articleDAO = new ArticleDAO();
        ConsulterDAO consulterDAO = new ConsulterDAO();
        List<Consulter> consulterList = new ArrayList();
        consulterList = consulterDAO.listConsulter();
        for(com.model.Consulter consulter : consulterList){
            if(consulter.getArId().getArId() == this.article.getArId()){
                consulterDAO.delete(consulter);
            }
        }
        articleDAO.delete(this.article);
        
        
        (((Node) event.getSource()).getScene()).getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("view/MainView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("ACSI - Liste articles");
        stage.setScene(scene);
        stage.show();
    }
    
}
