import java.util.*;

class BubbleSort {
    public void sort(int[] marks) {
        int n = marks.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (marks[j] > marks[j + 1]) {
                    int temp = marks[j];
                    marks[j] = marks[j + 1];
                    marks[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        BubbleSort sorter = new BubbleSort();
        int[] marks = {88, 92, 75, 62, 80, 95, 70};
        
        System.out.println("Before Sorting:");
        for (int mark : marks) {
            System.out.print(mark + " ");
        }

        sorter.sort(marks);

        System.out.println("\nAfter Sorting:");
        for (int mark : marks) {
            System.out.print(mark + " ");
        }
    }
}
