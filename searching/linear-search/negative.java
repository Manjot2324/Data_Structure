import java.util.*;

public class negative {
    public static int findFirstNegativeNumber(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] numbers = {3, 5, -2, 7, 8};
        System.out.println(findFirstNegativeNumber(numbers));
    }
}
