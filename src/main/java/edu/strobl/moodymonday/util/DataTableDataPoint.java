/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.strobl.moodymonday.util;

import java.time.LocalDateTime;

/**
 *
 * @author Felipe
 */
public class DataTableDataPoint {
    
    private String dateTime;
    private Integer valence;
    private Integer arousal;

    public DataTableDataPoint(String dateTime, Integer valence, Integer arousal) {
        this.dateTime = dateTime;
        this.valence = valence;
        this.arousal = arousal;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getValence() {
        return valence;
    }

    public void setValence(Integer valence) {
        this.valence = valence;
    }

    public Integer getArousal() {
        return arousal;
    }

    public void setArousal(Integer arousal) {
        this.arousal = arousal;
    }
    
    
    
}
