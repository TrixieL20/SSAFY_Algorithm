import java.util.*;

/**
 * 거리두기확인하기_최인영 문제 풀이
 */
public class 거리두기확인하기_최인영 {
   public int[] solution(String[][] places) {
        int[] answer = new int [5];
        // 대기실마다 거리 두기 준수 여부 isQuarantined로 결과 받고 answer에 넣음
        for(int row = 0; row < 5; row++){
            answer[row] = (isQuarantined(places[row]) ? 1 : 0);
        }
        return answer;
    }
    
    // 대기실(5x5)별로 지점마다 거리 두기 지켰는지 검사하는 함수
    // 지점별 검사는 bfs 함수로 진행 ! 
    public boolean isQuarantined(String[] waiting_room){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if (waiting_room[i].charAt(j) == 'P') {
                    // 여기서 BFS로 맨해튼 거리 2 이내의 주변 P가 조건대로 떨어져 있는지 검사
                    if(! bfs(waiting_room, i, j) ) { 
                        return false; 
                    }
                }
            }
        }
        return true;
    }

    // BFS 사용하되 거리 함께 기록해 맨해튼 거리 2 이내인 지점만 검사
    // 해당 조건 내에서 파티션 X 거쳐가는 경로는 거리두기 수칙 지킨 것이므로 미리 통과
    // 파티션 X 거치지 않고 P 만나는 경로는 False 처리
    // 빈테이블 O 거치는 경우는 다시 queue에 넣음
    public boolean bfs(String[] waiting_room, int row, int col){
        // 큐 & 방문 여부 저장 배열
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[5][5];

        // 열, 행 별 이동 방향 
        int[] dir_row = {-1, 1, 0, 0};
        int[] dir_col = {0, 0, -1, 1};

        // queue에 열과 행 외에도 index 2에는 이동거리 함께 저장
        // => bfs 중 거리 2 되면 그만 탐색할 수 있도록 함
        queue.add(new int[]{row, col, 0});
        visited[row][col] = true;

        while(!queue.isEmpty()){
            // 현재 큐에서 꺼낸 지점
            int[] current_pos = queue.poll();
            if(current_pos[2] == 2){ continue; }
            
            // 이동 방향 별 이동
            for(int i = 0; i < 4; i++){
                // 다음 이동할 열과 행
                int next_row = current_pos[0] + dir_row[i];
                int next_col = current_pos[1] + dir_col[i];

                // 배열 범위 나가는 경우 continue
                if (next_row < 0 || next_row >= 5 || next_col < 0 || next_col >= 5) { continue; }
                // 이미 방문한 경우 continue
                if (visited[next_row][next_col]) { continue; }

                // 다음 지점에 적힌 문자
                char c = waiting_room[next_row].charAt(next_col);
                
                // X 거쳐 가는 경우는 통과 ! 
                if (c == 'X') { continue; }
                
                // X 거치지 않고 P 만난 경우 -> 격리 안 된 것 !
                if (c == 'P') {
                    return false;
                }

                // 이외의 경우(빈테이블 O) 다음 지점 보기 위해 방문 처리 / 큐에 넣음
                visited[next_row][next_col] = true;
                queue.offer(new int[]{next_row, next_col, current_pos[2] + 1});
            }
        }
        return true;
    }
}