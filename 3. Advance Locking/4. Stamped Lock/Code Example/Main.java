package StampedLockExample;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        SharedResource resource = new SharedResource();

        Thread writer = new Thread(() ->
        {
            for (int i = 0; i < 5; i++)
            {

                try
                {
                    resource.write(i);
                    Thread.sleep(500);
                }
                catch (InterruptedException e)
                {

                }
            }
        }, "Writer Thread");

        Thread reader1 = new Thread(() ->
        {
            for (int i = 0; i < 5; i++)
            {

                try
                {
                    resource.read();
                    Thread.sleep(250);
                }
                catch (InterruptedException e)
                {

                }
            }
        }, "Pessimistic Reader Thread");

        Thread reader2 = new Thread(() ->
        {
            for (int i = 0; i < 5; i++)
            {

                try
                {
                    resource.readOptimistic();
                    Thread.sleep(250);
                }
                catch (InterruptedException e)
                {

                }
            }
        }, "Pessimistic Reader Thread");

        writer.start();
        reader1.start();
        reader2.start();

        writer.join();
        reader1.join();
        reader2.join();

    }
}