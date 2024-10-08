package StampedLockExample;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        SharedResource sharedResource = new SharedResource(3);

        // Create multiple threads that will try to access the resource
        Thread t1 = new Thread(sharedResource::accessResource, "Thread-1");
        Thread t2 = new Thread(sharedResource::accessResource, "Thread-2");
        Thread t3 = new Thread(sharedResource::accessResource, "Thread-3");
        Thread t4 = new Thread(sharedResource::accessResource, "Thread-4");
        Thread t5 = new Thread(sharedResource::accessResource, "Thread-5");
        Thread t6 = new Thread(sharedResource::accessResource, "Thread-6");

        // Start the threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
    }
}