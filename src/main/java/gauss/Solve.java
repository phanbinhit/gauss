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
            for (i = 0; i < row; i++) {

                pivot = matrix[i][i];

                if (pivot == 0) {
                    if (!isSwapped(matrix, i)) {
                        return null;
                    }
                    isSwapped(matrix, i);
                }

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (j = 0; j < col; j++) {
                    matrix[i][j] = matrix[i][j] / pivot;

                }

                for (k = i + 1; k < row; k++) {
                    coefficient = matrix[k][i];
                    for (j = 0; j < col; j++) {
                        matrix[k][j] = matrix[k][j] - matrix[i][j] * coefficient;
                    }
                }
                    }
                });
                thread.start();
                thread.stop();
            }

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
