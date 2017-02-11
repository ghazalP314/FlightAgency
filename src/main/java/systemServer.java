

import common.ChairClass;
import common.Flight;
import common.Reserve;
import user.Person;


import java.net.*;
import java.io.*;
import java.util.ArrayList;


/**
 * Created by daddy on 2/11/17.
 */
public class systemServer {
    Socket socket;
    PrintWriter writer;
    BufferedReader reader;


    public systemServer(int portNumber) throws  IOException{
        ServerSocket listener = new ServerSocket(portNumber);
        socket = listener.accept();
        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer.println("********************************************************");
        writer.println("*** Welcome to the online ticket reservation service ***");
        writer.println("********************************************************");

//        System.out.println(reader.readLine());

    }
    public  void userRequest(String IP,int port )throws IOException{
        writer.println("What's your request?");
        String in = reader.readLine();
        String[] in_splitted = in.split(" ");

        if(in_splitted[0].equals("search")){
            ReserveSystem rs;
            ArrayList<Flight> flights= (rs=new ReserveSystem()).ask_about_flight(in_splitted[1], in_splitted[2], in_splitted[3],IP,port);
            int i=0;
            System.out.println(flights.size());
        }





    }
    public void close()throws IOException{
        socket.close();
        writer.close();
        reader.close();

    }



    public static void main(String[] args) throws IOException {
        systemServer ourServer = new systemServer(9092);
        ourServer.userRequest("188.166.78.119",8081);

        ourServer.close();


    }



}
