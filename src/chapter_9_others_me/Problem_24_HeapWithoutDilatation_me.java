package chapter_9_others_me;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by bigming on 16/11/21.
 * 题目: 设计一个没有扩容负担的堆结构
 *      堆结构一般使用固定长度的数组结构来实现. 这样实现非常经典,但是当固定数组耗尽时,
 *      就不得不申请一个更大的固定数组,然后把原来数组中的对象复制到新的数组里完成堆的
 *      扩容. 所以, 如果扩容时堆中的元素个数为N, 那么扩容行为的时间复杂度为O(N). 请
 *      设计一种堆结构, 即在任何时刻有关堆的操作时间复杂度都不超过O(LogN).
 * 要求: 1. 没有扩容的负担.
 *      2. 可以生成小根堆, 也可以生成大根堆.
 *      3. 包含getHead方法, 返回当前堆顶的值.
 *      4. 包含getSize方法,返回当前堆的大小.
 *      5. 包含add(x)方法, 即向堆中新加元素x, 操作后依然是小根堆/大根堆.
 *      6. 包含popHead方法, 即删除并返回堆顶的值, 操作后依然是小根队/大根对.
 *      7. 如果堆中的节点个数为N, 那么各个方法的时间复杂度为:
 *          getHead: O(1)
 *          getSize: O(1)
 *          add: O(LogN)
 *          popHead: O(LogN)
 * 难度: ****
 * 思路: 用完全二叉树结构, 并且Node节点多一个指向父节点的parent指针.
 *
 */
public class Problem_24_HeapWithoutDilatation_me {

    // binary tree node contains parent reference
    public static class Node<K> {
        public K value;
        public Node<K> left;
        public Node<K> right;
        public Node<K> parent;

        public Node(K data) {
            value = data;
        }
    }

    public static class MyHeap<K> {
        private Node<K> head; // 堆头结点
        private Node<K> last; // 堆尾节点
        private long size; // 堆大小
        private Comparator<K> comp; // 基于该比较器来看是大根堆还是小根堆

        public MyHeap(Comparator<K> compare) {
            head = null;
            last = null;
            size = 0;
            comp = compare;
        }

        public K getHead() {
            return head == null ? null : head.value;
        }

        public long getSize() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0 ? true : false;
        }

        public void add(K value) {
            Node<K> newNode = new Node<K>(value);
            if (size == 0) {
                head = newNode;
                last = newNode;
                size++;
                return;
            }
            Node<K> node = last;
            Node<K> parent = node.parent;
            // 找到正确的插入位置
            while (parent != null && node != parent.left) {
                node = parent;
                parent = node.parent;
            }
            Node<K> nodeToAdd = null;
            if (parent == null) { // last是一层中最后一个节点的情况
                nodeToAdd = mostLeft(head);
                nodeToAdd.left = newNode;
                newNode.parent = nodeToAdd;
            } else if (parent.right == null) {
                parent.right = newNode;
                newNode.parent = parent;
            } else {
                nodeToAdd = mostLeft(parent.right);
                nodeToAdd.left = newNode;
                newNode.parent = nodeToAdd;
            }
            last = newNode;
            // 插入后还要调整
            heapInsertModify();
            size++;
        }

        public K popHead() {
            if (size == 0) {
                return null;
            }
            Node<K> res = head;
            if (size == 1) {
                head = null;
                last = null;
                size--;
                return res.value;
            }
            Node<K> oldLast = popLastAndSetPreviousLast();
            // 如果弹出堆尾节点后, 堆的大小等于1的处理
            if (size == 1) {
                head = oldLast;
                last = oldLast;
                return res.value;
            }
            // 如果弹出堆尾节点后, 堆的大小大于1的处理
            Node<K> headLeft = res.left;
            Node<K> headRight = res.right;
            oldLast.left = headLeft;
            if (headLeft != null) {
                headLeft.parent = oldLast;
            }
            oldLast.right = headRight;
            if (headRight != null) {
                headRight.parent = oldLast;
            }
            res.left = null;
            res.right = null;
            head = oldLast;

            // 堆heapify的过程
            heapify(oldLast);
            return res.value;
        }

