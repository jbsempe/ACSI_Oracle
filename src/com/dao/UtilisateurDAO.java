/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Utilisateur;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author nicolasthy
 */
public class UtilisateurDAO {
    
    EntityManager em;
    EntityManagerFactory emf;
    
    public UtilisateurDAO(){
        emf = Persistence.createEntityManagerFactory("ACSIProjetPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    public List<Utilisateur> listUtilisateur(){
        return em.createNamedQuery("Utilisateur.findAll", Utilisateur.class).getResultList();
    }
    
    public List<Utilisateur> listUtilisateurByDate(Date date){
        return em.createNamedQuery("Utilisateur.findByDate", Utilisateur.class).setParameter("utDateinscri", date).getResultList();
    }
    
    public Utilisateur listUtilisateurById(int utId){
        emf = Persistence.createEntityManagerFactory("ACSIProjetPU");
        em = emf.createEntityManager();

        Utilisateur utilisateur = (Utilisateur) em.createNamedQuery("Utilisateur.findByUtId", Utilisateur.class)
                .setParameter("utId", utId)
                .getSingleResult();
        em.close();
        emf.close();
        return utilisateur;
    }
    
    public void create(Utilisateur user){
        emf = Persistence.createEntityManagerFactory("ACSIProjetPU");
        em = emf.createEntityManager();
        //Get transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(user);
        transaction.commit();

        //Close entity manager
        em.close();
        emf.close();
    }
}
