package chapter_9_others_me;

import java.util.HashMap;

/**
 * Created by bigming on 16/11/4.
 * 题目: 设计有setAll功能的哈希表
 *      哈希表常见的三个操作是put, get和containsKey,而且这三个操作的时间
 *      复杂度为O(1).现在想加一个setAll功能,就是把所有记录的value都设成统一
 *      的值.请设计并实现这种有setAll功能的哈希表,并且put,get,containsKey,
 *      setAll四个操作的时间复杂度都为O(1).
 * 难度: *
 * 思路: setAll操作的时间复杂度为O(1),说明肯定不是去改变所有的value值,所以
 *      可以用时间戳来解决.
 */
public class Problem_08_SetAllHashMap_me {
    public static class MyValue<V>{
        private V value;
        private long time;

        public MyValue(V value, long time){
            this.value = value;
            this.time = time;
        }

        public V getValue(){
            return this.value;
        }

        public long getTime(){
            return this.time;
        }
    }

    public static class MyHashMap<K, V>{
        private HashMap<K, MyValue<V>> baseMap;
        private long time;
        private MyValue<V> setAll;

        public MyHashMap(){
            this.baseMap = new HashMap<K, MyValue<V>>();
            this.time = 0;
            this.setAll = new MyValue<V>(null, -1);
        }

        public boolean containsKey(K key){
            return this.baseMap.containsKey(key);
        }

        public void put(K key, V value){
            this.baseMap.put(key, new MyValue(value, this.time++));
        }

        public void setAll(V value){
            this.setAll = new MyValue<V>(value, this.time++);
        }

        public V get(K key){
            if (this.containsKey(key)){
                if (this.baseMap.get(key).time > this.setAll.getTime()){
                    return this.baseMap.get(key).getValue();
                } else {
                    return this.setAll.getValue();
                }
            } else {
                return null;
            }
        }
    }


    public static void main(String[] args) {
        MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
        test.put("Tom", 1);
        test.put("James", 2);
        System.out.println(test.containsKey("Tom"));
        System.out.println(test.get("Tom"));
        System.out.println(test.containsKey("James"));
        System.out.println(test.get("James"));
        test.setAll(3);
        test.put("Rose", 4);
        System.out.println(test.get("Tom"));
        System.out.println(test.get("James"));
        System.out.println(test.get("Rose"));
        test.setAll(5);
        System.out.println(test.get("Tom"));
        System.out.println(test.get("James"));
        System.out.println(test.get("Rose"));

    }
}
