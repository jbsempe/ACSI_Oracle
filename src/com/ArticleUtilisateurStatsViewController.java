/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import com.dao.ConsulterDAO;
import com.dao.StatistiqueDAO;
import com.dao.UtilisateurDAO;
import com.model.Consulter;
import com.model.Statistique;
import com.model.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nicolasthy
 */
public class ArticleUtilisateurStatsViewController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private Label addedArticlesLabel;
    @FXML
    private Label updatedArticlesLabel;
    @FXML
    private Label deletedArticlesLabel;

    @FXML
    private CategoryAxis xAxis;

    private List<Utilisateur> listUtilisateur = new ArrayList();
    private List<Consulter> listConsulter = new ArrayList();
    private List<Consulter> listConsulterByUser = new ArrayList();
    private List<Statistique> listStatistique = new ArrayList();
    private ObservableList<String> chartCP = FXCollections.observableArrayList();
    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    private ConsulterDAO consulterDAO = new ConsulterDAO();
    private StatistiqueDAO statistiqueDAO = new StatistiqueDAO();
    
    private Utilisateur currentUser;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Get an array with the English month names.
        ACSIController acsi = new ACSIController();
        currentUser = acsi.getUtilisateur();
        if(currentUser.getUtIsadmin() == 1){
            addedArticlesLabel.setVisible(true);
            updatedArticlesLabel.setVisible(true);
            deletedArticlesLabel.setVisible(true);
        }
        listUtilisateur = utilisateurDAO.listUtilisateur();
        listStatistique = statistiqueDAO.listStatistique();
        Statistique statistique = listStatistique.get(0);
        addedArticlesLabel.setText("Articles ajoutés : "+statistique.getStNbcree());
        deletedArticlesLabel.setText("Articles supprimés : "+statistique.getStNbsuppr());
        updatedArticlesLabel.setText("Articles modifiés : "+statistique.getStNbmodif());
        
        for(com.model.Utilisateur utilisateur : listUtilisateur){
            if(!chartCP.contains(utilisateur.getUtCp())){
                chartCP.add(utilisateur.getUtCp());
            }
        }

        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(chartCP);
        setData();
    }
    
    public void setData(){
        listConsulter = consulterDAO.listConsulter();
        int[] visitesCount = new int[chartCP.size()];
        for (com.model.Utilisateur utilisateur : listUtilisateur) {
            int userCP = chartCP.indexOf(utilisateur.getUtCp());
            listConsulterByUser = consulterDAO.listConsulterIdUt(utilisateur.getUtId());
            visitesCount[userCP] = visitesCount[userCP] + listConsulterByUser.size();
        }
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        
        for (int i = 0; i < visitesCount.length; i++) {
            series.getData().add(new XYChart.Data<>(chartCP.get(i), visitesCount[i]));
        }

        barChart.getData().add(series);
    }
    
    @FXML
    public void backAction(ActionEvent event) throws IOException{
        ((Node)event.getSource()).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("view/HitParadeView.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("ACSI - Consultations");
        stage.setScene(scene);
        stage.show();
    }
    
}
