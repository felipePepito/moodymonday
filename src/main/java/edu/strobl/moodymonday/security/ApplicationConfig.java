/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.strobl.moodymonday.security;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;


/**
 *
 * @author Felipe
 */
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                errorPage = "/login.xhtml"))
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "${'jdbc/moodymonday'}",
        callerQuery = "#{'select PASSWORD from moodymondayuser where IDUSER = ?'}",
        groupsQuery = "select GROUPNAME from userentitygroups where IDUSER = ?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priorityExpression = "#{100}",
        hashAlgorithmParameters = {
        "Pbkdf2PasswordHash.Iterations=2795",
        "${applicationConfig.dyna}"
        })

@Named
@ApplicationScoped
public class ApplicationConfig {
    
    public String[] getDyna() {
        return new String[]{"Pbkdf2PasswordHash.Algorithm=PBKDF2WithHmacSHA512", "Pbkdf2PasswordHash.SaltSizeBytes=64"};
    }

    
}
