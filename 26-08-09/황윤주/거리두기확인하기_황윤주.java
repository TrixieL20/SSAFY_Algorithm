import java.util.*;

class Solution {

    // 상하좌우 이동
    private static int[] dr = {-1,1,0,0};
    private static int[] dc = {0,0,-1,1};

    public int[] solution(String[][] places) {
        // 대기실마다 결과를 저장
        int[] answer = new int[places.length];

        // 대기실 하나씩 검사
        for(int i=0; i<places.length; i++){
            // check함수에서 true면 1, false면 0
            answer[i] = check(places[i]) ? 1 : 0;
        }

        return answer;
    }

    // 대기실 거리두기 지키고 있는지 검사
    static boolean check(String[] place){
        // 모든 P를 찾아서 BFS
        for(int r=0; r<5; r++){
            for(int c=0; c<5; c++){
                // 사람 P이면 이 위치 기준으로 BFS 실행
                if(place[r].charAt(c)=='P'){
                    if(!bfs(place,r,c)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // 주변에 다른 사람이 있느지 BFS로 확인
    static boolean bfs(String[] place, int startR, int startC){
        Queue<int[]> queue = new LinkedList<>();
        // 방문 여부 저장
        boolean[][] visited = new boolean[5][5];

        // 큐에 시작점 넣기 (행, 열, 거리)
        queue.offer(new int[]{startR, startC, 0});
        // 시작점은 방문 했다고 표시
        visited[startR][startC] = true;

        // 큐에 확인할 위치가 남아있는 동안 반복
        while(!queue.isEmpty()){
            // 큐에서 현재 위치 하나 꺼내기
            int[] current = queue.poll();
            // 행, 열, 거리 꺼내기
            int r = current[0];
            int c = current[1];
            int distance = current[2];

            // 거리 2까지만 확인
            if(distance ==2){
                continue;
            }
            // 현재 위치에서 상하좌우 확인
            for(int d=0; d<4; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];

                // 범위 밖인 경우
                if(nr<0 || nr>=5 || nc<0 || nc>=5){
                    continue;
                }
                // 이미 방문했을 때
                if(visited[nr][nc]){
                    continue;
                }

                // X면 이동 불가
                if(place[nr].charAt(nc)=='X'){
                    continue;
                }

                // P면 false
                if(place[nr].charAt(nc)=='P'){
                    return false;
                }

                visited[nr][nc] = true;
                // 현재 거리 +1
                queue.offer(new int[]{nr, nc, distance+1});
            }
        }
        // BFS를 끝까지 했는데 P를 발견하지 못했다면 true반환
        return true;
    }
}