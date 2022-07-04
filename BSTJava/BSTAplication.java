package BSTJava;

public class BSTAplication {
  public static void main(final String[] args) {
    try {
      BST<Integer> tree = new BST<>();
      tree.insert(20);
      tree.insert(10);
      tree.insert(5);
      tree.insert(12);
      tree.insert(25);
      tree.insert(30);
      tree.insert(4);
      tree.insert(28);
      tree.insert(36);
      tree.insert(2);
      System.out.println("PreOrder: " + tree.preOrder());
      System.out.println("InOrder: " + tree.inOrder());
      System.out.println("PostOrder: " +  tree.postOrder());
      System.out.println("Is 28 there?: " + tree.search(28));
      System.out.println("Is 20 there?: " + tree.search(20));
      System.out.println("Is 12 there?: " + tree.search(12));
      // System.out.println("Is 60 there?: " + tree.search(60));
      System.out.println("Minimum Value: " + tree.minRecover());
      // Deleting the minimum value in the BST
      System.out.println(tree.minRemove());
      System.out.println(tree.postOrder());
      // Deleting the next minimum value in the BST
      System.out.println(tree.minRemove());
      System.out.println(tree.postOrder());
      // Deleting some values
      BST<Integer> tmp = new BST<>();
      tmp.insert(10);
      tmp.insert(4);
      tmp.insert(8);
      tmp.insert(3);
      tmp.insert(20);
      tmp.insert(50);
      tmp.insert(100);
      tmp.insert(12);
      tmp.insert(22);
      tmp.insert(24);
      tmp.insert(28);
      tmp.insert(27);
      tmp.insert(29);
      tmp.insert(30);
      System.out.println(tmp.postOrder());
      tmp.remove(20);
      System.out.println(tmp.postOrder());
    } catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }
}