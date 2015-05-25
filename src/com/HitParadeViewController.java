/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.dao.ArticleDAO;
import com.model.Article;
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
    private TableView<Article> articleStatTable;
    @FXML
    private TableColumn<Article, String> articleImageStatColumn;
    @FXML
    private TableColumn<Article, String> articleNameStatColumn;
    @FXML
    private TableColumn<Article, String> articleRefStatColumn;
    @FXML
    private TableColumn<Article, String> articlePriceStatColumn;
    
    private List<Article> listArticle = new ArrayList();
    private ArticleDAO articleDAO = new ArticleDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        listArticle = articleDAO.listArticle();
        articleNameStatColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("arLabel"));
        articleRefStatColumn.setCellValueFactory(new PropertyValueFactory<Article, String>("arRef"));
        
        articleStatTable.getItems().setAll(listArticle);
        
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
