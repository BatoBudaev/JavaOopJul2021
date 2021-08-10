package budaev.vector_main;

import budaev.vector.Vector;

public class VectorMain {
    public static void main(String[] args) {
        Vector vector = new Vector(5);

        double[] arr = {1, 3, 7};

        Vector vector2 = new Vector(arr);

        double[] arr2 = {5, 4};
        Vector vector3 = new Vector(2, arr2);

        vector2.addition(vector3);

        vector2.add(10);
        vector2.expand();

        System.out.println(vector2);
    }
}