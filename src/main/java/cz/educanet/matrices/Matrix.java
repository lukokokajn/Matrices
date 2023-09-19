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
        if (getColumns() != matrix.getRows())
            throw new IllegalArgumentException();

        double[][] result = new double[getRows()][matrix.getColumns()];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                for (int k = 0; k < matrix.getRows(); k++) {
                    result[i][j] += (get(i, k) * matrix.get(k, j));
                }
            }
        }

        return new Matrix(result);
    }

    @Override
    public IMatrix times(Number scalar) {
        double[][] result = new double[getRows()][getColumns()];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = scalar.doubleValue() * rawArray[i][j];
            }
        }
        return new Matrix(result);
    }

    /**
     * TODO: Implement
     */
    @Override
    public IMatrix add(IMatrix matrix) {

        double[][] sumResult = new double[getRows()][getColumns()];
        for (int i = 0; i < rawArray.length; i++) {
            for (int j = 0; j < rawArray[i].length; j++) {
                sumResult[i][j] = rawArray[i][j] + matrix.get(i, j);
            }
        }
        return new Matrix(sumResult);
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
