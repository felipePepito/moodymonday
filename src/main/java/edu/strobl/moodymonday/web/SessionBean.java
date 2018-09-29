/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.strobl.moodymonday.web;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.servlet.http.Cookie;

/**
 *
 * @author aaron
 */
@Named
@SessionScoped
public class SessionBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
	
	private String localeCode;
	
	private static Map<String,Object> countries;
	static{
		countries = new LinkedHashMap<String,Object>();
		countries.put("Deutsch", Locale.GERMAN); 
		countries.put("English", Locale.ENGLISH);
	}
        
        @PostConstruct
        public void init() {
            Map<String, Object> cookieMap = FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap();
            if (cookieMap.containsKey("language")) {
                Cookie langCookie = (Cookie)cookieMap.get("language");
                this.localeCode = langCookie.getValue();
                setSessionLocale(this.localeCode);
            } else {
                this.localeCode = "de";
            }
        }
        
        public void localeChanged(ValueChangeEvent e) {
            String newLocaleValue = e.getNewValue().toString();
            
            setSessionLocale(newLocaleValue);
            FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("language", newLocaleValue, null);
        }
        
        public void setSessionLocale(String newLocaleValue) {
            for (Map.Entry<String, Object> entry : countries.entrySet()) {
                if (entry.getValue().toString().equals(newLocaleValue)) {
                    FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale)entry.getValue());
                }
            }
        }
        
        public void setContextLocale() {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.forLanguageTag(this.localeCode));
        }

	public Map<String, Object> getCountriesInMap() {
		return countries;
	}

	
	public String getLocaleCode() {
		return localeCode;
	}


	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}
        
        
    
}
