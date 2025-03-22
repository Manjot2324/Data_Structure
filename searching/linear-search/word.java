import java.util.*;

public class word {
    public static String findSentenceWithWord(String[] sentences, String word) {
        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                return sentence;
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        String[] sentences = {"This is a test.", "Hello world!", "Java programming is fun."};
        String word = "Java";
        System.out.println(findSentenceWithWord(sentences, word));
    }
}
