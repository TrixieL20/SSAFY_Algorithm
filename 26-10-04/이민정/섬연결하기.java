import java.util.*;

class Solution {
    private static int[] parent = null;

    private static int find(int element) {
        if (parent[element] == element) return element;

        return find(parent[element]);
    }

    private static void union(int parent1, int parent2) {
        if (parent1 != parent2) {
            parent[parent2] = parent1;
        }
    }

    public int solution(int n, int[][] costs) {
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        Arrays.sort(costs, (e1, e2) -> e1[2] - e2[2]);

        int answer = 0;

        for (int i = 0; i < costs.length; i++) {
            int parent1 = find(costs[i][0]);
            int parent2 = find(costs[i][1]);

            if (parent1 != parent2) {
                union(parent1, parent2);
                answer += costs[i][2];
            }
        }

        return answer;
    }
}