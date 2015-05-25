/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.dao.ArticleDAO;
import com.dao.StatistiqueDAO;
import com.model.Article;
import com.model.Statistique;
import java.io.File;
import java.io.IOException;
import static java.lang.Double.parseDouble;
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
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nicolasthy
 */
public class EditArticleViewController implements Initializable {
    
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

    private ArticleDAO articleDAO = new ArticleDAO();
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
    
    public void getArticle(Article article){
        label.setText(article.getArLabel());
        reference.setText(article.getArRef());
        prix.setText(""+article.getArPrix());
        image.setText(article.getArImage());
        
        this.article = article;
    }
    
    @FXML
    public void editArticleAction(ActionEvent event) throws IOException{
        if (listStatistique.isEmpty()){
            Statistique statistique = new Statistique();
            statistique.setStId(1);
            statistique.setStNbcree(0);
            statistique.setStNbmodif(1);
            statistique.setStNbsuppr(0);

            statistiqueDAO.create(statistique);
        } else {
            Statistique statistique = listStatistique.get(0);
            int nb_modif = 0;
            for(com.model.Statistique statistiqueList : listStatistique){
                nb_modif = statistiqueList.getStNbmodif();
            }
            nb_modif = nb_modif + 1;
            statistique.setStNbmodif(nb_modif);
            statistiqueDAO.edit(statistique);
        }
        
        this.article.setArLabel(label.getText());
        this.article.setArRef(reference.getText());
        Double articlePrix = parseDouble(prix.getText());
        this.article.setArPrix(articlePrix);
        this.article.setArImage(image.getText());
        
        ArticleDAO articleDAO = new ArticleDAO();
        articleDAO.edit(this.article);
        
        (((Node) event.getSource()).getScene()).getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("view/MainView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("ACSI - Liste articles");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void cancelAction(ActionEvent event) throws IOException{
        (((Node) event.getSource()).getScene()).getWindow().hide();
    }
    
    @FXML
    private void openImageFile(ActionEvent event){
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
        System.out.println(file);
        image.setText(file.toURI().toString());
    }
    
}
