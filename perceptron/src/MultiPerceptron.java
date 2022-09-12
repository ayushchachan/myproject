/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perceptron;

/**
 *
 * @author Ayush Chachan
 */
public class MultiPerceptron {

    private Perceptron[] all_perceptrons;
    private int n, m;

    // Creates a multi-perceptron object with m classes and n inputs.
    // It creates an array of m perceptrons, each with n inputs.
    public MultiPerceptron(int m, int n) {
        all_perceptrons = new Perceptron[m];

        for (int i = 0; i < m; i++) {
            all_perceptrons[i] = new Perceptron(n);
        }
    }

    // Returns the number of classes m.
    public int numberOfClasses() {
        return m;
    }

    // Returns the number of inputs n (length of the feature vector).
    public int numberOfInputs() {
        return n;
    }

    // Returns the predicted label (between 0 and m-1) for the given input.
    public int predictMulti(double[] x) {
        double max_weighted_sum = 0;
        int label = 0;

        for (int i = 0; i < m; i++) {
            double weighted_sum = all_perceptrons[i].weightedSum(x);
            if (weighted_sum > max_weighted_sum) {
                label = i;
                max_weighted_sum = weighted_sum;
            }
        }
        return label;
    }

    // Trains this multi-perceptron on the labeled (between 0 and m-1) input.
    public void trainMulti(double[] x, int label) {
        for (int i = 0; i < m; i++) {
            if (i == label) {
                all_perceptrons[i].train(x, 1);
            } else {
                all_perceptrons[i].train(x, -1);
            }
        }

    }

    // Returns a String representation of this MultiPerceptron, with
    // the string representations of the perceptrons separated by commas
    // and enclosed in parentheses.
    // Example with m = 2 and n = 3: ((2.0, 0.0, -2.0), (3.0, 4.0, 5.0))
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("(");
        for (Perceptron p : this.all_perceptrons) {
            s.append(p.toString() + ", ");
        }
        s.deleteCharAt(s.length() - 1);
        s.append(")");
        return s.toString();
    }

    // Tests this class by directly calling all instance methods.   
    public static void main(String[] args) {

    }
}
