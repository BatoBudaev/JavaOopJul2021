package budaev.vector;

import java.util.Arrays;

public class Vector {
    private double[] vector;

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

    public Vector(int n, double[] array) throws IllegalArgumentException {
        if (n <= 0) {
            throw new IllegalArgumentException("n <= 0");
        }

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

    public void add(double e) {
        double[] tempArray = Arrays.copyOf(vector, vector.length + 1);
        tempArray[vector.length] = e;
        vector = tempArray;
    }

    private void getEqualLengthVectors(Vector vector1, Vector vector2) {
        if (vector1.getSize() == vector2.getSize()) {
            return;
        }

        double[] array;

        if (vector1.getSize() > vector2.getSize()) {
            array = Arrays.copyOf(vector2.vector, vector1.getSize());
            vector2.vector = array;
        } else {
            array = Arrays.copyOf(vector1.vector, vector2.getSize());
            vector1.vector = array;
        }
    }

    public void addition(Vector otherVector) {
        if (vector.length != otherVector.getSize()) {
            getEqualLengthVectors(this, otherVector);
        }

        for (int i = 0; i < vector.length; i++) {
            vector[i] += otherVector.vector[i];
        }
    }

    public void subtraction(Vector otherVector) {
        if (vector.length != otherVector.getSize()) {
            getEqualLengthVectors(this, otherVector);
        }

        for (int i = 0; i < vector.length; i++) {
            vector[i] -= otherVector.vector[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < vector.length; i++) {
            vector[i] *= scalar;
        }
    }

    public void expand() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double e : vector) {
            sum += e * e;
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int index) {
        return vector[index];
    }

    public void setComponent(double value, int index) {
        vector[index] = value;
    }
}