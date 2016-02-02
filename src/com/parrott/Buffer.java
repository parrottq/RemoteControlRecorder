package com.parrott;


import java.nio.ByteBuffer;

public class Buffer {

    private ByteBuffer buffer;
    private boolean isWritable = true;
    private Object lock;

    public Buffer() {
        this.buffer = ByteBuffer.allocate(1024);
    }

    public void write(byte write) {
        synchronized (lock) {
            if (isWritable) {
                buffer.put(write);
            } else {
                flip();
                write(write);
            }
        }
    }

    public byte read() {
        synchronized (lock) {
            if (isWritable) {
                flip();
                return read();
            } else {
                return buffer.get();
            }
        }
    }

    private void flip() {
        buffer.flip();
        isWritable = !isWritable;
    }
}
