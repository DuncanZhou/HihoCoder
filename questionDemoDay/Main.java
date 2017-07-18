package questionDemoDay;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] maze = new int[N + 1][M + 1];
        for (int i = 1; i < N + 1; i++) {
            String tempString = sc.next();
            for (int j = 1; j < M + 1; j++) {
                if (tempString.charAt(j - 1) == 'b')
                    maze[i][j] = 1;
                else
                    maze[i][j] = 0;
            }
        }
        sc.close();


        int[][] score = new int[N + 1][M + 1];// 分數矩陣
        int[][] direction = new int[N + 1][M + 1];// left 1;up 2;both 0 方向矩陣

        // 初始化分數矩陣
        for (int i = 1; i < N + 1; i++)
            score[i][0] = N * M + 100;// 無線大
        for (int i = 1; i < M + 1; i++)
            score[0][i] = N * M + 100;// 無線大

        // 初始化方向矩陣
        for (int i = 1; i < N + 1; i++)
            direction[i][0] = 2;// 向下
        for (int i = 1; i < M + 1; i++)
            direction[0][i] = 1;// 向右

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                int fromUp = 0;
                int fromLeft = 0;
                if (maze[i][j] == 1)//这个点有阻碍，需要变化才能到这个点，代价为1
                    score[i][j] = score[i][j] + 1;

                if (j + 1 >= M + 1 || maze[i - 1][j + 1] == 1)//必须向下走，代价为0
                    fromUp = 0;
                else if (direction[i - 1][j] == 1)//需要改变方向才能向下走，代价为1
                    fromUp = 1;
                else if (direction[i - 1][j] == 2)//必须向下走，代价为0
                    fromUp = 0;
                else if (direction[i - 1][j] == 0)//可以向下也可以向右边，代价为0
                    fromUp = 0;

                if (i + 1 >= N + 1 || maze[i + 1][j - 1] == 1)//必须向右走，代价为0
                    fromLeft = 0;
                else if (direction[i][j - 1] == 2)//需要改变方向才能向右走，代价为1
                    fromLeft = 1;
                else if (direction[i][j - 1] == 1)//必须向右走，代价为0
                    fromLeft = 0;
                else if (direction[i][j - 1] == 0)//可以向下也可以向右边，代价为0
                    fromLeft = 0;

                int min = Math.min(fromUp + score[i - 1][j], fromLeft
                        + score[i][j - 1]);//求最小的代价
                score[i][j] = score[i][j] + min;

                //计算方向
                if (min == fromUp + score[i - 1][j])
                    direction[i][j] = 2;
                if (min == fromLeft + score[i][j - 1])
                    direction[i][j] = 1;
                if ((fromUp + score[i - 1][j]) == (fromLeft + score[i][j - 1]))
                    direction[i][j] = 0;

                if (i == 1 && j == 1) {
                    score[i][j] = 0;
                    direction[i][j] = 1;
                }

            }
        }

        for(int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++)
                System.out.print(score[i][j] + " ");
            System.out.println();
        }


        System.out.println(score[N][M]);
    }
}
