package chapter_3_binarytreeproblem_me;

/**
 * Created by bigming on 16/8/30.
 * 题目: 逆时针打印二叉树的边界节点,
 * 难度: **
 * 要求: 两种标准:
 *      第一种标准: 每层的最左最右节点以及叶子节点为边界
 *      第二种标准: 头,业节点以及树左边界延伸下去的路径为边界节点,
 *                树右边界延伸下去的路径为边界节点
 *
 */
public class Problem_02_PrintEdgeNodes_me {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    /*
    得到每层最左最右的节点,在这里因为递归的时候都是先查找左节点,所以最左节点都是第一次赋值的,
    而最右节点都是最后一次赋值的
     */
    public static void setEdgeMap(Node h, int l, Node[][] edgeMap){
        if (h == null){
            return;
        }

        edgeMap[l][0] = edgeMap[l][0] == null ? h : edgeMap[l][0];
        edgeMap[l][1] = h;

        setEdgeMap(h.left, l + 1, edgeMap);
        setEdgeMap(h.right, l + 1, edgeMap);
    }

    public static int getHeight(Node h, int l){
        if (h == null){
            return l;
        }
        return Math.max(getHeight(h.left, l + 1 ), getHeight(h.right, l + 1));
    }

    public static void printLeafNotInMap(Node h, int l, Node[][] m){
        if (h == null){
            return;
        }
        if (h.left == null && h.right == null && h != m[l][0] && h != m[l][1]){
            System.out.print(h.value + " ");
        }
        printLeafNotInMap(h.left, l + 1, m);
        printLeafNotInMap(h.right, l + 1, m);
    }

    public static void printEdge1(Node head){
        if (head == null){
            return;
        }
        int height = getHeight(head, 0);
        Node[][] edgeMap = new Node[height][2];
        setEdgeMap(head, 0, edgeMap);
        //打印左边界
        for (int i = 0; i != edgeMap.length; i++){
            System.out.print(edgeMap[i][0].value + " ");
        }
        //打印既不是左边界,也不是右边界的叶子节点
        printLeafNotInMap(head, 0, edgeMap);
        //打印右边界,但不是左边界的节点
        for (int i = edgeMap.length - 1; i != -1; i--){
            if (edgeMap[i][0] != edgeMap[i][1]){
                System.out.print(edgeMap[i][1].value + " ");
            }
        }
        System.out.println();
    }


    public static void printLeftEdge(Node h, boolean print){
        if (h == null){
            return;
        }
        if (print || (h.left == null && h.right == null)){
            System.out.print(h.value + " ");
        }
        printLeftEdge(h.left, print);
        printLeftEdge(h.right, print && h.left == null ? true : false);
    }

    public static void printRightEdge(Node h, boolean print){
        if (h == null){
            return;
        }
        printRightEdge(h.left, print && h.right == null ? true : false);
        printRightEdge(h.right, print);
        if (print || (h.left == null && h.right == null)){
            System.out.print(h.value + " ");
        }
    }

    public static void printEdge2(Node head){
        if (head == null){
            return;
        }
        System.out.print(head.value + " ");
        if (head.left != null && head.right != null){
            printLeftEdge(head.left, true);
            printRightEdge(head.right, true);
        } else {
            printEdge2(head.left != null ? head.left : head.right);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.right = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.left.right.left = new Node(7);
        head.left.right.right = new Node(8);
        head.right.left.left = new Node(9);
        head.right.left.right = new Node(10);
        head.left.right.right.right = new Node(11);
        head.right.left.left.left = new Node(12);
        head.left.right.right.right.left = new Node(13);
        head.left.right.right.right.right = new Node(14);
        head.right.left.left.left.left = new Node(15);
        head.right.left.left.left.right = new Node(16);

        printEdge1(head);
        printEdge2(head);

    }

}
