import java.util.*;

class Solution {

    static List<Integer>[] graph;
    static boolean[] visited;
    static int count;

    static void dfs(int current, int cutA, int cutB) {

        visited[current] = true;
        count++;

        for (int next : graph[current]) {

            // 현재 끊었다고 가정한 전선이면 지나가지 않음
            if ((current == cutA && next == cutB)
                    || (current == cutB && next == cutA)) {
                continue;
            }

            if (visited[next]) continue;

            dfs(next, cutA, cutB);
        }
    }

    public int solution(int n, int[][] wires) {

        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 생성
        for (int[] wire : wires) {

            int a = wire[0];
            int b = wire[1];

            graph[a].add(b);
            graph[b].add(a);
        }

        int answer = Integer.MAX_VALUE;

        // 전선을 하나씩 끊어보기
        for (int[] wire : wires) {

            int cutA = wire[0];
            int cutB = wire[1];

            visited = new boolean[n + 1];
            count = 0;

            // cutA 쪽 전력망 크기 확인
            dfs(cutA, cutA, cutB);

            int otherCount = n - count;

            int diff = Math.abs(count - otherCount);

            answer = Math.min(answer, diff);
        }

        return answer;
    }
}