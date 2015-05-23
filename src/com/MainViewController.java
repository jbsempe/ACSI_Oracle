/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.dao.ArticleDAO;
import com.model.Article;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author nicolasthy
 */
public class MainViewController implements Initializable {
    
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
    
}
