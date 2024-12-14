package dev.erantimothy.BasicMultiThreading;

public class DaemonUserThreadDemo {
    public static void main(String[] args) {
        Thread bgThread = new Thread(new DaemonHelper());
        Thread userThread = new Thread(new UserThread());
        bgThread.setDaemon(true);

        bgThread.start(); // even though the daemon thread can run till count become 500 the program terminates
        // when the user thread finishes, since
        userThread.start();
    }
}

class DaemonHelper implements Runnable {

    @Override
    public void run() {
        int count = 0;
        while (count < 500){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
            System.out.println("Deamon Helper running...");
        }
    }
}

class UserThread implements Runnable {

    @Override
    public void run(){
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User thread done with execution.");
    }
}
