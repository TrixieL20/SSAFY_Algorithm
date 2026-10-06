import java.util.*;
class Solution {
    private int[] parent;
    public int find(int a) {
        if(parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }

    public void union(int parent1, int parent2) {
        if(parent1 != parent2) {
            parent[parent2] = parent1;
        }
    }

    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);

        //Kruskal Algorithm
        for(int i = 0; i < costs.length; i++) {
            int parent1 = find(costs[i][0]);
            int parent2 = find(costs[i][1]);

            if(parent1 != parent2) {
                union(parent1, parent2);
                answer += costs[i][2];
            }
        }
        return answer;
    }
}