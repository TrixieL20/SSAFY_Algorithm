import java.util.*;

class Solution {
    static int n;
    final int[] mx = {1, -1, 0, 0};
    final int[] my = {0, 0, 1, -1};
    
    // 보드의 빈 공간 모양, 퍼즐의 모양 인덱스를 탐색 (bfs)
    private List<List<int[]>> extract(int[][] board, int type) {
        boolean[][] visited = new boolean[n][n];
        List<List<int[]>> shapes = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] != type || visited[i][j]) continue;
                
                List<int[]> shape = new ArrayList<>();
                Queue<int[]> q = new LinkedList<>();
                
                q.offer(new int[] {i, j});
                visited[i][j] = true;
                
                while(!q.isEmpty()) {
                    int[] curr = q.poll();
                    shape.add(curr); // 퍼즐 모양 or 퍼즐 공간 인덱스 추가
                    
                    for(int d = 0; d < 4; d++) {
                        int nx = curr[0] + mx[d];
                        int ny = curr[1] + my[d];
                        
                        if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                        if(visited[nx][ny] || board[nx][ny] != type) continue;
                        
                        visited[nx][ny] = true;
                        q.offer(new int[] {nx, ny});
                    }
                }
                normalize(shape); // shape 정규화([0, 0] 기준)
                shapes.add(shape); // 정규화된 shape을 shapes 리스트에 추가
            }
        }
        return shapes;
    }
    
    // 퍼즐을 [0, 0]을 기준으로 정규화
    private List<int[]> normalize(List<int[]> shape) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        
        // 제일 작은 열, 행 번호를 찾아서 저장
        // [5, 3], [5, 4], [6, 3]이 shape에 저장 돼 있다면
        // minX는 5, minY는 3이 된다
        for(int[] cell: shape) {
            minX = Math.min(minX, cell[0]);
            minY = Math.min(minY, cell[1]);
        }
        
        // minX, minY를 이용해 정규화 하면
        // [0, 0], [0, 1], [1, 0]이 됨
        for(int[] cell: shape) {
            cell[0] -= minX;
            cell[1] -= minY;
        }
        return shape;
    }
    
    // 정규화된 shape 인덱스 정렬
    public String makeKey(List<int[]> shape) {
        List<String> coords = new ArrayList<>();
        
        for(int[] cell: shape) {
            coords.add(cell[0] + " " + cell[1]);
        }
        
        Collections.sort(coords);
        return String.join("/", coords);
    }
    
    // 퍼즐 회전
    public List<int[]> rotate(List<int[]> shape) {
        List<int[]> rotated = new ArrayList<>();
        
        for(int[] cell: shape) {
            rotated.add(new int[] {cell[1], -cell[0]});
        }
        
        normalize(rotated);
        return rotated;
    }
    
    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;
        
        List<List<int[]>> blanks = extract(game_board, 0); // 빈 공간
        List<List<int[]>> pieces = extract(table, 1); // 퍼즐 조각
        
        boolean[] used = new boolean[pieces.size()];
        int answer = 0;
        
        for(List<int[]> blank: blanks) {
            String blankKey = makeKey(blank);
            
            for(int i = 0; i < pieces.size(); i++) {
                if(used[i]) continue;
                
                List<int[]> piece = pieces.get(i);
                boolean matched = false;
                
                // 퍼즐 조각을 90도로 회전을 반복하며 사용 가능한지 확인
                for(int rot = 0; rot < 4; rot++) {
                    if(makeKey(piece).equals(blankKey)) {
                        matched = true;
                        break;
                    }
                    piece = rotate(piece);
                }
                
                // 보드의 공간과 가지고 있는 퍼즐 조각이 매치가 된다면
                // 해당 조각을 사용했음을 표시하고 빈 공간만큼 answer에 더하기
                if(matched) {
                    used[i] = true;
                    answer += blank.size();
                    break;
                }
            }
        }
        return answer;
    }
}
