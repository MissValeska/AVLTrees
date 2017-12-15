// Adapted from Code By Y. Daniel Liang
// Modified by C. Lee-Klawender
// Note: this interface is NOT the same as the General Tree version shown in Catalyst

interface Visitor<T>
{
    public void visit(T obj);
}

public interface TreeInterface<E>
{

  /** Clear the whole Tree */
  public void clear();

  /** Return true if the element is in the tree */
  public boolean contains(E e);

    /** Return an element in the tree which matches the parameter*/
  public E getEntry(E e);

  /** Insert element o into the binary tree
   * Return true if the element is inserted successfully */
  public boolean insert(E e);

  /** Delete the specified element from the tree
   * Return true if the element is deleted successfully */
  public boolean delete(E e);

  /** Inorder traversal from the root*/
  public void inorder(Visitor<E> visitor);

  /** Postorder traversal from the root */
  public void postorder(Visitor<E> visitor);

  /** Preorder traversal from the root */
  public void preorder(Visitor<E> visitor);

  /** Get the number of nodes in the tree */
  public int getSize();

  /** Return true if the tree is empty */
  public boolean isEmpty();
}
