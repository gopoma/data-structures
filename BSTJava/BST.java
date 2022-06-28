package BSTJava;

public class BST<E extends Comparable<E>> implements IBST<E> {
  private Node<E> root;

  public BST() {
    this.root = null;
  }

  // Operaciones de búsqueda
  public E search(E x) throws Exception {
    Node<E> result = searchNode(x, this.root);
    if(result == null)
      throw new Exception("El dato "+x+" no está");
    return result.getData();
  }
  protected Node<E> searchNode(E x, Node<E> n) {
    if(n == null) {return null;}
    final int comparationResult = n.getData().compareTo(x);
    if(comparationResult == 0) {return n;}
    return (comparationResult < 0)? searchNode(x, n.getRight()) : searchNode(x, n.getLeft());
  }

  // Operaciones de inserción
  public void insert(E x) throws Exception {
    this.root = insertNode(x, this.root);
  }
  // Retorna el nodo con el que se tiene que reemplazar current
  protected Node<E> insertNode(E x, Node<E> current) throws Exception {
    Node<E> result = current;
    if(current == null) {
      result = new Node<E>(x);
    } else {
      final int comparisonResult = current.getData().compareTo(x);
      if(comparisonResult == 0) { 
        throw new Exception(x + " is duplicated"); 
      }
      if(comparisonResult < 0) {
        result.setRight(insertNode(x, current.getRight()));
      } else {
        result.setLeft(insertNode(x, current.getLeft()));
      }
    }

    return result;
  }

  // Operaciones de Eliminación
  // Precondition: !isEmpty()
  public E minRemove() {
    if(this.isEmpty()) {return null;}
    E min = this.minRecover(); // devuelve el menor del árbol
    this.root = minRemove(this.root);
    return min;
  }
  protected Node<E> minRemove(Node<E> current) {
    if(current.getLeft() != null) // busca el mínimo
      current.setLeft(minRemove(current.getLeft()));
    else
      current = current.getRight(); // Elimina el mínimo
    return current;
  }
  
  // Consultores
  public E minRecover() {
    Node<E> tmp = this.root;
    while(tmp.getLeft() != null)
      tmp = tmp.getLeft();
    return tmp.getData();
  }
  public boolean isEmpty() {
    return this.root == null;
  }

  // Operaciones de recorrido
  public String preOrder() {
    return (this.root != null)? preOrder(this.root) : "*";
  }
  public String preOrder(Node<E> current) {
    String result = current.getData().toString();
    if(current.getLeft() != null) {
      result += "-" + preOrder(current.getLeft());
    }
    if(current.getRight() != null) {
      result += "-" + preOrder(current.getRight());
    }
    return result;
  }

  public String inOrder() {
    return (this.root != null)? inOrder(this.root) : "*";
  }
  public String inOrder(Node<E> current) {
    String result = "";
    if(current.getLeft() != null) {
      result += inOrder(current.getLeft());
    }
    result += current.getData().toString() + "-";
    if(current.getRight() != null) {
      result += inOrder(current.getRight());
    }
    return result;
  }

  public String postOrder() {
    return (this.root != null)? postOrder(this.root) : "*";
  }
  protected String postOrder(Node<E> current) {
    String result = "";
    if(current.getLeft() != null) {
      result += postOrder(current.getLeft()) + "-";
    }
    if(current.getRight() != null) {
      result += postOrder(current.getRight()) + "-";
    }
    return result + current.getData().toString();
  }
}