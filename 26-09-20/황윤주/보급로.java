import java.io.*;
import java.util.*;

public class Solution {

    static int N;
    static int[][] map;
    static int[][] dist;

    // 상 하 좌 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Node implements Comparable<Node> {

        int r;
        int c;
        int cost;

        Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }

        // cost가 작은 순서대로 꺼내기
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            // 지도 입력
            for (int r = 0; r < N; r++) {
                String line = br.readLine();
                for (int c = 0; c < N; c++) {
                    map[r][c] = line.charAt(c) - '0';
                }
            }

            // 최소 비용 저장 배열
            dist = new int[N][N];

            for (int r = 0; r < N; r++) {
                Arrays.fill(dist[r], Integer.MAX_VALUE);
            }

            dijkstra();

            System.out.println("#" + tc + " " + dist[N - 1][N - 1]);
        }
    }


    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // 시작점 비용 = 0
        dist[0][0] = 0;
        pq.offer(new Node(0, 0, 0));


        while (!pq.isEmpty()) {

            Node current = pq.poll();

            int r = current.r;
            int c = current.c;
            int cost = current.cost;


            // 이미 더 짧은 경로가 존재하면 무시
            if (cost > dist[r][c]) {
                continue;
            }


            // 상하좌우 탐색
            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];


                // 범위 확인
                if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                    continue;
                }


                // 현재까지 비용 + 다음 칸 복구비용
                int newCost = cost + map[nr][nc];


                // 더 저렴한 길을 발견했다면
                if (newCost < dist[nr][nc]) {
                    // 최소 비용 갱신
                    dist[nr][nc] = newCost;
                    // 다시 탐색
                    pq.offer(new Node(nr, nc, newCost));
                }
            }
        }
    }
}