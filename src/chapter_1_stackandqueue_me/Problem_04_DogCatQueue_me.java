package chapter_1_stackandqueue_me;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by bigming on 16/8/16.
 *
 * 题目: 实现一种猫狗队列,既可以全局地对猫狗队列add,poll,isEmpty,又可以
 *      分别地对猫或狗进行add,poll,isEmpty.
 * 难度: *
 * 思路: 猫和狗分别一个队列,只不过都加上一个计数器来表示先后顺序,然后通过
 *      比较两个队列的队头的顺序即可.
 */
public class Problem_04_DogCatQueue_me {
    public static class Pet{
        String type;

        public Pet(String type){
            this.type = type;
        }

        public String getPetType(){
            return this.type;
        }
    }

    public static class Dog extends Pet{
        public Dog(){
            super("dog");
        }
    }

    public static class Cat extends Pet{
        public Cat(){
            super("cat");
        }
    }

    public static class PetEnterQueue{
        private Pet pet;
        private long count;

        public PetEnterQueue(Pet pet, long count){
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet(){
            return this.pet;
        }

        public long getCount(){
            return this.count;
        }

        public String getPetType(){
            return this.pet.getPetType();
        }
    }

    public static class DogCatQueue{
        private Queue<PetEnterQueue> dogQ;
        private Queue<PetEnterQueue> catQ;
        private long count;

        public DogCatQueue(){
            this.dogQ = new LinkedList<PetEnterQueue>();
            this.catQ = new LinkedList<PetEnterQueue>();
            this.count = 0;
        }

        public void add(Pet pet){
            if (pet.getPetType().equals("dog")){
                this.dogQ.add(new PetEnterQueue(pet, this.count++));
            }else if (pet.getPetType().equals("cat")){
                this.catQ.add(new PetEnterQueue(pet, this.count++));
            }else{
                throw new RuntimeException("error, not dog or cat");
            }
        }

        public Pet pollAll(){
            if (!this.dogQ.isEmpty()&&!this.catQ.isEmpty()){
                if (this.dogQ.peek().getCount()<this.catQ.peek().getCount()){
                    return this.dogQ.poll().getPet();
                }else{
                    return this.catQ.poll().getPet();
                }
            }else if (!this.dogQ.isEmpty()){
                return this.dogQ.poll().getPet();
            }else if (!this.catQ.isEmpty()){
                return this.catQ.poll().getPet();
            }else{
                throw new RuntimeException("error, queue is empty");
            }
        }

        public Dog pollDog(){
            if (!this.dogQ.isEmpty()){
                return (Dog)this.dogQ.poll().getPet();
            }else {
                throw new RuntimeException("Dog queue is empty");
            }
        }

        public Cat pollCat(){
            if (!this.catQ.isEmpty()){
                return (Cat)this.catQ.poll().getPet();
            }else {
                throw new RuntimeException("Cat queue is empty");
            }
        }

        public boolean isEmpty(){
            return this.dogQ.isEmpty() && this.catQ.isEmpty();
        }

        public boolean isDogEmpty(){
            return this.dogQ.isEmpty();
        }

        public boolean isCatEmpty(){
            return this.catQ.isEmpty();
        }
    }

    public static void main(String[] args){
        DogCatQueue test = new DogCatQueue();
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Dog dog3 = new Dog();

        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);
        while (!test.isDogEmpty()) {
            System.out.println(test.pollDog().getPetType());
        }

        while (!test.isEmpty()) {
            System.out.println(test.pollAll().getPetType());
        }


        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);
        while (!test.catQ.isEmpty()) {
            System.out.println(test.pollCat().getPetType());
        }

        while (!test.isEmpty()) {
            System.out.println(test.pollAll().getPetType());
        }


        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);
        while (!test.isEmpty()) {
            System.out.println(test.pollAll().getPetType());
        }

    }

}
