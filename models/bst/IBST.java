package models.bst;

interface IBST<E> {
  // Operaciones de búsqueda
  E search(E x) throws Exception;

  // Operaciones de inserción
  void insert(E x) throws Exception;

  // Operaciones de Eliminación
  void remove(E x);
  E minRemove();

  // Consultores
  boolean isEmpty();
  E minRecover();

  // Operaciones de recorrido
  String preOrder();
  String inOrder();
  String postOrder();
}