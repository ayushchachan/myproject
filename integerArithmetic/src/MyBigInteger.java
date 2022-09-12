import java.util.Arrays;

/**
 * @author Ayush Chachan
 */
public class MyBigInteger {

    public static void main(String[] args) {
        String a = "2466666653656";
        String b = "5387682446367";

        MyBigInteger T = new MyBigInteger();
        String a_minus_b = T.subtract(a, b);
        System.out.println("a - b = " + a_minus_b);
    }

    public String karatsubaMultiply(String x, String y) {

        if (x.length() <= 5 && y.length() <= 5) {
            return Integer.parseInt(x) * Integer.parseInt(y) + "";
        }

        int n = Integer.max(x.length(), y.length());

        // now we have x, y < 10^n

        String x1 = x.substring(0, n / 2);
        String x0 = x.substring(n / 2, n);

        String y1 = y.substring(0, n / 2);
        String y0 = y.substring(n / 2, n);

        String z0 = karatsubaMultiply(x0, y0);
        String z2 = karatsubaMultiply(x1, y1);
        return null;
    }

    public String integerAddition(String x, String y) {
        int n = Math.max(x.length(), y.length());

        int[] x_plus_y = new int[n];

        for (int i = 0; i < x.length(); i++) {
            char c = x.charAt(x.length() - i - 1);
            x_plus_y[n - i - 1] = Character.getNumericValue(c);
        }


        for (int i = 0; i < y.length(); i++) {
            char c = y.charAt(y.length() - i - 1);
            x_plus_y[n - i - 1] += Character.getNumericValue(c);
        }

        for (int i = 0; i < n - 1; i++) {
            int z = x_plus_y[n - i - 1];
            if (z > 9) {
                x_plus_y[n - i - 1] = z % 10;
                x_plus_y[n - i - 2] += 1;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int j : x_plus_y) {
            sb.append(j);
        }
        return sb.toString();
    }

    /**
     * Returns x- y; assumes x > y
     */
    public String IntegerSubtraction(String x, String y) {
        int n = Math.max(x.length(), y.length());

        int[] x_plus_y = new int[n + 1];

        for (int i = 0; i < x.length(); i++) {
            char c = x.charAt(x.length() - i - 1);
            x_plus_y[n - i] = Character.getNumericValue(c);
        }
        System.out.println("array = " + Arrays.toString(x_plus_y));
        for (int i = 0; i < y.length(); i++) {
            char c = y.charAt(y.length() - i - 1);

            int yi = Character.getNumericValue(c);
            int xi = x_plus_y[n - i];

            if (xi >= yi) {
                x_plus_y[n - i] = xi - yi;
            } else {
                x_plus_y[n - i - 1] -= 1;
                x_plus_y[n - i] = Integer.parseInt("1" + xi) - yi;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int j : x_plus_y) {
            sb.append(j);
        }
        return sb.toString();
    }

    public String subtract(String x, String y) {
        return null;
    }
}
