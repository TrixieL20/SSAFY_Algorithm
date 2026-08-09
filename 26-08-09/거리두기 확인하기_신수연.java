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
           
            // 맨해튼 거리가 2인데 q에 적재 됐다는 건 이미 거리두기를 준수했다는 것
            // 따라서 탐색 X
            if(curr[2] == 2) continue;
            
            for(int i = 0; i < moveX.length; i++) {
                int nextX = curr[0] + moveX[i];
                int nextY = curr[1] + moveY[i];
                
                if(nextX < 0 || nextY < 0 || nextX >= n || nextY >= n) continue;
                
                // 이미 방문했거나 파티션이면 이동하지 않음
                if(visited[nextX][nextY]) continue;
                if(place[nextX].charAt(nextY) == 'X') continue;
                
                // 거리 2 이내에 다른 사람이 있으면 거리두기 실패 => false 리턴
                if(place[nextX].charAt(nextY) == 'P') return false;
                
                // 이외의 경우 탐색 필요 => q에 추가
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