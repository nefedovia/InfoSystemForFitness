package sample.infosystemforfitness;

public class Train{

    private String name;
    private String dayOfWeek;
    private String time;

    public Train(String name, String dayOfWeek,String time){

        this.name = name;
        this.dayOfWeek = dayOfWeek;
        this.time  = time;

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
}
