import java.util.ArrayList;
import java.util.List;

/**
 * n+1 카드 게임
 */
class Solution {
    // 기존에 뽑아서 갖고 있던 카드
    List<Integer> my_deck;
    // 이후 새로 뽑게 되는 카드
    List<Integer> new_deck;
    // 전체 카드 개수
    int n;
    // 동전 개수
    int coin_count;

    public int solution(int coin, int[] cards) {        
        int answer = 1;
        // 이후 method에 넣기 위해 전역 변수들에 값 넣기 ! 
        n = cards.length;
        my_deck = new ArrayList<>();
        coin_count = coin;

        // n/3-1까지의 카드는 처음에 갖게 되는 카드 -> my_deck에 넣기
        for(int i = 0; i < n/3; i++){
            my_deck.add(cards[i]);
        }
 
        // my_deck or new_deck에 넣을 남은 cards index 세는 count
        // 남은 카드가 0이 되면 게임 종료 조건 이걸로 체크
        int count = n/3;
        new_deck = new ArrayList<>();


        // 남은 카드 0일 때까지 반복 수행
        while(count < n){
            // 새 카드 2 장 뽑기
            new_deck.add(cards[count++]);
            new_deck.add(cards[count++]);

            // 기존 카드 or 새 카드로 n+1 만들 수 있는지 확인해 주는 method isCardExist 호출
            // -> 실패하면 round 추가 않고 끝
            if(! isCardsExist()){ break; }
            // 성공 시 round 추가 -> 다시 수행
            answer++;
        }

        return answer;
    }

    // 기존 카드 or 새 카드로 n+1 만들 수 있는지 확인해 주는 method
    public boolean isCardsExist(){
        // case 01. 이미 갖고 있는 카드 안에서 해결 (코인 소모 0)
            for(int i = 0; i < my_deck.size()-1; i++){
                int target_num = n+1 - my_deck.get(i);
                for(int j = i+1; j < my_deck.size(); j++){
                    if(my_deck.get(j) == target_num){
                        // 이때 뽑는 카드 두 장 중 index 더 뒤인 j를 먼저 remove 해 줘야 오류 안 생김 ! 
                        my_deck.remove(j);
                        my_deck.remove(i);
                        return true;   
                    }
                }
            }

            // case 02. 이미 갖고 있는 카드 1 + 새로 뽑은 카드 1로 해결 (코인 소모 1)
            if(coin_count < 1){ return false; }

            for(int i = 0; i < my_deck.size(); i++){
                int target_num = n+1 - my_deck.get(i);
                for(int j = 0; j < new_deck.size(); j++){
                    if(new_deck.get(j) == target_num){
                        my_deck.remove(i);
                        new_deck.remove(j);
                        coin_count--;
                        return true;
                    }
                }
            }

            // case 03. 새로 뽑은 카드 2로 해결 (코인 소모 2)
            if(coin_count < 2){ return false; }

            for(int i = 0; i < new_deck.size()-1; i++){
                int target_num = n+1 - new_deck.get(i);
                for(int j = i+1; j < new_deck.size(); j++){
                    if(new_deck.get(j) == target_num){
                        // 이때 뽑는 카드 두 장 중 index 더 뒤인 j를 먼저 remove 해 줘야 오류 안 생김 ! 
                        new_deck.remove(j);
                        new_deck.remove(i);
                        coin_count -= 2;
                        return true;   
                    }
                }
            }

            return false;
    }
}