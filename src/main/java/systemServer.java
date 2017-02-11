

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
    PrintWriter out;
    BufferedReader reader;
    public systemServer(int portNumber) throws  IOException{
        ServerSocket listener = new ServerSocket(portNumber);
        try {
            while (true) {
                socket = listener.accept();
                try {
                     out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Welcome to the online ticket reservation service:");
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println(reader.readLine());
                } finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }

    }




    public static void main(String[] args) throws IOException {
        systemServer ourServer = new systemServer(9092);


    }



}
