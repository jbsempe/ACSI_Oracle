/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.dao.ArticleDAO;
import com.dao.UtilisateurDAO;
import com.helper.Hash;
import com.model.Article;
import com.model.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.lang.String;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sébastien
 */
public class AddArticleViewController implements Initializable {

    @FXML
    private TextField label;
    @FXML
    private TextField reference;
    @FXML
    private TextField prix;
    @FXML
    private TextField image;
    @FXML
    private Label message;

    private List<Article> listArticle = new ArrayList();
    private ArticleDAO articleDAO = new ArticleDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listArticle = articleDAO.listArticle();
      
    }
    
    @FXML
    private void addArticleAction (ActionEvent event)throws IOException, Exception{
       
       int art_id = -1;
        for(com.model.Article article_list : listArticle){
            if(art_id <= article_list.getArId()){
                art_id ++;         
            }
        }
        art_id = art_id + 1;
        short s1 = (short) art_id;
        if(label.getText().length() == 0 || reference.getText().length() == 0 || prix.getText().length() == 0 || image.getText().length() == 0){
            message.setText("Pas bien remplis !");
    
        }
        else{    
            Article article = new Article();
            article.setArId(s1);
            article.setArRef( reference.getText() );
            article.setArPrix(Double.parseDouble(prix.getText()));
            article.setArLabel(label.getText());
            article.setArImage(image.getText());

            articleDAO.create(article);

            (((Node) event.getSource()).getScene()).getWindow().hide();
            Parent parent = FXMLLoader.load(getClass().getResource("view/MainView.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("ACSI - Liste articles");
            stage.setScene(scene);
            stage.show();
        }
    }
    
    @FXML
    private void cancelAction(ActionEvent event) throws IOException{
        (((Node) event.getSource()).getScene()).getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("view/MainView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("ACSI - Liste articles");
        stage.setScene(scene);
        stage.show();
    }
}