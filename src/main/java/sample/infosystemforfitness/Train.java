package sample.infosystemforfitness;

import java.sql.Date;
import java.sql.Timestamp;

public class Train{

    private String name;
    private Timestamp date;
    private String typeOfAction;

    public Train(String name, String dayOfWeek,String time){

        this.name = name;
        this.date = date;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

}
