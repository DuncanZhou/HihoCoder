package offer53_01;

import java.util.*;

/**
 * Created by duncan on 18-4-1.
 */


class TreeNode{
    String name;
    List<TreeNode> children = new ArrayList<>();
    TreeNode(String val){
        name = val;
    }
}
public class Main {
    public static void main(String[] args) {
        Main solution = new Main();
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        String king = in.next();
        Set<String> death = new HashSet<>();
        HashMap<String,String> map = new HashMap<>();
        map.put(king,"0");
        TreeNode root = new TreeNode(king);
        in.nextLine();
        for(int i = 0; i < N; i++){
            String str = in.nextLine();
            String[] temp = str.split(" ");
            if(temp[0].equals("birth"))
                solution.Insert(root,temp[1],temp[2]);
            else death.add(temp[1]);
        }
        //构造好树之后,对每个结点编码
        Queue<TreeNode> queue = new LinkedList<TreeNode>(){{add(root);}};
        while (!queue.isEmpty()){
            TreeNode temp = queue.poll();
            String str = map.get(temp.name);
            int i = 0;
            for(TreeNode child : temp.children){
                queue.add(child);
                map.put(child.name,str+i++);
            }
        }
        PriorityQueue<String> res = solution.helper(map);
        while (!res.isEmpty()){
            String value = res.poll();
            for(Map.Entry<String,String> entry: map.entrySet()){
                if(entry.getValue().equals(value)){
                    if(!death.contains(entry.getKey()) && !entry.getKey().equals(king))
                        System.out.println(entry.getKey());
                    break;
                }
            }
        }
    }
    //插入
    private void Insert(TreeNode root,String parent,String child){
        //从root遍历,找到parent
        if(root.name.equals(parent)){
            root.children.add(new TreeNode(child));
            return;
        }
        for(TreeNode children : root.children)
            Insert(children,parent,child);
    }
    private PriorityQueue<String> helper(HashMap<String,String> map){
        PriorityQueue<String> res = new PriorityQueue<>((o1, o2) -> {
            if(check(o1) && check(o2)){
                if(o1.equals(o2)) return 0;
                else if(o1.length() > o2.length()) return 1;
                else return -1;
            }else{
                if(check(o1)) return -1;
                if(check(o2)) return 1;
                if(o1.length() < o1.length()) return -1;
                if(o1.length() > o2.length()) return 1;
                for(int i = 0 ; i < o1.length() && i < o2.length(); i++){
                    if(o1.charAt(i) == o2.charAt(i)) continue;
                    else if(o1.charAt(i) < o2.charAt(i)) return -1;
                    else return 1;
                }
                return 0;
            }
        });
        for(Map.Entry<String,String> entry : map.entrySet())
            res.add(entry.getValue());
        return res;
    }
    private boolean check(String s){
        //检测s是否都为0
        int i;
        for(i = 0; i < s.length(); i++)
            if(s.charAt(i) != '0') break;
        if(i < s.length()) return false;
        else return true;
    }
}
