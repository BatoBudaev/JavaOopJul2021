package ru.academits.budaev.vector_main;

import ru.academits.budaev.vector.Vector;

public class VectorMain {
    public static void main(String[] args) {
        Vector vector = new Vector(5);

        System.out.println("Вектор с 0 " + vector);

        double[] arr = {1, 3, 7};
        Vector vector2 = new Vector(arr);
        System.out.println(vector2);

        double[] arr2 = {5, 4};
        Vector vector3 = new Vector(2, arr2);

        Vector newVector = Vector.getAddition(vector2, vector3);
        vector2.add(vector3);

        System.out.println("Сложение векторов " + newVector);
        System.out.println("Сложение векторов статик методом " + vector2);

        System.out.println("Сравнение векторов " + vector2.equals(newVector));
        System.out.println("Хэш-код " + vector2.hashCode());

        vector2.reverse();

        Vector newVector2 = Vector.getSubtraction(vector2, vector3);

        System.out.println("Вычитание векторов " + newVector2);

        newVector2.setComponent(0, 100);

        System.out.println("Получение компонеты по индексу " + newVector2.getComponent(0));
        System.out.println(newVector2);
        System.out.println("Получение длины вектора " + newVector2.getLength());

        double scalarProduct = Vector.getScalarProduct(vector2, vector3);

        System.out.println("Скалярное произведение векторов " + scalarProduct);
    }
}