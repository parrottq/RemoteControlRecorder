package com.parrott;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiverThread extends Thread {

    private Socket socketOut;
    private DataOutputStream outputStream;

    private ServerSocket serverSocket;
    private Socket socketIn;
    private DataInputStream inputStream;

    public String address;
    public boolean done = false;

    public ReceiverThread(String address){
        this.address = address;
    }

    @Override
    public void run() {
        System.out.println("Started");
        try {
            socketOut = new Socket(address, 1234);
            outputStream = new DataOutputStream(socketOut.getOutputStream());
            System.out.println("Connected");

            serverSocket = new ServerSocket(4321);
            socketIn = serverSocket.accept();
            inputStream = new DataInputStream(socketIn.getInputStream());
            System.out.println("Receiving");

            while (!done){
                int read = inputStream.read();
                outputStream.write(read);
            }

        } catch (IOException e) {e.printStackTrace();}

    }
}
