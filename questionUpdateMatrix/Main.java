package questionUpdateMatrix;

import java.util.Scanner;

/**
 * Created by duncan on 17-10-17.
 */

//二维树状数组
public class Main {
    public int lowbit(int i){
        return i & (-i);
    }
    public void Update(int x, int y, int value, int[][] C,int[][] matrix){
        matrix[x][y] = value;
        int x1 = x;
        while(x1 < matrix.length){
            int y1 = y;
            while(y1 < matrix.length) {
                C[x1][y1] += value;
                y1 += lowbit(y1);
            }
            x1 += lowbit(x1);
        }
    }
    public int Sum(int x, int y ,int[][] C){
        int sum = 0, x1 = x;
        while(x1 > 0){
            int y1 = y;
            while(y1 > 0){
                sum += C[x1][y1];
                y1 -= lowbit(y1);
            }
            x1 -= lowbit(x1);
        }
        return sum;
    }
    public static void main(String[] args) {
        Main solution = new Main();
        int mod = (int)1e9+7;
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[][] matrix = new int[N + 1][N + 1];
        int[][] C = new int[N + 1][N + 1];
        int operationsN = in.nextInt();
        while(operationsN-- > 0){
            String operation = in.next();
            if(operation.equals("Add")){
                int x = in.nextInt();
                int y = in.nextInt();
                int value = in.nextInt();
                solution.Update(x + 1,y + 1,value,C,matrix);
            }
            else{
                int x1 = in.nextInt();
                int y1 = in.nextInt();
                int x2 = in.nextInt();
                int y2 = in.nextInt();
                System.out.println((solution.Sum(x2 + 1,y2 + 1,C) -solution.Sum(x2 + 1, y1,C) - solution.Sum(x1, y2 + 1,C) + solution.Sum(x1, y1 ,C) + mod) % mod);
            }
        }
    }
}
