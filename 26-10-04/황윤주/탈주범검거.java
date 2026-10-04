import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int count, R, C, L, N, M;
    static int[][] map;
    static Queue<int[]> queue;
    static boolean[][] visited;
    // 위 아래 좌 우
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    // 상 하 좌 우 반대 (하 상 우 좌)
    static int[] opposite = {1,0,3,2};

    // 그 방향으로 갈 수 있는지 확인하는 pipes 배열
    static boolean[][] pipes = {
            {false, false, false, false}, // dir == 0일 때 이동 X
            {true, true, true, true}, // dir == 1일때 상 하 좌 우 모두 이동 가능
            {true, true, false, false}, // dir == 2 일 때 상 하 이동 가능
            {false, false, true, true}, // dir == 3 일 때 좌 우 이동 가능
            {true, false, false, true}, // dir == 4 일 때 위 오른쪽 가능
            {false, true, false, true}, // dir == 5 일 때 아래 오른쪽 가능
            {false, true, true, false}, // dir == 6 일 때 왼쪽, 아래 이동 가능
            {true, false, true, false} // dir == 7 일 때 왼쪽, 위 이동 가능
    };

    static void bfs(){
        queue = new ArrayDeque<>();
        queue.offer(new int[]{R,C,1}); // 맨홀 위치 (R,C), time 넣기
        visited[R][C] = true;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int time = current[2];

            if(time == L){
                continue;
            }

            for(int d=0; d<4; d++){
                // 현재 r,c에서 갈 수 있는 방향 탐색
                if(!pipes[map[r][c]][d]) continue;

                // 갈 수 있으면 nr, nc로 이동
                int nr = r + dr[d];
                int nc = c + dc[d];


                // 맵 범위 벗어나는지 검사
                if(nr<0 || nr>=N || nc<0 || nc>=M) continue;

                // 방문했는지 검사
                if(visited[nr][nc]) continue;


                // 이동한 위치에서 그 반대방향 (원래 r,c)로 이동할 수 있는지 검사
                int nextDir = map[nr][nc];
                if(!pipes[nextDir][opposite[d]]) continue;
                count++;
                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc, time+1});
            }


        }


    }




    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 세로 길이
            M = Integer.parseInt(st.nextToken()); // 가로 길이

            R = Integer.parseInt(st.nextToken()); // 멘홀 뚜껑 r
            C = Integer.parseInt(st.nextToken()); // 멘홀 뚜껑 c

            L = Integer.parseInt(st.nextToken()); // 소요 시간 L

            map = new int[N][M];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<M; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            count = 1;
            visited = new boolean[N][M];
            bfs();



            System.out.println("#"+tc+" "+count);
        }

    }

}
