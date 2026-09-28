
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    static int n;
    static int[][] map;

    static final int[] mx = {1, -1, 0, 0};
    static final int[] my = {0, 0, 1, -1};

    static public int bfs() {
        Queue<int[]> q = new LinkedList<>();
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        q.offer(new int[]{0, 0});
        dist[0][0] = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + mx[i];
                int ny = curr[1] + my[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                int nd = dist[curr[0]][curr[1]] + map[nx][ny];

                if (nd < dist[nx][ny]) {
                    q.offer(new int[]{nx, ny});
                    dist[nx][ny] = nd;
                }
            }
        }
        return dist[n - 1][n - 1];
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];

            for (int i = 0; i < n; i++) {
                String input = br.readLine();
                for (int j = 0; j < n; j++) {
                    map[i][j] = input.charAt(j) - '0';
                }
            }

            int answer = bfs();

            sb.append("#" + test_case + " " + answer + "\n");
        }
        System.out.println(sb);
    }
}
