import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
        LinkedList<int[]> q = new LinkedList<>();
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        q.add(new int[]{0, 0, 1});
        maps[0][0] = -1;
        
        while(!q.isEmpty())
        {
            int[] cur = q.poll();
            for(int i = 0; i < 4; i++)
            {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx < 0 || nx >= maps.length || ny < 0 || ny >= maps[0].length || maps[nx][ny] != 1)
                {
                    continue;
                }
                maps[nx][ny] = cur[2] + 1;
                q.offer(new int[]{nx, ny, maps[nx][ny]});        
            }
        }
        
        if(maps[maps.length - 1][maps[0].length - 1] == 1) return - 1; 
        return maps[maps.length - 1][maps[0].length - 1];
    }
}