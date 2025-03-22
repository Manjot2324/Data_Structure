import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;

public class compare {

    public static void main(String[] args) throws IOException {
        compareStringConcatenation();
        compareFileReading();
    }

    public static void compareStringConcatenation() {
        String str = "hello";
        int numIterations = 1000000;

        long startTime = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numIterations; i++) {
            sb.append(str);
        }
        long endTime = System.nanoTime();
        long stringBuilderTime = endTime - startTime;
        System.out.println("StringBuilder time: " + stringBuilderTime + " nanoseconds");

        startTime = System.nanoTime();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < numIterations; i++) {
            sf.append(str);
        }
        endTime = System.nanoTime();
        long stringBufferTime = endTime - startTime;
        System.out.println("StringBuffer time: " + stringBufferTime + " nanoseconds");
    }

    public static void compareFileReading() throws IOException {
        String filePath = "largefile.txt";

        long startTime = System.nanoTime();
        int fileReaderWordCount = countWordsWithFileReader(filePath);
        long endTime = System.nanoTime();
        long fileReaderTime = endTime - startTime;
        System.out.println("FileReader word count: " + fileReaderWordCount);
        System.out.println("FileReader time: " + fileReaderTime + " nanoseconds");

        startTime = System.nanoTime();
        int inputStreamReaderWordCount = countWordsWithInputStreamReader(filePath);
        endTime = System.nanoTime();
        long inputStreamReaderTime = endTime - startTime;
        System.out.println("InputStreamReader word count: " + inputStreamReaderWordCount);
        System.out.println("InputStreamReader time: " + inputStreamReaderTime + " nanoseconds");
    }

    public static int countWordsWithFileReader(String filePath) throws IOException {
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            count += line.split("\\s+").length;
        }
        br.close();
        fr.close();
        return count;
    }

    public static int countWordsWithInputStreamReader(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line;
        int count = 0;
        while ((line = br.readLine()) != null) {
            count += line.split("\\s+").length;
        }
        br.close();
        isr.close();
        fis.close();
        return count;
    }
}
