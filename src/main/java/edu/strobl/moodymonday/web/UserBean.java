/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.strobl.moodymonday.web;

import edu.strobl.moodymonday.ejb.MoodStateEJB;
import edu.strobl.moodymonday.ejb.UserEJB;
import edu.strobl.moodymonday.entity.MoodState;
import edu.strobl.moodymonday.entity.MoodyMondayUser;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe
 */
@Named
@RequestScoped
public class UserBean {
    
    private String idUser;
    private String password;
    
    
    @Inject
    private UserEJB userEJB;
    
    @Inject
    private SecurityContext securityContext;
    
    public String login() {
        
        String navigation = "login";
        
        FacesContext context = FacesContext.getCurrentInstance();
        Credential credential = new UsernamePasswordCredential(idUser, password);
        
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        AuthenticationStatus authStatus = securityContext.authenticate(
                request, 
                response, 
                AuthenticationParameters.withParams().credential(credential));
        
        FacesMessage message;
        if (authStatus == AuthenticationStatus.SUCCESS) {
            message = new FacesMessage("Login Successfull");
            navigation = "moodTrend";
            
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Failed", "");
        }
        context.addMessage("login:result", message);
        
        
        return navigation;
    }
    
    
    public String createAccount() {
        
        MoodyMondayUser user = new MoodyMondayUser();
        user.setIdUser(idUser);
        user.setPassword(password);
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        String navigation = "login";
        
        try {
            user = userEJB.persist(user);
            
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "User successfully saved", "");
            context.addMessage(null, message);
            
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Sorry, we could not create your account, please try again.", "");
            context.addMessage(null, message);
            
            navigation = "createaccount";
        }
        
        return navigation;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
