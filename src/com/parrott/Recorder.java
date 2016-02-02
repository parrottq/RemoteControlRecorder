package com.parrott;

import java.nio.ByteBuffer;

public class Recorder extends Thread {

    private ByteBuffer buffer;

    public Recorder(ByteBuffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run() {


    }
}
