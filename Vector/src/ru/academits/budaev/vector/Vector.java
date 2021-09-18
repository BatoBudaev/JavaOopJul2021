package ru.academits.budaev.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0. Размерность = " + size);
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        this(vector.components);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Длина массива должна быть больше 0");
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int size, double[] array) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0. Размерность = " + size);
        }

        components = Arrays.copyOf(array, size);
    }

    public int getSize() {
        return components.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append(Arrays.toString(components)).append("}").deleteCharAt(1).deleteCharAt(sb.length() - 2);

        return sb.toString();
    }

    private static void increaseSize(Vector vector1, Vector vector2) {
        if (vector1.getSize() < vector2.components.length) {
            vector1.components = Arrays.copyOf(vector1.components, vector2.getSize());
        }
    }

    public void add(Vector vector) {
        increaseSize(this, vector);

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        increaseSize(this, vector);

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double e : components) {
            sum += e * e;
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " отсутствует. Границы вектора [0-" + (components.length - 1) + "]");
        }

        return components[index];
    }

    public void setComponent(int index, double value) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " отсутствует. Границы вектора [0-" + (components.length - 1) + "]");
        }

        components[index] = value;
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

        return Arrays.equals(components, v.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector resultingVector = new Vector(vector1);

        resultingVector.add(vector2);

        return resultingVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector resultingVector = new Vector(vector1);

        resultingVector.subtract(vector2);

        return resultingVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double scalarProduct = 0;
        int minVectorSize = Math.min(vector1.getSize(), vector2.getSize());

        for (int i = 0; i < minVectorSize; i++) {
            scalarProduct += vector1.components[i] * vector2.components[i];
        }

        return scalarProduct;
    }
}