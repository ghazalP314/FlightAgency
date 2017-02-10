package common;

import java.util.ArrayList;

/**
 * Created by ghazal on 2/10/2017 AD.
 */
public class Flight {
    private String airline; // IR or W5
    private String flight_num;
    private String date;
    private String origin;
    private String dest;
    private String t_departure;
    private String t_arrival;
    private String airplane;
    private int price_adult=0;
    private int price_child=0;
    private int price_infant=0;
    private ArrayList<ChairClass> classes;

    public Flight(String[] finfo){
        airline=finfo[0];
        flight_num=finfo[1];
        date=finfo[2];
        origin=finfo[3];
        dest=finfo[4];
        t_departure=finfo[5];
        t_arrival=finfo[6];
        airplane=finfo[7];
    }

    public void  set_classes(ArrayList<ChairClass> chc) { classes=chc; }
    public String get_origin() { return origin; }
    public ArrayList<ChairClass> get_classes() { return classes; }
    // todo: handle classes, set prices
}
