package ReentrantLockExample;

import java.util.concurrent.locks.ReentrantLock;

public class SharedResource
{
    private final ReentrantLock lock = new ReentrantLock();

    public void performTask()
    {
        try
        {

            lock.lock();
            System.out.println(Thread.currentThread().getName() + " acquired the lock.");
            nestedTask();
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

    public void nestedTask()
    {
        try
        {

            lock.lock();
            System.out.println(Thread.currentThread().getName() + " reentered the lock.");
        }
        finally
        {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " released the lock.");
        }
    }
}
