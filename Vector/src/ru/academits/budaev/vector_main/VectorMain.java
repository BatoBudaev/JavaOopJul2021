package ru.academits.budaev.vector_main;

import ru.academits.budaev.vector.Vector;

public class VectorMain {
    public static void main(String[] args) {
        Vector vector1 = new Vector(5);

        System.out.println("Вектор с 0 " + vector1);

        double[] array1 = {1, 3, 7};
        Vector vector2 = new Vector(array1);
        System.out.println(vector2);

        double[] array2 = {5, 4};
        Vector vector3 = new Vector(2, array2);

        Vector resultingVector = Vector.getSum(vector2, vector3);
        vector2.add(vector3);

        System.out.println("Сложение векторов " + resultingVector);
        System.out.println("Сложение векторов статик методом " + vector2);

        System.out.println("Сравнение векторов " + vector2.equals(resultingVector));
        System.out.println("Хэш-код " + vector2.hashCode());

        vector2.reverse();
        System.out.println("Разворот вектора " + vector2);
        Vector resultingVector2 = Vector.getDifference(vector2, vector3);

        System.out.println("Вычитание векторов " + resultingVector2);

        resultingVector2.setComponent(0, 100);

        int index = 0;
        System.out.println("Получение компонеты по индексу " + index + " = " + resultingVector2.getComponent(index));
        System.out.println(resultingVector2);
        System.out.println("Получение длины вектора " + resultingVector2.getLength());

        double scalarProduct = Vector.getScalarProduct(vector2, vector3);

        System.out.println("Скалярное произведение векторов " + scalarProduct);
    }
}