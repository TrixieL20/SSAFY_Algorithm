import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        // 구간 끝나는 순으로 오름차순 정렬 
        Arrays.sort(routes,(a,b)-> Integer.compare(a[1], b[1]));
                        
        int idx =0;
        

        // 모든 구간을 돌기 
        while(idx <= routes.length-1){
            
            int endPoint = routes[idx][1];
            
            // 현 구간의 끝나는 지점보다 시작점이 작은 구간들 스킵, 현 구간 끝 지점보다 시작점이 큰 idx를 찾는다
            while(idx < routes.length && endPoint >= routes[idx][0]){
                idx++;
            }
            
            // 카메라 추가
            answer++;
            
            
        }
        return answer;
}}