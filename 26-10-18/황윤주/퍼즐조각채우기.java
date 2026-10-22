import java.util.*;

class Solution {

    static int N;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // 빈칸과 퍼즐 조각들을 저장
    static List<List<int[]>> blanks;
    static List<List<int[]>> blocks;

    public int solution(int[][] game_board, int[][] table) {

        int answer = 0;

        N = game_board.length;

        blanks = new ArrayList<>();
        blocks = new ArrayList<>();

        // 1. 게임판에서 빈칸 찾기 (0)
        findBlock(game_board, 0, blanks);

        // 2. 테이블에서 퍼즐 조각 찾기 (1)
        findBlock(table, 1, blocks);

        // 이미 사용한 퍼즐 조각인지 확인
        boolean[] used = new boolean[blocks.size()];

        // 3. 빈칸마다 들어갈 수 있는 퍼즐 조각 찾기
        for (List<int[]> blank : blanks) {

            for (int i = 0; i < blocks.size(); i++) {

                if (used[i]) continue;

                List<int[]> block = blocks.get(i);

                // 블록의 칸 수가 다르면 비교할 필요 없음
                if (blank.size() != block.size()) continue;

                // 0도, 90도, 180도, 270도 비교
                for (int r = 0; r < 4; r++) {

                    // 현재 방향에서 모양이 같다면
                    if (isSame(blank, block)) {

                        answer += block.size();
                        used[i] = true;

                        break;
                    }

                    // 90도 회전
                    rotate(block);
                }

                // 빈칸을 채웠다면 다음 빈칸으로 이동
                if (used[i]) break;
            }
        }

        return answer;
    }


    // ==========================================
    // 1. 빈칸 또는 퍼즐 조각 찾기
    // ==========================================

    static void findBlock(int[][] map, int target,
                          List<List<int[]>> result) {

        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (visited[i][j]) continue;
                if (map[i][j] != target) continue;

                // BFS로 연결된 칸 탐색
                List<int[]> block = bfs(map, visited, i, j, target);

                // 찾은 블록 저장
                result.add(block);
            }
        }
    }


    // ==========================================
    // 2. BFS
    // ==========================================

    static List<int[]> bfs(int[][] map, boolean[][] visited,
                           int x, int y, int target) {

        Queue<int[]> queue = new ArrayDeque<>();

        // 블록을 구성하는 좌표 저장
        List<int[]> block = new ArrayList<>();

        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int cx = current[0];
            int cy = current[1];

            // 현재 좌표 저장
            block.add(new int[]{cx, cy});

            // 상하좌우 탐색
            for (int d = 0; d < 4; d++) {

                int nx = cx + dx[d];
                int ny = cy + dy[d];

                // 범위 검사
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                // 방문한 칸이면 제외
                if (visited[nx][ny]) continue;

                // 찾으려는 값이 아니면 제외
                if (map[nx][ny] != target) continue;

                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny});
            }
        }

        // 좌표 정규화
        normalize(block);

        return block;
    }


    // ==========================================
    // 3. 좌표 정규화
    // ==========================================

    static void normalize(List<int[]> block) {

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        // 블록에서 가장 작은 x, y 찾기
        for (int[] pos : block) {

            minX = Math.min(minX, pos[0]);
            minY = Math.min(minY, pos[1]);
        }

        // 모든 좌표에서 최솟값 빼기
        for (int[] pos : block) {

            pos[0] -= minX;
            pos[1] -= minY;
        }
    }


    // ==========================================
    // 4. 블록 90도 회전
    // ==========================================

    static void rotate(List<int[]> block) {

        // (x, y) -> (y, -x)
        for (int[] pos : block) {

            int x = pos[0];
            int y = pos[1];

            pos[0] = y;
            pos[1] = -x;
        }

        // 회전 후 좌표 다시 정규화
        normalize(block);
    }


    // ==========================================
    // 5. 블록 모양 비교
    // ==========================================

    static boolean isSame(List<int[]> blank, List<int[]> block) {

        if (blank.size() != block.size()) return false;

        // 빈칸의 모든 좌표 확인
        for (int[] a : blank) {

            boolean found = false;

            // 블록에서 같은 좌표가 있는지 확인
            for (int[] b : block) {

                if (a[0] == b[0] && a[1] == b[1]) {

                    found = true;
                    break;
                }
            }

            // 일치하는 좌표가 없다면 다른 모양
            if (!found) return false;
        }

        return true;
    }
}