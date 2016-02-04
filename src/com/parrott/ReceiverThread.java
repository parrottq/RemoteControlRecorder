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

            System.out.println("Recording");
            int[] actions = {0, 0};
            int count = 0;

            int right = 0;
            int left = 0;
            boolean[] changed = {false, false};

            while (!done){
                int read = inputStream.read();
                //outputStream.write(read);
                if (read == 0x00) {
                    count = 0;
                    actions[0] = 0;
                    actions[1] = 0;
                } else {
                    if (count == 1) {
                        actions[0] = read;
                    } else if (count == 2) {
                        actions[1] = read;
                    }
                }

                if (count == 2) {
                    System.out.print(actions[0] + "-" + actions[1] + "\n");
                    if (actions[0] == 0x31) {
                        System.out.println("Right");
                        right = actions[1];
                        changed[0] = true;
                    } else if (actions[0] == 0x32) {
                        System.out.println("Left");
                        left = actions[1];
                        changed[1] = true;
                    }
                    System.out.println(changed[0] + "+" + changed[1]);

                    if (changed[0] && changed[1]) {
                        System.out.println("Pushed");
                        changed[0] = false;
                        changed[1] = false;
                        System.out.println(right);

                        file.write(String.valueOf(right) + ':' + String.valueOf(left) + ":50:");
                        file.flush();
                    }
                }
                count++;
            }

        } catch (IOException e) {e.printStackTrace();}

    }
}
