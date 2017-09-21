
import java.util.ArrayList;
import java.util.List;

public class SortingAll {

    /**
     * ** Begin Quick Sort ***
     */
    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    public static void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    public static int partition(int[] list, int first, int last) {
        int pivot = list[first];
        int low = first + 1;
        int high = last;

        while (high > low) {

            while (low <= high && list[low] <= pivot) {
                low++;
            }

            while (low <= high && list[high] > pivot) {
                high--;
            }

            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && list[high] >= pivot) {
            high--;
        }

        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return first;
        }
    }

    /**
     * ** End Quick Sort ***
     */
    /**
     * ** Begin Merge Sort ***
     */
    public static void mergeSort(int[] list) {
        if (list.length > 1) {

            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf);

            int secondHalfLength = list.length - list.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length / 2,
                    secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);
            merge(firstHalf, secondHalf, list);
        }
    }

    public static void merge(int[] list1, int[] list2, int[] temp) {
        int current1 = 0; // Current index in list1
        int current2 = 0; // Current index in list2
        int current3 = 0; // Current index in temp

        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1] < list2[current2]) {
                temp[current3++] = list1[current1++];
            } else {
                temp[current3++] = list2[current2++];
            }
        }

        while (current1 < list1.length) {
            temp[current3++] = list1[current1++];
        }

        while (current2 < list2.length) {
            temp[current3++] = list2[current2++];
        }
    }

    /**
     * ** End Merge Sort ***
     */
    /**
     * ** Begin Radix Sort ***
     */
    public static void radixSort(int[] list) {
        final int length = 10;

        List<Integer>[] bucket = new ArrayList[length];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<Integer>();
        }

        boolean maxLength = false;
        int num1 = -1, num2 = 1;
        while (!maxLength) {
            maxLength = true;

            for (Integer x : list) {
                num1 = x / num2;
                bucket[num1 % length].add(x);
                if (maxLength && num1 > 0) {
                    maxLength = false;
                }
            }

            int m = 0;
            for (int n = 0; n < length; n++) {
                for (Integer y : bucket[n]) {
                    list[m++] = y;
                }
                bucket[n].clear();
            }

            num2 *= length;

        }
    }

    /**
     * ** End Radix Sort ***
     */
    
    /**
     * ** Begin Radix Binary Sort ***
     */
    /*
    public static void binarySort(int[] list) {
        int m = 1000000;
        int p = 1;
        int x = 0;
        int[] list2 = new int[m];

        for (int size = 0; size < 32; size++) {
            for (int times = 31250; times > 0; times--) {

                list2[x] = ((list[x] & p) >> size);
                p = p << 1;
                x++;
            }
        }
        for (int size = 0; size < m; size++){
         System.out.println(list2[size]);   
        }
    }
*/
    /**
     * ** End Radix Binary Sort ***
     */
    /**
     * ** Begin Heap Sort ***
     */
    public static class Heap<E extends Comparable<E>> {

        private java.util.ArrayList<E> list = new java.util.ArrayList<>();

        public Heap() {
        }

        public Heap(E[] objects) {
            for (int i = 0; i < objects.length; i++) {
                add(objects[i]);
            }
        }

        public void add(E newObject) {
            list.add(newObject); // Append to the heap
            int currentIndex = list.size() - 1; // The index of the last node

            while (currentIndex > 0) {
                int parentIndex = (currentIndex - 1) / 2;
                // Swap if the current object is greater than its parent
                if (list.get(currentIndex).compareTo(
                        list.get(parentIndex)) > 0) {
                    E temp = list.get(currentIndex);
                    list.set(currentIndex, list.get(parentIndex));
                    list.set(parentIndex, temp);
                } else {
                    break; // the tree is a heap now
                }
                currentIndex = parentIndex;
            }
        }

        public E remove() {
            if (list.size() == 0) {
                return null;
            }

            E removedObject = list.get(0);
            list.set(0, list.get(list.size() - 1));
            list.remove(list.size() - 1);

            int currentIndex = 0;
            while (currentIndex < list.size()) {
                int leftChildIndex = 2 * currentIndex + 1;
                int rightChildIndex = 2 * currentIndex + 2;

                // Find the maximum between two children
                if (leftChildIndex >= list.size()) {
                    break; // The tree is a heap
                }
                int maxIndex = leftChildIndex;
                if (rightChildIndex < list.size()) {
                    if (list.get(maxIndex).compareTo(
                            list.get(rightChildIndex)) < 0) {
                        maxIndex = rightChildIndex;
                    }
                }

                // Swap if the current node is less than the maximum
                if (list.get(currentIndex).compareTo(
                        list.get(maxIndex)) < 0) {
                    E temp = list.get(maxIndex);
                    list.set(maxIndex, list.get(currentIndex));
                    list.set(currentIndex, temp);
                    currentIndex = maxIndex;
                } else {
                    break; // The tree is a heap
                }
            }

            return removedObject;
        }

        public int getSize() {
            return list.size();
        }
    }

    public static <E extends Comparable<E>> void heapSort(E[] list) {
        Heap<E> heap = new Heap<>();

        for (int i = 0; i < list.length; i++) {
            heap.add(list[i]);
        }

        for (int i = list.length - 1; i >= 0; i--) {
            list[i] = heap.remove();
        }
    }

    /**
     * ** End Heap Sort ***
     */
    /**
     * ** Begin Selection Sort ***
     */
    public static void selectionSort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            // Find the minimum in the list[i..list.length-1]
            int currentMin = list[i];
            int currentMinIndex = i;

            for (int j = i + 1; j < list.length; j++) {
                if (currentMin > list[j]) {
                    currentMin = list[j];
                    currentMinIndex = j;
                }
            }

            // Swap list[i] with list[currentMinIndex] if necessary;
            if (currentMinIndex != i) {
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
            }
        }
    }

    /**
     * ** End Selection Sort ***
     */
    /**
     * ** Begin Insertion Sort ***
     */
    public static void insertionSort(int[] list) {
        for (int i = 1; i < list.length; i++) {

            int currentElement = list[i];
            int k;
            for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
                list[k + 1] = list[k];
            }

            // Insert the current element into list[k+1]
            list[k + 1] = currentElement;
        }
    }

    /**
     * ** End Insertion Sort ***
     */
    /**
     * ** Begin Bubble Sort ***
     */
    public static void bubbleSort(int[] list) {

        boolean needNextPass = true;

        for (int k = 1; k < list.length && needNextPass; k++) {
            // Array may be sorted and next pass not needed
            needNextPass = false;
            for (int i = 0; i < list.length - k; i++) {
                if (list[i] > list[i + 1]) {
                    // Swap list[i] with list[i + 1]
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;

                    needNextPass = true; // Next pass still needed
                }
            }
        }

    }

    /**
     * ** End Bubble Sort ***
     */
    public static void main(String[] args) {
        int m = 1000000, NUM = 1;
        int[] list = new int[m];
        int[] list2 = new int[m];
        Integer[] list3 = new Integer[m];

        System.out.println("This program tests how fast 7 different sorting methods can sort through 1 Million integers with 10 separate trials. \n"
                + "The 7 sorting methods: Quick Sort, Merge Sort, Radix Sort, Heap Sort, Insertion Sort, Selection Sort, and Bubble Sort. \n");
        for (int j = 0; j < 10; j++) {

            for (int i = 0; i < m; i++) {
                int r = (int) (Math.random() * 10000);
                list[i] = r;
                list2[i] = r;
                list3[i] = r;
            }

            System.out.println("Trial " + NUM++ + "\n");
            // Quick Sort
            long startTime = System.currentTimeMillis();
            quickSort(list);
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println("Quick Sort: " + totalTime + " milliseconds");
            System.arraycopy(list2, 0, list, 0, m);

            // Merge Sort
            long startTime1 = System.currentTimeMillis();
            mergeSort(list);
            long endTime1 = System.currentTimeMillis();
            long totalTime1 = endTime1 - startTime1;
            System.out.println("Merge Sort: " + totalTime1 + " milliseconds");
            System.arraycopy(list2, 0, list, 0, m);

            // Radix Sort
            long startTime2 = System.currentTimeMillis();
            radixSort(list);
            long endTime2 = System.currentTimeMillis();
            long totalTime2 = endTime2 - startTime2;
            System.out.println("Radix Sort: " + totalTime2 + " milliseconds");
            System.arraycopy(list2, 0, list, 0, m);

            // Radix Binary Sort
            long startTime7 = System.currentTimeMillis();
           // binarySort(list);
            long endTime7 = System.currentTimeMillis();
            long totalTime7 = endTime7 - startTime7;
            System.out.println("Radix Binary Sort: " + totalTime7 + " milliseconds");

            // Heap Sort
            long startTime3 = System.currentTimeMillis();
            heapSort(list3);
            long endTime3 = System.currentTimeMillis();
            long totalTime3 = endTime3 - startTime3;
            System.out.println("Heap Sort: " + totalTime3 + " milliseconds");
            System.arraycopy(list2, 0, list, 0, m);

            // Insertion Sort
            long startTime5 = System.currentTimeMillis();
            insertionSort(list);
            long endTime5 = System.currentTimeMillis();
            long totalTime5 = endTime5 - startTime5;
            System.out.println("Insertion Sort: " + totalTime5 + " milliseconds");
            System.arraycopy(list2, 0, list, 0, m);

            // Selection Sort
            long startTime4 = System.currentTimeMillis();
            selectionSort(list);
            long endTime4 = System.currentTimeMillis();
            long totalTime4 = endTime4 - startTime4;
            System.out.println("Selection Sort: " + totalTime4 + " milliseconds");
            System.arraycopy(list2, 0, list, 0, m);

            // Bubble Sort
            long startTime6 = System.currentTimeMillis();
            bubbleSort(list);
            long endTime6 = System.currentTimeMillis();
            long totalTime6 = endTime6 - startTime6;
            System.out.println("Bubble Sort: " + totalTime6 + " milliseconds \n");

        }

    }

}
