import java.util.*;

public class Webserver implements Runnable { // Web server removes elements from the buffer

  private int id;
  private int num_elements;
  private static Buffer buf;
  private int n;

  private Semaphore webSemaphore;

  public Webserver(int i, int el, Buffer b, Semaphore w) {
    id = i;
    num_elements = el;
    buf = b;
    webSemaphore = w;

  }

  public void remove_element() {

    while (num_elements > 0) { // removes same way as user adds except it checks while its above 0 it will just
                               // keep removing with a semaphore to ensure it does it concurrently.
      webSemaphore.pLock();
      if (buf.isEmpty() == true) {

        System.out.println("Buffer empty - web server wait");

      } else {
        buf.remove();
        num_elements--;
        n++;
        System.out.println("Server " + id + " removed element " + buf.bufferTotal() + "/" + buf.bufferList());

      }
      webSemaphore.vLock();

    }
  }

  public int items_Removed() { // total items each server removed.
    return n;
  }

  public void run() {
    remove_element();
  }
}