package budaev.matrix;

import ru.academits.budaev.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(Vector[] rows) {
        this.rows = rows;
    }

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк должно быть больше 0: "
                    + rowsCount);
        } else if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов должно быть больше 0: "
                    + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.rows);
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Количество строк должно быть больше 0: " + array.length);
        }

        int arrayMaxLength = array[0].length;

        for (double[] e : array) {
            arrayMaxLength = Math.max(arrayMaxLength, e.length);
        }

        if (arrayMaxLength == 0) {
            throw new IllegalArgumentException("Количество столбцов должно быть больше 0: " + arrayMaxLength);
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
        if (index < 0 || index >= rows[0].getSize()) {
            throw new IndexOutOfBoundsException("Индекс " + index + " отсутствует. Допустимые значения [0-" + (rows[0].getSize() - 1) + "]");
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
            throw new IllegalArgumentException("Размер  вектора должен быть равен: " + columnsCount);
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
        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].multiplyByScalar(scalar);
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
                array[newIndex][j - 1] = matrix.getRow(i).getComponent(j);
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
            throw new IllegalArgumentException("Матрица не квадртаная");
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
            throw new IllegalArgumentException("Количество столбцов: "
                    + columnsCount + " не совпадает с размером вектора: " + vectorSize);
        }

        Vector resultVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            double componentValue = 0;

            for (int j = 0; j < columnsCount; j++) {
                componentValue += rows[i].getComponent(j) * vector.getComponent(j);
            }

            resultVector.setComponent(i, componentValue);
        }

        return resultVector;
    }

    private void checkMatrixSizes(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Матрицы должны иметь одинаковые размеры.");
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
    //TODO статические методы
}