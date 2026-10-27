import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int d;
    static int w;
    static int k;
    static int[][] film;
    static int min;

    static void dfs(int depth, int medicine) {
        // 최소 medicine 값을 넘어가면 답이 될 수 없으므로 바로 리턴
        if (min <= medicine) return;

        // 탐색 완료 시 동일 특성이 K개 이상인지 확인
        if (depth == d) {
            for (int i = 0; i < w; i++) {
                if (!check(i)) return;
            }
            min = Math.min(min, medicine);
            return;
        }

        int[] copied = film[depth].clone();

        // 약물 투여 하지 않는 경우
        dfs(depth + 1, medicine);

        // A 약물 투여
        Arrays.fill(film[depth], 0);
        dfs(depth + 1, medicine + 1);

        // B 약물 투여
        Arrays.fill(film[depth], 1);
        dfs(depth + 1, medicine + 1);

        // 원상 복구
        film[depth] = copied;
    }

    static boolean check(int col) {
        if (k == 1) return true;

        int count = 1;
        int prev = film[0][col];
      
        for (int row = 1; row < d; row++) {
            if (prev != film[row][col]) {
                count = 1;
                prev = film[row][col];
                continue;
            }
            count++;

            if (count == k) return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            min = Integer.MAX_VALUE;
            film = new int[d][w];
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dfs(0, 0);
            sb.append("#" + t + " " + min + "\n");
        }

        System.out.print(sb);
    }
}
