package offer52_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by duncan on 18-3-25.
 */
public class Main {
    public static void main(String[] args) {
        int res = 0;
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        char[][] lights = new char[N][M];
        //初始化lights
        List<int[]> toTurn = new ArrayList<>();
        for(int i = 0; i < N; i++){
            String str = in.next();
            for(int j = 0; j < M; j++)
                lights[i][j] = str.charAt(j);
        }
        //无论从哪个灯开始点亮,最后肯定能点亮所有的灯
        //统计可点亮的灯
        toTurn = solution.helper(lights);
        //已经访问过的
        boolean[][] visited = new boolean[N][M];
        for(int[] x: toTurn)
            visited[x[0]][x[1]] = true;
        for(int[] x: toTurn){
            lights[x[0]][x[1]] = '1';
            res += solution.dfs(lights);
            lights[x[0]][x[1]] = '0';
        }
        System.out.println(res);
    }
    //统计可点亮的位置
    private List<int[]> helper(char[][] lights){
        List<int[]> toTurn = new ArrayList<>();
        int N = lights.length;
        int M = lights[0].length;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(lights[i][j] == '0' && (( j - 1 >= 0 && lights[i][j-1] == '1') || (i - 1>=0 && lights[i-1][j] == '1') || (i+1<N && lights[i+1][j] == '1') || (j+1 < M && lights[i][j+1] == '1')))
                    toTurn.add(new int[]{i,j});
            }
        }
        return toTurn;
    }
    private int dfs(char[][] lights){
        //递归终止
        List<int[]> toTurn = helper(lights);
        if(toTurn.size() == 1) return 1;
        int res = 0;
        for(int[] x : toTurn)
        {
            lights[x[0]][x[1]] = '1';
            //当该处被点亮后,更新toTurn
            res += dfs(lights);
            lights[x[0]][x[1]] = '0';
        }
        return res;
    }
}
