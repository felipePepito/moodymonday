/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.strobl.moodymonday.ejb;


import edu.strobl.moodymonday.entity.MoodState;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.security.enterprise.SecurityContext;


/**
 *
 * @author Felipe
 */
@Stateless
public class MoodStateEJB {

    @PersistenceContext(unitName = "developmentPU")
    private EntityManager em;
   
    @Inject
    private SecurityContext securityContext;
    
    public void persist(MoodState mood) {
        em.persist(mood);
    }
    
    public List<MoodState> getAllMoodStates() {
        
        
        String idUser = securityContext.getCallerPrincipal().getName();
        System.out.println(idUser);
        
        String queryString = "SELECT m FROM MoodState m WHERE m.idUser LIKE :id";
        TypedQuery<MoodState> query = em.createQuery(queryString, MoodState.class)
                .setParameter("id", idUser);
        
        return query.getResultList();
               
        
    }

}
