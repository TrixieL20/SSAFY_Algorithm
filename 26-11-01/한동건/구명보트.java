import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        int end = people.length-1;

        // 젤 작은거랑 더하서 limit이 안넘는 인덱스 구하기
        for(int i=people.length-1; i>=0; i--){
            if(people[i]+people[0]<=limit){
                end = i;
                
                answer += people.length - (end + 1);
                break;
            }  
        }
        
        
        // 두 사람 탈 수 있는지 투포인터
        int left = 0;
        int right = end;
        
        while(left < right){
            int sum = people[left]+people[right];
            
            if(sum<=limit){
                answer++; // 둘이 타는 쌍 증가
                left++;
                right--;
            }
            
            else if(sum > limit){
                right--;
                answer++; // 혼자 타는애 증가
            }
            
        }
        
    
        // 혼자 남았을때
        if(left==right)
            answer++;
        
    
        
        
        return answer;
    }
}