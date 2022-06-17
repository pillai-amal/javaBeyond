import java.lang.Exception;
import java.io.*;

public class IOStreamDemo {
    public static void main(String[] args) throws Exception {
        try (
            FileOutputStream outFile = new FileOutputStream("outFile.txt");
            FileInputStream inFile = new FileInputStream("inFile.txt");

            Reader readFile = new FileReader("readFrom.txt");
            Writer writeFile = new FileWriter("writeTo.txt");
        ) {
            int value = inFile.read();
            while (value !=-1) {
                System.out.println("value=" + value);
                outFile.write(value);
                value = inFile.read();
            }

            int value1 = readFile.read();
            while (value1 != -1) {
                writeFile.write(value1);
                value1 = readFile.read();
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}