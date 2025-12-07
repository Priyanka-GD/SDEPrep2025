package uber;

import java.util.*;

public class EulerianCircuit {
    private static HashMap<String, LinkedList<String>> flightMap = new HashMap<>();
    private static LinkedList<String> result = new LinkedList<>();

    public static void main(String[] args) {
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("MUC", "LHR"),
                Arrays.asList("JFK", "MUC"),
                Arrays.asList("SFO", "SJC"),
                Arrays.asList("LHR", "SFO"),
                Arrays.asList("LHR", "XYZ")
        );


        List<String> itinerary = findItinerary(tickets);
        System.out.println(itinerary);
    }

    public static List<String> findItinerary(List<List<String>> tickets) {
        // Step 1: Build the graph first
        for (List<String> ticket : tickets) {
            String origin = ticket.get(0);
            String dest = ticket.get(1);
            if (flightMap.containsKey(origin)) {
                LinkedList<String> destList = flightMap.get(origin);
                destList.add(dest);
            } else {
                LinkedList<String> destList = new LinkedList<>();
                destList.add(dest);
                flightMap.put(origin, destList);
            }
        }

        // Step 2: Order the destinations
        flightMap.forEach((key, value) -> Collections.sort(value));

        // Step 3: Post-order DFS
        DFS("JFK");
        return result;
    }

    protected static void DFS(String origin) {
        // Visit all the outgoing edges first
        if (flightMap.containsKey(origin)) {
            LinkedList<String> destList = flightMap.get(origin);
            while (!destList.isEmpty()) {
                // While we visit the edge, we trim it off from the graph
                String dest = destList.pollFirst();
                DFS(dest);
            }
        }
        // Add the airport to the head of the itinerary
        result.offerFirst(origin);
    }
}
