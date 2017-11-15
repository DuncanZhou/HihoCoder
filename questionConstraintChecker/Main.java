package questionConstraintChecker;

import java.util.*;

/**
 * Created by duncan on 17-11-15.
 */
//op:< || <= m,q代替
public class Main {
    public int convert(char A,Map<String,Integer> values){
        if(Character.isUpperCase(A))
            return values.get(String.valueOf(A));
        else
            return A - '0';
    }
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int rulesN = in.nextInt();
        //变量的个数
        int variables = 0;
        Set<Character> params = new HashSet<>();
        //rulesN条规则
        String[] rules = new String[rulesN];
        for(int i = 0; i < rulesN; i++) {
            rules[i] = in.next();
            //遍历该规则
            String rule = "";
            int j = 0;
            while(j < rules[i].length()){
                if(Character.isUpperCase(rules[i].charAt(j)) || Character.isDigit(rules[i].charAt(j))){
                    rule += rules[i].charAt(j);
                    if(!params.contains(rules[i].charAt(j)) && Character.isUpperCase(rules[i].charAt(j)))
                    {
                        params.add(rules[i].charAt(j));
                        variables++;
                    }
                    j++;
                }
                else if(rules[i].charAt(j) == '<' && j + 1 != rules[i].length() && rules[i].charAt(j+1) == '=')
                {
                    rule += 'q';
                    j += 2;
                }
                else
                {
                    rule += 'm';
                    j++;
                }
            }
            rules[i] = rule;
        }
        //需要判断的样例个数
        int examples = in.nextInt();
        for(int i = 0; i < examples; i++){
            //有variables个变量
            Map<String,Integer> values = new HashMap<>();
            for(int j = 0; j < variables; j++)
            {
                String var = in.next();
                int value = in.nextInt();
                values.put(var,value);
            }
            //判断规则
            boolean flag = true;
            for(int j = 0; j < rulesN; j++){
                String rule = rules[j];
                char[] nums = new char[rule.length()];
                char[] ops = new char[rule.length()];
                int numsindex = 0,opsindex = 0;
                for(int k = 0; k < rule.length(); k++){
                    if(Character.isUpperCase(rule.charAt(k)) || Character.isDigit(rule.charAt(k)))
                        nums[numsindex++] = rule.charAt(k);
                    else
                        ops[opsindex++] = rule.charAt(k);
                }
                //不止三个数
                int index = 0;
                while(index < numsindex-1) {
                    int num1 = solution.convert(nums[index],values);
                    int num2 = solution.convert(nums[index+1],values);
                    if((num1 == num2 && ops[index] != 'q') || num1 > num2 ) {
                        flag = false;
                        break;
                    }
                    index++;
                }
                if(flag == false)
                    break;
            }
            if(flag == true)
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }
}
