import java.util.*;

class Solution {
    
     // 두 Set에서 합이 n + 1이 되는 카드 찾기
    private int[] findPair(Set<Integer> set1, Set<Integer> set2, int n) {

        for (int card : set1) {

            int pair = n + 1 - card;

            // 자기 자신과 짝일 때는 패스
            if (card == pair) {
                continue;
            }

            if (set2.contains(pair)) {
                return new int[]{card, pair};
            }
        }

        return null;
    }

    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int initial = n / 3;

        Set<Integer> hand = new HashSet<>();
        Set<Integer> extra = new HashSet<>();

        // 처음 n / 3장 뽑기
        for (int i = 0; i < initial; i++) {
            hand.add(cards[i]);
        }

        int index = initial;
        int round = 1;

        while (index < n) {

            // 현재 라운드 카드 2장 뽑기
            extra.add(cards[index]);
            extra.add(cards[index + 1]);
            index += 2;

            // 1. 가지고 있는 카드 2개 조합
            int[] pair = findPair(hand, hand, n);

            // 가지고 있는 카드 중 짝이 있으면 다음 라운드로
            if (pair != null) {
                hand.remove(pair[0]);
                hand.remove(pair[1]);

                round++;
                continue;
            }

            // 2. 가지고 있는 카드 + 뽑은 카드 조합
            pair = findPair(hand, extra, n);

            if (pair != null) {
                // coin이 1보다 작은 경우 카드를 더 뽑을 수 없으므로 round 리턴
                if (coin < 1) {
                    return round;
                }

                coin--;

                hand.remove(pair[0]);
                extra.remove(pair[1]);

                round++;
                continue;
            }

            // 3. 뽑은 카드 두 개 조합
            pair = findPair(extra, extra, n);

            if (pair != null) {
                if (coin < 2) {
                    return round;
                }

                coin -= 2;

                extra.remove(pair[0]);
                extra.remove(pair[1]);

                round++;
                continue;
            }

            // 어떤 방법으로도 n+1을 만들 수 없음
            // 최대 라운드이기 때문에 리턴
            return round;
        }

        return round;
    }
}
