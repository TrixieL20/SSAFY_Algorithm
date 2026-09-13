import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {
    static int maxScore;
    static int n;
    static int l;
    static int[][] info;

    public static void dfs(int start, int score, int calorie) {
        // 현재까지 선택한 조합으로 만들 수 있는 최대 점수 갱신
        maxScore = Math.max(maxScore, score);

        for (int i = start; i < n; i++) {

            // 칼로리 제한을 넘으면 선택하지 않음
            if (calorie + info[i][1] > l) {
                continue;
            }

            // 다음 음식은 i + 1부터 선택
            dfs(
                i + 1,
                score + info[i][0],
                calorie + info[i][1]
            );
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            String[] input = br.readLine().split(" ");

            n = Integer.parseInt(input[0]);
            l = Integer.parseInt(input[1]);

            info = new int[n][2];

            for (int i = 0; i < n; i++) {
                input = br.readLine().split(" ");

                info[i][0] = Integer.parseInt(input[0]); // 점수
                info[i][1] = Integer.parseInt(input[1]); // 칼로리
            }

            maxScore = 0;

            dfs(0, 0, 0);

            sb.append("#")
              .append(test_case)
              .append(" ")
              .append(maxScore)
              .append("\n");
        }

        System.out.println(sb);
    }
}
