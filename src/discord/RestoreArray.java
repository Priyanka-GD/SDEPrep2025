package discord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/restore-the-array-from-adjacent-pairs/description/
public class RestoreArray {
    Map<Integer, List<Integer>> map = new HashMap<>();

    public int[] restoreArray(int[][] adjacentPairs) {
        // We can't use List<List<Integer>> because we don't know number of elements
        for (int adjacentPair[] : adjacentPairs) {
            map.putIfAbsent(adjacentPair[0], new ArrayList<>());
            map.putIfAbsent(adjacentPair[1], new ArrayList<>());

            map.get(adjacentPair[0]).add(adjacentPair[1]);
            map.get(adjacentPair[1]).add(adjacentPair[0]);
        }
        int root = 0;
        for (int key : map.keySet()) {
            if (map.get(key).size() == 1) {
                root = key;
                break;
            }
        }
        int result[] = new int[map.size()];
        dfs(root, Integer.MAX_VALUE, result, 0);
        return result;
    }

    private void dfs(int node, int prev, int[] result, int idx) {
        result[idx] = node;
        for (int neighbor : map.get(node)) {
            if (neighbor != prev)
                dfs(neighbor, node, result, idx + 1);
        }
    }

}
