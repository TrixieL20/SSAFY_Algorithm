import java.util.*;

class Solution {
    private boolean[][] map;
    private int min = Integer.MAX_VALUE;
    
    public int bfs(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(1); // 노드 1부터 bfs 탐색하여 연결된 노드만 탐색
        
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        
        int count = 0;
        while(!q.isEmpty()) {
            int curr = q.poll();
            count++; // 연결된 노드 카운트
      
            for(int i = 1; i <= n; i++) {
                if(map[curr][i] && !visited[i]) {
                    q.offer(i);
                    visited[curr] = true;
                }
            }
        }
        return count;
    }
    public int solution(int n, int[][] wires) {
        map = new boolean[n + 1][n + 1];

        // 간선 연결
        for(int[] w: wires) {
            map[w[0]][w[1]] = true;
            map[w[1]][w[0]] = true;
        }
        
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                // 간선이 존재할 때
                // 해당 연결을 끊고 bfs 탐색 후 재연결
                if(map[i][j]) {
                    map[i][j] = false;
                    map[j][i] = false;
                    
                    int result = bfs(n);
                    
                    min = Math.min(min, Math.abs((n - result) - result));
                   
                    map[i][j] = true;
                    map[j][i] = true;
                }
            }
        }
        return min;
    }
}
