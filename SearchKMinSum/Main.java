package SearchKMinSum;

import java.util.ArrayList;

/**
 * Created by duncan on 17-8-3.
 */

//在有序数组中搜索m组k个和最小的数,存储下标
public class Main {
    public void Search(int[] array,int m,int k){
        ArrayList<Integer> tempArr = new ArrayList<>();
        //array数组已是有序数组
        //第一个解先加入
        for(int i = 0; i < k; i++)
            tempArr.add(i);
        int count = 1;
        System.out.println(tempArr.toString());
        int newdelta = 0;
        int olddelta = newdelta;
        int start = array.length - k;
        while(count < m){
            for(int i = start; i < array.length; i++){
                for(int j = start - 1; j >= i - k; j--){
                    olddelta = newdelta;
                    newdelta = array[i] - array[j];
                    for(int s = i + 1; s < array.length; s++){
                        if(array[s] - array[start - 1] > newdelta)
                            break;
                        for(int ss = start - 1; ss >= j; ss--){
                            if(array[s] - array[ss] <= newdelta && array[s] - array[ss] > olddelta) {
                                //有一个可行解
                                tempArr.remove((Object)ss);
                                tempArr.add(s);
                                System.out.println(tempArr.toString());
                                count++;
                                tempArr.remove((Object)s);
                                tempArr.add(ss);
                                if(count == m)
                                    return;
                            }
                            else if(array[s] - array[ss] > newdelta)
                                break;
                        }
                    }
                    //用start来替换
                    tempArr.remove((Object)j);
                    tempArr.add(i);
                    System.out.println(tempArr.toString());
                    count++;
                    if(count == m)
                        return;
                    tempArr.remove((Object)i);
                    tempArr.add(j);
                }
                //重新更新tempArr
                tempArr.remove((Object)(i - k));
                tempArr.add(start);
            }
        }
    }

    public static void main(String[] args) {
        int[] arryay = {1,2,3,4,5,6,7,8};
        Main solution = new Main();
        solution.Search(arryay,14,4);
    }
}
