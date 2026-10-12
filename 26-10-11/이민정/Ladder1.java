import java.util.*;
import java.io.*;

class Solution
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] dy = new int[] {0, -1, 0};
        int[] dx = new int[] {1, 0, -1};

        for(int test_case = 1; test_case <= 10; test_case++)
        {
            int t = Integer.parseInt(br.readLine()), x = 0;

            int[][] ladder = new int[100][100];

            for (int i = 0; i < 100; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");

                for (int j = 0; j < 100; j++) {
                    ladder[i][j] = Integer.parseInt(st.nextToken());

                    if (i == 99 && ladder[i][j] == 2) x = j;
                }
            }

            int dir = 1; // 1일 때는 위로 감 / 0은 우측 / 2은 좌측
            int y = 99, answer = 0;

            while (y >= 0) {
                if (y == 0) {
                    answer = x;
                    break;
                }

                if (dir == 1) { // 좌우에 같은 높이에서 이동할 수 있는 경우는 없다고 가정 -|-
                    if (x - 1 >= 0 && ladder[y][x - 1] == 1) { // 좌측
                        x--;
                        dir = 2;
                    }

                    else if (x + 1 < 100 && ladder[y][x + 1] == 1) { // 우측
                        x++;
                        dir = 0;
                    }

                    else if (ladder[y - 1][x] == 1) { // 위
                        y--;
                    }
                }

                else if (dir == 0) {
                    if (ladder[y - 1][x] == 1) { // 위
                        y--;
                        dir = 1;
                    }

                    else if (x + 1 < 100 && ladder[y][x + 1] == 1) { // 우측
                        x++;
                    }
                }

                else if (dir == 2) {
                    if (ladder[y - 1][x] == 1) { // 위
                        y--;
                        dir = 1;
                    }

                    else if (x - 1 >= 0 && ladder[y][x - 1] == 1) { // 좌측
                        x--;
                    }
                }
            }

            sb.append("#").append(t).append(" ").append(answer).append('\n');
        }

        System.out.println(sb);
    }
}