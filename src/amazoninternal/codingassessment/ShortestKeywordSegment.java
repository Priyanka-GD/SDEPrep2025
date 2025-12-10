package amazoninternal.codingassessment;

import java.util.*;
/*
* Problem Statement
* Given a document and a set of keywords, find the shortest segment of the document containing all of the keywords.
Given a document and a query made up keywords of document, find the shortest segment of the document containing all of the keywords. author: tsierek
Milestones:
	•	API. Are the keywords an array or set? Do you return an array of words, a string containing them, or the word index and length of the match?
	•	Get basic windowing approach
	•	Working code
	•	Optimized code for minimal iterations through words and using maps as necessary (bar raising)
	•	Test and Debug
* */

public class ShortestKeywordSegment {

    static class Result {
        int start;
        int end;
        List<String> segment;

        Result(int start, int end, List<String> segment) {
            this.start = start;
            this.end = end;
            this.segment = segment;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "start=" + start +
                    ", end=" + end +
                    ", segment=" + segment +
                    '}';
        }
    }

    // Convenience wrapper: takes document as a single string
    public static Result findShortestSegment(String document, Set<String> keywords) {
        if (document == null || document.isEmpty()) return null;
        List<String> words = Arrays.asList(document.split("\\s+"));
        return findShortestSegment(words, keywords);
    }

    // Core logic on list of words
    public static Result findShortestSegment(List<String> document, Set<String> keywords) {
        if (document == null || document.isEmpty() || keywords == null || keywords.isEmpty()) {
            return null;
        }

        int n = document.size();
        Map<String, Integer> windowFreq = new HashMap<>();

        int required = keywords.size();
        int covered = 0;

        int left = 0;
        int bestStart = -1;
        int bestEnd = -1;
        int minLen = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            String word = document.get(right);

            if (keywords.contains(word)) {
                windowFreq.put(word, windowFreq.getOrDefault(word, 0) + 1);
                if (windowFreq.get(word) == 1) {
                    // first time this keyword appears in the window
                    covered++;
                }
            }

            // Try to shrink from left while we still cover all keywords
            while (covered == required && left <= right) {
                int currentLen = right - left + 1;
                if (currentLen < minLen) {
                    minLen = currentLen;
                    bestStart = left;
                    bestEnd = right;
                }

                String leftWord = document.get(left);
                if (keywords.contains(leftWord)) {
                    int count = windowFreq.get(leftWord) - 1;
                    windowFreq.put(leftWord, count);
                    if (count == 0) {
                        // lost one keyword from the window
                        covered--;
                    }
                }
                left++;
            }
        }

        if (bestStart == -1) {
            // No segment contains all keywords
            return null;
        }

        List<String> segment = document.subList(bestStart, bestEnd + 1);
        return new Result(bestStart, bestEnd, new ArrayList<>(segment));
    }

    // Basic testing in main
    public static void main(String[] args) {
        String document = "newUserLogin john newUserLogin jeff newUserLogin jeff " +
                "getOldestOneTimeVisitingUser newUserLogin chriss newUserLogin john " +
                "newUserLogin adam newUserLogin sandy getOldestOneTimeVisitingUser";

        Set<String> keywords = new HashSet<>(Arrays.asList("john", "chriss", "adam"));

        Result res = findShortestSegment(document, keywords);
        if (res != null) {
            System.out.println("Shortest segment: " + res.segment);
            System.out.println("Start index: " + res.start + ", End index: " + res.end);
        } else {
            System.out.println("No segment contains all keywords.");
        }

        // Simpler example
        String doc2 = "this is a test document and this document is for test";
        Set<String> kw2 = new HashSet<>(Arrays.asList("this", "test", "document"));

        Result res2 = findShortestSegment(doc2, kw2);
        if (res2 != null) {
            System.out.println("Shortest segment 2: " + res2.segment);
            System.out.println("Start index: " + res2.start + ", End index: " + res2.end);
        } else {
            System.out.println("No segment contains all keywords.");
        }
    }
}

