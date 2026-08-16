import java.util.*;

class Solution
{
    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) throws Exception
    {
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            System.out.println(solution());
        }
    }

    private static int solution() {
        int n = sc.nextInt();

        int[][] tasks = new int[n][2];

        for (int i = 0; i < n; i++) {
            tasks[i][0] = sc.nextInt();
            tasks[i][1] = sc.nextInt();
        }

        Arrays.sort(tasks, (a, b) -> Integer.compare(b[1], a[1]));
        int date = tasks[0][1];

        for (int i = 0; i < tasks.length; i++) {
            int duration = tasks[1][0], deadline = tasks[i][1];

            if (date >= deadline) date = deadline;
            date -= duration;
        }

        return date;
    }
}