package amazoninternal.codingassessment;

import java.util.*;

/*
* Buildable Packages / Package Dependency Graph
*
Problem Description
You are an engineer on the Builder Tools team at Amazon. Code across Amazon is split into distinct software packages,
where each package may depend on zero or more other packages to be successfully compiled and built.
You are provided with:
packages: An array of strings representing all unique package names.
dependencies: A 2D array of strings, where dependencies[i] contains the direct prerequisites needed to build packages[i].
available: A list of strings representing packages that have already been built (or are available in the repository).
A package can be built only if all of its direct dependencies have already been built.
Write a function to return a list of all packages that can now be built, ordered such that every package appears after all of its prerequisites.
*
* */
public class PackageDependency {
    public static void main(String args[]) {
        String[] packages = {"a", "b", "c", "d", "e", "f", "g"};

        String[][] dependencies = {
                {"b", "c"},
                {"g", "d"},
                {},
                {"e", "f"},
                {},
                {"d"},
                {}
        };
        System.out.println("Packages to be built : " + getPackagesToBeBuilt(packages, dependencies, List.of("g", "e", "f")));
    }

    private static List<String> getPackagesToBeBuilt(String[] packages, String[][] dependencies, List<String> available) {
        Map<String, List<String>> dependencyList = new HashMap<>();
        int len = packages.length;
        Map<String, Integer> dependencyCount = new HashMap<>();
        for (int idx = 0; idx < len; idx++) {
            String currPkg = packages[idx];
            dependencyCount.put(currPkg, dependencies[idx].length);
            for (String dependency : dependencies[idx]) {
                dependencyList.putIfAbsent(dependency, new ArrayList<>());
                dependencyList.get(dependency).add(currPkg);
            }
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        for (String pkg : available) {
            if (!visited.contains(pkg) && dependencyCount.containsKey(pkg) && dependencyCount.get(pkg) == 0) {
                queue.add(pkg);
                visited.add(pkg);
            }
        }
        List<String> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            String currPkg = queue.poll();
            result.add(currPkg);
            for (String nextPkg : dependencyList.getOrDefault(currPkg, new ArrayList<>())) {
                if (dependencyCount.containsKey(nextPkg)) {
                    dependencyCount.put(nextPkg, dependencyCount.get(nextPkg) - 1);
                    if (dependencyCount.get(nextPkg) == 0 && !visited.contains(nextPkg)) {
                        queue.add(nextPkg);
                        visited.add(nextPkg);
                    }
                }
            }
        }
        return result;
    }
}
/*
 * V be the number of total packages
 * E be the total number of direct dependency relationships across all packages.
 * TC and SC O(V + E)
 * BFS - Each package enters and leaves the BFS queue at most once, which takes
 * O(V) operations. When popping a package, we iterate over its outgoing edges in dependencyList to
 * decrement dependency counts. Across the entire traversal, every dependency edge is visited
 * at most once, taking O(E) time.
 *
 * Why BFS not DFS?
 *
 * For the Amazon Buildable Packages problem, BFS (Kahn's Algorithm) is preferred over DFS because
 * it operates naturally via forward propagation. Standard DFS traverses backwards from target nodes
 * and relies on post-order recursion stack unwinding, whereas BFS allows us to directly seed the queue
 * with all pre-compiled available packages as multi-source starting points. By continuously updating
 * and checking each package's in-degree (remaining prerequisite count), BFS guarantees that a package
 * enters the queue only after all of its prerequisites are satisfied. Furthermore, an iterative BFS
 * avoids recursion depth limits (StackOverflowError) on deep dependency chains while cleanly
 * modeling real-world parallel build waves.
 *
 * */