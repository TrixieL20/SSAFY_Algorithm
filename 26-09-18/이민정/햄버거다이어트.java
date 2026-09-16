import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static int[][] ingredients = null;
    static int n = 0, l = 0, answer = 0;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T;
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());

            isInclude = new boolean[n];
            ingredients = new int[n][2];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                ingredients[i][0] = Integer.parseInt(st.nextToken()); // 맛
                ingredients[i][1] = Integer.parseInt(st.nextToken()); // 칼로리
            }

            answer = 0;

            dfs(0, 0, l);

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int score, int idx, int leftCalorie) {
        if (idx == n) {
            answer = Math.max(answer, score); return;
        }

        int taste = ingredients[idx][0];
        int calorie = ingredients[idx][1];

        if (calorie <= leftCalorie) {
            dfs(score + taste, idx + 1, leftCalorie - calorie);
        }

        dfs(score, idx + 1, leftCalorie);
    }
}
