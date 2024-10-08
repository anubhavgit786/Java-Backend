package ThreadCreation;

public class Main
{
    public static void main(String[] args)
    {
        UsingRunnable task1 = new UsingRunnable();
        Thread thread1 = new Thread(task1, "Thread 1");

        UsingInheritance thread2 = new UsingInheritance();
        thread2.setName("Thread 2");

        Thread thread3 = new Thread(()->
        {
            System.out.println(Thread.currentThread().getName() + " is implemented using Lambda Expression");
        }, "Thread 3");


        UsingMethodReference reference = new UsingMethodReference();

        Runnable task2 = reference::performTask;

        Thread thread4 = new Thread(task2, "Thread 4");

        Runnable task3 = new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println(Thread.currentThread().getName() + " is implemented using runnable");
            }
        };

        Thread thread5 = new Thread(task3, "Thread 5");

        Runnable task4 = ()->
        {
            System.out.println(Thread.currentThread().getName() + " is implemented using lambda expression");
        };

        Thread thread6 = new Thread(task4, "Thread 6");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
    }
}
