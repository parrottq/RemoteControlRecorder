package com.parrott;

import java.io.FileWriter;
import java.io.IOException;

public class Recorder extends Thread {

    private Buffer buffer;
    private String file;

    public Recorder(Buffer buffer, String file) {
        this.buffer = buffer;
        this.file = file;
    }

    @Override
    public void run() {

        try {
            FileWriter fileWriter = new FileWriter(file);

            while (true) {
                System.out.println("Read");
                fileWriter.write(buffer.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
