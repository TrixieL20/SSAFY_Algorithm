import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayDeque;

public class Solution {
    static class FastScanner {
        private final BufferedInputStream in = new BufferedInputStream(System.in);

        int nextInt() throws IOException {
            int c;
            do {
                c = in.read();
                if (c == -1) throw new IOException("Unexpected end of input");
            } while (c <= ' ');

            int value = 0;
            while (c > ' ' && c != -1) {
                value = value * 10 + c - '0';
                c = in.read();
            }
            return value;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder output = new StringBuilder();
        int T = fs.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = fs.nextInt();
            int M = fs.nextInt();
            int K = fs.nextInt();
            int A = fs.nextInt();
            int B = fs.nextInt();

            int[] a = new int[N + 1];
            int[] b = new int[M + 1];
            int[] arrival = new int[K + 1];
            for (int i = 1; i <= N; i++) a[i] = fs.nextInt();
            for (int j = 1; j <= M; j++) b[j] = fs.nextInt();
            for (int k = 1; k <= K; k++) arrival[k] = fs.nextInt();

            // 창구의 고객번호가 0이면 빈 창구이다.
            int[] receptionCustomer = new int[N + 1];
            int[] receptionEnd = new int[N + 1];
            int[] repairCustomer = new int[M + 1];
            int[] repairEnd = new int[M + 1];
            int[] usedReception = new int[K + 1];
            ArrayDeque<Integer> receptionWait = new ArrayDeque<>();
            ArrayDeque<Integer> repairWait = new ArrayDeque<>();

            int nextCustomer = 1;
            int assignedRepair = 0;
            int answer = 0;

            for (int time = 0; assignedRepair < K; time++) {
                // 1. 도착한 고객을 고객번호 순서대로 추가한다.
                while (nextCustomer <= K && arrival[nextCustomer] <= time) {
                    receptionWait.addLast(nextCustomer++);
                }

                // 2. 정비가 끝난 창구를 비운다.
                for (int j = 1; j <= M; j++) {
                    if (repairCustomer[j] != 0 && repairEnd[j] == time) {
                        repairCustomer[j] = 0;
                    }
                }

                // 3. 동시 접수 완료 고객은 접수 창구번호 순으로 추가한다.
                for (int i = 1; i <= N; i++) {
                    if (receptionCustomer[i] != 0 && receptionEnd[i] == time) {
                        repairWait.addLast(receptionCustomer[i]);
                        receptionCustomer[i] = 0;
                    }
                }

                // 4. 번호가 작은 빈 접수 창구부터 배정한다.
                for (int i = 1; i <= N && !receptionWait.isEmpty(); i++) {
                    if (receptionCustomer[i] != 0) continue;

                    int customer = receptionWait.removeFirst();
                    receptionCustomer[i] = customer;
                    receptionEnd[i] = time + a[i];
                    usedReception[customer] = i;
                }

                // 5. 번호가 작은 빈 정비 창구부터 배정한다.
                for (int j = 1; j <= M && !repairWait.isEmpty(); j++) {
                    if (repairCustomer[j] != 0) continue;

                    int customer = repairWait.removeFirst();
                    repairCustomer[j] = customer;
                    repairEnd[j] = time + b[j];
                    assignedRepair++;

                    if (usedReception[customer] == A && j == B) {
                        answer += customer;
                    }
                }
            }

            output.append('#').append(tc).append(' ')
                  .append(answer == 0 ? -1 : answer).append('\n');
        }
        System.out.print(output);
    }
}
