import java.util.*;
class Solution {
    

    public String solution(String number, int k) {

        int stIdx=0;
        
        while(k>0 && stIdx < number.length()){
            int maxIdx=stIdx;
            
            // 문자열 처음 부터 K범위 내 젤 큰 인덱스 찾기
            for(int i=stIdx+1; i<=stIdx+k && i < number.length(); i++){
                if(number.charAt(i) > number.charAt(maxIdx)){
                    maxIdx = i;
                }
            }
            
            if(maxIdx != stIdx) {
               number = number.substring(0,stIdx) + number.substring(maxIdx);
                k -= maxIdx - stIdx;
            }
            stIdx++; // 이번 탐색 할때 맨 앞숫자 유지
            

            
            

        }

        // 다 돌고 k남앗을때 맨 뒤 k개 자르기
        if (k > 0) number = number.substring(0, number.length() - k);  
            return number;
        
    }      
}
     
  
    
