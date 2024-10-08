package ReentrantLockExample;

import java.util.concurrent.locks.ReentrantLock;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        Buffer buffer = new Buffer();

        Thread writeThread1 = new Thread(()->
        {
           for(int i = 0; i < 5; i++)
           {
               buffer.write(i);
           }
        }, "Writer Thread 1");

        Thread writeThread2 = new Thread(()->
        {
            for(int i = 5; i < 10; i++)
            {
                buffer.write(i);
            }
        }, "Writer Thread 2");

        Thread readThread1 = new Thread(()->
        {
            for(int i = 0; i < 5; i++)
            {
                buffer.read();
            }
        }, "Read Thread 1");

        Thread readThread2 = new Thread(()->
        {
            for(int i = 0; i < 5; i++)
            {
                buffer.read();
            }
        }, "Read Thread 2");

        writeThread1.start();
        writeThread2.start();
        readThread1.start();
        readThread2.start();

        writeThread1.join();
        writeThread2.join();
        readThread1.join();
        readThread2.join();
    }
}
