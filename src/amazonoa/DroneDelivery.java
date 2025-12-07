package amazonoa;

import java.util.*;

public class DroneDelivery {
    public static int minReplacements(int[] pack) {
        if (pack.length == 0) {
            return 0;
        }

        // Collect all unique elements in odd and even positions
        Set<Integer> oddElements = new HashSet<>();
        Set<Integer> evenElements = new HashSet<>();

        for (int i = 0; i < pack.length; i++) {
            if (i % 2 == 0) {
                oddElements.add(pack[i]);
            } else {
                evenElements.add(pack[i]);
            }
        }

        // If there's only one package, no replacements needed
        if (pack.length == 1) {
            return 0;
        }

        // Find the maximum in odd and even positions to consider as potential drone capacities
        int maxOdd = 0;
        int maxEven = 0;

        for (int i = 0; i < pack.length; i++) {
            if (i % 2 == 0) {
                if (pack[i] > maxOdd) {
                    maxOdd = pack[i];
                }
            } else {
                if (pack[i] > maxEven) {
                    maxEven = pack[i];
                }
            }
        }

        // Consider the top candidates from odd and even positions to minimize replacements
        List<Integer> oddCandidates = new ArrayList<>(oddElements);
        List<Integer> evenCandidates = new ArrayList<>(evenElements);

        // Also consider the maximums as they might be the best candidates
        oddCandidates.add(maxOdd);
        evenCandidates.add(maxEven);

        int minReplacements = Integer.MAX_VALUE;

        // Check all possible pairs of odd and even candidates
        for (int odd : oddCandidates) {
            for (int even : evenCandidates) {
                int replacements = 0;
                boolean possible = true;

                for (int i = 0; i < pack.length; i++) {
                    if (i % 2 == 0) {
                        if (pack[i] > odd) {
                            replacements++;
                        }
                    } else {
                        if (pack[i] > even) {
                            replacements++;
                        }
                    }

                    if (replacements >= minReplacements) {
                        possible = false;
                        break;
                    }
                }

                if (possible && replacements < minReplacements) {
                    minReplacements = replacements;
                }
            }
        }

        return minReplacements;
    }

    public static void main(String[] args) {
        int[] pack1 = {3, 1, 3, 2};
        System.out.println(minReplacements(pack1)); // Output: 1

        int[] pack2 = {1, 3, 2, 3};
        System.out.println(minReplacements(pack2)); // Output: 1

        int[] pack3 = {1, 1, 1, 1};
        System.out.println(minReplacements(pack3)); // Output: 0

        int[] pack4 = {5, 5, 5, 5, 5};
        System.out.println(minReplacements(pack4)); // Output: 2
    }
}
