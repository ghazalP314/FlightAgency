

import common.Flight;
import common.Reserve;
import companies.ConnectFlightCompany;
import user.Person;


import java.net.*;
import java.io.*;
import java.util.ArrayList;


/**
 * Created by daddy on 2/11/17.
 */
public class OnlineReserveSystem {
    public Socket socket;
    public PrintWriter writer;
    public BufferedReader reader;
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
    public void reserve(Flight f, char _class, ArrayList<Person> p, String comp_server, int comp_port)throws IOException{
        Reserve r=new Reserve(f,_class,p);
        ConnectFlightCompany c=new ConnectFlightCompany(comp_server,comp_port);
        String[] result=c.QueryReserve(r).split(" ");
        r.set_token(result[0]);
    }

    public void close()throws IOException{
        socket.close();
        writer.close();
        reader.close();

    }

    public OnlineReserveSystem(int portNumber , String IP, int port) throws  IOException{
        ServerSocket listener = new ServerSocket(portNumber);
        socket = listener.accept();
        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer.println("********************************************************");
        writer.println("*** Welcome to the online ticket reservation service ***");
        writer.println("********************************************************");
        userRequest(IP,port);

//        System.out.println(reader.readLine());

    }
    public  void userRequest(String IP,int port )throws IOException{
        writer.println("What's your request?");
        String in = reader.readLine();
        String[] in_splitted = in.split(" ");

        if(in_splitted[0].equals("search")){
            ArrayList<Flight> flights= ask_about_flight(in_splitted[1], in_splitted[2], in_splitted[3],IP,port);
            for (int i =0;i<flights.size();i++){
                writer.println("Flight : "+flights.get(i).getAirline()+" "+flights.get(i).getFlight_num()+" Departure : "
                        +flights.get(i).getT_departure().charAt(0)+flights.get(i).getT_departure().charAt(1)+":"+flights.get(i).getT_departure().charAt(2)+flights.get(i).getT_departure().charAt(3)
                        +" Arrival : "+flights.get(i).getT_arrival().charAt(0)+flights.get(i).getT_arrival().charAt(1)+":"+
                        flights.get(i).getT_arrival().charAt(2)+flights.get(i).getT_arrival().charAt(3)
                        +" Airplane : "+flights.get(i).getAirplane());
            }


        }

    }




    public static void main(String[] args) throws IOException {
        OnlineReserveSystem ourServer = new OnlineReserveSystem(9092,"188.166.78.119",8081);
        ourServer.close();


    }



}
