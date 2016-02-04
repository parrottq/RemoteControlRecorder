package com.parrott;


import java.io.DataInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiverThread extends Thread {

    //private Socket socketOut;
    //private DataOutputStream outputStream;

    public String address;
    public String path;
    public boolean done = false;
    private ServerSocket serverSocket;
    private Socket socketIn;
    private DataInputStream inputStream;
    private FileWriter file;

    public ReceiverThread(String address, String path) {
        this.address = address;
        this.path = path;
    }

    @Override
    public void run() {
        System.out.println("Started");
        try {
            //socketOut = new Socket(address, 1234);
            //outputStream = new DataOutputStream(socketOut.getOutputStream());
            System.out.println("Connected");

            serverSocket = new ServerSocket(4321);
            socketIn = serverSocket.accept();
            inputStream = new DataInputStream(socketIn.getInputStream());
            System.out.println("Receiving");

            file = new FileWriter(path);

            while (!done){
                System.out.println(")8(");
                int read = inputStream.read();
                //outputStream.write(read);
                file.write(read);
                file.flush();
            }

        } catch (IOException e) {e.printStackTrace();}

    }
}
