package ThreadCreation;

public class UsingInheritance extends Thread
{
    public void run()
    {
        System.out.println(Thread.currentThread().getName() + " is implemented using Thread Class");
    }
}
