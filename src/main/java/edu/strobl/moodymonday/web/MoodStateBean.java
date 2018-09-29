package edu.strobl.moodymonday.web;

import edu.strobl.moodymonday.ejb.MoodStateEJB;
import edu.strobl.moodymonday.entity.MoodState;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.logging.log4j.Logger;



/**
 *
 * @author Philipp Strobl
 */

@Named
@RequestScoped
public class MoodStateBean {
    
    @Inject
    private Logger logger;
    
    private MoodState moodState;
    
    @Inject
    private MoodStateEJB moodStateEJB;
    
    public String submitMoodState(){
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        try {
            moodStateEJB.persist(moodState);
            logger.debug("persisted moodState for user-id {}", this.moodState.getIdUser());
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_INFO, 
                    "Persisting Mood State succeeded", 
                    "Thank you for submitting your mood state.");
            ctx.addMessage("response:result", message);
        } catch (Exception e) {
            logger.error("persisted moodState for user-id {} failed. {}", this.moodState.getIdUser(), e.getMessage());
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, 
                    "Persisting Mood State failed", 
                    "Due to an internal Error, persisting your current Mood to the database failed. Please try again later.");
            ctx.addMessage("response:result", message);
        }
        
        return "response";
    }
    
    /*** Getter, Setter, Constructor ***/
    
    public MoodStateBean() {
        this.moodState = new MoodState();
    }
    
    public MoodState getMoodState() {
        return this.moodState;
    }

    public void setMoodState(MoodState moodState) {
        this.moodState = moodState;
    }

    
    
}
