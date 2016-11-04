package chapter_9_others_me;

import java.util.HashMap;

/**
 * Created by bigming on 16/11/4.
 * 题目: 设计可以变更的缓存结构
 *      设计一种缓存结构,该结构再构造时确定大小,假设大小为K, 并由两个功能:
 *      1. set(key, value): 将记录(key, value)插入该结构
 *      2. get(key): 返回key对应的value值
 *      要求:
 *      1. set和get方法的时间复杂度为O(1)
 *      2. 某个key的set和get操作一旦发生,认为这个key的记录成了最经常使用的.
 *      3. 当缓存的大小超过K时, 移除最不经常使用的记录,即set或get最久远的.
 * 思路: 可以由双端队列与哈希表相结合的方式实现.
 */
public class Problem_10_LeastRecentlyUsedCache_me {
    public static class Node<V>{
        public V value;
        public Node<V> last;
        public Node<V> next;

        public Node(V value){
            this.value = value;
        }
    }

    public static class NodeDoubleLinkedList<V>{
        private Node<V> head;
        private Node<V> tail;

        public NodeDoubleLinkedList(){
            this.head = null;
            this.tail = null;
        }

        public void addNode(Node<V> newNode){
            if (newNode == null){
                return;
            }
            if (this.head == null){
                this.head = newNode;
                this.tail = newNode;
            } else {
                this.tail.next = newNode;
                newNode.last = this.tail;
                this.tail = newNode;
            }
        }

        public void moveNodeToTail(Node<V> node){
            if (this.tail == node){
                return;
            }
            if (this.head == node){
                this.head = node.next;
                this.head.last = null;
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
            }
            node.last = this.tail;
            node.next = null;
            this.tail.next = node;
            this.tail = node;
        }

        public Node<V> removeHead(){
            if (this.head == null){
                return null;
            }
            Node<V> res = this.head;
            if (this.head == this.tail){
                this.head = null;
                this.tail = null;
            } else {
                this.head = res.next;
                res.next = null;
                this.head.last = null;
            }
            return res;
        }
    }

    public static class MyCache<K, V>{
        private HashMap<K, Node<V>> keyNodeMap;
        private HashMap<Node<V>, K> nodeKeyMap;
        private NodeDoubleLinkedList<V> nodeList;
        private int capacity;

        public MyCache(int capacity){
            if (capacity < 1){
                throw new RuntimeException("capacity should be more than 0");
            }
            this.keyNodeMap = new HashMap<K, Node<V>>();
            this.nodeKeyMap = new HashMap<Node<V>, K>();
            this.nodeList = new NodeDoubleLinkedList<V>();
            this.capacity = capacity;
        }

        public V get(K key){
            if (this.keyNodeMap.containsKey(key)){
                Node<V> res = this.keyNodeMap.get(key);
                this.nodeList.moveNodeToTail(res);
                return res.value;
            }
            return null;
        }

        public void set(K key, V value){
            if (this.keyNodeMap.containsKey(key)){
                Node<V> node = this.keyNodeMap.get(key);
                node.value = value;
                this.nodeList.moveNodeToTail(node);
            } else {
                Node<V> newNode = new Node<V>(value);
                this.keyNodeMap.put(key, newNode);
                this.nodeKeyMap.put(newNode, key);
                this.nodeList.addNode(newNode);
                if (this.keyNodeMap.size() == this.capacity + 1){
                    Node<V> removeNode = this.nodeList.removeHead();
                    K removeKey = this.nodeKeyMap.get(removeNode);
                    this.nodeKeyMap.remove(removeNode);
                    this.keyNodeMap.remove(removeKey);
                }
            }
        }
    }

    public static void main(String[] args) {
        MyCache<String, Integer> testCache = new MyCache<String, Integer>(3);
        testCache.set("A", 1);
        testCache.set("B", 2);
        testCache.set("C", 3);
        System.out.println(testCache.get("B"));
        System.out.println(testCache.get("A"));
        testCache.set("D", 4);
        System.out.println(testCache.get("D"));
        System.out.println(testCache.get("C"));

    }
}
