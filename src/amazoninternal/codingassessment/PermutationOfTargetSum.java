package amazoninternal.codingassessment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
* QUESTION 1 – Lexicographically Smallest Signed Permutation
📘 Problem Statement

You are given two integers:
size (n): number of elements
target_sum: required sum of elements

You must construct an integer array arr of length size such that:
The sum of all elements equals target_sum
The absolute values of the elements form a permutation of {1, 2, ..., size}

Among all valid arrays, return the lexicographically smallest one
If it is not possible, return an array of size size filled with 0

📌 Notes
A permutation contains every integer from 1 to size exactly once
Lexicographical order is decided by the first differing index

🧪 Example 1
Input
size = 5
target_sum = 9

Valid sequences
[-1, -2, 3, 4, 5] → sum = 9
[-3, 1, 2, 4, 5] → sum = 9

Output
[-3, 1, 2, 4, 5]
✔ Lexicographically smallest

🧪 Example 2
Input
size = 4
target_sum = -2

Output
[-4, -2, 1, 3]

🧪 Example 3 (Impossible Case)
Input
size = 3
target_sum = 5

Output
[0, 0, 0]
❌ No valid combination exists
* */
public class PermutationOfTargetSum {
    static List<List<Integer>> combinations = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(getCombinations(5, 9));    // expected: [-3, 1, 2, 4, 5]
        System.out.println(getCombinations(4, -2));   // expected: [-4, -2, 1, 3]
        System.out.println(getCombinations(3, 6));    // expected: [1, 2, 3]
        System.out.println(getCombinations(3, -6));   // expected: [-3, -2, -1]
        System.out.println(getCombinations(3, 5));    // expected: [0, 0, 0]
        System.out.println(getCombinations(4, 1));    // expected: [0, 0, 0, 0]
        System.out.println(getCombinations(1, 1));    // expected: [1]
        System.out.println(getCombinations(1, -1));   // expected: [-1]
        System.out.println(getCombinations(2, 1));    // expected: [-2, 1]
    }


    public static List<Integer> getCombinations(int size, int targetSum) {
        combinations.clear();

        boolean[] usedAbs = new boolean[size + 1];
        backtrack(0, size, targetSum, new ArrayList<>(), 0, usedAbs);

        if (combinations.isEmpty()) {
            return new ArrayList<>(Collections.nCopies(size, 0));
        }

        combinations.sort((a, b) -> {
            for (int i = 0; i < a.size(); i++) {
                int cmp = Integer.compare(a.get(i), b.get(i));
                if (cmp != 0) return cmp;
            }
            return 0;
        });

        return combinations.get(0);
    }

    // CHANGED: recurse by position, not num range
    public static void backtrack(
            int pos,
            int size,
            int targetSum,
            List<Integer> combination,
            int currSum,
            boolean[] usedAbs
    ) {
        if (pos == size) {
            if (currSum == targetSum) {
                combinations.add(new ArrayList<>(combination)); // COPY
            }
            return;
        }

        // lexicographic order: negatives first
        for (int num = size; num >= 1; num--) {
            if (!usedAbs[num]) {
                // try -num
                usedAbs[num] = true;
                combination.add(-num);
                backtrack(pos + 1, size, targetSum, combination, currSum - num, usedAbs);
                combination.remove(combination.size() - 1);

                // try +num
                combination.add(num);
                backtrack(pos + 1, size, targetSum, combination, currSum + num, usedAbs);
                combination.remove(combination.size() - 1);
                usedAbs[num] = false;
            }
        }
    }
}
//
// TC - 2^n * n!
//SC O(M * n)
// Worstcase -  2^n * n!