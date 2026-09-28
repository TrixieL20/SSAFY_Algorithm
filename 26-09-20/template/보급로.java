import java.util.*;
import java.io.*;

class Solution
{
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T =Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n =Integer.parseInt(br.readLine());
            int[][] map = new int[n][n];

            for (int i = 0; i < n; i++) {
                String line = br.readLine();

                for (int j = 0; j < n; j++) {
                    map[i][j] = line.charAt(j) - '0';
                }
            }

            int[][] distance = new int[n][n];

            for (int i = 0; i < n; i++) {
                Arrays.fill(distance[i], Integer.MAX_VALUE);
            }

            Queue<int[]> q = new ArrayDeque<>();

            distance[0][0] = 0;
            q.add(new int[]{0, 0});

            while (!q.isEmpty()) {

                int[] point = q.poll();

                int y = point[0];
                int x = point[1];

                for (int i = 0; i < 4; i++) {

                    int cy = y + dy[i];
                    int cx = x + dx[i];

                    if (cy < 0 || cy >= n || cx < 0 || cx >= n)
                        continue;

                    int newDistance = distance[y][x] + map[cy][cx];

                    if (distance[cy][cx] > newDistance) {

                        distance[cy][cx] = newDistance;

                        q.add(new int[]{cy, cx});
                    }
                }
            }

            sb.append("#").append(test_case).append(" ").append(distance[n - 1][n - 1]).append("\n");
        }

        System.out.print(sb);
    }
}