package amazonoa;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Packages {
    public int[] minPackage(int[] packages, int k) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((pair1, pair2) -> (pair1.weight - pair2.weight));

        for(int idx = 0; idx < k; idx++){
            minHeap.add(new Pair(packages[idx], idx));
        }
        //{1 , 2}, {2, 0}, {3, 1}
        result.add(minHeap.peek().weight);
        int index = 1;

        for(int idx = k; idx < packages.length; idx++){

            while(!minHeap.isEmpty() && minHeap.peek().idx < (idx - k + 1)){
                minHeap.poll();
            }
            minHeap.add(new Pair(packages[idx], idx));
            result.add(minHeap.peek().weight);
        }
        int arr[] = result.stream().mapToInt(Integer::intValue).toArray();
        return arr;
    }

//[1,1,1,1,3,3,0,0]

    class Pair {
        int weight;
        int idx;

        public Pair(int weight, int idx){
            this.weight = weight;
            this.idx = idx;
        }
    }
}
