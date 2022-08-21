package java.heap;

import java.lang.reflect.Array;

public class HeapAplication {
  public static void main(final String[] args) {
    final Integer[] heap = createGenericArray(Integer.class, 255);
    insert(16, heap);
    insert(14, heap);
    insert(10, heap);
    insert(8, heap);
    insert(7, heap);
    insert(9, heap);
    insert(3, heap);
    insert(2, heap);
    insert(4, heap);
    insert(20, heap);
    insert(100, heap);
    insert(11, heap);
    showHeap(heap);
  }

  public static <E> E[] createGenericArray(Class<E> dataType, int length) {
    return (E[]) Array.newInstance(dataType, length);
  }
  public static <E> int getLength(E[] heap) {
    int length = 0;
    for(int i = 1; i < heap.length && heap[i] != null; i++) {
      length++;
    }
    return length;
  }
  public static <E extends Comparable<E>> void insert(E item, E[] heap) {
    int tam = getLength(heap);
    heap[++tam] = item;
    E tmp;

    for(int i = tam; i > 1 && heap[i].compareTo(heap[i/2]) > 0; i/=2) {
      tmp = heap[i];
      heap[i] = heap[i/2];
      heap[i/2] = tmp;
    }
  }
  public static <E> void showHeap(E[] arr) {
    for(int i = 1; i < arr.length && arr[i] != null; i++) {
      System.out.print(arr[i] + " ");
    }
  }
}