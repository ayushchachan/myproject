/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perceptron;

import edu.princeton.cs.algs4.Picture;

import java.awt.*;

/**
 * @author Ayush Chachan
 */
public class Grayscale {

    public Picture getGrayScale(Picture p1) {

        Picture p2 = new Picture(p1.width(), p1.height());
        for (int i = 0; i < p1.height(); i++) {
            for (int j = 0; j < p1.width(); j++) {

                Color currentPixel = p1.get(j, i);
                Color gray = Luminance.toGray(currentPixel);

                p2.set(j, i, gray);
            }
        }

        return p2;
    }
}
