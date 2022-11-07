package network;

public class ResponseVisitor extends Thread {
    private final Runnable runnable;
    private boolean running;
    private boolean isPaused;
    private final int sendPerSecond;

    public ResponseVisitor(Runnable runnable, int sendPerSecond) {
        this.runnable = runnable;
        this.sendPerSecond = sendPerSecond;
        this.running = true;
        this.isPaused = false;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    @Override
    public void run() {
        while (running) {
            try {
                if (isPaused) {
                    synchronized (this) {
                        wait();
                    }
                    continue;
                }
                System.out.println("hi");
                runnable.run();
                Thread.sleep(sendPerSecond);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public synchronized void pauseThread() {
        this.isPaused = true;
    }

    public synchronized void continueThread() {
        this.isPaused = false;
        synchronized (this) {
            this.notifyAll();
        }
    }

    public void finishThread() {
        this.running = false;
        synchronized (this){
            notifyAll();
        }
    }
}
