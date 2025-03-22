import java.util.*;

public class Compare {
    public static void main(String[] args) {
        int numStrings = 1000000;
        String str = "hello";

        StringBuffer sb = new StringBuffer();
        long startTime = System.nanoTime();
        for (int i = 0; i < numStrings; i++) {
            sb.append(str);
        }
        long endTime = System.nanoTime();
        long stringBufferTime = endTime - startTime;
        System.out.println("StringBuffer time: " + stringBufferTime + " nanoseconds");

        StringBuilder sbld = new StringBuilder();
        startTime = System.nanoTime();
        for (int i = 0; i < numStrings; i++) {
            sbld.append(str);
        }
        endTime = System.nanoTime();
        long stringBuilderTime = endTime - startTime;
        System.out.println("StringBuilder time: " + stringBuilderTime + " nanoseconds");
    }
}
