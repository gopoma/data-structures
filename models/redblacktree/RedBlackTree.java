package models.redblacktree;
import models.bst.BST;

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

  public void deleteNode(E key) {
    Node<E> node = root;
  
    // Find the node to be deleted
    while (node != null && node.data.compareTo(key) != 0) {
      // Traverse the tree to the left or right depending on the key
      if (key.compareTo(node.data) < 0) {
        node = node.left;
      } else {
        node = node.right;
      }
    }
  
    // Node not found?
    if (node == null) {
      return;
    }
  
    // At this point, "node" is the node to be deleted
  
    // In this variable, we'll store the node at which we're going to start to fix the R-B
    // properties after deleting a node.
    Node<E> movedUpNode;
    boolean deletedNodeColor;
  
    // Node has zero or one child
    if (node.left == null || node.right == null) {
      movedUpNode = deleteNodeWithZeroOrOneChild(node);
      deletedNodeColor = node.color;
    }
  
    // Node has two children
    else {
      // Find minimum node of right subtree ("inorder successor" of current node)
      Node<E> inOrderSuccessor = findMinimum(node.right);
  
      // Copy inorder successor's data to current node (keep its color!)
      node.data = inOrderSuccessor.data;
  
      // Delete inorder successor just as we would delete a node with 0 or 1 child
      movedUpNode = deleteNodeWithZeroOrOneChild(inOrderSuccessor);
      deletedNodeColor = inOrderSuccessor.color;
    }
  
    if (deletedNodeColor == Node.BLACK) {
      fixRedBlackPropertiesAfterDelete(movedUpNode);
  
      // Remove the temporary NIL node
      if (movedUpNode.getClass() == NilNode.class) {
        replaceParentsChild(movedUpNode.parent, movedUpNode, null);
      }
    }
  }

  private Node<E> deleteNodeWithZeroOrOneChild(Node<E> node) {
    // Node has ONLY a left child --> replace by its left child
    if (node.left != null) {
      replaceParentsChild(node.parent, node, node.left);
      return node.left; // moved-up node
    }
  
    // Node has ONLY a right child --> replace by its right child
    else if (node.right != null) {
      replaceParentsChild(node.parent, node, node.right);
      return node.right; // moved-up node
    }
  
    // Node has no children -->
    // * node is red --> just remove it
    // * node is black --> replace it by a temporary NIL node (needed to fix the R-B rules)
    else {
      Node<E> newChild = node.color == Node.BLACK ? new NilNode<>() : null;
      replaceParentsChild(node.parent, node, newChild);
      return newChild;
    }
  }

  private Node<E> findMinimum(Node<E> node) {
    while (node.left != null) {
      node = node.left;
    }
    return node;
  }

  private void replaceParentsChild(Node<E> parent, Node<E> oldChild, Node<E> newChild) {
    if (parent == null) {
      root = newChild;
    } else if (parent.left == oldChild) {
      parent.left = newChild;
    } else if (parent.right == oldChild) {
      parent.right = newChild;
    } else {
      throw new IllegalStateException("Node is not a child of its parent");
    }
  
    if (newChild != null) {
      newChild.parent = parent;
    }
  }

  private void fixRedBlackPropertiesAfterDelete(Node<E> node) {
    // Case 1: Examined node is root, end of recursion
    if (node == root) {
      // Uncomment the following line if you want to enforce black roots (rule 2):
      // node.color = BLACK;
      return;
    }
  
    Node<E> sibling = getSibling(node);
  
    // Case 2: Red sibling
    if (sibling.color == Node.RED) {
      handleRedSibling(node, sibling);
      sibling = getSibling(node); // Get new sibling for fall-through to cases 3-6
    }
  
    // Cases 3+4: Black sibling with two black children
    if (isBlack(sibling.left) && isBlack(sibling.right)) {
      sibling.color = Node.RED;
  
      // Case 3: Black sibling with two black children + red parent
      if (node.parent.color == Node.RED) {
        node.parent.color = Node.BLACK;
      }
  
      // Case 4: Black sibling with two black children + black parent
      else {
        fixRedBlackPropertiesAfterDelete(node.parent);
      }
    }
  
    // Case 5+6: Black sibling with at least one red child
    else {
      handleBlackSiblingWithAtLeastOneRedChild(node, sibling);
    }
  }
  private Node<E> getSibling(Node<E> node) {
    Node<E> parent = node.parent;
    if (node == parent.left) {
      return parent.right;
    } else if (node == parent.right) {
      return parent.left;
    } else {
      throw new IllegalStateException("Parent is not a child of its grandparent");
    }
  }
  
  private boolean isBlack(Node<E> node) {
    return node == null || node.color == Node.BLACK;
  }

  private void handleRedSibling(Node<E> node, Node<E> sibling) {
    // Recolor...
    sibling.color = Node.BLACK;
    node.parent.color = Node.RED;
  
    // ... and rotate
    if (node == node.parent.left) {
      rotateLeft(node.parent);
    } else {
      rotateRight(node.parent);
    }
  }

  private void handleBlackSiblingWithAtLeastOneRedChild(Node<E> node, Node<E> sibling) {
    boolean nodeIsLeftChild = node == node.parent.left;
  
    // Case 5: Black sibling with at least one red child + "outer nephew" is black
    // --> Recolor sibling and its child, and rotate around sibling
    if (nodeIsLeftChild && isBlack(sibling.right)) {
      sibling.left.color = Node.BLACK;
      sibling.color = Node.RED;
      rotateRight(sibling);
      sibling = node.parent.right;
    } else if (!nodeIsLeftChild && isBlack(sibling.left)) {
      sibling.right.color = Node.BLACK;
      sibling.color = Node.RED;
      rotateLeft(sibling);
      sibling = node.parent.left;
    }
  
    // Fall-through to case 6...
  
    // Case 6: Black sibling with at least one red child + "outer nephew" is red
    // --> Recolor sibling + parent + sibling's child, and rotate around parent
    sibling.color = node.parent.color;
    node.parent.color = Node.BLACK;
    if (nodeIsLeftChild) {
      sibling.right.color = Node.BLACK;
      rotateLeft(node.parent);
    } else {
      sibling.left.color = Node.BLACK;
      rotateRight(node.parent);
    }
  }
}