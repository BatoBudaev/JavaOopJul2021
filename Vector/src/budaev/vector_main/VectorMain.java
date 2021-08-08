package budaev.vector_main;

import budaev.vector.Vector;

public class VectorMain {
    public static void main(String[] args) {
        Vector vector = new Vector(5);

        double[] arr = {1.2, 3};

        Vector vector2 = new Vector(arr);

        Vector vector3 = new Vector(10,arr);
        System.out.println(vector3);


    }
}
