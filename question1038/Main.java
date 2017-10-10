package question1038;

import java.util.Scanner;

/**
 * Created by duncan on 17-10-10.
 */
//01背包问题
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int itemsN = in.nextInt();
        int tickets = in.nextInt();
        int[] need = new int[itemsN + 1];
        int[] value = new int[itemsN + 1];
        for(int i = 1; i <= itemsN; i++){
            need[i] = in.nextInt();
            value[i] = in.nextInt();
        }
//        int[][] best = new int[itemsN + 1][tickets + 1];
        int[] best = new int[tickets + 1];
//        for(int i = 0; i <= tickets; i++)
//            best[i] = 0;
        for(int i = 1; i <= itemsN; i++){
            for(int j = tickets; j >= need[i]; j--){
                best[j] = Math.max(best[j - need[i]] + value[i],best[j]);
            }
        }
        System.out.println(best[tickets]);
    }
}
