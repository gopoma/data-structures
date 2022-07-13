package RedBlackTreeJava;
import BSTJava.BST;

public class RedBlackTree<E extends Comparable<E>> extends BST<E> {
  Node<E> root;

  public RedBlackTree() {
    this.root = null;
  }

  private Node<E> rotateLeft(Node<E> node) {
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
  // Here we are to the JavaScript's Hoisting 
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

    // Rotations can't take place in last inserted Node, so in its parent
    // Let's Rotate
    if(this.ll) { // For Left Rotation
      root = rotateLeft(root);
      root.color = Node.BLACK;
      root.left.color = Node.RED;

      this.ll = false;
    } else if(this.rr) { // For Right Rotation
      root = rotateRight(root);
      root.color = Node.BLACK;
      root.right.color = Node.RED;

      this.rr = false;
    } else if(this.rl) { // For Right and then Left Rotations
      root.right = rotateRight(root.right);
      root.right.parent = root;
      root = rotateLeft(root);
      root.color = Node.BLACK;
      root.left.color = Node.RED;

      this.rl = false;
    } else if(this.lr) { // For Left and then Right Rotations
      root.left = rotateLeft(root.left);
      root.left.parent = root;
      root = rotateRight(root);
      root.color = Node.BLACK;
      root.right.color = Node.RED;

      this.lr = false;
    }

    // Let's take care of RED RED conclict
    if(conflict) { // This only can occur from the first Node above the Last inserted
      // root.parent is the parent of the Last Node inserted
      if(root.parent.right == root) { // Current Node is the right child of its parent
        final Node<E> uncle = root.parent.left;
        if(uncle == null || uncle.color == Node.BLACK) { // Checking the fifth Case's case
          // The use of if else if is because some cases would be dismissed
          if(root.left != null && root.left.color == Node.RED) { // Checking the Last Node inserted orientation, remember that is some of them is not null his sibling is null
            this.rl = true;
          } else if(root.right != null && root.right.color == Node.RED) {
            this.rr = true;
          }
        } else { // Functionality for Case 3
          uncle.color = Node.BLACK;
          root.color = Node.BLACK;
          // Remember that root.parent is the grandfather to take into consideration in the Hoisting
          if(root.parent != this.root) {
            root.parent.color = Node.RED;
          }
        }
      } else { // Current Node is the left child of its parent
        final Node<E> uncle = root.parent.right;
        if(uncle == null || uncle.color == Node.BLACK) {
          if(root.left != null && root.left.color == Node.RED) {
            this.rr = true;
          } else if(root.right != null && root.right.color == Node.RED) {
            this.lr = true;
          }
        } else {
          uncle.color = Node.BLACK;
          root.color = Node.BLACK;
          if(root.parent != this.root) {
            root.parent.color = Node.RED;
          }
        }
      }
      conflict = false;
    }
    return root;
  }
}