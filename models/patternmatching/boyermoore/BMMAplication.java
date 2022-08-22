package models.patternmatching.boyermoore;

import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class BMMAplication {
  public static int[] getLastOccurenceFunction(String pattern) {
    int[] L = new int[256];
    for(int i = 0; i < L.length; i++) 
      L[i] = -1;

    for(int i = 0; i < pattern.length(); i++) {
      L[pattern.charAt(i)] = i;
    }
    return L;
  }

  public static int matchWithBoyerMoore(String T, String P) {
    int[] L = getLastOccurenceFunction(P);
    
    final int n = T.length();
    final int m = P.length();
    int i = m - 1;
    int j = m - 1;

    do {
      if(T.charAt(i) == P.charAt(j)) {
        if(j == 0) {
          return i; // match at i
        } else {
          i--;
          j--;
        }
      } else {
        int l = L[T.charAt(i)];
        i += m - Math.min(j, 1 + l);
        j = m - 1;
      }
    } while(i <= n - 1);

    return -1;
  }

  public static void main(final String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    System.out.print("text: ");
    String text = br.readLine();
    System.out.print("pattern: ");
    String pattern = br.readLine();

    int result = matchWithBoyerMoore(text, pattern);
    System.out.println("Was Founded?: "+(result != -1 ? "YES":"NO"));
    if(result != -1) {
      System.out.println("Matchs at: "+result);
    }
  }
}
