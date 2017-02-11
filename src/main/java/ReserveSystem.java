import common.Flight;
import companies.ConnectCompany;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ghazal on 2/10/2017 AD.
 */
public class ReserveSystem {
    //public ReserveSystem(){}
    public ArrayList<Flight> ask_about_flight(String origin,String dest,String date,String comp_server_addr,int comp_port) throws IOException{
        ArrayList<Flight> result=new ArrayList<Flight>();
        ConnectCompany c=new ConnectCompany(comp_server_addr,comp_port);
        ArrayList<String> flights=c.QueryFlight(origin,dest,date);
        int i,j;
        Flight temp_flight;
        String[] temp_classes;
        ArrayList<String> temp_prices=new ArrayList<String>();
        for(i=0;i<flights.size()/2;i++){
            temp_flight=new Flight(flights.get(i).split(" "));
            temp_classes=flights.get(i=i+1).split(" ");
            for(j=0;j<temp_classes.length;j++){
                temp_prices.add(c.QueryPrice(origin, dest, temp_flight.getAirline(), temp_classes[j].charAt(0)));
            }
            temp_flight.set_classes(temp_classes,temp_prices);
            result.add(temp_flight);
        }
        return result;
    }
    public static void main(String[] args) {
        try {
            ArrayList<Flight> flights= (new ReserveSystem()).ask_about_flight("THR", "MHD", "05Feb","188.166.78.119",8081);
            Flight f=flights.get(0);
            System.out.println("Adult prices: "+(f.get_classes().get(1).getPrice_adult()));
            System.out.println("Adult prices: "+(f.get_classes().get(1).getPrice_child()));
            System.out.println("Adult prices: "+(f.get_classes().get(1).getPrice_infant()));
        }catch (IOException exp){
            System.out.print("An IOException occurred!");
        }
    }
}
