package com.jdbc;

import java.net.*;
import java.io.*;

public class Socket {
    //инициализация сокетов и инпут стрима
    private java.net.Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public Socket(int port, String line)
    {
    // starts server and waits for a connection
        try{

            server = new ServerSocket(port, 4, Inet4Address.getByName("192.168.1.17"));
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            System.out.println(server.isBound());

            socket = server.accept();

            System.out.println("Client accepted");
    // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
    // reads message from client until "Over" is sent
            while (!line.equals("Authorization"))
            {
                try
                {
                    line = in.readUTF();
                    System.out.println(line);
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i){
            System.out.println(i);
        }
    }
}
