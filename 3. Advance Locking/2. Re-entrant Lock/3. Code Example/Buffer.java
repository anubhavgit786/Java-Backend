package ReentrantLockExample;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer
{
    private final ReentrantLock lock = new ReentrantLock();
    private final int CAPACITY = 5;
    private final Condition notEmpty = lock.newCondition();
    private final Queue<Integer> queue = new LinkedList<>();

    public void produce(int value)
    {
        try
        {
            lock.lock();
            while (queue.size() == CAPACITY)
            {
                System.out.println("Queue is full. " + Thread.currentThread().getName() + " is waiting.");
                notEmpty.await();
            }

            System.out.println(Thread.currentThread().getName() + " produced: " + value);
            queue.add(value);

            // Signal consumer item is available
            notEmpty.signal();
        }
        catch (Exception e)
        {

        }
        finally
        {
            lock.unlock();
        }
    }

    public void consume()
    {
        try
        {
            lock.lock();
            while (queue.isEmpty())
            {
                System.out.println("Queue is empty. " + Thread.currentThread().getName() + " is waiting.");
                notEmpty.await();
            }

            int value = queue.poll();
            System.out.println(Thread.currentThread().getName() + " consumed: " + value);

            // Signal producer that there is space in the queue
            notEmpty.signal();
        }
        catch (Exception e)
        {

        }
        finally
        {
            lock.unlock();
        }
    }
}