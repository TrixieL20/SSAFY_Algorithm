
import java.util.*;

class Solution {
    static int n;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // BFS로 연결된 영역의 좌표 추출
    private List<int[]> bfs(int[][] board, boolean[][] visited,
                           int sx, int sy, int target) {
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> cells = new ArrayList<>();

        queue.offer(new int[]{sx, sy});
        visited[sx][sy] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            cells.add(new int[]{cur[0], cur[1]});

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }

                if (visited[nx][ny]) continue;
                if (board[nx][ny] != target) continue;

                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny});
            }
        }

        return normalize(cells);
    }

    // 좌표를 왼쪽 위 기준으로 정규화
    private List<int[]> normalize(List<int[]> cells) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (int[] cell : cells) {
            minX = Math.min(minX, cell[0]);
            minY = Math.min(minY, cell[1]);
        }

        List<int[]> result = new ArrayList<>();

        for (int[] cell : cells) {
            result.add(new int[]{
                cell[0] - minX,
                cell[1] - minY
            });
        }

        result.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        return result;
    }

    private List<int[]> rotate(List<int[]> cells) {
        List<int[]> result = new ArrayList<>();

        for (int[] cell : cells) {
            result.add(new int[]{cell[1], -cell[0]});
        }

        return normalize(result);
    }

    // 두 도형의 좌표 비교
    private boolean sameShape(List<int[]> a, List<int[]> b) {
        if (a.size() != b.size()) return false;

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i)[0] != b.get(i)[0]
                || a.get(i)[1] != b.get(i)[1]) {
                return false;
            }
        }

        return true;
    }
    
    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;

        boolean[][] visitedBoard = new boolean[n][n];
        boolean[][] visitedTable = new boolean[n][n];

        List<List<int[]>> blanks = new ArrayList<>();
        List<List<int[]>> pieces = new ArrayList<>();

        // 보드의 빈 영역 추출
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (game_board[i][j] == 0 && !visitedBoard[i][j]) {
                    blanks.add(bfs(game_board, visitedBoard, i, j, 0));
                }
            }
        }

        // 테이블의 퍼즐 조각 추출
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (table[i][j] == 1 && !visitedTable[i][j]) {
                    pieces.add(bfs(table, visitedTable, i, j, 1));
                }
            }
        }

        boolean[] used = new boolean[pieces.size()];
        int answer = 0;

        // 각 빈 영역에 맞는 퍼즐 조각 찾기
        for (List<int[]> blank : blanks) {
            for (int i = 0; i < pieces.size(); i++) {
                if (used[i]) continue;

                List<int[]> piece = pieces.get(i);

                if (blank.size() != piece.size()) continue;

                List<int[]> rotated = piece;

                for (int r = 0; r < 4; r++) {
                    if (sameShape(blank, rotated)) {
                        answer += blank.size();
                        used[i] = true;
                        break;
                    }

                    rotated = rotate(rotated);
                }

                if (used[i]) break;
            }
        }

        return answer;
    }
}
