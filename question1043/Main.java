package question1043;

import java.util.Scanner;

/**
 * Created by duncan on 17-10-17.
 */
//完全背包问题
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int itemsN = in.nextInt();
        int tickets = in.nextInt();
        int[] need = new int[itemsN];
        int[] value = new int[itemsN];
        for(int i = 0; i < itemsN; i++){
            need[i] = in.nextInt();
            value[i] = in.nextInt();
        }
        int[] best = new int[tickets + 1];
        for(int i = 0; i < itemsN; i++){
            for(int j = need[i]; j <= tickets; j++){
                best[j] = Math.max(best[j - need[i]] + value[i],best[j]);
            }
        }
        System.out.println(best[tickets]);
    }
}
