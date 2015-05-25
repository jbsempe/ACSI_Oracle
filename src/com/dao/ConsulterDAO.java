/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

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
}
