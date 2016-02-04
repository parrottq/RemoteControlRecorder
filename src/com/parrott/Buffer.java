package com.parrott;


import java.nio.ByteBuffer;

public class Buffer {

    private final Object lock = new Object();
    private ByteBuffer buffer;
    private boolean isWritable = true;

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

    public boolean hasRemaining(){
        return buffer.hasRemaining();
    }

    private void flip() {
        buffer.flip();
        isWritable = !isWritable;
    }
}
