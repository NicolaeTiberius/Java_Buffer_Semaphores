import java.util.*;

public class User implements Runnable {

  private int id;
  private int num_elements;
  private static Buffer buf;
  private int n;

  private Semaphore uSemaphore;

  public User(int i, int el, Buffer b, Semaphore u) // Created user will add a certain number of elements to the buffer.
  {

    id = i;
    num_elements = el;
    buf = b;
    uSemaphore = u;

  }

  public void add_element() {

    while (num_elements > 0) {

      uSemaphore.pLock();

      if (buf.isFull() == true) { // if buffer is full it should wait for the server to remove until it can add
                                  // again, semaphores help with this by locking.
        System.out.println("Buffer full -- User now sleeping");
      } else {
        buf.add(n); // add to the user
        n++;

        num_elements--;

        System.out.println("User " + id + " adds an element");

      }
      uSemaphore.vLock();
    }
  }

  public int elements_added() {
    return n;
  }

  public void run() {

    add_element();

  }
}
