import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        // 무적권을 가장 enemy 많은 k개에 사용하는 게 최대 라운드 수 일 것
        // -> 최소 힙 보장하는 우선순위 큐로 enemy 수 가장 많은 k개 라운드 저장
        PriorityQueue<Integer> maxEnemies = new PriorityQueue<>();

        // 문제 조건 중 k가 500,000 이하고 enemy 길이가 1,000,000 이하란 조건만 있었을 뿐 k <= enemy란 보장 없음 
        // 따라서 k가 enemy 길이보다 큰 경우 아래 반복문에서 ArrayIndexOutOfBoundsException 발생. 그래서 한 번 틀렸음
        // 반복문 전에 k가 더 크면 enemy.length return ! 
        if(k >= enemy.length){
            return enemy.length;
        }

        // 무적권 사용 가능한 k개 저장
        for(int i = 0; i < k; i++){
            maxEnemies.add(enemy[i]);
        }
        
        int sum = 0;
        int round = k;

        for(int i = k; i < enemy.length; i++){
            // 우선순위 큐는 최소 힙 -> 일단 다음 enemy 넣고 poll 하면 가장 큰 k개 유지 가능
            // 빼낸 건 무적권 안 쓰므로 sum에 더함 
            maxEnemies.add(enemy[i]);
            sum += maxEnemies.poll();
            // 병사 수보다 sum이 많아지면 그 라운드 실패 이므로 break
            if(n < sum){
                break;
            }
            // 병사 수가 sum보다 작거나 같으면 방어 가능 -> round++
            round++;
        }
        return round;
    }
}