import java.util.*;

public class ReverseString {
    public static String reverseString(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseString("hello"));
    }
}
