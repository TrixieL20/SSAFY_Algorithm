import java.util.*;



class Solution {
    
    int[] dxs = {1,-1,0,0};
    int[] dys = {0,0,1,-1};
    int C ;
    int R;
   
    
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        C = game_board.length;
        R = game_board[0].length;
        
        List<int[][]> blanks = extract(game_board,0);
        List<int[][]> pieces = extract(table,1);

        boolean[] used = new boolean[pieces.size()];

        for(int[][] blank : blanks){
            answer += compare(blank,pieces,used);
        }
        return answer;
    }
    
    public int compare(int[][] blank, List<int[][]> pieces, boolean[] used){
                
        // 이번 빈칸에 대해 피스들이 같은지 비교
        for(int p=0; p<pieces.size(); p++){
            
            if(used[p]) continue;
            
            int[][] piece = pieces.get(p);

            // 크기 다르면 다음 피스로 건너뛰기
            if(piece.length*piece[0].length != blank.length*blank[0].length ) continue;
            
            // 2차원 배열 같은지 비교
            
            for(int rot =0; rot<4; rot++){
                if(Arrays.deepEquals(blank,piece)){
                    used[p] = true; // 사용처리
                    int cnt =0;
                    // 이번 빈칸을 채우는 칸 수
                    for(int[] row : piece){
                        for(int e : row){
                            cnt+=e;
                        }
                    }

                    return cnt;

                }
                // 퍼즐 조각 회전
                piece = rotate(piece);
            }
            
        }
        
      return 0;
    }
    
    public int[][] rotate(int[][] g) {
        int h = g.length, w = g[0].length;
        int[][] r = new int[w][h];            // 가로세로 뒤바뀜
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++)
                r[j][h - 1 - i] = g[i][j];
        return r;
    }   
    
    // 격자 따기
    public List<int[][]> extract(int[][] board, int target){
        
        Deque<int[]> q = new ArrayDeque<>();
        
        List<int[][]> extracted = new ArrayList<>();
        
        // 방문 표시를 위해 0과 1을 바꿔서 사용(game_board는 0이 빈칸, table은 1이 퍼즐 조각)
       int visitedMark = 1 - target;
       
        for(int j=0 ;j<C;j++){
            for(int i=0; i<R; i++){
                if(board[j][i]!=target) continue;
                
                q.offer(new int[]{i,j});
                List<int[]> tmpBlanksInfo = new ArrayList<>();
        
                board[j][i] = visitedMark;
                
                int maxX = Integer.MIN_VALUE;
                int maxY = Integer.MIN_VALUE;

                int minX = Integer.MAX_VALUE;
                int minY = Integer.MAX_VALUE;
                
                while(!q.isEmpty()){
                    int[] polled = q.poll();
                    
                    int curX = polled[0];
                    int curY = polled[1];
                    
                    maxX = Math.max(curX,maxX);
                    maxY = Math.max(curY,maxY);
                    
                    minX = Math.min(curX,minX);
                    minY = Math.min(curY,minY);
                    
                    
                    tmpBlanksInfo.add(new int[]{curX,curY});
                    
                    for(int dir=0; dir<4;dir++){
                        int nx = curX+dxs[dir];
                        int ny = curY+dys[dir];
                        
                        if(canGo(nx,ny) && board[ny][nx]==target){
                            board[ny][nx] = visitedMark;
                            q.offer(new int[]{nx,ny});
                            
                        }
                    }
                }
                
                int[][] tmpGrid = new int[maxY-minY+1][maxX-minX+1];
                
                for(int[] pos : tmpBlanksInfo){
                    tmpGrid[pos[1]-minY][pos[0]-minX] = 1; 
                }
                
                extracted.add(tmpGrid);
            }
        }// end of격자 따기
        
        return extracted;
    }
        
    
    
    public boolean canGo(int x, int y){
        if(x>=R || x<0 | y>=C || y<0)
            return false;
        return true;
    }
    
}