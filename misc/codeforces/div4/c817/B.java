package misc.codeforces.div4.c817;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class B {
  public static void main(final String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while(t-- > 0) {
      final int n = Integer.parseInt(br.readLine());
      final String s = br.readLine();
      final String s2 = br.readLine();

      boolean result = true;
      for(int i = 0; i < n; i++) {
        final char current = s.charAt(i);
        final char current2 = s2.charAt(i);
        if((current == 'R' || current2 == 'R') && current != current2) {
          result = false;
        }
      }
      System.out.println(result ? "YES" : "NO");
    }
  }
}
