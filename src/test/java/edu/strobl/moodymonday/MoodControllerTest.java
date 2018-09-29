/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.strobl.moodymonday;

import edu.strobl.moodymonday.web.MoodStateBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;



/**
 *
 * @author Felipe
 */
public class MoodControllerTest {
    
    private MoodStateBean moodController;
    
    @Test @Disabled
    void submitMoodStateShouldReturnResponse() {
        assertEquals("response", moodController.submitMoodState(), "Navigation to response fails");
    }
    
    public MoodControllerTest() {
    }
    
    
}
