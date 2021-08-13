package budaev1.vector_main;

import budaev1.vector.*;

public class VectorMain {
    public static void main(String[] args) {
        Vector vector = new Vector(5);

        System.out.println("Вектор с 0 " + vector);

        double[] arr = {1, 3, 7};
        Vector vector2 = new Vector(arr);

        double[] arr2 = {5, 4};
        Vector vector3 = new Vector(2, arr2);

        Vector newVector = Vector.additionVectors(vector2, vector3);
        vector2.addition(vector3);

        System.out.println("Сложение векторов " + newVector);
        System.out.println("Сложение векторов статик методом " + vector2);

        System.out.println("Сравнение векторов " + vector2.equals(newVector));
        System.out.println("Хэш-код " + vector2.hashCode());

        vector2.expand();

        Vector newVector2 = Vector.subtractVectors(vector2, vector3);

        System.out.println("Вычитание векторов " + newVector2);

        newVector2.setComponent(100, 0);
        newVector2.add(333);

        System.out.println("Получение компонеты по индексу " + newVector2.getComponent(0));
        System.out.println(newVector2);
        System.out.println("Получение длины вектора " + newVector2.getLength());

        double scalarProduct = Vector.scalarProduct(vector2, vector3);

        System.out.println("Скалярное произведение векторов " + scalarProduct);
    }
}