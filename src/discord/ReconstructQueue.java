package discord;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/queue-reconstruction-by-height/

public class ReconstructQueue {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);

        List<int[]> ordered = new LinkedList<>();
        for (int[] p : people)
            ordered.add(p[1], p);

        return ordered.toArray(new int[people.length][2]);
    }
}
/*
Input: people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
Output: [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
Input: people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
Output: [[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
*/