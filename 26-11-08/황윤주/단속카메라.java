import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        //1. routes 첫번째 원소 기준으로 오름차순 정렬 (start는 자동으로 줄어들게 됨)
        Arrays.sort(routes, (a,b)->Integer.compare(a[0], b[0]));
        
        //2. tart end로 구간 설정한 다음에 end 갱신하기
        int count = 1;
        int end = routes[0][1];
        
        for(int i=0; i<routes.length; i++){
            int start = routes[i][0];
            
            if(start<=end){ // 범위 안에 들어오는 경우 
                // 겹치는 부분에서 end값이 더 작은걸로 갱신
                end = Math.min(end, routes[i][1]);
            }else{
                 // 겹치는 부분에서 벗어나면 카메라 한대 추가
                count++;
                // end 갱신
                end = routes[i][1];
            }
            
        }
        
        
       return count;
        
    }
}