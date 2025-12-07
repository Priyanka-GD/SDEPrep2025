package neetcode75;

import DAO.Bucket;

import java.io.IOException;

public class HashMapDesign {
    private static final int BUCKET_COUNT = 1000;
    private Bucket buckets[];

    public HashMapDesign() {
        this.buckets = new Bucket[BUCKET_COUNT];
        for (int i = 0; i < BUCKET_COUNT; i++)
            buckets[i] = new Bucket();
    }

    public int hash(int key) {
        return key % BUCKET_COUNT;
    }

    public void put(int key, int val) {
        int idx = hash(key);
        buckets[idx].put(key, val);
    }

    public int get(int key) {
        int idx = hash(key);
        return buckets[idx].get(key);
    }

    public void remove(int key) {
        int idx = hash(key);
        buckets[idx].remove(key);
    }

    public static void main(String args[]) throws IOException {
        HashMapDesign hashMapDesign = new HashMapDesign();
        hashMapDesign.put(1, 1);
        hashMapDesign.put(2, 2);
        System.out.println("Value is : " + hashMapDesign.get(1));
        System.out.println("Value is : " + hashMapDesign.get(3));
        System.out.println("Value is : " + hashMapDesign.get(2));
        hashMapDesign.put(2, 1);
        System.out.println("Value is : " + hashMapDesign.get(2));
        hashMapDesign.remove(2);
        System.out.println("Value is : " + hashMapDesign.get(2));
    }

}
