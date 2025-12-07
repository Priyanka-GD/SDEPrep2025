package multithreading.youtubelearn;

import java.security.spec.ECField;

public class ThreadTester {
    private static final Object lock = new Object();

    public static void main (String args[]) {

        System.out.println("Main is Starting");
        //Threads share same memory space, so they can share resources, so one thread at a time can have access to a shared resource

        /*thread1 spawned from main thread, so child of main thread
        Main is starting and exiting,No order being followed
        No user thread running, then mercy of JVM to run either daemon
        thread or continue & finish its job or end daemon thread abruptly
        Daemon thread lives to serve a user thread, if user thread dies, daemon thread no existence */
        /* Interview purpose, We should implement Runnable because then we can implement other interfaces
         * as Java supports multiple implementation but doesn't allow multiple inheritence so using extend Thread
         * will have constraint.*/

        //Thread1 thread1 = new Thread1("Thread1");
        //thread1.setDaemon(true);
        //thread1.start();

        //Thread thread2 = new Thread(new Thread2(), "Thread2");
        //thread2.start();

        //new Thread().run();
        //new Thread(new Thread2()).run();

        // Block of code inside synchronized is a critical section

        //Stack stack = new Stack(5);
        //implementation of run method of runnable interface

       /*
       new Thread(() ->
        {
            int counter = 0;
            while (++counter < 10) {
                System.out.println("Pushed : " + Thread.currentThread() + " " + stack.push(100));
            }
        }, "Pusher").start();

        new Thread(() ->
        {
            int counter = 0;
            while (++counter < 10) {
                System.out.println("Popped : " + Thread.currentThread() + " " + stack.pop());
            }
        }, "Popper").start();

        Thread thread3 = new Thread(() ->
        {
            try {
                Thread.sleep(1);

                for (int i = 10000; i > 0; i--) {
                    //System.out.println("Pushed : " + Thread.currentThread() + " " + stack.push(100));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "States");
        thread3.start();
        while (true) {
            Thread.State state = thread3.getState();
            System.out.println(state);
            if (state == Thread.State.TERMINATED)
                break;
        }

        Thread thread = new Thread(() ->
        {
            System.out.println(Thread.currentThread());
        }, "Our Thread");
        thread.start();
        try {
            thread.join();// Child threads completes first, blocked main from executing further, Sequential Execution
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        String lock1 = "Lock1";
        String lock2 = "Lock2";
        Thread thread1 = new Thread(() ->
        {
            synchronized (lock1) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("Lock Acquired by " + Thread.currentThread());
                }
            }
        }, "thread1");
        thread1.start();
        Thread thread2 = new Thread(() ->
        {
            synchronized (lock2) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("Lock Acquired by " + Thread.currentThread());
                }
            }
        }, "thread2");
        thread2.start();
        //Deadlocks - One thread waiting for another thread to relinquish a lock.
        //Wait indefinitely, program went into deadlock situation
        //To avoid deadlock, locks should be acquired in same sequence.
        System.out.println("Main is Exiting");
    }
}
/*
 * Deadlocks - One thread waiting for another thread to relinquish a lock.
 *  */