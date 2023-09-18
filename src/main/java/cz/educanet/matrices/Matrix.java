package cz.educanet.matrices;

import java.util.Arrays;

public class Matrix implements IMatrix {

    private final double[][] rawArray;

    public Matrix(double[][] rawArray) {
        this.rawArray = rawArray;
    }

    /**
     * TODO: Implement
     */
    @Override
    public IMatrix times(IMatrix matrix) {
        Matrix first = new Matrix(rawArray);
        Matrix second = (Matrix) matrix;
        double[][] result = new double[first.getRows()][second.getColumns()];
        if (first.getColumns() != second.getRows())
            throw new IllegalArgumentException();
// https://www.javatpoint.com/java-program-to-multiply-two-matrices#:~:text=We%20can%20multiply%20two%20matrices,add%2C%20subtract%20and%20multiply%20matrices.
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                for (int k = 0; k < first.getRows(); k++) {
                    result [i][j] += (first.get(i, k) * second.get(k, j));
                }
            }
        }
        return new Matrix(result);
    }

    @Override
    public IMatrix times(Number scalar) {
        double[][] a = rawArray;
        int b = (int) scalar;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                a[i][j] *= b;
            }
        }
        return new Matrix(a);
    }

    /**
     * TODO: Implement
     */
    @Override
    public IMatrix add(IMatrix matrix) {

        double[][] addMatrix = rawArray;
        for (int i = 0; i < addMatrix.length; i++)
            for (int j = 0; j < addMatrix.length; j++)
                addMatrix[i][j] += matrix.get(i, j);
        return new Matrix(addMatrix);


    }

    /**
     * TODO: Implement
     */
    @Override
    public IMatrix transpose() {
        double[][] transpose = new double[this.getColumns()][this.getRows()];
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                transpose[j][i] = this.rawArray[i][j];
            }
        }
        return MatrixFactory.instance.create(transpose);
    }

    @Override
    public double determinant() {
        return 0;
    }

    /**
     * TODO: Implement
     */
    @Override
    public boolean isSquare() {
        return rawArray.length == rawArray[0].length;
    }

    @Override
    public boolean isDiagonal() {
        if (!this.isSquare()) return false;
        double[][] matrixDiagonal = new double[rawArray.length][rawArray[0].length];
        for (int i = 0; i < rawArray.length; i++)
            matrixDiagonal[i][i] = rawArray[i][i];
        return Arrays.deepEquals(matrixDiagonal, rawArray);
    }

    @Override
    public Number getTrace() {
        if (!this.isSquare()) {
            throw new UnsupportedOperationException();
        }
        double trace = 0;
        for (int i = 0; i < this.getRows(); i++) {
            trace += this.rawArray[i][i];
        }

        return trace;
    }


    @Override
    public int getRows() {
        return rawArray.length;
    }

    @Override
    public int getColumns() {
        if (getRows() > 0)
            return rawArray[0].length;

        return 0;
    }

    @Override
    public double get(int n, int m) {
        if(n >= getRows() || n < 0 || m >= getColumns() || m < 0)
            throw new IllegalArgumentException();

        return rawArray[n][m];
    }
}
