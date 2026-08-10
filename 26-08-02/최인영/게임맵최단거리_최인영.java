import java.util.*;

public class 게임맵최단거리_최인영 {
    public int solution(int[][] maps) {     
        int n = maps[0].length;
        int m = maps.length;
        
        boolean[][] visited = new boolean[m][n];
        
        Queue<int[]> queue = new ArrayDeque<>();
        
        int[] dir_x = {-1, 1, 0, 0};
        int[] dir_y = {0, 0, -1, 1};
        
        queue.add(new int[] {0, 0, 1});
        visited[0][0] = true;
        
        while(!queue.isEmpty()){
            int[] pos = queue.poll();
            int pos_x = pos[0];
            int pos_y = pos[1];
            
            if(pos_x == n-1 && pos_y == m-1) { return pos[2]; }
            
            for(int i = 0; i < 4; i++){
                int next_x = pos_x + dir_x[i];
                int next_y = pos_y + dir_y[i];
                
                if (next_x < 0 || next_x >= n || next_y < 0 || next_y >= m){
                    continue;
                }
                if(visited[next_y][next_x] == true){
                    continue;
                }
                if (maps[next_y][next_x] == 0) {
                    continue;
                }
                
                visited[next_y][next_x] = true;
                queue.add(new int[]{next_x, next_y, pos[2]+1});
            }
        }
        
        return -1;
    }
}