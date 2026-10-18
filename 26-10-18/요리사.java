import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int n;
    static int[][] synergy;
    static boolean[] selected;
    static int answer;

    static void dfs(int start, int depth) {
        if (depth == n / 2) {
            int tasteA = 0;
            int tasteB = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (selected[i] && selected[j]) {
                        tasteA += synergy[i][j];
                    } else if (!selected[i] && !selected[j]) {
                        tasteB += synergy[i][j];
                    }
                }
            }

            answer = Math.min(answer, Math.abs(tasteA - tasteB));
            return;
        }

        for (int i = start; i < n; i++) {
            selected[i] = true;
            dfs(i + 1, depth + 1);
            selected[i] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine().trim());

            synergy = new int[n][n];
            selected = new boolean[n];
            answer = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                StringTokenizer st =
                        new StringTokenizer(br.readLine());

                for (int j = 0; j < n; j++) {
                    synergy[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, 0);

            sb.append("#").append(tc).append(" ")
                    .append(answer).append("\n");
        }

        System.out.print(sb);
    }
}
