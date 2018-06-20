package TheLastestTime;

import java.util.*;

public class Main {
    private void Swap(int[] nums,int a ,int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    //生成全排列
    private void Pernumate(int[] nums, int index, List<Integer> res){
        if(index == nums.length){
            int temp = 0;
            for(int x : nums) temp = temp * 10 + x;
            res.add(temp);
            return;
        }
        for(int i = index; i < nums.length; i++){
            Swap(nums,i,index);
            Pernumate(nums,index+1,res);
            Swap(nums,i,index);
        }
    }
    //检查
    private boolean check(int x){
        //取前两位
        int pre = x / 100;
        //取后两位
        int last = x % 100;
        if(pre >= 0 && pre <= 23 && last >= 0 && last <= 59)
            return true;
        else return false;
    }
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int[] nums = new int[4];
        for(int i = 0; i < nums.length; i++) nums[i] = in.nextInt();
        //排列组合
        List<Integer> res = new ArrayList<>();
        solution.Pernumate(nums,0,res);
        for(int x : res)
            if(solution.check(x)) queue.add(x);
        if(queue.size() == 0) System.out.println("NOT POSSIBLE");
        else {
            StringBuilder result = new StringBuilder(String.valueOf(queue.poll()));
            while (result.length() != 4) {
                result.insert(0, '0');
            }
            result.insert(2, ':');
            System.out.println(result.toString());
        }
    }
}
