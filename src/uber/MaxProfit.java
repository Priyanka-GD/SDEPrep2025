package uber;

public class MaxProfit {
    public static void main(String[] args) {
        int[][] edges = {{1, 2, 2}, {2, 3, 2}, {2, 4, 3}, {1, 5, 4}};
        int n = 5;
        int result = findTheCity(n, edges);
        System.out.println("Minimum sum of selected elements: " + result); // Output: -96
    }

    public static int findTheCity(int n, int[][] edges) {
        int matrix[][] = new int[n + 1][n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.MAX_VALUE;

            }
        }

        for (int edge[] : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            matrix[from][to] = weight; // Distance along direct edge
            matrix[to][from] = weight; // Bidirectional connections
        }

        // Set diagonal elements to 0 (distance from a city to itself)
        for (int i = 0; i < n; i++)
            matrix[i][i] = 0;

        // Fill the intermediate matrix with values comparing with prior intermediate
        // matrices
        int distance = 0;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // Skip if either path is unknown
                    if (matrix[i][k] == Integer.MAX_VALUE || matrix[k][j] == Integer.MAX_VALUE)
                        continue;
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                    distance += matrix[i][j];
                }
            }
        }
        return 0;
    }
}
