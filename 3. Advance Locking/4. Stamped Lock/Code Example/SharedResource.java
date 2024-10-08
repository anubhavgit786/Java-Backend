package StampedLockExample;

import java.util.concurrent.locks.StampedLock;

public class SharedResource
{
    private final StampedLock lock = new StampedLock();
    private int sharedResource = 0;

    public void write(int value)
    {
        long stamp = lock.writeLock();
        System.out.println(Thread.currentThread().getName() + " acquired the write lock");

        try
        {
            sharedResource = value;
            System.out.println(Thread.currentThread().getName() + " writing to shared resource " + value);

        }
        finally
        {
            lock.unlockWrite(stamp);
            System.out.println(Thread.currentThread().getName() + " released the write lock");
        }
    }

    public long read()
    {
        long stamp = lock.readLock();
        System.out.println(Thread.currentThread().getName() + " acquired the reading lock");

        try
        {
            System.out.println(Thread.currentThread().getName() + " reading shared resource " + sharedResource);
            return sharedResource;
        }
        finally
        {
            lock.unlockRead(stamp);
            System.out.println(Thread.currentThread().getName() + " released the reading lock");
        }
    }

    public long readOptimistic()
    {
        long stamp = lock.tryOptimisticRead();
        System.out.println(Thread.currentThread().getName() + " acquired the optimistic reading lock");

        // Validate if the read was consistent
        if (!lock.validate(stamp))
        {
            System.out.println("Optimistic Read Invalid. Falling back to Pessimistic Read Lock.");
            stamp = lock.readLock();  // Fallback to pessimistic read lock
            System.out.println(Thread.currentThread().getName() + " acquired the pessimistic reading lock");
            try
            {
                System.out.println(Thread.currentThread().getName() + " reading shared resource " + sharedResource);
                return sharedResource;
            }
            finally
            {
                lock.unlockRead(stamp);
                System.out.println(Thread.currentThread().getName() + " released the pessimistic reading lock");
            }
        }

        System.out.println(Thread.currentThread().getName() + " successfully validated optimistic read: " + sharedResource);
        return sharedResource;
    }

}
