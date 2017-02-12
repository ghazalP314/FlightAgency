import common.Flight;
import common.Reserve;
import companies.ConnectFlightCompany;
import user.Person;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ghazal on 2/10/2017 AD.
 */
public class OnlineReserveSystem {
    //public OnlineReserveSystem(){}
    public ArrayList<Flight> ask_about_flight(String origin,String dest,String date,String comp_server_addr,int comp_port) throws IOException{
        ArrayList<Flight> result=new ArrayList<Flight>();
        ConnectFlightCompany c=new ConnectFlightCompany(comp_server_addr,comp_port);
        ArrayList<String> flights=c.QueryFlight(origin,dest,date);
//        System.out.println("flights : "+flights.size());
//        for(int k =0;k<flights.size();k++)
//            System.out.println(flights.get(k));
        int i,j;
        Flight temp_flight;
        String[] temp_classes;
        ArrayList<String> temp_prices=new ArrayList<String>();
        for(i=0;i<flights.size();i++){
            temp_flight=new Flight(flights.get(i).split(" "));
            temp_classes=flights.get(i=i+1).split(" ");
            for(j=0;j<temp_classes.length;j++){
                temp_prices.add(c.QueryPrice(origin, dest, temp_flight.getAirline(), temp_classes[j].charAt(0)));
            }
            temp_flight.set_classes(temp_classes,temp_prices);
            result.add(temp_flight);
        }
        c.close();
        return result;
    }
    public void reserve(Flight f,char _class,ArrayList<Person> p,String comp_server,int comp_port)throws IOException{
        Reserve r=new Reserve(f,_class,p);
        ConnectFlightCompany c=new ConnectFlightCompany(comp_server,comp_port);
        String[] result=c.QueryReserve(r).split(" ");
        r.set_token(result[0]);
    }


}
