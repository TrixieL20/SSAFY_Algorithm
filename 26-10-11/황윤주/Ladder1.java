package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

// Ladder 1
public class Solution {
    static int answer; // (answer, 0) 갑 출력
    static int[][] ladder;
    static boolean[][] visited;
    static int fx, fy;

    static void bfs(int startX, int startY) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {startX, startY});
        visited[startX][startY] = true;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // 맨 위 도착
            if(x==0) {
                answer = y;
                return;
            }

            // 왼쪽으로 갈 수 있으면 왼쪽
            if(y-1 >= 0 && ladder[x][y-1]==1 && !visited[x][y-1]) {
                visited[x][y-1] = true;
                queue.offer(new int[] {x,y-1});
            }

            // 오른쪽으로 갈 수 있으면 오른쪽
            else if(y+1<100 && ladder[x][y+1]==1 && !visited[x][y+1]) {
                visited[x][y+1] = true;
                queue.offer(new int[] {x,y+1});
            }

            // 좌우로 못가면 위로
            else if(x-1 >= 0 && ladder[x-1][y]==1 &&!visited[x-1][y]){
                visited[x-1][y] = true;
                queue.offer(new int[] {x-1, y});
            }
        }

    }

    static boolean isOutOfBounds(int x, int y) {
        return x<0 || x>=100 || y<0 || y>=100;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int tc=1; tc<=10; tc++) {

            int T = Integer.parseInt(br.readLine());

            StringTokenizer st;

            ladder = new int[100][100];
            visited = new boolean[100][100];

            for(int i=0; i<100; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<100; j++) {
                    ladder[i][j] = Integer.parseInt(st.nextToken());
                    // 도착점 저장
                    if(ladder[i][j]==2) {
                        fx = i;
                        fy = j;
                    }
                }
            }

            answer = 0;

            bfs(fx, fy);



            System.out.println("#"+T+" "+answer);
        }

    }


}
