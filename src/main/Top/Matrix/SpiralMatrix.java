package Top.Matrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    /**
     * No.54 螺旋顺序大于矩阵
     *
     * 四个指针控制方向
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int up = 0, down = m - 1, left = 0, right = n - 1;
        List<Integer> result = new ArrayList<>();
        while (true) {
            for (int i = left; i <= right; i++) {
                result.add(matrix[up][i]);
            }
            if (++up > down) break;
            for (int i = up; i <= down; i++) {
                result.add(matrix[i][right]);
            }
            if (--right < left) break;
            for (int i = right; i >= left; i--) {
                result.add(matrix[down][i]);
            }
            if (--down < up) break;
            for (int i = down; i >= up; i--) {
                result.add(matrix[i][left]);
            }
            if (++left > right) break;
        }
        return result;
    }
}
