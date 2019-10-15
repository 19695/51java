package com.learn.homework.second;

/**
 * 矩阵平方
 * 时间复杂度 O(n^3)
 *
 * @author Colm
 * @create 2019/10/14
 */
public class Matrix {
    private static int[][] matrix1 = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};

    public static void main(String[] args){
        int[][] res = getResult(matrix1);
        printMatrix(res);

        int[][] matrix2 = {{1, 1},{1, 1}};
        printMatrix(getResult(matrix2));

        int[][] matrix3 = {{1, 5, 7},{2, 3, 1},{4, 6, 8}};
        printMatrix(getResult(matrix3));
    }

    // 矩阵打印
    public static void printMatrix(int[][] matrix){
        for(int[] arr : matrix){
            for(int i = 0; i < arr.length; i++){
                System.out.print(arr[i] + ",");
            }
            System.out.println();
        }
    }

    // 矩阵计算
    public static int[][] getResult(int[][] matrix1){
        if(matrix1 == null){
            return null;
        }
        int count = matrix1.length; // 行（列）数
        int[][] result = new int[count][count];
        int sum = 0; // 记录单步计算结果
        // （外，内）*（内，中）
        for(int k = 0; k < count; k++){ // 外标
            for(int i = 0; i < count; i++){ // 中标
//                System.out.println(k + "," + i); // 结果矩阵的下标
                sum = 0;
                for(int j = 0; j < count; j++){ // 内标
//                    System.out.println("   " + k + "," + j + "*" + j + "," + i); // 计算方式
                    sum += (matrix1[k][j] * matrix1[j][i]);
                }
                result[k][i] = sum;
//                System.out.println();
            }
        }
        return result;
    }
}
