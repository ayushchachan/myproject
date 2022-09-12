/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perceptron;

import edu.princeton.cs.algs4.Picture;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayush Chachan
 */
public class ImageClassifier {

    static String path = "test/perceptron/";

    // Creates a feature vector (1D array) from the given picture.
    public static double[] extractFeatures(Picture p1) {
        double[] vector = new double[p1.width() * p1.height()];

        for (int i = 0; i < p1.width(); i++) {
            for (int j = 0; j < p1.height(); j++) {
                int y = p1.get(j, i).getRed();          // since image is grayscale
                vector[i * p1.height() + j] = y;
            }
        }
        return vector;
    }

    // See below.
    public static void main(String[] args) {
        String training_file = args[0];
        String test_file = args[1];
        MultiPerceptron my_perceptron = read_training_file(training_file);
        double error_rate = read_test_file(my_perceptron, test_file);
        System.out.println("error rate = " + error_rate);
        // System.out.println(java.util.Arrays.toString(extractFeatures(pic)));       
    }

    public static MultiPerceptron read_training_file(String filename) {
        MultiPerceptron h = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));

            int m = Integer.parseInt(br.readLine());

            String[] dimensions = br.readLine().split(" ");

            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);

            h = new MultiPerceptron(m, width * height);

            String l = br.readLine();
            int z = 1;
            while (l != null) {
                //System.out.println("l = " + l);
                String[] A = l.split("\\s+");
                String img_filename = A[0];
                int int_label = Integer.parseInt(A[1]);
                Picture pic = new Picture(img_filename);
                System.out.println("z = " + z++);

                double[] features = extractFeatures(pic);
                h.trainMulti(features, int_label);
                l = br.readLine();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return h;
    }

    public static double read_test_file(MultiPerceptron h, String filename) {
        BufferedReader br = null;
        double error_rate = 1;

        try {
            br = new BufferedReader(new FileReader(filename));

            int m = Integer.parseInt(br.readLine());

            String[] dimensions = br.readLine().split(" ");

            int width = Integer.parseInt(dimensions[0]);
            int height = Integer.parseInt(dimensions[1]);

            String l = br.readLine();

            int total_test = 0;
            double error = 0;
            int z = 1;
            while (l != null) {
                String[] A = l.split("\\s+");
                String img_filename = A[0];
                int true_label = Integer.parseInt(A[1]);
                Picture pic = new Picture(img_filename);

                double[] features = extractFeatures(pic);
                int predict_label = h.predictMulti(features);
                System.out.println("z = " + z++);
                if (true_label != predict_label) {
                    error += 1;
                }
                total_test += 1;
                l = br.readLine();
            }
            error_rate = error / total_test;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return error_rate;
    }

    private static void printPictureAsArray(Picture p1) {
        String[][] pixelArray = new String[p1.height()][p1.width()];

        for (int i = 0; i < p1.width(); i++) {
            for (int j = 0; j < p1.height(); j++) {
                Color c = p1.get(j, i);
                pixelArray[i][j] = "(" + c.getRed() + ", " + c.getGreen() + ", "
                        + c.getBlue() + ")";
            }
        }
        for (String[] A : pixelArray) {
            System.out.println(java.util.Arrays.toString(A));
        }
    }
}
