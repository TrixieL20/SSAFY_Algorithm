import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int n = maps.length, m = maps[0].length;
        int[][] visited = new int[n][m];
        int[] dy = {0, 0, -1, 1};
        int[] dx = {-1, 1, 0, 0};

        Queue<int[]> queue = new ArrayDeque<>();
        visited[0][0] = 1;
        queue.add(new int[] {0, 0});

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int y = point[0], x = point[1];

            for (int i = 0; i < 4; i++) {
                int cy = y + dy[i], cx = x + dx[i];

                if (cy == n - 1 && cx == m - 1) return visited[y][x] + 1;

                if (cy < 0 || cy >= n || cx < 0 || cx >= m || visited[cy][cx] > 0 || maps[cy][cx] == 0) continue;

                queue.add(new int[] {cy, cx});
                visited[cy][cx] = visited[y][x] + 1;
            }
        }

        return -1;
    }
}