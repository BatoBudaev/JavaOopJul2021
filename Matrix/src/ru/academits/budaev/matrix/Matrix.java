package ru.academits.budaev.matrix;

import ru.academits.budaev.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(Vector[] rows) {
        if (rows.length == 0) {
            throw new IllegalArgumentException("Размер массива векторов должен быть больше 0");
        }

        int maxVectorsSize = 0;

        for (Vector row : rows) {
            if (maxVectorsSize < row.getSize()) {
                maxVectorsSize = row.getSize();
            }
        }

        this.rows = new Vector[rows.length];

        for (int i = 0; i < rows.length; i++) {
            this.rows[i] = new Vector(maxVectorsSize);
            this.rows[i].add(rows[i]);
        }
    }

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк должно быть больше 0, а сейчас: " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов должно быть больше 0, а сейчас: " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        this.rows = new Vector[matrix.getRowsCount()];

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            this.rows[i] = matrix.getRow(i);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Количество строк должно быть больше 0, а сейчас: " + array.length);
        }

        int arrayMaxLength = array[0].length;

        for (double[] e : array) {
            arrayMaxLength = Math.max(arrayMaxLength, e.length);
        }

        if (arrayMaxLength == 0) {
            throw new IllegalArgumentException("Количество столбцов должно быть больше 0, а сейчас: " + arrayMaxLength);
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(arrayMaxLength, array[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{").append(Arrays.toString(rows)).append("}").deleteCharAt(1).deleteCharAt(sb.length() - 2);

        return sb.toString();
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    private void checkRowIndex(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " отсутствует. Допустимые значения [0-" + (rows.length - 1) + "]");
        }
    }

    private void checkColumnIndex(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Индекс " + index + " отсутствует. Допустимые значения [0-" + (getColumnsCount() - 1) + "]");
        }
    }

    public Vector getRow(int index) {
        checkRowIndex(index);

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector row) {
        checkRowIndex(index);

        int columnsCount = getColumnsCount();
        int rowSize = row.getSize();

        if (rowSize != columnsCount) {
            throw new IllegalArgumentException("Размер  вектора должен быть равен: " + columnsCount + ". Размер переданного вектора: " + rowSize);
        }

        rows[index] = new Vector(row);
    }

    public Vector getColumn(int index) {
        checkColumnIndex(index);

        int rowsCount = getRowsCount();
        Vector column = new Vector(rowsCount);

        for (int i = 0; i < rowsCount; i++) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    public void transpose() {
        Vector[] transposedRows = new Vector[getColumnsCount()];

        for (int i = 0; i < transposedRows.length; i++) {
            transposedRows[i] = getColumn(i);
        }

        rows = transposedRows;
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    private static Matrix getMinor(Matrix matrix, int index) {
        double[][] array = new double[matrix.getRowsCount() - 1][matrix.getRowsCount() - 1];

        int newIndex = 0;

        for (int i = 0; i < matrix.getRowsCount(); ) {
            if (i == index) {
                i++;
                continue;
            }

            for (int j = 1; j < matrix.getRowsCount(); j++) {
                array[newIndex][j - 1] = matrix.rows[i].getComponent(j);
            }

            newIndex++;
            i++;
        }

        return new Matrix(array);
    }

    public double getDeterminant() {
        int rowsCount = getRowsCount();
        int columnsCount = getColumnsCount();

        if (rowsCount != columnsCount) {
            throw new UnsupportedOperationException("Матрица не квадртаная");
        }

        if (rowsCount == 1) {
            return getColumn(0).getComponent(0);
        }

        double determinant = 0;

        for (int i = 0; i < rowsCount; i++) {
            determinant += Math.pow(-1, i) * getColumn(0).getComponent(i) * getMinor(this, i).getDeterminant();
        }

        return determinant;
    }

    public Vector multiplyByVector(Vector vector) {
        int columnsCount = getColumnsCount();
        int vectorSize = vector.getSize();

        if (columnsCount != vectorSize) {
            throw new IllegalArgumentException("Количество столбцов: " + columnsCount
                    + " не совпадает с размером вектора: " + vectorSize);
        }

        double[] result = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            result[i] = Vector.getScalarProduct(rows[i], vector);
        }

        return new Vector(result);
    }

    private void checkMatrixSizes(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Матрицы должны иметь одинаковые размеры. Размеры матриц " + getRowsCount() + "x" + getColumnsCount()
                    + " и " + matrix.getRowsCount() + "x" + matrix.getColumnsCount());
        }
    }

    public void add(Matrix matrix) {
        checkMatrixSizes(matrix);

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatrixSizes(matrix);

        for (int i = 0; i < matrix.getRowsCount(); i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        matrix1.checkMatrixSizes(matrix2);

        Matrix resultingMatrix = new Matrix(matrix1);
        resultingMatrix.add(matrix2);

        return resultingMatrix;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        matrix1.checkMatrixSizes(matrix2);

        Matrix resultingMatrix = new Matrix(matrix1);
        resultingMatrix.subtract(matrix2);

        return resultingMatrix;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы, должно быть равно количеству строк второй матрицы. "
                    + "Количество столбцов первой матрицы: " + matrix1.getColumnsCount() + ". Количество строк второй матрицы: " + matrix2.getRowsCount());
        }

        double[][] array = new double[matrix1.getRowsCount()][matrix2.getColumnsCount()];

        for (int i = 0; i < matrix1.getRowsCount(); i++) {
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                array[i][j] = Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j));
            }
        }

        return new Matrix(array);
    }
}