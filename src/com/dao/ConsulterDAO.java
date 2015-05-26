/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Article;
import com.model.Consulter;
import com.model.Utilisateur;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author nicolasthy
 */
public class ConsulterDAO {
    EntityManager em;
    EntityManagerFactory emf;
    
    public ConsulterDAO(){
        emf = Persistence.createEntityManagerFactory("ACSIProjetPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    public List<Consulter> listConsulter(){
        return em.createNamedQuery("Consulter.findAll", Consulter.class).getResultList();
    }
    
    public List<Consulter> listConsulterIdArt(int arId){
        ArticleDAO articleDAO = new ArticleDAO();
        Article article = articleDAO.listArticleById(arId);
        
        return em.createNamedQuery("Consulter.findByArId", Consulter.class)
                .setParameter("arId", article)
                .getResultList();
    }
    
    public List<Consulter> listConsulterIdUt(int utId){
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        Utilisateur utilisateur = utilisateurDAO.listUtilisateurById(utId);
        
        return em.createNamedQuery("Consulter.findByUtId", Consulter.class)
                .setParameter("utId", utilisateur)
                .getResultList();
    }
    
    public void create(Consulter consulter){
        emf = Persistence.createEntityManagerFactory("ACSIProjetPU");
        em = emf.createEntityManager();
        //Get transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(consulter);
        transaction.commit();

        //Close entity manager
        em.close();
        emf.close();
    }
    
    public void update(Consulter consulter){
        emf = Persistence.createEntityManagerFactory("ACSIProjetPU");
        em = emf.createEntityManager();

        //Get transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.merge(consulter);
        transaction.commit();

        //Close entity manager
        em.close();
        emf.close();
    }
    
    public void delete(Consulter consulter){
        emf = Persistence.createEntityManagerFactory("ACSIProjetPU");
        em = emf.createEntityManager();
        //Get transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.remove(em.contains(consulter) ? consulter : em.merge(consulter));
        transaction.commit();

        //Close entity manager
        em.close();
        emf.close();

    }
}
