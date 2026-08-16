import java.util.ArrayList;
import java.util.List;

/**
 * n+1 카드 게임
 */
class Solution {
    List<Integer> my_deck;
    List<Integer> new_deck;
    int n;
    int coin_count;

    public int solution(int coin, int[] cards) {        
        int answer = 1;

        n = cards.length;
        my_deck = new ArrayList<>();
        coin_count = coin;

        for(int i = 0; i < n/3; i++){
            my_deck.add(cards[i]);
        }

        int count = n/3;
        new_deck = new ArrayList<>();


        while(count < n){
            new_deck.add(cards[count++]);
            new_deck.add(cards[count++]);

            if(! isCardsExist()){ break; }
            answer++;
        }

        return answer;
    }

    public boolean isCardsExist(){
        // case 01. 이미 갖고 있는 카드 안에서 해결 (코인 소모 0)
            for(int i = 0; i < my_deck.size()-1; i++){
                int target_num = n+1 - my_deck.get(i);
                for(int j = i+1; j < my_deck.size(); j++){
                    if(my_deck.get(j) == target_num){
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