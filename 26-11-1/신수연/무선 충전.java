import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BC {
    int x, y, c, p;
    public BC(int x, int y, int c, int p) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.p = p;
    }
}

public class Solution {
    // 이동 없음, 상(1), 우(2), 하(3), 좌(4)
    static int[] dx = {0, 0, 1, 0, -1};
    static int[] dy = {0, -1, 0, 1, 0};

    static int M, A;
    static int[] moveA, moveB;
    static BC[] bcs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            moveA = new int[M + 1];
            moveB = new int[M + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                moveA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= M; i++) {
                moveB[i] = Integer.parseInt(st.nextToken());
            }

            bcs = new BC[A];
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                bcs[i] = new BC(x, y, c, p);
            }

            int totalSum = simulate();
            System.out.println("#" + t + " " + totalSum);
        }
    }

    static int simulate() {
        // 사용자 A 초기 위치: (1, 1), 사용자 B 초기 위치: (10, 10)
        int ax = 1, ay = 1;
        int bx = 10, by = 10;
        int totalCharge = 0;

        // 0초부터 M초까지 반복
        for (int time = 0; time <= M; time++) {
            // 1. 이동 방향에 따라 좌표 갱신 (0초에는 이동하지 않음)
            if (time > 0) {
                ax += dx[moveA[time]];
                ay += dy[moveA[time]];
                bx += dx[moveB[time]];
                by += dy[moveB[time]];
            }

            // 2. 현재 위치에서 각 BC에 접속 가능한지 확인하여 리스트에 성능과 함께 기록
            boolean[] reachA = new boolean[A];
            boolean[] reachB = new boolean[A];

            for (int i = 0; i < A; i++) {
                if (Math.abs(bcs[i].x - ax) + Math.abs(bcs[i].y - ay) <= bcs[i].c) {
                    reachA[i] = true;
                }
                if (Math.abs(bcs[i].x - bx) + Math.abs(bcs[i].y - by) <= bcs[i].c) {
                    reachB[i] = true;
                }
            }

            // 3. 이번 초에 얻을 수 있는 최대 충전량 계산 (완전탐색)
            int maxCurrentCharge = 0;

            for (int i = 0; i < A; i++) {
                for (int j = 0; j < A; j++) {
                    int sumA = 0;
                    int sumB = 0;

                    if (reachA[i]) sumA = bcs[i].p;
                    if (reachB[j]) sumB = bcs[j].p;

                    // 만약 두 사람이 같은 BC에 접속했다면 성능을 반으로 나눔
                    if (i == j && reachA[i] && reachB[j]) {
                        sumA /= 2;
                        sumB /= 2;
                    }

                    maxCurrentCharge = Math.max(maxCurrentCharge, sumA + sumB);
                }
            }

            totalCharge += maxCurrentCharge;
        }

        return totalCharge;
    }
}
