import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);

        //구명 보트 탔는지 저장
        boolean[] isSaved = new boolean[people.length];

        // 남은 사람들 수
        int count = people.length;

        // 최대한 100에 가깝되 초과하면 안 됨 -> 투 포인터 ! 
        // 가장 가벼운 사람(앞 인덱스) 고름 
        // -> 가장 무거운 사람(뒷 인덱스)부터 합이 limit 이하 되는 경우 찾음

        int light = 0 ;
        int heavy = people.length -1;

        while(light <= heavy){
          if(people[light] + people[heavy] <= limit){
            light++;
          }
          // 가장 가벼운 사람이랑도 못 태우는 가장 무거운 사람 -> 혼자 타야함
          // heavy 왼쪽 이동 + 구명보트 1개 추가
          heavy--;
          answer++;
        }

        /*
        처음에는 그리디로 생각해 풀었음 
        i와 j 양 끝에 두고(i 가벼움 j 무거움) i와 합했을 때 처음으로 limit 이하 되는 j 찾는 방식
        결과는 맞지만 효율성 검사 몇 개 시간 초과 -> 투 포인터로 개선함

          for (int i = 0; i < people.length; i++) {
          if(count <= 1){
            break;
          }
          if(isSaved[i]){
            continue;
          }
          // people[i]가 함께 타는 2인 중 작은 쪽
          // -> limit / 2 넘어가는 경우 조합 안 돼 혼자 타야 함
          if(people[i] > limit / 2){
            count--;
            answer++;
            isSaved[i] = true;
            continue;
          }
          int target = limit - people[i];
          for(int j = people.length-1; j > i; j--){
            if(isSaved[j]){
              continue;
            }
            if(people[j] > target){
              continue;
            }
            isSaved[j] = true;
            isSaved[i] = true;
            count -= 2;
            answer++;
            break;
          }
        }
        answer += count;
         */
        return answer;
    }
}