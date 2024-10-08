package ReentrantLockExample;

import java.util.concurrent.locks.ReentrantLock;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        Buffer buffer = new Buffer();

        Thread producerThread = new Thread(()->
        {
            for(int i = 0; i < 10; i++)
            {
                try
                {
                    buffer.produce(i);
                    Thread.sleep(500);
                }
                catch(Exception e)
                {
                    throw new RuntimeException(e);
                }
            }

        }, "Producer Thread");

        Thread consumerThread = new Thread(()->
        {
            for(int i = 0; i < 10; i++)
            {
                try
                {
                    buffer.consume();
                    Thread.sleep(1000);
                }
                catch(Exception e)
                {
                    throw new RuntimeException(e);
                }
            }

        }, "Consumer Thread");

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
        
    }
}
