package amazonoa;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaximumSimilarity {

    public static int getMaxEqualIndices (int[] inv1, int[] inv2) {
        int equalCount = 0;
        int len = inv1.length;
        int positiveCnt = 0, negativeCnt = 0;
        Deque<int[]> deque = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            if (inv1[i] == inv2[i]) {
                equalCount++;
            } else {
                int diff = inv1[i] - inv2[i];
                if (diff > 0) {
                    deque.addFirst(new int[]{diff, i});
                    positiveCnt++;
                } else {
                    deque.addLast(new int[]{diff, i});
                    negativeCnt++;
                }
            }
        }
        if (positiveCnt == 0 || negativeCnt == 0)
            return equalCount;

        while (!deque.isEmpty() && deque.size() > 1) {
            int[] first = deque.removeFirst();
            int[] last = deque.removeLast();

            last[0] += 1;
            first[0] -= 1;

            if (first[0] > 0) {
                deque.addFirst(first);
            } else if (first[0] == 0) {
                equalCount++;
            }
            if (last[0] < 0) {
                deque.addLast(last);
            } else if (last[0] == 0) {
                equalCount++;
            }
        }
        return equalCount;
    }

    public static void main (String[] args) {
        //int[] inv1 = {2, 4, 1};
        //int[] inv2 = {1, 2, 3};
        //[-1, -2, 2]
        //[0, 2, 0]
        int[] inv1 = {1, 1};
        int[] inv2 = {1, 1};
        int result = getMaxEqualIndices(inv1, inv2);
        System.out.println("Maximum equal indices: " + result);
    }
}
