package com.parrott;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ReceiverThread implements Runnable {

    private Socket socket;
    private DataOutputStream outputStream;

    public String address;
    public boolean done = false;

    public ReceiverThread(String address){
        this.address = address;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(address, 1234);
            outputStream = new DataOutputStream(socket.getOutputStream());

            while (!done){
                break;
            }

        } catch (IOException e) {e.printStackTrace();}

    }
}
