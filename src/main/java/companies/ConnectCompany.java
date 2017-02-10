package companies;

import common.ChairClass;
import common.Flight;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by ghazal on 2/9/2017 AD.
 */
public class ConnectCompany {
    Socket client;
    PrintWriter writer;
    BufferedReader reader;

    ConnectCompany(String server_addr, int port) throws IOException {
        client = new Socket(server_addr,port);
        writer = new PrintWriter(client.getOutputStream(),true);
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public ArrayList<Flight> QueryFlight(String from,String to,String date) throws IOException{
        this.writer.println("AV "+from+" "+to+" "+date);
        ArrayList<Flight> result=new ArrayList<Flight>();
        String flight_info; //first line
        while ((flight_info=this.reader.readLine())!=null){
            String[] fi_splitted=flight_info.split(" ");
            String[] flight_classes=this.reader.readLine().split(" ");
            ArrayList<ChairClass> _classes=new ArrayList<ChairClass>();
            int i;
            for(i=0;i<flight_classes.length;i++)
                _classes.add(new ChairClass(flight_classes[i].charAt(0),flight_classes[i].charAt(1)));
            Flight f=new Flight(fi_splitted);
            f.set_classes(_classes);
            result.add(f);
            if(!reader.ready()){ break; } // readLine() traps in loop without this
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            ConnectCompany c = new ConnectCompany("188.166.78.119", 8081);
            ArrayList<Flight> flights= c.QueryFlight("THR", "MHD", "05Feb");
//            Flight f=flights.get(0);
//            System.out.println("hey!! this is availables of class M : "+(f.get_classes().get(1).get_num_of_availables()));
        }catch (IOException exp){
            System.out.print("An IOException occurred!");
        }
    }

}
