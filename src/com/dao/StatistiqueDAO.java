/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Statistique;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Jean-Baptiste
 */
public class StatistiqueDAO {
    
    EntityManager em;
    EntityManagerFactory emf;
    
    public StatistiqueDAO(){
        emf = Persistence.createEntityManagerFactory("ACSIProjetPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    public List<Statistique> listStatistique(){
        return em.createNamedQuery("Statistique.findAll", Statistique.class).getResultList();
    }
    
    public void create(Statistique statistique){
        emf = Persistence.createEntityManagerFactory("ACSIProjetPU");
        em = emf.createEntityManager();
        //Get transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(statistique);
        transaction.commit();

        //Close entity manager
        em.close();
        emf.close();
    }
    
    public void edit(Statistique statistique){
        emf = Persistence.createEntityManagerFactory("ACSIProjetPU");
        em = emf.createEntityManager();

        //Get transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.merge(statistique);
        transaction.commit();

        //Close entity manager
        em.close();
        emf.close();
    }
    
    public void delete(Statistique statistique){
        emf = Persistence.createEntityManagerFactory("ACSIProjetPU");
        em = emf.createEntityManager();
        //Get transaction
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.remove(em.contains(statistique) ? statistique : em.merge(statistique));
        transaction.commit();

        //Close entity manager
        em.close();
        emf.close();

    }
    
}
