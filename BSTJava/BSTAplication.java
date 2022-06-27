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
      tree.insert(28);
      tree.insert(36);
      System.out.println("PreOrder: " + tree.preOrder());
      System.out.println("InOrder: " + tree.inOrder());
      System.out.println("PostOrder: " +  tree.postOrder());
      System.out.println("Is 28 there?: " + tree.search(28));
      System.out.println("Is 20 there?: " + tree.search(20));
      System.out.println("Is 12 there?: " + tree.search(12));
      System.out.println("Is 60 there?: " + tree.search(60));
    } catch(Exception e) {
      System.err.println(e.getMessage());
    }
  }
}