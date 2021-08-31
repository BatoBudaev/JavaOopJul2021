package ru.academits.budaev.vector;

import java.util.Arrays;

public class Vector {
    private double[] componentsArray;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0. Размерность = " + size);
        }

        componentsArray = new double[size];
    }

    public Vector(Vector v) {
        this(v.componentsArray);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Пустой массив");
        }

        componentsArray = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0. Размерность = " + size);
        }

        componentsArray = Arrays.copyOf(array, size);
    }

    public int getSize() {
        return componentsArray.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append(Arrays.toString(componentsArray)).append("}").deleteCharAt(1).deleteCharAt(sb.length() - 2);

        return sb.toString();
    }

    private static void increaseSize(Vector vector1, Vector vector2) {
        if (vector1.getSize() >= vector2.getSize()) {
            return;
        }
        vector1.componentsArray = Arrays.copyOf(vector1.componentsArray, vector2.getSize());
    }

    public void add(Vector vector) {
        increaseSize(this, vector);

        for (int i = 0; i < vector.componentsArray.length; i++) {
            componentsArray[i] += vector.componentsArray[i];
        }
    }

    public void subtract(Vector vector) {
        increaseSize(this, vector);

        for (int i = 0; i < vector.componentsArray.length; i++) {
            componentsArray[i] -= vector.componentsArray[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < componentsArray.length; i++) {
            componentsArray[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double e : componentsArray) {
            sum += e * e;
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Индекс " + index + " отсутствует. Границы вектора [0-" + getSize() + "]");
        }

        return componentsArray[index];
    }

    public void setComponent(int index, double value) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Индекс " + index + " отсутствует. Границы вектора [0-" + getSize() + "]");
        }

        componentsArray[index] = value;
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

        return Arrays.equals(componentsArray, v.componentsArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(componentsArray);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector tempVector = new Vector(vector1);

        tempVector.add(vector2);

        return tempVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector tempVector = new Vector(vector1);

        tempVector.subtract(vector2);

        return tempVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double scalarProduct = 0;
        int minVectorSize = Math.min(vector1.getSize(), vector2.getSize());

        for (int i = 0; i < minVectorSize; i++) {
            scalarProduct += vector1.componentsArray[i] * vector2.componentsArray[i];
        }

        return scalarProduct;
    }
}