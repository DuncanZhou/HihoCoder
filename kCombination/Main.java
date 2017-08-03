package kCombination;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by duncan on 17-8-1.
 */
public class Main {
    private static ArrayList<Integer> tempArr = new ArrayList<>();
    public static void main(String[] args) {
        Main solution = new Main();
        int[] com = {1,2,3,4,5,6,7,8};
        int k = 4;
        solution.kCombination(0,k,com);
    }
    //求n个数中k个数的组合
    public static void kCombination(int index,int k,int[] arrary){
        if(k == 1){
            //在剩下来的数中选取最后一个加入其中
            for(int i = index; i < arrary.length; i++) {
                tempArr.add(arrary[i]);
                System.out.println(tempArr.toString());
                tempArr.remove((Object)arrary[i]);
            }
        }
        else if(k > 1){
            for(int i = index; i <= arrary.length - k; i++){
                tempArr.add(arrary[i]);
                kCombination(i + 1, k - 1,arrary);
                tempArr.remove((Object)arrary[i]);
            }
        }
    }
}
