package common;

import user.Person;

import java.util.ArrayList;

/**
 * Created by ghazal on 2/11/2017 AD.
 */
public class Reserve {
    private Flight flight;
    private char chair_class;
    private ArrayList<Person> persons;
    private String token;

    public Reserve(Flight f,char _class,ArrayList<Person> p){
        flight=f;
        persons=p;
        chair_class=_class;
    }

    public Flight getFlight() { return flight; }
    public char getChair_class() { return chair_class; }
    public int get_num_of_adults() {
        int i,result=0;
        for(i=0;i<persons.size();i++)
            if(persons.get(i).getAge().equals("adult"))
                result++;
        return result;
    }
    public int get_num_of_childs() {
        int i,result=0;
        for(i=0;i<persons.size();i++)
            if(persons.get(i).getAge().equals("child"))
                result++;
        return result;
    }
    public int get_num_of_infants() {
        int i,result=0;
        for(i=0;i<persons.size();i++)
            if(persons.get(i).getAge().equals("infant"))
                result++;
        return result;
    }
    public void set_token(String t){ token=t; }
}
