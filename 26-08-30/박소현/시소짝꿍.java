import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        Map<Integer, Integer> m = new HashMap<>();
        Integer[] wArr;
        
        for(int w : weights)
        {
            m.compute(w, (k, v)-> {
                if(v == null)
                {
                    return 1;
                }
                return v + 1;
            });
        }
        
        wArr = m.keySet().toArray(new Integer[m.size()]);
        Arrays.sort(wArr);
        
        for(int i = 0; i < wArr.length; i++)
        {
            Integer w = wArr[i];
            long cnt = m.get(w);
            
            if(cnt > 1)
            {
                answer += ((long)cnt * (cnt - 1)) / 2;
            }
            for(int j = i + 1; j < wArr.length; j++)
            {
                Integer other = wArr[j];
                //이미 정렬해서, w < other 확정
                if(3 * w == 2 * other || 2 * w == other || 4 * w == 3 * other)
                {
                    answer += (long)m.get(w) * m.get(other);
                }
            }
        }
        
        return answer;
    }
}

