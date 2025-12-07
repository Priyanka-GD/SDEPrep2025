package amazonoa;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
Your project team is collaborating with a group of software testers who have requested an array generator service to assist in testing software functionality.
Problem Statement
The service takes the following input parameters:

arr: An array of n positive integers.
state: A string of n characters, where:
'1' indicates that the corresponding arr[i] is available for selection.
'0' indicates that arr[i] is blocked initially.
m: The number of operations to perform.
Operations
To generate an integer sequence S, perform exactly m operations as follows:

Choose any available arr[i] (where state[i] = '1'). The same element can be chosen multiple times.
Append the selected arr[i] to S.
Update the state:
Any state[i] = '0' where state[i-1] = '1' is changed to '1'.
This means that blocked elements adjacent to available ones become available.
Objective
Find the lexicographically largest sequence S that can be obtained after exactly m operations.

Example
Input
arr = [10, 5, 7, 6]
state = "0101"
m = 2
Operations
Step	Available Elements	Chosen Element	Updated State
1	{5, 6}	6	"0111"
2	{5, 6, 7}	7	"0111"
Output
S = [6, 7]
Since [6, 7] is lexicographically larger than [5, 6] or [5, 5], this is the optimal solution.


Constraints
1 ≤ n ≤ 10^5
1 ≤ arr[i] ≤ 10^9
1 ≤ m ≤ 10^5

state is a binary string of length n.  List<Integer> arr = Arrays.asList(4,9,1,2,10);
String state = "00100";
int m = 4;
S = {1,2,10,10}
 */
public class ArrayGeneratorService {
    public static void main(String args[]) {
        int[] arr = {4, 9, 1, 2, 10};
        int m = 4;
        String state = "00100";
        List<Integer> result = generateSequence(arr, m, state);
        System.out.println(result);
    }

    private static List<Integer> generateSequence(int[] arr, int m, String state) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        List<Integer> answer = new ArrayList<>();

        for (int idx = 0; idx < arr.length; idx++) {
            if (state.charAt(idx) == '1')
                maxHeap.add(new int[]{arr[idx], idx});
        }
        for (int step = 1; step <= m; step++) {
            int[] ele = maxHeap.peek();
            int index = ele[1];
            int value = ele[0];
            answer.add(value);
            char[] states = state.toCharArray();
            if (index + 1 < arr.length && states[index + 1] == '0') {
                states[index + 1] = '1';
                maxHeap.add(new int[]{arr[index + 1], index + 1});
            }
            state = new String(states);
        }
        return answer;
    }
}
