import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Count {
    public static int countWordOccurrence(String filePath, String targetWord) throws IOException {
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            String[] words = line.split("\\s+");
            for (String word : words) {
                if (word.equalsIgnoreCase(targetWord)) {
                    count++;
                }
            }
        }
        br.close();
        fr.close();
        return count;
    }

    public static void main(String[] args) {
        try {
            int count = countWordOccurrence("example.txt", "hello");
            System.out.println("The word 'hello' appears " + count + " times.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
