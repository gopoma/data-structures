package java.patternmatching.bruteforce;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class BFMAplication {
  public static void main(final String[] args) throws IOException {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("Text: ");
    final String text = br.readLine();
    System.out.print("Pattern: ");
    final String pattern = br.readLine();

    System.out.printf("Match At: %d", bruteForceMatch(text, pattern));
  }

  public static int bruteForceMatch(String text, String pattern) {
    // We also take care the case that pattern's length is greater than text's length
    for(int i = 0; i <= text.length() - pattern.length(); i++) {
      int j = 0;
      while(j < pattern.length() && text.charAt(i + j) == pattern.charAt(j)) {
        j++;
      }
      if(j == pattern.length()) {
        return i; // Match at i
      }
    }
    return -1; // No Match Anywhere
  }
}
