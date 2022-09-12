import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ayush Chachan
 */
public class LZWCompress {

    static FileReader inputStream;
    static FileWriter outputStream;

    static HashMap<String, Integer> dict;

    public static void main(String[] args) {
        try {
            inputStream = new FileReader("test_case.txt");
            outputStream = new FileWriter("compress.txt");

            StringBuilder input_file = new StringBuilder();
            int letter;

            while ((letter = inputStream.read()) != -1) {       // while P is not
                // last character do
                input_file.append((char) letter);
            }

            List<Integer> encrypt = compress(input_file.toString());
            for (int i : encrypt) {
                System.out.println(i);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
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

    public static List<Integer> compress(String s_in) {
        init();
        int i = 256;

        LinkedList<Integer> answer = new LinkedList<>();

        int j = 0;
        String P = "" + s_in.charAt(j);
        String Q = "";

        while (j < s_in.length()) {
            Q = "" + s_in.charAt(j + 1);
            if (dict.containsKey(P + Q)) {
                P = P + Q;
            } else {
                answer.add(dict.get(P));
                dict.put(P + Q, i++);
                P = Q;
            }
        }

        return answer;
    }

    /**
     * initialize the dictionary
     */
    public static void init() {
        dict = new HashMap<>();

        for (int i = 0; i < 256; i++) {
            String ch = "" + (char) i;
            dict.put(ch, i);
            //System.out.println(Character.toString((char)i) + ": " + i);
        }
        System.out.println("initial dict = " + dict);
    }

}
