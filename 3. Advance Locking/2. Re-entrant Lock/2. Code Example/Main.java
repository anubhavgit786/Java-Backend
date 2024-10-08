package ReentrantLockExample;

import java.util.concurrent.locks.ReentrantLock;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        SharedResource sharedResource = new SharedResource();

        Thread thread1 = new Thread(()->
        {
            sharedResource.performTask();
        }, "Thread 1");

        Thread thread2 = new Thread(()->
        {
            sharedResource.performTask();
        }, "Thread 2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
