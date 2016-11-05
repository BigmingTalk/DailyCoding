package chapter_9_others_me;

import java.util.HashMap;

/**
 * Created by bigming on 16/11/5.
 * 题目: 设计RandomPool结构
 * 设计一种结构,在该结构中有如下是三种功能:
 * 1. insert(key): 将某个key加入到该组织,做到不重复加入.
 * 2. delete(key): 将原本在结构中的某个key删除.
 * 3. getRandom(): 等概率随机返回结构中的任何一个key
 * 要求insert,delete,getRandom的时间复杂度都是O(1)
 * 难度: **
 * 思路:
 */
public class Problem_11_RandomPool_me {
    public static void main(String[] args) {
        Pool<String> pool = new Pool<String>();
        pool.insert("ying");
        pool.insert("jian");
        pool.insert("ming");
        pool.insert("Bigming");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());

        pool.delete("Bigming");
        System.out.println("After delete");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());

    }

    public static class Pool<K> {
        private HashMap<K, Integer> keyIndexMap;
        private HashMap<Integer, K> indexKeyMap;
        private int size;

        public Pool() {
            this.indexKeyMap = new HashMap<Integer, K>();
            this.keyIndexMap = new HashMap<K, Integer>();
            this.size = 0;
        }

        public void insert(K key) {
            if (!this.keyIndexMap.containsKey(key)) {
                this.keyIndexMap.put(key, this.size);
                this.indexKeyMap.put(this.size++, key);
            }
        }

        public void delete(K key) {
            if (this.keyIndexMap.containsKey(key)) {
                int deleteIndex = this.keyIndexMap.get(key);
                int lastIndex = --this.size;
                K lastKey = this.indexKeyMap.get(lastIndex);
                this.keyIndexMap.put(lastKey, deleteIndex);
                this.indexKeyMap.put(deleteIndex, lastKey);
                this.keyIndexMap.remove(key);
                this.indexKeyMap.remove(lastIndex);
            }
        }

        public K getRandom() {
            if (this.size == 0) {
                return null;
            }
            int randomIndex = (int) (Math.random() * this.size);
            return this.indexKeyMap.get(randomIndex);
        }
    }
}
