import java.util.*;

class Solution {
    private final int n = 5;
    private final int[] moveX = {1, -1, 0, 0};
    private final int[] moveY = {0, 0, 1, -1};
    private boolean[][] visited;
    private Queue<int[]> q;
    
    
    public boolean bfs(String[] place, int a, int b) {
        q = new LinkedList<>();
        q.offer(new int[] {a, b, 0});
        
        visited = new boolean[n][n];
        visited[a][b] = true;
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
           
            if(curr[2] == 2) continue;
            
            for(int i = 0; i < moveX.length; i++) {
                int nextX = curr[0] + moveX[i];
                int nextY = curr[1] + moveY[i];
                
                if(nextX < 0 || nextY < 0 || nextX >= n || nextY >= n) continue;
                
                if(visited[nextX][nextY]) continue;
                if(place[nextX].charAt(nextY) == 'X') continue;
                
                if(place[nextX].charAt(nextY) == 'P') return false;
                
                q.offer(new int[] {nextX, nextY, curr[2] + 1});
                visited[nextX][nextY] = true;
            }
        }
        return true;
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        int k = 0;
        for(String[] place: places) {
            
            answer[k] = 1;
            
            for(int i = 0; i < place.length; i++) {
                for(int j = 0; j < place[i].length(); j++) {
                    if(place[i].charAt(j) == 'P') {
                        if(!bfs(place, i, j)) {
                            answer[k] = 0;
                            break;
                        }
                    }
                }
                if(answer[k] == 0) break;
            }
            k++;
        }
        
        return answer;
    }
}