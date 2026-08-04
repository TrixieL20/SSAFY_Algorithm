import java.util.*;

class Solution {
    private final int[] moveX = {1, -1, 0, 0};
    private final int[] moveY = {0, 0, 1, -1};
    
    public int bfs(int[][] maps, int n, int m) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 1});
        
        int len = maps.length;
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            
            if(curr[0] == n - 1 && curr[1] == m - 1) {
                return curr[2];
            }
            
            for(int i = 0; i < 4; i++) {
                int nextX = curr[0] + moveX[i];
                int nextY = curr[1] + moveY[i];
                
                if((nextX >= 0 && nextX < n) 
                   && (nextY >= 0 && nextY < m) 
                   && maps[nextX][nextY] != 0
                   && !visited[nextX][nextY]) {
                    q.offer(new int[]{nextX, nextY, curr[2] + 1});
                    visited[nextX][nextY] = true;
                }
            }
        }
        return -1;
    }
    
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        System.out.println(n + " " + m);
        int answer = bfs(maps, n, m);
        return answer;
    }
}