import java.util.*;

class Solution {
    
    //카드 인덱스를 기준으로 몇 라운드에 나오는지 계산
    int convertIndexToRound(final int n, final int idx)
    {
        if(idx < (n / 3)) // 처음 3분의 1은 미리 손에 있음
        {
            return 0;
        }
        return ((idx - (n / 3)) / 2) + 1; // 3분의 1 이후는 2쌍씩 라운드 카운트
    }

    public int solution(int coin, int[] cards) {
        int n = cards.length;
        List<int[]> pairs = new ArrayList<>();
        
        int a, b; // a + b = n + 1
        //a + b == n + 1인 짝을 찾아 pairs에 추가
        for(int i = 0; i < n - 1; i++)
        {
            a = cards[i];
            if(a == -1) //이미 짝이 된 건 건너뛰기
            {
                continue;
            }
            for(int j = i + 1; j < n; j++)
            {
                b = cards[j];
                if(a + b == n + 1)
                {
                    int aRound = convertIndexToRound(n, i);
                    int bRound = convertIndexToRound(n, j);
                    pairs.add(new int[]{aRound, bRound});
                    cards[j] = -1;
                    break;
                }
            }
        }
        
        //어차피 p1[0]과 p2[0]는 오름차순으로 이미 정렬되어 있음(index 기준으로 계산이 되어 있음)
        Collections.sort(pairs, (p1, p2)->{
            return p1[1] - p2[1];
        });
        
        int maxRound = n / 3;
        
        // 카드 두 장 낼 수 없으면 즉시 종료
        for(int round = 1; round <= maxRound; round++)
        {
            //짝을 찾아보면서 낼 수 있는 카드 쌍 찾기
            List<int[]> temp = new ArrayList<>();
            
            for(int idx = 0; idx < pairs.size(); idx++)
            {
                int[] pair = pairs.get(idx);
                if(pair[0] > round || pair[1] > round)
                {
                    continue;
                }
                
                int needCoin = 0;
                if(pair[0] > 0)
                {
                    needCoin++;
                }
                if(pair[1] > 0)
                {
                    needCoin++;
                }
                
                if(coin >= needCoin)
                {
                    temp.add(new int[]{idx, needCoin});
                }
            }
            
            //낼 수 있는 쌍들 필요한 coin 갯수 오름차수 순으로 정렬
            Collections.sort(temp, (t1, t2) -> {
                return t1[1] - t2[1];
            });
            //더이상 낼 수 있는 카드가 없다.
            if(temp.isEmpty())
            {
                return round;
            }
            
            //coin 갯수 제일 적게 필요한 쌍 쓰기
            int idx = temp.get(0)[0];
            int needCoin = temp.get(0)[1];
            
            coin -= needCoin;
            
            //N은 무조건 maxRound보다 크므로 못 쓰게 막기 -> 넣고 빼는 것보다 빠를 듯
            // N은 최대 996이기 때문에 pairs는 최대 498
            // 최악의 경우 498 * 498 * 332(MaxRound 값) = 82_337_328번 계산
            pairs.get(idx)[0] = n;
            pairs.get(idx)[1] = n;
            
        }
        
        //여기까지 왔다는 건 더이상 카드를 뽑을 수 없는 라운드까지 왔다는 뜻
        return maxRound + 1;
    }
}
