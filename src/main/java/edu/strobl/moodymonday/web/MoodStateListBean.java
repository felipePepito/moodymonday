/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.strobl.moodymonday.web;

import edu.strobl.moodymonday.ejb.MoodStateEJB;
import edu.strobl.moodymonday.entity.MoodState;
import edu.strobl.moodymonday.util.DataTableConverter;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author aaron
 */
@Named
@RequestScoped
public class MoodStateListBean {
    
    @Inject
    MoodStateEJB moodStateEJB;
    
    @Inject
    DataTableConverter converter;
    
    public String getMoodStateListForChart() {
        
        List<MoodState> moodStateList = moodStateEJB.getAllMoodStates();
        return converter.moodStateListToDataString(moodStateList);
        
    }
    
}
