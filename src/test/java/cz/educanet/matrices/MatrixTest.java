package cz.educanet.matrices;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    public void should_ReturnResult_When_matrix1_Times_matrix2_equals_expectResult_or_throw_nelzevypocitat() {
        IMatrix matrix1 = MatrixFactory.instance.create(new double[][]{
                {5, 3}, {-5, 1}, {2, -3}
        });
        IMatrix matrix2 = MatrixFactory.instance.create(new double[][]{
                {1, -2, 3, 1}, {2, -1, 0, 5}
        });

        IMatrix expectResult = MatrixFactory.instance.create(new double[][]{
                {11, -13, 15, 20},
                {-3, 9, -15, 0},
                {-4, -1, 6, -13}
        });

        if (matrix1.getColumns() != matrix2.getRows()) {
            throw new IllegalArgumentException("Nelze vypočítat");
        }

        IMatrix result = matrix1.times(matrix2);

        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                Assertions.assertEquals(expectResult.get(i, j), result.get(i, j));
            }
        }
    }


    @Test
    public void should_ReturnResult_When_testingMatrix_Times_scalar_equals_expectResult() {
        IMatrix testingMatrix = MatrixFactory.instance.create(new double[][]{
                {1, 2, 3},
                {4, 5, 6}
        });

        int scalar = 6;

        IMatrix expectResult = MatrixFactory.instance.create(new double[][]{
                {6, 12, 18},
                {24, 30, 36}
        });


        IMatrix result = testingMatrix.times(scalar);

        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                Assertions.assertEquals(expectResult.get(i, j), result.get(i, j));
            }
        }
    }

    @Test
    public void should_ReturnResult_When_matrix_Sum_sumMatrix_equals_expectResult() {
        IMatrix matrix = MatrixFactory.instance.create(new double[][]{
                {1, 2, 3},
                {4, 5, 6}
        });

        IMatrix sumMatrix = MatrixFactory.instance.create(new double[][]{
                {4, 5, 1},
                {2, 0, 8}
        });

        IMatrix expectResult = MatrixFactory.instance.create(new double[][]{
                {5, 7, 4},
                {6, 5, 14}
        });

        IMatrix result = matrix.add(sumMatrix);

        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                Assertions.assertEquals(expectResult.get(i, j), result.get(i, j));
            }
        }
    }

    @Test
    public void should_ReturnTransposed_when_matrix_is_Transposed() {
        IMatrix matrix = MatrixFactory.instance.create(new double[][]{
                {1, 5, 4},
                {8, 4, 1},
                {7, 2, 1}
        });
        IMatrix expectResult = MatrixFactory.instance.create(new double[][]{
                {1, 8, 7},
                {5, 4, 2},
                {4, 1, 1}
        });

        IMatrix result = matrix.transpose();

        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                Assertions.assertEquals(expectResult.get(i, j), result.get(i, j));
            }
        }
    }

    @Test
    public void should_ReturnTrue_When_MatrixIsSquare() {
        IMatrix testMatrix = MatrixFactory.instance.create(new double[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        });

        assertTrue(testMatrix.isSquare());
    }

    @Test
    public void should_ReturnFalse_When_MatrixIsNotSquare() {
        IMatrix testMatrix = MatrixFactory.instance.create(new double[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0}

        });

        assertFalse(testMatrix.isSquare());
    }

    @Test
    public void should_ReturnTrue_When_MatrixIsDiagonal() {
        IMatrix testMatrix = MatrixFactory.instance.create(new double[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        });

        assertTrue(testMatrix.isDiagonal());
    }


    @Test
    public void should_ReturnFalse_When_MatrixIsNotDiagonal() {
        IMatrix testMatrix = MatrixFactory.instance.create(new double[][]{
                {1, 0, 0},
                {0, 1, 1},
                {1, 1, 1}
        });

        assertFalse(testMatrix.isDiagonal());
    }

    @Test
    public void should_ReturnTrace_When_MatrixIsSquare() {
        IMatrix testMatrix = MatrixFactory.instance.create(new double[][]{
                {1, 3, 2},
                {3, 1, 4},
                {5, 10, 10}
        });

        double expectedResult = 12;

        double result = testMatrix.getTrace().intValue();

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void should_ThrowError_When_MatrixIsNotSquare() {
        IMatrix testMatrix = MatrixFactory.instance.create(new double[][]{
                {1, 2, 2, 4},
                {4, 4, 4, 4},
                {1, 2, 1, 3}
        });


        Assertions.assertThrows(RuntimeException.class, testMatrix::getTrace);
    }

}