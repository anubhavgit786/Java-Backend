package ReentrantLockExample;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Buffer
{
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private int sharedResource = 0;

    public int read()
    {
        try
        {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + ": Reading from shared resource " + sharedResource);
            return sharedResource;
        }
        catch(Exception e)
        {

        }
        finally
        {
            lock.readLock().unlock();
        }

        return 0;
    }

    public void write(int value)
    {
        try
        {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + ": Writing to shared resource " + value);
            sharedResource = value;
        }
        catch(Exception e)
        {

        }
        finally
        {
            lock.writeLock().unlock();
        }
    }

    public void upgaradeAndDowngrade(int value)
    {
        try
        {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " acquired the write lock");
            sharedResource = value;
            System.out.println(Thread.currentThread().getName() + ": Writing to shared resource " + value);
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " acquired the read lock");
        }
        finally
        {
            lock.writeLock().unlock();
            System.out.println(Thread.currentThread().getName() + " released the write lock");
        }

        try
        {
            System.out.println(Thread.currentThread().getName() + ": Reading from shared resource " + sharedResource);
        }
        finally
        {
            lock.readLock().unlock();
            System.out.println(Thread.currentThread().getName() + " released the read lock");
        }
    }
}