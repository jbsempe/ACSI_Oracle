/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Article;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author nicolasthy
 */
public class ArticleDAO {
    
    EntityManager em;
    EntityManagerFactory emf;
    
    public ArticleDAO(){
        emf = Persistence.createEntityManagerFactory("ACSIProjetPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    public List<Article> listArticle(){
        return em.createNamedQuery("Article.findAll", Article.class).getResultList();
    }
    
}
