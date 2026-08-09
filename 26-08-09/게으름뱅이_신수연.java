import java.util.Arrays;
import java.util.Scanner;

class Solution {

    public static void main(String args[]) throws Exception {
		
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
		
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();

            int[][] task = new int[n][2];
            for (int i = 0; i < n; i++) {
                task[i][0] = sc.nextInt();
                task[i][1] = sc.nextInt();
            }

            Arrays.sort(task, (a, b) -> b[1] - a[1]);

            int day = task[0][1];

            for (int i = 0; i < n; i++) {
                day = Math.min(day, task[i][1]);
                day -= task[i][0];
            }
            System.out.println(day);
        }
    }
}