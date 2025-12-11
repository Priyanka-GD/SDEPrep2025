package amazoninternal.codingassessment;

import java.util.*;

public class PackageManager {
    public static void main(String args[]) {
        Map<Character, List<Character>> mapOfDependentProject = new HashMap<>();
        mapOfDependentProject.put('A', Arrays.asList('B', 'C'));
        mapOfDependentProject.put('B', Arrays.asList('D', 'E', 'F'));
        mapOfDependentProject.put('C', List.of('F'));
        mapOfDependentProject.put('F', List.of('G'));
        List<Character> packagesList = getPackageInstallationList(mapOfDependentProject);
        System.out.println("All packages installed : " + (packagesList.size() == 7));
        System.out.println("All packages installed : " + packagesList.toString());
    }

    private static List<Character> getPackageInstallationList(Map<Character, List<Character>> mapOfDependentProject) {
        Set<Character> nodes = new HashSet<>();
        for (char key : mapOfDependentProject.keySet()) {
            nodes.add(key);
            nodes.addAll(mapOfDependentProject.get(key));
        }

        Map<Character, Integer> inDegree = new HashMap<>();
        Map<Character, List<Character>> graph = new HashMap<>();

        for (char node : nodes) {
            graph.put(node, new ArrayList<>());
            inDegree.put(node, 0);
        }

        for (Map.Entry<Character, List<Character>> entry : mapOfDependentProject.entrySet()) {
            char pkg = entry.getKey();
            for (char dep : entry.getValue()) {
                graph.get(dep).add(pkg);
                inDegree.put(pkg, inDegree.get(pkg) + 1);
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for (char node : nodes) {
            if (inDegree.get(node) == 0) {
                queue.add(node);
            }
        }
        List<Character> order = new ArrayList<>();


        while (!queue.isEmpty()) {
            char currPack = queue.poll();
            order.add(currPack);
            for (char dependent : graph.get(currPack)) {
                inDegree.put(dependent, inDegree.get(dependent) - 1);
                if (inDegree.get(dependent) == 0) {
                    queue.add(dependent);
                }
            }
        }
        return order;
    }
}
