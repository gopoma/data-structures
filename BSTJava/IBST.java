package BSTJava;

interface IBST<E> {
  // Operaciones de búsqueda
  E search(E x) throws Exception;

  // Operaciones de inserción
  void insert(E x) throws Exception;

  // Operaciones de Eliminación
  E minRemove();

  // Consultores
  boolean isEmpty();

  // Operaciones de recorrido
  String preOrder();
  String inOrder();
  String postOrder();
}