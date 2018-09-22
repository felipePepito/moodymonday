/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.strobl.moodymonday.ejb;

import edu.strobl.moodymonday.entity.MoodyMondayUser;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Felipe
 */
@Stateless
public class UserEJB {
    
    @PersistenceContext(name="developmentPU")
    private EntityManager em;
    
    @Inject
    private Pbkdf2PasswordHash passwordHash;
    
    @Inject
    private Logger logger;
    
    public MoodyMondayUser persist(MoodyMondayUser user) throws Exception {
        
        Map<String, String> parameters = new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Iterations", "2795");
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
        
        passwordHash.initialize(parameters);
        
        user.setPassword(passwordHash.generate(user.getPassword().toCharArray()));
        
        try {
            em.persist(user);
        } catch (PersistenceException e) {
            logger.warn("Persisting user " + user.getIdUser() + " failed.");
            throw new Exception();
        }
        
        logger.info("Successfully persisted user " + user.getIdUser());
        return user;
    }
}
