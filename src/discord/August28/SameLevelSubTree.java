package discord.August28;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/description/

public class SameLevelSubTree {
    static int label[] = new int[26];
    static boolean visited[];


    public static void main(String args[]) throws IOException {
        int n = 7;
        int[][] edges = {{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        String labels = "abaedcd";
        countSubTrees(n, edges, labels);
    }

    private static void countSubTrees(int n, int[][] edges, String labels) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int node = 0; node < n; node++)
            adjList.add(new ArrayList<>());


        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        visited = new boolean[n];
        int[] result = new int[n];
        getLabelsCount(0, adjList, labels, result);
        System.out.println(Arrays.toString(result));
    }

    private static void getLabelsCount(int nodeNum, List<List<Integer>> adjList, String labels, int[] result) {
        visited[nodeNum] = true;

        int currentLabel = labels.charAt(nodeNum) - 'a';

        int prevCount = label[currentLabel];

        for (int adjNode : adjList.get(nodeNum)) {
            if (!visited[adjNode])
                getLabelsCount(adjNode, adjList, labels, result);
        }

        label[currentLabel]++;
        result[nodeNum] = label[currentLabel] - prevCount;
    }
}
