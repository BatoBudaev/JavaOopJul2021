package budaev.matrix_main;

import budaev.matrix.Matrix;
import ru.academits.budaev.vector.Vector;

public class MatrixMain {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 3);
        System.out.println("Матрица нулей " + matrix1);

        double[][] array1 = new double[2][];
        array1[0] = new double[]{5, 10};
        array1[1] = new double[]{6, 7};
        Matrix matrix2 = new Matrix(array1);
        System.out.println("Матрица 2 " + matrix2);

        double[] array2 = {1, 3, 7, 5, 10};
        Vector[] vectorsArray = new Vector[2];
        vectorsArray[0] = new Vector(5);
        vectorsArray[1] = new Vector(array2);
        Matrix matrix3 = new Matrix(vectorsArray);
        System.out.println("Матрица 3 " + matrix3);

        double[] array3 = {100, 32, 75, 5.5, 11};
        matrix3.setRow(0, new Vector(array3));
        System.out.println("Замена строки " + matrix3);

        System.out.println("Получение строки " + matrix2.getRow(1));
        System.out.println("Полечение столбца " + matrix2.getColumn(1));

        matrix2.transpose();
        System.out.println("Транспонирование матрицы = " + matrix2);

        matrix3.multiplyByScalar(2.5);
        System.out.println("Умножение матрицы на скаляр 2.5 " + matrix3);

        System.out.println("Определитель матрицы = " + matrix2.getDeterminant());

        Vector resultVector = matrix3.multiplyByVector(new Vector(array3));
        System.out.println("Умножение матрицы на вектор " + resultVector);

        Matrix matrix4 = new Matrix(2, 2);
        double[] array4 = {33, 10};
        matrix4.setRow(0, new Vector(array4));
        matrix4.add(matrix2);
        System.out.println("Сложение матриц " + matrix4);

        matrix2.subtract(matrix4);
        System.out.println("Вычитание матриц " + matrix2);

        System.out.println(matrix2);
        System.out.println(matrix4);
        Matrix matrix5 = Matrix.getSum(matrix2, matrix4);
        System.out.println("Сложение матриц статик методом " + matrix5);

        Matrix matrix6 = Matrix.getDifference(matrix2, matrix4);
        System.out.println("Вычитание матриц статик методом " + matrix6);

        Matrix matrix7 = Matrix.getMultiplication(matrix2, matrix4);
        System.out.println("Умножение матриц статик методом " + matrix7);
    }
}