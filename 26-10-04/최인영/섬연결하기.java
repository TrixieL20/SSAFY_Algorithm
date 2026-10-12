import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        boolean visited[] = new boolean[n];
        int visitCount = 0;

        // pq -> int[도착 섬, 비용]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        pq.offer(new int[]{0, 0});

        while(!pq.isEmpty()){
            int curr[] = pq.poll();
            int arrival = curr[0];
            int cost = curr[1];

            // 이미 방문한 적 있는 섬이면 넘어감
            if(visited[arrival]){
                continue;
            }

            // 방문해 본 적 없는 섬이면 방문 처리 / cost 추가 / 방문 섬 개수 +1
            visited[arrival] = true;
            answer += cost;
            visitCount++;

            // 모든 섬 방문한 경우 끝
            if(visitCount == n){
                break;
            }

            // 가능해진 간선 추가
            for(int[] bridge: costs){
                // 현재 도착지가 시작점인 간선
                if(bridge[0] == arrival && !visited[bridge[1]]){
                    pq.offer(new int[]{bridge[1], bridge[2]});
                }

                // 양방향인 경우 반대로 처리도 해 줘야 함. 현재 도착지가 도착지인 간선
                else if(bridge[1] == arrival && !visited[bridge[0]]){
                    pq.offer(new int[]{bridge[0], bridge[2]});
                }
            }

        }
        return answer;
    }
}
