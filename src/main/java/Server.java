

import common.Flight;


import java.net.*;
import java.io.*;
import java.util.ArrayList;


/**
 * Created by daddy on 2/11/17.
 */
public class Server {
    Socket socket;
    PrintWriter writer;
    BufferedReader reader;


    public Server(int portNumber , String IP,int port) throws  IOException{
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
            OnlineReserveSystem rs;
            ArrayList<Flight> flights= (rs=new OnlineReserveSystem()).ask_about_flight(in_splitted[1], in_splitted[2], in_splitted[3],IP,port);
            for (int i =0;i<flights.size();i++){
                writer.println("Flight : "+flights.get(i).getAirline()+" "+flights.get(i).getFlight_num()+" Departure : "
                        +flights.get(i).getT_departure().charAt(0)+flights.get(i).getT_departure().charAt(1)+":"+flights.get(i).getT_departure().charAt(2)+flights.get(i).getT_departure().charAt(3)
                        +" Arrival : "+flights.get(i).getT_arrival().charAt(0)+flights.get(i).getT_arrival().charAt(1)+":"+
                        flights.get(i).getT_arrival().charAt(2)+flights.get(i).getT_arrival().charAt(3)
                        +" Airplane : "+flights.get(i).getAirplane());
            }


        }





    }
    public void close()throws IOException{
        socket.close();
        writer.close();
        reader.close();

    }



    public static void main(String[] args) throws IOException {
        Server ourServer = new Server(9092,"188.166.78.119",8081);
        ourServer.close();


    }



}
