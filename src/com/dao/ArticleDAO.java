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
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
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
    
    public Article listArticleById(int arId){
        emf = Persistence.createEntityManagerFactory("ACSIProjetPU");
        em = emf.createEntityManager();

        Article article = (Article) em.createNamedQuery("Article.findByArId", Article.class)
                .setParameter("arId", arId)
                .getSingleResult();
        em.close();
        emf.close();
        return article;
    }
    
    public void create(Article article){
        emf = Persistence.createEntityManagerFactory("ACSIProjetPU");
        em = emf.createEntityManager();
        //Get transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(article);
        transaction.commit();

        //Close entity manager
        em.close();
        emf.close();
    }
    
    public void edit(Article article){
        emf = Persistence.createEntityManagerFactory("ACSIProjetPU");
        em = emf.createEntityManager();

        //Get transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.merge(article);
        transaction.commit();

        //Close entity manager
        em.close();
        emf.close();
    }
    
    public void delete(Article article){
        emf = Persistence.createEntityManagerFactory("ACSIProjetPU");
        em = emf.createEntityManager();
        //Get transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.remove(em.contains(article) ? article : em.merge(article));
        transaction.commit();

        //Close entity manager
        em.close();
        emf.close();

    }
    
}
