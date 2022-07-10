import java.util.*;

public class Buffer // Provides data and operations onto the fixed-length buffer
{
  private LinkedList<Object> buf_list;

  private int elements; // Number of elements currently on queue
  private int buf_size; // Maximum number of elements allowed on queue

  public Buffer(int n) // Buffer creation, with n indicating the maximum capacity
  {
    buf_list = new LinkedList<Object>();
    elements = 0;
    buf_size = n;
  }

  public void add(int num) { // add elements in the buffer list.

    buf_list.add(num);

    elements++;

  }

  public void remove() { // removes last element from buffer list.

    buf_list.removeLast();

  }

  // returns if the list is empty by checking the current size.
  public boolean isEmpty() {
    return buf_list.isEmpty();
  }

  public boolean isFull() { // Checks if buffer is full

    return buf_list.size() == bufferList();
  }

  // Size of the buffer currently
  public int bufferTotal() {
    return buf_list.size();

  }

  // Maximum size of buffer (specified)
  public int bufferList() {
    return buf_size;
  }

}
