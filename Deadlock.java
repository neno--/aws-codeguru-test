public class Deadlock {

  private boolean stopped = true;

  public synchronized void run() {
    if (isStopped()) {
      stopped = false;
    }
  }

  private boolean isStopped() {
    return stopped;
  }

  public synchronized void stopProcess() {
    if (!isStopped()) {
      System.out.println("Stopping...");
      stopped = true;
    }
  }

  public static void main(String args[]) throws InterruptedException {
    Deadlock deadlock = new Deadlock();

    Thread threadA = new Thread(() -> {
      deadlock.run();
      System.out.println("Running...");
    });
    Thread threadB = new Thread(() -> {
      deadlock.stopProcess();
      System.out.println("Stopped...");
    });
    threadA.start();
    threadB.start();
  }
}

