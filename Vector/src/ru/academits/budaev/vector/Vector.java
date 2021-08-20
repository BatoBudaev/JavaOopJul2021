package ru.academits.budaev.vector;

import java.util.Arrays;

public class Vector {
    private double[] vectorArray;

    public Vector(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Размерность вектора: " + dimension);
        }

        vectorArray = new double[dimension];
    }

    public Vector(Vector v) {
        this(v.vectorArray);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Пустой массив");
        }

        vectorArray = Arrays.copyOf(array, array.length);
    }

    public Vector(int dimension, double[] array) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Размерность вектора: " + dimension);
        }

        vectorArray = Arrays.copyOf(array, dimension);
    }

    public int getSize() {
        return vectorArray.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ").append(Arrays.toString(vectorArray)).append(" }").deleteCharAt(2).deleteCharAt(sb.length() - 3);

        return sb.toString();
    }

    private static void doEqualLengthVectors(Vector vector1, Vector vector2) {
        if (vector1.getSize() == vector2.getSize()) {
            return;
        }

        double[] array;

        if (vector1.getSize() > vector2.getSize()) {
            array = Arrays.copyOf(vector2.vectorArray, vector1.getSize());
            vector2.vectorArray = array;
        } else {
            array = Arrays.copyOf(vector1.vectorArray, vector2.getSize());
            vector1.vectorArray = array;
        }
    }

    public void add(Vector otherVector) {
        double[] tempArray = otherVector.vectorArray;

        if (vectorArray.length != otherVector.getSize()) {
            doEqualLengthVectors(this, otherVector);
        }

        for (int i = 0; i < vectorArray.length; i++) {
            vectorArray[i] += otherVector.vectorArray[i];
        }

        otherVector.vectorArray = tempArray;
    }

    public void subtract(Vector otherVector) {
        double[] tempArray = otherVector.vectorArray;

        if (vectorArray.length != otherVector.getSize()) {
            doEqualLengthVectors(this, otherVector);
        }

        for (int i = 0; i < vectorArray.length; i++) {
            vectorArray[i] -= otherVector.vectorArray[i];
        }

        otherVector.vectorArray = tempArray;
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < vectorArray.length; i++) {
            vectorArray[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double e : vectorArray) {
            sum += e * e;
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Нет такого индекса");
        }

        return vectorArray[index];
    }

    public void setComponent(int index, double value) {
        if (index < 0 || index > this.getSize() - 1) {
            throw new IndexOutOfBoundsException("Нет такого индекса");
        }

        vectorArray[index] = value;
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

        return Arrays.equals(vectorArray, v.vectorArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vectorArray);
    }

    public static Vector getAddition(Vector vector1, Vector vector2) {
        Vector tempVector = new Vector(vector1);

        tempVector.add(vector2);

        return tempVector;
    }

    public static Vector getSubtraction(Vector vector1, Vector vector2) {
        Vector tempVector = new Vector(vector1);

        tempVector.subtract(vector2);

        return tempVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double scalarProduct = 0;
        int minLengthVector = Math.min(vector1.getSize(), vector2.getSize());

        for (int i = 0; i < minLengthVector; i++) {
            scalarProduct += vector1.vectorArray[i] * vector2.vectorArray[i];
        }

        return scalarProduct;
    }
}