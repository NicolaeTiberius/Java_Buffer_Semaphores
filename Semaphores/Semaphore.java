import java.util.*;

public class Semaphore {

  // This is an example class for using a primitive synchronization (semaphore,
  // lock). Please note that you
  // can ONLY put the synchronization keyword within these type of classes, and
  // nowhere else within the program.

  public int count = 1;

  public Semaphore(int i) {

    count = i;

  }

  public synchronized void pLock() { // Check if its >= 0

    count--;

    if (count < 0) {
      try {
        wait(); // Goes on the buffer queue
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }

  }

  public synchronized void vLock() {
    count++;
    if (count <= 0) {
      notify(); // Free a waiting thread
    }

  }
}
