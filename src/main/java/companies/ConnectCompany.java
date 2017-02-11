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
    public void close() throws IOException{ client.close(); }

    public ConnectCompany(String server_addr, int port) throws IOException {
        client = new Socket(server_addr,port);
        writer = new PrintWriter(client.getOutputStream(),true);
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public ArrayList<String> QueryFlight(String from,String to,String date)throws IOException{
        writer.println("AV "+from+" "+to+" "+date);
        ArrayList<String> result=new ArrayList<String>();
        String flight_info;
        while ((flight_info=reader.readLine())!=null){
            result.add(flight_info);
            result.add(reader.readLine());
            if(!reader.ready()){ break; }
        }
        return result;
    }

    public String QueryPrice(String from,String to,String airline,char _class)throws IOException{
        writer.println("PRICE " + from + " " + to + " " + airline + " " + _class);
        return reader.readLine();
    }

}
