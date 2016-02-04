package com.parrott;


import java.nio.ByteBuffer;

public class Buffer {

    private final Object lock = new Object();
    private ByteBuffer buffer;
    private boolean isWritable = true;
    private int count = 0;

    public Buffer() {
        this.buffer = ByteBuffer.allocate(10000000);
    }

    public void write(byte write) {
        synchronized (lock) {
            System.out.println("Write");
            if (isWritable) {
                buffer.put(write);
                count++;
            } else {
                flip();
                write(write);
            }
        }
    }

    public byte read() {
        System.out.println("Read");
        if (isWritable) {
            synchronized (lock) {
                flip();
            }
            return read();
        } else {
            do {
                if (count > 0) {
                    synchronized (lock) {
                        return buffer.get();
                    }
                }
            } while (count == 0);
        }

        return Byte.parseByte(null);
    }

    public boolean hasRemaining(){
        return buffer.hasRemaining();
    }

    private void flip() {
        buffer.flip();
        isWritable = !isWritable;
    }
}
