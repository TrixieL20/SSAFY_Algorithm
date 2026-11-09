import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int ans = 0;
        boolean[] visited = new boolean[people.length];
         
        // 1. 오름차순 정렬
        Arrays.sort(people);
        
        // 2. 투포인터로 계산 
        int front = 0;
        int rear = people.length-1;
        
        while(front<rear){
            int sum = people[front] + people[rear];
                
            // limit보다 크면 맨 끝 인덱스 줄이기
            if(sum > limit){
                rear--;
            }else{
                // limit보다 작거나 같으면 두 개 선택 
                ans++;
                visited[front] = true;
                visited[rear] = true;
                front++;
                rear--;
            }   
        }
        
        // 방문 처리 안한 친구들은 한명씩 구명보트 태우기
        for(int i=0; i<people.length; i++){
            if(visited[i]) continue;
            ans++;
            
        }
        
        return ans;
        
    }
}