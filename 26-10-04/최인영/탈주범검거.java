
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution
{
    // dir을 반드시 상, 좌, 우, 하로 설정
    // 이동 가능 여부를 양쪽에서 체크해야 하는데, (상, 하), (좌, 우)를 3-index로 관리하기 위해서
    // 현재 위치에서 상이었다면 반대 위치에서 하로 이동 가능해야 하므로 이 체크를 간편화하기 위함
    public static int[] dirRow  = {-1, 0, 0, 1};
    public static int[] dirCol = {0, -1, 1, 0};

    public static Boolean[][] tunnel_dir = {
        {},
        {true, true, true, true},
        {true, false, false, true},
        {false, true, true, false},
        {true, false, true, false},
        {false, false, true, true},
        {false, true, false, true},
        {true, true, false, false}
    };

    public static int answer;
    public static int[] requirements;
    public static int[][] tunnel;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++){
            // 입출력
            sb.append("#").append(test_case).append(" ");
            // requirements -> 세로 크기 N, 가로 크기 M, 맨홀 뚜껑 세로 위치 R, 가로 위치 C, 소요된 시간 L 
            requirements = new int[5];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 5; i++){
                requirements[i] = Integer.parseInt(st.nextToken());
            }
            tunnel = new int[requirements[0]][requirements[1]];
            for(int row = 0; row < requirements[0]; row++){
                st = new StringTokenizer(br.readLine());
                for(int col = 0; col < requirements[1]; col++){
                   tunnel[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            bfs();

            sb.append(answer).append("\n");
            
        }
        System.out.println(sb.toString());
    }

    static void bfs(){
        int startRow = requirements[2];
        int startCol = requirements[3];
        int endHour = requirements[4];
        boolean[][] visited = new boolean[requirements[0]][requirements[1]];
        int count = 1;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startRow, startCol, tunnel[startRow][startCol], 1});
        visited[startRow][startCol] = true;

        while(!queue.isEmpty()){
            int[] curr = queue.poll();

            if(curr[3]== endHour){
                continue;
            }

            for(int index = 0; index < 4; index++){
                // 현재 방향에서 이동 가능한 위치인 경우에만
                if(tunnel_dir[curr[2]][index]){
                    int nextRow = curr[0] + dirRow[index];
                    int nextCol = curr[1] + dirCol[index];

                    // 다음 위치 범위 벗어난 경우
                    if(nextRow < 0 || nextRow >= requirements[0] || nextCol < 0 || nextCol >= requirements[1]){
                        continue;
                    }

                    // 다음 위치 이미 방문된 경우
                    if(visited[nextRow][nextCol]){
                        continue;
                    }

                    if (tunnel[nextRow][nextCol] == 0) {
                        continue;
                    }

                    // 새로 방문해 보는 경우 현재 위치와 이어져 있는지 확인
                    if(tunnel_dir[tunnel[nextRow][nextCol]][3-index] == true){
                        queue.offer(new int[] {nextRow, nextCol, tunnel[nextRow][nextCol], curr[3]+1});
                        visited[nextRow][nextCol] = true;
                        count++;
                    }
                }
            }
        }

         answer = count;
    }
}