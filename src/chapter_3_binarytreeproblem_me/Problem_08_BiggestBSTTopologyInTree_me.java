package chapter_3_binarytreeproblem_me;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bigming on 16/9/5.
 * 题目: 找到二叉树中符合搜索二叉树条件的最大拓扑结构
 * 难度: ***
 * 思路: 这里不要求是一颗子树,这也是与上一题的区别.
 *      方法一: 对于以任何一个节点为head的树中,找到符合搜索二叉树条件的
 *             最大结构,然后对树的每一个节点都遍历,时间复杂度为O(N^2)
 *      方法二: 用拓扑贡献记录来做,从小树的记录整合成大树的记录,相当于利用
 *             二叉树的后序遍历.时间复杂度为O(NlogN).
 */
public class Problem_08_BiggestBSTTopologyInTree_me {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public static int bstTopoSize1(Node head){
        if (head == null){
            return 0;
        }
        int max = maxTopo(head, head);
        max = Math.max(bstTopoSize1(head.left), max);
        max = Math.max(bstTopoSize1(head.right), max);
        return max;
    }

    public static int maxTopo(Node h, Node n){
        if (h != null && n != null && isBSTNode(h, n, n.value)){
            return maxTopo(h, n.left) + maxTopo(h, n.right) + 1;
        }
        return 0;
    }

    public static boolean isBSTNode(Node h, Node n, int value){
        if (h == null){
            return false;
        }
        if (h == n){
            return true;
        }
        return isBSTNode(h.value > value ? h.left : h.right, n, n.value);
    }




    public static class Record{
        public int l;
        public int r;

        public Record(int left, int right){
            this.l = left;
            this.r = right;
        }
    }

    public static int bstTopoSize2(Node head){
        Map<Node, Record> map= new HashMap<Node, Record>();
        return posOrder(head, map);
    }

    public static int posOrder(Node h, Map<Node, Record> map){
        if (h == null){
            return 0;
        }
        int ls = posOrder(h.left, map);
        int rs = posOrder(h.right, map);
        modifyMap(h.left, h.value, map, true);
        modifyMap(h.right, h.value, map, false);
        Record lr = map.get(h.left);
        Record rr = map.get(h.right);
        int lbst = lr == null ? 0 : lr.l + lr.r + 1;
        int rbst = rr == null ? 0 : rr.l + rr.r + 1;
        map.put(h, new Record(lbst, rbst));
        return Math.max(lbst + rbst + 1, Math.max(ls, rs));
    }

    public static int modifyMap(Node n, int v, Map<Node, Record> m, boolean s){
        if (n == null || (!m.containsKey(n))){
            return 0;
        }
        Record r = m.get(n);
        if ((s && n.value > v) || ((!s) && n.value < v)){
            m.remove(n);
            return r.l + r.r + 1;
        } else {
            int minus = modifyMap(s ? n.right : n.left, v, m, s);
            if (s){
                r.r = r.r - minus;
            } else {
                r.l = r.l - minus;
            }
            m.put(n, r);
            return minus;
        }
    }





}