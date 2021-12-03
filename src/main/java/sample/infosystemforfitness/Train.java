package sample.infosystemforfitness;

public class Train{

    private String name;
    private String dayOfWeek;
    private String time;
    private String typeOfAction;

    public Train(String name, String dayOfWeek,String time,String typeOfAction){

        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.time  = time;
        this.typeOfAction = typeOfAction;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getTypeOfAction() {
        return typeOfAction;
    }
}
