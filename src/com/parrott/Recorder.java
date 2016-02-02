package com.parrott;

import java.nio.ByteBuffer;

public class Recorder extends Thread {

    private Buffer buffer;

    public Recorder(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {


    }
}
