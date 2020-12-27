package gauss;

public class Solve {

    private static int i, j, k;
    private static double pivot, coefficient;

    @SuppressWarnings("deprecation")
    public static double[] solveEquations(final double[][] matrix) {
        double[] results = new double[matrix[0].length - 1];
        int row = matrix.length;
        int col = matrix[0].length;
        if (col > row + 1) {
            return null;
        } else {
            long start = System.currentTimeMillis();

            for (i = 0; i < matrix[0].length - 1; i++) {
                for (j = i + 1; j < matrix.length; j++) {
                    if (matrix[i][i] == 0) {
                        if (!isSwapped(matrix, i)) {

                        }
                        isSwapped(matrix, i);
                    } else {
                        Thread thread1 = new Thread() {
                            @Override
                            public void run() {
                                coefficient = matrix[j][i] / matrix[i][i];
                            }
                        };
                        thread1.run(); 
                        for (k = i; k < matrix[0].length; k++) {
                            Thread thread2 = new Thread() {
                                @Override
                                public void run() {
                                    matrix[j][k] -= coefficient * matrix[i][k];
                                }
                            };
                            thread2.run();
                            thread2.interrupt();
                        }
                        thread1.interrupt();
                    }

                }
            }
            System.out.println("Time"
                    + (System.currentTimeMillis() - start) + "ms");
            results[row - 1] = matrix[row - 1][col - 1] / matrix[row - 1][row - 1];
            for (int i = row - 2; i >= 0; i--) {
                double sum = 0;
                for (int j = i + 1; j < col - 1; j++) {
                    sum += matrix[i][j] * results[j];
                }
                results[i] = (matrix[i][col - 1] - sum) / matrix[i][i];
            }
        }
        return results;
    }

    private static boolean isSwapped(double[][] matrix, int index) {
        for (int j = index + 1; j < matrix.length; j++) {
            if (matrix[j][index] != 0) {
                swap(matrix, index, j);
                pivot = matrix[index][index];
                return true;
            }
        }
        return false;
    }

    private static void swap(double[][] matrix, int before, int after) {
        for (int i = 0; i < matrix[before].length; i++) {
            double temp = matrix[before][i];
            matrix[before][i] = matrix[after][i];
            matrix[after][i] = temp;
        }
    }

}
