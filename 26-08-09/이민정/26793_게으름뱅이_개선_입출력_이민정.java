import java.io.*;
import java.util.*;

class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int nextInt() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return Integer.parseInt(st.nextToken());
    }

    public static void main(String args[]) throws Exception {
        int T = nextInt();

        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append(solution()).append('\n');
        }

        System.out.print(sb);
    }

    private static int solution() throws IOException {
        int n = nextInt();

        int[][] tasks = new int[n][2];

        for (int i = 0; i < n; i++) {
            tasks[i][0] = nextInt();
            tasks[i][1] = nextInt();
        }

        Arrays.sort(tasks, (a, b) -> Integer.compare(b[1], a[1]));

        int date = tasks[0][1];

        for (int i = 0; i < tasks.length; i++) {
            int duration = tasks[i][0];
            int deadline = tasks[i][1];

            if (date >= deadline)
                date = deadline;

            date -= duration;
        }

        return date;
    }
}