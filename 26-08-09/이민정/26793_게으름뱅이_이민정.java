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

        Queue<int[]> q = new PriorityQueue<>(
                (a, b) -> Integer.compare(b[1], a[1])
        );

        for (int i = 0; i < n; i++) {
            int duration = sc.nextInt(), deadline = sc.nextInt();
            q.add(new int[] {duration, deadline});
        }

        int date = q.peek()[1];

        while (!q.isEmpty()) {
            int[] task = q.poll();
            int duration = task[0], deadline = task[1];

            if (date >= deadline) date = deadline;
            date -= duration;
        }

        return date;
    }
}