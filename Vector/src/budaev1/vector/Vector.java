package budaev1.vector;

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

    private static void getEqualLengthVectors(Vector vector1, Vector vector2) {
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
        Vector tempVector = new Vector(otherVector);

        if (vector.length != tempVector.getSize()) {
            getEqualLengthVectors(this, tempVector);
        }

        for (int i = 0; i < vector.length; i++) {
            vector[i] += tempVector.vector[i];
        }
    }

    public void subtract(Vector otherVector) {
        Vector tempVector = new Vector(otherVector);

        if (vector.length != tempVector.getSize()) {
            getEqualLengthVectors(this, tempVector);
        }

        for (int i = 0; i < vector.length; i++) {
            vector[i] -= tempVector.vector[i];
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

    public double getComponent(int index) throws IllegalArgumentException {
        if (index < 0 || index > this.getSize() - 1) {
            throw new IllegalArgumentException("Нет такого индекса");
        }

        return vector[index];
    }

    public void setComponent(double value, int index) throws IllegalArgumentException {
        if (index < 0 || index > this.getSize() - 1) {
            throw new IllegalArgumentException("Нет такого индекса");
        }

        vector[index] = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Vector v = (Vector) o;

        return getSize() == v.getSize() && Arrays.equals(vector, v.vector);
    }

    @Override
    public int hashCode() {
        final int prime = 37;

        return prime + Arrays.hashCode(vector);
    }

    public static Vector additionVectors(Vector vector1, Vector vector2) {
        Vector tempVector1 = new Vector(vector1);
        Vector tempVector2 = new Vector(vector2);

        tempVector1.addition(tempVector2);

        return tempVector1;
    }

    public static Vector subtractVectors(Vector vector1, Vector vector2) {
        Vector tempVector1 = new Vector(vector1);
        Vector tempVector2 = new Vector(vector2);

        tempVector1.subtract(tempVector2);

        return tempVector1;
    }

    public static double scalarProduct(Vector vector1, Vector vector2) {
        Vector tempVector1 = new Vector(vector1);
        Vector tempVector2 = new Vector(vector2);

        if (tempVector1.getSize() != tempVector2.getSize()) {
            getEqualLengthVectors(tempVector1, tempVector2);
        }

        double scalarSum = 0;

        for (int i = 0; i < tempVector1.getSize(); i++) {
            scalarSum += tempVector1.vector[i] * tempVector2.vector[i];
        }

        return scalarSum;
    }
}