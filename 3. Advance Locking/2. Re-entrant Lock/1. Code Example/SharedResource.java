package ReentrantLockExample;

import java.util.concurrent.locks.ReentrantLock;

public class SharedResource
{
    private boolean isAvailable = false;

    public void produce(ReentrantLock lock)
    {
        try
        {

            lock.lock();
            System.out.println(Thread.currentThread().getName() + " acquired the lock.");
            isAvailable = true;
            Thread.sleep(8000);
        }
        catch (InterruptedException e)
        {

        }
        finally
        {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " released the lock.");
        }
    }
}
