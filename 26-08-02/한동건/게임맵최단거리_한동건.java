import java.util.*;

class Solution {
      // 동서남북
    int[] dx = {0,0,1,-1};
    int[] dy = {1,-1,0,0};
    
    public boolean is_in_range(int x, int y, int[][] ary){
        int row = ary.length;
        int col = ary[0].length;
        
        if(x>=0 && y>=0 && x<row && y<col && ary[x][y] == 1){
            return true;
        }
        return false;
        
    }
    
    
    public int solution(int[][] maps) {
        int col = maps[0].length;
        int row = maps.length;
        
        
        Queue<int[]> que = new ArrayDeque<>();
        
        
//         int[][] visited = new int[row][col];
        
//         for(int i=0; i<row; i++){
//             visited[i] = Arrays.copyOf(maps[i], col);
//         }
        
        que.offer(new int[]{0,0,1});
        maps[0][0]=0;
        
        while(!que.isEmpty()){
            int[] cur = que.poll();
            int cur_x = cur[0];
            int cur_y = cur[1];
            
            if(cur_x == row-1 && cur_y == col-1){
                return cur[2]; 
                
            }
            
//             visited[cur_x][cur_y] = 0;
            
            
            for(int i=0; i<4; i++){
                if(is_in_range(cur_x+dx[i], cur_y+dy[i], maps)){
                    maps[cur_x+dx[i]][cur_y+dy[i]] = 0;
                    que.offer(new int[]{cur_x+dx[i], cur_y+dy[i], cur[2]+1});
                }
            }   
        }
        return -1;   
        
    }
}
