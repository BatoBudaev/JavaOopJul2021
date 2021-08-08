package budaev.vector;

import java.util.Arrays;
import java.util.LinkedList;

public class Vector {
    private final double[] vector;

    public Vector(int n) throws IllegalArgumentException {
        if (n <= 0) {
            throw new IllegalArgumentException("n <= 0");
        }

        vector = new double[n];
    }

    public Vector(Vector v) {
        this(v.vector);
    }

    public Vector(double[] array) {
        vector = new double[array.length];

        System.arraycopy(array, 0, vector, 0, array.length);
    }

    public Vector(int n, double[] array) {
        vector = new double[n];

        for (int i = 0; i < n; i++) {
            if (i < array.length) {
                vector[i] = array[i];
            } else {
                vector[i] = 0;
            }
        }

    }

    public int getSize() {
        return vector.length;
    }

    public String toString() {
        String line = Arrays.toString(vector);
        return "{ " + line.substring(1, line.length() - 1) + " }";
    }
}