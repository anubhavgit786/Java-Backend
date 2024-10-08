package StampedLockExample;


import java.util.concurrent.Semaphore;

public class SharedResource
{
    private Semaphore semaphore;

    public SharedResource(int numThreads)
    {
        semaphore = new Semaphore(numThreads);
    }

    public void accessResource()
    {
        try
        {
            // Acquire a permit before accessing the resource
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " is accessing the resource.");
            // Simulate some work with the resource
            Thread.sleep(8000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally
        {
            // Release the permit after work is done
            semaphore.release();
            System.out.println(Thread.currentThread().getName() + " has released the resource.");
        }
    }
}
