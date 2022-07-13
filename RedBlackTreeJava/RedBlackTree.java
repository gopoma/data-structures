package RedBlackTreeJava;

public class RedBlackTree<E extends Comparable<E>> {
  Node<E> root;

  public RedBlackTree() {
    this.root = null;
  }

  private Node<E> rorateLeft(Node<E> node) {
    Node<E> rightChild = node.right;
    node.right = rightChild.left;
    rightChild.left = node;

    return rightChild;
  }
  private Node<E> rotateRight(Node<E> node) {
    Node<E> leftChild = node.left;
    node.left = leftChild.right;
    leftChild.right = node;

    return leftChild;
  }
  public void insertNode(E data) throws Exception {
    if(this.root == null) {
      this.root = new Node<>(data);
      this.root.color = Node.BLACK;
    } else {
      try {
        this.root = insertNode(this.root, data);
      } catch(Exception ex) {
        System.out.println(ex.getMessage());
      }
    }
  }

  boolean ll = false;
  boolean rr = false;
  boolean lr = false;
  boolean rl = false;
  protected Node<E> insertNode(Node<E> root, E data) throws Exception {
    boolean conflict = false;

    // Recursive calls to insert at proper position according to BST properties
    if(root == null) {
      return new Node<>(data);
    }
    final int comparisonResult = data.compareTo(root.data);
    if(comparisonResult == 0) {
      throw new Exception("Item already there!");
    }
    if(comparisonResult < 0) { // Lower than
      root.left = insertNode(root.left, data);
      root.left.parent = root;
      if(root != this.root) { // Inserting to the root case was implemented in the main condition in insertNode(data)
        if(root.color == Node.RED && root.left.color == Node.RED) { // Remember that we are traversing through the Left Side, so at this moment our brother doesn't exist
          conflict = true;
        }
      }
    } else { // Greater than
      root.right = insertNode(root.right, data);
      root.right.parent = root;
      if(root != this.root) {
        if(root.color == Node.RED && root.right.color == Node.RED) {
          conflict = true;
        }
      }
    }
  }
}