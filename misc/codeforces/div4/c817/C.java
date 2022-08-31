package misc.codeforces.div4.c817;

import java.util.Scanner;
import java.util.ArrayList;

public class C {
  public static void main(final String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    ArrayList<String> w1 = new ArrayList<>();
    ArrayList<String> w2 = new ArrayList<>();
    ArrayList<String> w3 = new ArrayList<>();

    while(t-- > 0) {
      final int n = sc.nextInt();
      for(int i = 0; i < n; i++)
        w1.add(sc.next());
      for(int i = 0; i < n; i++)
        w2.add(sc.next());
      for(int i = 0; i < n; i++)
        w3.add(sc.next());

      int t1 = 0;
      int t2 = 0;
      int t3 = 0;
      while(!w1.isEmpty()) {
        final String current = w1.get(w1.size() - 1);
        if(w2.contains(current) && w3.contains(current)) {
          w2.remove(current);
          w3.remove(current);
        } else if(w2.contains(current) && !w3.contains(current)) {
          t1 += 1;
          t2 += 1;
          w2.remove(current);
        } else if(!w2.contains(current) && w3.contains(current)) {
          t1 += 1;
          t3 += 1;
          w3.remove(current);
        } else {
          t1 += 3;
        }
        w1.remove(w1.size() - 1);
      }

      while(!w2.isEmpty()) {
        final String current = w2.get(w2.size() - 1);
        if(w1.contains(current) && w3.contains(current)) {
          w1.remove(current);
          w3.remove(current);
        } else if(w1.contains(current) && !w3.contains(current)) {
          t2 += 1;
          t1 += 1;
          w1.remove(current);
        } else if(!w1.contains(current) && w3.contains(current)) {
          t2 += 1;
          t3 += 1;
          w3.remove(current);
        } else {
          t2 += 3;
        }
        w2.remove(w2.size() - 1);
      }

      while(!w3.isEmpty()) {
        final String current = w3.get(w3.size() - 1);
        if(w1.contains(current) && w2.contains(current)) {
          w1.remove(current);
          w2.remove(current);
        } else if(w1.contains(current) && !w2.contains(current)) {
          t3 += 1;
          t1 += 1;
          w1.remove(current);
        } else if(!w1.contains(current) && w2.contains(current)) {
          t3 += 1;
          t2 += 1;
          w2.remove(current);
        } else {
          t3 += 3;
        }
        w3.remove(w3.size() - 1);
      }

      System.out.println(t1 + " " + t2 + " " + t3);
    }

    sc.close();
  }
}
