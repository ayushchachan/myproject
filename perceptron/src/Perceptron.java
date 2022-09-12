/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perceptron;

/**
 *
 * @author Ayush Chachan
 */
public class Perceptron {

    private double[] weights;
    private int n;                      // the number of inputs

    // Creates a perceptron with n inputs. It should create an array
    // of n weights and initialize them all to 0.
    public Perceptron(int n) {
        weights = new double[n];
        this.n = n;                 // length of feature vector
    }

    // Returns the number of inputs n.
    public int numberOfInputs() {
        return n;

    }

    // Returns the weighted sum of the weight vector and x. 
    public double weightedSum(double[] x) {
        if (x.length != n) {
            throw new IllegalArgumentException("Invalid feature argument! Invalid length");
        }
        double answer = 0;

        for (int i = 0; i < n; i++) {
            answer += x[i] * weights[i];
        }
        return answer;

    }

    // Predicts the label (+1 or -1) of input x. It returns +1
    // if the weighted sum is positive and -1 if it is negative (or zero).
    public int predict(double[] x) {
        double w = weightedSum(x);
        if (w > 0) {
            return 1;
        } else {
            return -1;
        }

    }

    // Trains this perceptron on the labeled (+1 or -1) input x.
    // The weights vector is updated accordingly.
    public void train(double[] x, int label) {
        if (label != 1 || label != -1) {
            throw new IllegalArgumentException("Invalid label argument!");
        }
        int estimate = predict(x);
        if (estimate > label) {
            for (int i = 0; i < n; i++) {
                weights[i] -= x[i];
            }
        } else if (estimate < label) {
            for (int i = 0; i < n; i++) {
                weights[i] += x[i];
            }
        }

    }

    // Returns a String representation of the weight vector, with the
    // individual weights separated by commas and enclosed in parentheses.
    // Example: (2.0, 1.0, -1.0, 5.0, 3.0)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("(");
        for (double w : this.weights) {
            s.append(w + ", ");
        }
        s.deleteCharAt(s.length() - 1);
        s.append(")");
        return s.toString();

    }

    // Tests this class by directly calling all instance methods.   
    public static void main(String[] args) {

    }
}
