import java.util.*;

class Solution {
    public int solution(int[][] routes) {

        // 고속도로 진입점을 기준으로 정렬
        Arrays.sort(routes, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        
        int minEnd = routes[0][1];
        int count = 1;
        for(int i = 1; i < routes.length; i++) {
            // 이전 범위와 겹친다면 minEnd만 최솟값으로 변환
            if(routes[i][0] <= minEnd) {
                minEnd = Math.min(minEnd, routes[i][1]);
            }
            // 범위가 겹치지 않으면 단속 카메라 수를 1 증가
            else if(routes[i][0] > minEnd) {
                count++;
                minEnd = Math.max(minEnd, routes[i][1]);
            }
        }
        
        return count;
    }
}