        // 找到以node为头的子树中,最左的节点
        private Node<K> mostLeft(Node<K> node) {
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        // 找到以node为头的子树中, 最右的节点
        private Node<K> mostRight(Node<K> node) {
            while (node.right != null) {
                node = node.right;
            }
            return node;
        }

        private void heapInsertModify() {
            Node<K> node = last;
            Node<K> parent = node.parent;
            if (parent != null && comp.compare(node.value, parent.value) < 0) {
                last = parent;
            }
            while (parent != null && comp.compare(node.value, parent.value) < 0) {
                swapClosedTwoNodes(node, parent);
                parent = node.parent;
            }
            if (head.parent != null) {
                head = head.parent;
            }
        }

        // heap heapify process
        private void heapify(Node<K> node) {
            Node<K> left = node.left;
            Node<K> right = node.right;
            Node<K> most = node;
            while (left != null) {
                if (left != null && comp.compare(left.value, most.value) < 0) {
                    most = left;
                }
                if (right != null && comp.compare(right.value, most.value) < 0) {
                    most = right;
                }
                if (most != node) {
                    swapClosedTwoNodes(most, node);
                } else {
                    break;
                }
                left = node.left;
                right = node.right;
                most = node;
            }
            if (node.parent == last) {
                last = node;
            }
            while (node.parent != null) {
                node = node.parent;
            }
            head = node;
        }

        private void swapClosedTwoNodes(Node<K> node, Node<K> parent) {
            if (node == null || parent == null) {
                return;
            }
            Node<K> parentParent = parent.parent;
            Node<K> parentLeft = parent.left;
            Node<K> parentRight = parent.right;
            Node<K> nodeLeft = node.left;
            Node<K> nodeRight = node.right;
            node.parent = parentParent;
            if (parentParent != null) {
                if (parent == parentParent.left) {
                    parentParent.left = node;
                } else {
                    parentParent.right = node;
                }
            }
            parent.parent = node;
            if (nodeLeft != null) {
                nodeLeft.parent = parent;
            }
            if (nodeRight != null) {
                nodeRight.parent = parent;
            }
            if (node == parent.left) {
                node.left = parent;
                node.right = parentRight;
                if (parentRight != null) {
                    parentRight.parent = node;
                }
            } else {
                node.left = parentLeft;
                node.right = parent;
                if (parentLeft != null) {
                    parentLeft.parent = node;
                }
            }
            parent.left = nodeLeft;
            parent.right = nodeRight;
        }

        // pop last node in Tree, and set last to previous last node
        private Node<K> popLastAndSetPreviousLast() {
            Node<K> node = last;
            Node<K> parent = node.parent;
            while (parent != null && node != parent.right) {
                node = parent;
                parent = node.parent;
            }
            if (parent == null) {
                node = last;
                parent = node.parent;
                node.parent = null;
                if (node == parent.left) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                last = mostRight(head);
            } else {
                Node<K> newLast = mostRight(parent.left);
                node = last;
                parent = node.parent;
                node.parent = null;
                if (node == parent.left) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                last = newLast;
            }
            size--;
            return node;
        }

        // for test
        public void changeHead(K value) {
            if (this.head != null) {
                Node<K> newNode = new Node<K>(value);
                Node<K> headLeft = this.head.left;
                Node<K> headRight = this.head.right;
                if (headLeft != null) {
                    headLeft.parent = newNode;
                    newNode.left = headLeft;
                }
                if (headRight != null) {
                    headRight.parent = newNode;
                    newNode.right = headRight;
                }
                this.head.left = null;
                this.head.right = null;
                this.head = newNode;
                this.heapify(this.head);
            }
        }

        // for test
        public void printHeapByLevel() {
            if (this.size == 0) {
                System.out.println("Heap Empty!");
                return;
            }
            System.out.println("Head: " + this.head.value);
            System.out.println("Last: " + this.last.value);
            System.out.println("Size: " + this.size);
            printBinaryTreeByDepth(this.head);
        }

        // for test
        private void printBinaryTreeByDepth(Node<K> head) {
            Queue<Node<K>> nodeQueue = new LinkedList<Node<K>>();
            Node<K> levelLastNode = head;
            Node<K> nextLevelLastNode = null;
            int levelNum = 0;
            nodeQueue.add(head);
            System.out.print("Level 0 nodes: ");
            while (!nodeQueue.isEmpty()) {
                Node<K> current = nodeQueue.poll();
                System.out.print(current.value + " ");
                if (current.left != null) {
                    nextLevelLastNode = current.left;
                    nodeQueue.add(current.left);
                }
                if (current.right != null) {
                    nextLevelLastNode = current.right;
                    nodeQueue.add(current.right);
                }
                if (current == levelLastNode) {
                    levelLastNode = nextLevelLastNode;
                    nextLevelLastNode = null;
                    System.out.println();
                    if (levelLastNode != null) {
                        System.out.print("Level " + (++levelNum) + " nodes: ");
                    }
                }
            }
        }

        // for test
        public static class MyComparator implements Comparator<Integer> {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o2 > o1) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }

        // for test
        public static void main(String[] args) {
            MyHeap<Integer> test = new MyHeap<Integer>(new MyComparator());
            test.add(4);
            test.add(6);
            test.add(8);
            test.changeHead(3);
            test.add(9);
            test.add(10);
            test.add(1);
            test.printHeapByLevel();
            System.out.println(test.popHead());
            test.printHeapByLevel();
            test.add(7);
            test.printHeapByLevel();
            test.add(10);
            test.add(11);
            test.printHeapByLevel();

            System.out.println(test.popHead());
            System.out.println(test.popHead());
            System.out.println(test.popHead());
            System.out.println(test.popHead());
            System.out.println(test.popHead());
            System.out.println(test.popHead());
            test.printHeapByLevel();

        }
}




























}
