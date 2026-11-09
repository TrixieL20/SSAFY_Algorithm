import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        // 무게가 무거운 순으로 정렬
        Arrays.sort(people);
        
        int left = 0;
        int right = people.length - 1;
        int count = 0;
        
        while(left < right) {
            // 제일 작은 수와 큰 수의 함이 limit보다 작거나 같으면
            // 다음 포인터로 이동 및 count 증가
            if(people[left] + people[right] <= limit) {
                count++;
                left++;
                right--;
            } 
            // limit을 넘어가면 큰 무게만 보트에 탑승
            // right만 다음 인덱스로 이동
            else {
                count++;
                right--;
            }
            // 하나 남은 사람을 탑승 시킬 보트 수 증가
            if(right == left) {
                count++;
            }
        }
        
        return count;
    }
}
//50 70 80
