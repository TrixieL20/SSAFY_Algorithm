import java.util.*;

class Solution {

    public int solution(int coin, int[] cards) {

        int n = cards.length;

        // 현재 가지고 있는 카드
        Set<Integer> hand = new HashSet<>();

        // 이번 라운드에 뽑았지만 아직 가지고 있지 않은 카드
        Set<Integer> candidate = new HashSet<>();

        // 처음 n/3장
        for (int i = 0; i < n / 3; i++) {
            hand.add(cards[i]);
        }

        int round = 1;

        // 이후 카드 2장씩
        for (int i = n / 3; i < n; i += 2) {

            candidate.add(cards[i]);
            candidate.add(cards[i + 1]);

            // 코인 0개, hand 안에서 짝 찾기
            if (check(hand, hand, n)) {
                round++;
                continue;
            };


            // 코인 1개, hand와 candidate에서 찾기
            if (coin >= 1) {
                if (check(hand, candidate, n)) {
                    round++;
                    coin--;
                    continue;
                }
            }

            // 코인 2개, candidate 2개에서 내기
            if (coin >= 2) {
                if (check(candidate, candidate, n)) {
                    round++;
                    coin -= 2;
                    continue;
                }
            }

            // 모든 방법 실패
            break;
        }

        return round;
    }

    private boolean check(Set<Integer> deck1, Set<Integer> deck2, int n) {
        Integer a = null;
        Integer b = null;

        for (int card : deck1) {
            int pair = n + 1 - card;

            if (deck2.contains(pair)) {
                a = card;
                b = pair;
                break;
            }
        }

        if (a != null) {
            deck1.remove(a);
            deck2.remove(b);

            return true;
        }
        return false;
    }
}