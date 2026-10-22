import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {
    // game_board 크기 행렬 안에서 조각 찾기 위해 시작점마다 bfs
    // 조각인 경우 시작점 기준으로 한 row 와 col 좌표들로 조각 저장해 list 화 (예: ㄱ 모양 {{0, 0}, {1, 0}, {1, 1}})
    // 이후 table에서 찾은 조각들도 똑같이 처리해 완전히 같은지 확인
    private static final int[] dirRow = {-1, 1, 0, 0};
    private static final int[] dirCol = {0, 0, -1, 1};

    // BFS로 연결된 조각 찾기
    // game_board는 0, table은 1을 탐색
    private List<List<int[]>> findPuzzle(int[][] board, int targetIdx) {
        int n = board.length;
        boolean[][] visited = new boolean[n][n];

        List<List<int[]>> puzzleList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (board[i][j] != targetIdx || visited[i][j]) {
                    continue;
                }

                // 하나의 연결된 조각 저장
                List<int[]> puzzle = new ArrayList<>();
                Deque<int[]> queue = new ArrayDeque<>();

                queue.offer(new int[] {i, j});
                visited[i][j] = true;

                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    int row = cur[0];
                    int col = cur[1];

                    puzzle.add(new int[] {row, col});

                    for (int d = 0; d < 4; d++) {
                        int nextRow = row + dirRow[d];
                        int nextCol = col + dirCol[d];

                        if (nextRow < 0 || nextRow >= n ||
                            nextCol < 0 || nextCol >= n) {
                            continue;
                        }

                        if (visited[nextRow][nextCol] ||
                            board[nextRow][nextCol] != targetIdx) {
                            continue;
                        }

                        visited[nextRow][nextCol] = true;
                        queue.offer(new int[] {nextRow, nextCol});
                    }
                }

                // 조각 좌표를 (0,0) 기준으로 변환
                normalize(puzzle);
                puzzleList.add(puzzle);
            }
        }

        return puzzleList;
    }

    // 조각 좌표 시작점 기준 정규화 및 정렬
    private void normalize(List<int[]> puzzle) {
        int minRow = Integer.MAX_VALUE;
        int minCol = Integer.MAX_VALUE;

        for (int[] point : puzzle) {
            minRow = Math.min(minRow, point[0]);
            minCol = Math.min(minCol, point[1]);
        }

        for (int[] point : puzzle) {
            point[0] -= minRow;
            point[1] -= minCol;
        }

        // 행 오름차순, 열 오름차순
        puzzle.sort((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
    }

    // 조각을 시계방향으로 90도 회전
    private List<int[]> rotate(List<int[]> puzzle) {
        List<int[]> rotated = new ArrayList<>();

        for (int[] point : puzzle) {
            // (row, col) -> (col, -row)
            rotated.add(new int[] {point[1], -point[0]});
        }

        normalize(rotated);
        return rotated;
    }

    // 두 조각의 좌표가 완전히 같은지 확인
    private boolean isSame(List<int[]> a, List<int[]> b) {
        if (a.size() != b.size()) {
            return false;
        }

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i)[0] != b.get(i)[0] ||
                a.get(i)[1] != b.get(i)[1]) {
                return false;
            }
        }

        return true;
    }

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;

        // 보드의 빈 공간 / 테이블의 퍼즐 조각 추출
        List<List<int[]>> emptySpaces = findPuzzle(game_board, 0);
        List<List<int[]>> pieces = findPuzzle(table, 1);

        // 이미 사용한 퍼즐 조각 관리
        boolean[] used = new boolean[pieces.size()];

        // 보드의 빈 공간 하나씩 확인
        for (List<int[]> space : emptySpaces) {
            boolean filled = false;

            for (int j = 0; j < pieces.size(); j++) {
                // 사용한 조각이거나 크기가 다르면 패스
                if (used[j] || space.size() != pieces.get(j).size()) {
                    continue;
                }

                List<int[]> piece = pieces.get(j);

                // 0도, 90도, 180도, 270도 비교
                for (int r = 0; r < 4; r++) {
                    if (isSame(space, piece)) {
                        used[j] = true;
                        answer += space.size();
                        filled = true;
                        break;
                    }

                    piece = rotate(piece);
                }

                if (filled) {
                    break;
                }
            }
        }

        return answer;
    }
}