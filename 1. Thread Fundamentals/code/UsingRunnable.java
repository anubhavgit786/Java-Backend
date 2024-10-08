package ThreadCreation;

public class UsingRunnable implements Runnable
{
    public void run()
    {
        System.out.println(Thread.currentThread().getName() + " is implemented using Runnable");
    }
}
