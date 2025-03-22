import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;

public class read {
    public static void writeToFile(String filePath) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        FileWriter writer = new FileWriter(filePath, true);
        String input;
        
        while (true) {
            input = br.readLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            writer.write(input + System.lineSeparator());
        }
        
        writer.close();
        br.close();
        isr.close();
    }

    public static void main(String[] args) {
        try {
            writeToFile("output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
