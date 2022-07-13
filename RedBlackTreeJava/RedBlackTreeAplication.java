package RedBlackTreeJava;

public class RedBlackTreeAplication {
  public static void main(final String[] args) throws Exception {
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    tree.insert(3);
    tree.insert(21);
    tree.insert(32);
    System.out.println(tree.inOrder());
  }
}
