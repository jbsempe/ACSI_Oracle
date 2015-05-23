/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Utilisateur;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
    
}
