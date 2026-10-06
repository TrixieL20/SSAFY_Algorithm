import java.util.*;
import java.io.*;

class Solution
{
    static int[] dy = new int[]{-1, 0, 1, 0};
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[][] map = null, visited = null;
    static int n = 0, m = 0;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            map = new int[n][m];
            visited = new int[n][m];

            // 지하 터널 완성
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < m; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // bfs를 통한 탐색
            Queue<int[]> q = new ArrayDeque<>();
            q.add(new int[]{r, c});
            visited[r][c] = 1;
            int move = 1;

            while(!q.isEmpty() && move < l + 2) {
                int[] p = q.poll();
                int y = p[0], x = p[1];
                int tunnelType = map[y][x];

                switch (tunnelType) {
                    case 1: // +
                        for (int i = 0; i < 4; i++) {
                            if (canMove(i, y + dy[i], x + dx[i])) {
                                move = getMove(q, y, i, x, move);
                            }
                        }
                        break;

                    case 2: // ㅣ
                        int dir = 0;
                        if (canMove(dir, y + dy[dir], x + dx[dir])) {
                            move = getMove(q, y, dir, x, move);
                        }

                        dir = 2;
                        if (canMove(dir, y + dy[dir], x + dx[dir])) {
                            move = getMove(q, y, dir, x, move);
                        }
                        break;

                    case 3: // ㅡ
                        dir = 1;
                        if (canMove(dir, y + dy[dir], x + dx[dir])) {
                            move = getMove(q, y, dir, x, move);
                        }

                        dir = 3;
                        if (canMove(dir, y + dy[dir], x + dx[dir])) {
                            move = getMove(q, y, dir, x, move);
                        }
                        break;

                    case 4: // ㄴ
                        dir = 0;
                        if (canMove(dir, y + dy[dir], x + dx[dir])) {
                            move = getMove(q, y, dir, x, move);
                        }

                        dir = 1;
                        if (canMove(dir, y + dy[dir], x + dx[dir])) {
                            move = getMove(q, y, dir, x, move);
                        }
                        break;

                    case 5: // ⌜
                        dir = 1;
                        if (canMove(dir, y + dy[dir], x + dx[dir])) {
                            move = getMove(q, y, dir, x, move);
                        }

                        dir = 2;
                        if (canMove(dir, y + dy[dir], x + dx[dir])) {
                            move = getMove(q, y, dir, x, move);
                        }
                        break;

                    case 6: // ㄱ
                        dir = 2;
                        if (canMove(dir, y + dy[dir], x + dx[dir])) {
                            move = getMove(q, y, dir, x, move);
                        }

                        dir = 3;
                        if (canMove(dir, y + dy[dir], x + dx[dir])) {
                            move = getMove(q, y, dir, x, move);
                        }
                        break;

                    case 7: // ⨼
                        dir = 0;
                        if (canMove(dir, y + dy[dir], x + dx[dir])) {
                            move = getMove(q, y, dir, x, move);
                        }

                        dir = 3;
                        if (canMove(dir, y + dy[dir], x + dx[dir])) {
                            move = getMove(q, y, dir, x, move);
                        }
                        break;
                }
            }

            int answer = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (visited[i][j] > 0 && visited[i][j] <= l) {
                        answer++;
                    }
                }
            }

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    private static int getMove(Queue<int[]> q, int y, int dir, int x, int move) {
        q.add(new int[]{y + dy[dir], x + dx[dir]});
        visited[y + dy[dir]][x + dx[dir]] = visited[y][x] + 1;
        move = visited[y + dy[dir]][x + dx[dir]];
        return move;
    }

    private static boolean canMove(int dir, int r, int c) {
        if (r <0 || r >= n || c <0 || c >= m) return false; // map 바깥
        if (visited[r][c] > 0) return false; // 방문했을 경우

        if (dir == 0 && (map[r][c] == 1 || map[r][c] == 2 || map[r][c] == 5 || map[r][c] == 6)) return true; // 이전 타일이 위로 가고 싶어 할 때 +, ㅣ, ⌜, ㄱ
        if (dir == 1 && (map[r][c] == 1 || map[r][c] == 3 || map[r][c] == 6 || map[r][c] == 7)) return true; // 이전 타일이 우측으로 가고 싶어 할 때 +, ㅡ, ㄱ, ⨼
        if (dir == 2 && (map[r][c] == 1 || map[r][c] == 2 || map[r][c] == 4 || map[r][c] == 7)) return true; // 이전 타일이 아래로 가고 싶어 할 때 +, ㅣ, ㄴ, ⨼
        if (dir == 3 && (map[r][c] == 1 || map[r][c] == 3 || map[r][c] == 4 || map[r][c] == 5)) return true; // 이전 타일이 좌측으로 가고 싶어 할 때 +, ㅣ, ⌜, ㄴ

        return false; // 터널이 이어져 있지 않은 경우
    }
}