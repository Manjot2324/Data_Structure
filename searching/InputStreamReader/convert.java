import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class convert {
    public static void readFile(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
        isr.close();
        fis.close();
    }

    public static void main(String[] args) {
        try {
            readFile("example.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
