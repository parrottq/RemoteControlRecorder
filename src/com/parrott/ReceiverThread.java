package com.parrott;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiverThread extends Thread {

    public String address;
    public String file;
    public boolean done = false;
    public Buffer buffer;
    public Recorder recorder;
    //private Socket socketOut;
    //private DataOutputStream outputStream;
    private ServerSocket serverSocket;
    private Socket socketIn;
    private DataInputStream inputStream;

    public ReceiverThread(String address, String file) {
        this.file = file;
        this.address = address;
    }

    @Override
    public void run() {
        buffer = new Buffer();
        recorder = new Recorder(buffer, file);

        System.out.println("Started");
        try {
            //socketOut = new Socket(address, 1234);
            //outputStream = new DataOutputStream(socketOut.getOutputStream());
            //System.out.println("Connected");

            serverSocket = new ServerSocket(4321);
            socketIn = serverSocket.accept();
            inputStream = new DataInputStream(socketIn.getInputStream());
            System.out.println("Receiving");
            recorder.start();

            while (!done){
                //System.out.println("TLoop");
                int read = inputStream.read();
                //outputStream.write(read);
                buffer.write((byte) read);
            }

        } catch (IOException e) {e.printStackTrace();}

    }
}
