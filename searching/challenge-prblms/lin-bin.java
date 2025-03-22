import java.util.*;

public class lin-bin {

    public static int findFirstMissingPositive(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            if (arr[i] <= 0 || arr[i] > n) {
                arr[i] = n + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            int num = Math.abs(arr[i]);
            if (num <= n && arr[num - 1] > 0) {
                arr[num - 1] = -arr[num - 1];
            }
        }


        for (int i = 0; i < n; i++) {
            if (arr[i] > 0) {
                return i + 1;
            }
        }

        return n + 1;
    }

    public static int binarySearch(int[] arr, int target) {
        Arrays.sort(arr); 
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 4, -1, 1};
        System.out.println("First missing positive: " + findFirstMissingPositive(arr1));

        int[] arr2 = {5, 3, 8, 6, 2};
        int target = 6;
        System.out.println("Index of target " + target + ": " + binarySearch(arr2, target));
    }
}
