import java.util.*;

class Solution {
    // 입력 값으로 받은 cards, coin 멤버변수로 받기
    int[] cards;
    int coin;
    
    // 초기에 받는 카드 + 라운드마다 뽑는 카드를 저장하는 배열
    List<Integer> currentCards = new ArrayList<>();
    
    // 라운드 마다 뽑는 카드 저장하는 배열
    List<Integer> pulledCards = new ArrayList<>();
    
    
    // 다음 라운드로 갈 수 있는지 판단 메서드
    public boolean canNextRound(){
        
        int N = cards.length +1;
        int size = currentCards.size(); 
        int current_coin = coin;    
    
        
        for(int i=0; i<size-1; i++){
            for(int j=i+1; j<size; j++){
                
                // 합이 N이 되는 한 쌍이 있으면
                if(currentCards.get(i)+currentCards.get(j)==N){
                    
                    // 합이 N이 되는 한 쌍 값 저장 
                    // (리스트.remove() 하면 인덱스가 앞으로 당겨져서 값을 매개변수로 지울려고 저장)
                    int val1 = currentCards.get(i);
                    int val2 = currentCards.get(j);
                    
                    // 지우려는 카드가 뽑은 카드였다면 코인 지불
                    if(pulledCards.contains(val1)){
                        pulledCards.remove(Integer.valueOf(val1));
                        coin--;
                    }
                    
                    // 지우려는 카드가 뽑은 카드였다면 코인 지불
                    if(pulledCards.contains(val2)){
                        pulledCards.remove(Integer.valueOf(val2));
                        coin--;
                    }
                    
                    // 코인 부족하면 코인 원상복구
                    if(coin < 0){
                        
                        coin = current_coin;
                        // 한 쌍이 N이 되는 카드 찾기 for문 재개
                        continue;
                    }
                    
                    // 합이 N이 되는 쌍 버리기 
                    currentCards.remove(Integer.valueOf(val1));
                    currentCards.remove(Integer.valueOf(val2));
                    
                    return true;
                }
            }
        }
        
        return false;
    }
    
    
    public int solution(int coin, int[] cards) {
        int round = 1;
        
        int initial_size = cards.length/3;
        
        // 다음 뽑을 카드 시작점 저장
        int card_cursor = initial_size;
        
        this.cards = cards;
        this.coin = coin;
        
        // 맨 처음 라운드 초기화 (카드 intial_size장 갖기)
        for(int i=0; i<initial_size; i++){
            currentCards.add(cards[i]);
        }
        
        while(true){
            
            // 카드 두장 뽑아서 현재 카드 / 뽑은 카드 배열에 넣기
            for(int i=0; i<2; i++){
                pulledCards.add(cards[card_cursor]);
                currentCards.add(cards[card_cursor]);
                card_cursor++;
            }
            if(canNextRound()){
                round++;
                
                if(card_cursor>=cards.length){
                    break;
                }
                    
            }else{
                break;
            }     
        }
        
        
        return round;
    }// end of solution class
    
    
} // end of class