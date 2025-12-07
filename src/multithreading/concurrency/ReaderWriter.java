package multithreading.concurrency;
/*
* Reader-Writer Problem Overview
Readers can read simultaneously (multiple readers allowed).
Writers need exclusive access (only one writer at a time and no readers allowed).
* */
public class ReaderWriter {
    private int readerCount = 0;  // Tracks number of active readers
    private boolean isWriting = false;  // True when a writer is writing
    private int data = 0;  // Shared data

    // Readers should read concurrently if no writer is writing
    public synchronized void readData(String readerName) {
        while (isWriting) {  // Wait if a writer is active
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        readerCount++;  // Increment reader count
        System.out.println(readerName + " is reading data: " + this.data);

        // Once done reading, decrement reader count
        readerCount--;
        if (readerCount == 0) {  // If last reader is done, notify waiting writers
            notifyAll();
        }
    }

    // Writers need exclusive access (must wait for all readers to finish)
    public synchronized void writeData(int data) {
        while (isWriting || readerCount > 0) {  // Wait if readers or another writer is active
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        isWriting = true;  // Lock for writing
        this.data = data;
        System.out.println("Writer is writing data: " + this.data);

        isWriting = false;  // Unlock after writing
        notifyAll();  // Notify waiting readers and writers
    }

    public static void main(String[] args) {
        ReaderWriter readerWriter = new ReaderWriter();

        // Simulating multiple readers and writers in parallel
        Thread reader1 = new Thread(() -> readerWriter.readData("Reader1"));
        Thread reader2 = new Thread(() -> readerWriter.readData("Reader2"));
        Thread writer = new Thread(() -> readerWriter.writeData(800));
        Thread writer2 = new Thread(() -> readerWriter.writeData(870));

        reader1.start();
        reader2.start();
        writer.start();
        writer2.start();
    }
}
