import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Ayush Chachan
 */
public class LZWDecompress {
    static Scanner inputStream;
    static FileWriter outputStream;
    static StringBuilder fileText = new StringBuilder();
    static HashMap<Integer, String> dict;
    private static int i = 0;

    public static void main(String[] args) {
        init();

        try {
            inputStream = new Scanner(new FileReader("compress.txt"));
            outputStream = new FileWriter("Decompress.txt");

            String P = dict.get(Integer.parseInt(inputStream.next()));
            String Q;
            while (inputStream.hasNext()) {
                int j = Integer.parseInt(inputStream.next());
                System.out.println("P = " + P);

                fileText.append(P);
                outputStream.write(P);
                System.out.println("text = " + P);


                if (dict.containsKey(j)) {
                    Q = dict.get(j);
                    dict.put(i++, P + Q.charAt(0));
                    System.out.println("dict.put(" + (i - 1) + ", " + P + Q.charAt(0) + ")");

                } else {
                    dict.put(i++, P + P.charAt(0));
                    Q = dict.get(j);
                    System.out.println("dict.put(" + (i - 1) + ", " + P + P.charAt(0) + ")");

                }

                P = Q;
            }
            fileText.append(P);
            System.out.println("fileText = " + fileText.length());
            outputStream.write(P);

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex2) {
            ex2.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * initialize the dictionary
     */
    public static void init() {
        dict = new HashMap<>();

        for (int c = 1; c < 127; c++) {
            dict.put(i++, Character.toString((char) c));
            //System.out.println(i + ": " + c);
        }
        dict.put(i++, "\n");
        dict.put(i++, "");
        ;
        System.out.println(dict);
    }

}
