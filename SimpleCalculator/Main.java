package SimpleCalculator;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by duncan on 17-9-24.
 */
//完成一个简单的四则计算器(+,-,*,/)
public class Main {
    public int operate(String a,String b,String operator){
        int temp = 0;
            switch (operator) {
                case "*":
                    temp = Integer.valueOf(a) * Integer.valueOf(b);
                    break;
                case "/":
                    temp = Integer.valueOf(a) / Integer.valueOf(b);
                    break;
                case "+":
                    temp = Integer.valueOf(a) + Integer.valueOf(b);
                    break;
                case "-":
                    temp = Integer.valueOf(a) - Integer.valueOf(b);
                    break;
        }
        return temp;
    }
    public int Calaculate(ArrayList<String> string){
        int result = 0;
        int temp = 0;
        Stack<String> stack = new Stack<>();
        //traverse string
        for(int i = 0; i < string.size(); i ++){
            if(string.get(i).equals("+") || string.get(i).equals("-") || string.get(i).equals("(") || string.get(i).equals("*") || string.get(i).equals("/"))
                stack.push(string.get(i));
            else if(string.get(i).equals(")"))
            {
                //pop
                String b = stack.pop();
                String operator = stack.pop();
                if(!operator.equals("(")) {

                    String a = stack.pop();
                    temp = operate(a,b,operator);
                    //pop '('
                    stack.pop();
                    if(!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/"))) {
                        operator = stack.pop();
                        a = stack.pop();
                        temp = operate(a,String.valueOf(temp),operator);
                    }
                    //push
                    stack.push(String.valueOf(temp));
                }
                else
                    stack.push(b);
            }
            else{
                //if top of stack is * or /, then calaculate
                if(!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/"))){
                    String b = string.get(i);
                    String operator = stack.pop();
                    String a = stack.pop();
                    temp  = operate(a,b,operator);
                    stack.push(String.valueOf(temp));
                }
                else
                    stack.push(string.get(i));
            }
        }
        //if stack is not empty, pop and calculate
        while(!stack.isEmpty()){
            String b = stack.pop();
            if(!stack.isEmpty())
            {
                String operator = stack.pop();
                String a = stack.pop();
                if(!stack.isEmpty() && stack.peek().equals("-")) {
                    if(operator.equals("+"))
                        temp = operate(a, b, "-");
                    else
                        temp = operate(a,b,"+");
                }
                else
                    temp = operate(a,b,operator);
                stack.push(String.valueOf(temp));
            }
            else
                return Integer.parseInt(b);
        }
        return result;
    }
    public ArrayList<String> prepare(String string){
        //split according to +,-,*,/
        ArrayList<String> results = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for(int i = 0; i < string.length(); i++)
            if(string.charAt(i) >= '0' && string.charAt(i) <= '9')
                //push
                stack.push(string.charAt(i));
            else
            {
                char operator = string.charAt(i);
                if(i != 0 && string.charAt(i - 1) >= '0' && string.charAt(i) <= '9') {
                    //pop all elements
                    StringBuilder tempstring = new StringBuilder();
                    while (!stack.isEmpty())
                        tempstring.append(stack.pop());
                    results.add(tempstring.reverse().toString());
                }
                results.add(String.valueOf(operator));
            }
            if(!stack.isEmpty()) {
                StringBuilder tempstring = new StringBuilder();
                while (!stack.isEmpty())
                    tempstring.append(stack.pop());
                results.add(tempstring.reverse().toString());
            }
            return results;
    }
    //convert to suffix expression
    /*
    * 1.遇到操作数：直接输出（添加到后缀表达式中）
    * 2.栈为空时，遇到运算符，直接入栈
    * 3.遇到左括号：将其入栈
    * 4.遇到右括号：执行出栈操作，并将出栈的元素输出，直到弹出栈的是左括号，左括号不输出。
    * 5.遇到其他运算符：加减乘除：弹出所有优先级大于或者等于该运算符的栈顶元素，然后将该运算符入栈
    * 6.最终将栈中的元素依次出栈，输出。
     */
    //100*(2+12)-(20/3)*2
    public ArrayList<String> SuffixExpression(ArrayList<String> string){
        ArrayList<String> suffix = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < string.size(); i++){
            if(string.get(i).equals("*") || string.get(i).equals("/") || string.get(i).equals("(")){
                //push
                stack.push(string.get(i));
            }
            else if(string.get(i).equals("+") || string.get(i).equals("-")){
                while(!stack.isEmpty() && (stack.peek().equals("*") || stack.peek().equals("/")))
                {
                        suffix.add(stack.pop());
                }
                //push
                stack.push(string.get(i));
            }
            else if(string.get(i).equals(")"))
            {
                while(!stack.isEmpty() && !stack.peek().equals("("))
                    suffix.add(stack.pop());
                //pop (
                stack.pop();
            }else{
                suffix.add(string.get(i));
            }
        }
        //push all elements in stack
        while(!stack.isEmpty())
            suffix.add(stack.pop());
        return suffix;
    }
    public int CalaculateSuffix(ArrayList<String> suffix){
        //when meet operator, pop two elements to calaculate
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < suffix.size(); i++){
            if(!(suffix.get(i).equals("+") || suffix.get(i).equals("-") || suffix.get(i).equals("*") || suffix.get(i).equals("/")))
                //push into stack
                stack.push(suffix.get(i));
            else{
                //calucate
                String operator = suffix.get(i);
                String b = stack.pop();
                String a = stack.pop();
                String temp = String.valueOf(operate(a,b,operator));
                stack.push(temp);
            }
        }
        return Integer.valueOf(stack.pop());
    }
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        String string = in.next();
//        String string = "100*(2+12)-(20/3)*2";
        //prepare
//        System.out.println(solution.prepare(string));
//        System.out.println(solution.SuffixExpression(solution.prepare(string)));
        System.out.println(solution.CalaculateSuffix(solution.SuffixExpression(solution.prepare(string))));
//        System.out.println(solution.Calaculate(solution.prepare(string)));
    }
}
