package chapter_2_listproblem_me;

/**
 * Created by bigming on 16/8/23.
 *
 * 题目: 将单向链表按某值划分成左边小,中间相等,右边大的形式
 * 进阶: 要求调整后原来节点的相对顺序不变, 额外空间复杂度为O(1),时间复杂度为O(N)
 * 普通解法: 参考本书"数组类似partition的调整"
 * 进阶解法: 用三个链表分别保存三种情况,最后将它们首尾连接起来
 *
 */
public class Problem_08_SamllerEqualBigger_me {
    public static class Node{
        public int value;
        public Node next;
        public Node(int value) {
            this.value = value;
        }
    }

    public static void swap(Node[] nodeArr, int a, int b){
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

    public static void arrPartition(Node[] nodeArr, int pivot){
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big){
            if (nodeArr[index].value < pivot){
                swap(nodeArr, ++small, index);
            }else if (nodeArr[index].value == pivot){
                index++;
            }else {
                swap(nodeArr, index, --big);
            }
        }
    }

    public static Node listPartition1(Node head, int pivot) {
        if (head == null){
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null){
            cur = cur.next;
            i++;
        }
        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        for (i = 0; i < nodeArr.length; i++){
            nodeArr[i] = cur;
            cur = cur.next;
        }

        arrPartition(nodeArr, pivot);

        for (i = 1; i != nodeArr.length; i++){
            nodeArr[i-1].next = nodeArr[i];
        }
        nodeArr[i-1].next = null;
        return nodeArr[0];
    }

    public static Node listPartition2(Node head, int pivot){
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node bH = null;
        Node bT = null;
        Node next = null;

        while (head != null){
            next = head.next;
            head.next = null;
            if (head.value < pivot){
                if (sH == null){
                    sH = head;
                    sT = head;
                }else {
                    sT.next = head;
                    sT = head;
                }
            }else if(head.value == pivot){
                if (eH == null){
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = head;
                }
            }else {
                if (bH == null){
                    bH = head;
                    bT = head;
                }else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }

        if (sT != null){
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }

        if (eT != null){
            eT.next = bH;
        }

        return sH != null ? sH : eH != null ? eH : bH;

    }


    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        head1 = listPartition1(head1, 5);
        printLinkedList(head1);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }






}
