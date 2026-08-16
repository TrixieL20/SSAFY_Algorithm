import java.util.*;

class Solution {
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int k = 0; k < places.length; k++) {
            answer[k] = findAnswer(places[k]);
        }

        return answer;
    }

    public int findAnswer(String[] places) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (places[i].charAt(j) == 'P' && !isManhattan(i, j, places)) {
                    return 0;
                }
            }
        }

        return 1;
    }

    public boolean isManhattan(int y, int x, String[] place) {
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] visited = new int[place.length][place[0].length()];

        queue.add(new int[]{y, x});
        visited[y][x] = 1;

        while(!queue.isEmpty()) {
            int[] location = queue.poll();
            y = location[0];
            x = location[1];

            if (visited[y][x] >= 3) continue;

            for (int i = 0; i < 4; i++) {
                int cy = y + dy[i], cx = x + dx[i];

                if (cy < 0 || cy >= 5 || cx < 0 || cx >= 5 || place[cy].charAt(cx) == 'X' || visited[cy][cx] > 0) continue;
                else if (place[cy].charAt(cx) == 'P') return false;

                queue.add(new int[]{cy, cx});
                visited[cy][cx] = visited[y][x] + 1;
            }
        }

        return true;
    }
}