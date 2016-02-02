package com.parrott;


import java.nio.ByteBuffer;

public class Buffer {

    private ByteBuffer buffer;
    private boolean isWritable = true;

    public Buffer() {
        this.buffer = ByteBuffer.allocate(1024);
    }

    private void write(byte write) {
        if (isWritable) {
            buffer.put(write);
        } else {
            flip();
            write(write);
        }
    }

    private byte read() {
        if (isWritable) {
            flip();
            return read();
        } else {
            return buffer.get();
        }
    }

    private void flip() {
        buffer.flip();
        isWritable = !isWritable;
    }
}
