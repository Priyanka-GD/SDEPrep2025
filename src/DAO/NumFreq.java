package DAO;

public class NumFreq implements Comparable<NumFreq> {
    public int number;
    int freq;

    public NumFreq(int number) {
        this.number = number;
        this.freq = 1;
    }

    public void updateFreq() {
        this.freq++;
    }

    @Override
    public int compareTo(NumFreq other) {
        return other.freq - this.freq;
    }
}