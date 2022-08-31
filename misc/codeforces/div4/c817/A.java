package misc.codeforces.div4.c817;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.HashMap;

public class A {
  public static void main(final String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    HashMap<Character, Boolean> pre = new HashMap<>();
    pre.put('T', false);
    pre.put('i', false);
    pre.put('m', false);
    pre.put('u', false);
    pre.put('r', false);

    int t = Integer.parseInt(br.readLine());
    while(t-- > 0) {
      final int n = Integer.parseInt(br.readLine());
      final String s = br.readLine();
      if(n == 5) {
        for(char c: s.toCharArray()) {
          if(pre.containsKey(c)) {
            pre.put(c, true);
          }
        }
        
        boolean result = true;
        for(char k: pre.keySet()) {
          result = result && pre.get(k);
        }
        System.out.println(result ? "YES" : "NO");
        pre.put('T', false);
        pre.put('i', false);
        pre.put('m', false);
        pre.put('u', false);
        pre.put('r', false);
      } else {
        System.out.println("NO");
      }
    }
  }
}
