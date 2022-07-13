package RedBlackTreeJava;

public class Node<E> {
  static final boolean RED = true;
  static final boolean BLACK = false;

  E data;
  Node<E> left;
  Node<E> right;
  boolean color;
  Node<E> parent;

  public Node(E data) {
    this.data = data;
    this.left = null;
    this.right = null;
    this.color = Node.RED;
    this.parent = null;
  }
}

class NilNode<E> extends Node<E> {
  public NilNode() {
    super(null);
    this.color = Node.BLACK;
  }
}