package BruteForceMatch;

public class BFMAplication {
  public static void main(final String[] args) {
    final String text = "The busboy is a good npm package";
    final String pattern = "package";

    System.out.println(bruteForceMatch(text, pattern));
  }

  public static int bruteForceMatch(String text, String pattern) {
    // We also take care the case that pattern's length is greater than text's length
    for(int i = 0; i <= text.length() - pattern.length(); i++) {
      int j = 0;
      while(j < pattern.length() && text.substring(i + j, i + j + 1).equals(pattern.substring(j, j + 1))) {
        j++;
      }
      if(j == pattern.length()) {
        return i; // Match at i
      }
    }
    return -1; // No Match Anywhere
  }
}
