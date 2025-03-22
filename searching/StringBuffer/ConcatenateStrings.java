import java.util.*;

public class ConcatenateStrings {
    public static String concatenateStrings(String[] inputArray) {
        StringBuffer sb = new StringBuffer();
        for (String str : inputArray) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String[] strings = {"hello", " ", "world", "!"};
        System.out.println(concatenateStrings(strings));
    }
}
