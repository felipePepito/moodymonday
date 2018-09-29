package edu.strobl.moodymonday.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.strobl.moodymonday.entity.MoodState;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

@Stateless
public class DataTableConverter {
    
    /**
     * Not used at the moment, just for testing purposes
     * 
     * @param moodStateList
     * @return 
     */
    public String moodStateListToJson(List<MoodState> moodStateList) {
        
        // Convert List to an appropriate format to be inserted into the DataTable
        List<DataTableDataPoint> dataList = new ArrayList<>();
        for (MoodState ms : moodStateList) {
            String dateTime = "new Date(" + ms.getDatetime().toEpochSecond(ZoneOffset.UTC) + "000)";
            
            DataTableDataPoint d = new DataTableDataPoint(dateTime, ms.getValence(), ms.getArousal());
            dataList.add(d);
        }
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        String json = gson.toJson(dataList);
        System.out.println(json);
        
        return json;
    }
    
    /**
     * Formats the Data-String to be used by googleCharts to display the Line Chart
     * 
     * Returns a String (a javascript literal object) to be used by the dataTable Constructor
     * @see https://developers.google.com/chart/interactive/docs/reference#datatable-class
     * 
     * @param moodStateList
     * @return 
     */
    public String moodStateListToDataString(List<MoodState> moodStateList) {
        
        String data = "";
        
        if(!moodStateList.isEmpty()) {
            for(MoodState ms : moodStateList) {
               
                // Date, using the Date Constructor that expects the UNIX epoch interval
                data += "{c: [ {v: ";
                String date = "new Date(" + Long.toString(ms.getDatetime().toEpochSecond(ZoneOffset.UTC)) + "000)";
                data += date;
                // Formatted Datum to show in the tooltip
                data += ", f: \'";
                String dateFormatted = ms.getDatetime().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
                data += dateFormatted;
                // Valence
                data += "\'} , {v: ";
                data += Integer.toString(ms.getValence());
                // Arousal
                data += "} , {v: ";
                data +=Integer.toString(ms.getArousal());
                data += "} ] }";
                
                if (moodStateList.iterator().hasNext()) {
                    data += ",";
                }
            }
        }
        return data;
        
    }
}
