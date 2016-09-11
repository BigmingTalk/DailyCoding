package chapter_3_binarytreeproblem_me;

/**
 * Created by bigming on 16/9/9.
 * 题目: 判断数组arr是否可能是节点值类型为整型的搜索二叉树后序
 *      遍历的结果,如果可能是,根据后序数组重建搜索二叉树(没有重复值)
 * 难度: *
 * 思路: 后序遍历只需要取最后一个节点为父节点即可.
 *
 */
public class Problem_14_PosArrayToBST_me {
    public static boolean isPostArray(int[] array){
        if (array == null || array.length == 0){
            return false;
        }
        return isPost(array, 0, array.length - 1);
    }

    public static boolean isPost(int[] arr, int start, int end){
        if (start == end){
            return true;
        }
        int less = -1; //最后一次arr[end]> arr[i]的 i
        int more = end; //第一次arr[end] < arr[i]的 i
        for (int i = start; i < end; i++){
            if (arr[end] > arr[i]){
                less = i;
            } else {
                more =  more == end ? i : more;
            }
        }
        if (less == -1 || more == end){
            return isPost(arr, start, end -1);
        }
        if (less != more -1){
            return false;
        }
        return isPost(arr, start, less) && isPost(arr, more, end -1);
    }

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public static Node posArrayToBST(int[] posArr){
        if (posArr == null){
            return null;
        }
        return posToBST(posArr, 0, posArr.length -1);
    }

    public static Node posToBST(int[] posArr, int start, int end){
        if (start > end){
            return null;
        }
        Node head = new Node(posArr[end]);
        int less = -1;
        int more = end;
        for (int i = start; i < end; i ++){
            if (posArr[end] > posArr[i]){
                less = i;
            } else{
                more = more == end ? i : more;
            }
        }
        head.left = posToBST(posArr, start, less);
        head.right = posToBST(posArr, more, end-1);
        return head;
    }



    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int[] arr = { 2, 1, 3, 6, 5, 7, 4 };
        System.out.println(isPost(arr, 0, arr.length - 1));
        printTree(posArrayToBST(arr));

    }

}
