import java.util.*;

class Solution {

    static int[][] graph;

    // 특정 전선을 끊은 상태에서 BFS
    public static int bfs(int n, int start, int cutA, int cutB) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];

        q.offer(start);
        visited[start] = true;

        int count = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next = 1; next <= n; next++) {

                // 연결된 전선이 없는 경우
                if (graph[cur][next] == 0) {
                    continue;
                }

                // 이번에 끊은 전선이면 이동하지 않음
                if ((cur == cutA && next == cutB) ||
                    (cur == cutB && next == cutA)) {
                    continue;
                }

                // 이미 방문한 송전탑이면 넘어감
                if (visited[next]) {
                    continue;
                }

                visited[next] = true;
                q.offer(next);
                count++;
            }
        }

        return count;
    }

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        // 인접 행렬 생성
        graph = new int[n + 1][n + 1];

        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];

            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        // 모든 전선을 하나씩 끊어 보기
        for (int[] wire : wires) {
            int cutA = wire[0];
            int cutB = wire[1];

            // 1번 송전탑이 속한 전력망 크기
            int count = bfs(n, 1, cutA, cutB);

            // 두 전력망의 송전탑 개수 차이
            int diff = Math.abs(count - (n - count));

            answer = Math.min(answer, diff);
        }

        return answer;
    }
}