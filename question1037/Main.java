package question1037;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int levels = in.nextInt();
    int[][] reward = new int[levels + 1][levels + 1];
    int[][] best = new int[levels + 1][levels + 1];
    for(int i = 1; i <= levels; i++)
      for(int j = 1; j <= i; j++){
        reward[i][j] = in.nextInt();
      }
    //init best
    best[1][1] = reward[1][1];
    for(int i = 2; i <= levels; i++){
      for(int j = 1; j <= i; j++){
        if(j == 1)
          best[i][j] = best[i - 1][j] + reward[i][j];
        else if(j == i)
          best[i][j] = best[i - 1][j - 1] + reward[i][j];
        else
          best[i][j] = Math.max(best[i - 1][j],best[i - 1][j - 1]) + reward[i][j];
      }
    }
    //get the max of the n-th level
    int result = 0;
    for(int i = 1; i <= levels; i++)
      result = Math.max(result,best[levels][i]);
    System.out.println(result);
  }
}