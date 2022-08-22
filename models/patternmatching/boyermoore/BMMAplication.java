package models.patternmatching.boyermoore;

import java.util.HashMap;
// import java.util.Arrays; // Just for Debugging the LOF 

public class BMMAplication {
  public static final int EXTENDED_ASCII_LIMIT = 256;

  public static int[] getLastOccurenceFunction(String pattern) {
    int[] L = new int[EXTENDED_ASCII_LIMIT];
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

  public static void main(final String[] args) {
    String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus ac ornare orci. Fusce id viverra felis, non commodo tellus. In sit amet lacus vulputate, aliquam leo sed, porttitor arcu. Donec in nibh nec leo gravida facilisis et vel risus. Mauris faucibus dictum est, vitae tempus enim hendrerit vitae. Vivamus id urna mollis, commodo metus a, laoreet quam. Phasellus aliquam vestibulum porttitor. In nisi urna, sodales rhoncus magna at, mollis accumsan eros. Vivamus luctus orci sed elit faucibus congue. Phasellus cursus luctus ex sed dapibus. Duis sit amet elementum mauris. Etiam mauris purus, tempus sed imperdiet eu, eleifend venenatis tellus. Morbi mi nisl, aliquet ac neque rhoncus, accumsan sodales felis. Nunc nunc felis, consequat a leo eu, tincidunt fringilla turpis. Sed sodales dictum semper. Nullam sed dui ornare odio scelerisque interdum sed sed libero. In turpis arcu, imperdiet nec sapien in, scelerisque vulputate ipsum. Suspendisse eget ultricies risus. Proin a pulvinar quam, et tristique nulla. Aenean et tincidunt est, sit amet elementum turpis. Aliquam faucibus magna nec tellus maximus ornare sed eu justo. Nam iaculis tellus in accumsan fringilla. Suspendisse non est justo. Nam tempus rutrum sem. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed aliquam at velit in finibus. Maecenas id quam lobortis, convallis nibh at, pharetra felis. Nam ornare nisi lectus, quis congue turpis mattis gravida. In finibus ipsum ut nisl semper sodales. Sed euismod luctus eros, id rutrum dui luctus quis. Donec et ex ut erat lobortis consectetur in sed massa. Nam maximus vitae nisl vitae faucibus. Nunc finibus, velit quis eleifend vestibulum, nibh mi interdum risus, sed pellentesque arcu turpis vel erat. Duis lacus sem, volutpat quis leo id, posuere lobortis erat. Proin sed malesuada nulla, non maximus lacus. Maecenas elementum vehicula nibh, dictum pulvinar mi congue ac. Vivamus gravida velit id eros lacinia, in eleifend mi fringilla. Aliquam porttitor dui at vestibulum semper. Morbi faucibus ac ipsum eget venenatis. Etiam ipsum libero, posuere non metus eu, facilisis rhoncus libero. In purus nisl, consectetur at efficitur quis, luctus eget dolor. Nunc fringilla volutpat cursus. Etiam interdum nibh purus, eget pellentesque nibh laoreet vel. Proin euismod vel urna id dictum. Ut elementum elit tellus, mattis maximus leo tempor vel. Morbi ultrices tellus nec faucibus euismod. Donec porttitor turpis et dolor pulvinar, ac aliquet mi ullamcorper. Suspendisse molestie metus eu iaculis lobortis. Vivamus ultricies malesuada lectus, sit amet ultricies tortor varius a. Praesent lobortis eros sit amet diam scelerisque, sollicitudin pellentesque orci venenatis. Sed cursus blandit accumsan. In malesuada mattis nibh. Proin sed est orci. Fusce venenatis ante vitae efficitur vehicula. Morbi semper nec libero a blandit. Phasellus luctus tempor volutpat. Mauris venenatis nisl et tempus venenatis. Vestibulum bibendum in tortor eu egestas. Mauris elementum ipsum a iaculis ultrices. Aenean non pretium odio, quis porta ipsum. Aliquam vitae interdum arcu. Vestibulum tristique nibh in orci mollis tempus. Praesent condimentum libero a felis consequat, in blandit lacus fringilla. Praesent facilisis molestie fermentum. Maecenas laoreet enim ac ligula luctus, quis feugiat nunc placerat. Praesent ac ullamcorper neque. Morbi mollis commodo felis, at faucibus tortor semper iaculis. In hendrerit tortor sed tellus accumsan suscipit ac non dolor. Donec vel ante id turpis hendrerit malesuada. Ut nec dolor semper, scelerisque massa vitae, gravida nulla. Morbi volutpat felis sit amet purus volutpat tincidunt. Cras vitae mi diam. Morbi venenatis augue sit amet accumsan pellentesque. Pellentesque eros lacus, aliquet ut enim sed, lacinia imperdiet mauris. Mauris auctor sapien libero, sagittis vehicula eros dapibus facilisis. Suspendisse eleifend nulla erat, vel egestas diam imperdiet ut. Donec ut quam sed elit semper consectetur. Nullam mollis velit quis dignissim tincidunt. Nam non lectus ut massa luctus consectetur. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed eu ligula mattis, cursus dolor id, gravida diam. Donec nec est nec justo egestas tristique quis at massa. Donec euismod pretium velit, a accumsan massa posuere nec. Duis ac tincidunt eros. Nunc molestie dui quis ex faucibus, in accumsan quam efficitur. Maecenas lobortis vitae nisi vitae mollis. Sed ullamcorper nunc non ullamcorper placerat. Mauris ullamcorper purus ut ante porta tempor. Etiam non elit vitae erat imperdiet egestas. Morbi vulputate ultricies nulla et pellentesque. Vestibulum bibendum libero at risus mattis, vitae pretium nisi dignissim. Sed gravida facilisis pellentesque. Quisque sit amet posuere purus. Aenean pharetra ex vel malesuada sodales. Nam a vulputate tellus. Aliquam quis blandit magna. Vivamus fermentum augue quis elit fringilla semper. Duis dignissim felis at risus bibendum efficitur. Nunc consectetur a ante eget venenatis. Nulla eget odio in dolor condimentum rutrum id id nisi. Vestibulum vel tincidunt purus, et accumsan est. Aenean dictum imperdiet diam, ac porta nunc dictum eget. Donec porta, eros sit amet dignissim ultricies, enim ex suscipit nisl, a venenatis lectus magna vitae mi. Nam sed metus fringilla, luctus ante vel, pharetra diam. In hac habitasse platea dictumst. Duis tempor arcu sit amet magna convallis, ac consequat orci feugiat. Nam nec mi lobortis, luctus quam sed, suscipit diam. Nulla viverra justo quis varius lobortis. Fusce volutpat viverra congue. Nulla ac lacus eros. Nunc nec efficitur urna. Cras dui nulla, consequat ut ornare ut, mattis quis augue. Nam aliquam, quam et dapibus tincidunt, quam felis lacinia libero, vitae tincidunt velit libero et justo. Nullam eu lacus vulputate sapien mattis dignissim ac ut leo. Donec eget volutpat nibh. Aenean dignissim commodo dolor at vehicula. Integer dui nibh, ultrices quis enim eu, vulputate laoreet sem. Praesent nulla mauris, condimentum quis bibendum faucibus, luctus vitae metus. Sed lacinia orci ac neque maximus varius. Vestibulum felis mauris, viverra in purus at, cursus cursus felis. Nulla molestie pharetra tincidunt. Etiam venenatis ac nisi quis consequat. Quisque vel dolor quis sapien blandit cursus et vel turpis. Fusce iaculis tortor pellentesque elit placerat, ac tincidunt arcu pharetra. Vivamus sed felis bibendum ipsum ullamcorper imperdiet. Nullam suscipit magna sed egestas sollicitudin. Nunc enim lorem, consequat sit amet orci ac, condimentum laoreet neque. Cras ut diam vitae nibh ultricies hendrerit. Donec mattis condimentum congue. Donec sit amet ex in orci ullamcorper iaculis. Aenean dignissim at risus nec cursus. Cras rutrum libero nibh, ac pellentesque sem tincidunt a. Donec nec laoreet dolor. Sed id dui et purus condimentum sagittis ac a purus. Nulla convallis, nulla eu ullamcorper gravida, nisl odio suscipit arcu, nec mollis ex erat eu magna. A good algorithm is that can't be broken with hacks.";
    String pattern = "with ";

    long initial = System.nanoTime();
    int result = matchWithBoyerMoore(text, pattern);
    long executionTime = System.nanoTime() - initial;
    System.out.println("Execution Time: "+executionTime+"ns");

    final boolean found = result != -1;
    System.out.println("Was Found?: "+(found ? "YES":"NO"));
    if(found) {
      System.out.println("Match at: "+result);
    }
  }
}
