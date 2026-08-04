import java.util.*;
public class 게임맵최단거리_황윤주 {
	

	class Solution {
	    // 위 아래 왼쪽 오른쪽 이동
	    private static int[] dr = {-1,1,0,0};
	    private static int[] dc = {0,0,1,-1};
	    
	    public int solution(int[][] maps) {
	        int n = maps.length;
	        int m = maps[0].length;
	        // 거리 저장
	        int[][] distance = new int[n][m];
	        
	        Queue<int[]> q = new LinkedList<>();
	        
	        q.offer(new int[]{0,0});
	        boolean[][] visited = new boolean[n][m];
	        visited[0][0] = true;
	        distance[0][0] = 1;
	        
	        while(!q.isEmpty()){
	            int[] cur = q.poll();
	            
	            int r = cur[0];
	            int c = cur[1];
	            
	            for(int d=0; d<4; d++){
	                int nr = r + dr[d];
	                int nc = c + dc[d];
	                
	                if(nr<0 || nc<0 || nr>=n || nc >=m) continue;
	                if(maps[nr][nc]==0) continue;
	                
	                if(visited[nr][nc]) continue;
	                visited[nr][nc] = true;
	                distance[nr][nc] = distance[r][c]+1;
	                q.offer(new int[]{nr,nc});
	                
	                
	            }
	        }
	        
	        if(distance[n-1][m-1]==0){
	                    return -1;
	                }
	        
	        return distance[n-1][m-1];
	        
	    }
	}
}
