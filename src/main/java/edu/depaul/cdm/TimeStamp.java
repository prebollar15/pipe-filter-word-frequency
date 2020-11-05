package edu.depaul.cdm;

import java.sql.Timestamp;

public class TimeStamp {
    
    public static Timestamp getTimeStamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            return timestamp;

    }
}
