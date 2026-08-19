import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {

        int n = cards.length;

        // 처음 가진 카드
        List<Integer> hand = new ArrayList<>();

        // 새로 뽑은 카드들
        List<Integer> candidates = new ArrayList<>();

        for(int i = 0; i < n / 3; i++) {
            hand.add(cards[i]);
        }

        int idx = n / 3;
        int round = 1;

        while(true) {

            // 더 뽑을 카드가 없으면 종료
            if(idx >= n) {
                return round;
            }

            // 카드 2장 뽑기
            candidates.add(cards[idx++]);
            candidates.add(cards[idx++]);

            boolean canProceed = false;

            // 1. 손에 있는 카드 2개제출 (코인 x)
            for(int i = 0; i < hand.size(); i++) {
                int card = hand.get(i);
                int target = n + 1 - card;

                int targetIdx = hand.indexOf(target);

                if(targetIdx != -1 && targetIdx != i) {
                    // 큰 인덱스부터 삭제
                    hand.remove(Math.max(i, targetIdx));
                    hand.remove(Math.min(i, targetIdx));

                    canProceed = true;
                    break;
                }
            }

            if(canProceed) {
                round++;
                continue;
            }

            // 2. 손에 있는 카드 1개 + 새로 뽑은 카드 1개 (코인 1개 소모)
            if(coin >= 1) {

                for(int i = 0; i < hand.size(); i++) {

                    int card = hand.get(i);
                    int target = n + 1 - card;

                    int targetIdx = candidates.indexOf(target);

                    if(targetIdx != -1) {

                        hand.remove(i);
                        candidates.remove(targetIdx);

                        coin--;

                        canProceed = true;
                        break;
                    }
                }
            }

            if(canProceed) {
                round++;
                continue;
            }

            // 3. 새로 뽑은 카드 2개 (코인 2)
            if(coin >= 2) {

                for(int i = 0; i < candidates.size(); i++) {

                    int card = candidates.get(i);
                    int target = n + 1 - card;

                    int targetIdx = candidates.indexOf(target);

                    if(targetIdx != -1 && targetIdx != i) {

                        candidates.remove(Math.max(i, targetIdx));
                        candidates.remove(Math.min(i, targetIdx));

                        coin -= 2;

                        canProceed = true;
                        break;
                    }
                }
            }

            if(canProceed) {
                round++;
            } else {
                return round;
            }
        }
    }
}