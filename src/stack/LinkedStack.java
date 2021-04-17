package stack;

/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List
 * structure to allow for unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {

  private int size;
  private LLNode<T> head = null;

  public LinkedStack() {
    this.head = null;
    this.size = 0;
  }

  /** {@inheritDoc} */
  @Override
  public T pop() throws StackUnderflowException {
    T poppedItem = null;
    if (isEmpty()) {
      throw new StackUnderflowException();
    } else {
      poppedItem = head.getData();
      head = head.getNext();
      this.size--;
    }
    return poppedItem;
  }

  /** {@inheritDoc} */
  @Override
  public T top() throws StackUnderflowException {
    T topItem = null;
    if (size == 0) {
      throw new StackUnderflowException();
    } else {
      topItem = head.getData();
    }
    return topItem;
  }

  /** {@inheritDoc} */
  @Override
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    } else {
      return false;
    }
  }

  /** {@inheritDoc} */
  @Override
  public int size() {
    return this.size;
  }

  /** {@inheritDoc} */
  @Override
  public void push(T elem) {
    LLNode<T> new_node = new LLNode<T>(elem);
    if (head == null) {
      head = new_node;
    } else {
      new_node.setNext(head);
    }
    head = new_node;
    head.setData(elem);
    this.size++;
  }
}
