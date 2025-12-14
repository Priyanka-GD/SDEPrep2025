package amazoninternal.codingassessment;

import java.util.*;
/*
* Time: O(R × L + K log F)
Space: O(F)
F = number of possible features
R = number of feature requests
L = average number of words per request
K = topFeatures (features to return)

* Problem: Top Feature Requests

You are given a list of possible features for a product and a list of feature requests made by users.
Each feature request is a string describing what the user wants.

Your task is to determine the top K most frequently requested features.

Rules

A feature is considered requested if its name appears as a substring in a feature request (case-insensitive).

If a feature appears multiple times in the same request, it should be counted only once for that request.

Rank features by the number of distinct requests they appear in.

If two features have the same count, break ties by lexicographical (alphabetical) order.

Return the top K features.

Input

numFeatures: Integer — total number of possible features

topFeatures: Integer — number of top features to return

possibleFeatures: List of strings — all possible feature names

numFeatureRequests: Integer — number of user requests

featureRequests: List of strings — user feature request sentences
* */

public class TopFeatures {

    public static List<String> getTopNFeatures(
            int topFeatures,
            List<String> possibleFeatures,
            List<String> featureRequests
    ) {
        // O(1) lookup set
        Set<String> possibleSet = new HashSet<>();
        for (String f : possibleFeatures) possibleSet.add(f.toLowerCase());

        Map<String, Integer> freq = new HashMap<>();

        for (String request : featureRequests) {
            // normalize: lowercase + remove punctuation -> spaces
            String cleaned = request.toLowerCase().replaceAll("[^a-z ]", "");
            String[] words = cleaned.split("\\s+");

            Set<String> seenInThisRequest = new HashSet<>();
            for (String w : words) {
                if (possibleSet.contains(w)) {
                    seenInThisRequest.add(w);
                }
            }

            for (String w : seenInThisRequest) {
                freq.put(w, freq.getOrDefault(w, 0) + 1);
            }
        }

        // max-heap: higher freq first; if tie, lexicographically smaller first
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int fa = freq.get(a), fb = freq.get(b);
            if (fa != fb) return Integer.compare(fb, fa); // desc freq
            return a.compareTo(b); // asc alphabetical
        });

        pq.addAll(freq.keySet());

        List<String> result = new ArrayList<>();
        while (!pq.isEmpty() && result.size() < topFeatures) {
            result.add(pq.poll());
        }
        return result;
    }

    // ===== TEST =====
    public static void main(String[] args) {
        int numFeatures = 6;
        int topFeatures = 2;

        List<String> possibleFeatures = Arrays.asList(
                "storage", "battery", "hover", "alexa", "waterproof", "solar"
        );

        List<String> featureRequests = Arrays.asList(
                "I wish my Kindle had even more storage!",
                "I wish the battery life on my Kindle lasted 2 years.",
                "I read in the bath and would enjoy a waterproof Kindle",
                "Waterproof and increased battery are my top two requests.",
                "I want to take my Kindle into the shower. Waterproof please waterproof!",
                "It would be neat if my Kindle would hover on my desk when not in use.",
                "How cool would it be if my Kindle charged in the sun via solar power?"
        );

        List<String> ans = getTopNFeatures(topFeatures, possibleFeatures, featureRequests);
        System.out.println(ans); // expected: [waterproof, battery]
    }
}
